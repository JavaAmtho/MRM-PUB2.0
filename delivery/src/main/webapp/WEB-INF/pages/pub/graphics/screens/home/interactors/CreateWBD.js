function CreateWBD(){

}

CreateWBD.createWBD = function(publicationID,assortmentID,templateID,callBack){
    Router.loadRequest("createWBD" + publicationID + "/" + assortmentID + "/" + templateID,true,callBack);
}
