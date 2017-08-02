define(['node_modules/handlebars/dist/amd/handlebars'],function(Handlebars){
    var position;
    var source;
    var template;

    var createTemplate = function(handlebarsId) {
        position=handlebarsId;
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
        create : function(handlebarsId,data,r_postion) {
            var template = cache[handlebarsId.attr("id")];
            if(!template){
                cache[handlebarsId.attr("id")] = createTemplate(handlebarsId);
                template = cache[handlebarsId.attr("id")];
            }
            if(r_postion === undefined) {
                handlebarsId.after(template(data));
            }else {
                r_postion.after(template(data));
            }
        },
        customHelper : function(helperName, func) {
            Handlebars.registerHelper(helperName,func);
        },
        setPartial: function(partialName,partialHandlebarsId){
            var partialSource = partialHandlebarsId.html();
            Handlebars.registerPartial(partialName, partialSource);
        },
        createTemplate:createTemplate
    }

});



