package kr.or.seongjin.reservation.dao;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.seongjin.reservation.dao.sql.UserCommentSqls;
import kr.or.seongjin.reservation.dto.UserComment;

@Repository
public class UserCommentDao {
	
	 	private NamedParameterJdbcTemplateHandlingNull jdbc; // sql 을 실행하기 위해 사용되는 객체
	    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
	    private SimpleJdbcInsert insertActionForImage;
	    private RowMapper<UserComment> rowMapper = BeanPropertyRowMapper.newInstance(UserComment.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
	    public UserCommentDao(DataSource dataSource) {
	        this.jdbc = new NamedParameterJdbcTemplateHandlingNull(dataSource);
	        this.insertAction = new SimpleJdbcInsert(dataSource)
	                .withTableName("reservation_user_comment")
	                .usingGeneratedKeyColumns("id");
	        this.insertActionForImage = new SimpleJdbcInsert(dataSource)
	                .withTableName("reservation_user_comment_image")
	                .usingGeneratedKeyColumns("id");
	        
	    }

	    //Create
	    public int insertComment(UserComment userComment){
	        SqlParameterSource params = new BeanPropertySqlParameterSource(userComment);
	        return insertAction.executeAndReturnKey(params).intValue();
	    }
	    
	    public Integer insertCommentImage(Integer fileId,Integer userCommentId) {
	    	Map<String, Object> params = new HashMap<>();
	    	params.put("file_id", fileId);
			params.put("reservation_user_comment_id",userCommentId);
			return insertActionForImage.executeAndReturnKey(params).intValue();
		}

	    
	    //Read
	    public UserComment selectById(Integer id){
	        Map<String, Object> params = Collections.singletonMap("id", id);
	        return jdbc.queryForObject(UserCommentSqls.SELECT_BY_ID,params,rowMapper);
	    }
	
	    //Read List
	    public List<UserComment> listByProductId(Integer productId,int limit,int page){
	    	Map<String, Object> params = new HashMap<>();
			params.put("productId", productId);
			params.put("limit",limit);
			params.put("offset",limit*page);
	        
	        return jdbc.query(UserCommentSqls.LIST_BY_PRODUCT_ID,params,rowMapper);
	    }
	    
	    
	    //Read ImageId List
	    public List<String> listImageByCommentId(Integer id) {
	    	Map<String, Object> params =Collections.singletonMap("reservation_user_comment_id", id);
	        return jdbc.queryForList(UserCommentSqls.LIST_IMAGE_BY_COMMENT_ID,params,String.class);
	    }

		public String getAvgScore(Integer productId) {
			Map<String, Object> params =Collections.singletonMap("productId", productId);
			return jdbc.queryForObject(UserCommentSqls.SELECT_AVG_SCORE,params,String.class);
		}

		public Integer getTotalCount(Integer productId) {
			Map<String, Object> params =Collections.singletonMap("productId", productId);
			return jdbc.queryForObject(UserCommentSqls.SELECT_TOTAL_COUNT,params,Integer.class);
		}

	

		
	    
	    //Update
	    
	    //Delete
	   
	    
	   

	    
	   
	    

}
