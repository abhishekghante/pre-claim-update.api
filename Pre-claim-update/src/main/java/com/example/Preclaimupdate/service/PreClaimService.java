package com.example.Preclaimupdate.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Preclaimupdate.common.Config;
import com.example.Preclaimupdate.common.CustomMethods;
import com.example.Preclaimupdate.controller.Repository.AdminuserRepository;
import com.example.Preclaimupdate.controller.Repository.Audit_casemovementRepository;
import com.example.Preclaimupdate.controller.Repository.Case_movementRepository;
import com.example.Preclaimupdate.controller.Repository.CaselistsRepository;
import com.example.Preclaimupdate.controller.Repository.MailConfigRepository;
import com.example.Preclaimupdate.controller.Repository.caseSubStatusRepository;
import com.example.Preclaimupdate.entity.Admin_user;
import com.example.Preclaimupdate.entity.CaseSubStatus;
import com.example.Preclaimupdate.entity.Case_lists;
import com.example.Preclaimupdate.entity.Case_movement;
import com.example.Preclaimupdate.entity.Mail_config;
import com.example.Preclaimupdate.entity.Request;

@Service
public class PreClaimService {
	Logger logger = LoggerFactory.getLogger(PreClaimService.class);

	@Autowired
	public JavaMailSender mailSender;

	@Autowired
	private AdminuserRepository Adminuser;

	@Autowired
	private Case_movementRepository caserepo;

	@Autowired
	private CaselistsRepository Caselist;

	@Autowired
	private Audit_casemovementRepository audit_repo;
	
	@Autowired
	private MailConfigRepository mailConfig;
	
	@Autowired
	private caseSubStatusRepository casesubStatus;
	

	public Admin_user getbyusername(String username) {
		Admin_user user = Adminuser.findByUsername(username);
		if(!user.getUser_image().equals(""))
			user.setUser_image(Config.uploadURL + user.getUser_image());
		return user;

	}

	public void save(Admin_user user) {
		Adminuser.save(user);
	}
	
	public Case_movement findByCaseId(long caseId)
	{
		return caserepo.findByCaseId(caseId);
	}
	
     public void Sendmail(String username, String pass) throws UnsupportedEncodingException {
		Admin_user user = Adminuser.findByUsername(username);
		Encoder encoder = Base64.getEncoder();
		user.setPassword(encoder.encodeToString(pass.getBytes()));
		Adminuser.save(user);
		
		Mail_config mConfig =  mailConfig.findBymailConfigId(9);		
		String senderName = "Your company name";
		String toAddress = user.getUser_email();

		String subject = "You temp password ";
		String content = "Your temp password is<h3> [[name]]</h3>Kindly set your password,<br>" + "Thank you,<br>"
				       + "Your company name.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			
			helper.setFrom(mConfig.getUsername(),senderName);
			helper.setTo(toAddress);
			helper.addCc("xangars.aniketr@xangarsinfra.com");
			helper.setSubject(subject);
			content = content.replace("[[name]]", pass);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (MessagingException e) {

			CustomMethods.logError(e);
			e.printStackTrace();
		}
	}
	
	public boolean changepassword(Request username) {
		Encoder encoder = Base64.getEncoder();
		Admin_user user = Adminuser.findByUsername(username.getUsername());
		String encodedPassword = encoder.encodeToString(username.getNewpassword().getBytes());
		String oldPassword = encoder.encodeToString(username.getOldpassword().getBytes());
		if (user != null && user.getPassword().equals(oldPassword)) {
			logger.error("user found" + username.getUsername());
			user.setPassword(encodedPassword);
			user.setUpdatedDate(new Date());
			Adminuser.save(user);

			return true;
		}
		return false;

	}

	public Case_lists GetCaseDetailsByCaseId(int id) {
		Case_lists caselist = Caselist.findByCaseId(id);
		return caselist;
	}

	public List<Case_lists> GetCaseListByUsername(String username, int min, int max) {
		List<Case_lists> caselist = Caselist.getCaselists(username, min, max);
		return caselist;
	}

