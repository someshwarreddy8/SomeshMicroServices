package com.somesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.somesh.model.Address;

import jakarta.transaction.Transactional;

public interface AddressDao extends JpaRepository<Address, Integer>{
	@Transactional
	@Modifying
	@Query(value="INSERT INTO address (lane, zip, state, emp_id) VALUES (:#{#a.lane}, :#{#a.zip}, :#{#a.state}, :#{#a.emp_id})", nativeQuery = true)
	Integer insertAddress(@Param("a") Address a);

	//@Query(value="INSERT INTO address (lane, zip, state, emp_id) VALUES (:#{#lane}, :#{#zip}, :#{#state}, :#{#emp_id})", nativeQuery = true)
    //void insertAddress(String lane, String zip, String state, int emp_id);
	

}
