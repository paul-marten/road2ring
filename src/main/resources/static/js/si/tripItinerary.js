var indexTmps = 1;

function drawIterField(){
  var curIndex = indexTmps;

  var formFields = $('<div>', {
    'class': 'itinerary-event'
  });

  var formGroup = $('#itinerary-form')
  formFields.append("<br/>")

  formFields.append(drawIterEventLabel('picture-'+indexTmps,'Picture'))
  formFields.append(drawIterEventInput('picture-'+indexTmps,'file'))
  formFields.append(drawIterEventLabel('event-'+indexTmps,'Event'))
  formFields.append(drawIterEventInput('event-'+indexTmps,'text', 'itineraries['+curIndex+'].title'))
  formFields.append(drawIterEventLabel('description-'+indexTmps,'Description'))
  formFields.append(drawIterTextArea('description', 'itineraries['+curIndex+'].description'))
  formFields.append(drawInputHidden('itineraries['+curIndex+'].group', 'group'))
  formFields.append(drawInputHidden('itineraries['+curIndex+'].groupTitle', 'hidden'))
//  formFields.append(drawInputHidden('itineraries['+curIndex+'].groupTitles', 'hidden'))
//  formFields.append(drawInputHidden('itineraries['+curIndex+'].title', $('#title').val()))
  formFields.append(drawIterEventDeleteButton())

  formGroup.append(formFields)
}
$(document).on("click", "#add-event", function(event) {
    drawIterField();
    indexTmps += 1;
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

function drawIterEventInput(label, type, name){
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



function drawInputHidden(name, className){
  var inputHidden = $('<input>', {
        'type': 'hidden',
        'class': className,
        'name': name,
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

$(document).on("click", "#delete-form-event-itinerary", function(event) {
   $(this).parent().remove()
   console.log($(this).parent())
   indexTmps -= 1;
 })

 $(document).on("click", "#itinerarySaveBtn", function(event){
    setInputHidden()
    console.log($('#itineraryForm').serialize())
    var uri = window.location.pathname.split('/');
    console.log(uri[2])
//    $("#itinerarySaveBtn").submit();
    $.post( "/trip/"+uri[2]+ '/itinerary/save', $("#itineraryForm").serialize()).done(function(data) {
//      location.reload("/trip/"+uri[2]+ '/itinerary");
      window.location.href = "/trip/"+uri[2]+ '/itinerary';
    })
 })

 function setInputHidden(){
   $('.hidden').each(function(index){
      $(this).val($('#title').val())
   })

   $('.group').each(function(index){
      $(this).val($('#group').val())
   })
 }