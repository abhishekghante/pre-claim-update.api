package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "case_lists")
public class Case_lists {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "caseId")
	private int caseId;
	
	@Column(name = "policyNumber")
	private String policyNumber;
	
	@JsonIgnore
	@Column(name = "investigationId")
	private int investigationId;
	
	@Transient
	private String investigationType;
	
	@Column(name = "insuredName")
	private String insuredName;
	
	@Column(name = "insuredDOD")
	private Date insuredDOD;
	
	@Column(name = "insuredDOB")
	private Date insuredDOB;
	
	@Column(name = "sumAssured")
	private double sumAssured;
	
	@Column(name = "intimationType")
	private String intimationType;
	
	@Column(name = "locationId")
	private int locationId;
	
	@Column(name = "caseStatus")
	private String caseStatus;
	
	@Column(name = "nominee_Name")
	private String nominee_Name;
	
	@Column(name = "nominee_ContactNumber")
	private String nominee_ContactNumber;
	
	@Column(name = "nominee_address")
	private String nominee_address;
	
	@Column(name = "insured_address")
	private String insured_address;
	
	@Column(name = "case_description")
	private String case_description;
	
	@Column(name = "longitude")
	private String longitude;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "pdf1FilePath")
	private String pdf1FilePath;
	
	@Column(name = "pdf2FilePath")
	private String pdf2FilePath;
	
	@Column(name = "pdf3FilePath")
	private String pdf3FilePath;
	
	@Column(name = "audioFilePath")
	private String audioFilePath;
	
	@Column(name = "videoFilePath")
	private String videoFilePath;
	
	@Column(name = "signatureFilePath")
	private String signatureFilePath;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updatedDate")
	private Date updatedDate;
	
	@Column(name = "updatedBy")
	private String updatedBy;

	public Case_lists() {
		this.caseId = 0;
		this.policyNumber = "";
		this.investigationId = 0;
		this.insuredName = "";
		this.insuredDOD = new Date();
		this.insuredDOB = new Date();
		this.sumAssured = 0;
		this.intimationType = "";
		this.locationId = 0;
		this.caseStatus = "";
		this.nominee_Name = "";
		this.nominee_ContactNumber = "";
		this.nominee_address = "";
		this.insured_address = "";
		this.case_description = "";
		this.longitude = "";
		this.latitude = "";
		this.pdf1FilePath = "";
		this.pdf2FilePath = "";
		this.pdf3FilePath = "";
		this.audioFilePath = "";
		this.videoFilePath = "";
		this.signatureFilePath = "";
		this.createdBy = "";
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.updatedBy = "";
		
	}

	public Case_lists(int caseId, String policyNumber, int investigationId, String insuredName, Date insuredDOD,
			Date insuredDOB, Long sumAssured, String intimationType, int locationId, String caseStatus,
			String nominee_Name, String nominee_ContactNumber, String nominee_address, String insured_address,
			String case_description, String longitude, String latitude, String pdf1FilePath, String pdf2FilePath,
			String pdf3FilePath, String audioFilePath, String videoFilePath, String signatureFilePath, String createdBy,
			Date createdDate, Date updatedDate, String updatedBy) {
		super();
		this.caseId = caseId;
		this.policyNumber = policyNumber;
		this.investigationId = investigationId;
		this.insuredName = insuredName;
		this.insuredDOD = insuredDOD;
		this.insuredDOB = insuredDOB;
		this.sumAssured = sumAssured;
		this.intimationType = intimationType;
		this.locationId = locationId;
		this.caseStatus = caseStatus;
		this.nominee_Name = nominee_Name;
		this.nominee_ContactNumber = nominee_ContactNumber;
		this.nominee_address = nominee_address;
		this.insured_address = insured_address;
		this.case_description = case_description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.pdf1FilePath = pdf1FilePath;
		this.pdf2FilePath = pdf2FilePath;
		this.pdf3FilePath = pdf3FilePath;
		this.audioFilePath = audioFilePath;
		this.videoFilePath = videoFilePath;
		this.signatureFilePath = signatureFilePath;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
	}

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public int getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(int investigationId) {
		this.investigationId = investigationId;
	}

	public String getInvestigationType() {
		return investigationType;
	}

	public void setInvestigationType(String investigationType) {
		this.investigationType = investigationType;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public Date getInsuredDOD() {
		return insuredDOD;
	}

	public void setInsuredDOD(Date insuredDOD) {
		this.insuredDOD = insuredDOD;
	}

	public Date getInsuredDOB() {
		return insuredDOB;
	}

	public void setInsuredDOB(Date insuredDOB) {
		this.insuredDOB = insuredDOB;
	}

	public double getSumAssured() {
		return sumAssured;
	}

	public void setSumAssured(double sumAssured) {
		this.sumAssured = sumAssured;
	}

	public String getIntimationType() {
		return intimationType;
	}

	public void setIntimationType(String intimationType) {
		this.intimationType = intimationType;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getNominee_Name() {
		return nominee_Name;
	}

	public void setNominee_Name(String nominee_Name) {
		this.nominee_Name = nominee_Name;
	}

	public String getNominee_ContactNumber() {
		return nominee_ContactNumber;
	}

	public void setNominee_ContactNumber(String nominee_ContactNumber) {
		this.nominee_ContactNumber = nominee_ContactNumber;
	}

	public String getNominee_address() {
		return nominee_address;
	}

	public void setNominee_address(String nominee_address) {
		this.nominee_address = nominee_address;
	}

	public String getInsured_address() {
		return insured_address;
	}

	public void setInsured_address(String insured_address) {
		this.insured_address = insured_address;
	}

	public String getCase_description() {
		return case_description;
	}

	public void setCase_description(String case_description) {
		this.case_description = case_description;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPdf1FilePath() {
		return pdf1FilePath;
	}

	public void setPdf1FilePath(String pdf1FilePath) {
		this.pdf1FilePath = pdf1FilePath;
	}

	public String getPdf2FilePath() {
		return pdf2FilePath;
	}

	public void setPdf2FilePath(String pdf2FilePath) {
		this.pdf2FilePath = pdf2FilePath;
	}

	public String getPdf3FilePath() {
		return pdf3FilePath;
	}

	public void setPdf3FilePath(String pdf3FilePath) {
		this.pdf3FilePath = pdf3FilePath;
	}

	public String getAudioFilePath() {
		return audioFilePath;
	}

	public void setAudioFilePath(String audioFilePath) {
		this.audioFilePath = audioFilePath;
	}

	public String getVideoFilePath() {
		return videoFilePath;
	}

	public void setVideoFilePath(String videoFilePath) {
		this.videoFilePath = videoFilePath;
	}

	public String getSignatureFilePath() {
		return signatureFilePath;
	}

	public void setSignatureFilePath(String signatureFilePath) {
		this.signatureFilePath = signatureFilePath;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "Case_lists [caseId=" + caseId + ", policyNumber=" + policyNumber + ", investigationId="
				+ investigationId + ", insuredName=" + insuredName + ", insuredDOD=" + insuredDOD + ", insuredDOB="
				+ insuredDOB + ", sumAssured=" + sumAssured + ", intimationType=" + intimationType + ", locationId="
				+ locationId + ", caseStatus=" + caseStatus + ", nominee_Name=" + nominee_Name
				+ ", nominee_ContactNumber=" + nominee_ContactNumber + ", nominee_address=" + nominee_address
				+ ", insured_address=" + insured_address + ", case_description=" + case_description + ", longitude="
				+ longitude + ", latitude=" + latitude + ", pdf1FilePath=" + pdf1FilePath + ", pdf2FilePath="
				+ pdf2FilePath + ", pdf3FilePath=" + pdf3FilePath + ", audioFilePath=" + audioFilePath
				+ ", videoFilePath=" + videoFilePath + ", signatureFilePath=" + signatureFilePath + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", updatedBy="
				+ updatedBy + "]";
	}

}
