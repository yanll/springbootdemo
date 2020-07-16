package tk.techforge.springdemo.utils;


import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Created by YAN on 2015/09/30.
 */
public class UtilJackson {
    private static final Logger logger = LoggerFactory.getLogger(UtilJackson.class);
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final JsonFactory factory = mapper.getFactory();

    /**
     * 创建一个新的ObjectNode对象
     *
     * @return
     */
    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    /**
     * 创建一个新的ArrayNode对象
     *
     * @return
     */
    public static ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    /**
     * 格式化输出
     *
     * @param object
     * @return
     */
    @Deprecated
    public static String writerWithDefaultPrettyPrinter(Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("UtilJackson.writerWithDefaultPrettyPrinter error", e);
        }
    }

    /**
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            logger.error("UtilJackson.readValue error:" + content, e);
            throw new RuntimeException("UtilJackson.readValue error", e);
        }
    }

    /**
     * @param content
     * @return
     */
    public static JsonNode readTree(String content) {
        try {
            return mapper.readTree(content);
        } catch (IOException e) {
            logger.error("UtilJackson.readTree error:" + content, e);
            throw new RuntimeException("UtilJackson.readTree error", e);
        }
    }

    public static JsonNode readTree(InputStream is) {
        try {
            return mapper.readTree(is);
        } catch (IOException e) {
            throw new RuntimeException("UtilJackson.readTree error", e);
        }

    }


    /**
     * 创建JSON处理器的静态方法
     *
     * @param content JSON字符串
     * @return
     */
    private static JsonParser createParser(String content) {
        try {
            return factory.createParser(content);
        } catch (IOException e) {
            logger.error("UtilJackson.createParser error:" + content, e);
            throw new RuntimeException("UtilJackson.createParser error", e);
        }
    }

    /**
     * 创建JSON生成器的静态方法, 使用标准输出
     *
     * @return
     */
    private static JsonGenerator createGenerator(StringWriter sw) {
        try {
            return factory.createGenerator(sw);
        } catch (IOException e) {
            throw new RuntimeException("UtilJackson.createGenerator error", e);
        }
    }

    /**
     * JSON对象序列化
     */
    public static String toJSON(Object obj) {
        StringWriter sw = new StringWriter();
        JsonGenerator jsonGen = createGenerator(sw);
        if (jsonGen == null) {
            try {
                sw.close();
            } catch (IOException e) {
                logger.error("UtilJackson StringWriter close error", e);
            }
            return null;
        }
        try {
            jsonGen.writeObject(obj);
            jsonGen.flush();
            jsonGen.close();
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("UtilJackson.toJSON JsonGenerationException", e);
        }
    }

    /**
     * JSON对象反序列化
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        try {
            JsonParser jp = createParser(json);
            return jp.readValueAs(clazz);
        } catch (JsonParseException e) {
            logger.error("UtilJackson.fromJSON JsonParseException:" + json, e);
            throw new RuntimeException("UtilJackson.fromJSON JsonParseException", e);
        } catch (JsonMappingException e) {
            logger.error("UtilJackson.fromJSON JsonMappingException:" + json, e);
            throw new RuntimeException("UtilJackson.fromJSON JsonMappingException", e);
        } catch (IOException e) {
            logger.error("UtilJackson.fromJSON IOException:" + json, e);
            throw new RuntimeException("UtilJackson.fromJSON IOException", e);
        }
    }

    /**
     * 复杂对象JSON转换
     *
     * @param content
     * @param valueTypeRef
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJSON(String content, TypeReference<?> valueTypeRef) {
        try {
            return (T) mapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            logger.error("UtilJackson.fromJSON IOException:" + content, e);
            throw new RuntimeException("UtilJackson.fromJSON IOException", e);
        }
    }

    public static <T> List<T> fromJSONtoList(String json, Class<T> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz, clazz);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            logger.error("UtilJackson.fromJSONtoList IOException:" + json, e);
            throw new RuntimeException("UtilJackson.fromJSONtoList IOException", e);
        }
    }

    public static <K, V> Map<K, V> fromJSONtoMap(String json, Class<K> class_key, Class<V> class_value) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, class_key, class_value);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            logger.error("UtilJackson.fromJSONtoMap IOException:" + json, e);
            throw new RuntimeException("UtilJackson.fromJSONtoMap IOException", e);
        }
    }

    public static Object readValueNode(JsonNode node) {
        try {
            if (node == null) return null;
            if (!node.isValueNode()) {
                return null;
            }
            if (node.isTextual()) {
                return node.textValue();
            }
            if (node.isInt()) {
                return node.intValue();
            }
            if (node.isLong()) {
                return node.longValue();
            }
            if (node.isBoolean()) {
                return node.booleanValue();
            }
            if (node.isShort()) {
                return node.shortValue();
            }
            if (node.isDouble()) {
                return node.doubleValue();
            }
            if (node.isFloat()) {
                return node.floatValue();
            }
            if (node.isBigDecimal()) {
                return node.decimalValue();
            }
            if (node.isNumber()) {
                return node.numberValue();
            }
            if (node.isBinary()) {
                return node.binaryValue();
            }
            return node.asText();
        } catch (IOException e) {
            throw new RuntimeException("UtilJackson.readValueNode IOException", e);
        }
    }


}
