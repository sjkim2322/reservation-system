//console.log($('input.hidden_input'));
var ReviewRating = (function() {
  var rt = new Rating($("div.rating"),"rating_rdo","checked");
  var starRank = $('span.star_rank');
  rt.on("change",function(data) {
    if(data.score === 0) {
      starRank.addClass('gray_star');
      starRank.text(data.score);
    } else {
      starRank.removeClass('gray_star');
      starRank.text(data.score);
    }
  })

})();

var ReviewWrite = (function() {
  var writeInfo = $('.review_write_info');
  var textArea = $('.review_textarea');
  writeInfo.on('click',function(){
    writeInfo.hide();
    textArea.focus();
  });

  textArea.on('focusout',function() {
    if(textArea.val().length === 0) {
      writeInfo.show();
    }
  });

  textArea.on('keydown',function() {
    $('div.guide_review > span:first').text(this.value.length);
    if(this.value.length === 10) {
      alert("최대 400자만 입력할 수 있습니다.")
      return false;
    }

  });
})();




var FileUpload = (function(){
  var fileList = $('input.hidden_input');
  var imgTest = $('.item_test');
  var previewImagePosition = $('div.item_preview_thumbs > ul.lst_thumb');
  var currentLength = 0;
  var finalFileList;
  var formData;
  var filePromise;
  var handlebarsTemplate;

  var drawPreviewImage = function(result,status) {
    console.log(status);
    previewImagePosition.append(handlebarsTemplate(result));
    currentLength +=result.length;
  }
  var redirectPage = function(result) {
      if(result.status === 401) {
        //머지후에 로그인 페이지로 이동
        location.href ="/";
      }
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
        $.when(filePromise).done(drawPreviewImage).fail(redirectPage);
      }
    });

    previewImagePosition.on("click","li > a.anchor",function(){
      $(this).parent().remove();
    });

    $('.bk_btn').on("click",function(){
      finalFileList = new Array();
      previewImagePosition.children().each(function(){
        finalFileList.push($(this).data('id'));
      });
      console.log(finalFileList);
    });
  }

  return {
    init : init
  }
})();
FileUpload.init();
// var reader = new FileReader();
// reader.onload = function (e) {
//     imgTest.attr('src', e.target.result);
// }
// reader.readAsDataURL(this.files[i]);
