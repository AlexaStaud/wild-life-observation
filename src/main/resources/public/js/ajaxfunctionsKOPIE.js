//On Page load-registerlistenersandloadexistingvideosin datatable
$(document).ready(function() {
	loadDataTable();
	//processtheform newVideo
	$("#newVideo").submit(function(event) {
		postVideo(event);
	});
	//Beobachtungen laden, wenn der Button geklickt wird
	$("#loadObservations").click(function() {
		loadObservations();
	});
});

function postVideo(event) {
	// get the form data
	var formData = {
		'title': $('input[name=title]').val(),
		'description': $('textarea[name=description]').val(),
		'ageRating': $('input[name=agerating]').val(),
		'genre': $('input[name=genre]').val()
	};
	// processtheform
	$.ajax({
		type: 'POST', // definethetype ofHTTP verbwewanttouse(POST forourform)
		contentType: 'application/json',
		url: '/videos', // urlwherewewanttoPOST
		data: JSON.stringify(formData), // datawewanttoPOST
		success: function(data, textStatus, jQxhr) {
			loadDataTable();
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
			{ "data": "location.shorttitle" },
			{ "data": "animal.estimatedSize" },
			{ "data": "animal.estimatedWeight" },
			{ "data": "animal.estimatedAge" },
			{ "data": "animal.gender" },						
			{ "data": "date" },
			{ "data": "time" }
		]
	});
}