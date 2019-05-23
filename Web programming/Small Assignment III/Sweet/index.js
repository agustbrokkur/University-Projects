const express = require("express");
const bodyParser = require("body-parser");
const port = 3000;
const pinataService = require("./services/pinataService");

const app = express();

app.use(bodyParser.json());

app.get("/api/candies", (req, res) => {
  return res.json(pinataService.getAllCandies());
});

app.post("/api/candies", (req, res) => {
  const candy = req.body;
  pinataService.createNewCandy(candy);
  return res.status(201).send();
});

app.get("/api/candies/:id", (req, res) => {
  const id = req.params.id;
  const result = pinataService.getCandyById(id);
  if(result === -1) {
    return res.status(404).send("No such candy exists");
  }
  return res.json(result);
});

app.get("/api/offers", (req, res) => {
  return res.json(pinataService.getAllOffers());

});

app.get("/api/pinatas", (req, res) => {
  return res.json(pinataService.getAllPinatas());

});

app.post("/api/pinatas", (req, res) => {
  const pinata = req.body;
  pinataService.createNewPinata(pinata);
  return res.status(201).send();

});

app.get("/api/pinatas/:id", (req, res) => {
  const id = req.params.id;
  const result = pinataService.getPinataById(id);
  if(result === -1) {
    return res.status(404).send("No such pinata exists");
  }
  return res.json(result);

});

app.put("/api/pinatas/:id/hit", (req, res) => {
  const id = req.params.id;
  const result = pinataService.hitPinata(id);
  if(result == -1) {
    return res.status(404).send();
  } else if(result == 0) {
    return res.status(204).send();
  } else if (result == 1) {
    return res.status(423).send();
  }
  return res.status(200).send(result);

});
