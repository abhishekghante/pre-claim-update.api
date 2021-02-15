package com.example.Preclaimupdate.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import org.apache.commons.lang.RandomStringUtils;
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
import com.example.Preclaimupdate.entity.Admin_user;
import com.example.Preclaimupdate.entity.Case_lists;
import com.example.Preclaimupdate.entity.Case_movement;
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

	public Admin_user getbyusername(String username) {
		logger.error("passed value to Service Method getbyusername");
		return Adminuser.findByUsername(username);

	}

	public void Sendmail(Admin_user user, String pass) {
		logger.error("passed value to Service Method Sendmail");
		user.setPassword(pass);
		Adminuser.save(user);
		logger.error("passed Saved Value in Adminuser DB");
		String fromAddress = "claims@xangarsinfra.com";
		String senderName = "Your company name";
		String toAddress = user.getUser_email();

		String subject = "You temp password ";
		String content = "Your temp password is<h3> [[name]]</h3>Kindly set your password,<br>" + "Thank you,<br>"
				+ "Your company name.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom(fromAddress);
			helper.setTo(toAddress);
			helper.addCc("xangars.aniketr@xangarsinfra.com");

			helper.setSubject(subject);

			content = content.replace("[[name]]", RandomStringUtils.random(6, true, true));

			helper.setText(content, true);

			mailSender.send(message);

		} catch (MessagingException e) {

			CustomMethods.logError(e);
			e.printStackTrace();
		}
	}

	public boolean changepassword(Request username) {
		logger.error("passed value to Service Method changepassword");
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
		logger.error("passed value to Service Method GetCaseDetailsByCaseId");
		Case_lists caselist = Caselist.findByCaseId(id);
		return caselist;
	}

	public List<Case_lists> GetCaseListByUsername(String username, int min, int max) {
		logger.error("passed value to Service Method GetCaseListByUsername");
		List<Case_lists> caselist = Caselist.getCaselists(username, min, max);
		
		
		return caselist;
	}

	public HashMap<String, Object> fileupload(MultipartFile uploadedFile, HttpServletRequest request) {
		logger.error("passed value to Service Method fileupload");
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
				String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
				}
				Caselist.save(caselist);
			}
		} catch (Exception e) {
			logger.error("Failed", e);
			e.printStackTrace();
			log.put("error_code", "Failed");
			log.put("error_description", e.getMessage());
			return log;
		}

		return log;

	}

	public HashMap<String, Object> updateCaseDetails(Request username) {
		logger.error("passed value to Service Method updateCaseDetails");
		Case_lists caselist = Caselist.findByCaseId(username.getCaseid());
		HashMap<String, Object> log = new HashMap<String, Object>();
		Case_movement cas = caserepo.findByCaseId(caselist.getCaseId());
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
			Caselist.save(caselist);

			if (caselist != null) {
				log.put("error_code", "****");
				log.put("error_description", "Cases Details submitted successfully");
				Caselist.save(caselist);
			}

		} catch (Exception e) {
			CustomMethods.logError(e);
		}
		return log;

	}

	public HashMap<String, Object> dashboard(Request username) {
		logger.error("passed value to Service Method dashboard");
		HashMap<String, Object> log = new HashMap<String, Object>();
		try {

			log.put("New", caserepo.getNewCaseCount(username.getUsername()));
			log.put("Actioned by Investigator", caserepo.getCaseSubmittedCount(username.getUsername()));
			log.put("PIV/PRV/LIVE count", Caselist.getCaseIntimationCount(username.getUsername()));
			log.put("Claim Document Pickup", Caselist.getCDPCaseCount(username.getUsername()));
			log.put("Closed", caserepo.getCaseClosedCount(username.getUsername()));

		} catch (Exception e) {
			CustomMethods.logError(e);
		}

		return log;

	}

}
