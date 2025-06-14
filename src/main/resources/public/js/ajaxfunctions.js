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
	
	// Beobachtungen Bearbeiten
	$("#editSelected").click(function() {
		const selected = $(".rowCheckbox:checked");
		if (selected.length !== 1) {
			alert("Bitte genau eine Beobachtung auswählen.");
			return;
		}
		
		const id = selected.val();
		
		$.get(`/observation/${id}`, function (data) {
			$("input[name=time]").val(data.time);
			$("input[name=date]").val(data.date);
			$("select[name=gender]").val(data.animal.gender);
			$("input[name=weight]").val(data.animal.weight);
			$("input[name=size]").val(data.animal.size);
			$("input[name=age]").val(data.animal.age);
			$("#genusSelect").val(data.animal.genus.id);
			$("#locationSelect").val(data.location.lNr);
			
			$("#observationForm").data("edit-id", id); //für PUT
		});	
	});
	
	// Beobachtungen löschen
	$("#deleteSelected").click(function() {
		const selectedIds = $(".rowCheckbox:checked").map(function () {
			return $(this).val();
		}).get();
		
		if (selectedIds.length === 0) {
			alert("Bitte mindestens eine Beobachtung auswählen.");
			return;
		}
		
		if (!confirm("${selectedIds.length} Beobachtung wirklich löschen?")) return;
	
		selectedIds.forEach(id => {
			$.ajax({
				url: `/observation/${id}`,
				type: "DELETE",
				success: loadObservationTable
			});
		});
	});
	
	$(document).on("click", "#selectAll", function () {
		$(".rowCheckbox").prop("checked", this.checked).trigger("change");
	});	
	
	$(document).on("change", "#.rowCheckbox", function () {
			$(this).closest("tr").toggleClass("selected-row", this.checked);
		});	
});

// Beobachtungen speichern und absenden (linke Seite)
//Eingabemaske (Yaren)
function postObservation(event) {
	event.preventDefault(); // verhindert normales Abschicken, man bleibt auf der Seite

	//ACHTUNG: Änderung Alexa
	const formData = {
		'time': $('input[name=time]').val(),
		'date': $('input[name=date]').val(),
		'animal':{
			'gender': $('select[name=gender]').val(),
			'weight': $('input[name=weight]').val(),
			'size': $('input[name=size]').val(),
			'age': $('input[name=age]').val(),
			'genus': {
				'id': $('#genusSelect').val()
			}
		},
		'location': {
			'lNr': $('#locationSelect').val()
		}
	};
	
	const id = $("#observationForm").data("edit-id");
	const method = id ? "PUT" : "POST";
	const url = id ? `/observation/${id}` : "/observation";
	
	
	$.ajax({
		type: method, 
		contentType: 'application/json',
		url: url,
		data: JSON.stringify(formData), // datawewanttoPOST
		success: function(data, textStatus, jQxhr) {
			$("#observationForm").removeData("edit-id");
			loadObservationTable();
		},
		error: function(jqXhr, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
	// stoptheform fromsubmittingthenormal wayandrefreshingthepage
}



//rechte Seite Beobachtungstabelle (Danny)

//ACHTUNG: Änderung Alexa 
function loadObservationTable() {	
	var table = $('#observationTable').DataTable({
		destroy: true,
		scrollX: true,
		"ajax": {
			"url": "/observation", //URL
			"dataSrc": ""// Causeofflat JsonObjects
		},
		"columns": [
			{
				"data": null,
				"render": function (data, type ,row) {
					return '<input type="checkbox" class="rowCheckbox" value="' + row.id + '">'
				},
				"orderable": false
			},
			{ "data": "id" },
			{ "data": "animal.genus.designation" },  
			{ "data": "location.city" },
			{ "data": "animal.size" },
			{ "data": "animal.weight" },
			{ "data": "animal.age" },
			{ "data": "animal.gender" },						
			{ "data": "date" },
			{ "data": "time" }
		]
	});
}

// Dropdown Gattung (Genus) laden
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
	const latinName = $('#newGenusLatinName').val();
	if (!name) return alert("Name der Gattung fehlt.");

	$.ajax({
		type: 'POST',
		url: '/genus',
		contentType: 'application/json',
		data: JSON.stringify({ designation: name, latinDesignation: latinName}),
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
			select.append('<option value="' + l.lNr + '">' + l.city + '</option>'); //ACHTUNG: Änderung Alexa (id zu lNR)
		});
	});
}


// Neuer Ort speichern
function saveNewLocation() {
	const name = $('#newLocationName').val();
	const description = $('#newLocationDescription').val();
	if (!name) return alert("Name des Ortes fehlt.");

	$.ajax({
		type: 'POST',
		url: '/locations',
		contentType: 'application/json',
		data: JSON.stringify({ city: name, description: description}),
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
	$('#newGenusLatinInput').toggle();
}

function toggleLocationInput() {
	$('#newLocationInput').toggle();
	$('#newLocationDescriptionInput').toggle();
}