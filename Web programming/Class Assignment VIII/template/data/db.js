const mongoose = require('mongoose');
const sharkSchema = require('../schemas/sharkSchema');
const attackSchema = require('../schemas/attackSchema');
const areaSchema = require('../schemas/areaSchema');

const connection = mongoose.createConnection('mongodb://agustha16:password123@ds125723.mlab.com:25723/spy_on_shark', {
    useNewUrlParser: true
});

module.exports = {
    Shark: connection.model('Shark', sharkSchema),
    Attack: connection.model('Attack', attackSchema),
    Area: connection.model('Area', areaSchema),
    connection
};
