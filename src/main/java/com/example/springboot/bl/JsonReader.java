package com.example.springboot.bl;

import java.util.List;

import com.example.springboot.jackson.Modeljson;
import com.example.springboot.jackson.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonReader
 *
 */
public class JsonReader 
{
    public List<Value> collectData(String jsonData) throws JsonMappingException, JsonProcessingException
    {

        ObjectMapper mapper = new ObjectMapper();

        Modeljson data = mapper.readValue(jsonData, Modeljson.class);

        List<Value> getVal = data.getGetStatsData().getStatisticalData().getDataInf().getValue();

        return getVal;
    }
}
