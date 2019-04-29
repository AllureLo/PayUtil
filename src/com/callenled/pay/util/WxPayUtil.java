package com.callenled.pay.util;

import com.callenled.pay.annotations.MappingValueAnno;
import com.callenled.pay.wechat.api.BaseWxPayModel;
import com.callenled.pay.wechat.api.WxPayApiException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Author: Callenld
 * @Date: 19-4-29
 */
public class WxPayUtil {

    /**
     * 生成签名
     * @param object 对象参数
     * @param key 签名密钥
     * @return
     */
    public static String createSign(Object object, String key){
        //序列化-反序列化转换key值
        String json = GsonUtil.gsonString(object);
        Map<String, Object> map = GsonUtil.gsonToMaps(json);
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        List<Map.Entry<String, Object>> infoIds = new ArrayList<>(map.entrySet());
        infoIds.sort(Comparator.comparing(o -> (o.getKey())));
        return createSign(infoIds, key);
    }

    /**
     * 生成签名
     * @param collection 集合
     * @param key 签名密钥
     * @return
     */
    public static String createSign(Collection<Map.Entry<String, Object>> collection, String key) {
        // 构造签名键值对的格式
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : collection) {
            String k = entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        //最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
        sb.append("key=").append(key);
        //进行MD5加密
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes()).toUpperCase();
    }

    /**
     * 将封装好的参数转换成Xml格式类型的字符串
     * @param object 对象参数
     * @return
     */
    public static String object2Xml(Object object) {
        List<Field> list = FieldUtil.getDeclaredFields(object);
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        String sign = "";
        for (Field field : list) {
            // 实体属性的映射处理
            String fromField = null;
            String defaultValue = null;
            try {
                // 有注解获取注解信息
                if (field.isAnnotationPresent(MappingValueAnno.class)) {
                    MappingValueAnno fieldAnno = field.getAnnotation(MappingValueAnno.class);
                    fromField = fieldAnno.fromField();
                    defaultValue = fieldAnno.defaultValue();
                }
                Object oldValue;
                if (null != defaultValue && !"".equals(defaultValue)) {
                    oldValue = defaultValue;
                } else {
                    // 当fromField映射的属性没有时候，默认获取自身的属性，即新老属性一致
                    if (null == fromField || "".equals(fromField)) {
                        fromField = field.getName();
                    }
                    //表示原有实体的属性
                    Field oldF = FieldUtil.getDeclaredField(object, fromField);
                    // 取消属性的访问权限控制，即使private属性也可以进行访问。
                    assert oldF != null;
                    oldF.setAccessible(true);
                    // 调用get()方法取得对应属性值。
                    oldValue = oldF.get(object);
                }
                if (oldValue == null) {
                    continue;
                } else if ("sign".equalsIgnoreCase(fromField)) {
                    sign = oldValue.toString();
                    continue;
                }

                String k = fromField;
                String v = oldValue.toString();
                if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)) {
                    sb.append("<").append(k).append(">").append("<![CDATA[").append(v).append("]]></").append(k).append(">");
                } else {
                    sb.append("<").append(k).append(">").append(v).append("</").append(k).append(">");
                }
                // 实体中属性为null，xml转时回忽略掉，统一做空字符处理
                //field.set(toBean,null == oldValue ? "" : oldValue);
            } catch (Exception e) {
                // 实体中属性为null，xml转时回忽略掉，统一做空字符处理
            }
        }
        sb.append("<" + "sign" + ">" + "<![CDATA[").append(sign).append("]]></").append("sign").append(">");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * xml解析转换成object
     * @param xml 报表
     * @param clazz 对象
     * @throws WxPayApiException
     */
    public static <T> T xml2Object(String xml, Class<T> clazz) throws WxPayApiException {
        return xml2Object(xml, clazz, false, null);
    }

    /**
     * xml解析转换成object
     * @param xml 报表
     * @param clazz 对象
     * @param signature 是否校验签名
     * @param key 签名密钥
     * @throws WxPayApiException
     */
    public static <T> T xml2Object(String xml, Class<T> clazz, boolean signature, String key) throws WxPayApiException {
        SortedMap<String, Object> map = new TreeMap<>();
        xml = xml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(in);
            Element root = doc.getRootElement();
            List list = root.getChildren();
            for (Object aList : list) {
                Element e = (Element) aList;
                String k = e.getName();
                Object v;
                List children = e.getChildren();
                if (children.isEmpty()) {
                    v = e.getTextNormalize();
                } else {
                    v = getChildrenText(children);
                }
                map.put(k, v);
            }
        } catch (IOException | JDOMException e) {
            throw new WxPayApiException(e.getMessage(), e);
        } finally {
            // 关闭流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (CollectionUtils.isEmpty(map)) {
            throw new WxPayApiException("xml解析对象为空");
        }
        //是否校验签名
        if (signature) {
            // 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
            String sign = map.get("sign").toString();
            String resultSign = createSign(map.entrySet(), key);
            if (StringUtils.isEmpty(sign)) {
                throw new WxPayApiException("API返回的数据签名数据不存在");
            }
            if (sign.toUpperCase().equals(resultSign)) {
                throw new WxPayApiException("API返回的数据签名不一致，有可能被第三方篡改!!!");
            }
        }
        //序列化-反序列化转换key值
        String json = GsonUtil.gsonString(map);
        return GsonUtil.gsonToBean(json, clazz);
    }

    private static Object getChildrenText(List children) {
        StringBuilder sb = new StringBuilder();
        if (!children.isEmpty()) {
            for (Object aChildren : children) {
                Element e = (Element) aChildren;
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<").append(name).append(">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</").append(name).append(">");
            }
        }
        return sb.toString();
    }
}
