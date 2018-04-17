package com.winnerlook.huzou.pojo;


public class StatusObj {
	
	private String ID;
	
	private String mobile;
	
	private String SrcNumber;
	
	private String MessageID;
	
	private String statustext;
	
	private String org_messageid;
	
	private String inserttime;
	
	private String token;
	
	private String IP;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSrcNumber() {
		return SrcNumber;
	}

	public void setSrcNumber(String srcNumber) {
		SrcNumber = srcNumber;
	}

	public String getMessageID() {
		return MessageID;
	}

	public void setMessageID(String messageID) {
		MessageID = messageID;
	}

	public String getStatustext() {
		return statustext;
	}

	public void setStatustext(String statustext) {
		this.statustext = statustext;
	}

	public String getOrg_messageid() {
		return org_messageid;
	}

	public void setOrg_messageid(String org_messageid) {
		this.org_messageid = org_messageid;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}
	
	public static void main(String[] args) {
		String hehe = "OK:[201706148716760670485101]";
		String[] str = hehe.split(":");
		String pipestatusString = str[1].replaceAll("\\[", "").replaceAll("\\]", "");
		System.out.println(pipestatusString);
		
	}

}
