function CreateDimensions(){

}

CreateDimensions.createDim = function(prefix,action,name,currentPath,flag,callBack){

    var reqBody = new Object();
//    if(action === "Publication"){
//        var myArr = EngineDataStore.getPublicationDetailsArray();
//        var item = myArr[Math.floor(Math.random()*myArr.length)];
//        reqBody = item;
//    }

    Router.forwardWithPost(prefix+action+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,true,reqBody,function(data){
        callBack(data);
    });
}

