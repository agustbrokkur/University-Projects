module.exports = `
    type Customer {
    	id: ID!
    	name: String!
    	email: String!
    	orders: [Order!]!
    }
`;
