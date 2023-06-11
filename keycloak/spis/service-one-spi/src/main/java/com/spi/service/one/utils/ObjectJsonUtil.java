package com.spi.service.one.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spi.service.one.adapter.ObjectAdapter;
import com.spi.service.one.client.dto.ObjectDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObjectJsonUtil {
    public static List<String> getObjectJson(List<Object> objectList) throws JsonProcessingException {
        List<String> objectJsonList = new ArrayList<>();
        for (Object object : objectList) {
            ObjectAdapter objectAdapter = new ObjectAdapter(object);
            objectJsonList.add(new ObjectMapper().writeValueAsString(objectAdapter));
        }
        return objectJsonList;
    }

    public static ObjectDTO getObject(String objectJson) throws JsonProcessingException {
        if (Objects.isNull(objectJson)) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        var objectDTO = objectMapper.readValue(
                objectJson,
                ObjectDTO.class
        );
        return objectDTO;
    }

}
