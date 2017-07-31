package kr.or.seongjin.reservation.domain;

import java.sql.Date;

public class Product {
	
	//In product Table
	private Integer id;
	private Integer category_id;
	private String name;
	private String description;
	private Date sales_start;
	private Date sales_end;
	private Integer sales_flag;
	private String event;
	
	//In product_detail Table
	private String content;

	//In display_info Table
	private String observation_time;
	private Date display_start;
	private Date display_end;
	private String place_name;
	private String place_lot;
	private String place_street;
	private String tel;
	private String homepage;
	private String email;
	 
	//In product_img Table for RepresentImg
	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getObservation_time() {
		return observation_time;
	}
	public void setObservation_time(String observation_time) {
		this.observation_time = observation_time;
	}
	public Date getDisplay_start() {
		return display_start;
	}
	public void setDisplay_start(Date display_start) {
		this.display_start = display_start;
	}
	public Date getDisplay_end() {
		return display_end;
	}
	public void setDisplay_end(Date display_end) {
		this.display_end = display_end;
	}
	public String getPlace_lot() {
		return place_lot;
	}
	public void setPlace_lot(String place_lot) {
		this.place_lot = place_lot;
	}
	public String getPlace_street() {
		return place_street;
	}
	public void setPlace_street(String place_street) {
		this.place_street = place_street;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getSales_start() {
		return sales_start;
	}
	public void setSales_start(Date sales_start) {
		this.sales_start = sales_start;
	}
	public Date getSales_end() {
		return sales_end;
	}
	public void setSales_end(Date sales_end) {
		this.sales_end = sales_end;
	}
	public Integer getSales_flag() {
		return sales_flag;
	}
	public void setSales_flag(Integer sales_flag) {
		this.sales_flag = sales_flag;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
}
