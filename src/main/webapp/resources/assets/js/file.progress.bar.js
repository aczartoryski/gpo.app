(function() {
    
var bar = $('.bar');
var percent = $('.percent');
var status = $('#status');
   
$('form').ajaxForm({
    type: 'POST',

    beforeSend: function() {
        status.empty();
        var percentVal = '0%';
        bar.width(percentVal);
        percent.html(percentVal);
    },
    uploadProgress: function(event, position, total, percentComplete) {
        var percentVal = percentComplete + '%';
        bar.width(percentVal);
        percent.html(percentVal);
    },
    success: function() {
        var percentVal = '100%';
        bar.width(percentVal);
        percent.html(percentVal);
    }
}); 

})(); 