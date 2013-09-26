function CreateAssortment(){

}

CreateAssortment.create = function(prefix,action,name,currentPath,flag,callBack){
    var datas={"products":[]};
    Router.forwardWithPost(prefix+name+"/"+currentPath,true,datas,function(data){
        callBack(data);
    });
}

