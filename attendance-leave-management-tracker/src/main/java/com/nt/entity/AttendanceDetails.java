package com.nt.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDetails {
	
	private Integer attendanceId;
	private Integer studentId;
	private String month;
	private Integer year;


	private Date day1;
	private String day1Status;
	private Date day2;
	private String day2Status;
	private Date day3;
	private String day3Status;
	private Date day4;
	private String day4Status;
	private Date day5;
	private String day5Status;
	private Date day6;
	private String day6Status;
	private Date day7;
	private String day7Status;
	private Date day8;
	private String day8Status;
	private Date day9;
	private String day9Status;
	private Date day10;
	private String day10Status;
	private Date day11;
	private String day11Status;
	private Date day12;
	private String day12Status;
	private Date day13;
	private String day13Status;
	private Date day14;
	private String day14Status;
	private Date day15;
	private String day15Status;
	private Date day16;
	private String day16Status;
	private Date day17;
	private String day17Status;
	private Date day18;
	private String day18Status;
	private Date day19;
	private String day19Status;
	private Date day20;
	private String day20Status;
	private Date day21;
	private String day21Status;
	private Date day22;
	private String day22Status;
	private Date day23;
	private String day23Status;
	private Date day24;
	private String day24Status;
	private Date day25;
	private String day25Status;
	private Date day26;
	private String day26Status;
	private Date day27;
	private String day27Status;
	private Date day28;
	private String day28Status;
	private Date day29;
	private String day29Status;
	private Date day30;
	private String day30Status;
	private Date day31;
	private String day31Status;

	
	private Integer totalDays;
	
	
	

}
