function GetPageRules(){

}

GetPageRules.get = function(pageID,callBack){
    Router.loadRequest("getRules",false,callBack,pageID);
}
