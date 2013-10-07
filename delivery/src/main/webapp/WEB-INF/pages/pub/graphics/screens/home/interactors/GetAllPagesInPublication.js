function GetAllPagesInPublication(){
}

GetAllPagesInPublication.get = function(publicationID,callBack){
    Router.loadRequest("getAllPagesUnderPublication",false,callBack,publicationID);
}
