//
var resv_ProductDetail = (function () {

  //변수
  var productId = $(location).attr('href').split('/')[4];
  var productObject = null;
  var reviewObject = null;

  //함수
  var requestDetailProduct = function() {
    if(productObject === null) {
        $.ajax({
          url:'/api/productList/'+productId,
          dataType:'json',
          type:'get',
          success:titleInit
        });
    }
  };
  var requestReviews = function() {
    $.ajax({
      url:'/api/userComment/'+productId+"?limit=3&page=0",
      dataType:'json',
      type:'get',
      success:reviewInit
    });
  };
  var reviewInit = function(result,status,xhr) {
    reviewObject = result;
    HandlebarsModule.customHelper("resHeader",function(name) {
      if(name === 'rate') {
        return (xhr.getResponseHeader('avgScore') / 5) * 100;
      }
      else {
        return xhr.getResponseHeader(name);
      }
    });



    resv_reviews.drawReviews(reviewObject);
  }
  var titleInit = function(result) {
    productObject = result;
    resv_ProductImg.initTitle(productObject.id);
    resv_ProductTitleDetail.drawDescription(productObject);
    resv_ProductTitleDetail.drawEvent(productObject.event);
    $('div.section_btn').on('click','button.bk_btn',function() {
      location.href="/reservation/"+productId;
    })
  };
  var createLayer = function() {
    $('.visual_txt_tit span').text(productObject.name);
    $('.visual_txt_inn .visual_txt_dsc').text(productObject.description);
  }

  return  {
    init : function() {
      requestDetailProduct();
      requestReviews();
    },
    getProduct : function() {
      return productObject;
    },
    drawLayer : function() {
        createLayer();
    }
  }
})();


//상품 이미지 모듈
var resv_ProductImg = (function() {


  //변수
  var flicking = new FlickingModule();
  var numberingTarget;
  var leftBtn;
  var rightBtn;




  //함수
  var requestImageList = function(productId) {
    $.ajax({
      url:'/product/detail/images/'+productId,
      dataType:'json',
      type:'get',
      success:drawImage
    });
  };

  var numbering = function(event,index) {
    numberingTarget.text(index);

    //시작
    if(index===1) {
      leftBtn.find('i').addClass('off');
    }else {
      leftBtn.find('i').removeClass('off');
    }

    //끝 적용되는 CSS 클래스가 없음
    // if(index===event.target.childElementCount) {
    //   rightBtn.find('i').addClass('off');
    // }else {
    //   rightBtn.find('i').removeClass('off');
    // }
  }

  var drawImage = function (result) {
    HandlebarsModule.create($('#product-image-template'),result);
    resv_ProductDetail.drawLayer();
    leftBtn = $('div.prev > div.prev_inn');
    rightBtn = $('div.nxt > div.nxt_inn');
    numberingTarget=$('div.figure_pagination span:first');
    flicking.init($('ul.visual_img'));
    flicking.buttonInit(leftBtn,rightBtn)
    $('ul.visual_img').on("moved",numbering);
  };

  return {
    initTitle : function(productId) {
      requestImageList(productId);
    }
  };
})();



//상단 타이틀 세부 모듈
var resv_ProductTitleDetail = (function() {

  //변수

  var extend_bt = $('a.bk_more');
  //함수
  var extendDescription = function(descriptionSection) {
    if(descriptionSection.hasClass('close3')) {
      descriptionSection.removeClass('close3');
    }
    else {
      descriptionSection.addClass('close3');
    }
  }

  var addEvent = function() {
    extend_bt.on('click',function(){
      extend_bt.toggle();
      extendDescription($('div.store_details'));
    });
  };


  return {
    drawDescription : function(product) {
      HandlebarsModule.create($('#product-description-template'),product);
      addEvent();
    },
    drawEvent : function(event) {
      HandlebarsModule.create($('#product-event-template'),event);
    }
  }
})();

function Popup(){

};

Popup.prototype = new eg.Component();
Popup.prototype.constructor = Popup;
Popup.prototype.showPopup = function(){

    this.trigger("clickPopup");
};


//한줄평 모듈
var resv_reviews = (function() {

  var flicking = new FlickingModule();
  var popup = new Popup();

  //함수
  var addEvent = function() {
    $('div.thumb_area').on('click','a.thumb',function(){

      HandlebarsModule.create($('#product-photoviews-template'),($(this).data('imglist')));
      popup.showPopup.call(popup);

    })
  };

  return {
    drawReviews : function(reviews){
      HandlebarsModule.create($('#product-reviews-template'),reviews);
      addEvent();
      popup.on("clickPopup",function(){
          flicking.init($('ul.popup_img'));
      })
    }
  }
})();





//하단 상세 모듈
var ResvBottomDetail = (function(){

  var tabElements = $('ul.info_tab_lst > li.item');
  var targetElements = $('div.section_info_tab > div');


  //함수
  var controllTarget = function(openTarget) {
    targetElements.addClass('hide');
    openTarget.removeClass('hide');
  };
  var addEvent = function() {
    tabElements.on('click','a.anchor',function() {
    tabElements.find('a.active').removeClass('active');
      $(this).addClass('active');
      targetElements.toggle();
      // if($(this).closest('.item').hasClass('_detail')) {;
      //   controllTarget($('div.detail_area_wrap'));
      // }
      // else {
      //   controllTarget($('div.detail_location'));
      // }
    });
  }
  return {
    addEvent : addEvent
  }
})();
ResvBottomDetail.addEvent();
resv_ProductDetail.init();