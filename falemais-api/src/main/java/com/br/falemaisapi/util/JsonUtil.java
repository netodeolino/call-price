package com.br.falemaisapi.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JsonUtil {
    public static String mapToJson(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException jsonProcessingException) {
            log.error(jsonProcessingException.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static <T> T mapFromJson(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clazz);
        } catch (JsonParseException jsonParseException) {
            log.error(jsonParseException.getMessage());
        } catch (JsonMappingException jsonMappingException) {
            log.error(jsonMappingException.getMessage());
        } catch (IOException iOException) {
            log.error(iOException.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
