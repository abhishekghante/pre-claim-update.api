package com.example.Preclaimupdate.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.example.Preclaimupdate.controller.Repository.InvestigationRepository;
import com.example.Preclaimupdate.entity.Admin_user;
import com.example.Preclaimupdate.entity.Audit_case_movement;
import com.example.Preclaimupdate.entity.Case_lists;
import com.example.Preclaimupdate.entity.Case_movement;
import com.example.Preclaimupdate.entity.Investigation_type;
import com.example.Preclaimupdate.req.Request;

@Service
public class PreClaimService {

	@Autowired
	public JavaMailSender mailSender;

	@Autowired
	private AdminuserRepository Adminuser;
	
	@Autowired
	private InvestigationRepository ino;
	
	@Autowired
	private Case_movementRepository caserepo;

	@Autowired
	private CaselistsRepository Caselist;
	
	@Autowired
	private Audit_casemovementRepository audite;

	public Admin_user getbyusername(String username) {

		return Adminuser.findByUsername(username);

	}

	public void Sendmail(Admin_user user, String pass) throws MessagingException, UnsupportedEncodingException {
		user.setPassword(pass);
		Adminuser.save(user);
		String toAddress = user.getUser_email();
//			    String fromAddress = "claims@xangarsinfra.com";
		String senderName = "Your company name";
		String subject = "You temp password ";
		String content = "Your temp password is<h3> [[name]]</h3>Kindly set your password,<br>" + "Thank you,<br>"
				+ "Your company name.";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

//			    helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[name]]", RandomStringUtils.random(6, true, true));

		helper.setText(content, true);

		mailSender.send(message);
	}

	public boolean changepassword(Request username) {
		Encoder encoder = Base64.getEncoder();
		Admin_user user = Adminuser.findByUsername(username.getUsername());
		String encodedPassword = encoder.encodeToString(username.getNewpassword().getBytes());
		String oldPassword = encoder.encodeToString(username.getOldpassword().getBytes());
		if (user != null && user.getPassword().equalsIgnoreCase(oldPassword)) {
			user.setPassword(encodedPassword);
			user.setUpdatedDate(new Date());
			Adminuser.save(user);
			return true;
		}

		return false;

	}

	public HashMap<String, Object> GetCaseDetailsByCaseId(int id) {

		HashMap<String, Object> log = new HashMap<String, Object>();

		Case_lists caselist = Caselist.findByCaseId(id);
		Investigation_type invotype =ino.findByInvestigationId(caselist.getInvestigationId());
		System.out.println(invotype.getInvestigationType());
		if (caselist != null) {
			log.put("caseId", caselist.getCaseId());
			log.put("policyNumber", caselist.getPolicyNumber());
			log.put("investigationCategory", invotype.getInvestigationType());
			log.put("insuredName", caselist.getInsuredName());
			log.put("insuredDOD", caselist.getInsuredDOD());
			log.put("insuredDOB", caselist.getInsuredDOB());
			log.put("sumAssured", caselist.getSumAssured());
			log.put("intimationType", caselist.getIntimationType());
			log.put("claimantCity", caselist.getCaseStatus());
			log.put("claimantZone", caselist.getCaseStatus());
			log.put("claimantState", caselist.getCaseStatus());
			if (caselist.getAudioFilePath() != null)
				log.put("audio", caselist.getAudioFilePath());
			if (caselist.getVideoFilePath() != null)
				log.put("video ", caselist.getVideoFilePath());
			if (caselist.getPdf2FilePath() != null)
				log.put("pdf2", caselist.getPdf2FilePath());
			if (caselist.getPdf3FilePath() != null)
				log.put("pdf3", caselist.getPdf3FilePath());
			if (caselist.getPdf1FilePath() != null)
				log.put("pdf1", caselist.getPdf1FilePath());
			if (caselist.getSignatureFilePath() != null)
				log.put("sign", caselist.getSignatureFilePath());

		}

		return log;

	}

	public List<Case_lists> GetCaseListByUserId(int size, String status, String updatedby) {

		HashMap<String, Object> log = new HashMap<String, Object>();
		List<Case_lists> caselist = Caselist.getCaselists(size,status,updatedby);
		System.out.println(caselist);
		return caselist;

	}

