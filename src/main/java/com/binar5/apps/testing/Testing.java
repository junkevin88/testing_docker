package com.binar5.apps.testing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Testing {

    @Test
    public void konversiJsonKeString() throws JsonProcessingException {
        String json = "{ \"f1\" : \"v1\" } ";

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        System.out.println(jsonNode.get("f1").asText());
        System.out.println(jsonNode);
    }

    @Test
    public void barangResponseJackson() throws JsonProcessingException {
        String json = "{\n" +
                "    \"nama\":\"nama 2\",\n" +
                "    \"stok\":200,\n" +
                "    \"satuan\":\"kg\",\n" +
                "    \"harga\":\"100000\",\n" +
                "    \"supplier\":{\n" +
                "        \"id\":123\n" +
                "    }\n" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        System.out.println(jsonNode.get("supplier").get("id").asLong());
        System.out.println(jsonNode);
    }
}
