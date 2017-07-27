package kr.or.seongjin.reservation.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.seongjin.reservation.dao.sql.ReservationSqls;
import kr.or.seongjin.reservation.domain.Reservation;
import kr.or.seongjin.reservation.domain.ReservationCount;
import kr.or.seongjin.reservation.domain.ReservationDTO;

@Repository
public class ReservationDAOImpl implements ReservationDAO{

	private NamedParameterJdbcTemplate jdbc; 
    private SimpleJdbcInsert insertAction; 
    private RowMapper<ReservationCount> reservationCountRowMapper = BeanPropertyRowMapper.newInstance(ReservationCount.class);
    private RowMapper<ReservationDTO> reservationDtoRowMapper = BeanPropertyRowMapper.newInstance(ReservationDTO.class); 
    
    public ReservationDAOImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource); 
        this.insertAction = new SimpleJdbcInsert(dataSource) 
                .withTableName("reservation_info")   
                .usingGeneratedKeyColumns("id"); 
    }
	
	@Override
	public int insertReservation(Reservation reservation) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);
        return insertAction.executeAndReturnKey(params).intValue();
	}

	@Override
	public List<ReservationCount> selectReservationCountByUser(int userId) {
		Map<String, ?> params = Collections.singletonMap("id", userId);
		return jdbc.query(ReservationSqls.SELECT_COUNT_BY_TYPE, params, reservationCountRowMapper);
	}

	@Override
	public List<ReservationDTO> selectReservationByUser(int userId) {
		Map<String, ?> params = Collections.singletonMap("id", userId);
		return jdbc.query(ReservationSqls.SELECT_MY_RESERVATION, params, reservationDtoRowMapper);
	}

	
}
