$(document).ready(function() {
    
    
    // Hide deselect all checkbox button
    $('a.deselect-all').hide();

    // DataTable
    var table = $('#datatable1').DataTable();
    
    
    
    // Check all checkboxes in the table after click on select-all
    $('a.select-all').on( 'click', function (e) {
        $(":checkbox").attr('checked', true);
        $(this).hide();
        $('a.deselect-all').show();
    });

    // Uncheck all checkboxes in the table after click on select-all
    $('a.deselect-all').on( 'click', function (e) {
        $(":checkbox").attr('checked', false);
        $(this).hide();
        $('a.select-all').show();
    });

    $('#datetimepicker1.input').on('click', function (e) {
                $('#datetimepicker1').data("DateTimePicker").FUNCTION();
            });

    // Hide or show selected column in the table
    $('a.toggle-vis').on( 'click', function (e) {
        e.preventDefault();
 
        // Get the column API object
        var column = table.column( $(this).attr('data-column') );
        if ($(this).children('span').hasClass('badge-success')) {
            $(this).children('span').removeClass('badge-success').addClass('badge-danger');
            $(this).children('span').children('i').removeClass('fa-plus-square').addClass('fa-minus-square');
        } else {
            $(this).children('span').removeClass('badge-danger').addClass('badge-success');
            $(this).children('span').children('i').removeClass('fa-minus-square').addClass('fa-plus-square');
        }
        
        // Toggle the visibility
        column.visible( ! column.visible() );
        } );

    // Apply the search in the table columns
    table.columns().eq( 0 ).each( function ( colIdx ) {
        $( 'input', table.column( colIdx ).header() ).on( 'keyup change', function () {
            table
                .column( colIdx )
                .search( this.value )
                .draw();
        } );
        $('input', table.column(colIdx).header()).on('click', function(e) {
        e.stopPropagation();
        });        
    } );
} );