package kr.or.seongjin.reservation.dto;

public class ReservationUser implements User{

	private Integer adminFlag = 0;
	
	private Integer id;
	private String username;
	private String email;
	private String nickname;
	private Integer snsId;
	private String snsProfile;
	public Integer getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(Integer adminFlag) {
		this.adminFlag = adminFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
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
	public Integer getSnsId() {
		return snsId;
	}
	public void setSnsId(Integer snsId) {
		this.snsId = snsId;
	}
	public String getSnsProfile() {
		return snsProfile;
	}
	public void setSnsProfile(String snsProfile) {
		this.snsProfile = snsProfile;
	}
	
	
	
	
	
	
}
