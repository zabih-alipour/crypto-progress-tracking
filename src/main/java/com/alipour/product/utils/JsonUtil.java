package com.alipour.product.utils;

import com.alipour.product.models.MarketStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JsonUtil {
    private ObjectMapper mapper;

    public JsonUtil(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public JsonNode toJson(Object o) {
        try {
            return readAsJson(mapper.writeValueAsString(o));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonNode readAsJson(String txt) {
        try {
            return mapper.readTree(txt);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    TypeReference<HashMap<String, MarketStatus>> typeRef
            = new TypeReference<HashMap<String, MarketStatus>>() {
    };

    public Map<String, MarketStatus> mapFromJson(JsonNode node) {
        try {
            return mapper.readValue(node.toString(), typeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
