var AssetTreePresenter = function(){
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

AssetTreePresenter.getInstance = function(){
    return new AssetTreePresenter();
}
