const { ApolloServer } = require('apollo-server');
const typeDefs = require('./graphql-schema');
const resolvers = require('./resolvers');
const furnitureDB = require('./data-sources/furniture-db/db');
const orderDB = require('./data-sources/order-db/db');

const server = new ApolloServer({
    /* Add typeDefs */
    /* Add resolvers */
    /* Add database connections to context */
    typeDefs,
    resolvers,
    context: async () => ({
    	dbFur: await furnitureDb,
    	dbOrd: await orderDb
    })
});

server
    .listen()
    .then(({ url }) => console.log(`Listening on ${url}`));
