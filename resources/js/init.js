var Init = (function(){

  var requestData = function(url,handlingData) {
    return   $.ajax({
                url : url,
                dataType :'json',
                type :'GET'
              });
  }

  return {
    getData : requestData
  }
})();
