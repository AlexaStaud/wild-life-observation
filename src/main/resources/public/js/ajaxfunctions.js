$(document).ready(function() {
	

	//Tabs
	  $(".tab-button").click(function () {
	    const tab = $(this).data("tab");

	    //Tabs wechseln
	    $(".tab-button").removeClass("active");
	    $(".tab-content").removeClass("active");
	    $(this).addClass("active");
	    $("#" + tab).addClass("active");

	    //Beim Umschalten die jeweilige Tabelle laden
	    if (tab === "locations") {
	      loadLocationTable();
	    } else if (tab === "genus") {
	      loadGenusTable();
	    } else if (tab === "observations") {
	      loadObservationTable();
	    }
	  });
	
	loadObservationTable();
	loadGenusOptions();
	loadLocationOptions();
	
	//Absenden (Neu anlegen oder bearbeiten)
	$("#observationForm").submit(function(event) {
		postObservation(event);
	});
	
	//Beobachtungen durch Button neu laden
	$("#loadObservations").click(function() {
		loadObservationTable();
	});
	
	//Beobachtung bearbeiten
	$("#editSelected").click(function () {
		updateSelected();
	});
	
	//Beobachtung öschen
	$("#deleteSelected").click(function () {
		deleteSelected();
	});
	
	//Alle Felder auswählen, je nach aktiver Tabelle
	$(document).on("click", "#selectAllObservations", function () {
		$(".rowCheckbox").prop("checked", this.checked);
	});
	$(document).on("click", "#selectAllLocations", function () {
		$(".locationCheckbox").prop("checked", this.checked);
	});
	$(document).on("click", "#selectAllGenus", function () {
		$(".genusCheckbox").prop("checked", this.checked);
	});

});

