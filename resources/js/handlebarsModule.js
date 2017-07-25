//핸들바 모듈
var HandlebarsModule = (function() {
  var position;
  var source;
  var template;

  var createTemplate = function(r_position) {
    position=r_position;
    source = position.html();
    template = Handlebars.compile(source);
    return template;
  };
  var addToPosition= function(data) {
    position.after(template(data));
  };
  var cache = {

  };
  return {
    create : function(r_position,data) {
      var template = cache[r_position.attr("id")];
      if(!template){
        cache[r_position.attr("id")] = createTemplate(r_position);
        template = cache[r_position.attr("id")];
      }
      r_position.after(template(data));
    },
    customHelper : function(helperName, func) {
      Handlebars.registerHelper(helperName,func);
    }
  }
})();
