var AssetTreeSelector = function(){
    var treeObj;
    this.design = function(id){
        //var treeData;
       /* $(document).bind("assetsTreeDataLoaded", function(e){*/
            var treeObj = document.getElementById(id);
            /*var darkTree = ElementFactory.getLazyTree();
            darkTree.createTree(treeObj,url);*/
       /* });*/
    }

}

AssetTreeSelector.getInstance = function(){
    return new AssetTreeSelector();
}
