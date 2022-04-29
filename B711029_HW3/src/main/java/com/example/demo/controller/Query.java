package com.example.demo.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {	// 마리아DB로 쿼리문을 날려 결과를 받아오는 메소드가 있는 클래스
	
	public Query() {}	// 클래스 생성자
	
    public List<Result> query_one() throws SQLException {	// 마리아DB로 step1의 쿼리를 날리는 query_one() 메소드
		List<Result> rs_list1 = new ArrayList<Result>();	// Result 클래스 객체들의 리스트인 rs_list 생성
    	try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/HW2", "root", "980627")) {
            try (Statement stmt = conn.createStatement()) {
            	try (ResultSet rs2 = stmt.executeQuery("UPDATE Sailors\n"
                		+ "SET age = 25.5\n"
                		+ "WHERE sname = \"Andy\"")){
                }
            	// Step 1. 18세 이상인 두 명 이상의 뱃사람을 가지는 등급에 대해 각각 등급별 투표가 가능한 뱃사람들의 평균 나이 구하기 
            	try (ResultSet rs1 = stmt.executeQuery("SELECT S.rating, AVG(S.age) as avgage\n"
                		+ "FROM Sailors S\n"
                		+ "WHERE S.age >= 18\n"
                		+ "GROUP BY S.rating\n"
                		+ "HAVING 1 < (SELECT COUNT(*)\n"
                		+ "			FROM Sailors S2\n"
                		+ "			WHERE S.rating = S2.rating and S2.age >= 18)")) {
                	while(rs1.next()) {	// 결과가 더 존재하면 반복
                		int rating1 = rs1.getInt("S.rating");	// 결과로 나온 등급을 rating1에 저장
                		float avgage1 = rs1.getFloat("avgage");	// 결과로 나온 평균 나이를 avgage1에 저장
                		// Result클래스의 객체를 생성한 뒤, rating1과 avgage1을 객체에 저장하고 그 객체를 rs_list에 추가해준다.
                    	Result rs_one = new Result(rating1, avgage1);
                		rs_list1.add(rs_one);
                	}
                }
            	return rs_list1;	// 결과 리스트 리턴
            }
        }
    }
    
    public List<Result> query_two() throws SQLException{	// step2 쿼리
    	List<Result> rs_list2 = new ArrayList<Result>();
    	try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/HW2", "root", "980627")) {
            try (Statement stmt = conn.createStatement()) {
            	// Step 2-1. Andy의 나이 30으로 UPDATE
                try (ResultSet rs2 = stmt.executeQuery("UPDATE Sailors\n"
                		+ "SET age = 30\n"
                		+ "WHERE sname = \"Andy\"")){
                }
                // Step 2-2. step1의 질의를 반복 
                try (ResultSet rs3 = stmt.executeQuery("SELECT S.rating, AVG(S.age) as avgage\n"
                		+ "FROM Sailors S\n"
                		+ "WHERE S.age >= 18\n"
                		+ "GROUP BY S.rating\n"
                		+ "HAVING 1 < (SELECT COUNT(*)\n"
                		+ "			FROM Sailors S2\n"
                		+ "			WHERE S.rating = S2.rating and S2.age >= 18)")) {
                	while(rs3.next()) {
                		int rating2 = rs3.getInt("S.rating");	// Step 2. 결과로 나온 등급 
                		float avgage2 = rs3.getFloat("avgage");	// Step 2. 결과로 나온 평균 나이
                		Result rs_two = new Result(rating2, avgage2);
                		rs_list2.add(rs_two);
                	}
                }
                return rs_list2;
            }
        }
    }
    
}