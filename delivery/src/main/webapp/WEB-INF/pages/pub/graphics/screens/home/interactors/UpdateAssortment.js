function UpdateAssortment(){

}

UpdateAssortment.update = function(assortmentObj,reqBody,callBack){
    Router.forwardWithPost("/delivery/assortment/update"+"/"+assortmentObj.title+"/"+assortmentObj.path,true,reqBody,function(data){
        callBack(data);
    });
}

this.onUpdateSuccess = function(data){
    /*$(document).trigger({
        type: "assetsTreeDataLoaded",
        treeData: data
    });*/
}