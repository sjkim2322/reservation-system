package kr.or.seongjin.reservation.dao.sql;

public class ProductSqls {

	public final static String SELECT_ALL = "SELECT *  FROM product LIMIT 10 OFFSET :offset;";
	public final static String SELECT_BY_CATEGORY = "SELECT *  FROM product WHERE category_id=:id LIMIT 10 OFFSET :offset;";
	public final static String COUNT_ALL = "SELECT count(id) count  FROM product";
	public final static String COUNT_BY_CATEGORY = "SELECT count(id) count  FROM product WHERE category_id=:id";
}
