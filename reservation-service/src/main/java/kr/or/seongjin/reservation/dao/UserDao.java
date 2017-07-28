package kr.or.seongjin.reservation.dao;


import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.seongjin.reservation.dao.sql.UserSqls;
import kr.or.seongjin.reservation.dto.ReservationUser;
import kr.or.seongjin.reservation.dto.User;

@Repository
public class UserDao {
	
	 	private NamedParameterJdbcTemplateHandlingNull jdbc; // sql 을 실행하기 위해 사용되는 객체
	    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
	    private RowMapper<ReservationUser> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUser.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.

	    public UserDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplateHandlingNull(dataSource);
	        this.insertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("users")
	                .usingGeneratedKeyColumns("id");
	    }

	    //Create
	    public Integer insert(User user){
	    	SqlParameterSource params = new BeanPropertySqlParameterSource(user);
	        return insertAction.executeAndReturnKey(params).intValue();
	    }

	    //Read
		public ReservationUser selectUser(Integer id) {
			Map<String, ?> params = Collections.singletonMap("sns_id", id);
			return jdbc.queryForObject(UserSqls.SELECT_USER_BY_SNS_ID, params, rowMapper);
		}
		
		public Integer selectUserIdBySnsId(int snsId) {
			Map<String, ?> params = Collections.singletonMap("sns_id", snsId);
			return jdbc.queryForObject(UserSqls.SELECT_USER_ID_BY_SNS_ID, params, Integer.class);
		}
}
