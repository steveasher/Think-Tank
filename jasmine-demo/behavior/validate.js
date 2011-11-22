// NOTE: Reusable, agnostic of any view
// NOTE: Only adds behavior to elements based on classes, that way behavior is 'opt-in'

var createValidator = function(jqForm, handleErrors) {
    
    var invalidEmail = function() {
        return $(this).val().match(/\w+@\w+\.\w+/) === null;
    };
    
    var validate = function() {
        var errors = [];
        var textFields = jqForm.find('input.validated[type=text]');
        
        textFields.each(function(){
            if (!$(this).val()) {
                errors.push('Empty field: ' + $(this).attr('id'));
            }
        });
        
        textFields.filter('.email').filter(invalidEmail).each(function(){
            errors.push('Invalid Email: ' + $(this).attr('id'));
        });
        
        handleErrors(errors);
        
        return errors.length === 0;
    };
    
    return {
        isValid: validate       
    };
};