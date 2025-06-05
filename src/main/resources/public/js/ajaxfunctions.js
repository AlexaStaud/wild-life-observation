$(document).ready(function() {
	// Tabelle mit Beobachtungen laden
	loadObservationTable();
	
	// Gattung und Ort für Dropdown laden
		loadGenusOptions();
		loadLocationOptions();
	
	// Neue Beobachtung senden
	$("#observationForm").submit(function(event) {
		postObservation(event);
	});
	
	// Tabelle manuell laden per Button
	$("#loadObservations").click(function() {
		loadObservationTable();
	});
});

// Beobachtungen speichern und absenden (linke Seite)
//Eingabemaske (Yaren)
function postObservation(event) {
	event.preventDefault(); // verhindert normales Abschicken, man bleibt auf der Seite


	const formData = {
		'time': $('input[name=time]').val(),
		'date': $('input[name=date]').val(),
		'gender': $('select[name=gender]').val(),
		'weight': $('input[name=weight]').val(),
		'size': $('input[name=size]').val(),
		'age': $('input[name=age]').val(),
		'genusId' : $('#genusSelect').val(),
		'locationId': $('#locationSelect').val(),
	};
	// processtheform
	$.ajax({
		type: 'POST', 
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
}



//rechte Seite Beobachtungstabelle (Danny) 
function loadObservationTable() {
	var table = $('#observationTable').DataTable({
		destroy: true,
		scrollX: true,
		"ajax": {
			"url": "/observation", //URL
			"dataSrc": ""// Causeofflat JsonObjects
		},
		"columns": [
			{ "data": "id" },
			{ "data": "genus" },  
			{ "data": "location" },
			{ "data": "animal.size" },
			{ "data": "animal.weight" },
			{ "data": "animal.age" },
			{ "data": "animal.gender" },						
			{ "data": "date" },
			{ "data": "time" }
		]
	});
}// Dropdown Gattung (Genus) laden
function loadGenusOptions() {
	$.get('/genus', function(data) {
		const select = $('#genusSelect');
		select.empty().append('<option value="">--Bitte auswählen--</option>');
		data.forEach(function(g) {
			select.append('<option value="' + g.id + '">' + g.designation + '</option>');
		});
	});
}


// Neue Gattung speichern
function saveNewGenus() {
	const name = $('#newGenusName').val();
	if (!name) return alert("Name der Gattung fehlt.");

	$.ajax({
		type: 'POST',
		url: '/genus',
		contentType: 'application/json',
		data: JSON.stringify({ designation: name, latinDesignation: "Unbekannt" }),
		success: function() {
			alert("Neue Gattung gespeichert.");
			$('#newGenusInput').hide();
			$('#newGenusName').val('');
			loadGenusOptions();
		},
		error: function() {
			alert("Fehler beim Speichern der Gattung.");
		}
	});
}


// Dropdown Ort (Location) laden
function loadLocationOptions() {
	$.get('/locations', function(data) {
		const select = $('#locationSelect');
		select.empty().append('<option value="">--Bitte auswählen--</option>');
		data.forEach(function(l) {
			select.append('<option value="' + l.id + '">' + l.city + '</option>');
		});
	});
}


// Neuer Ort speichern
function saveNewLocation() {
	const name = $('#newLocationName').val();
	if (!name) return alert("Name des Ortes fehlt.");

	$.ajax({
		type: 'POST',
		url: '/locations',
		contentType: 'application/json',
		data: JSON.stringify({ city: name }),
		success: function() {
			alert("Neuer Ort gespeichert.");
			$('#newLocationInput').hide();
			$('#newLocationName').val('');
			loadLocationOptions();
		},
		error: function() {
			alert("Fehler beim Speichern des Ortes.");
		}
	});
}


// Toggle-Funktionen
function toggleGenusInput() {
	$('#newGenusInput').toggle();
}

function toggleLocationInput() {
	$('#newLocationInput').toggle();
}