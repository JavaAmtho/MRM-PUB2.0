function SavePageRules(){

}


SavePageRules.save = function(key,rulesObj,callBack){
    var reqBody = rulesObj;
    var url = EngineDataStore.getApiMappingObject()[key];
    Router.forwardWithPost(url,true,reqBody,function(data){
        callBack(data);
    });
}
