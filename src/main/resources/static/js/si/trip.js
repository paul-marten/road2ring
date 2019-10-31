
$(document).on('click', '.btn-embed', function() {
    /* Act on the event */
//    var hide_id = $(this).parent().find('input').val();
//    console.log($(this).parent());
    $('#publishConfirm').popup('show');
//    $('#publishConfirm input[name=api_id]').val(hide_id);
    return false;
});

$(document).on("click", '#publishConfirm .do-it', function() {
  var link = $('.embed-input').val();
  var iframe = new RegExp('<iframe(.+)</iframe>');

  if(iframe.test(link)){
    var pattern = /src="([^"]+)"/
    var match = pattern.exec(link);
    var url = match[1];
    console.log(url)
    $('#embed-map').next().removeClass("collapse")
    $('#embed-map').next().attr("src",url)
    $('#hiddenMapLink').val(url);
  }

  $('#publishConfirm').popup('hide');
});

$(document).on('click', '#publishConfirm .cancel', function(event) {
    /* Act on the event */
    $('#publishConfirm').popup('hide');
});

function setVenueGMaps(){
  var link = $('#hiddenMapLink').val()
  if(link != null && link != ''){
    $('#embed-map').next().removeClass("collapse")
    $('#embed-map').next().attr("src",link)
  }
}