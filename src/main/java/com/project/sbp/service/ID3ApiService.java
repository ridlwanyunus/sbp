package com.project.sbp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sbp.model.ResponseTemplate;
import com.project.sbp.utils.AppConstant;
import com.project.sbp.utils.ID3ApiUtils;

@Service
@EnableConfigurationProperties({ID3ApiUtils.class})
public class ID3ApiService {
	
	@Autowired
	private ID3ApiUtils apiUtils;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public ResponseTemplate createDecisionTree() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseTemplate response = new ResponseTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			HttpEntity body = new HttpEntity(headers);
			
			String url = apiUtils.getUrl();
			
			ResponseEntity<Object> result = restTemplate.exchange(url, HttpMethod.GET, body, Object.class);
			
			if(result.getBody() != null) {
				response.setCode(AppConstant.CODE_SUCCESS);
				response.setStatus(AppConstant.STATUS_SUCCESS);
				response.setMessage("Success generate decision tree");
				response.setData(result.getBody());
				
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(result.getBody());
//				String jsonString = "{\r\n"
//						+ "    \"Outlook\": {\r\n"
//						+ "        \"Sunny\": {\r\n"
//						+ "            \"Humidity\": {\r\n"
//						+ "                \"High\": \"No\",\r\n"
//						+ "                \"Normal\": \"Yes\"\r\n"
//						+ "            }\r\n"
//						+ "        },\r\n"
//						+ "        \"Overcast\": \"Yes\",\r\n"
//						+ "        \"Rain\": {\r\n"
//						+ "            \"Wind\": {\r\n"
//						+ "                \"Weak\": \"Yes\",\r\n"
//						+ "                \"Strong\": \"No\"\r\n"
//						+ "            }\r\n"
//						+ "        }\r\n"
//						+ "    }\r\n"
//						+ "}";

				 JSONObject jsonObject = new JSONObject(jsonString);
				 recursive(jsonObject);
			}

		} catch(Exception e) {
			e.printStackTrace();
			response.setCode(AppConstant.CODE_ERROR);
			response.setStatus(AppConstant.STATUS_ERROR);
			response.setMessage("Cannot create decision tree");
		}
		
		
		return response;
	}
	
	public void recursive(JSONObject jsonObject) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if(jsonObject != null) {
				Iterator<String> keys = jsonObject.keys();
				while(keys.hasNext()) {
		            String key = keys.next();
		            Object value = jsonObject.get(key);
		            if(value instanceof JSONArray) {
		            	
		            	
		            	JSONArray array = (JSONArray) value;
		            	for(int i=0; i<array.length();i++) {
		            		JSONObject object = (JSONObject) array.get(i);
		            		Iterator<String> innerKeys = object.keys();
		            		while(innerKeys.hasNext()) {
		            			String innerKey = innerKeys.next();
		            			System.out.println(String.format("JSONArray from: %s to: %s", key, innerKey));
		            		}
		            		
		            	}
		                
		                for(int i=0; i<array.length(); i++) {
		                	JSONObject object = (JSONObject) array.get(i);
			                recursive(object);
		                }
		                
		            } else
		            if (value instanceof JSONObject) {

		            	JSONObject object = (JSONObject) value;
		            		Iterator<String> innerKeys = object.keys();
		            		while(innerKeys.hasNext()) {
		            			String innerKey = innerKeys.next();
		            			System.out.println(String.format("Not JSONArray 1 from: %s to: %s", key, innerKey));
		            		}
		            		recursive(object);
		            } else {
		            	System.out.println(String.format("Not JSONArray 2 from: %s to: %s", key, value.toString()));
		            }
		            
		        }

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
