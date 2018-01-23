$.datepicker.regional['es'] = {
	 currentText: 'Hoy',
	 monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
	 monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
	 dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
	 dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
	 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
	 weekHeader: 'Sm',
	 dateFormat: 'dd/mm/yy',
	 firstDay: 1,
	 isRTL: false,
	 showMonthAfterYear: false,
	 yearSuffix: ''
	 };
$.datepicker.setDefaults($.datepicker.regional['es']);

			 
$( function() {
    var dateFormat = "dd/mm/yy",
      arrival = $( "#arrival" )
        .datepicker({
          defaultDate: "+1d",
          changeMonth: true,
          numberOfMonths: 1
        })
        .on( "change", function() {
          departure.datepicker( "option", "minDate", getDate( this ) );
        }),
      departure = $( "#departure" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 1
      })
      .on( "change", function() {
        arrival.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
  } );

$( function() {
    var dateFormat = "dd/mm/yy",
      arrival = $( "#arrivalb" )
        .datepicker({
          defaultDate: "+1d",
          changeMonth: true,
          numberOfMonths: 1
        })
        .on( "change", function() {
          departure.datepicker( "option", "minDate", getDate( this ) );
        }),
      departure = $( "#departureb" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 1
      })
      .on( "change", function() {
        arrival.datepicker( "option", "maxDate", getDate( this ) );
      });
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
  } );