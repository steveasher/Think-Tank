// NOTE: Reusable, agnostic of any view

var createService = function(maxDelay) {
    var messages = [];
    
    var randomUnder = function(max) {
		return Math.floor(Math.random() * max);	
	}
	
	var theCall = function(message, callback) {
	    messages.push(message);
	    
	    setTimeout(callback(messages), randomUnder(maxDelay));
	}
	
	return {
	    call: theCall
	};
};