
var myObserver = new eg.Component();

function Ticket(target) {

  var price = target.find('em.product_dsc > span').text();
  var minusBtn = target.find('div.clearfix > .ico_minus3');
  var plusBtn = target.find('div.clearfix > .ico_plus3');
  var countPosition = target.find('div.clearfix > .count_control_input');
  var totalPrice = target.find('div.individual_price > .total_price');
  var count = 0;


  minusBtn.on('click',function(){
    if(count!=0) {
      countPosition.val(--count);
      totalPrice.text(countPosition.val()*price);
      this.minus();
      if(count===0) {
        totalPrice.closest('.individual_price').removeClass('on_color');
        toggling();
      }
    }
  }.bind(this));
  plusBtn.on('click',function(){
    countPosition.val(++count);
    totalPrice.text(countPosition.val()*price);
    this.plus();
    if(count===1) {
      totalPrice.closest('.individual_price').addClass('on_color');
      toggling();
    }
  }.bind(this));

  function toggling() {
      minusBtn.toggleClass('disabled');
      countPosition.toggleClass('disabled');
    }
}

Ticket.prototype = myObserver;
Ticket.prototype.constructor=Ticket;
Ticket.prototype.plus = function() {
  this.trigger("plus");
};
Ticket.prototype.minus = function() {
  this.trigger("minus");
};


function UserInfo() {
    var phoneNum;
    var email;

    var phoneNumPattern=/^\d{2,3}-\d{3,4}-\d{4}$/;
    var emailPattern=/\w+@+\w+\.+[A-Za-z]{2,3}$/;
    var userAgreement;

     var validCheck = function(position,pattern) {
      var checkPoint = position.closest('.inline_form').find('label > span');
      position.keyup(function(){
        if(pattern.test(position.val())) {
          checkPoint.addClass('valid');
          checkPoint.removeClass('invalid');
          this.valid({"id":position.attr('id')});
        }else {
          checkPoint.removeClass('valid');
          checkPoint.addClass('invalid');
          this.invalid({"id":position.attr('id')});
        }
      }.bind(this));
      position.trigger('keyup');
    }.bind(this);

    phoneNum = $('div.inline_control > input.tel')
    email = $('div.inline_control > input.email')
    userAgreement = $('div.agreement > input#chk3');
    $('a.btn_agreement > span.btn_text').on('click',function(){
      $(this).closest('.agreement').toggleClass('open');
    });
    validCheck(email,emailPattern);
    validCheck(phoneNum,phoneNumPattern);
    userAgreement.on('click',function(){
      if(userAgreement.prop('checked')) {
        this.valid({"id":userAgreement.attr('id')});
      }
      else {
        this.invalid({"id":userAgreement.attr('id')});
      }
    }.bind(this));
};
UserInfo.prototype = myObserver;
UserInfo.prototype.constructor=UserInfo;
UserInfo.prototype.valid = function(data) {
  console.log(data.id);
  this.trigger("valid",data);
};
UserInfo.prototype.invalid = function(data) {
  this.trigger("invalid",data);
}
