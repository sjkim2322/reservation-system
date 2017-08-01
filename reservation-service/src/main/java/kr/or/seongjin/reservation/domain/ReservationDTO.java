package kr.or.seongjin.reservation.domain;

import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ReservationDTO {

	private String reservationType;
	private int id;
	private int generalTicketCount;
	private int youthTicketCount;
	private int childTicketCount;
	private int generalTicketCost;
	private int youthTicketCost;
	private int childTicketCost;
	private int productId;
	private String name;
	private Date displayStart;
	private Date displayEnd;
	
	public String getReservationType() {
		return reservationType;
	}
	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getGeneralTicketCost() {
		return generalTicketCost;
	}
	public void setGeneralTicketCost(int generalTicketCost) {
		this.generalTicketCost = generalTicketCost;
	}
	public int getYouthTicketCost() {
		return youthTicketCost;
	}
	public void setYouthTicketCost(int youthTicketCost) {
		this.youthTicketCost = youthTicketCost;
	}
	public int getChildTicketCost() {
		return childTicketCost;
	}
	public void setChildTicketCost(int childTicketCost) {
		this.childTicketCost = childTicketCost;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
