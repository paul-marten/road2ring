var index = 1;
var curIndex = 0;
var contDel;

$(document).on("click", "#add-article", function(event) {

    var child = $(this).parent().children().length
    $(this).remove()
    clickAddTextArea('')
    multi_page_editor();
    index++;
    curIndex++;
});

function clickAddTextArea(subtitle){
  var formGroup = $('.article-list')
  var contArticle = $('<div>', {
    'class' : 'container-article form-group'
  })

  contArticle.append(drawInputSubtitle(subtitle))
  contArticle.append(drawTextArea)
  contArticle.append(drawButton())
  formGroup.append(contArticle)


}

function drawInputSubtitle(value){
  var field = $('<input>', {
    'type': 'text',
    'class': 'form-control subtitle',
    'placeholder': 'Enter Sub Title',
    'data-parsley-required': 'true',
    'value': value
  })

  return field;
}

function drawTextArea(){
  var input = $('<textarea>', {
      'class': 'form-control mce-editor ta_tmce',
      'placeholder': 'Enter Description',
      'data-parsley-required': 'true',
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
      title = '<div class="text-center mb-4"><span class="h2 title-section title-section__with-border">'
              +$(this).parent().children('input').val() + '</span></div>';

      text += '<div class="pt-4 mt-4">' + title +'<div class="body-desc">' + $(this).val() + '</div></div>';
    }
    i++;
  })
  return text;
}

function setPageBreak(){
  var text = $('.ta_tmce').val();

  var rgx = /\<div class=\"pt-4 mt-4\">/g;
  var rgxSub = /\<span(.+?)\>(.+?)\<\/span\>/g;
  var rgxBody = /\<div class="body-desc"\>(.+?)\<\/div\>/g;
  var body = text.match(rgxBody)
  var listSubtitle = text.match(rgx);
  var sum = listSubtitle.length
  var subtitle = text.match(rgxSub)

  var splitSub = ''
  for(var i = 0; i < sum; i++ ){
    splitSub = subtitle[i].match('<span class="h2 title-section title-section__with-border">(.*)</span>')
    console.log(splitSub[1])
    if(i > 0){
     clickAddTextArea(splitSub[1])
    }else{
     $(".subtitle").val(splitSub[1])
    }
  }
  setAllTextArea(body)
  reDrawButton();
  setToHidden();
}

//set textarea value from hidden field
function setAllTextArea(val){
  var i = 0;
  var value = '';
  $('.ta_tmce').each(function(){
    value = val[i].match('<div class="body-desc">(.*)</div>');
    $(this).text(value[1])
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
$("#submit-btn-gallery").click(function(){
  //submit here
  tinyMCE.triggerSave();
  setToHidden()


    $.post( "/gallery/save", $('#headlineForm').serialize()).done(function(data) {
  //      if(data.length != 0){
          window.location.href = "/gallery";
  //      }else{
  //        if(previous != 'Select'){
  //          $('#group').val(previous)
  //        }
  //      }
      })

})

$("#submit-btn-testi").click(function(){
  //submit here
  tinyMCE.triggerSave();
  setToHidden()


    $.post( "/testimonial/save", $('#headlineForm').serialize()).done(function(data) {
  //      if(data.length != 0){
          window.location.href = "/testimonial";
  //      }else{
  //        if(previous != 'Select'){
  //          $('#group').val(previous)
  //        }
  //      }
      })

})