	public HashMap<String, Object> fileupload(MultipartFile uploadedFile, HttpServletRequest request) {
		HashMap<String, Object> log = new HashMap<String, Object>();
		// Input Validation

		String username = request.getParameter("username");
		String fileType = request.getParameter("uploadType");
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		int caseId = Integer.parseInt(request.getParameter("caseId"));
		Case_lists caselist = Caselist.findByCaseId(caseId);
		if (caselist == null) {
			log.put("error_code", "Failed");
			log.put("error_description", "case not found");
			return log;
		}
		if (username == null) {
			log.put("error_code", "Failed");
			log.put("error_description", "Username not entered");
			return log;
		}
		if (longitude == null || latitude == null) {
			log.put("error_code", "Failed");
			log.put("error_description", "Geo-tagging missing");
			return log;
		}
		if (fileType == null) {
			log.put("error_code", "Failed");
			log.put("error_description", "File Type not entered");
			return log;
		}
		List<String> uploadType = CustomMethods.getUploadType();
		if (!uploadType.contains(fileType.toLowerCase())) {
			log.put("error_code", "Failed");
			log.put("error_description", "Invalid File Type");
			return log;
		}

		try {
			if (!uploadedFile.isEmpty()) {
				byte[] bytes = uploadedFile.getBytes();
				String originalFilename = uploadedFile.getOriginalFilename();
				String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
				String filename = caseId + "_" + fileType + "_" + currentDate + "."
						+ FileUtils.getFileExtension(new File(originalFilename));
				File serverFile = new File(Config.uploadDirectory + filename);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				// Database Logic
				String fileURL = Config.uploadURL + filename;
				caselist.setUpdatedBy(username);
				switch (fileType) {
				case "pdf1": {
					caselist.setPdf1FilePath(fileURL);
					break;
				}
				case "pdf2": {
					caselist.setPdf2FilePath(fileURL);
					break;
				}
				case "pdf3": {
					caselist.setPdf3FilePath(fileURL);
					break;
				}
				case "audio": {
					caselist.setAudioFilePath(fileURL);
					break;
				}
				case "video": {
					caselist.setVideoFilePath(fileURL);
					break;
				}
				case "signature": {
					caselist.setSignatureFilePath(fileURL);
					break;
				}
				case "image": {
					caselist.setImage(fileURL);
					break;
				}
				}
				Caselist.save(caselist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.put("error_code", "Failed");
			log.put("error_description", e.getMessage());
			CustomMethods.logError(e);
			return log;
		}

		return log;

	}

	public HashMap<String, Object> updateCaseDetails(Request username) {
		Case_lists caselist = Caselist.findByCaseId(username.getCaseid());
		HashMap<String, Object> log = new HashMap<String, Object>();
		Case_movement cas = caserepo.findByCaseId(caselist.getCaseId());
		CaseSubStatus caseSubstatus = casesubStatus.findById(4);
		try {

			String to = cas.getToId();
			String from = cas.getFromId();
			cas.setFromId(to);
			cas.setToId(from);
			cas.setUpdatedDate(new Date());
			caserepo.save(cas);
			audit_repo.insertlog(caselist.getCaseId());

			caselist.setUpdatedBy(username.getUsername());
			caselist.setCase_description(username.getCase_description());
			caselist.setCreatedDate(new Date());
			caselist.setLatitude(username.getLatitude());
			caselist.setLongitude(username.getLongitude());
			caselist.setCapturedDate(username.getCapturedDate());
			caselist.setCaseStatus(caseSubstatus.getCase_status());
			caselist.setCaseSubStatus(caseSubstatus.getCaseSubStatus());
			Caselist.save(caselist);

			if (caselist != null) 
			{
				log.put("error_code", "****");
				log.put("error_description", "Cases Details submitted successfully");
				Caselist.save(caselist);
			}
		} 
		catch (Exception e) 
		{
			log.put("error_code", "Failed");
			log.put("error_description", e.getMessage());
			CustomMethods.logError(e);
		}
		return log;
	}

	public HashMap<String, Object> dashboard(Request username) {
		HashMap<String, Object> log = new HashMap<String, Object>();
		try {

			log.put("New", caserepo.getNewCaseCount(username.getUsername()));
			log.put("Actioned by Investigator", caserepo.getCaseSubmittedCount(username.getUsername()));
			log.put("PIV/PRV/LIVE count", Caselist.getCaseIntimationCount(username.getUsername()));
			log.put("Claim Document Pickup", Caselist.getCDPCaseCount(username.getUsername()));
			log.put("Closed", caserepo.getCaseClosedCount(username.getUsername()));

		} 
		catch (Exception e) 
		{
			log.put("error_code", "Failed");
			log.put("error_description", e.getMessage());
			CustomMethods.logError(e);
		}

		return log;

	}

}
