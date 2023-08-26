package com.somesh.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.somesh.model.AddressResp;

@FeignClient(name="ADDRESS-SERVICE", path = "/address-app")
public interface AddressServiceFeignClient {
	
	@RequestMapping(value="/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<AddressResp> getAddress(@PathVariable("id") int id);
	
	@GetMapping(value="/getAddresses")
	public ResponseEntity<List<AddressResp>> getAllAddresses();
}
