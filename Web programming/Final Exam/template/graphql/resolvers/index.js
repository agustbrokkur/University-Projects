const customScalarResolver = require('./customScalarResolver');
const { Furniture } = require('../data-sources/furniture-db/db');
const { Order, Customer } = require('../data-sources/order-db/db');

module.exports = {
    /* Implement resolvers */
    ...customScalarResolver,
    Query: {
    	allFurnitures: async () => {
    		return await Furniture.find({}, (err, furnature) => {
    			if(err) {
    				console.log("allFurnitures: ", err)
    			}
    		});
    	},
    	customer: async (parent, args) => {
    		return await Customer.findById(args.id, (err, customer) => {
    			if(err) {
    				console.log("customer: ", err);
    			}
    		});
    	}
    },
    Mutation: {
    	createOrder: async (parent, args) => {
    		const newOrder = {
    			
    		}
    		return Order.create(newOrder);
    	}
    }
}
