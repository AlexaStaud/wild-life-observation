//On Page load-registerlistenersandloadexistingvideosin datatable
$(document).ready(function() {
	loadObservationTable();
	//processtheform newVideo
	$("#observationForm").submit(function(event) {
		postObservation(event);
	});
	
	//Beobachtungen laden, wenn der Button geklickt wird
	$("#loadObservations").click(function() {
		loadObservationTable();
	});
});


//linke Seite Eingabemaske (Yaren)
function postObservation(event) {
	// get the form data
	var formData = {
		'time': $('input[name=time]').val(),
		'date': $('input[name=date]').val(),
		'gender': $('select[name=gender]').val(),
		'weight': $('input[name=weight]').val(),
		'size': $('input[name=size]').val(),
		'age': $('input[name=age]').val(),
		'genusId' : $('#locationSelect').val(),
	};
	// processtheform
	$.ajax({
		type: 'POST', // definethetype ofHTTP verbwewanttouse(POST forourform)
		contentType: 'application/json',
		url: '/observation', // urlwherewewanttoPOST
		data: JSON.stringify(formData), // datawewanttoPOST
		success: function(data, textStatus, jQxhr) {
			loadObservationTable();
		},
		error: function(jqXhr, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
	// stoptheform fromsubmittingthenormal wayandrefreshingthepage
	event.preventDefault();
}



//rechte Seite Beobachtungstabelle (Danny) 
function loadObservationTable() {
	var table = $('#observationTable').DataTable({
		destroy: true,
		"ajax": {
			"url": "/observation", //URL
			"dataSrc": ""// Causeofflat JsonObjects
		},
		"columns": [
			{ "data": "id" },
			{ "data": "animal.genus.designation" },  
			{ "data": "location" },
			{ "data": "animal.size" },
			{ "data": "animal.weight" },
			{ "data": "animal.age" },
			{ "data": "animal.gender" },						
			{ "data": "date" },
			{ "data": "time" }
		]
	});
}