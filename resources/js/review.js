function Popup(){

};

Popup.prototype = new eg.Component();
Popup.prototype.constructor = Popup;
Popup.prototype.showPopup = function(){

    this.trigger("clickPopup");
};

var resv_reviews = (function() {

    var flicking = new Flicking();
    var popup = new Popup();

    var productId = $(location).attr('href').split('/')[4];
    var page = 0;
    var scrollDownLock = 0;
    var scrollUpLock = 1;

    var requestReviews = function() {
        $.ajax({
            url:'/api/products/'+productId+"/comments?page=0"+"&limit=10",
            dataType:'json',
            type:'get',
            success:reviewInit
        });
    };

    var requestPageReviews = function(flag) {

        if(flag === 0) {
            scrollDownLock = 1;
        }else {
            scrollUpLock = 1;
        }

        $.ajax({
            url:'/api/products/'+productId+"/comments?page="+page+"&limit=10",
            dataType:'json',
            type:'get',
            success:function(data){
                drawReviews(data,flag);
            }
        });
    };

    var reviewInit = function(result,status,xhr) {
        var reviewObject = result;
        HandlebarsModule.customHelper("resHeader", function (name) {
            if (name === 'rate') {
                return (xhr.getResponseHeader('avgScore') / 5) * 100;
            }
            else {
                return xhr.getResponseHeader(name);
            }
        });

        HandlebarsModule.setPartial("reviewItem",$("#product-reviews-item-template"));

        HandlebarsModule.create($('#product-reviews-template'),reviewObject);

        popupEvent();
        popup.on("clickPopup",function(){
            flicking.init($('ul.popup_img'));
            $("#close").on("click",function(){
                $("#photoviewer").remove();
            })

        })
    };

    var popupEvent = function() {
        $('.section_review_list').on('click','a.thumb',function(){

            HandlebarsModule.create($('#product-photoviews-template'),($(this).data('imglist')));
            popup.showPopup.call(popup);

        })
    };

    var drawReviews = function(reviews,flag){
        if(reviews.length) {
            if(flag === 0) {
                console.log(page);
                if(page>1) {
                    $(".list_short_review > div").eq(0).remove();
                }
                var template = HandlebarsModule.createTemplate($("#product-reviews-item-template"));
                $(".short_review_area .list_short_review").append(template(reviews));
                scrollDownLock = 0;
                scrollUpLock = 0;
            }else if(flag === 1){

                $(".list_short_review > div").eq(1).remove();
                var preSize = $(document).height();
                var template = HandlebarsModule.createTemplate($("#product-reviews-item-template"));
                $(".short_review_area .list_short_review").prepend(template(reviews));

                $(document).scrollTop($(document).height() - preSize + 200);

                scrollDownLock=0;
                scrollUpLock =0;
                page++;
            }
        }else{
            if(flag === 0){
                scrollDownLock = 1;
                page--;
                console.log(page);
            }
        }

    };

    var loadReviewByScroll = function(){

        var maxHeight = $(document).height();
        var currentScroll = $(window).scrollTop() + $(window).height();
        var currentScrollUp = $(window).scrollTop();

        if (maxHeight <= currentScroll + 100 && scrollDownLock === 0 ) {
            console.log("scroll down event");
            page++;
            requestPageReviews(0);
        }else if(currentScrollUp - 200 <= 0 && scrollUpLock === 0 && page > 1){ // 100이였을 때는 안됨
            console.log("scroll up event");
            page = page-2;
            requestPageReviews(1);
        }
    };

    return{
        init: function(){
            requestReviews();
            $(document).scroll(loadReviewByScroll);
        }
    }

})();

resv_reviews.init();
