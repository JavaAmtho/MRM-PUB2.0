function GetPublications(){

}

GetPublications.get = function(ccId,callBack){
    Router.loadRequest("getPublications",true,callBack,ccId);
}
