const { GraphQLScalarType } = require('graphql');

module.exports = {
    Date: new GraphQLScalarType({
        name: 'Date',
        description: 'A valid date time value.',
        parseValue: value => new Date(value),
        serialize: value => new Date(value).toUTCString(),
        parseLiteral: ast => new Date(ast.value)
    })
};
