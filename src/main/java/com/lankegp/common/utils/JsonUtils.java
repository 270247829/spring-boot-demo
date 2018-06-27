package com.lankegp.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return object.toString();
        }
    }


    public static <T> List<T> readValueAsArray(String json, Class<T> valueType) {
        List<T> list = null;
        try {
            JsonNode node = getObjectMapper().readTree(json);
            if (node.isArray()) {
                list = new ArrayList<>(node.size());
                for (int i = 0; i < node.size(); i++) {
                    JsonNode n = node.get(i);
                    T t = readValue(n.toString(), valueType);
                    list.add(t);
                }
            } else {
                list = new ArrayList<T>(1);
                T t = readValue(node.toString(), valueType);
                list.add(t);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> T readValue(String json, Class<T> valueType) {
        T object = null;
        try {
            object = getObjectMapper().readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
