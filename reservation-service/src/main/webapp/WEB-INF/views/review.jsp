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
#photoviewer {
  position: fixed;
    overflow: hidden;
    top: 0;
    width: 414px;
    height: 100%;
    background-color: #23252b;
    z-index: 10000;
}

#close{
	position: relative;
	width:30px;
	height:30px;
	float:right;
	background-image: url('/resources/img/Close.png');
	background-color:#23252b;
	background-size: 30px 30px;
	z-index: 10001;


}

.popup_img {
    position: relative;
    min-height: 210px;
    font: 0/0 a;
    color: #fff;
    white-space: nowrap
}
.popup_img .item{
  overflow: hidden;
    position: relative;
    display: inline-block;
    white-space: normal;
    vertical-align: top;
    padding-top :100px
}
</style>
</head>

<body>
    <div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="#" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="#" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="#" class="btn_my"> <span title="내 예약">MY</span> </a>
			</header>
		</div>
        <div class="ct">
            <div class="wrap_review_list">
                <div class="review_header">
                    <div class="top_title gr">
                        <a href="javascript:history.back()" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                        <h2><a class="title" href="#">오디컴퍼니 주식회사</a></h2>
                    </div>
                </div>
                <div class="section_review_list">
                <script id="product-reviews-item-template" type="text/x-handlebars-template">
						<div>
							{{#each this}}
								
								<li class="list_item">
									<div>
										{{#if imgList}}
										<div class="review_area">
											<div class="thumb_area">
												<a class="thumb" title="이미지 크게 보기" data-imgList=[{{imgList}}]> <img
													width="90" height="90" class="img_vertical_top"
													src="/files/{{imgList.[0]}}"
													alt="리뷰이미지">
												</a> <span class="img_count">{{imgList.length}}</span>
											</div>
										{{else}}
										<div class="review_area no_img">		
										{{/if}}
											<h4 class="resoc_name">{{name}}</h4>
											<p class="review">{{comment}}</p>
										</div>
										<div class="info_area">
											<div class="review_info">
												<span class="grade">{{score}}</span> <span class="name">{{username}}</span>
												<span class="date">{{createDate}} 방문</span>
											</div>
										</div>
									</div>
								</li>
								{{/each}}
					</div>

				</script>
                    <script id="product-reviews-template" type="text/x-handlebars-template">
					<div class="review_box">
						{{#if this}}
						<h3 class="title_h3">예매자 한줄평</h3>
						<div class="short_review_area">
							<div class="grade_area">
								<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<span class="graph_mask"> <em class="graph_value"
									style="width: {{resHeader 'rate'}}%;"></em>
								</span> <strong class="text_value"> <span>{{resHeader 'avgScore'}}</span> <em
									class="total">5.0</em>
								</strong> <span class="join_count"><em class="green">{{resHeader 'totalCount'}}건</em> 등록</span>
							</div>
							<ul class="list_short_review">
								{{#> reviewItem}}
    								
								{{/reviewItem}}
							</ul>
						</div>
						{{/if}}
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한
								이용자가 남긴 평가입니다.</span>
						</p>
					</div>
					</script>
                </div>
            </div>
        </div>
        <hr> </div>
		<footer>
	        <div class="gototop">
	            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
	        </div>
	        <div id="footer" class="footer">
	            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
	            <span class="copyright">© NAVER Corp.</span>
	        </div>
	    </footer>
	    	<script id="product-photoviews-template" type="text/forHandlebars">
				<div id="photoviewer">
					
					<div id="header" class="header_tit"></div>
						<a id = "close"></a>
						<ul class="popup_img">
							{{#each this}}
							<li class="item">
								<img class="img_thumb" src="/files/{{this}}"/>
							</li>
							{{/each}}
						</ul>
				</div>
	</script>
</body>
<script src="/resources/js/node_modules/jquery/dist/jquery.js"></script>
<script src="/resources/js/node_modules/handlebars/dist/handlebars.js"></script>
<script src="/resources/js/node_modules/@egjs/component/dist/component.js"></script>
<script src="/resources/js/handlebarsModule.js"></script>
<script src="/resources/js/flicking.js"></script>
 <script src="/resources/js/review.js"></script>
</html>
