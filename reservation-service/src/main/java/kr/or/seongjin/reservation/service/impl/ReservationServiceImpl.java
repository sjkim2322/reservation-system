package kr.or.seongjin.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.seongjin.reservation.dao.ReservationDao;
import kr.or.seongjin.reservation.domain.Reservation;
import kr.or.seongjin.reservation.domain.ReservationCount;
import kr.or.seongjin.reservation.domain.ReservationDTO;
import kr.or.seongjin.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{

	private ReservationDao reservationDao;
	
	@Autowired
	public ReservationServiceImpl(ReservationDao reservationDao) {
		this.reservationDao = reservationDao; 
	}
	
	@Override
	public int setReservation(Reservation reservation) {
		return reservationDao.insertReservation(reservation);
	}

	@Override
	public List<ReservationCount> getReservationCountByUser(int userId) throws Exception {
		List<ReservationCount> list = reservationDao.selectReservationCountByUser(userId);
		if(list == null){
			//다른 exception
			throw new Exception();
		}
		return list;
	}

	@Override
	public List<ReservationDTO> getReservationByUser(int userId) throws Exception {
		List<ReservationDTO> list =reservationDao.selectReservationByUser(userId);
		if(list == null){
			//exception
			throw new Exception();
		}
		return list;
	}

	@Override
	public void modifyReservationTypeById(int id, int type) {
		if(0 <= type && type <= 3) {
			reservationDao.updateReservationTypeById(id, type);
		}else{
			//exception
			return;
		}
	}
	

	
}
