0.Reservation-system
================
이 프로젝트는 기본적인 웹프로그래밍 지식으로 Front-End에서 Back-End까지 전문화된 개발을 한다.  
Back-End/Front-End 지식을 기반으로 예약 서비스를 구현할 수 있다.  
Back-End : Spring을 활용하여 API을 추축하여 개발할 수 있다.  
Front-End : jQuery을 기반으로 요구 사항을 개발할 수 있다.  

team. Apink
[김성진, 정구범, 윤주형]

1.Convention
=============
 우리끼리 규칙

## [BE]
* 메소드는 동사, lowerCamelCase
* 클래스는 명사, UpperCamelCase
* 

## [FE]
* module
* component
* gulp
* require

2.RESTFul API 정리
=================

2.1 category


URI|method|기능
----|-------|----
/categories|GET|전체 카테고리 목록 조회
/categories|POST|카테고리 등록
/categories/{id}|PUT|카테고리 수정
/categories/{id}|DELETE|카테고리 삭제
/categories/{id}/products + page(param)|GET|선택된 카테고리에 등록된 상품 목록 조회
/categories/{id}/count + page(param)|GET|선택된 카테고리에 등록된 상품 갯수 조회


2.2 product  


URI|method|기능
----|-------|----
/products + page(param)|GET|전체 상품 목록 조회
/products|POST|상품 등록
/products/{id}|PUT|상품 수정
/products/{id}|DELETE|상품 삭제
/products/{id}|GET|상품 상세 조회
/products/{id}/images|GET|선택된 상품에 등록된 파일 조회

2.3 images (file : 여기서는 image만 사용한다.) 


URI|method|기능
----|-------|----
/images|POST|파일 등록
/images/{id}|GET|해당 파일 화면에 출력

2.4 comment   


URI|method|기능
----|-------|----
/comments|GET|파일 등록
/comments|POST|선택된 상품에 등록된 파일 조회
/comments|PUT|해당 파일 화면에 출력

2.5 login //user 와 구분하여 로그인만 처리


URI|method|기능
----|-------|----
/login|GET|네이버 아이디 로그인 화면으로 이동
/login/check|GET|로그인이 유효한지 검사 이후 유효하면 redirect:/{다음페이지}  유효하지 않으면 login화면

