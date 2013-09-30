function CreateWBD(){

}

CreateWBD.createWBD = function(ruleID,logicalPageID,publicationID,callBack){
    Router.forward("/delivery/page/createwbd/" + ruleID + "/" + logicalPageID + "/" + publicationID,true,callBack);
}
