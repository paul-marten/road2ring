var indexTmps = 1;

function drawIterField(){
  var curIndex = indexTmps - 1;

  var formFields = $('<div>', {
    'id': 'day',
    'class': 'box-header container-day'
  });

  var formGroup = $('#itinerary-form')
  formFields.append("<br/>")

  formFields.append(drawIterEventLabel('picture-'+indexTmps,'Picture'))
  formFields.append(drawIterEventInput('picture-'+indexTmps,'file'))
  formFields.append(drawIterEventLabel('event-'+indexTmps,'Event'))
  formFields.append(drawIterEventInput('event-'+indexTmps,'text', 'itineraries['+curIndex+'].title'))
  formFields.append(drawIterEventLabel('description','Description'))
  formFields.append(drawIterTextArea('description', 'itineraries['+curIndex+'].description'))
//  formFields.append(drawInputHidden('itineraries['+curIndex+'].group', indexDay))
//  formFields.append(drawInputHidden('itineraries['+curIndex+'].groupTitle', 'day'))
  formFields.append(drawIterEventDeleteButton())

  formGroup.append(formFields)
}
$(document).on("click", "#add-event", function(event) {
    var numItems = $('.container-day').length
    console.log($(this).parent().attr('id'))
//    drawIterEventField($(this).parent().attr('id'));
    drawIterField();
    indexTmps += 1;
    $('.itinerary-btn').removeClass('collapse')
});

function drawAddButton(){
  var button= $('<button>', {
    'style': 'margin: 0px 15px; height: 30px;',
    'id': 'add-event',
    'type': 'button',
    'day-id': indexDay,
    'class': 'btn btn-default btn-sm m-bottom-sm btn-add-tab ',
  });

  var icon = $('<i>',{
    'class': 'fa fa-plus fa-lg',
  });

  return button.append(icon)
}


function drawIterEventLabel(label, text){
  var label = $('<label>',{
    'for': label,
  }).text(text);

  return label
}

function drawIterLabel(label, text){
  var label = $('<label>',{
    'class': 'col-lg-1',
  }).text(text);

  return label
}

function drawIterInput(label, type){
    var input = $('<input>', {
      'type': type,
      'class': 'form-control',
      'id': label,
      'name': 'groupTitle['+indexTmps+']'
    });

  var container = $('<div>', {
    'class': 'col-lg-6'
  });
//return input
  return container.append(input);
}

function drawIterEventInput(label, type){
 var input;
 if(type == 'file'){
    input = $('<input>', {
    'type': type,
    'class': 'form-control',
//    'name': 'itineraries['+indexDay+'].'
    'id': label,
  });
  }
  else{
    input = $('<input>', {
      'type': type,
      'class': 'form-control',
      'id': label,
      'name': name,
    });
  }

return input
}



function drawInputHidden(name, value){
  var inputHidden = $('<input>', {
        'type': 'hidden',
        'class': 'hidden',
        'name': name,
        'value': value,
      });

      return inputHidden;
}

function drawIterTextArea(label,name){
  var input = $('<textarea>', {
    'class': 'form-control',
    'id': label,
    'name': name,
    'placeholder': "Enter Description"
  });
return input
}

function drawIterDeleteButton(){
  var button = $('<button>', {
    'id': 'delete-form-itinerary',
    'style': 'margin: 2px 10px; height: 30px;',
    'type': 'button',
    'class': 'btn btn-default btn-sm m-bottom-xs btn-add-tab btn-del-margin',
  })

  var icon = $('<i>', {
    'class': 'fa fa-minus fa-lg',
  });

  return button.append(icon)
}

function drawIterEventDeleteButton(){
  var button = $('<button>', {
    'id': 'delete-form-event-itinerary',
    'style': 'margin-top: 10px; height: 30px;',
    'type': 'button',
    'class': 'btn btn-default btn-sm m-bottom-xs btn-add-tab btn-del-margin',
  })

  var icon = $('<i>', {
    'class': 'fa fa-minus fa-lg',
  });

  return button.append(icon)
}

$(document).on("click", "#delete-form-itinerary", function(event) {
   $(this).parent().remove()
   console.log($(this).parent())
   indexDay -= 1;
   if(indexDay == 1){
    $('.itinerary-btn').addClass('collapse')
   }
 })

$(document).on("click", "#delete-form-event-itinerary", function(event) {
   $(this).parent().remove()
   console.log($(this).parent())
   indexDay -= 1;
   if(indexDay == 1){
    $('.itinerary-btn').addClass('collapse')
   }
 })