package kr.or.seongjin.reservation.dao.sql;

public class ReservationSqls {

	public final static String SELECT_COUNT_BY_TYPE = 
			"select reservation_type, count(*) from reservation_info group by reservation_type";
	//예약번호, 전시 이름, 일정, 내역(가격) -> reservation_info, product, diplay_info
	public final static String SELECT_MY_RESERVATION =
			"select r.reservation_type, r.id, r.general_ticket_count, r.youth_ticket_count, r.child_ticket_count, " //reservation_info
			+ "p.name, " //product
			+ "d.display_start, d.display_end "	//display_info
			+ "from reservation_info as r, product as p, display_info as d "
			+ "where d.product_id = r.product_id and p.id = r.product_id and r.user_id= :id "
			+ "order by reservation_type";
}

