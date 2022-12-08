package com.project.sbp.utils;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.sbp.model.Dataset;
import com.project.sbp.model.Node;
import com.project.sbp.repostiory.DatasetRepository;
import com.project.sbp.service.DatasetService;
import com.project.sbp.service.NodeService;

@Component
public class ID3Utils {

	@Autowired
	private NodeService nodeService;
	
	@Autowired
	private DatasetService datasetService;
	
	public Double calculateEntropy(List<Dataset> currentDataset, String node) {
		int positive = 0;
		int negative = 0;
		
		String positiveClassifier = findNode("yes");
		String negativeClassifier = findNode("no");
		
		Iterator<Dataset> iterator = currentDataset.iterator();
		
		System.out.println("Node: " + node);
		
		while(iterator.hasNext()) {
			Dataset dataset = iterator.next();
			
			
			if(dataset.getOutlook().equalsIgnoreCase(node)) {
				System.out.println("outlook: " + dataset.getOutlook());
				if(dataset.getTennis().equalsIgnoreCase(positiveClassifier)) {
					positive++;
				}
				if(dataset.getTennis().equalsIgnoreCase(negativeClassifier)) {
					negative++;
				}
			}
			
			if(dataset.getTemperature().equalsIgnoreCase(node)) {
				System.out.println("temperature: " + dataset.getTemperature());
				if(dataset.getTennis().equalsIgnoreCase(positiveClassifier)) {
					positive++;
				}
				if(dataset.getTennis().equalsIgnoreCase(negativeClassifier)) {
					negative++;
				}
			}
			
			if(dataset.getHumidity().equalsIgnoreCase(node)) {
				System.out.println("humidity: " + dataset.getHumidity());
				
				if(dataset.getTennis().equalsIgnoreCase(positiveClassifier)) {
					positive++;
				}
				if(dataset.getTennis().equalsIgnoreCase(negativeClassifier)) {
					negative++;
				}
			}
			
			if(dataset.getWind().equalsIgnoreCase(node)) {
				System.out.println("wind: " + dataset.getWind());
				if(dataset.getTennis().equalsIgnoreCase(positiveClassifier)) {
					positive++;
				}
				if(dataset.getTennis().equalsIgnoreCase(negativeClassifier)) {
					negative++;
				}
			}
			

		}
		double total = (double) positive + negative;
		
		System.out.println("Positive: " + positive);
		System.out.println("Negative: " + negative);
		
		double entropy = xLogx(positive/total) + xLogx(negative/total);
		return entropy;
	}
	
	private String findNode(String keyword) {
		Node node = nodeService.findClassifier(keyword);
		
		return node.getNode();
	}

	private Double xLogx(double x) {
		System.out.println("x: " + x);
		if(x == 0) {
			return 0.0;
		}
		return (x * Math.log(x) / Math.log(2));
	}
}
