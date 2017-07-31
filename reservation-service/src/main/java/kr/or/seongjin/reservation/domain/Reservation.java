package kr.or.seongjin.reservation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Reservation {
	private int id;
	private int productId;
	private int userId;
	private int generalTicketCount;
	private int youthTicketCount;
	private int childTicketCount;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private int reservationType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGeneralTicketCount() {
		return generalTicketCount;
	}
	public void setGeneralTicketCount(int generalTicketCount) {
		this.generalTicketCount = generalTicketCount;
	}
	public int getYouthTicketCount() {
		return youthTicketCount;
	}
	public void setYouthTicketCount(int youthTicketCount) {
		this.youthTicketCount = youthTicketCount;
	}
	public int getChildTicketCount() {
		return childTicketCount;
	}
	public void setChildTicketCount(int childTicketCount) {
		this.childTicketCount = childTicketCount;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTel() {
		return reservationTel;
	}
	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public int getReservationType() {
		return reservationType;
	}
	public void setReservationType(int reservationType) {
		this.reservationType = reservationType;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}