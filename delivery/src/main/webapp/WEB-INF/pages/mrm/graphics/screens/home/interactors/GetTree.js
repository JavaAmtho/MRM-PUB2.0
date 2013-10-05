function GetTree(){

}

GetTree.get = function(){
    Router.loadRequest("getTree",true,onTreeSuccess,GraphicDataStore.getCurrentSchema().name);
}

this.onTreeSuccess = function(data){
    $(document).trigger({
        type: "treeDataLoaded",
        treeData: data
    });
}