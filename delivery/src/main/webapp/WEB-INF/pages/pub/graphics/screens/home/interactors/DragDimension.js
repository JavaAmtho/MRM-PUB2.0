function DragDimension(){

}

DragDimension.dragAndDropDimensions = function(prefix,name,oldPath,flag,newPath,callBack){
    Router.forward(prefix+"/name/"+name+"/path/"+oldPath+"/folder/"+flag+"/newpath/"+newPath,true,callBack);
}

