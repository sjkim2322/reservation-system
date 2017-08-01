
function Ticket(target,index) {
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
      this.minus(index);
      if(count===0) {
        totalPrice.closest('.individual_price').removeClass('on_color');
        toggling();
      }
    }
  }.bind(this));


  plusBtn.on('click',function(){
    countPosition.val(++count);
    totalPrice.text(countPosition.val()*price);
    this.plus(index);

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

Ticket.prototype = new eg.Component();
Ticket.prototype.constructor=Ticket;
Ticket.prototype.plus = function(index) {
  this.trigger("plus"+index);
};
Ticket.prototype.minus = function(index) {
  this.trigger("minus"+index);
};



// UserInfo.prototype = myObserver;
// UserInfo.prototype.constructor=UserInfo;
// UserInfo.prototype.valid = function(data) {
//   console.log(data.id);
//   this.trigger("valid",data);
// };
// UserInfo.prototype.invalid = function(data) {
//   this.trigger("invalid",data);
// }
