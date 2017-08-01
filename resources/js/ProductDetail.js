var FirstiInit = {

};

function ProductDetail(productId) {

  var getResult = function(result) {
    console.log(result);
  }

  var requestDetailProduct = function() {
    $.ajax({
      url:'/api/products/' +productId + 'prices',
      dataType:'json',
      type:'get',
      success:getResult
    });
  }

  return {

  }
}
