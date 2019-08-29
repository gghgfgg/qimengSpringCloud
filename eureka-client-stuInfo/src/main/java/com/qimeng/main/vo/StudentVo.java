package com.qimeng.main.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

public class StudentVo {

	private String uuid;

	private String identityCard;

	private String studentCode;

	private String qrcode;

	private String name;

	private Byte type;

	private String schoolCode;

	private Byte binding;

	private Byte active;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") // 页面写入数据库时格式化
	@JSONField(format = "yyyy-MM-dd HH-mm-ss") // 数据库导出页面时json格式化
	private Date firstTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss") // 页面写入数据库时格式化
	@JSONField(format = "yyyy-MM-dd HH-mm-ss") // 数据库导出页面时json格式化
	private Date lastTime;

	private Integer activityCount;

	private Integer totalPoints;

	private Integer usedPoints;

	private Integer deductPoints;
	
	private String postalCodename;
	
	private String schoolName;
	
	private String schoolId;
	
	private String postalCode;
	
	private String classS;
	
	private String grade;

    private String sex;

    private String nativePlace;

    private String nation;

    private String phone;

    private String birthday;

    private String birthplace;

    private String nationality;

    private String identityType;

    private String overseasChinese;

    private String politicsStatus;

    private String health;

    private String censusRegister;

    private String censusRegisterType;

    private String enrollmentTime;

    private String entranceWay;

    private String studyingWay;

    private String address;

    private String contactAddress;

    private String residence;

    private String workersChildren;

    private String onlyChild;

    private String preschoolEducation;

    private String leftoverChildren;

    private String orphan;

    private String martyrChildren;

    private String funding;

    private String boarderAlimony;

    private String auxiliaryNumber;

    private String studentNumber;

    private String studentSource;

    private String learningClass;

    private String disabilityTypes;

    private String familyName;

    private String familyRelationship;

    private String familyWork;

    private String familyResidence;

    private String familyCensusRegister;

    private String familyPhone;
    
    private String teacherPhone;
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public Byte getBinding() {
		return binding;
	}

