var TreePresenter = function(){

    this.design = function(id)
    {
        var treeData;

        $(document).bind("viewStructureLoaded", function onSchemaLoadedHandler(e){
            //This is to create folder as per first element of the current schema
            treeData = {};
            treeData = {
                "id" : "-1" ,"type": 'root',
                "title" : GraphicDataStore.getFirstDimension(),
                "isFolder" : "true",
                children:[]
            };
            $(document).bind("treeDataLoaded", function onSchemaLoadedHandler(e){
                if(e.treeData != "error"){
                    treeData.children = e.treeData;
                }
                var treeObj = document.getElementById(id);
                var darkTree = ElementFactory.getTree();
                darkTree.createTree(treeObj,treeData);
                $(document).unbind("treeDataLoaded");
            });
            TreePresenter.getTree();
        });

    }
}

TreePresenter.getInstance = function(){
    return new TreePresenter();
}

TreePresenter.getTree = function(){
    GetTree.get();
}

TreePresenter.createDimension = function(prefix,action,name,currentPath,flag,callBack){
    CreateDimensions.createDim(prefix,action,name,currentPath,flag,callBack);
}

TreePresenter.dragAndDropDimensions = function(prefix,name,oldPath,flag,newPath,callBack){
    DragDimension.dragAndDropDimensions(prefix,name,oldPath,flag,newPath,callBack);
}

TreePresenter.createAssortment = function(prefix,action,name,currentPath,flag,callBack){
    CreateAssortment.create(prefix,action,name,currentPath,flag,callBack);
}

TreePresenter.dragAndDropAssortment = function(prefix,name,newPath,callBack){
    var jsonData = {};
    var columnName = "products";
    jsonData[columnName] = GraphicDataStore.getProdcutsArr();
    CopyAssortment.dragAndDropAssortment(prefix,name,newPath,jsonData,callBack);
}

/**
 *
 * @param prefix
 * @param type
 * @param input
 * @param callback
 * @description calls DeleteDimension interactor
 */
TreePresenter.deleteDimension = function(prefix,type,input,callback){
    DeleteDimension.deleteDim(prefix,type,input,callback);
}
