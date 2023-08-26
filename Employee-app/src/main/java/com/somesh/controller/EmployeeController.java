package com.somesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.somesh.feignClient.AddressServiceFeignClient;
import com.somesh.model.AddressResp;
import com.somesh.model.EmpResp;
import com.somesh.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AddressServiceFeignClient feignClient;
	/*  * 
	 *  * public EmployeeController(@Value("${addressService.base.url}") String
	 * baseUrl, RestTemplateBuilder builder) { this.restTemplate = builder
	 * .rootUri(baseUrl) .build(); }
	 */
	@Autowired
	private WebClient webClient;
	
	@RequestMapping(value="/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmpResp> getEmployee(@PathVariable("id") int id) {
		
		EmpResp resp = service.getEmployee(id);
		
		AddressResp addRespObj = feignClient.getAddress(id).getBody();
		resp.setAddressResp(addRespObj);
		
		return ResponseEntity.status(HttpStatus.OK).body(resp); 
	}

	private AddressResp restCalUsingWebClient(int id) {
		
		return webClient.get()
				        .uri("/getById/"+id)
				        .retrieve()
				        .bodyToMono(AddressResp.class)
				        .block();
	}

	 //restCal Using RestTemplate
		/*
		 * private AddressResp restCalUsingRestTemplate(int id) {
		 * 
		 * ServiceInstance serviceInstance =
		 * loadBalancerClient.choose("ADDRESS-SERVICE"); String uri =
		 * serviceInstance.getUri().toString();
		 * 
		 * return
		 * restTemplate.getForObject("http://ADDRESS-SERVICE/address-app/getById/{id}",
		 * AddressResp.class, id); }
		 */
	
	@GetMapping(value="getEmps")
	public List<EmpResp> getEmps() {

		List<EmpResp> emps = service.getEmps();

		return emps;

	}
}
