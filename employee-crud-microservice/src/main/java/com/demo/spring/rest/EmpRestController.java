package com.demo.spring.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.EmpRepository;
import com.demo.spring.entity.Employees;

@RestController
public class EmpRestController {

	@Autowired
	EmpRepository emprepo;
	
	@PostMapping(path="emp/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmp(@RequestBody Employees e){
		if(emprepo.existsById(e.getId())) {
			return ResponseEntity.ok("Sorry, Employee already exists..");
		}else {
			emprepo.save(e);
			return ResponseEntity.ok("Employee saved successfully..");
		} 
	}
	
	@GetMapping(path="emp/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getEmp(@PathVariable("id") int id ) {
		Optional<Employees> emp = emprepo.findById(id);
		if(emp.isPresent()) {
			return ResponseEntity.ok(emp.get());
		}else {
			return ResponseEntity.ok("Employee not found");
			
		}
	}
	
	@GetMapping(path="emp/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employees>> listAll() {
		return ResponseEntity.ok(emprepo.findAll());
		
	}
	
	@PutMapping(path="emp/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmp( @RequestBody Employees e , @PathVariable("id") int id){
		Optional<Employees> emp = emprepo.findById(id);
		if(emp.isPresent()) {
			e.setId(id);
			
			emprepo.save(e);
			return ResponseEntity.ok("Employee Details Updated Successfully..");
			
		}else {
			return ResponseEntity.ok("Employee not Found..");
		}
	}
	
	@DeleteMapping(path = "emp/delete/{id}" , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteEmp(@PathVariable("id") int id){
		Optional<Employees> emp = emprepo.findById(id);
		if(emp.isPresent()) {
			emprepo.deleteById(id);
			return ResponseEntity.ok("Employee deleted Successfully..");
		}else {
			return ResponseEntity.ok("Employee not found..");
		}
	}
	
	
	
}
