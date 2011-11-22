describe("The validator", function() {
	var validator;
	var result;
	var errors = [];
	
	beforeEach(function() {
        // NOTE: the jasmine-jquery plugin promises to clean this up
        $('#testDiv').remove();
        $('body').append('<div id="testDiv">' +
                            '<ul class="errors"></ul>' +
                            '<form id="testForm">' +
                                     '<input type="submit" class="validated"/>' +
                            '</form>' +
                        '</div>');
                        
        validator = createValidator($('#testForm'), function(theErrors){
            errors = theErrors;
        });
  	});
  	
  	it("should validate empty form", function(){
        result = validator.isValid();
  	    
  	    expect(result).toBe(true);
        expect(errors.length).toBe(0);
  	});
  	
  	it("should fail for empty text field", function(){
  	    $('#testForm').prepend('<input type="text" id="field" class="validated"/>');
  	    
  	    result = validator.isValid();
  	    
  	    expect(result).toBe(false);
        expect(errors.length).toBe(1);
  	});
  	
  	it("should rewrite errors each time", function(){
  	    $('#testForm').prepend('<input type="text" id="field" class="validated"/>');
  	    
  	    validator.isValid();
  	    validator.isValid();
  	    
        expect(errors.length).toBe(1);
  	});

  	it("should report all empty fields", function() {
  	    $('#testForm').prepend('<input type="text" id="field1" class="validated">' +
                                '<input type="text" id="field2" class="validated" value="x">' +
                                '<input type="text" id="field3" class="validated">');

  	    result = validator.isValid();

  	    expect(result).toBe(false);
        expect(errors.length).toBe(2);
  	});
  	
  	describe("For email field", function(){
  	    beforeEach(function(){
  	       $('form').prepend('<input type="text" id="emailField" class="validated email"/>'); 
  	    });
  	    
  	  	it("should reject anything without an '@'", function() {
  	  	    $('#emailField').val('notAn.Email');
  	  	    
  	  	    result = validator.isValid();
  	  	    expect(result).toBe(false);
      	});   
      	
      	it("should reject anything without a '.'", function() {
  	  	    $('#emailField').val('not@AnEmail');
  	  	    
  	  	    result = validator.isValid();
  	  	    expect(result).toBe(false);
      	});
      	
      	it("should accept valid emails", function() {
  	  	    $('#emailField').val('valid@email.com');
  	  	    
  	  	    result = validator.isValid();
  	  	    expect(result).toBe(true);
      	});
  	});
});