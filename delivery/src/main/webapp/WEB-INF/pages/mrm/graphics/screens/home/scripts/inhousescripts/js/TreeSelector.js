var TreeSelector = function(){

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
            GetTree.get();
        });

    }

}

TreeSelector.getInstance = function(){
    return new TreeSelector();
}
