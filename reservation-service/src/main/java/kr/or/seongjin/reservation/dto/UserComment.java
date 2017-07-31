package kr.or.seongjin.reservation.dto;

import java.sql.Date;
import java.util.List;

public class UserComment {

	//In reservation_user_comment Table
	private Integer id;
	private Integer productId;
	private Integer userId;
	private Double score;
	private String comment;
	private Date createDate;
	private Date modifyDate;
	private List<String> imgList;
	
	//In other Table
	private String username;
	private String name;
	
	
	
	
	
	@Override
	public String toString() {
		return "UserComment [id=" + id + ", productId=" + productId + ", userId=" + userId + ", score=" + score
				+ ", comment=" + comment + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", imgList="
				+ imgList + ", username=" + username + ", name=" + name + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
}
