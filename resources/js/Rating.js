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
  });
  instance.targetList.on("mouseleave",function() {
    instance.targetList.each(function(){
      if(instance.clickedTarget.score >= this.value) {
        $(this).addClass(instance.activeClassName);
      } else {
        $(this).removeClass(instance.activeClassName);
      }
    });
    instance.trigger("change",instance.clickedTarget);
  });
  instance.targetList.on("click",function(event) {
    event.preventDefault();
    if($(this).hasClass(instance.activeClassName)) {
      instance.clickedTarget.score = this.value;
    } else {
      instance.clickedTarget.score = this.value -1;
    }
    instance.trigger("change",instance.clickedTarget);
  });

};
