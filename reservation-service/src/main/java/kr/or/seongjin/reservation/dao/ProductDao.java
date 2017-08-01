package kr.or.seongjin.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.seongjin.reservation.Exception.ReservationException;
import kr.or.seongjin.reservation.dao.sql.ProductSqls;
import kr.or.seongjin.reservation.domain.Product;
import kr.or.seongjin.reservation.domain.ProductPrice;

@Repository
public class ProductDao {

 	private NamedParameterJdbcTemplateHandlingNull jdbc; // sql 을 실행하기 위해 사용되는 객체
    private SimpleJdbcInsert insertAction; // insert 를 편리하게 하기 위한 객체
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class); // 칼럼 이름을 보통 user_name 과 같이 '_'를 활용하는데 자바는 낙타표기법을 사용한다 이것을 자동 맵핑한다.
    
    
    public ProductDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplateHandlingNull(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }
    
    public List<Product> selectAll(int offset) {
    	Map<String, Object> params = new HashMap<>();
		params.put("offset", offset);
    	return jdbc.query(ProductSqls.SELECT_ALL,params,rowMapper);
    }
    
    public List<Product> selectByCategory(int categoryId,int offset) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("id", categoryId);
    	params.put("offset", offset);
    	return jdbc.query(ProductSqls.SELECT_BY_CATEGORY, params, rowMapper);
    }
    
    public Integer countAll() {
    	Map<String, ?> params = null;
    	return jdbc.queryForObject(ProductSqls.COUNT_ALL,params,Integer.class);
    }
    
    public Integer countByCategory(int categoryId) {
    	Map<String, ?> params = Collections.singletonMap("id", categoryId);
    	return jdbc.queryForObject(ProductSqls.COUNT_BY_CATEGORY,params,Integer.class);
    }
    
    
    public String selectPlaceNameByProductId(int productId) {
    	Map<String, ?> params = Collections.singletonMap("productId", productId);
    	return jdbc.queryForObject(ProductSqls.SELECT_PLACE_NAME_BY_PRODUCT_ID,params,String.class); 
    }
    
    public Product selectByProductId(Integer productId) throws ReservationException {
    	Map<String, ?> params = Collections.singletonMap("id", productId);
    	 return jdbc.queryForObject(ProductSqls.SELECT_BY_PRODUCT_ID, params, rowMapper);
    }

	public String selectReprentImgByProductId(Integer productId) {
		Map<String, ?> params = Collections.singletonMap("id", productId);
		return jdbc.queryForObject(ProductSqls.SELECT_REPRSENT_IMG_BY_PRODUCT_ID, params, String.class);
	}

	public List<String> selectImagesByProductId(Integer productId) {
		Map<String, ?> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForList(ProductSqls.SELECT_IMAGES_BY_PRODUCT_ID, params, String.class);
	}

	public List<ProductPrice> selectPricesByProductId(int productId) {
		Map<String, ?> params = Collections.singletonMap("productId", productId);
		return jdbc.query(ProductSqls.SELECT_PRICES_BY_PRODUCT_ID, params, BeanPropertyRowMapper.newInstance(ProductPrice.class));
	}

	public String selectProductName(Integer productId) {
		Map<String, ?> params = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(ProductSqls.SELECT_PRODUCT_NAME_BY_PRODUCT_ID, params, String.class);
	}

}
