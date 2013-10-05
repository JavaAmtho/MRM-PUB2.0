$(document).ready(function() {
     loadInitialConfigurations();

});

function loadInitialConfigurations(){
    Router.forward("../../../graphics/tacks/InitialConfiguration.json",true,function(json){
        parseInitialConfiguration(json);
        getPublicationDetailsObject();
    });
}

function parseInitialConfiguration(data){
    EngineDataStore.setBaseURL(data.baseUrl);
    EngineDataStore.setMrmUrl(data.MRMUrl);
    EngineDataStore.setPubUrl(data.PubUrl);
}

function getPublicationDetailsObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/PublicationDetails.json",true,function(json){
        parsePublicationDetailsObject(json);
    });
}

function parsePublicationDetailsObject(json){
    EngineDataStore.setPublicationDetailsArray(json);
    getScreenMappingObject();
}


function getScreenMappingObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/screenMapping.json",true,function(json){
        parseScreenMappingObject(json);
    });
}

function parseScreenMappingObject(json){
    EngineDataStore.setScreenMappingObject(json);
    $.each(json, function (key, item) {
        if(item.loadOnStartup == "true"){
            TemplateLoader.loadTemplate(key,'',item.containerId);
        }
    });
    getApiMappingObject();
}

function getApiMappingObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/RequestMapping.json",true,function(json){
        parseApiMappingObject(json);
    });
}

function parseApiMappingObject(json){
    EngineDataStore.setApiMappingObject(json);
}

