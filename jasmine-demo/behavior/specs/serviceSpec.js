describe("The service", function() {
	var service;

  	it("should be created", function() {
	    service = createService(0);
	    expect(service).toBeDefined();
  	});
  	
  	describe("After creation, it", function() {
  	    var maxDelay = 3000;
  	    beforeEach(function() {
            service = createService(maxDelay);
      	});
      	
      	it("should call my callback", function() {
            var called = false;
            var myMessage = "x";
            var messages;
            
            runs(function() {
                service.call(myMessage, function(fromService) { 
                    called = true;
                    messages = fromService;
                });
            });
            
            waitsFor(function(){ return called === true }, "Never returned", maxDelay + 1);
            
            runs(function() {
                expect(called).toBe(true);
                expect(messages).toContain(myMessage);
            });
      	});
      	
      	it("should accumulate my messages", function() {
      	    var myMessages = ["m1", "m2"];
      	    var doNothing = function(){};
      	    
      	    var captureOutput = function(messages) { 
      	        expect(messages).toContain(myMessages[0]);
      	        expect(messages).toContain(myMessages[1]);
      	    };
      	    
      	    service.call(myMessages[0], doNothing);
      	    service.call(myMessages[1], captureOutput);
      	});
  	});
});