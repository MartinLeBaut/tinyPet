var topPetition = {
	list: [],
	onchange : m.redraw(),
	loadList: function() {
		return m.request({
			method: "GET",
			url: "https://projetcloud-232813.appspot.com/_ah/api/petApi/v1/entity"
		})
		.then(function(result) {
			topPetition.list = result.items;
	        m.redraw(true); // force
	        m.redraw();
	        })
	},
}

var topPetitionView = {
	oninit: topPetition.loadList,
	view: function() {
		return m("table", {"class":"table"},
			[
				m("thead", {"class": "thead-dark"},
				[
				m("tr",
					[
					m("th", {"scope":"col"}, 
						"Position"
						),
					m("th", {"scope":"col"}, 
						"Title"
						),
					m("th", {"scope":"col"}, 
						"Number of signatures"
						),
					m("th", {"scope":"col"}, 
						"Signature"
						)
					]
				)
				]),
				m("tbody", topPetition.list.map(function(item){
				return m("tr",
					[
					m("th", {"scope":"row"}, 
						topPetition.list.indexOf(item) + 1
						),
					m("td", 
						item.properties.petName
						),
					m("td", 
						item.properties.nbSignature
						),
					m("td", item.properties.signatories.includes(window.userFullName) ? m(btnSignedPet) : m(btnNotSignedPet, {onsubmit: function(e) { e.preventDefault(); window.log ? signTopPetition.sign(item.key.id) : alert("Veuillez vous connecter");}})),
					]
				)
			})
			)
			]
		)
	},	
}

var signTopPetition = {
	list: [],
	sign: function(id) {
		return m.request({
			method: "POST",
			url: "https://projetcloud-232813.appspot.com/_ah/api/petApi/v1/signPet/" + id + "/" + encodeURIComponent(window.userFullName) 
		})
		.then(function(result) {
			topPetition.loadList()
		})
	},
}

var topPetitionGlobal = {
	view: function() {
		return m("main", [
			m("h1", {class: "title"}, "TOP 100 petitions"),
			m("div", {id: "box"}, m(topPetitionView)),
			])
	}
}