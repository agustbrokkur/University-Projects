const order = require('./order');

module.exports = `
    type Mutation {
        ${order}
    }
`;
