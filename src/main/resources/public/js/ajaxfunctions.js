//On Page load-registerlistenersandloadexistingvideosin datatable
$(document).ready(function() {
	loadDataTable();
	//processtheform newVideo
	$("#newVideo").submit(function(event) {
		postVideo(event);
	});
	//Load Datatable
	$("#loadtable").click(function() {
		loadDataTable();
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

function loadDataTable() {
	var table = $('#videotable').DataTable({
		destroy: true,
		"ajax": {
			"url": "/videos", //URL
			"dataSrc": ""// Causeofflat JsonObjects
		},
		"columns": [
			{ "data": "title" },
			{ "data": "description" },
			{ "data": "ageRating" },
			{ "data": "genre" }
		]
	});
}