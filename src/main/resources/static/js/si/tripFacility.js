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
  var labelIndex = indexTmp +1;

  var formGroup = $('#form-list')
  formField.append("<br/>")
  formField.append(drawLabel('Facility'))
  formField.append(drawInput('Facility-'+labelIndex))
  formField.append(drawDeleteButton())

  formGroup.append(formField)
}

function drawLabel(label){
  var label = $('<label>',{
    'for': label,
//    'class': 'col-lg-2'
  }).text(label);

  return label
}

function drawDeleteButton(){
  var button = $('<button>', {
    'id': 'delete-form',
    'style': 'margin: 2px 0px; height: 30px;',
    'type': 'button',
    'class': 'btn btn-default btn-sm m-bottom-xs btn-add-tab btn-del-margin',
  })

  var icon = $('<i>', {
    'class': 'fa fa-minus fa-lg',
  });

  return button.append(icon)
}

 function drawInput(label){
  var indexName = indexTmp;
  var input = $('<input>', {
    'type': 'text',
    'class': 'form-control',
    'name': 'tripFacilities['+indexName+'].facilityName',
    'id': label,
    'required': true,
    'placeholder': 'Facility Name',
  });
  var inputHidden = $('<input>', {
      'type': 'hidden',
      'class': 'hidden',
      'name': 'tripFacilities['+indexName+'].isIncluded',
      'value': false
    });

  var container = $('<div>', {
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