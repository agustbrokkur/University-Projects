const express = require("express");
const myEmitter = require("./MyEmitter");
const port = 3000;

const app = express();

app.get("/api/ispalindrome/:palindrome", (req, res) => {
	const emitter = new myEmitter();

	emitter.on("palindrome", () =>{
		return res.status(200).send("This was a palindrome!");
	});
	emitter.on("non-palindrome", () =>{
		return res.status(400).send("This was not a palindrome!");
	});

	emitter.checkPalindrome(req.params.palindrome);
});

app.listen(port, () => {
	console.log("Server is listening on port " + port);
});