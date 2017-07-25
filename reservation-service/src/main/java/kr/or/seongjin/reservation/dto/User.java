package kr.or.seongjin.reservation.dto;

public class User {

	private String nickname;
	private String enc_id;
	private String profile_image;
	private String age;
	private String gender;
	private Integer id;
	private String name;
	private String email;
	private String birthday;
	
	
	
	
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
	public String getEnc_id() {
		return enc_id;
	}
	public void setEnc_id(String enc_id) {
		this.enc_id = enc_id;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [nickname=" + nickname + ", enc_id=" + enc_id + ", profile_image=" + profile_image + ", age=" + age
				+ ", gender=" + gender + ", id=" + id + ", name=" + name + ", email=" + email + ", birthday=" + birthday
				+ "]";
	}
	
	
	
	
}
