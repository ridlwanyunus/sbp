package com.project.sbp.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sbp.dto.DatasetRequest;
import com.project.sbp.dto.InferensiRequest;
import com.project.sbp.model.ResponseTemplate;
import com.project.sbp.utils.AppConstant;
import com.project.sbp.utils.ID3ApiUtils;

@Service
public class InferensiService {

	@Autowired
	private ID3ApiUtils apiUtils;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public ResponseTemplate predict(InferensiRequest request) {
		ResponseTemplate response = new ResponseTemplate();
		try {
			
			DatasetRequest message = new ObjectMapper().convertValue(request, DatasetRequest.class);
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			
			MultiValueMap<String, DatasetRequest> map = new LinkedMultiValueMap<String, DatasetRequest>();
			map.add("message", message);
			
			HttpEntity<MultiValueMap<String, InferensiRequest>> body = new HttpEntity(map, headers);
			
			String url = apiUtils.getUrl();
			
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, body, String.class);
			if(result.getBody() != null) {
				response.setCode(AppConstant.CODE_SUCCESS);
				response.setStatus(AppConstant.STATUS_SUCCESS);
				response.setMessage("Success get prediction");
				response.setData(result.getBody().toString().replaceAll("[\\r\\n]", "").replaceAll("[\\\"]", ""));
			} else {
				response.setCode(AppConstant.CODE_ERROR);
				response.setStatus(AppConstant.STATUS_ERROR);
				response.setMessage("Failed to get prediction");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(AppConstant.CODE_ERROR);
			response.setStatus(AppConstant.STATUS_ERROR);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	
}
