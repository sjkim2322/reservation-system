var Count =(function(){
        var count = [0, 0, 0, 0];

        drawCount = function(data){
            //0 :이용 신청 1 :이용 확정 2 :이용완료 3 :취소
            for (i = 0; i < data.length; i++) {
                count[data[i].type] += data[i].count;
            }
            count[1] += count[0]
            count[0] = count[1] + count[2] + count[3];
            HandlebarsModule.create($("#count-template"), count);
        };

        moveSummary = function(){
            $(".summary_board").on("click", function(event){
                var view = $("a.link_summary_board");
                var currentView = $(event.target).closest(view);
                var filter = currentView.data("filter");
                var err = $("div.err");
                var card = $("li.card");

                view.removeClass("on");
                currentView.addClass("on");
                err.addClass("invisible");
                card.addClass("invisible");

                if(filter == "all") {
                    if(currentView.find("span.figure").html() == 0){
                        err.removeClass("invisible");
                    } else {
                        card.removeClass("invisible");
                    }
                } else if (filter == "confirmed") {
                    if(currentView.find("span.figure").html() == 0){
                        err.removeClass("invisible");
                    }else{
                        $("li.card.one").removeClass("invisible");
                        $("li.card.two").removeClass("invisible");
                    }
                } else if (filter == "used") {
                    if(currentView.find("span.figure").html() == 0){
                        err.removeClass("invisible");
                    } else {
                        $("li.card.three").removeClass("invisible");
                    }
                } else if (filter == "cancel") {
                    if(currentView.find("span.figure").html() == 0){
                        err.removeClass("invisible");
                    } else {
                        $("li.card.four").removeClass("invisible");
                    }
                } else{
                    card.addClass("invisible");
                }
            });
        }

        getCount = function(){
            $.ajax({
              type:'get',
              url:'/api/reservations/count',
              success:function(result) {
                  drawCount(result);
                  moveSummary();
              }
           });
        };

        return{
            getCount: getCount,
            increment: function(type){
                type.html(++count[3]);
            },
            decrement: function(type){
                type.html(--count[1]);
            }
        }
    })();

    var Reservation = (function(){
        var pop = $("div.popup_booking_wrapper");

        popup = function(){
            $(".booking_cancel").on("click", function(event){
                var wrapper = pop;
                wrapper.fadeIn(1000)
                wrapper.css("display", "block");
                reject();
                cancelReservation(event);
            });
        };

        reject = function(){
            $("div.btn_gray, a.popup_btn_close").on("click", function(){
                pop.fadeOut( 1000, function() {
                    $("div.btn_green").off("click");
                    pop.css("display", "none");
                });
            });
        }
        cancelReservation = function(){
                var reservationInfoView = $(event.target).closest("div.card_detail");
                var view =reservationInfoView.find($("em.booking_number"));
                var id = view.data('id');
                $("div.btn_green").on("click", function(){
                    modifyReservationType(id, 3, reservationInfoView);
                })
        };

        moveReservation = function(view){
            var content = view.closest("article.card_item");
            content.find("div.booking_cancel").addClass("invisible");
            $("li.card.four").append(content.html());
            content.html("");
        };

        modifyReservationType = function(id, type, view){
            pop.fadeOut( 1000, function() {
                pop.css("display", "none");
                updateReservationType(id, type, view);
            });
        }

        drawMyReservations = function(data){
             if(data.length == 0){
                $("li.card").addClass("invisible");
                 $("div.err").removeClass("invisible");
             } else {
                  var cancelSource = $("#cancel-reservation-template").html();
                  var reviewSource = $("#review-reservation-template").html();
                  var source = $("#reservation-template").html();
                  var cancelTemplate = Handlebars.compile(cancelSource);
                  var reviewTemplate = Handlebars.compile(reviewSource);
                  var template = Handlebars.compile(source);

                  //0 :이용 신청 1 :이용 확정 2 :이용완료 3 :취소
                  var str = ["", "", "", ""];
                  for(var index in data){
                      if(data[index].reservationType == 0 ) {
                          str[0] +=  cancelTemplate(data[index]);
                          } else if(data[index].reservationType == 1) {
                              str[1] +=  cancelTemplate(data[index]);
                          } else if( data[index].reservationType == 2) {
                              str[2] +=  reviewTemplate(data[index]);
                          } else if(data[index].reservationType == 3) {
                              str[3] += template(data[index]);
                          }
                  }
                  $("li.card.one").append(str[0]);
                  $("li.card.two").append(str[1]);
                  $("li.card.three").append(str[2]);
                  $("li.card.four").append(str[3]);
                  popup();
            }
        }


        updateReservationType = function(id, type, view){
            $.ajax({
                type:"put",
                url:"/api/reservations/" + id,
                data : JSON.stringify(type),
                contentType: "application/json; charset=utf-8",
                success:function(){
                    console.log("ddd");
                    Count.countCheck();
                    Count.increment($("i.spr_book2.ico_back ~ span.figure"));
                    Count.decrement($("i.spr_book2.ico_book_ss ~ span.figure"));
                    moveReservation(view);
                }
            });
        }
        getMyReservations = function(){
            $.ajax({
              type:'get',
              url:'/api/reservations/',
              success:function(data) {
                  drawMyReservations(data);
              }
            });
        }
        return {
            getMyReservations: getMyReservations
        }
    })();
