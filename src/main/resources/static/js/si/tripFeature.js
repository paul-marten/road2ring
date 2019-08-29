function checkImageIsReady(count){
  var totalEmpty = 0;

  var picList = $(".pic-input");

  for(var i = 0; i<picList.length; i++){
    var pic = picList[i];
    if(pic.value == ''){
      totalEmpty++;
    }
  }

  return totalEmpty === 0;
}

$("#submit-btn").click(function(){

  $('#tripFeatureForm').parsley().validate()
  if(checkImageIsReady(2) && $('#tripFeatureForm').parsley().isValid()){
    $.post( "/trip-feature/save/", $('#tripFeatureForm').serialize()).done(function(data) {
          window.location.href = "/trip-feature";
    })
  }else{
    alert("Semua gambar harap diisi")
  }
})