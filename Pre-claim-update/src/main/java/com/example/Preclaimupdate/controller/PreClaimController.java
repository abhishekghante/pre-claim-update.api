package com.example.Preclaimupdate.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
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
import com.example.Preclaimupdate.req.Request;
import com.example.Preclaimupdate.res.Responce;
import com.example.Preclaimupdate.service.PreClaimService;

@RestController
@RequestMapping(path = "preclaim")
public class PreClaimController {

	@Autowired
	private PreClaimService pre;

	@PostMapping("/login")
	public ResponseEntity<Responce> login(@RequestBody Request username) {
		Responce jsonResponse;
		Admin_user user = pre.getbyusername(username.getUsername());
		Encoder encoder = Base64.getEncoder();
		if (user != null && user.getPassword() != null
				&& user.getPassword().equalsIgnoreCase(encoder.encodeToString(username.getPassword().getBytes()))) {
			jsonResponse = new Responce();
			jsonResponse.setData(user);
			jsonResponse.setStatus("Login Sucsses");

			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} else {
			jsonResponse = new Responce();
			jsonResponse.setStatus("Enter registerd Username & password");
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		}
	}

	@PostMapping("/forgot")
	public ResponseEntity<Responce> get(@RequestBody Request username)
			throws UnsupportedEncodingException, MessagingException {
		Responce jsonResponse;
		Admin_user user = pre.getbyusername(username.getUsername());
		if (user != null) {
			String pass = RandomStringUtils.random(6, true, true);
			pre.Sendmail(user, pass);
			jsonResponse = new Responce();
			jsonResponse.setStatus("Temporary Password sent to registered Email ID");
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} else
			jsonResponse = new Responce();
		jsonResponse.setStatus("Username not found");
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PostMapping("/changePassword")
	public ResponseEntity<Responce> changePassword(@RequestBody Request username) {
		Responce jsonResponse;
		HashMap<String, String> log = new HashMap<String, String>();

		System.out.println(pre.changepassword(username));
		if (pre.changepassword(username)) {
			log.put("error_code", "****");
			log.put("error_description", "Password changed successfully");
		} else {
			log.put("error_code", "failed");
			log.put("error_description", "Invalid Credentials");
		}
		jsonResponse = new Responce();
		jsonResponse.setData(log);
		jsonResponse.setStatus("Sucsses");
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PostMapping("/getCaseDetailsByCaseId")
	public ResponseEntity<Responce> getCaseDetailsByCaseId(@RequestBody Request username) {
		int id =username.getCaseid();
		System.out.println(id + username.getUsername());
		HashMap<String, Object> log = pre.GetCaseDetailsByCaseId(id);
		Responce jsonResponse;
		if (log != null) {
			jsonResponse = new Responce();
			jsonResponse.setData(log);
			jsonResponse.setStatus("Assigned");
			return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
		} else {
			jsonResponse = new Responce();
			jsonResponse.setData(null);
			jsonResponse.setStatus("Case not maching with System");
			return new ResponseEntity<>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/// pending
	@PostMapping("/GetCaseListByUserId")
	public List<Case_lists> GetCaseListByUserId(@RequestBody Request username) {
		int id =username.getPagesize();
		System.out.println(id);
		System.out.println(username.getStatus());
		System.out.println(username.getUsername());
		System.out.println(pre.GetCaseListByUserId(id,username.getStatus(),username.getUsername()));
		return 	pre.GetCaseListByUserId(id,username.getStatus(),username.getUsername());		
	}
	
	@PostMapping("/dashboard")
	public ResponseEntity<Responce> dashboard(@RequestBody Request username) {
		Responce jsonResponse = new Responce();
		HashMap<String, Object> log = pre.dashboard(username);
		if(username.getUsername()!=null) {
		jsonResponse.setData(log);
		jsonResponse.setStatus("Dashboard");
	}
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);		
	}

///till here//	

	@PostMapping("/uploadFile")
	public ResponseEntity<Responce> uploadFile(@RequestParam("uploadedFile") MultipartFile uploadedFile,
			HttpServletRequest request) throws IOException {
		Responce jsonResponse = new Responce();
		HashMap<String, Object> log = pre.fileupload(uploadedFile, request);
		System.out.println(log);
		if (log.isEmpty()) {
			jsonResponse.setStatus("Updated the parameters in case_lists");
		} else {
			log.put("error_code", HttpStatus.INTERNAL_SERVER_ERROR);
			log.put("error_description", "case id not found");
			jsonResponse.setData(log);
		}

		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}

	@PostMapping("/updateCaseDetails")
	public ResponseEntity<Responce> updateCaseDetails(@RequestBody Request username) {
		Responce jsonResponse = new Responce();
		HashMap<String, Object> log = pre.updateCaseDetails(username);
		if (log.isEmpty()) {
			log.put("error_code", "failed");
			log.put("error_description", "Error adding case. Kindly contact system administrator");
			jsonResponse.setData(log);
		}
		jsonResponse.setData(log);
		jsonResponse.setStatus("Succses");
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

	}

}
