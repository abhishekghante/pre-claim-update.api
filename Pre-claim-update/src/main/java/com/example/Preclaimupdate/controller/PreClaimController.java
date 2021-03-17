package com.example.Preclaimupdate.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.example.Preclaimupdate.common.Config;
import com.example.Preclaimupdate.common.CustomMethods;
import com.example.Preclaimupdate.entity.Admin_user;
import com.example.Preclaimupdate.entity.Case_lists;
import com.example.Preclaimupdate.entity.Case_movement;
import com.example.Preclaimupdate.entity.Request;
import com.example.Preclaimupdate.entity.Response;
import com.example.Preclaimupdate.service.PreClaimService;

@RestController
@RequestMapping
public class PreClaimController {
	
	Logger logger = LoggerFactory.getLogger(PreClaimController.class);

	@Autowired
	private PreClaimService pre;
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody Request username) {
		Response jsonResponse;
		Admin_user user = pre.getbyusername(username.getUsername());
		Encoder encoder = Base64.getEncoder();
		if (user != null && user.getPassword() != null && user.getRole_name().equals("INV")
				&& user.getPassword().equals(encoder.encodeToString(username.getPassword().getBytes()))) 
		{
			jsonResponse = new Response();
			jsonResponse.setData(user);
			jsonResponse.setStatus("Login successful");
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} 
		else 
		{
			jsonResponse = new Response();
			jsonResponse.setStatus("Invalid credentials");
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		}
	}

	@PostMapping("/forgot")
	public ResponseEntity<Response> get(@RequestBody Request username)
	{
		Response jsonResponse = new Response();
		try
		{
			if (username.getUsername() != null) 
			{
				String pass = RandomStringUtils.random(6, true, true);
				pre.Sendmail(username.getUsername(), pass);
				jsonResponse = new Response();
				jsonResponse.setStatus("Temporary Password sent to registered Email ID");
				return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
			} 
			else
				jsonResponse.setStatus("Username not found");
		}
		catch(Exception e)
		{
			jsonResponse.setStatus(e.getMessage());
			CustomMethods.logError(e);
			return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<Response> changePassword(@RequestBody Request username)
	{ 
		Response jsonResponse;
		HashMap<String, String> log = new HashMap<String, String>();
		System.out.println(pre.changepassword(username));
		if (pre.changepassword(username)) 
		{
			log.put("error_code", "****");
			log.put("error_description", "Password changed successfully");
		} 
		else 
		{
			log.put("error_code", "failed");
			log.put("error_description", "Invalid Credentials");
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
		if (log != null) 
		{
			Case_movement caserepo = pre.findByCaseId(id);
			Admin_user user = pre.getbyusername(caserepo.getFromId());
			log.setRemarks(caserepo.getRemarks());
			log.setFromUser(user.getFull_name());
	
			jsonResponse = new Response();
			jsonResponse.setData(log);
			jsonResponse.setStatus("****");
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} 
		else 
		{
			jsonResponse = new Response();
			jsonResponse.setData(null);
			jsonResponse.setStatus("Case not maching with System");
			return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

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

	@PostMapping("/updateUserDetails")
	public ResponseEntity<Response> updateUserDetails(@RequestParam("updatePhoto") MultipartFile updatePhoto,
			HttpServletRequest request) 
	{
		Response jsonResponse = new Response();
		HashMap<String, String> log = new HashMap<String, String>();
		String candidatePhoto = "";	
		try
		{
			if(updatePhoto != null)
			{
				candidatePhoto = updatePhoto.getOriginalFilename();
				Files.write(Paths.get(Config.uploadDirectory + candidatePhoto),updatePhoto.getBytes());
			}
			String username  = request.getParameter("username");
			String password  = request.getParameter("password");
			String full_name = request.getParameter("full_name");
			String emailId   = request.getParameter("emailId");
			String contactNumber = request.getParameter("contactNumber");
			
			if(username == null)
			{
				log.put("error_code","Failed");
				log.put("error_description", "User ID not entered");
			}
			
			Admin_user user  = pre.getbyusername(username);
			
			if(user == null)
			{
				log.put("error_code","Failed");
				log.put("error_description", "Invalid User ID");
			}
			
			if(full_name != null)
				user.setFull_name(full_name);
			if(password != null)
			{
				Encoder encoder = Base64.getEncoder();
				user.setPassword(encoder.encodeToString(password.getBytes()));
			}
			if(emailId != null)
				user.setUser_email(emailId);
			if(contactNumber != null)
				user.setMobile_number(contactNumber);
			if(!candidatePhoto.equals(""))
				user.setUser_image(candidatePhoto);
			
			if(log.size() == 0)
			{
				pre.save(user);
				jsonResponse.setStatus("****");
				jsonResponse.setData("Candidate Details updated successfully");
			}
			else
			{
				jsonResponse.setData(log);
			}
		}
		catch(Exception ex)
		{
			log.put("error_code","Failed");
			log.put("error_description", ex.getMessage());
			CustomMethods.logError(ex);
		}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}
	
}
