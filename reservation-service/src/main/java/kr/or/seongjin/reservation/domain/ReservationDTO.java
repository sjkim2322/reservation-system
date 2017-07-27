package kr.or.seongjin.reservation.domain;

import java.util.Date;

public class ReservationDTO {

	private String reservationType;
	private int reservationId;
	private int generalTicketCount;
	private int youthTicketCount;
	private int childTicketCount;
	private String name;
	private Date displayStart;
	private Date displayEnd;
	
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDisplayStart() {
		return displayStart;
	}
	public void setDisplayStart(Date displayStart) {
		this.displayStart = displayStart;
	}
	public Date getDisplayEnd() {
		return displayEnd;
	}
	public void setDisplayEnd(Date displayEnd) {
		this.displayEnd = displayEnd;
	}
	
	
}
