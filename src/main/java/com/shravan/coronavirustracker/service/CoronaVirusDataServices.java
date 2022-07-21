package com.shravan.coronavirustracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import com.shravan.coronavirustracker.model.Model;



@Service
public class CoronaVirusDataServices {
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<Model> allStats = new ArrayList<>();
	private int totalCase = 0;
	@PostConstruct
	@Autowired
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<Model> newStats = new ArrayList<>();
		
		//create a client
		HttpClient client = HttpClient.newHttpClient();
		//build a request
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL))
				.build();
		//object for response in a list of string format
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(httpResponse.body());
		StringReader body = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(body);
		for (CSVRecord record : records) {
			Model location = new Model();
			location.setState(record.get("Province/State"));
		    System.out.println(location.getState());
		    location.setCountry(record.get("Country/Region"));
		    System.out.println(location.getCountry());
		    location.setLatestTotal(Integer.parseInt(record.get(record.size()-1)));
		    this.totalCase += Integer.parseInt(record.get(record.size()-1));
		    System.out.println(location.getLatestTotal());
		    newStats.add(location);
		}
		this.allStats = newStats;
	}
	public List<Model> getAll(){
        return this.allStats;
    }
	
	public int totalCase() {
		
		return this.totalCase;
	}
	
	public List<Model> countryCase(String country) {
		List<Model> countryStats = new ArrayList<>();
		
		for(int i=0;i<allStats.size();i++) {
			
			if(allStats.get(i).getCountry().equals(country)) {
				countryStats.add(allStats.get(i));
				System.out.println(allStats.get(i));
				
			}
			//System.out.print(allStats.get(i).getCountry());
		}
		
		
		return countryStats;
		
	}
}
