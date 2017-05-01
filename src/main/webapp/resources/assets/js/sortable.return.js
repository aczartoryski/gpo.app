$('form').submit(function(){
    $('#fieldMappingString').val($( "#sortable2" ).sortable("toArray"));
});