function GetAssetsTree(){

}

GetAssetsTree.get = function(){
    Router.loadRequest("getAssetsTree",true,onAssetsTreeSuccess);
}

this.onAssetsTreeSuccess = function(data){
    data=eval('(' + data + ')');
    $(document).trigger({
        type: "assetsTreeDataLoaded",
        treeData: data
    });
}