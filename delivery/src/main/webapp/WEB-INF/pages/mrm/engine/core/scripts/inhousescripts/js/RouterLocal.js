var Router = function(){

}

Router.forwardWithPost = function(url,async,reqBody,callback){
    $.ajax({
        url:url,
        contentType: "application/json",
        type:"POST",
        data:JSON.stringify(reqBody),
        dataType:"json",
        async:async,
        success:function(result){
            callback(result);
        },
        error: function (error) {
            callback("error");
        }
    });
}

Router.forward = function(url,async,callback){
    jQuery.getJSON(url).done(callback)
        .fail(function(data){
            callback(data);
        });
}

Router.forwardAPIRequests = function(url,async,callback){
    jQuery.getJSON(EngineDataStore.getBaseURL()+"../"+url).done(callback)
        .fail(function(data){

        });
}

Router.loadRequest = function(key,async,callBack,params){
    if(params){
        switch(params){
            case "1":
                key = "getViewStructure1";
                break;
            case "MI-Campaign-SubCampaign-CP-CC-Publication":
                key = "getTree1";
                break;
        }
        Router.forwardAPIRequests(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }
    else{
        Router.forwardAPIRequests(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }

}