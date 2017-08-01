package kr.or.seongjin.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.seongjin.reservation.Exception.NotExistProductException;
import kr.or.seongjin.reservation.Exception.ReservationException;
import kr.or.seongjin.reservation.dao.ReservationDao;
import kr.or.seongjin.reservation.domain.ProductPrice;
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
	public List<ReservationCount> getReservationCountByUser(int userId) throws NotExistProductException {
		List<ReservationCount> list = reservationDao.selectReservationCountByUser(userId);
		if(list == null){
			throw new NotExistProductException();
		}
		return list;
	}

	@Override
	@Transactional
	public List<ReservationDTO> getReservationByUser(int userId) throws Exception {
		List<ReservationDTO> reservationList = reservationDao.selectReservationByUser(userId);
		List<ProductPrice> priceList = reservationDao.selectReservationPriceByType(userId);
		if(reservationList == null){
			//exception
			throw new Exception();
		} else {
			for(ReservationDTO reservation : reservationList){
				for(ProductPrice price : priceList){
					if(reservation.getProductId() == price.getProduct_id()){
						switch(price.getPrice_type()){
							case 1:
								reservation.setGeneralTicketCost(price.getPrice());
								break;
							case 2:
								reservation.setYouthTicketCost(price.getPrice());
								break;
							case 3:
								reservation.setChildTicketCost(price.getPrice());
								break;
						}
					}
				}
			}
		}
		return reservationList;
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
