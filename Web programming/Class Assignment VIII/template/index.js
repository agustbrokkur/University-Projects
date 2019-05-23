const { Area, Shark, Attack, connection } = require('./data/db');
const { TIGER_SHARK, HAMMERHEAD_SHARK, GREAT_WHITE_SHARK, BULL_SHARK } = require('./constants');

// 1.1. Get all sharks
Shark.find({}, (err, sharks) => {
  if(err) { throw new Error(err); }
  console.log(sharks);
  console.log("\n");
});

// 1.2. Get all tiger sharks
Shark.find( { species: TIGER_SHARK }, (err, sharks) => {
  if(err) { throw new Error(err); }
  console.log(sharks);
  console.log("\n");
});

// 1.3. Get all tiger and bull sharks
Shark.find( { $or: [{ species: TIGER_SHARK }, { species: BULL_SHARK }] }, (err, sharks) => {
  if(err) { throw new Error(err); }
  console.log(sharks);
  console.log("\n");
});

// 1.4. Get all sharks except great white sharks
Shark.find( { species: { $ne: GREAT_WHITE_SHARK } }, (err, sharks) => {
  if(err) { throw new Error(err); }
  console.log(sharks);
  console.log("\n");
});

// 1.5. Get all sharks that have been known to attack
Attack.find( {}, (err, attacks) => {
  if(err) { throw new Error(err); }
  Shark.where("id").in("attacks.sharkId").exec(function (err, sharks) {
    if(err) { throw new Error(err); }


    console.log("Attack Sharks: \n");
    console.log(sharks);
    console.log("\n");
    connection.close();
  });
});

// 1.6. Get all areas with registered attacks

// 1.7. Get all areas with more than 5 registered attacks

// 1.8. Get the area with the most registered shark attacks

// 1.9. Get the total count of great white shark attacks

// 1.10. Get the total count of hammerhead and tiger shark attacks
