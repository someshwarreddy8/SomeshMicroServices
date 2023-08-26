package com.somesh.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.somesh.dao.EmployeeDao;
import com.somesh.feignClient.AddressServiceFeignClient;
import com.somesh.model.AddressResp;
import com.somesh.model.EmpResp;
import com.somesh.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao dao;
	
	@Autowired
	private ModelMapper map;
	
	@Autowired
	private AddressServiceFeignClient feignClient;
	
	public EmpResp getEmployee(int id) {
		
		Employee employee = dao.findById(id).get();
		
		EmpResp empResp = map.map(employee, EmpResp.class);
		
		return empResp;
	}
	
	public List<EmpResp> getEmps() {
		
		List<Employee> listOfEmps = dao.findAll();
		List<EmpResp> empResp = Arrays.asList(map.map(listOfEmps, EmpResp[].class));
		
		List<AddressResp> addressResp = feignClient.getAllAddresses().getBody();
		
		empResp.stream().forEach(t -> {
			addressResp.stream().forEach(a -> {
				if (t.getId() == a.getEmp_id()) {
					t.setAddressResp(a);
				}
			});

		});
		return empResp;
	}
}
