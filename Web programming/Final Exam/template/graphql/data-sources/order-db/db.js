const mongoose = require('mongoose');
const orderSchema = require('./schema/orderSchema');
const customerSchema = require('./schema/customerSchema');

const connection = mongoose.createConnection('insert-your-connection-string-here', {
    useNewUrlParser: true
});

module.exports = {
    Order: connection.model('Order', orderSchema),
    Customer: connection.model('Customer', customerSchema)
};
