//use when delete, to accomodate the deleted field
var contDel;

//method to set all text area value to hidden field
function setToHidden (){
  var hidden = document.querySelector("#hiddenBody");
  var txt = getAllEditorValue()
  hidden.value = txt;
}

//add text area when button add clicked
$(document).on("click", "#add-body", function(event){
  var label;
  var subtitle;

  var listBody = $('.list-body');
  var formBody = drawBodyForm('');
  listBody.append(formBody)
  multi_page_editor();
  changeAllLabel();

  var child = $(this).parent().children().length
  $(this).remove()
});

//draw the text area with this function
function drawBodyForm(title){
  var btn = drawButton();

  var sublabel = $('<label>',{
    'class': 'col-lg-2 control-label',
  }).text("Pagebreak Title");

  var subDiv = $('<div>', {
    'class' : 'col-lg-10 text-field-col',
    'style' : 'margin-bottom:10px;',
  });

  var subInput = $('<input>', {
    'class' : 'form-control sub-input',
    'type' : 'text',
    'maxlength' : '30',
  }).val(title);

  var wrapper = $('<div>', {
    'class' : 'multi-page',
    'style' : 'margin-bottom:40px;',
  });

  var formGroup = $('<div>', {
    'class' : 'form-group',
    'style' : 'position:relative',
  });

  var label = $('<label>', {
    'class': 'col-lg-2 control-label body-label',
    })

  var span = $('<span>',{
    'class' : 'font-sm text-danger',
  });

  var icon = $('<i>',{
    'class' : 'fa fa-asterisk fa-sm',
  });

  var textArea = $('<textarea>', {
    'class' : 'form-control mce-editor ta_tmce',
    'rows' : '4',
    'data-parsley-required': true,
  });

  var divField = $('<div>', {
      'class' : 'col-lg-10 text-field-col',
  });



  var spann = span.append(icon)
  var labell = label.append(span)
  var formss = divField.append(textArea)
  divField.append(btn)

  var divInput = subDiv.append(subInput);
  formGroup.append(sublabel)
  formGroup.append(divInput)

  var wrapForm = formGroup.append(labell);
  var warpform2 = formGroup.append(formss)
  var warpperDiv = wrapper.append(warpform2)

  return warpperDiv;
}

//draw button with this method
function drawButton(){
  var divBtn = $('<div>', {
    'class' : 'button-plus-minus',
    'style' : 'position:absolute; bottom: -45px;',
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
    'id' : 'add-body',
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

//to get all the editor value before set to hidden field
function getAllEditorValue(){
var text = '';
var i = 0;
  $('.ta_tmce').each(function(){
    var title = '';
    if($(this).val().trim() != "" && $(this).val().trim() != null){
      if(i == 0)
        text += $(this).val();
      else{
        title = $(this).parent().prev().prev().children('input').val();
        if(title.length > 0)
          text +='[pagebreak]'+ title +'[/pagebreak]'+$(this).val();
        else
          text +='[pagebreak] [/pagebreak]'+$(this).val();
      }
    }
    i++;
  })
  return text;
}

//delete button to show pop up for delete
$(document).on("click", '#delete-body', function(event){
  $('#publishConfirm').popup('show');
  //assign the element to global variable for futher use
  contDel = $(this)
});

//cancel delete
$(document).on("click", '.custom-popup .dont-delete', function() {
  $('.custom-popup').popup('hide');
});

//do delete
function goDelete(){
  var container = contDel.parent().parent().parent().parent();
  container.remove();
  addButtonWhenDelete();
  changeAllLabel();
  setToHidden();
}
//delete confirm
$(document).on("click", '.delete-it', function() {
  $('#publishConfirm').popup('hide');
  goDelete();
});

//change all the label name
function changeAllLabel(){
  var availLabel = $('.body-label').length;
  var label;

  var iterasi = 1;
  $('.body-label').each(function(){
     var span = $('<span>',{
        'class' : 'font-sm text-danger',
      });

      var icon = $('<i>',{
        'class' : 'fa fa-asterisk fa-sm',
      });
      var spann = span.append(icon)

    label = $(this).text("Body-page-"+iterasi+" ");
    label.append(spann);
    iterasi++
  });
}

//draw textarea by page break count
function setPageBreak(){
  var text = $('.ta_tmce').val();
  var rgx = /\[pagebreak\](.+?)\[\/pagebreak\]/g;
  var pb = text.split(/\[pagebreak\].+?\[\/pagebreak\]/g)
  var listSubtitle = text.match(rgx);
  var sum = pb.length
  var label = '';
  var subtitle = '';
  for(var i = 0; i < sum; i++ ){
    if(i > 0){
      var splitPagebreak = listSubtitle[i-1].split('[pagebreak]');
      title = splitPagebreak[1].split('[/pagebreak]');
      $('.list-body').append(drawBodyForm(title[0]));
      changeAllLabel()
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
        $('#add-body').remove()
        $(this).remove();
      }
      i++;
    }
  });
}

//do submit form
$(document).on("click", ".sbmt-btn", function(event){
  setToHidden();
  $('#contentForm').submit();
});

//manipulate button after deleting
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
