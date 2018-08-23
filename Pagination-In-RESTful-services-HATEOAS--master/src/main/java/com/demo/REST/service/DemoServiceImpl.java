package com.demo.REST.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.REST.pojo.Employee;
import com.demo.REST.repository.EmployeeRepository;

@org.springframework.stereotype.Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		//List<Employee> addEmp = new ArrayList<>();
		 return employeeRepository.findAll();
				/*.forEach(addEmp :: add);
		 return addEmp;*/
	}
	
	public Employee getEmpById(int id) {
		return employeeRepository.findById(id).get();
	}
	
	public void updateEmployee(int id, Employee employee) {
		if(employeeRepository.existsById(id))
			employeeRepository.save(employee);
	}
	
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	
	
}
