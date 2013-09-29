var Router = function(){

}

Router.forward = function(url,async,callback){
    $.ajax({
        url:url,
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
            alert(JSON.stringify(error))
            callback("error");
        }
    });
}

Router.loadRequest = function(key,async,callBack,params){
    if(params){
        Router.forward(EngineDataStore.getApiMappingObject()[key]+params,async,function(data){
            callBack(data);
        });
    }
    else{
        Router.forward(EngineDataStore.getApiMappingObject()[key],async,function(data){
            callBack(data);
        });
    }
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
