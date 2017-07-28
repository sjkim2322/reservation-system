package kr.or.seongjin.reservation.dao.sql;

public class UserSqls {
	
	public final static String SELECT_USER_BY_SNS_ID = 
			"SELECT  username name,"
			+ "email,"
			+ "nickname,"
			+ "sns_id id,"
			+ "sns_profile profile_image "
			+ "FROM users "
			+ "WHERE sns_id=:sns_id";

	public final static String SELECT_USER_ID_BY_SNS_ID =
			"select id from users where sns_id= :sns_id";
}
