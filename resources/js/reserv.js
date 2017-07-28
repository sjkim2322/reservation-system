var ReservInit = (function(){

  var productId = $(location).attr('href').split('/')[4];
  var productPrice = Init.getData('/product/detail/prices/'+ productId);
  var productImage = Init.getData('/product/detail/images/'+ productId);
  var product = Init.getData('/api/productList/'+ productId);
  var user = Init.getData('/session/user');



  $.when(productPrice,productImage,product,user).done(
    function(priceResult,imageResult,productResult,userResult){
    handlingProduct(priceResult[0],imageResult[0],productResult[0],userResult[0]);
  });

  function handlingProduct(price,image,product,user) {
    product.prices = price;
    product.images = image;
    product.user = user;
    HandlebarsModule.customHelper("priceHelper",function(index) {
        var priceType;
        if(index===1) { priceType = "성인(만 19~64세)";}
        else if(index===2) {priceType ="청소년(만 13~18세)";}
        else if(index===3) {priceType ="어린이(만 4~12세)";}

        return priceType;
    });
    HandlebarsModule.customHelper("discountHelper",function(rate,price) {
      if(typeof price === 'number') {
        var discountPrice = price - (rate*price);
        return discountPrice;
      }else if(typeof price ==='object'){
        return rate*100;
      }
    });
    HandlebarsModule.create($('#product-reservDetail-template'),product);
    new Validation();
    new UserInfo();
    $.each($('div.qty'),function(i,result){
      new Ticket($(result));
    });

  }
})();



function Validation() {
  var totalCount = 0;
  var phoneValid = false;
  var emailValid = false;
  var agreementValid = false;

  var updateTotalCount = function(total) {
    $('p.inline_txt > span').text(total);
  }
  var checkReservBtn = function() {
    if(totalCount!=0 && phoneValid && emailValid && agreementValid) {
      $('div.bk_btn_wrap').removeClass('disable');
    }
    else {
        $('div.bk_btn_wrap').addClass('disable');
    }
  }
  this.on("plus",function(){
    updateTotalCount( ++totalCount);
    checkReservBtn();
  });
  this.on("minus",function(){
    updateTotalCount( --totalCount);
    checkReservBtn();
  });
  this.on("valid",function(data){
    if(data.id === "email") {
      emailValid = true;
    }
    else if(data.id === "tel") {
      phoneValid = true;
    }
    else if(data.id === "chk3") {
      agreementValid = true;
    }
    checkReservBtn();
  });
  this.on("invalid",function(data){
    if(data.id === "email") {
      emailValid = false;
    }
    else if(data.id === "tel") {
      phoneValid = false;
    }
    else if(data.id === "chk3") {
      agreementValid = false;
    }
    checkReservBtn();
  });
}
  Validation.prototype = myObserver;
  Validation.prototype.constructor=Validation;