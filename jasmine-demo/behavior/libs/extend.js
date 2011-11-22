// these are straight from "Javascript: The Good Parts"

Function.prototype.method = function(name, func) {
    if (!this.prototype[name]) {
        this.prototype[name] = func;
        return this;
    }
};

Function.method('curry', function(){
    var slice = Array.prototype.slice;
    var args = slice.apply(arguments);
    var that = this;
    
    return function() {
        return that.apply(null, args.concat(slice.apply(arguments)));
    };
});