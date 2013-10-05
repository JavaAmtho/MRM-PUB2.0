var TreeObjectVO = function(){

}

TreeObjectVO.prototype = {
    "id": "",
    "title": "",
    "type": "",
    "path": "",
    "children": [],

    "isFolder": function(type) {
        if(type == "Page"){
            return true;
        }
        else{
            return false;
        }
    }
};
