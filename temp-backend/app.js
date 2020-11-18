var fs = require("fs");
var https = require("https");
var express = require("express");
var bp = require("body-parser");

var port = 9090;

var app = express();
app.use(function(req, res, next) {
	if ("content-type" in req.headers)
		req.headers["content-type"] = "application/json";
	next();
});
app.use(bp.json());

app.use((err, req, res, next) => {
	//if (err instanceof SyntaxError && err.status === 400 && "body" in err) {
	//	console.error(err);
	//	return res.sendStatus(400); // Bad request
	//}
	next();
});

app.use(function(req, res, next) {
	var currentDateTime = new Date().toUTCString();
	console.info(req.method + " " + req.originalUrl + " - " + currentDateTime);
	next();
});

app.use(function(req, res, next) {
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, " + 
		"Content-Type, Accept, X-API-Key");
	res.header("Access-Control-Allow-Credentials", "true");
	res.header("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, " +
		"POST, PUT, DELETE");
	var secpolicy = "default-src 'self'; child-src 'none'; object-src 'none'; " +
		"style-src 'self' 'unsafe-inline'; font-src data:; frame-src 'self'; " +
		"connect-src 'self' https://localhost:" + port +
		"; script-src 'self' 'unsafe-inline' 'unsafe-eval';";
	res.header("Content-Security-Policy", secpolicy);
	res.header("X-Content-Security-Policy", secpolicy);
	res.header("Frame-Options", "SAMEORIGIN");
	res.header("X-Frame-Options", "SAMEORIGIN");
	res.header("X-Content-Type-Options", "nosniff");
	res.header("Strict-Transport-Security", "max-age=15768000");
	next();
});

var server = https.createServer({
	key: fs.readFileSync("certs/server.key"),
	cert: fs.readFileSync("certs/server.crt")
}, app).listen(port, function() {
	console.log("Server started (" + port + ")...");
});

function prepareShutdown() {
	server.close(function() {
		console.log("Shutting down...");
		process.exit(0);
	});

	setTimeout(function() {
		console.log("Force shutting down...");
		process.exit(1);
	}, 100);
}

process.on("SIGINT", prepareShutdown);
process.on("SIGTERM", prepareShutdown);

//app.use((req, res, next) => setTimeout(next, 800));

// ----------------------------------------------

var teams = [
	{
		"name": "Zathin",
		"type": "T",
		"parentTeamId": null
	},
	{
		"name": "Rank",
		"type": "O",
		"parentTeamId": 0
	},
	{
		"name": "Tresom",
		"type": "T",
		"parentTeamId": null
	},
	{
		"name": "Tres-Zap",
		"type": "O",
		"parentTeamId": 2
	},
	{
		"name": "Hatity",
		"type": "O",
		"parentTeamId": 3
	},
	{
		"name": "Toughjoyfax",
		"type": "O",
		"parentTeamId": 4
	},
	{
		"name": "Ventosanzap",
		"type": "O",
		"parentTeamId": 3
	},
	{
		"name": "Keylex",
		"type": "T",
		"parentTeamId": null
	},
	{
		"name": "Vagram",
		"type": "O",
		"parentTeamId": 4
	},
	{
		"name": "Daltfresh",
		"type": "O",
		"parentTeamId": 1
	}
];

for (let i = 0; i < teams.length; i++) {
	teams[i] = {...{"id": i+1}, ...teams[i]};
	if (teams[i]["parentTeamId"] !== null)
		teams[i]["parentTeamId"]++;
}

var user = {
	"firstName": "Anderea",
	"middleName": null,
	"lastName": "Carson"
};

function isString(obj) {
	return obj !== undefined && obj !== null &&
		(typeof obj === "string" || obj instanceof String);
}

function isValidExistingTeamId(id) {
	if (id === null)
		return true;

	if (isNaN(id))
		return false;

	for (let i = 0; i < teams.length; i++) {
		if (teams[i] !== null && teams[i].id === id)
			return true;
	}

	return false;
}

function getTeamById(id, reference=false) {
	for (let i = 0; i < teams.length; i++) {
		if (teams[i] !== null && teams[i].id === id) {
			if (reference)
				return teams[id];
			let result = {...teams[i]};
			delete result.id;
			return result;
		}
	}
	return null;
}

function getNewTeamId() {
	for (let i = (teams.length-1); i >= 0; i--) {
		if (teams[i] !== null)
			return teams[i].id+1;
	}
	return 1;
}

// ----------------------------------------------

