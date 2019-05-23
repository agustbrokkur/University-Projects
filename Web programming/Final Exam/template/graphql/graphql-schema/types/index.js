const customer = require('./customer');
const furniture = require('./furniture');
const order = require('./order');

module.exports = `
    ${customer}
    ${furniture}
    ${order}
`;
