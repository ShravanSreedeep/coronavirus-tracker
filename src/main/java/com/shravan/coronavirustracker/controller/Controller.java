package com.shravan.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shravan.coronavirustracker.model.Model;
import com.shravan.coronavirustracker.service.CoronaVirusDataServices;

@RestController
@RequestMapping("/coronacount")
public class Controller{
	
	@Autowired
	CoronaVirusDataServices cs;
	@RequestMapping("/")
	public List<Model> getAll(){
		return cs.getAll();
	}
	
	@RequestMapping("/total")
	public String totalCase(){
		return "Total Cases : " + cs.totalCase();
	}
	
	
	
	
	
}