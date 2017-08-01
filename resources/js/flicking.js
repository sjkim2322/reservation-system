//현재 page에서는 상단타이틀이 핸들바를통해 그려진 이후 init!
//targetelemnet가 그때 그려짐
//flicking ul태그 받고 밑에 li 태그 Flicking
function Flicking() {
  var testval=0;
  //플리킹 메인 엘리먼트 이 엘리먼트의 right값으로 animate
  var flickingTarget;
  //플리킹 단위로 사용되는 엘리먼트 현재사용 X
  var targetChildren;
  //플리킹 할 엘리먼트 개수
  var childrenLength;
  //플리킹단위가 메인엘리먼트width와 다를수 있음, 메인페이지 프로모션의 경우그럼
  var flickingSize;
  //플리킹 되여야할 최소 이동길이
  var boundary;
  //mousemove에서 boundary값 over시 더이상 움직이지 않도록
  var maxMargin;
  //현재 보이는 자식 엘리먼트의 인덱스
  var currentIndex;
  //메인 엘리먼트기준으로 현재 Right값
  var currentRight;
  //초기 메인 엘리먼트 위치
  var touchX=0;

  var moveRight = function() {
    if(currentIndex < (childrenLength-1)) {
      currentIndex++;
      currentRight = currentIndex*flickingSize;
      move();
    }
  }
  var moveLeft = function() {
    if(currentIndex != 0) {
      currentIndex--;
      currentRight = currentIndex*flickingSize;
      move();
    }
  }
  var move = function() {
      flickingTarget.animate({'right': currentRight },"normal");
      //이동된 인덱스 전달
      flickingTarget.trigger("moved",currentIndex+1);
  }

  //mouse이벤트안에서는 절대경로는 move외에 사용 X
  var addFlickingEvent = function() {
    // console.log(flickingTarget);
    flickingTarget.on('touchstart', function(event) {
      touchX=event.changedTouches[0].pageX;
    });
    flickingTarget.on('touchmove', function(event) {
      // console.log("curpos" + (touchX-event.changedTouches[0].pageX));
      var curPosition = currentRight+(touchX-event.changedTouches[0].pageX);
      if(curPosition >= -boundary && curPosition <= maxMargin) {
        flickingTarget.css('right',currentRight+((touchX-event.changedTouches[0].pageX)));
      }
    });
    flickingTarget.on('touchend', function(event) {
      // console.log("end" + (touchX-event.changedTouches[0].pageX));
      moveTarget(touchX-event.changedTouches[0].pageX);
    });
  };

  var moveTarget =  function(movedLength) {
    //현재 이동한 길이에 따라 플리킹 되어야할 위치를 지정
    //오른쪽
    if(movedLength > boundary && currentIndex < childrenLength-1) {
      moveRight();
    //왼쪽
    } else if(movedLength < -boundary && currentIndex != 0) {
      moveLeft();
    }
    else { //조건에 안걸릴 경우 이동없음
      move();
    }
  }
  //optional buttonEvent 추가
  this.buttonInit = function(left,right) {
    left.on('click',moveLeft);
    right.on('click',moveRight);
  }

  this.init = function(target) {
    flickingTarget = target;
    targetChildren = target.children();
    childrenLength = targetChildren.length;
    flickingSize = targetChildren.css('width').replace("px","");
    boundary = flickingSize/4;
    maxMargin = (flickingSize * (childrenLength-1))+boundary;
    currentIndex = 0;
    currentRight = currentIndex * flickingSize;
    addFlickingEvent();
  };

};
