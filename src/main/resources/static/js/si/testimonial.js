var index = 1;
var curIndex = 0;
var contDel;

$(document).on("click", "#add-article", function(event) {

    var child = $(this).parent().children().length
    $(this).remove()
    clickAddTextArea()
    multi_page_editor();
    index++;
    curIndex++;
});

function clickAddTextArea(){
  var formGroup = $('.article-list')
  var contArticle = $('<div>', {
    'class' : 'container-article'
  })


  contArticle.append(drawTextArea)
  contArticle.append(drawButton())
  formGroup.append(contArticle)


}

function drawTextArea(){
  var input = $('<textarea>', {
      'class': 'form-control mce-editor ta_tmce',
      'placeholder': 'Enter Description',
    }).text('')

  return input
}

function drawButton(){
  var divBtn = $('<div>', {
    'class' : 'button-plus-minus',
    'style' : 'margin-top: 10px; padding-bottom: 10px;',
  })

  var buttonMin = $('<button>',{
    'id' : 'delete-body',
    'type' : 'button',
    'class' : 'btn btn-default btn-sm m-bottom-sm btn-del-body',
  });

  var iconMinus = $('<i>',{
    'class' : 'fa fa-minus fa-lg',
    'style' : 'color: #D81E36;',
  });

  var buttonPlus = $('<button>',{
    'id' : 'add-article',
    'style' : 'margin-right:3px;',
    'type' : 'button',
    'class' : 'btn btn-default btn-sm m-bottom-sm btn-add-body',
  });

  var iconPlus = $('<i>',{
    'class' : 'fa fa-plus fa-lg',
    'style' : 'color: #3DD091;',
  });

  var iconMin = buttonMin.append(iconMinus);
  buttonMin.append("&nbsp;");
  buttonMin.append('Delete Page');
  var iconPls = buttonPlus.append(iconPlus);
  buttonPlus.append("&nbsp;");
  buttonPlus.append('Add Page');
  divBtn.append(iconPls)
  divBtn.append(iconMin)

  return divBtn;
}

$(document).on("click", '#delete-body', function(event){
  $('#publishConfirm').popup('show');
  //assign the element to global variable for futher use
  contDel = $(this)
});

function goDelete(){
  var container = contDel.parent().parent();
  console.log(container)
  container.remove();
  addButtonWhenDelete();
  setToHidden();
}

$(document).on("click", '.delete-it', function() {
  $('#publishConfirm').popup('hide');
  goDelete();
});

$(document).on("click", '.custom-popup .dont-delete', function() {
  $('.custom-popup').popup('hide');
});

function setToHidden (){
  var hidden = document.querySelector('#hiddenBody');
  var txt = getAllEditorValue()
  hidden.value = txt;
}

function getAllEditorValue(){
var text = '';
var i = 0;
  $('.ta_tmce').each(function(){
    var title = '';
    if($(this).val().trim() != "" && $(this).val().trim() != null){
      if(i == 0)
        text += $(this).val();
      else{
//        title = $(this).parent().prev().prev().children('input').val();
//        if(title.length > 0)
//          text +='[pagebreak]'+ title +'[/pagebreak]'+$(this).val();
//        else
          text +='[pagebreak] [/pagebreak]'+$(this).val();
      }
    }
    i++;
  })
  return text;
}

function setPageBreak(){
  var text = $('.ta_tmce').val();
  var rgx = /\[pagebreak\](.+?)\[\/pagebreak\]/g;
  var pb = text.split(/\[pagebreak\].+?\[\/pagebreak\]/g)
  var listSubtitle = text.match(rgx);
  var sum = pb.length
  console.log(sum)
  var label = '';
  var subtitle = '';
  for(var i = 0; i < sum; i++ ){
    if(i > 0){
     clickAddTextArea()
    }
  }
  setAllTextArea(pb)
  reDrawButton();
  setToHidden();
}

//set textarea value from hidden field
function setAllTextArea(val){
  var i = 0;
  $('.ta_tmce').each(function(){
    $(this).text(val[i])
    i++;
  })
}

//re-draw the button after load
function reDrawButton(){
  var len = $('.button-plus-minus').length
  var i = 0;
  $('.button-plus-minus').each(function(){
    if(len == 1){
      $(this).parent().append(drawButton);
      $('#delete-body').remove()
      $(this).remove();
    }else{
      if(i==0){
        $(this).parent().append(drawButton);
        $(this).parent().find("button").remove()
        $(this).remove();
      }
      else{
        $(this).parent().append(drawButton);
        $('#add-article').remove()
        $(this).remove();
      }
      i++;
    }
  });
}

function addButtonWhenDelete(){
  var len = $('.button-plus-minus').length
  var i = 0;
  $('.button-plus-minus').each(function(){
    if(i == len-1 && i != 0){
      $(this).parent().append(drawButton);
      $(this).remove();
    }
    if(len == 1){
      $(this).parent().append(drawButton);
      $('#delete-body').remove()
      $(this).remove();
    }
    i++;
  })
}

$('#type').change(function() {
  if($(this).is(":checked")) {
    $('.type-true').removeClass('collapse');
    $('.type-false').addClass('collapse');
  }else{
    $('.type-false').removeClass('collapse');
    $('.type-true').addClass('collapse');
  }
})


var isError = false;
function setToHiddenMediaUrl() {
  var value;
  if($('#type').is(":checked")){
    value = $("#videoUrl").val();
    isError = false
    if(value == ''){
      alert('harap isi video url')
      isError = true;
    }
  }else{
    value = $("#hidden_cover_trip").val();
    isError = false
    if(value == ''){
      alert('harap masukkan image')
      isError = true;
    }
  }

  $('#hidden_mediaUrl').val(value)
}

$("#submit-btn-gallery").click(function(){
  //submit here
  setToHiddenMediaUrl()

  if(!isError){
    $.post( "/gallery/save", $('#headlineForm').serialize()).done(function(data) {
  //      if(data.length != 0){
          window.location.href = "/gallery";
  //      }else{
  //        if(previous != 'Select'){
  //          $('#group').val(previous)
  //        }
  //      }
      })
    }

})

$("#submit-btn-testi").click(function(){
  //submit here
  setToHiddenMediaUrl()

  if(!isError){
    $.post( "/testimonial/save", $('#headlineForm').serialize()).done(function(data) {
  //      if(data.length != 0){
          window.location.href = "/testimonial";
  //      }else{
  //        if(previous != 'Select'){
  //          $('#group').val(previous)
  //        }
  //      }
      })
   }

})