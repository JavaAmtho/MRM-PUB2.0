function GetAllPagesInPublication(){
}

GetAllPagesInPublication.get = function(publicationID,callBack){
    console.log("Hello")
    Router.loadRequest("getAllPagesUnderPublication",false,callBack,publicationID);
}
