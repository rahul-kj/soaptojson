package com.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.domain.LoanResponse;
import com.proj.service.LoanResponseService;

@RestController
@RequestMapping("/loanResponse")
public class LoanResponseController {

	@Autowired
	LoanResponseService loanResponseService;

	@RequestMapping(produces = "application/json")
	public LoanResponse getLoanResponse() {
		LoanResponse loanResponse = null;
		try {
			loanResponse = loanResponseService.getResponse();
		} catch (Exception e) {
			System.out.println("ERROR" + e.getMessage());
		}
		
		return loanResponse;
	}
}
