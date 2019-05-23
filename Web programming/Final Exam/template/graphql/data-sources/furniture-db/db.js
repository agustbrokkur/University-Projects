const mongoose = require('mongoose');
const furnitureSchema = require('./schema/furnitureSchema');

const connection = mongoose.createConnection('mongodb://furniflow_dbuser:Abc.12345@ds159293.mlab.com:59293/furniflow', {
    useNewUrlParser: true
});

module.exports = {
    Furniture: connection.model('Furniture', furnitureSchema)
};
