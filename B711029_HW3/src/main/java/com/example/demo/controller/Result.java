package com.example.demo.controller;

// 마리아DB로 날린 쿼리의 결과로 얻은 각 등급과 등급 별 평균 나이를 갖는 클래스
public class Result {
	public int rs_rating;	// 18세 이상인 두 명 이상의 뱃사람을 가지는 등급
	public float rs_avgage;	// 등급별 투표가 가능한 뱃사람들의 평균 나이
	
	public Result() {	// 클래스 생성자
	}
	public Result(int rs_rating, float rs_avgage) {	// 인자를 받는 클래스 생성자
	      super();
	      this.rs_rating = rs_rating;
	      this.rs_avgage = rs_avgage;
	   }

}
