package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.repo.AttendanceDetailsRepo;

@Service
public class UniversalService {
	@Autowired
	private AttendanceDetailsRepo attendanceDetailsRepo;

}
