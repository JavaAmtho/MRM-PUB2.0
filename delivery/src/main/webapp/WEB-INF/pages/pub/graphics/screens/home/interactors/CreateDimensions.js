function CreateDimensions(){

}

CreateDimensions.createDim = function(prefix,action,name,currentPath,flag,callBack){
       
    var reqBody = new Object();
    Router.forwardWithPost(prefix+action+"/name/"+name+"/path/"+currentPath+"/folder/"+flag,true,reqBody,function(data){
        callBack(data);
    });
}

