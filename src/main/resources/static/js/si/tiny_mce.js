function multi_page_editor(){
  tinymce.init({
          selector: ".mce-editor",
          forced_root_block: false,
          content_style: "body {font-size:13px !important; font-family: Roboto, sans-serif !important; color: #555 !important}",
          invalid_elements: "span",
          plugins: "autoresize",
          height: 300,
          menubar: false,
          plugins: ["advlist", "code", "paste", "hr", "link", "media", "fullscreen", "preview", "image"],
          theme_advanced_buttons3_add: "pastetext,pasteword,selectall",
          toolbar: "undo redo | bold italic underline | alignleft aligncenter alignright alignjustify | removeformat | code | link image | hr |  bullist numlist | media | fullscreen preview | suggested-article block-quote",
          extended_valid_elements: "+iframe[width|height|name|align|class|frameborder|allowfullscreen|allow|src|*]," +
                  "script[language|type|async|src|charset]" +
                  "img[*]" +
                  "embed[width|height|name|flashvars|src|bgcolor|align|play|loop|quality|allowscriptaccess|type|pluginspage]" +
                  "blockquote[dir|style|cite|class|id|lang|onclick|ondblclick"
                  +"|onkeydown|onkeypress|onkeyup|onmousedown|onmousemove|onmouseout"
                  +"|onmouseover|onmouseup|title]",

          image_caption: true,
          file_picker_types: 'image',
          file_picker_callback: function (cb, value, meta) {
              var input = document.createElement('input');
              input.setAttribute('type', 'file');
              input.setAttribute('accept', 'image/*');

              input.onchange = function () {
                var file = this.files[0];
                var reader = new FileReader();
                reader.onload = function () {
                  var hostname = $(location).attr('protocol') + '//' + $(location).attr('host');
                  var api = hostname + "/si/api";
                  var formData = new FormData();
                  formData.append("file", file)
                  var json_data = new XMLHttpRequest();
                  var url = api + '/gallery/uploadContent';
                  var data;

                  json_data.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                          var response = JSON.parse(this.responseText);
                          data = response.object.original;
                          cb(data, { title: file.name });
                      }
                  };

                  json_data.open("POST", url, true);
                  json_data.send(formData);
                };
                reader.readAsDataURL(file);
              };

              input.click();
          },
          valid_elements : '+*[*]',

          media_live_embeds: true,
          paste_auto_cleanup_on_paste: true,
          paste_remove_styles: true,
          paste_remove_styles_if_webkit: true,
          paste_strip_class_attributes: true,
          setup: function (ed) {
              ed.addButton('inlineimage', {
                  title: 'Add Image',
                  icon: 'image',
              });

              ed.addButton('suggested-article', {
                  title: 'Baca Juga',
                  icon: 'anchor',
                  onclick: function (event) {
                      content = '[suggestedarticle]';
                      ed.focus();
                      ed.insertContent(content);
                  }
              });

              ed.addButton('block-quote', {
                title: 'Blockquoute',
                icon: 'blockquote',
                onclick: function (event) {
                  ed.windowManager.open({
                    title: 'Insert Quote',
                    body: [
                        {type: 'textbox', name: 'quote', label: 'Quote'},
                        {type: 'textbox', name: 'author', label: 'Author'}
                    ],
                    onsubmit: function(e) {
                        ed.focus();
                        ed.selection.setContent('<blockquote><p>' + e.data.quote + '</p><p> '+ e.data.author + '</p></blockquote>');
                    }
                  });
                }
              })

              ed.on('blur', function(e){
                var iOS = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
                if(!iOS){
                  var text = tinyMCE.activeEditor.getContent({format : 'html'});
                  $('#'+ed.id).val(text)
                  setToHidden()
                }
              });
              ed.on('mouseout', function(e){
                var iOS = /iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream;
                if(iOS){
                  var text = tinyMCE.activeEditor.getContent({format : 'html'});
                  $('#'+ed.id).val(text)
                  setToHidden()
                }
              });
          },
      });
}