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
  
  $(".datatable").DataTable({
    "dom": '<"top"fl<"clear">>Brt<"bottom"ip<"clear">>',
    "buttons": [{
            extend: 'colvis',
            columns: ':gt(0)',
            text: 'Ukryj/pokaż kolumny',
            className: 'btn btn-success btn-xs'
        },
        {
            extend: 'colvisRestore',
            text: 'Pokaż wszystkie',
            className: 'btn btn-default btn-light btn-xs'
        }
        ],
    
    "iDisplayLength": 500,
    "lengthMenu": [ [500, 1000, 2000, -1], [500, 1000, 2000, "Wszystkie"] ],
    "columnDefs": [ { "orderable": false, "targets": 0 } ],
    "order": [[ 1, 'asc' ]],
    "oLanguage": {
        "sSearch": "",
        "sLengthMenu": "_MENU_",
        "sProcessing":     "Przetwarzanie...",
        "sInfo":           "Pozycje od _START_ do _END_ z _TOTAL_ łącznie",
        "sInfoEmpty":      "Pozycji 0 z 0 dostępnych",
        "sInfoFiltered":   "(filtrowanie spośród _MAX_ dostępnych pozycji)",
        "sInfoPostFix":    "",
        "sLoadingRecords": "Wczytywanie...",
        "sZeroRecords":    "Nie znaleziono pasujących pozycji",
        "sEmptyTable":     "Brak danych",
        "oPaginate": {
            "sFirst":      "Pierwsza",
            "sPrevious":   "Poprzednia",
            "sNext":       "Następna",
            "sLast":       "Ostatnia"
        },
	"oAria": {
		"sSortAscending": ": aktywuj, by posortować kolumnę rosnąco",
		"sSortDescending": ": aktywuj, by posortować kolumnę malejąco"
	}
    },
    "initComplete": function initComplete(settings, json) {
    $('div.dataTables_filter input').attr('placeholder', 'Szukaj...');
    // $(".dataTables_wrapper select").select2({
    //   minimumResultsForSearch: Infinity
    // });
    }
  });
  
    $("#deleteOrderItemsAction").click(function(){
       $("#indexform").attr("action", "deleteOrderItems");
       $("#indexform").submit();
    });
    
    