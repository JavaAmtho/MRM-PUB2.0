var pubIdToOpen;
$(document).ready(function() {

   /* $("body").queryLoader2({
        barColor: "#6e6d73",
        backgroundColor: "#343434",
        percentage: true,
        barHeight: 1,
        completeAnimation: "grow"
    });*/

    pubIdToOpen = getParameterByName("pubId");
    EngineDataStore.setBaseURL("../../../");
    getPublicationDetailsObject();
});

function getPublicationDetailsObject(){
    Router.forward(EngineDataStore.getBaseURL()+"graphics/tacks/PublicationDetails.json",true,function(json){
        parsePublicationDetailsObject(json);
    });
}

function parsePublicationDetailsObject(json){
    EngineDataStore.setPublicationDetailsArray(json);
    getScreenMappingObject();
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
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
    Router.loadRequest("getMasterTemplateList",false,function(data){
        EngineDataStore.setMasterTemplateList(data);
    });
}

