package com.demo.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entity.Employees;

public interface EmpRepository extends JpaRepository<Employees, Integer> {

}
