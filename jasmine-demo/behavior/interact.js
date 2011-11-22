// NOTE: non-reusable wiring of components that should be kept to a minimum and integration tested
// NOTE: Two simple integration tests can prove that is is wired correctly

var wireItUp = function() {
    var validator = createValidator($('#emailForm'), util.listify.curry($('#errorList')));
    var service = createService(3000);
    
    $('#emailForm').submit(function(){
        if (validator.isValid()) {
            service.call($('#emailField').val(), util.listify.curry($('#emailList')));
        }
        
        return false;
    });    
};

$(document).ready(wireItUp);