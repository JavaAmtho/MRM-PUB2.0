var TemplateLoader = function(){

}

var events;
var elements;
var currentHtml;

TemplateLoader.forward = function(url,async,callback){
    var eventsJsonPath = EngineDataStore.getBaseURL()+"graphics/screens"+url+"/tacks/eventsTack/events.json";
    var elementsJsonPath = EngineDataStore.getBaseURL()+"graphics/screens"+url+"/tacks/elementsTack/elements.json";
    var htmlPath = EngineDataStore.getBaseURL()+"graphics/screens"+url+"/htmls"+ url + ".html";
    getHtml(htmlPath,eventsJsonPath,elementsJsonPath,callback);
}

function createJSON(callback)
{
    var jsonData = {};
    var columnName = "events";
    jsonData[columnName] = events;
    var columnName = "elements";
    jsonData[columnName] = elements;
    var columnName = "html";
    jsonData[columnName] = currentHtml;
    //alert(JSON.stringify(jsonData));
    callback(jsonData)
}

function getHtml(htmlPath,eventsJsonPath,elementsJsonPath,callback){
    jQuery.get(htmlPath).done(
        function(data){
            currentHtml = data;
            getEvents(eventsJsonPath,elementsJsonPath,callback)
        })
        .fail(function(data){
            //alert("failed");
            currentHtml = null;
            getEvents(eventsJsonPath,elementsJsonPath,callback)
        });
}

function getEvents(eventsJsonPath,elementsJsonPath,callback){
    jQuery.get(eventsJsonPath).done(
        function(data){
            events = data;
            getElements(elementsJsonPath,callback);
        })
        .fail(function(data){
            events = null;
            getElements(elementsJsonPath,callback);
        });
}

function getElements(elementsJsonPath,callback){
    jQuery.getJSON(elementsJsonPath).done(
        function(data){
            elements = data;
            createJSON(callback);
        })
        .fail(function(data){
            //alert("failed");
            elements = null;
            createJSON(callback);
        });
}

var i = 0;

TemplateLoader.loadOnStartUp = function(){
    /*alert(JSON.stringify(arr[0].key + "key"));
     alert(JSON.stringify(arr[0].item.containerId + "item"));*/
    if(i<arr.length){
        TemplateLoader.loadTemplate(arr[i].key,TemplateLoader.loadOnStartUp,arr[i].item.containerId);
        i++;
    }

}

TemplateLoader.loadTemplate = function(key,callBack,containerID){
    //This sets the default value for the containerElementID
    containerID = typeof containerID !== ('undefined'||"") ? containerID : "mainContainer";

    TemplateLoader.forward(EngineDataStore.getScreenMappingObject()[key].screenName,true,function(data){
        TemplateLoader.designScreen(data,containerID);
        if(callBack){
            callBack();
        }
    });

}

TemplateLoader.designScreen = function(data,containerID){
    //Comment this while DEPLOYING
    //data=eval('(' + data + ')');
    var placeHolderElement = document.getElementById(containerID);
    placeHolderElement.innerHTML = data.html;

    if(data.events){
        TemplateLoader.attachEvents(data.events);
    }
    if(data.elements){
        TemplateLoader.createElements(data.elements);
    }

}

TemplateLoader.attachEvents = function(events){
    events=eval('(' + events + ')');
    for (var binding in events){
        HtmlEventDesigner.addEvents(events[binding].id,events[binding].event,events[binding].func,false);
    }
}

TemplateLoader.createElements = function(elements){
    //elements=eval('(' + elements + ')');
    for (var element in elements){
        HtmlElementDesigner.design(elements[element].id,elements[element].scriptName,elements[element].screenName);
    }
}