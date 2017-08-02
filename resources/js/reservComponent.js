
define(['node_modules/@egjs/component/dist/component'],function(egComponent){


    function Ticket(target) {

        this.price = target.find('em.product_dsc > span').text();
        this.minusBtn = target.find('div.clearfix > .ico_minus3');
        this.plusBtn = target.find('div.clearfix > .ico_plus3');
        this.countPosition = target.find('div.clearfix > .count_control_input');
        this.totalPrice = target.find('div.individual_price > .total_price');
        this.count = 0;

        this.eg = new egComponent();
        this.init();

    }

    Ticket.prototype.constructor=Ticket;
    Ticket.prototype.init= function(){
        this.minusBtn.on('click',this.minus.bind(this));
        this.plusBtn.on('click',this.plus.bind(this));
    };
    Ticket.prototype.on = function(trigger,callback){
        this.eg.on(trigger,callback);
    };
    Ticket.prototype.plus = function() {
        if(this.count === 0){
            this.toggling();
        }
        this.countPosition.val(++this.count);
        this.totalPrice.text(this.countPosition.val()*this.price);
        this.eg.trigger("plus");
    };
    Ticket.prototype.minus = function() {
        if(this.count > 0) {
            this.countPosition.val(--this.count);
            this.totalPrice.text(this.countPosition.val() * this.price);
            if (this.count === 0) {
                this.toggling();
            }
            this.eg.trigger("minus");
        }
    };
    Ticket.prototype.toggling = function(){
        this.minusBtn.toggleClass('disabled');
        this.countPosition.toggleClass('disabled');
        this.totalPrice.closest('.individual_price').toggleClass('on_color');

    };

    return Ticket;
});







