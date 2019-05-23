const Schema = require('mongoose').Schema;

module.exports = new Schema({
    description: { type: String, required: true },
    price: { type: Number, validate: value => value > 0, required: true },
    material: { type: String, validate: value => value in [ 'wood', 'metal', 'plastic' ], default: 'wood' },
    color: { type: String, default: 'white' }
});
