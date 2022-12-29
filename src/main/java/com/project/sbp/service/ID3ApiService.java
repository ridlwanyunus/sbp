package com.project.sbp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import com.project.sbp.dto.EdgeMapper;
import com.project.sbp.dto.NodeMapper;
import com.project.sbp.model.Edge;
import com.project.sbp.model.Node;
import com.project.sbp.model.ResponseTemplate;
import com.project.sbp.utils.AppConstant;
import com.project.sbp.utils.ID3ApiUtils;

@Service
@EnableConfigurationProperties({ID3ApiUtils.class})
public class ID3ApiService {
	
	@Autowired
	private ID3ApiUtils apiUtils;
	
	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private EdgeService edgeService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	static int counter = 0;
	
	@Transactional
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
				
				 JSONObject jsonObject = new JSONObject(jsonString);
				 nodeService.truncate();
				 edgeService.truncate();
				 
				 recursive(jsonObject);
				 recursiveEdge(jsonObject);
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
		            
		            nodeService.save(key);
		            Object value = jsonObject.get(key);
		            if(value instanceof JSONArray) {
		            	JSONArray array = (JSONArray) value;
		            	for(int i=0; i<array.length();i++) {
		            		JSONObject object = (JSONObject) array.get(i);
		            		Iterator<String> innerKeys = object.keys();
		            		while(innerKeys.hasNext()) {
		            			String innerKey = innerKeys.next();
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
		            			//System.out.println(String.format("Not JSONArray 1 counter: %s from: %s to: %s", counter, key, innerKey));

		            			//edgeService.save(from.getIdNode(), to.getIdNode());
		            		}
		            		recursive(object);
		            } else {
		            	nodeService.save(value.toString());
		            	//System.out.println(String.format("Not JSONArray 2 counter: %s from: %s to: %s", counter, key, value.toString()));

		            }
		            
		        }

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void recursiveEdge(JSONObject jsonObject) {
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
		            			System.out.println(String.format("JSONArray counter: %s from: %s to: %s", counter, key, innerKey));
		            			Node from = nodeService.findByLabel(key);
		            			Node to = nodeService.findByLabel(innerKey);
		            			System.out.println(String.format("JSONArray counter: %s from: %s to: %s", counter, from.getIdNode(), to.getIdNode()));
		            			edgeService.save(from.getIdNode(), to.getIdNode());
		            		}
		            		
		            	}
		                
		                for(int i=0; i<array.length(); i++) {
		                	JSONObject object = (JSONObject) array.get(i);
		                	recursiveEdge(object);
		                }
		                
		            } else
		            if (value instanceof JSONObject) {
		            	
		            	JSONObject object = (JSONObject) value;
		            		Iterator<String> innerKeys = object.keys();

		            		while(innerKeys.hasNext()) {
		            			String innerKey = innerKeys.next();
		            			System.out.println(String.format("Not JSONArray 1 counter: %s from: %s to: %s", counter, key, innerKey));
		            			
		            			Node from = nodeService.findByLabel(key);
		            			Node to = nodeService.findByLabel(innerKey);
		            			System.out.println(String.format("Not JSONArray 2 counter: %s from: %s to: %s", counter, from.getIdNode(), to.getIdNode()));
		            			edgeService.save(from.getIdNode(), to.getIdNode());
		            		}
		            		Node from = nodeService.findByLabel(key);
		            		from.setUsed(1);
	            			nodeService.save(from);
		            		recursiveEdge(object);
		            } else {
		            	int childCounter = ++counter;
//		            	nodeService.save(value.toString());
		            	
		            	//System.out.println(String.format("Node: %s to: %s", childCounter, value.toString()));
		            	System.out.println(String.format("Not JSONArray 2 counter: %s from: %s to: %s", counter, key, value.toString()));
		            	Node from = nodeService.findByLabel(key);
            			Node to = nodeService.findByLabel(value.toString());
            			System.out.println(String.format("Not JSONArray 2 counter: %s from: %s to: %s", counter, from.getIdNode(), to.getIdNode()));
            			edgeService.save(from.getIdNode(), to.getIdNode());
            			from.setUsed(1);
            			to.setUsed(1);
            			nodeService.save(from);
            			nodeService.save(to);
		            }
		            
		        }

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ResponseTemplate visualizeTreeDate() {
		ResponseTemplate response = new ResponseTemplate();
		List<Node> nodes = nodeService.findAll();
		List<Edge> edges = edgeService.findAll();
		
		List<NodeMapper> nodeMappers = new ArrayList<NodeMapper>();
		
		for(Node node: nodes) {
			NodeMapper nodeMapper = new NodeMapper();
			nodeMapper.setId(node.getIdNode());
			nodeMapper.setLabel(node.getLabel());
			nodeMappers.add(nodeMapper);
		}
		
		List<EdgeMapper> edgeMappers = new ArrayList<EdgeMapper>();
		for(Edge edge: edges) {
			EdgeMapper edgeMapper = new EdgeMapper();
			edgeMapper.setFrom(edge.getNode());
			edgeMapper.setTo(edge.getChild());
			edgeMappers.add(edgeMapper);
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nodes", nodeMappers);
		map.put("edges", edgeMappers);
		
		response.setCode(AppConstant.CODE_SUCCESS);
		response.setStatus(AppConstant.STATUS_SUCCESS);
		response.setMessage("Success get data visualizaition");
		response.setData(map);
		return response;
	}
	
}
