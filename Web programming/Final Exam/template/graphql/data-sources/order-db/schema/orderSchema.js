/* Setup order schema */
const Schema = require('mongoose').Schema;

module.exports = new Schema({
	dateOfOrder: {type: Date, required: true, default: Date.now()},
	customerId: {type: Schema.Types.ObjectId, required: true},
	furnitureIds: {type: [Schema.Types.ObjectId], required: true},
	amount: {type: Number, required: true}
});