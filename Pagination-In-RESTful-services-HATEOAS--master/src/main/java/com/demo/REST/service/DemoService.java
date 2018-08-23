package com.demo.REST.service;

import java.util.List;

import com.demo.REST.pojo.Employee;

public interface DemoService {

	void addEmployee(Employee employee);
	List<Employee> getAllEmployee();
	Employee getEmpById(int id);
	void deleteEmployee(int id);
	void updateEmployee(int id, Employee employee);

}