app.get("/team", (req, res, next) => {
	//res.json(teams.filter(x => x !== null).map(({id, ...x}) => x));
	res.json(teams.filter(team => team !== null));
});

app.get("/team/:id", (req, res, next) => {
	let id = parseInt(req.params.id);
	if (!isValidExistingTeamId(id)) {
		next();
		return;
	}
	res.json(getTeamById(id));
});

app.post("/team", (req, res, next) => {
	if (!("name" in req.body && isString(req.body.name)) ||
		!("type" in req.body && (req.body.type === "T" ||
			req.body.type === "O")) ||
		("parentTeamId" in req.body &&
			!isValidExistingTeamId(req.body.parentTeamId)))
	{
		next();
		return;
	}

	if (!("parentTeamId" in req.body))
		req.body.parentTeamId = null;

	let newId = getNewTeamId();
	let toPush = {
		"id": newId,
		"name": req.body.name,
		"type": req.body.type,
		"parentTeamId": req.body.parentTeamId
	};
	teams.push(toPush);
	res.setHeader("Location", "/team/" + newId);
	res.status(201).json(getTeamById(newId));
});

app.put("/team/:id", (req, res, next) => {
	let id = parseInt(req.params.id);
	if (!isValidExistingTeamId(id)) {
		//next();
		res.status(404).json({
			error: "STATUS_NOTFOUND"
		});
		return;
	}

	let teamRef = getTeamById(id, true);
	if ("name" in req.body) {
		if (!isString(req.body.name)) {
			next();
			return;
		} else {
			teamRef.name = req.body.name;
		}
	}

	if ("type" in req.body) {
		if (!(req.body.type === "T" ||
			req.body.type === "O"))
		{
			next();
			return;
		} else {
			teamRef.type = req.body.type;
		}
	}

	if ("parentTeamId" in req.body) {
		if (req.body.parentTeamId !== null &&
			(!isValidExistingTeamId(req.body.parentTeamId) ||
				id === req.body.parentTeamId))
		{
			next();
			return;
		} else {
			teamRef.parentTeamId = req.body.parentTeamId;
		}
	}

	res.setHeader("Location", "/team/" + id);
	res.status(201).json(getTeamById(id));
});

app.delete("/team/:id", (req, res, next) => {
	//let id = parseInt(req.params.id);
	//teams.splice(id, 1);
	//res.json({status: 0});

	let id = parseInt(req.params.id);
	if (!isValidExistingTeamId(id)) {
		next();
		return;
	}
	//teams[id] = null;
	let toRemove = [id];
	for (let cur of toRemove) {
		for (let i = 0; i < teams.length; i++) {
			//console.log(i, cur);
			if (teams[i] === null)
				continue;

			if (teams[i].id === cur) {
				teams[i] = null;
			} else if (teams[i].parentTeamId === cur) {
				toRemove.push(teams[i].id);
				teams[i] = null;
			}
		}
	}
	res.sendStatus(204);
});

var user = {
	"firstName": "Anderea",
	"middleName": null,
	"lastName": "Carson"
};

app.get("/user", (req, res, next) => {
	res.json([{...{id: 1}, ...user}]);
});

app.get("/user/:id", (req, res, next) => {
	let id = parseInt(req.params.id);
	if (id !== 1) {
		next();
		return;
	}

	res.json(user);
});

app.post("/user", (req, res, next) => {
	//user.firstName = req.body.firstName;
	//user.middleName = req.body.middleName;
	//user.lastName = req.body.lastName;
	//res.json({status: 0});

	if (!("firstName" in req.body && isString(req.body.firstName)) ||
		("middleName" in req.body && req.body.middleName !== null &&
			!isString(req.body.middleName)) ||
		!("lastName" in req.body && isString(req.body.lastName)))
	{
		next();
		return;
	}

	if (!("middleName" in req.body))
		req.body.middleName = null;

	user.firstName = req.body.firstName;
	user.middleName = req.body.middleName;
	user.lastName = req.body.lastName;
	res.setHeader("Location", "/user/" + 1);
	res.status(201).json(user);
});

// ----------------------------------------------

app.get("*", (req, res, next) => {
	res.status(404).json({
		error: "STATUS_NOTFOUND"
	});
});

app.post("*", (req, res, next) => {
	res.status(400).json({
		error: "STATUS_BADREQUEST"
	});
});

app.put("*", (req, res, next) => {
	res.status(400).json({
		error: "STATUS_BADREQUEST"
	});
});

app.delete("*", (req, res, next) => {
	res.status(404).json({
		error: "STATUS_NOTFOUND"
	});
});
