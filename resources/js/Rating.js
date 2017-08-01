function Rating(root,targetClassName,activeClassName) {
  this.activeClassName = activeClassName;
  this.targetList = root.find("[class="+targetClassName+"]");
  this.mouseEnteredTarget = {score : 0};
  this.clickedTarget = {score : 0};
  this.init();
}

Rating.prototype = new eg.Component();
Rating.prototype.constructor = Rating;
Rating.prototype.init = function() {
  var instance = this;
  instance.targetList.on("mouseenter",function() {
      instance.mouseenter.call(this,instance);
      //call은 인자가 1개씩, apply는 배열
    });
  instance.targetList.on("mouseleave",function() {
      instance.mouseleave(instance);
  });
  instance.targetList.on("click",function(event) {
    instance.click.call(this,event,instance);
  });

};

Rating.prototype.mouseenter = function(instance) {
  instance.mouseEnteredTarget.score = this.value;
    instance.targetList.each(function(){
      if(instance.mouseEnteredTarget.score < this.value) {
        $(this).removeClass(instance.activeClassName);
      } else if(this.value == instance.mouseEnteredTarget.score && this.value == instance.clickedTarget.score) {
        $(this).removeClass(instance.activeClassName);
        --instance.mouseEnteredTarget.score;
      } else {
        $(this).addClass(instance.activeClassName);
      }
    });
    instance.trigger("change",instance.mouseEnteredTarget);
}

Rating.prototype.mouseleave = function(instance) {
  instance.targetList.each(function(){
    if(instance.clickedTarget.score >= this.value) {
      $(this).addClass(instance.activeClassName);
    } else {
      $(this).removeClass(instance.activeClassName);
    }
  });
  instance.trigger("change",instance.clickedTarget);
}

Rating.prototype.click = function(event,instance) {
  event.preventDefault();
  instance.mouseenter.call(this,instance);
  if($(this).hasClass(instance.activeClassName)) {
      instance.clickedTarget.score = this.value;
  } else  {//mouseenter 이벤트가없는 모바일에만해당
    instance.clickedTarget.score = this.value -1;
  }
  instance.trigger("change",instance.clickedTarget);
}
// Rating.prototype.click = function(event,instance) {
//   event.preventDefault();
//   if($(this).hasClass(instance.activeClassName)) {
//     if(this.value == instance.clickedTarget.score) { //mouseenter 이벤트가없는 모바일에만해당 (web에서는 클래스가 있는데 클릭score와 value가 같을 수없음)
//       $(this).removeClass(instance.activeClassName);
//       instance.clickedTarget.score = this.value -1;
//     } else {
//       instance.clickedTarget.score = this.value;
//     }
//   } else if(this.value - 1 == instance.clickedTarget.score) {//mouseenter 이벤트가없는 모바일에만해당
//     $(this).addClass(instance.activeClassName);
//     instance.clickedTarget.score = this.value;
//   } else {
//     instance.clickedTarget.score = this.value -1;
//   }
//   instance.trigger("change",instance.clickedTarget);
// }
