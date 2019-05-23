const furniture = require('./furniture');
const customer = require('./customer');

module.exports = `
    type Query {
        ${furniture}
        ${customer}
    }
`;
