//$('#type').change(function() {
//  if($(this).is(":checked")) {
//    $('.type-true').removeClass('collapse');
//    $('.type-false').addClass('collapse');
//  }else{
//    $('.type-false').removeClass('collapse');
//    $('.type-true').addClass('collapse');
//  }
//})
//var isError = false;
//function setToHiddenMediaUrl() {
//  var value;
//  if($('#type').is(":checked")){
//    value = $("#videoUrl").val();
//    isError = false
//    if(value == ''){
//      alert('harap isi video url')
//      isError = true
//    }
//  }else{
//    value = $("#hidden_image").val();
//    isError = false
//    if(value == ''){
//      alert('harap masukkan image')
//      isError = true
//    }
//  }
//
//  $('#hidden_mediaUrl').val(value)
//}

$("#submit-btn").click(function(){
  //submit here
//  setToHiddenMediaUrl()
  tinyMCE.triggerSave();

    $.post( "/headline/save/", $('#headlineForm').serialize()).done(function(data) {
          window.location.href = "/headline";
    })


})