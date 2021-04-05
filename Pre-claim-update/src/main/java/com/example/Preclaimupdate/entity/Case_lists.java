package com.example.Preclaimupdate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "case_lists")
@Table(name = "case_lists")
public class Case_lists {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "caseId")
	private long caseId;
	
	@Column(name = "policyNumber")
	private String policyNumber;
	
	@OneToOne
	@JoinColumn(name = "investigationId", referencedColumnName = "investigationId")
	private Investigation_type investigation;
	
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
	
	@OneToOne
	@JoinColumn(name = "locationId", referencedColumnName = "locationId")
	Location_lists location;
	
	@Column(name = "caseStatus")
	private String caseStatus;
	
	@Column(name = "caseSubStatus")
	private String caseSubStatus;
	
	@Column(name = "nominee_Name")
	private String nominee_Name;
	
	@Column(name = "nominee_ContactNumber")
	private String nominee_ContactNumber;
	
	@Column(name = "nominee_address")
	private String nominee_address;
	
	@Column(name = "pincode")
	private String pincode;

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
	
	@Column(name = "excelFilepath")
	private String excelFilepath;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "capturedDate")
	private String capturedDate;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "createdDate")
	private Date createdDate;
	
	@Column(name = "updatedDate")
	private Date updatedDate;
	
	@Column(name = "updatedBy")
	private String updatedBy;
	
	@Transient
	private String remarks;
	@Transient
	private String fromUser;

	public Case_lists() {
		this.caseId = 0;
		this.policyNumber = "";
		this.insuredName = "";
		this.insuredDOD = new Date();
		this.insuredDOB = new Date();
		this.sumAssured = 0;
		this.intimationType = "";
		this.caseStatus = "";
		this.caseSubStatus = "";
		this.nominee_Name = "";
		this.nominee_ContactNumber = "";
		this.nominee_address = "";
		this.pincode = "";
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
		this.excelFilepath = "";
		this.image = "";
		this.createdBy = "";
		this.createdDate = new Date();
		this.updatedDate = new Date();
		this.updatedBy = "";
		this.capturedDate = "";
		this.remarks = "";
		this.fromUser = "";
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Investigation_type getInvestigation() {
		return investigation;
	}

	public void setInvestigation(Investigation_type investigation) {
		this.investigation = investigation;
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

	public Location_lists getLocation() {
		return location;
	}

	public void setLocation(Location_lists location) {
		this.location = location;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getCaseSubStatus() {
		return caseSubStatus;
	}

	public void setCaseSubStatus(String caseSubStatus) {
		this.caseSubStatus = caseSubStatus;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public String getExcelFilepath() {
		return excelFilepath;
	}

	public void setExcelFilepath(String excelFilepath) {
		this.excelFilepath = excelFilepath;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCapturedDate() {
		return capturedDate;
	}

	public void setCapturedDate(String capturedDate) {
		this.capturedDate = capturedDate;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	@Override
	public String toString() {
		return "Case_lists [caseId=" + caseId + ", policyNumber=" + policyNumber + ", investigation=" + investigation
				+ ", insuredName=" + insuredName + ", insuredDOD=" + insuredDOD + ", insuredDOB=" + insuredDOB
				+ ", sumAssured=" + sumAssured + ", intimationType=" + intimationType + ", location=" + location
				+ ", caseStatus=" + caseStatus + ", caseSubStatus=" + caseSubStatus + ", nominee_Name=" + nominee_Name
				+ ", nominee_ContactNumber=" + nominee_ContactNumber + ", nominee_address=" + nominee_address
				+ ", pincode=" + pincode + ", insured_address=" + insured_address + ", case_description="
				+ case_description + ", longitude=" + longitude + ", latitude=" + latitude + ", pdf1FilePath="
				+ pdf1FilePath + ", pdf2FilePath=" + pdf2FilePath + ", pdf3FilePath=" + pdf3FilePath
				+ ", audioFilePath=" + audioFilePath + ", videoFilePath=" + videoFilePath + ", signatureFilePath="
				+ signatureFilePath + ", excelFilepath=" + excelFilepath + ", image=" + image + ", capturedDate="
				+ capturedDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", updatedBy=" + updatedBy + ", remarks=" + remarks + ", fromUser=" + fromUser + "]";
	}
	
	
	
}
