const input = require('./input');
const mutations = require('./mutations');
const queries = require('./queries');
const scalar = require('./scalar');
const types = require('./types');

module.exports = `
    ${input}
    ${mutations}
    ${queries}
    ${scalar}
    ${types}
`;
