package kr.or.seongjin.reservation.dao;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.seongjin.reservation.dao.sql.UserSqls;
import kr.or.seongjin.reservation.dto.User;

@Repository
public class UserDao {
	
	 	private NamedParameterJdbcTemplateHandlingNull jdbc; // sql 을 실행하기 위해 사용되는 객체
	    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
	    private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.

	    public UserDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplateHandlingNull(dataSource);
	        this.insertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("users")
	                .usingGeneratedKeyColumns("id");
	    }

	    //Create
	    public Integer insert(User user){
	        Map<String, Object> params = new HashMap<>();
	        params.put("nickname", user.getNickname());
	        params.put("sns_id", user.getId());
	        params.put("sns_profile", user.getProfile_image());
	        params.put("username", user.getName());
	        params.put("email", user.getEmail());
	        params.put("admin_flag", "0");
	        return insertAction.execute(params);
	    }

	    //Read
		public User selectUser(Integer id) {
			Map<String, ?> params = Collections.singletonMap("sns_id", id);
			return jdbc.queryForObject(UserSqls.SELECT_USER_BY_SNS_ID, params, rowMapper);
		}

	   

	    

}
