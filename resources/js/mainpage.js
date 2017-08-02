require.config({

    baseUrl: '/resources/js',

    paths: {
        jquery: 'node_modules/jquery/dist/jquery',
		handlebars:'node_modules/handlebars/dist/handlebars'

    }
});

// Start the main app logic.
require(['jquery', 'handlebars'],
    function   ($,Handlebars) {
        //카테고리 모듈
        var resv_category = (function(){

            //변수
            var categorySource = $('#category-template').html();
            var categoryTemplate = Handlebars.compile(categorySource);

            //함수
            var getList;
            var drawCategory;

            getList = function() {
                var list_result;
                var ajax= $.ajax({
                    url:'/api/categories',
                    dataType:'json',
                    type:'get',
                    success:drawCategory
                });
            };
            drawCategory = function (result) {
                $("ul.event_tab_lst.tab_lst_min").append(categoryTemplate(result));
            };

            return {

                getCategoryList: function( ) {
                    getList();
                },

                change : function() {
                    $('.item > .active').removeClass('active');
                    $(this).find('.anchor').addClass('active');
                    resv_product.reDraw($(this).data('category'));
                }

            };
        })();

//프로모션 시간 모듈
        var resv_time = (function(){

            //변수
            var interval=null;
            var timeout=null;
            var imgcnt=($('ul.visual_img').children().length-1);
            var curcnt=0;
            var clicked=false;

            var	startInterval = function() {
                interval=setInterval(resv_promotion.moveNext,2000);//순환
                //interval=setInterval(moveNext,2000);//롤백
                timeout=null;
            };
            var	stopInterval = function() {
                clearInterval(interval);

            };
            var	startTimeout = function() {
                timeout= setTimeout(startInterval,4000);
            };
            var	stopTimeout = function() {
                clearTimeout(timeout);
                timeout=null;
            };

            return {
                buttonClicked : function() {
                    stopTimeout();
                    startTimeout();
                    stopInterval();
                    setTimeout(function(){ clicked = false; }, 1000);
                },
                getClicked : function() {
                    return clicked;
                },
                setClicked : function(_clicked) {
                    clicked=_clicked;
                },
                mouseEnter : function() {
                    stopInterval();
                    if(timeout===null)
                        startTimeout();
                },
                init : function() {
                    console.log("on");
                    startInterval();
                },
                inactive : function() {
                    console.log("off");
                    stopInterval();
                    stopTimeout();
                }
            }
        })();
//상단 프로모션 모듈
        var resv_promotion = (function(){
            var imgcnt=($('ul.visual_img').children().length-1);
            var curcnt=0;
            var clicked=false;

            var visual_img= $('ul.visual_img');
            var last_li;
            var first_li;

            function moveNext() {
                var li =   $('ul.visual_img li:first-child');
                if(curcnt === imgcnt) {
                    curcnt=0;
                    visual_img.animate({ "left": "0px" }, "normal");
                }
                else {
                    curcnt++;
                    visual_img.animate({ "left": "-=338px" }, "slow");
                }
            }
            function movePrev() {
                if(curcnt===0) {
                    curcnt=imgcnt;
                    visual_img.animate({
                        "left": "-="+ 338 * imgcnt+"px"
                    }, "normal");
                }
                else {
                    curcnt--;
                    visual_img.animate({
                        "left": "+=338px"
                    }, "slow");
                }
            }
            function moveRecurvNext() {
                first_li =   $('ul.visual_img li:first-child');
                visual_img.animate({ "left": "-=338px" },
                    {
                        duration : 1000,
                        complete : function() {
                            visual_img.css('left',"0px");
                            visual_img.append(first_li);
                        }
                    }
                );
            }
            function moveRecurvPrev() {
                last_li =   $('ul.visual_img li:last-child');
                var ul = $('ul.visual_img');
                visual_img.prepend(last_li);
                visual_img.css('left',"-=338px");
                visual_img.animate({ "left": "+=338px" }, "slow");
            }

            return {
                moveNext : function() {
                    moveRecurvNext();
                },
                movePrev : function() {
                    moveRecurvPrev();
                }
            }
        })();


        var resv_product = (function(){

            var productSource = $('#product-template').html();
            var productTemplate = Handlebars.compile(productSource);

            var requestProduct = function(categoryId) {
                $.ajax({
                    url:'/api/categories/' + categoryId + "/products",
                    dataType:'json',
                    type:'GET',
                    headers:{
                        'offset':$('.wrap_event_box li.item').length
                    },
                    success:drawProduct
                });
            };
            var updateCount = function(count) {
                $('.event_lst_txt > .pink').text(count+"개");
            };

            var drawProduct = function(result,status,xhr) {
                updateCount(xhr.getResponseHeader('totalCount'));
                for(var i in result) {
                    if(i%2===0){
                        $('.lst_event_box.left').append(productTemplate(result[i]));
                    }
                    else
                        $('.lst_event_box.right').append(productTemplate(result[i]));
                }
            };

            return {
                requestProduct :	function (categoryId) {
                    requestProduct(categoryId);
                },
                reDraw : function(categoryId){
                    $('.lst_event_box li.item').remove();
                    requestProduct(categoryId);
                }
            }
        })();


        resv_time.init();
        $(window).focus(function() {
            resv_time.init();
        });

        $(window).blur(function() {
            resv_time.inactive();
        });


        resv_category.getCategoryList();

        $('.event_tab_lst.tab_lst_min').on('click','.item',function() {
            resv_category.change.apply(this);
        });

        $('.btn_nxt_e').on({
            click : function () {
                if(!resv_time.getClicked()) {
                    resv_time.setClicked(true);
                    resv_promotion.moveNext();
                    resv_time.buttonClicked();
                }
            },
            mouseenter:resv_time.mouseEnter
        });

        $('.btn_pre_e').on({
            click:function() {
                if(!resv_time.getClicked()) {
                    resv_time.setClicked(true);
                    resv_promotion.movePrev();
                    resv_time.buttonClicked();
                }
            },
            mouseenter: resv_time.mouseEnter
        });

        $('.more').on('click','button.btn',function() {
            var categoryId = $('li.item > a.active').closest('.item').data('category');
            resv_product.requestProduct(categoryId);
        });
        $(document).scroll(function(){
            if($(window).scrollTop() >= $(document).height() - $(window).height()){
                var categoryId = $('li.item > a.active').closest('.item').data('category');
                resv_product.requestProduct(categoryId);
            }
        });

        resv_product.requestProduct(0);

    });











