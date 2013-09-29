function GetAssortments(){

}

GetAssortments.get = function(pagePath,logicalPageID,callBack){
    Router.loadRequest("getAssortmentsList",false,callBack, pagePath + "/" + logicalPageID);
}
