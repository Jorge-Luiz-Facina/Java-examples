
const user1 = {id :1, name: "Jorge", age:"18"};
const user2 = {id :2, name: "Luiz", age:"20"};
var users = [user1, user2];


const getUsers = async (req, res) => {
	log(req);
	res.status(200).set('Content-Type', 'application/json').json(users);
};

const getUser = async (req, res) => {
	log(req);
	res.status(200).set('Content-Type', 'application/json').json(user1);
};

const addUser = async (req, res) => {
	log(req);
	res.status(200).set('Content-Type', 'application/json').json(req.body);
};

const putUser = (req, res) => {
	log(req);
	res.status(200).set('Content-Type', 'application/json').json(req.body);
};

const patchUser = (req, res) => {
	log(req);
	res.status(200).set('Content-Type', 'application/json').json(req.body);
};

const deleteUser = async (req, res) => {
	log(req);
	res.status(200).set('Content-Type', 'application/json').json(user1);
};

const log =  (req) => {
	console.log("---params---");
	console.log(req.params);
	console.log("---query---");
	console.log(req.query);
	console.log("---body---");
	console.log(req.body);
	console.log("---header---");
	console.log(req.rawHeaders);
};


module.exports = {
	getUsers: getUsers,
	getUser: getUser,
	addUser: addUser,
	putUser: putUser,
	patchUser: patchUser,
	deleteUser: deleteUser,
};