	public void setBinding(Byte binding) {
		this.binding = binding;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getUsedPoints() {
		return usedPoints;
	}

	public void setUsedPoints(Integer usedPoints) {
		this.usedPoints = usedPoints;
	}

	public Integer getDeductPoints() {
		return deductPoints;
	}

	public void setDeductPoints(Integer deductPoints) {
		this.deductPoints = deductPoints;
	}

	

	public String getPostalCodename() {
		return postalCodename;
	}

	public void setPostalCodename(String postalCodename) {
		this.postalCodename = postalCodename;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getClassS() {
		return classS;
	}

	public void setClassS(String classS) {
		this.classS = classS;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getOverseasChinese() {
		return overseasChinese;
	}

	public void setOverseasChinese(String overseasChinese) {
		this.overseasChinese = overseasChinese;
	}

	public String getPoliticsStatus() {
		return politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getCensusRegister() {
		return censusRegister;
	}

	public void setCensusRegister(String censusRegister) {
		this.censusRegister = censusRegister;
	}

	public String getCensusRegisterType() {
		return censusRegisterType;
	}

	public void setCensusRegisterType(String censusRegisterType) {
		this.censusRegisterType = censusRegisterType;
	}

	public String getEnrollmentTime() {
		return enrollmentTime;
	}

	public void setEnrollmentTime(String enrollmentTime) {
		this.enrollmentTime = enrollmentTime;
	}

	public String getEntranceWay() {
		return entranceWay;
	}

	public void setEntranceWay(String entranceWay) {
		this.entranceWay = entranceWay;
	}

	public String getStudyingWay() {
		return studyingWay;
	}

	public void setStudyingWay(String studyingWay) {
		this.studyingWay = studyingWay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getWorkersChildren() {
		return workersChildren;
	}

	public void setWorkersChildren(String workersChildren) {
		this.workersChildren = workersChildren;
	}

	public String getOnlyChild() {
		return onlyChild;
	}

	public void setOnlyChild(String onlyChild) {
		this.onlyChild = onlyChild;
	}

	public String getPreschoolEducation() {
		return preschoolEducation;
	}

	public void setPreschoolEducation(String preschoolEducation) {
		this.preschoolEducation = preschoolEducation;
	}

	public String getLeftoverChildren() {
		return leftoverChildren;
	}

	public void setLeftoverChildren(String leftoverChildren) {
		this.leftoverChildren = leftoverChildren;
	}

	public String getOrphan() {
		return orphan;
	}

	public void setOrphan(String orphan) {
		this.orphan = orphan;
	}

	public String getMartyrChildren() {
		return martyrChildren;
	}

	public void setMartyrChildren(String martyrChildren) {
		this.martyrChildren = martyrChildren;
	}

	public String getFunding() {
		return funding;
	}

	public void setFunding(String funding) {
		this.funding = funding;
	}

	public String getBoarderAlimony() {
		return boarderAlimony;
	}

	public void setBoarderAlimony(String boarderAlimony) {
		this.boarderAlimony = boarderAlimony;
	}

	public String getAuxiliaryNumber() {
		return auxiliaryNumber;
	}

	public void setAuxiliaryNumber(String auxiliaryNumber) {
		this.auxiliaryNumber = auxiliaryNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getStudentSource() {
		return studentSource;
	}

	public void setStudentSource(String studentSource) {
		this.studentSource = studentSource;
	}

	public String getLearningClass() {
		return learningClass;
	}

	public void setLearningClass(String learningClass) {
		this.learningClass = learningClass;
	}

	public String getDisabilityTypes() {
		return disabilityTypes;
	}

	public void setDisabilityTypes(String disabilityTypes) {
		this.disabilityTypes = disabilityTypes;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFamilyRelationship() {
		return familyRelationship;
	}

	public void setFamilyRelationship(String familyRelationship) {
		this.familyRelationship = familyRelationship;
	}

	public String getFamilyWork() {
		return familyWork;
	}

	public void setFamilyWork(String familyWork) {
		this.familyWork = familyWork;
	}

	public String getFamilyResidence() {
		return familyResidence;
	}

	public void setFamilyResidence(String familyResidence) {
		this.familyResidence = familyResidence;
	}

	public String getFamilyCensusRegister() {
		return familyCensusRegister;
	}

	public void setFamilyCensusRegister(String familyCensusRegister) {
		this.familyCensusRegister = familyCensusRegister;
	}

	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	
	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}

	@Override
	public String toString() {
		return "StudentVo [uuid=" + uuid + ", identityCard=" + identityCard + ", studentCode=" + studentCode
				+ ", qrcode=" + qrcode + ", name=" + name + ", type=" + type + ", schoolCode=" + schoolCode
				+ ", binding=" + binding + ", active=" + active + ", firstTime=" + firstTime + ", lastTime=" + lastTime
				+ ", activityCount=" + activityCount + ", totalPoints=" + totalPoints + ", usedPoints=" + usedPoints
				+ ", deductPoints=" + deductPoints + ", postalCodename=" + postalCodename + ", schoolName=" + schoolName
				+ ", schoolId=" + schoolId + ", postalCode=" + postalCode + ", classS=" + classS + ", grade=" + grade
				+ ", sex=" + sex + ", nativePlace=" + nativePlace + ", nation=" + nation + ", phone=" + phone
				+ ", birthday=" + birthday + ", birthplace=" + birthplace + ", nationality=" + nationality
				+ ", identityType=" + identityType + ", overseasChinese=" + overseasChinese + ", politicsStatus="
				+ politicsStatus + ", health=" + health + ", censusRegister=" + censusRegister + ", censusRegisterType="
				+ censusRegisterType + ", enrollmentTime=" + enrollmentTime + ", entranceWay=" + entranceWay
				+ ", studyingWay=" + studyingWay + ", address=" + address + ", contactAddress=" + contactAddress
				+ ", residence=" + residence + ", workersChildren=" + workersChildren + ", onlyChild=" + onlyChild
				+ ", preschoolEducation=" + preschoolEducation + ", leftoverChildren=" + leftoverChildren + ", orphan="
				+ orphan + ", martyrChildren=" + martyrChildren + ", funding=" + funding + ", boarderAlimony="
				+ boarderAlimony + ", auxiliaryNumber=" + auxiliaryNumber + ", studentNumber=" + studentNumber
				+ ", studentSource=" + studentSource + ", learningClass=" + learningClass + ", disabilityTypes="
				+ disabilityTypes + ", familyName=" + familyName + ", familyRelationship=" + familyRelationship
				+ ", familyWork=" + familyWork + ", familyResidence=" + familyResidence + ", familyCensusRegister="
				+ familyCensusRegister + ", familyPhone=" + familyPhone + ", teacherPhone=" + teacherPhone + "]";
	}

}
