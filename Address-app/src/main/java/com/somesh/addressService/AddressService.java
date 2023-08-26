package com.somesh.addressService;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somesh.dao.AddressDao;
import com.somesh.model.Address;
import com.somesh.model.AddressResp;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;
	
	@Autowired
	private ModelMapper map;
	
	public AddressResp getAddress(int id) {
		
		Address address = dao.findById(id).get();
		
		AddressResp addResp = map.map(address, AddressResp.class);
		
		return addResp;
	}
	
	public List<AddressResp> getAllAddresses() {

		List<Address> addresses = dao.findAll();
		 List<AddressResp> respList = Arrays.asList(map.map(addresses, AddressResp[].class));
		return respList;
	}
	
	public Integer addAddress(Address add) {
		// dao.insertAddress(add.getLane(), add.getZip(), add.getState(), add.getEmp_id());
		Integer count = dao.insertAddress(add);
		return count;
	}
	
}
