var util = function(){
    var listify = function(jqList, strings){
        jqList.empty();
        $.each(strings, function() {
            jqList.append('<li>' + this + '</li>');
        });  
    };
    
    return {
        listify: listify
    };
}();

