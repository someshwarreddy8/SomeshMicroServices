package com.somesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.somesh.addressService.AddressService;
import com.somesh.model.Address;
import com.somesh.model.AddressResp;

@RestController
public class AddressController {
	
	@RequestMapping("/details")
	public String getAddressDetails() {
		
		return "this is customer address....";
	}

	@Autowired
	private AddressService service;
	
	@RequestMapping(value="/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<AddressResp> getAddress(@PathVariable("id") int id) {
		System.out.println("id : "+id);
		AddressResp resp = service.getAddress(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(resp); 
	}
	
	@GetMapping(value="/getAddresses")
	public ResponseEntity<List<AddressResp>> getAllAddresses() {
		
		List<AddressResp> allAddresses = service.getAllAddresses();
		
		return ResponseEntity.status(HttpStatus.OK).body(allAddresses);
	}
	
	@PostMapping("addAddress")
	public ResponseEntity<Integer> addAddress(@RequestBody Address add) {
		Integer count = service.addAddress(add);
		return ResponseEntity.status(HttpStatus.OK).body(count);
	}
}
