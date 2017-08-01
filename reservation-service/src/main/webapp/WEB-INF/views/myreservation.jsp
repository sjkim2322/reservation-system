<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
	<style>
	    .invisible {
			display: none;
		}
    </style>
</head>

<body>
    <div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="http://naver.com" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="/" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="/myreservation" class="btn_my"> <span title="내 예약">MY</span> </a>
            </header>
        </div>
        <hr>
        <div class="ct">
            <div class="section_my">
                <!-- 예약 현황 -->
                <div class="my_summary">
                    <ul class="summary_board">
                    <script id="count-template" type="text/x-handlebars-template">
                        <li class="item">
                            <!--[D] 선택 후 .on 추가 link_summary_board -->
                            <a href="#" class="link_summary_board on" data-filter="all"> <i class="spr_book2 ico_book2"></i> <em class="tit">전체</em><span class="figure">{{this.[0]}}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board" data-filter="confirmed"> <i class="spr_book2 ico_book_ss"></i> <em class="tit">이용예정</em><span class="figure">{{this.[1]}}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board" data-filter="used"> <i class="spr_book2 ico_check"></i> <em class="tit">이용완료</em><span class="figure">{{this.[2]}}</span> </a>
                        </li>
                        <li class="item">
                            <a href="#" class="link_summary_board" data-filter="cancel"> <i class="spr_book2 ico_back"></i> <em class="tit">취소·환불</em><span class="figure">{{this.[3]}}</span> </a>
                        </li>
					</script>
                    </ul>
                </div>
                <!--// 예약 현황 -->

                <!-- 내 예약 리스트 -->
                <div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0">
                        <li class="card one">
                        <!--[D] 예약확정: .confirmed, 취소된 예약&이용완료: .used 추가 card -->
							<div class=link_booking_details>
	                            <div class="card_header">
	                                <div class="left"></div>
	                                <div class="middle">
	                                    <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book2 -->
	                                    <i class="spr_book2 ico_clock"></i>
	                                    <span class="tit">예약 신청중</span>
	                                </div>
	                                <div class="right"></div>
	                            </div>
							</div>
                        </li>
                        <li class="card confirmed two">
                            <div class="link_booking_details">
                                <div class="card_header">
                                    <div class="left"></div>
                                    <div class="middle">
                                        <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                                        <i class="spr_book2 ico_check2"></i>
                                        <span class="tit">예약 확정</span>
                                    </div>
                                    <div class="right"></div>
                                </div>
							</div>
                        </li>
                        <li class="card used three">
                            <div class="link_booking_details">
                                <div class="card_header">
                                    <div class="left"></div>
                                    <div class="middle">
                                        <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                                        <i class="spr_book2 ico_check2"></i>
                                        <span class="tit">이용 완료</span>
                                    </div>
                                    <div class="right"></div>
                                </div>
							</div>
                        </li>
                        <li class="card used four">
                            <div class="link_booking_details">
                                <div class="card_header">
                                    <div class="left"></div>
                                    <div class="middle">
                                        <!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
                                        <i class="spr_book2 ico_cancel"></i>
                                        <span class="tit">취소된 예약</span>
                                    </div>
                                    <div class="right"></div>
                                </div>
							</div>
                        </li>
                    </ul>
                </div>
                <!--// 내 예약 리스트 -->
                <!-- 예약 리스트 없음 -->
                <div class="err invisible" > 
                	<i class="spr_book ico_info_nolist"></i>
                	<h1 class="tit">예약 리스트가 없습니다</h1>
				</div>
                <!--// 예약 리스트 없음 -->
            </div>
        </div>
        <hr>
    </div>
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div id="footer" class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>

    <!-- 취소 팝업 -->
    <!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
    <div class="popup_booking_wrapper" style="display:none;">
        <div class="dimm_dark" style="display:block"></div>
        <div class="popup_booking refund">
            <h1 class="pop_tit">
                <span>서비스명/상품명</span>
                <small class="sm">2000.0.00.(월)2000.0.00.(일)</small>
            </h1>
            <div class="nomember_alert">
                <p>취소하시겠습니까?</p>
            </div>
            <div class="pop_bottom_btnarea">
                <div class="btn_gray">
                    <a href="#" class="btn_bottom"><span>아니오</span></a>
                </div>
                <div class="btn_green">
                    <a href="#" class="btn_bottom"><span>예</span></a>
                </div>
            </div>
            <!-- 닫기 -->
            <a href="#" class="popup_btn_close" title="close">
                <i class="spr_book2 ico_cls"></i>
            </a>
            <!--// 닫기 -->
        </div>
    </div>
    <!--// 취소 팝업 -->
	<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
	<script src="/resources/js/node_modules/handlebars/dist/handlebars.js"></script>
	<script src="/resources/js/handlebarsModule.js"></script>
	<script src="/resources/js/node_modules/@egjs/component/dist/component.js"></script>
	
	<script>
	
	var Count =(function(){
		var count = [0, 0, 0, 0];
		
		drawCount = function(data){
			//0 :이용 신청 1 :이용 확정 2 :이용완료 3 :취소
			for (i = 0; i < data.length; i++) { 
				count[data[i].type] += data[i].count;
			}
			count[1] += count[0] 
			count[0] = count[1] + count[2] + count[3];
			HandlebarsModule.create($("#count-template"), count);
		};
		
		moveSummary = function(){
			$(".summary_board").on("click", function(event){
    			var view = $("a.link_summary_board");
    			var currentView = $(event.target).closest(view);
    			var filter = currentView.data("filter");
    			var err = $("div.err");
    			var card = $("li.card");
    			
				view.removeClass("on");
    			currentView.addClass("on");
    			err.addClass("invisible");
				card.addClass("invisible");
				 
    			if(filter == "all") {
    				if(currentView.find("span.figure").html() == 0){
	    				err.removeClass("invisible");
    				} else {
        				card.removeClass("invisible");
    				}
    			} else if (filter == "confirmed") {
    				if(currentView.find("span.figure").html() == 0){
    					err.removeClass("invisible");
    				}else{
	    				$("li.card.one").removeClass("invisible");
    					$("li.card.two").removeClass("invisible");
    				}
    			} else if (filter == "used") {
    				if(currentView.find("span.figure").html() == 0){
    					err.removeClass("invisible");
    				} else {
    					$("li.card.three").removeClass("invisible");
    				}
    			} else if (filter == "cancel") {
    				if(currentView.find("span.figure").html() == 0){
    					err.removeClass("invisible");
    				} else {
    					$("li.card.four").removeClass("invisible");
    				}
    			} else{
    				card.addClass("invisible");
    			}
    		});
		}
		
		getCount = function(){
			$.ajax({
		      type:'get',
		      url:'/api/reservations/count',
		      success:function(result) {
		    	  drawCount(result);
		    	  moveSummary();
		      }
		   });
		};
		
		return{
			getCount: getCount,
			increment: function(type){
				type.html(++count[3]);
			},
			decrement: function(type){
				type.html(--count[1]);
			},
			countCheck : function(){
				console.log(count);
			}
		}
	})();
	Count.getCount();

	
	var Reservation = (function(){
		var pop = $("div.popup_booking_wrapper");
		
		popup = function(){
			$(".booking_cancel").on("click", function(event){
				var wrapper = pop; 
				wrapper.fadeIn(1000)
				wrapper.css("display", "block");
				reject();
				cancelReservation(event);
			});
		};
		
		reject = function(){
			$("div.btn_gray, a.popup_btn_close").on("click", function(){
				pop.fadeOut( 1000, function() {
					$("div.btn_green").off("click");
					pop.css("display", "none");
				});
			});
		}
		cancelReservation = function(){
				var reservationInfoView = $(event.target).closest("div.card_detail");
				var view =reservationInfoView.find($("em.booking_number"));
				var id = view.data('id');
				$("div.btn_green").on("click", function(){
					modifyReservationType(id, 3, reservationInfoView);
				})
		};
		
		moveReservation = function(view){
			var content = view.closest("article.card_item");
			content.find("div.booking_cancel").addClass("invisible");
			$("li.card.four").append(content.html());
			content.html("");
		};
		
		modifyReservationType = function(id, type, view){
			pop.fadeOut( 1000, function() {
				pop.css("display", "none");
				updateReservationType(id, type, view);
			});
		}
		
		drawMyReservations = function(data){
			 if(data.length == 0){
				$("li.card").addClass("invisible");
				 $("div.err").removeClass("invisible");
	   	 	 } else {
		    	  var cancelSource = $("#cancel-reservation-template").html();
		    	  var reviewSource = $("#review-reservation-template").html();
		    	  var source = $("#reservation-template").html();
		    	  var cancelTemplate = Handlebars.compile(cancelSource);
		    	  var reviewTemplate = Handlebars.compile(reviewSource);
		    	  var template = Handlebars.compile(source);
		    	  
		    	  //0 :이용 신청 1 :이용 확정 2 :이용완료 3 :취소
		   		  var str = ["", "", "", ""];
		   		  for(var index in data){
		   			  if(data[index].reservationType == 0 ) {
		   				  str[0] +=  cancelTemplate(data[index]);	   				  
			    		  } else if(data[index].reservationType == 1) {
			    			  str[1] +=  cancelTemplate(data[index]);
			    		  } else if( data[index].reservationType == 2) {
			    			  str[2] +=  reviewTemplate(data[index]);
			    		  } else if(data[index].reservationType == 3) {
			    			  str[3] += template(data[index]);
			    		  }
		   		  }
		   		  $("li.card.one").append(str[0]);
		   		  $("li.card.two").append(str[1]);
		   		  $("li.card.three").append(str[2]);
		   		  $("li.card.four").append(str[3]);
		   		  popup();
			}
		}
		
		
		updateReservationType = function(id, type, view){
			$.ajax({
				type:"put",
				url:"/api/reservations/" + id,
				data : JSON.stringify(type),
				contentType: "application/json; charset=utf-8",
				success:function(){
					console.log("ddd");
					Count.countCheck();
					Count.increment($("i.spr_book2.ico_back ~ span.figure"));
					Count.decrement($("i.spr_book2.ico_book_ss ~ span.figure"));
					moveReservation(view);
				}
			});
		}
		getMyReservations = function(){
			$.ajax({
		      type:'get',
		      url:'/api/reservations/',
		      success:function(data) {
		    	  drawMyReservations(data);
		      }
			});
		}
		return {
			getMyReservations: getMyReservations
		}
	})();
	Reservation.getMyReservations();
	
    Handlebars.registerHelper('sumCost', function(generalTicketCost, generalTicketCount,
			youthTicketCost, youthTicketCount,
			childTicketCost, childTicketCount){
        return generalTicketCost * generalTicketCount +
				youthTicketCost * youthTicketCount +
				childTicketCost * childTicketCount;
    });
	
	</script>
	
		<script id="reservation-template" type="text/x-handlebars-template">
		<article class="card_item">
                        <a href="#" class="link_booking_details">
                            <div class="card_body">
                                <div class="left"></div>
                                <div class="middle">
                                    <div class="card_detail">
                                        <em class="booking_number" data-id={{id}}>No.{{reservationId}}</em>
                                        <h4 class="tit">{{name}}</h4>
                                        <ul class="detail">
                                            <li class="item">
                                                <span class="item_tit">일정</span>
                                                <em class="item_dsc">
										{{displayStart}}(월)~{{displayEnd}}(일)
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">내역</span>
                                                <em class="item_dsc">
										일반({{generalTicketCount}}),
										청소년({{youthTicketCount}}),
										어린이({{childTicketCount}})
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">상품</span>
                                                <em class="item_dsc">
										{{name}}
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">업체</span>
                                                <em class="item_dsc">
										{{name}}
									</em>
                                            </li>
                                        </ul>
                                        <div class="price_summary">
                                            <span class="price_tit">결제 예정금액</span>
                                            <em class="price_amount">
									<span>{{sumCost 
											generalTicketCost generalTicketCount
											youthTicketCost youthTicketCount
											childTicketCost childTicketCount}}</span>
									<span class="unit">원</span>
								</em>
                                        </div>
                                        <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                                    </div>
                                </div>
                                <div class="right"></div>
                            </div>
                            <div class="card_footer">
                                <div class="left"></div>
                                <div class="middle"></div>
                                <div class="right"></div>
                            </div>
                        </a>
			<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
		</article>
		</script>
		<script id="review-reservation-template" type="text/x-handlebars-template">
		<article class="card_item">
                        <a href="#" class="link_booking_details">
                            <div class="card_body">
                                <div class="left"></div>
                                <div class="middle">
                                    <div class="card_detail">
                                        <em class="booking_number" data-id={{id}}>No.{{reservationId}}</em>
                                        <h4 class="tit">{{name}}</h4>
                                        <ul class="detail">
                                            <li class="item">
                                                <span class="item_tit">일정</span>
                                                <em class="item_dsc">
										{{displayStart}}(월)~{{displayEnd}}(일)
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">내역</span>
                                                <em class="item_dsc">
										일반({{generalTicketCount}}),
										청소년({{youthTicketCount}}),
										어린이({{childTicketCount}})
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">상품</span>
                                                <em class="item_dsc">
										{{name}}
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">업체</span>
                                                <em class="item_dsc">
										{{name}}
									</em>
                                            </li>
                                        </ul>
                                        <div class="price_summary">
                                            <span class="price_tit">결제 예정금액</span>
                                            <em class="price_amount">
									<span>{{sumCost 
											generalTicketCost generalTicketCount
											youthTicketCost youthTicketCount
											childTicketCost childTicketCount}}</span>
									<span class="unit">원</span>
								</em>
                                        </div>
                                        <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
									<a href=/comments/
										<div class="bookingl">
                                            <button class="btn"><span>예매자 리뷰 남기기</span></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="right"></div>
                            </div>
                            <div class="card_footer">
                                <div class="left"></div>
                                <div class="middle"></div>
                                <div class="right"></div>
                            </div>
                        </a>
			<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
		</article>
		</script>
		<script id="cancel-reservation-template" type="text/x-handlebars-template">
		<article class="card_item">
                        <a href="#" class="link_booking_details">
                            <div class="card_body">
                                <div class="left"></div>
                                <div class="middle">
                                    <div class="card_detail">
                                        <em class="booking_number" data-id={{id}}>No.{{id}}</em>
                                        <h4 class="tit">{{name}}</h4>
                                        <ul class="detail">
                                            <li class="item">
                                                <span class="item_tit">일정</span>
                                                <em class="item_dsc">
										{{displayStart}}(월)~{{displayEnd}}(일)
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">내역</span>
                                                <em class="item_dsc">
										일반({{generalTicketCount}}),
										청소년({{youthTicketCount}}),
										어린이({{childTicketCount}})
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">상품</span>
                                                <em class="item_dsc">
										{{name}}
									</em>
                                            </li>
                                            <li class="item">
                                                <span class="item_tit">업체</span>
                                                <em class="item_dsc">
										{{name}}
									</em>
                                            </li>
                                        </ul>
                                        <div class="price_summary">
                                            <span class="price_tit">결제 예정금액</span>
                                            <em class="price_amount">
									<span>{{sumCost 
											generalTicketCost generalTicketCount
											youthTicketCost youthTicketCount
											childTicketCost childTicketCount}}</span>
									<span class="unit">원</span>
								</em>
                                        </div>
                                        <!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
                                        <div class="booking_cancel">
                                            <button class="btn"><span>취소</span></button>
                                        </div>
                                    </div>
                                </div>
                                <div class="right"></div>
                            </div>
                            <div class="card_footer">
                                <div class="left"></div>
                                <div class="middle"></div>
                                <div class="right"></div>
                            </div>
                        </a>
			<a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
		</article>
		</script>
</body>

</html>