function Rating(root,targetClassName,activeClassName) {
  this.activeClassName = activeClassName;
  this.targetList = root.find("[class="+targetClassName+"]");
  this.mouseEnteredTarget = {score : 0};
  this.clickedTarget = {score : 0};
  this.init();
  this.mobileCheck = true;
}
//this=instance
//call은 인자가 1개씩, apply는 배열
Rating.prototype = new eg.Component();
Rating.prototype.constructor = Rating;
Rating.prototype.init = function() {
  // this.targetList.on("mouseenter",this.mouseenter.bind(undefined,event.target));
  this.targetList.on("mouseenter",function(event) {
      this.mouseenter(event.target);
    }.bind(this));
  this.targetList.on("mouseout",function(event) {
      this.mouseleave();
  }.bind(this));
  this.targetList.on("click",function(event) {
    this.click(event.target);
  }.bind(this));

};
Rating.prototype.mouseenter = function(target) {
  console.log(target);
  this.mouseEnteredTarget.score = target.value;
    this.targetList.each(function(index,target){
      if(this.mouseEnteredTarget.score < target.value) {
        $(target).removeClass(this.activeClassName);
      } else if(target.value == this.mouseEnteredTarget.score && target.value == this.clickedTarget.score) {
        $(target).removeClass(this.activeClassName);
        --this.mouseEnteredTarget.score;
      } else {
        $(target).addClass(this.activeClassName);
      }
    }.bind(this));
    this.trigger("change",this.mouseEnteredTarget);
}

Rating.prototype.mouseleave = function() {
  this.targetList.each(function(index,target){
    if(this.clickedTarget.score >= target.value) {
      $(target).addClass(this.activeClassName);
    } else {
      $(target).removeClass(this.activeClassName);
    }
  }.bind(this));
  this.trigger("change",this.clickedTarget);
}

Rating.prototype.click = function(target) {
  event.preventDefault();
  if($(target).hasClass(this.activeClassName)) {
      this.clickedTarget.score = target.value;
  } else  {//mouseenter 이벤트가없는 모바일에만해당
    this.clickedTarget.score = target.value -1;
  }
  this.trigger("change",this.clickedTarget);
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
