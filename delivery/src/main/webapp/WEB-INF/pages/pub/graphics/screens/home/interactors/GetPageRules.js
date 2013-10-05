function GetPageRules(){
//Not being used anymore since we are now using GetAllPageRules
}

GetPageRules.get = function(pageID,callBack){
    Router.loadRequest("getRules",false,callBack,pageID);
}
