package b711029_mariadb_practice;

import java.sql.*;

public class hw2 {
    public static void main( String[] args ) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/HW2", "root", "980627")) {
            // create a Statement
            try (Statement stmt = conn.createStatement()) {
            	float[][] rs_list = new float[11][2];	// Andy의 나이 업데이트 전후의 결과를 저장할 2차원 배열 
                //execute query
            	// Step 1. 18세 이상인 두 명 이상의 뱃사람을 가지는 등급에 대해 각각 등급별 투표가 가능한 뱃사람들의 평균 나이 구하기 
            	System.out.println("Step 1.\n");
            	try (ResultSet rs1 = stmt.executeQuery("SELECT S.rating, AVG(S.age) as avgage\n"
                		+ "FROM Sailors S\n"
                		+ "WHERE S.age >= 18\n"
                		+ "GROUP BY S.rating\n"
                		+ "HAVING 1 < (SELECT COUNT(*)\n"
                		+ "			FROM Sailors S2\n"
                		+ "			WHERE S.rating = S2.rating and S2.age >= 18)")) {
                	while(rs1.next()) {
                		int rating1 = rs1.getInt("S.rating");	// Step 1. 결과로 나온 등급 
                		float avgage1 = rs1.getFloat("avgage");	// Step 1. 결과로 나온 평균 나이 
                		System.out.println("등급 : " + rating1 + ", 평균 나이 : " + avgage1);
                		rs_list[rating1][0] = avgage1;	// 등급에 해당하는 인덱스에 평균 나이 값을 저장 
                	}
                	System.out.println("");
                }
            }
        }
    }
}


/*
public class hw2 {
    public static void main( String[] args ) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/HW2", "root", "980627")) {
            // create a Statement
            try (Statement stmt = conn.createStatement()) {
            	float[][] rs_list = new float[11][2];	// Andy의 나이 업데이트 전후의 결과를 저장할 2차원 배열 
                //execute query
            	// Step 1. 18세 이상인 두 명 이상의 뱃사람을 가지는 등급에 대해 각각 등급별 투표가 가능한 뱃사람들의 평균 나이 구하기 
            	System.out.println("Step 1.\n");
            	try (ResultSet rs1 = stmt.executeQuery("SELECT S.rating, AVG(S.age) as avgage\n"
                		+ "FROM Sailors S\n"
                		+ "WHERE S.age >= 18\n"
                		+ "GROUP BY S.rating\n"
                		+ "HAVING 1 < (SELECT COUNT(*)\n"
                		+ "			FROM Sailors S2\n"
                		+ "			WHERE S.rating = S2.rating and S2.age >= 18)")) {
                	while(rs1.next()) {
                		int rating1 = rs1.getInt("S.rating");	// Step 1. 결과로 나온 등급 
                		float avgage1 = rs1.getFloat("avgage");	// Step 1. 결과로 나온 평균 나이 
                		System.out.println("등급 : " + rating1 + ", 평균 나이 : " + avgage1);
                		rs_list[rating1][0] = avgage1;	// 등급에 해당하는 인덱스에 평균 나이 값을 저장 
                	}
                	System.out.println("");
                }
            	
            	// Step 2-1. Andy의 나이 30으로 UPDATE
            	System.out.println("Step 2.\n");
                try (ResultSet rs2 = stmt.executeQuery("UPDATE Sailors\n"
                		+ "SET age = 30\n"
                		+ "WHERE sname = \"Andy\"")){
                	System.out.println("[Andy의 나이 30으로 update]");
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
                		System.out.println("등급 : " + rating2 + ", 평균 나이 : " + avgage2);
                		rs_list[rating2][1] = avgage2;	// Andy의 나이를 업데이트 한 후의 질의 결과 저장 
                	}
                }
                for(int i=0; i<11; i++) {
                	if(rs_list[i][0] != rs_list[i][1]) {
                		System.out.println("평균 나이가 변한 등급 : " + i);
                	}
                }
            }
        }
    }
}
*/
