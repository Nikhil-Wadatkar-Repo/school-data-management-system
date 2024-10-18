package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.UniversalService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class UniversalController {
	@Autowired
	private UniversalService service;

	// create attendance sheet for student based on id

	// create attendance sheet for all students

	// update attendance of one student based on id
}
