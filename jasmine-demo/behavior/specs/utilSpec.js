describe("Utilities", function() {
    
    describe("Listify", function() {
        
        beforeEach(function() {
            $('#theList').remove();
            $('body').append('<ul id="theList"/>');
      	});
  	
      	it("should format array into html list", function() {
      	    expect($('#theList > li').length).toBe(0);
      	    
      	    util.listify($('#theList'), ['1', '2']);
      	    
      	    expect($('#theList > li').length).toBe(2);
  	    });
  	    
  	    it("should rewrite same list", function() {
      	    expect($('#theList > li').length).toBe(0);
      	    
      	    util.listify($('#theList'), ['1', '2']);
      	    util.listify($('#theList'), ['1', '2']);
      	    
      	    expect($('#theList > li').length).toBe(2);
  	    });
  	});
});