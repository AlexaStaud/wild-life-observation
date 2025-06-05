//On Page load-registerlistenersandloadexistingvideosin datatable
$(document).ready(function() {
	loadDataTable();
	//processtheform newVideo
	$("#newObservation").submit(function(event) {
		postObservation(event);
	});
	
	//Beobachtungen laden, wenn der Button geklickt wird
	$("#loadObservations").click(function() {
		loadObservations();
	});
});


//linke Seite Eingabemaske (Yaren)
function postObservation(event) {
	// get the form data
	var formData = {
		'time': $('input[name=title]').val(),
		'date': $('textarea[name=description]').val(),
		'ageRating': $('input[name=agerating]').val(),
		'genre': $('input[name=genre]').val()
	};
	// processtheform
	$.ajax({
		type: 'POST', // definethetype ofHTTP verbwewanttouse(POST forourform)
		contentType: 'application/json',
		url: '/observation', // urlwherewewanttoPOST
		data: JSON.stringify(formData), // datawewanttoPOST
		success: function(data, textStatus, jQxhr) {
			loadObservations();
		},
		error: function(jqXhr, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
	// stoptheform fromsubmittingthenormal wayandrefreshingthepage
	event.preventDefault();
}



//rechte Seite Beobachtungstabelle (Danny) 
function loadObservations() {
	var table = $('#observationTable').DataTable({
		destroy: true,
		"ajax": {
			"url": "/observations", //URL
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