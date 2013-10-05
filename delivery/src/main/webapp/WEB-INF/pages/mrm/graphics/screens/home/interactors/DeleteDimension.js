function DeleteDimension(){

}

DeleteDimension.deleteDim = function(prefix,type,input,callback){
    Router.forwardWithPost(prefix+type+"/"+input.name,true,input,callback);
}

