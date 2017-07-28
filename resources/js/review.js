function Popup(){

};

Popup.prototype = new eg.Component();
Popup.prototype.constructor = Popup;
Popup.prototype.showPopup = function(){

    this.trigger("clickPopup");
};

var resv_reviews = (function() {

    var flicking = new FlickingModule();
    var popup = new Popup();

    var productId = $(location).attr('href').split('/')[4];
    var page = 0;

    var requestReviews = function() {
        $.ajax({
            url:'/api/userComment/'+productId+"?page="+page+"&limit=10",
            dataType:'json',
            type:'get',
            success:reviewInit
        });
    };
    var reviewInit = function(result,status,xhr) {
        reviewObject = result;
        HandlebarsModule.customHelper("resHeader", function (name) {
            if (name === 'rate') {
                return (xhr.getResponseHeader('avgScore') / 5) * 100;
            }
            else {
                return xhr.getResponseHeader(name);
            }
        });


        drawReviews(reviewObject);
    };

    var popupEvent = function() {
        $('div.thumb_area').on('click','a.thumb',function(){

            HandlebarsModule.create($('#product-photoviews-template'),($(this).data('imglist')));
            popup.showPopup.call(popup);

        })
    };

    var drawReviews = function(reviews){
        HandlebarsModule.create($('#product-reviews-template'),reviews);
        popupEvent();
        popup.on("clickPopup",function(){
            flicking.init($('ul.popup_img'));
        })

    };

    return{
        init: function(){
            requestReviews();

        }
    }

})();

resv_reviews.init();