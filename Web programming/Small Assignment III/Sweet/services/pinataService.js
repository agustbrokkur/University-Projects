const candies = require("../data/data").candies;
const offers = require("../data/data").offers;
const pinatas = require("../data/data").pinatas;


const pinataService = () => {
  const getAllCandies = () => {
    return candies;
  };

  const createNewCandy = (candy) => {
    let highestId = 0;
    candies.forEach(c => {
      if(c.id > highestId) {
        highestId = c.id;
      }
    });

    candy.id = highestId + 1;
    candies.push(candy);
  };

  const getCandyById = (id) => {
    const candy = candies.filter(c => c.id == id);
    if(candy.length == 0) {
      return -1;
    }
    return candy[0];
  };

  const getAllOffers = () => {
    const betterOffers = offers;

    for(var i = 0; i < betterOffers.length; i++) {
      for(var j = 0; j < betterOffers[i].candies.length; j++) {
        candies.forEach(c => {
          if(betterOffers[i].candies[j] == c.id) {
            betterOffers[i].candies[j] = c;
          }
        });
      }
    }

    return betterOffers;
  };

  const getAllPinatas = () => {
    const noSurprise = pinatas;
    for(var i = 0; i < noSurprise.length; i++) {
      delete noSurprise[i]["surprise"];
    }
    return noSurprise;

  };

  const createNewPinata = (pinata) => {
    let highestId = 0;
    pinatas.forEach(c => {
      if(c.id > highestId) {
        highestId = c.id;
      }
    });

    pinata.id = highestId + 1;
    pinatas.push(pinata);
  };

  const getPinataById = (id) => {
    const pinata = pinatas.filter(p => p.id == id);
    if(pinata.length == 0) {
      return -1;
    }
    const selectedPinata = pinata[0];
    delete selectedPinata["surprise"];
    return selectedPinata;
  };

  // Þessi partur skilar alltaf undefined frá return og ég hef enga hugmynd
  // um afhverju það er að gerast.
  const hitPinata = (id) => {
    let result = -1;
    if(getPinataById(id) === -1) {
      return result;
    } else {
      for(var i = 0; i < pinatas.length; i++) {
        if(pinatas[i].id == id) {
          const currentElement = pinatas[i];

          if(currentElement.currentHits < currentElement.maximumHits - 1) {
            currentElement.currentHits++;
            result = currentElement.surprise;
          }
          else if (currentElement.currentHits === currentElement.maximumHits - 1) {
            currentElement.currentHits++;
            result = 0;
          }
          result = 1;
        }
      }
    }
    return result;
  };

  return {
    getAllCandies,
    createNewCandy,
    getCandyById,
    getAllOffers,
    getAllPinatas,
    createNewPinata,
    getPinataById,
    hitPinata
  };
};

module.exports = pinataService();
