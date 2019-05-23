const EventEmitter = require("events");
const StringHelper = require("./stringHelpers");

class MyEmitter extends EventEmitter {
	constructor() {
		super();
	}

	checkPalindrome(str) {
		if(StringHelper.isPalindrome(str)) {
			this.emit("palindrome");
		} else {
			this.emit("non-palindrome");
		}
	} 
}

module.exports = MyEmitter;