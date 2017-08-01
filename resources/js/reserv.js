var Reserve = (function(){
    var tickets = [];
    function handlingProduct(product) {

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

        $.each($('div.qty'),function(i,result){
            tickets.push(new Ticket($(result)));
            tickets[i].on("plus",function(){
                BookerInfo.updateTotalCount(1);
            });
            tickets[i].on("minus",function(){
                BookerInfo.updateTotalCount(0);
            });
        });
    }


    return {
        init : function(product){
            handlingProduct(product);

        }
    }
})();

var BookerInfo = (function(){

    var totalCount=0;

    var phoneNum;
    var email;

    var phoneNumPattern=/^\d{2,3}-\d{3,4}-\d{4}$/;
    var emailPattern=/\w+@+\w+\.+[A-Za-z]{2,3}$/;
    var userAgreement;

    var phoneValid = false;
    var emailValid = false;
    var agreementValid = false;

    var checkReserveBtn = function() {
        if (totalCount != 0 && phoneValid && emailValid && agreementValid) {
            $('div.bk_btn_wrap').removeClass('disable');
        }
        else {
            $('div.bk_btn_wrap').addClass('disable');
        }
    };

    function handlingBookerInfo(bookerInfoData){
        HandlebarsModule.create($('#booker-info-template'),bookerInfoData);

        phoneNum = $('div.inline_control > input.tel');
        email = $('div.inline_control > input.email');
        userAgreement = $('div.agreement > input#chk3');

    }

    function setEvent(){
        $('a.btn_agreement > span.btn_text').on('click',function(){
            $(this).closest('.agreement').toggleClass('open');
        });

        userAgreement.on('click',function(){
            if(userAgreement.prop('checked')) {
                agreementValid = true;
                checkReserveBtn();
            }
            else {
                agreementValid = false;
                checkReserveBtn();
            }
        });

        validCheck(email,emailPattern);
        validCheck(phoneNum,phoneNumPattern);

    }

    function validCheck(position,pattern) {
        var checkPoint = position.closest('.inline_form').find('label > span');
        position.keyup(function(){
            if(pattern.test(position.val())) {
                checkPoint.addClass('valid');
                checkPoint.removeClass('invalid');
                if(position.attr('id') === "email") {
                    emailValid = true;
                }
                else if(position.attr('id') === "tel") {
                    phoneValid = true;
                }
                else if(position.attr('id') === "chk3") {
                    agreementValid = true;
                }
                checkReserveBtn();

            }else {
                checkPoint.removeClass('valid');
                checkPoint.addClass('invalid');
                if(position.attr('id') === "email") {
                    emailValid = false;
                }
                else if(position.attr('id') === "tel") {
                    phoneValid = false;
                }
                else if(position.attr('id') === "chk3") {
                    agreementValid = false;
                }
                checkReserveBtn();
            }
        });

    }

    function updateTotalCount(flag){
        flag ? totalCount++:totalCount--;
        $('p.inline_txt > span').text(totalCount);
        checkReserveBtn();
    }

    return {
      init:function(bookerInfoData){
          handlingBookerInfo(bookerInfoData);
          setEvent();
      },
      updateTotalCount : updateTotalCount
    }
})();

var Storage = (function(){
    var productId = $(location).attr('href').split('/')[4];
    var productPrice;
    var productImage;
    var product;
    var user;

    return {
      init : function(){
          var productPrice = Init.getData('/api/products/' + productId + '/prices');
          var productImage = Init.getData('/api/products/' + productId + '/images');
          var product = Init.getData('/api/products/'+ productId);
          var user = Init.getData('/api/users');

          return $.when(productPrice,productImage,product,user);
      },
      getProductReserve:function(){
        product.prices = productPrice;
        product.images = productImage;
        return product;
      },
      getBookerInfo:function(){
        return {"display_start":product.display_start,
                "display_end":product.display_end,
                "user":user};
      },
      setStorage:function(prices,images,products,users){
          productPrice = prices;
          productImage = images;
          product = products;
          user = users;
      }
    }


})();

Storage.init().done(
    function(priceResult,imageResult,productResult,userResult){
      console.log(priceResult);
        Storage.setStorage(priceResult[0],imageResult[0],productResult[0],userResult[0]);
        Reserve.init(Storage.getProductReserve());
        BookerInfo.init(Storage.getBookerInfo());

});

$(document).ready(function(){

});
