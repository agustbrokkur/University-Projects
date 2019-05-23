const isPalindrome = (str) => {
	var reverse = str.split("").reverse().join("").toLowerCase();
	if(reverse == str.toLowerCase()) {
		return true;
	}
	return false;
};

module.exports = { isPalindrome }; 