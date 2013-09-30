var Router = function(){

}

Router.forward = function(url,async,callback){
    jQuery.getJSON(url).done(callback)
        .fail(function(data){
            //alert("failed");
            callback(data);
        });
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


Router.forwardAPIRequests = function(url,async,callback){
    jQuery.getJSON(EngineDataStore.getBaseURL()+"../"+url).done(callback)
        .fail(function(data){
            //alert("failed");

        });
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

Router.loadRequest = function(key,async,callBack,params){
    //Comment this while DEPLOYING and uncomment belove
	
    if(params){

        switch(params){
            case "1":
                key = "getViewStructure1";
                break;
            case "2":
                key = "getViewStructure2";
                break;
            case "3":
                key = "getViewStructure3";
                break;
            case "MI-Campaign-SubCampaign-CP-CC-Publication":
                key = "getTree1";
                break;
            case "Campaign-SubCampaign-MI-CP-CC-Publication":
                key = "getTree2";
                break;
            case "Campaign-Publication-MasterPublication":
                key = "getTree3";
                break;
        }
        Router.forwardAPIRequests(EngineDataStore.getApiMappingObject()[key],async,function(data){
            //data=eval('(' + data + ')');
            callBack(data);
        });
    }
    else{
        Router.forwardAPIRequests(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }

    //Uncomment this while DEPLOYING and comment above
    /*if(params){
        Router.forward(EngineDataStore.getApiMappingObject()[key]+params,async,function(data){
            callBack(data);
        });
    }
    else{
        Router.forward(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }*/

}


/*
Router.forwardWithParams = function(url,path,type,callback){
    $.ajax({
        url:url,
        data:{path:path},
        dataType:'json',
        */
/* beforeSend: function(xhr){
         xhr.setRequestHeader('myName','rohan')
         },*//*

        type: type,
        success:function(result){
            callback(result);
        },
        error: function (error) {
            callback("error");
        }
    });

}*/
