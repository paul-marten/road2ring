var indexTmp = 1;
$(document).on("click", "#add-form", function(event) {
    drawField(indexTmp);
    indexTmp += 1;
    $('.facility-btn').removeClass('collapse')
});

function drawField(index){
  var formField = $('<div>', {
    'id': 'tripFacility-'+indexTmp
  });

  var formGroup = $('#form-list')
  formField.append("<br/>")
  formField.append(drawLabel('Facility-'+indexTmp))
  formField.append(drawInput('Facility-'+indexTmp))
  formField.append(drawDeleteButton())

  formGroup.append(formField)
}

function drawLabel(label){
  var label = $('<label>',{
    'for': label,
    'class': 'col-lg-2'
  }).text(label);

  return label
}

function drawDeleteButton(){
  var button = $('<button>', {
    'id': 'delete-form',
    'style': 'margin: 2px 15px; height: 30px;',
    'type': 'button',
    'class': 'btn btn-default btn-sm m-bottom-xs btn-add-tab btn-del-margin',
  })

  var icon = $('<i>', {
    'class': 'fa fa-minus fa-lg',
  });

  return button.append(icon)
}

 function drawInput(label){
  var input = $('<input>', {
    'type': 'text',
    'class': 'form-control',
    'name': 'tripFacilities['+indexTmp-1+'].facilityName',
    'id': label,
    'required': true
  });
  var inputHidden = $('<input>', {
      'type': 'hidden',
      'class': 'hidden',
      'name': 'tripFacilities['+indexTmp-1+'].isIncluded',
      'value': false
    });

  var container = $('<div>', {
    'class': 'col-lg-8'
  });
  container.append(input)
  container.append(inputHidden)
  return container;
 }

 $(document).on("click", "#delete-form", function(event) {
   $(this).parent().remove()
   indexTmp -= 1;
   if(indexTmp == 1){
    $('.facility-btn').addClass('collapse')
   }
 })