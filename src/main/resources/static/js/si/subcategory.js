var hostname = $(location).attr('protocol') + '//' + $(location).attr('host');
var api = hostname + "/api";
 $('#categoryId').autocomplete({
      autoFocus: true,
      selectFirst: true,
      source: function(request, response) {
        var arrdata = [];
        var json_data = $.getJSON(api + '/accessory_category/helper', {
            keyword: request.term
        });
        json_data.done(function(data) {
            //if (!$.isEmptyObject(data)) {
            $.each(data.object, function(key, value) {
            console.log(value)
                arrdata.push(value)
            });
            response($.map(arrdata, function (value, key) {
                return{
                    label: value.title,
                    value: value.id
            }
            }));
        });
    },
      select: function(event,ui){
        if(!ui.item){
          this.value = ''
          $("#hidden_motor").val("")
        }else{
          $(this).val(ui.item.label)
          $("#hidden_motor").val(ui.item.value)
        }
        return false
      },
      focus: function(event, ui){
        return false;
      },
      change: function (event, ui) {
          if (!ui.item) {
              this.value = '';
              $("#hidden_motor").val("")
          }
      },
    minLength: 1,
    delay: 100
  });