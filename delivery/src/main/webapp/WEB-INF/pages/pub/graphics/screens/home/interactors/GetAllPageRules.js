function GetAllPageRules(){
}

GetAllPageRules.get = function(reqBody,callBack){
    Router.forwardWithPost(EngineDataStore.getApiMappingObject()["getAllPageRules"],false,reqBody,callBack);
}
