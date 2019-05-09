//$(document).on("click", "#tripSaveBtn", function(event){
////    setInputHidden()
//
//  var validate = $("#tripForm").parsley().validate();
//  var isTextAreaFill = validateTextArea($("#description"))
//
//  var uri = window.location.pathname.split('/');
//  console.log($("#tripForm"))
//  console.log('here')
//  if(validate === true && isTextAreaFill === true){
//    $.post( "/trip/save", $("#itineraryForm").serialize()).done(function(data) {
//      window.location.href = "/trip";
//    })
//  }
//})
//
////$('#tripForm').submit(function(event){
////    if(!this.checkValidity())
////    {
////        event.preventDefault();
////        $('#tripForm :input:visible[required="required"]').each(function()
////        {
////            if(this.validity.valid)
////            {
////                $(this).focus();
////                // break
////                return false;
////            }
////        });
////    }
////});
//
//function validateTextArea(field){
//  var value = field.next().find(".ck-editor__main").first().first().text();
//  if(value === ''){
//    field.next().find(".ck-editor__main").first().first().text('-')
//    return false
//  }
//  else
//    return true
//}
