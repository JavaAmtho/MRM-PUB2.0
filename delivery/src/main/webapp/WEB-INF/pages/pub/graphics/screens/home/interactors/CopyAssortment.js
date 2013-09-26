function CopyAssortment(){

}

CopyAssortment.dragAndDropAssortment = function(prefix,name,newPath,reqBody,callBack){
    Router.forwardWithPost(prefix+name+"/"+newPath,true,reqBody,callBack);
}

