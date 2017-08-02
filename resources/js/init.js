define([],function(){
    var getData = function(url,handlingData) {
        return   $.ajax({
            url : url,
            dataType :'json',
            type :'GET'
        });
    };
    var postFileData = function(url,formData) {
        return   $.ajax({
            url : url,
            type :'POST',
            processData : false,
            contentType : false,
            data : formData
        });
    };

    return {
        getData : getData,
        postFileData : postFileData
    }

});