	public List<Case_lists> FindAll() {

		List<Case_lists> caselist;

		return Caselist.findAll();

	}
	public List<Investigation_type> Find() {

		List<Case_lists> caselist;

		return ino.findAll();

	}
	
	

	public HashMap<String, Object> fileupload(MultipartFile uploadedFile, HttpServletRequest request)
			throws IOException {
		HashMap<String, Object> log = new HashMap<String, Object>();
		String username = request.getParameter("username");
		String fileType = request.getParameter("uploadType").toLowerCase();
		String longitude = request.getParameter("longitude");
		String latitude = request.getParameter("latitude");
		int caseId = Integer.parseInt(request.getParameter("caseId"));
		Case_lists caselist = Caselist.findByCaseId(caseId);
		if (caselist == null) {
			log.put("error_code", "failed");
			log.put("error_description", "case not found");
			return log;
		}
		if (username == null) {
			log.put("error_code", "failed");
			log.put("error_description", "Username not entered");
			return log;
		}
		if (longitude == null || latitude == null) {
			log.put("error_code", "failed");
			log.put("error_description", "Geo-tagging missing");
			return log;
		}
		if (fileType == null) {
			log.put("error_code", "failed");
			log.put("error_description", "File Type not entered");
			return log;
		}
		List<String> uploadType = CustomMethods.getUploadType();
		if (!uploadType.contains(fileType)) {
			log.put("error_code", "failed");
			log.put("error_description", "Invalid File Type");
			return log;
		}

		try {
			if (!uploadedFile.isEmpty()) {
				byte[] bytes = uploadedFile.getBytes();
				String originalFilename = uploadedFile.getOriginalFilename();
				String filename = username + "_" + fileType + "_"
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
				caselist.setLongitude(longitude);
				caselist.setLatitude(latitude);
				Caselist.save(caselist);
				return log;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return log;

	}

	public HashMap<String, Object> updateCaseDetails(Request username) {
		System.out.println(username.getCaseid());
		System.out.println(username.getUsername());
				System.out.println(username.getDescription());
				System.out.println(username.getLat());
				System.out.println(username.getLongi());

		Case_lists caselist = Caselist.findByCaseId(username.getCaseid());
		Case_movement cas =caserepo.findByCaseId(caselist.getCaseId());
		String to = cas.getToId();
		String from =cas.getFromId();
		cas.setFromId(to);
		cas.setToId(from);
		cas.setUpdatedDate(new Date());
		caserepo.save(cas);
		Audit_case_movement audit=new Audit_case_movement();
		audit.setCaseId(caselist.getCaseId());
		audit.setCaseStatus(caselist.getCaseStatus());
		audit.setCreatedDate(caselist.getCreatedDate());
		audit.setFromId(to);
		audit.setRemarks(caselist.getCase_description());
		audit.setToId(from);
		audit.setUpdatedDate(new Date());
		audite.save(audit);
		caselist.setUpdatedBy(username.getUsername());
		caselist.setCase_description(username.getDescription());
		caselist.setCreatedDate(new Date());
		caselist.setLatitude(""+username.getLat());
		caselist.setLongitude(""+username.getLongi());
		HashMap<String, Object> log = new HashMap<String, Object>();
		Caselist.save(caselist);
		if (caselist != null) {
			log.put("error_code", "****");
			log.put("error_description", "Cases Details submitted successfully");
			Caselist.save(caselist);
		}

		return log;

	}
	
	public HashMap<String, Object> dashboard(Request username) {
		System.out.println(username.getUsername());
		List<Case_lists> list=Caselist.findall();
	List<Case_movement> ca = caserepo.getCaselists1(username.getUsername());
	List<Case_movement> case2 =caserepo.getCaselists(username.getUsername());
		System.out.println(list);

		HashMap<String, Object> log = new HashMap<String, Object>();
		log.put("actioned by Investigator", ca.size());
		log.put("Cases Assigned to Investigator", case2.size());
		log.put("PIV/PRV/LIVE count", list.size());
		return log;
		
	}
	

		
}
