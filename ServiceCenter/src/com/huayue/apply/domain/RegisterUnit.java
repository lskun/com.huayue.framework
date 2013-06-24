package com.huayue.apply.domain;

import java.util.List;

public class RegisterUnit 
{
	private int id;                   //单位id
	
	private String name;              //单位名称
	
	private String post_code;           //邮编
	
	private String invoiceAddr;       //发票地址
	
	private String unitAddr;          //单位地址
	
	private int isStay;               //是否要提供住宿 
	
	private int isTogether;           //是否服从调剂
	
	private int ronnNum;              //标间个数
	
	private long applyTime;           //注册时间
	
	private String invoiceRemark;     //发票备注
	
	private String invoiceContent;    //发票内容
	
	private String unitSurvey;        //调查
	
	private List<UnitMember> members; //单位报名人员信息
	
	private double charge_standard;   //收费标准
	
	private int charge_status;			//	收费标准修改状态
	
	private String contactWay;			//报名联系人
	
	public int getCharge_status() {
		return charge_status;
	}

	public void setCharge_status(int chargeStatus) {
		charge_status = chargeStatus;
	}

	public RegisterUnit(){}
	
	public double getCharge_standard() {
		return charge_standard;
	}

	public void setCharge_standard(double chargeStandard) {
		charge_standard = chargeStandard;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String postCode) {
		post_code = postCode;
	}
	public String getInvoiceAddr() {
		return invoiceAddr;
	}
	public void setInvoiceAddr(String invoiceAddr) {
		this.invoiceAddr = invoiceAddr;
	}
	public String getUnitAddr() {
		return unitAddr;
	}
	public void setUnitAddr(String unitAddr) {
		this.unitAddr = unitAddr;
	}
	public int getIsStay() {
		return isStay;
	}
	public void setIsStay(int isStay) {
		this.isStay = isStay;
	}
	public int getIsTogether() {
		return isTogether;
	}
	public void setIsTogether(int isTogether) {
		this.isTogether = isTogether;
	}
	public int getRonnNum() {
		return ronnNum;
	}
	public void setRonnNum(int ronnNum) {
		this.ronnNum = ronnNum;
	}
	public long getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(long applyTime) {
		this.applyTime = applyTime;
	}
	public String getInvoiceRemark() {
		return invoiceRemark;
	}
	public void setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
	}
	public String getInvoiceContent() {
		return invoiceContent;
	}
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	public String getUnitSurvey() {
		return unitSurvey;
	}
	public void setUnitSurvey(String unitSurvey) {
		this.unitSurvey = unitSurvey;
	}
	
	
	public List<UnitMember> getMembers() {
		return members;
	}

	public void setMembers(List<UnitMember> members) {
		this.members = members;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
}
