package kr.or.seongjin.reservation.domain;

import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProductPrice {
	
	private Integer id;
	private Integer product_id;
	private Integer price_type;
	private Integer price;
	private Double discount_rate;
	private Date create_date;
	private Date modify_date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getPrice_type() {
		return price_type;
	}
	public void setPrice_type(Integer price_type) {
		this.price_type = price_type;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Double getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(Double discount_rate) {
		this.discount_rate = discount_rate;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
}