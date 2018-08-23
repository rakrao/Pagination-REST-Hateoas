package com.demo.REST.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.REST.pojo.Employee;
import com.demo.REST.service.DemoServiceImpl;

@RestController
public class EmployeeController {
	
	@Autowired
	private DemoServiceImpl service;
	
	@RequestMapping(value="/employee", method=RequestMethod.POST)
	public void addEmployee(@RequestBody Employee employee) {
		service.addEmployee(employee);
	}
	
	@RequestMapping(value="/employees")
	public List<Employee> getAllEmployees() {
		return service.getAllEmployee();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/employees/{start}/{count}")
	public Resources getEmployeesByPagination(@PathVariable int start, @PathVariable int count) {
		List<Employee> employee = new ArrayList<>();
		List<Employee> tempEmployeeList = service.getAllEmployee();
		for(int i=start ; i< (start + count) ; i++) {
			employee.add(tempEmployeeList.get(i - 1)); 
		}
		
		if(start <= 3) {
			if(start == 1) {
				Link nextLink = linkTo(methodOn(this.getClass()).getEmployeesByPagination(start+count, count)).withRel("nextLink");
				@SuppressWarnings({ "unchecked" })
				Resources resources = new Resources(employee, nextLink);
				return resources;
			}
			else if(start == 2) {
				Link prevLink = linkTo(methodOn(this.getClass()).getEmployeesByPagination(1, 2)).withRel("previousLink");
				Link nextLink = linkTo(methodOn(this.getClass()).getEmployeesByPagination(start+count, count)).withRel("nextLink");
				@SuppressWarnings({ "unchecked" })
				Resources resources = new Resources(employee, prevLink, nextLink);
				return resources;
			}
			else {
				Link prevLink = linkTo(methodOn(this.getClass()).getEmployeesByPagination(1, 3)).withRel("previousLink");
				Link nextLink = linkTo(methodOn(this.getClass()).getEmployeesByPagination(start+count, count)).withRel("nextLink");
				@SuppressWarnings({ "unchecked" })
				Resources resources = new Resources(employee, prevLink, nextLink);
				return resources;
			}
		}
		else {
			Link nextLink = linkTo(methodOn(this.getClass()).getEmployeesByPagination(start+count, count)).withRel("nextLink");
			Link prevLink = linkTo(methodOn(this.getClass()).getEmployeesByPagination(start-count>0 ? start - count : 1, count)).withRel("previousLink");
			@SuppressWarnings({ "unchecked" })
			Resources resources = new Resources(employee, prevLink, nextLink);
			return resources;
		}
		//return resources;
	}
	
	@RequestMapping(value="/employees/{id}")
	public Resource<Employee> getEmployeeById(@PathVariable int id) {
		Employee employee = service.getEmpById(id);
		Link getAllLink = linkTo(methodOn(this.getClass()).getAllEmployees()).withRel("See All");
		Resource<Employee> resource = new Resource<Employee>(employee, getAllLink);
		return resource;
	}
	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable int id) {
		service.deleteEmployee(id);
	}
	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.PUT)
	public void updateeEmployee(@PathVariable int id, @RequestBody Employee employee ) {
		service.updateEmployee(id, employee);
	}
}
