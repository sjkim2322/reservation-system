//console.log($('input.hidden_input'));
var FileUpload = (function(){
  var fileList = $('input.hidden_input');
  var imgTest = $('.item_test');
  var formData;
  var filePromise;
  var handlebarsTemplate;
  var previewImagePosition = $('div.item_preview_thumbs > ul.lst_thumb');
  var currentLength = 0;
  // var reader = new FileReader();
  // reader.onload = function (e) {
  //     imgTest.attr('src', e.target.result);
  // }
  // reader.readAsDataURL(this.files[i]);
  var drawPreviewImage = function(result) {
    previewImagePosition.append(handlebarsTemplate(result));
    currentLength +=result.length;
  }
  var init = function() {
    handlebarsTemplate = HandlebarsModule.createTemplate($('#product-reviewImage-template'));
    fileList.on('change',function(){
      if(this.files.length + currentLength > 5 ) {
        alert("최대 5개의 파일을 업로드 할 수 있습니다.")
      } else {
        formData = new FormData();
        for(var i=0 ; i < this.files.length ; i++) {
            formData.append("file",this.files[i]);
        }
        filePromise = Init.postFileData('/files',formData);
        $.when(filePromise).done(drawPreviewImage);
      }
    });
    previewImagePosition.on("click","li > a.anchor",function(){
      console.log("!@#");
    });
    $('.bk_btn').on("click",function(){
      for(liTag in previewImagePosition.children) {
        console.log(liTag.data("id"));
      }
    });
  }
  return {
    init : init
  }
})();
