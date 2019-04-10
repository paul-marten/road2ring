function upload_trip(url_upload, section,image_field, hidden_field,width,height,type) {
    $(image_field).html5Uploader({
        name: "file",
        postUrl: url_upload+"/"+type,
        onClientLoadStart: function(e, file) {
            var warp = '.wrap-'+section;
            $(hidden_field).empty();
            $('.wrap-img-'+section).empty();
            $('.wrap_'+section+' .pre_img img').attr('src', '');
            $('.wrap_'+section).addClass('collapse');
            $('.wrap_'+section+' .msg').removeAttr('style');
            $('.wrap_'+section+' .progress').removeAttr('style');
        },
        onClientLoad: function(e, file) {
            $(".wrap_"+section).removeClass('collapse');
        },
        onServerLoadStart: function(e, file) {
            $('.wrap_'+section+' .progress-bar').attr({
                'aria-valuenow': 0,
                'style': 'width : 0'
            });
        },
        onServerProgress: function(e, file) {
            if (e.lengthComputable) {
                var percentComplete = (e.loaded / e.total) * 80;
                $('.wrap_'+section+' .progress-bar').attr({
                    'aria-valuenow': percentComplete,
                    'style': 'width : ' + percentComplete + '%'
                });
            }
        },
        onServerLoad: function(e, file) {
            $('.wrap_'+section+' .progress-bar').attr({
                'aria-valuenow': 95,
                'style': 'width : 95%'
            });
        },
        onSuccess: function(m, i, responseText) {
            var json = $.parseJSON(responseText);
            $('.wrap_'+section+' .progress-bar').attr({
                'aria-valuenow': 100,
                'style': 'width : 100%'
            }).parent().delay(500).fadeOut('fast', function() {
                $('.wrap_'+section+' .msg').fadeIn('slow');
            });
            var hidden_val = '';

            if (responseText != "") {
                hidden_val = json.object;

                console.log($('.wrap_'+section+' .pre_img img'))
                console.log('/img/assets/'+json.object+'.jpg')

                $('.wrap_'+section+' .pre_img img').attr('src', '/img/assets/'+json.object+'.jpg');
            }

            $(hidden_field).val('/img/assets/' + hidden_val + '.jpg');

        }
    });
}