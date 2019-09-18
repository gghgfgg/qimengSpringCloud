package com.qimeng.main.vo;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2019年9月10日 下午4:08:59
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class IndexCountVo {

	private String schoolName;

	private String schoolId;

	private String schoolCode;

	private String postalCode;
	
	private Integer totalWeight;
	
	private Integer totalPoints;
	
	private Integer totalNumber;
	
	private Integer headCount;
	
	private Integer headActCount;
	
	private Integer DevCount;
	
	private Integer DevActCount;
	
	private Integer runTime;

	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Integer totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Integer getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Integer totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getHeadCount() {
		return headCount;
	}

	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}

	public Integer getHeadActCount() {
		return headActCount;
	}

	public void setHeadActCount(Integer headActCount) {
		this.headActCount = headActCount;
	}

	public Integer getDevCount() {
		return DevCount;
	}

	public void setDevCount(Integer devCount) {
		DevCount = devCount;
	}

	public Integer getDevActCount() {
		return DevActCount;
	}

	public void setDevActCount(Integer devActCount) {
		DevActCount = devActCount;
	}

	public Integer getRunTime() {
		return runTime;
	}

	public void setRunTime(Integer runTime) {
		this.runTime = runTime;
	}

	@Override
	public String toString() {
		return "IndexCountVo [schoolName=" + schoolName + ", schoolId=" + schoolId + ", schoolCode=" + schoolCode
				+ ", postalCode=" + postalCode + ", totalWeight=" + totalWeight + ", totalPoints=" + totalPoints
				+ ", totalNumber=" + totalNumber + ", headCount=" + headCount + ", headActCount=" + headActCount
				+ ", DevCount=" + DevCount + ", DevActCount=" + DevActCount + ", runTime=" + runTime + "]";
	}

}
