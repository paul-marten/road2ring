$('#type').change(function() {
  if($(this).is(":checked")) {
    $('.type-true').removeClass('collapse');
    $('.type-false').addClass('collapse');
  }else{
    $('.type-false').removeClass('collapse');
    $('.type-true').addClass('collapse');
  }
})

function setToHiddenMediaUrl() {
  var value;
  if($('#type').is(":checked")){
    value = $("#videoUrl").val();
    if(value == ''){
      alert('harap isi video url')
    }
  }else{
    value = $("#hidden_image").val();
    if(value == ''){
      alert('harap masukkan image')
    }
  }

  $('#hidden_mediaUrl').val(value)
}

$("#submit-btn").click(function(){
  //submit here
  setToHiddenMediaUrl()

  $.post( "/headline/save/", $('#headlineForm').serialize()).done(function(data) {
//      if(data.length != 0){
        window.location.href = "/headline";
//      }else{
//        if(previous != 'Select'){
//          $('#group').val(previous)
//        }
//      }
    })

})