var FirstiInit = {

};

function ProductDetail(productId) {

  var getResult = function(result) {
    console.log(result);
  }

  var requestDetailProduct = function() {
    $.ajax({
      url:'/product/detail"/prices/'+productId,
      dataType:'json',
      type:'get',
      success:getResult
    });
  }

  return {

  }
}
