var EngineDataStore = function(){
    var screenMappingObject;
    var apiMappingObject;
    var baseURL;
    var mrmUrl;
    var pubUrl;
    var publicationDetailsArray;
}

EngineDataStore.setBaseURL = function(url){
    this.baseURL = url;
}

EngineDataStore.getBaseURL = function(){
    return this.baseURL;
}

EngineDataStore.setMrmUrl = function(url){
    this.mrmUrl = url;
}

EngineDataStore.getMrmUrl = function(){
    return this.mrmUrl;
}

EngineDataStore.setPubUrl = function(url){
    this.pubUrl = url;
}

EngineDataStore.getPubUrl = function(){
    return this.pubUrl;
}

EngineDataStore.setScreenMappingObject = function(obj){
    this.screenMappingObject = obj;
}

EngineDataStore.getScreenMappingObject = function(){
    return this.screenMappingObject;
}

EngineDataStore.setApiMappingObject = function(obj){
    this.apiMappingObject = obj;
}

EngineDataStore.getApiMappingObject = function(){
    return this.apiMappingObject;
}

EngineDataStore.setPublicationDetailsArray = function(obj){
    this.publicationDetailsArray = obj;
}

EngineDataStore.getPublicationDetailsArray = function(){
    return this.publicationDetailsArray;
}
