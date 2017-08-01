package kr.or.seongjin.reservation.dao.sql;

public class UserSqls {
	
	public final static String SELECT_USER_BY_SNS_ID = 
			"SELECT  "
			+ "id, "
			+ "username username,"
			+ "email,"
			+ "nickname,"
			+ "sns_id,"
			+ "sns_profile profileImage "
			+ "FROM users "
			+ "WHERE sns_id=:sns_id";

	public final static String SELECT_USER_ID_BY_SNS_ID =
			"SELECT id "
			+ "FROM users "
			+ "WHERE sns_id= :sns_id ";
}
