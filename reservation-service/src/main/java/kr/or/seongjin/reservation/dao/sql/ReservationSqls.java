package kr.or.seongjin.reservation.dao.sql;

public class ReservationSqls {

	public final static String SELECT_COUNT_BY_TYPE = 
			"SELECT reservation_type as type, "
			+ "count(*) as count "
			+ "FROM reservation_info "
			+ "GROUP BY reservation_type";
	
	//예약번호, 전시 이름, 일정, 내역(가격) -> reservation_info, product, diplay_info
	public final static String SELECT_MY_RESERVATION =
			"SELECT r.reservation_type, "
			+ "r.id, "
			+ "r.general_ticket_count, "
			+ "r.youth_ticket_count, "
			+ "r.child_ticket_count, " //reservation_info
			+ "p.id as product_id, "
			+ "p.name, " //product
			+ "d.display_start, "
			+ "d.display_end "	//display_info
			+ "FROM reservation_info as r, product as p, display_info as d "
			+ "WHERE d.product_id = r.product_id and p.id = r.product_id AND r.user_id= :id "
			+ "ORDER by reservation_type";
	
	public final static String SELECT_MY_RESERVATION_PRICE_BY_TYPE =
			"SELECT pp.product_id, "
			+ "pp.price_type, "
			+ "pp.price "
			+ "FROM product_price AS pp, reservation_info AS ri "
			+ "WHERE pp.product_id = ri.product_id AND ri.user_id = 12 "
			+ "ORDER BY pp.product_id, pp.price_type";
	
	
	//0 :이용 신청 1 :이용 확정 2 :이용완료 3 :취소
	public final static String UPDATE_RESERVATION_TYPE_BY_ID =
			"UPDATE reservation_info "
			+ "SET reservation_type = :type "
			+ "WHERE id= :id";
}
