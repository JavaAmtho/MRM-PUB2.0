function GetAssortments(){

}

GetAssortments.get = function(pagePath,logicalPageID,callBack){
    Router.loadRequest("getAssortmentsList" + pagePath + "/" + logicalPageID,false,callBack);
}
