package kr.or.seongjin.reservation.dao.sql;

public class UserCommentSqls {

	public final static String SELECT_BY_ID = 
			"SELECT * "
	     +  "FROM reservation_user_comment "
	     +  "WHERE id = :id";
	
	public final static String LIST3_BY_PRODUCT_ID = 
			"SELECT * "
			+ "FROM users C join (product A right outer join reservation_user_comment B on A.id = B.product_id ) on B.user_id = C.id "
			+ "WHERE A.id = :productId "
			+ "Order by B.create_date desc "
			+ "LIMIT 3 ";
	
	public final static String LIST_BY_PRODUCT_ID = 
			"SELECT * "
			+ "FROM users C join (product A right outer join reservation_user_comment B on A.id = B.product_id ) on B.user_id = C.id "
			+ "WHERE A.id = :productId "
			+ "Order by C.id "
			+ "LIMIT :offset,:limit ";
	
	
	public final static String LIST_IMAGE_BY_COMMENT_ID = 
			"SELECT file_id "
			+ "FROM reservation_user_comment_image "
			+ "WHERE reservation_user_comment_id = :reservation_user_comment_id ";
	
	public final static String SELECT_TOTAL_COUNT = 
			"SELECT COUNT(*) "
			+ "FROM reservation_user_comment "
			+ "WHERE product_id = :productId ";
	
	public final static String SELECT_AVG_SCORE = 
			"SELECT ROUND(AVG(score),1) "
			+ "FROM reservation_user_comment "
			+ "WHERE product_id = :productId ";
			
}
