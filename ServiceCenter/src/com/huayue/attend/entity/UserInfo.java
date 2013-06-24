package com.huayue.attend.entity;

public class UserInfo {
	
	private int userId;					//--员工ID号
	
	private String badgeNumber;			//--考勤号码
	
	private String ssn;					//--身份证/证件号
	
	private String name;				//--姓名
	
	private String gender;				//--性别
	
	private String title;				//--职务
	
	private String pager;				//--移动电话
	
	private String birthday;			//--生日
	
	private String hiredDay;			//--参加工作日期
	
	private String street;				//--家庭地址
	
	private String city;				//--市代码
	
	private String state;				//--省代码
	
	private String zip;					//--邮编
	
	private String ophone;				//--办公电话
	
	private String fphone;				//--家庭电话
	
	private int verificationMethod;		//--验证方式
	
	private int defaultDeptid;			//--所属部门ID号
	
	private String deptName;
	
	private int securityFlags;			//--管理员标志
	
	private int att;					//--考勤有效
	
	private int inLate;					//--计迟到
	
	private int outEarly;				//--计早退
	
	private int overTime;				//--计加班
	
	private int holiday;				//--假日休息
	
	private String minzu;				//--民族
	
	private String password;			//--口令
	
	private int lunchDuration;			//--有午休
	
	private String mVerifyPass;			//--考勤验证密码

	public UserInfo(){}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBadgeNumber() {
		return badgeNumber;
	}

	public void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHiredDay() {
		return hiredDay;
	}

	public void setHiredDay(String hiredDay) {
		this.hiredDay = hiredDay;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getOphone() {
		return ophone;
	}

	public void setOphone(String ophone) {
		this.ophone = ophone;
	}

	public String getFphone() {
		return fphone;
	}

	public void setFphone(String fphone) {
		this.fphone = fphone;
	}

	public int getVerificationMethod() {
		return verificationMethod;
	}

	public void setVerificationMethod(int verificationMethod) {
		this.verificationMethod = verificationMethod;
	}

	public int getDefaultDeptid() {
		return defaultDeptid;
	}

	public void setDefaultDeptid(int defaultDeptid) {
		this.defaultDeptid = defaultDeptid;
	}

	public int getSecurityFlags() {
		return securityFlags;
	}

	public void setSecurityFlags(int securityFlags) {
		this.securityFlags = securityFlags;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getInLate() {
		return inLate;
	}

	public void setInLate(int inLate) {
		this.inLate = inLate;
	}

	public int getOutEarly() {
		return outEarly;
	}

	public void setOutEarly(int outEarly) {
		this.outEarly = outEarly;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public int getHoliday() {
		return holiday;
	}

	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}

	public String getMinzu() {
		return minzu;
	}

	public void setMinzu(String minzu) {
		this.minzu = minzu;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLunchDuration() {
		return lunchDuration;
	}

	public void setLunchDuration(int lunchDuration) {
		this.lunchDuration = lunchDuration;
	}

	public String getmVerifyPass() {
		return mVerifyPass;
	}

	public void setmVerifyPass(String mVerifyPass) {
		this.mVerifyPass = mVerifyPass;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
