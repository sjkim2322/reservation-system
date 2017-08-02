define(['node_modules/@egjs/component/dist/component'],function(egComponent){
  function Popup(){

    };

    Popup.prototype = new egComponent();
    Popup.prototype.constructor = Popup;
    Popup.prototype.showPopup = function(){

        this.trigger("clickPopup");
    };
  return Popup;
});
