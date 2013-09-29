function GetAssortments(){

}

GetAssortments.get = function(pagePath,logicalPageID,callBack){
    console.log(pagePath,logicalPageID)
    Router.loadRequest("getAssortmentsList",false,callBack, pagePath + "/" + logicalPageID);
}