//Beobachtungen speichern
function postObservation(event) {
	event.preventDefault();

	//Daten für die Beobachtung
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
	
	//Zwischen neu anlegen und Bearbeiten wählen
	const id = $("#observationForm").data("edit-id");
	const method = id ? "PUT" : "POST";
	const url = id ? `/observation/${id}` : "/observation";
	
	
	$.ajax({
		type: method, 
		contentType: 'application/json',
		url: url,
		data: JSON.stringify(formData), 
		success: function(data, textStatus, jQxhr) {	//Nach Erfolg Bearbeitungsmodus verlassen
			$("#observationForm").removeData("edit-id");
			$("#observationForm")[0].reset(); 
			loadObservationTable();
		},
		error: function(jqXhr, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
}


//Bearbeiten 
function updateSelected() {
	const activeTab = $(".tab-content.active").attr("id");

	let selectedClass, getUrl, openEditUI, fillFormFields, storeEditId;

	//Abfrage welche Tabelle gerade offen ist
	if (activeTab === "observations") {
		selectedClass = ".rowCheckbox:checked";
		getUrl = id => `/observation/${id}`;
		openEditUI = () => {}; // nichts zu öffnen
		fillFormFields = data => { //Daten abrufen und links ins Formular eintragen 
			$("input[name=time]").val(data.time);
			$("input[name=date]").val(data.date);
			$("select[name=gender]").val(data.animal.gender);
			$("input[name=weight]").val(data.animal.weight);
			$("input[name=size]").val(data.animal.size);
			$("input[name=age]").val(data.animal.age);
			$("#genusSelect").val(data.animal.genus.id);
			$("#locationSelect").val(data.location.lNr);
		};
		storeEditId = id => $("#observationForm").data("edit-id", id);	//damit PUT und nicht POST beim speichern

	} else if (activeTab === "locations") {
		selectedClass = ".locationCheckbox:checked";
		getUrl = id => `/locations/${id}`;
		openEditUI = () => $("#newLocationInput").show();
		fillFormFields = data => {
			$("#newLocationName").val(data.city);
			$("#newLocationDescription").val(data.description);
		};
		storeEditId = id => $("#newLocationInput").data("edit-id", id);

	} else if (activeTab === "genus") {
		selectedClass = ".genusCheckbox:checked";
		getUrl = id => `/genus/${id}`;
		openEditUI = () => $("#newGenusInput").show();
		fillFormFields = data => {
			$("#newGenusName").val(data.designation);
			$("#newGenusLatinName").val(data.latinDesignation);
		};
		storeEditId = id => $("#newGenusInput").data("edit-id", id);

	} else {
		alert("Unbekannter Tab – kann nicht bearbeiten.");
		return;
	}

	//Abfrage damit nur eine Beobachtung/Gattung/Ort gleichzeitg bearbeitet werden kann
	const selected = $(selectedClass);
	if (selected.length !== 1) {	
		alert("Bitte genau einen Eintrag auswählen.");
		return;
	}

	const id = selected.val();
	$.get(getUrl(id), function (data) {
		openEditUI();
		fillFormFields(data);
		storeEditId(id);
	});
}


//Löschen
function deleteSelected() {
	const activeTab = $(".tab-content.active").attr("id");

	let selectedClass, deleteUrl, reloadFunction, itemName;

	if (activeTab === "observations") {
		selectedClass = ".rowCheckbox:checked";
		deleteUrl = "/observation/";
		reloadFunction = loadObservationTable;
		reloadOptions = [loadLocationOptions, loadGenusOptions]; 
		itemName = "Beobachtung(en)";
	} else if (activeTab === "locations") {
		selectedClass = ".locationCheckbox:checked";
		deleteUrl = "/locations/";
		reloadFunction = loadLocationTable;
		reloadOptions = loadLocationOptions;
		itemName = "Ort(e)";
	} else if (activeTab === "genus") {
		selectedClass = ".genusCheckbox:checked";
		deleteUrl = "/genus/";
		reloadFunction = loadGenusTable;
		reloadOptions = loadGenusOptions;
		itemName = "Gattung(en)";
	} else {
		alert("Unbekannter Tab – kann nicht löschen.");
		return;
	}

	const selectedIds = $(selectedClass).map(function () {	//Alle IDs der angeklickten Felder abrufen
		return $(this).val();
	}).get();

	if (selectedIds.length === 0) {	//Abfrage, damti mindestens eine Beobachtung/Gattung/Ort gelöscht wird
		alert("Bitte mindestens ein Element auswählen.");
		return;
	}

	if (!confirm(`${selectedIds.length} ${itemName} wirklich löschen?`)) return; //Kontrollabfrage

	selectedIds.forEach(id => {
		$.ajax({
			url: deleteUrl + id,
			type: "DELETE",
			success: [reloadFunction, reloadOptions],
			error: function (jqXhr, textStatus, errorThrown) {
				if (jqXhr.status === 500) {
					alert("Dieses Element ist noch mit Beobachtungen verknüpft und kann nicht gelöscht werden.");
				} else {
					alert("Fehler beim Löschen: " + errorThrown);
				}
			}
		});
	});
}

//Observation Tabelle laden
function loadObservationTable() {	
	var table = $('#observationTable').DataTable({
		destroy: true,
		scrollX: true,
		"ajax": {
			"url": "/observation", 
			"dataSrc": ""
		},
		"columns": [
			{	//Checkboxen in jede Zeile einfügen
				"data": null,
				"render": function (data, type ,row) {
					return '<input type="checkbox" class="rowCheckbox" value="' + row.id + '">'
				},
				"orderable": false
			},
			{ "data": "id" },
			{ "data": "animal.genus.designation" },  
			{ "data": "location.city" },
			{ "data": "animal.size" },
			{ "data": "animal.weight" },
			{ "data": "animal.age" },
			{ "data": "animal.gender" },						
			{ "data": "date" },
			{ "data": "time" }
		]
	});
}

//Location Tabelle laden
function loadLocationTable() {	
	var table = $('#locationTable').DataTable({
		destroy: true,
		scrollX: true,
		"ajax": {
			"url": "/locations",
			"dataSrc": ""
		},
		"columns": [
			{
				"data": null,
				"render": function (data, type ,row) {
					return '<input type="checkbox" class="locationCheckbox" value="' + row.lNr + '">'
				},
				"orderable": false
			},
			{ "data": "lNr" },
			{ "data": "city" },  
			{ "data": "description" }
		]
	});
}

//Genus Tabelle laden
function loadGenusTable() {	
	var table = $('#genusTable').DataTable({
		destroy: true,
		scrollX: true,
		"ajax": {
			"url": "/genus", 
			"dataSrc": ""
		},
		"columns": [
			{
				"data": null,
				"render": function (data, type ,row) {
					return '<input type="checkbox" class="genusCheckbox" value="' + row.id + '">'
				},
				"orderable": false
			},
			{ "data": "id" },
			{ "data": "designation" },  
			{ "data": "latinDesignation" }
		]
	});
}

//Gattung ins Dropdown einfügen
function loadGenusOptions() {
	$.get('/genus', function(data) {
		const select = $('#genusSelect');
		select.empty().append('<option value="">--Bitte auswählen--</option>');
		data.forEach(function(g) {
			select.append('<option value="' + g.id + '">' + g.designation + '</option>');
		});
	});
}


//Neue Gattung speichern
function saveNewGenus() {
	const id = $("#newGenusInput").data("edit-id");
	const name = $('#newGenusName').val();
	const latinName = $('#newGenusLatinName').val();
	if (!name) return alert("Name der Gattung fehlt.");
	
	const method = id ? 'PUT' : 'POST';
	const url = id ? `/genus/${id}` : '/genus';

	$.ajax({
		type: method,
		url: url,
		contentType: 'application/json',
		data: JSON.stringify({ designation: name, latinDesignation: latinName}),
		success: function() {
			alert(id ? "Gattung aktualisiert." : "Neue Gattung gespeichert.");
			$('#newGenusInput').hide();
			$('#newGenusName').val('');
			$('#newGenusLatinName').val('');
			$('#newGenusInput').removeData("edit-id");
			loadGenusOptions();
			loadGenusTable();
		},
		error: function() {
			alert("Fehler beim Speichern der Gattung.");
		}
	});
}


//Ort ins Dropdown einfügen
function loadLocationOptions() {
	$.get('/locations', function(data) {
		const select = $('#locationSelect');
		select.empty().append('<option value="">--Bitte auswählen--</option>');
		data.forEach(function(l) {
			select.append('<option value="' + l.lNr + '">' + l.lNr + ' - ' + l.city + '</option>'); 
		});
	});
}


//Neuen Ort speichern
function saveNewLocation() {
	const id = $("#newLocationInput").data("edit-id");
	const name = $('#newLocationName').val();
	const description = $('#newLocationDescription').val();
	if (!name) return alert("Name des Ortes fehlt.");

	const method = id ? 'PUT' : 'POST';
	const url = id ? `/locations/${id}` : '/locations';
	
	$.ajax({
		type: method,
		url: url,
		contentType: 'application/json',
		data: JSON.stringify({ city: name, description: description}),
		success: function() {
			alert(id ? "Ort aktualisiert." : "Neuer Ort gespeichert.");
			$('#newLocationInput').hide();
			$('#newLocationName').val('');
			$('#newLocationDescription').val('');
			$('#newLocationInput').removeData("edit-id");
			loadLocationOptions();
			loadLocationTable();
		},
		error: function() {
			alert("Fehler beim Speichern des Ortes.");
		}
	});
}


//Eingabemaske für neue Gattung anzeigen/verstecken
function toggleGenusInput() {
	$('#newGenusInput').toggle();
	$('#newGenusLatinInput').toggle();
}

//Eingabemaske für neuen Ort anzeigen/verstecken
function toggleLocationInput() {
	$('#newLocationInput').toggle();
	$('#newLocationDescriptionInput').toggle();
}