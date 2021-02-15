package com.example.Preclaimupdate.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Preclaimupdate.entity.Admin_user;
import com.example.Preclaimupdate.entity.Case_lists;
import com.example.Preclaimupdate.entity.Request;
import com.example.Preclaimupdate.entity.Response;
import com.example.Preclaimupdate.service.PreClaimService;

@RestController
@RequestMapping()
public class PreClaimController {
	
	Logger logger =LoggerFactory.getLogger(PreClaimController.class);

	@Autowired
	private PreClaimService pre;

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody Request username) {
		logger.error("Controller=/login -> ");
		Response jsonResponse;
		Admin_user user = pre.getbyusername(username.getUsername());
		Encoder encoder = Base64.getEncoder();
		if (user != null && user.getPassword() != null
				&& user.getPassword().equals(encoder.encodeToString(username.getPassword().getBytes()))) 
		{
			jsonResponse = new Response();
			jsonResponse.setData(user);
			jsonResponse.setStatus("Login Sucsses");
			logger.error("Controller=/login -> Login Sucsses"+user.getFull_name());
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} 
		else 
		{
			jsonResponse = new Response();
			jsonResponse.setStatus("Invalid credentials");
			logger.error("Controller=/login -> Invalid credentials"+username.getUsername());
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		}
	}

	@PostMapping("/forgot")
	public ResponseEntity<Response> get(@RequestBody Request username)
	{
		logger.error("Controller=/forgot ->");
		Response jsonResponse;
		try
		{
			Admin_user user = pre.getbyusername(username.getUsername());
			if (user != null) 
			{
				logger.error("Controller=/forgot -> user found "+user.getFull_name());
				String pass = RandomStringUtils.random(6, true, true);
				pre.Sendmail(user, pass);
				logger.error("Controller=/forgot -> Mail Sent");
				jsonResponse = new Response();
				jsonResponse.setStatus("Temporary Password sent to registered Email ID");
				return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
			} 
			else
				jsonResponse = new Response();
			jsonResponse.setStatus("Username not found");
		}
		catch(Exception e)
		{
			logger.error("Controller exception",e);
			jsonResponse = new Response();
			jsonResponse.setStatus(e.getMessage());
			return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<Response> changePassword(@RequestBody Request username)
	{ 
		logger.error("Controller=/changePassword ->");
		Response jsonResponse;
		HashMap<String, String> log = new HashMap<String, String>();
		System.out.println(pre.changepassword(username));
		if (pre.changepassword(username)) 
		{
			logger.error("Controller=/changePassword -> user found "+username.getUsername());
			log.put("error_code", "****");
			log.put("error_description", "Password changed successfully");
			logger.error("Controller=/changePassword -> user found password changed successfully");
		} 
		else 
		{
			log.put("error_code", "failed");
			log.put("error_description", "Invalid Credentials");
			logger.error("Controller=/changePassword -> Invalid Credentials");
		}
		jsonResponse = new Response();
		jsonResponse.setData(log);
		jsonResponse.setStatus("Success");
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PostMapping("/getCaseDetailsByCaseId")
	public ResponseEntity<Response> getCaseDetailsByCaseId(@RequestBody Request username) {
		int id = username.getCaseid();
		System.out.println(id + username.getUsername());
		Case_lists log = pre.GetCaseDetailsByCaseId(id);
		Response jsonResponse;
		if (log != null) {
			jsonResponse = new Response();
			jsonResponse.setData(log);
			jsonResponse.setStatus("Assigned");
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} else {
			jsonResponse = new Response();
			jsonResponse.setData(null);
			jsonResponse.setStatus("Case not maching with System");
			return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/// pending
	@PostMapping("/GetCaseListByUsername")
	public List<Case_lists> GetCaseListByUsername(@RequestBody Request username) 
	{
		//Input Parameters
		String investigatorId = username.getUsername();
		int pageSize = username.getPagesize();
		int pageNum = username.getPageNum();

		int min = pageSize*(pageNum - 1) + 1;
		int max	= pageNum*pageSize;
		
		return 	pre.GetCaseListByUsername(investigatorId, min, max);	
	}
	
	@PostMapping("/dashboard")
	public ResponseEntity<Response> dashboard(@RequestBody Request username) 
	{
		Response jsonResponse = new Response();
		HashMap<String, Object> log = pre.dashboard(username);
		if(username.getUsername() != null) 
		{
			jsonResponse.setData(log);
			jsonResponse.setStatus("****");
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);		
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<Response> uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFile,
			HttpServletRequest request) throws IOException {
		Response jsonResponse = new Response();
		HashMap<String, Object> log = pre.fileupload(uploadedFile, request);
		if (log.isEmpty()) 
		{
			jsonResponse.setData("File uploaded successfully");
			jsonResponse.setStatus("*****");
		} 
		else 
		{
			jsonResponse.setData(log);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PostMapping("/updateCaseDetails")
	public ResponseEntity<Response> updateCaseDetails(@RequestBody Request username) 
	{
		Response jsonResponse = new Response();
		jsonResponse.setData(pre.updateCaseDetails(username));	
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

}
