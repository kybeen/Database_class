package com.example.demo.controller;
 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HomeController {
    
	@RequestMapping(value="/employee") //http://localhost:8000/employee
	 public List<Employee> getEmployees() 
	    {
	      List<Employee> employeesList = new ArrayList<Employee>();
	      employeesList.add(new Employee(1,"young","yoon","virtuoso.yoon@gmail.com"));
	      return employeesList;
	    }
	
	@RequestMapping(value="/step1")	// step1. 쿼리
	 public List<Result> getResultOne() throws SQLException
	    {
		  List<Result> resultList = new ArrayList<Result>();	// 결과로 나오는 Result 객체 리스트를 받을 resultList
		  Query step_one = new Query();	// SQL 쿼리문을 날릴 수 있는 Query클래스의 객체 step_one 생성
		  resultList = step_one.query_one();	// throw_query() 메소드로 쿼리를 날린 결과로 얻은 객체 리스트를 resultList에 저장
		  return resultList;	// 결과를 json 형식으로 리턴
	    }
	
	@RequestMapping(value="/step2")	// step2. 쿼리 위와 동일하게 진행
	 public List<Result> getResultTwo() throws SQLException
	    {
		  List<Result> resultList = new ArrayList<Result>();
		  Query step_two = new Query();
		  resultList = step_two.query_two();
		  return resultList;
	    }
    
}
