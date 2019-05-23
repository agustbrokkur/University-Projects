/* Setup customer schema */
const Schema = require('mongoose').Schema;

module.exports = new Schema({
	name: {type: String, required: true},
	email: {type: String, required: true}
});