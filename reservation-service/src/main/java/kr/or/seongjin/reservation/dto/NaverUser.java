package kr.or.seongjin.reservation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaverUser implements User{

	private String email;
	private String nickname;
	@JsonProperty("profile_image")
	private String snsProfile;
	private String age;
	private String gender;
	@JsonProperty("name")
	private String userName;
	private String birthday;
	private String encId;
	@JsonProperty("id")
	private Integer snsId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSnsProfile() {
		return snsProfile;
	}
	public void setSnsProfile(String snsProfile) {
		this.snsProfile = snsProfile;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEncId() {
		return encId;
	}
	public void setEncId(String encId) {
		this.encId = encId;
	}
	public Integer getSnsId() {
		return snsId;
	}
	public void setSnsId(Integer snsId) {
		this.snsId = snsId;
	}
	
	
}
