function GetViewStructure(){

}

GetViewStructure.get = function(schemaId){
    Router.loadRequest("getSchema",true,GetViewStructure.onViewStructureSuccess,schemaId);
}

GetViewStructure.onViewStructureSuccess = function(data){
    GraphicDataStore.setCurrentSchema(data);
    GraphicDataStore.setSchemaLabel();
    //HomePresenter.clearList();
    $(document).trigger({
        type: "viewStructureLoaded",
        schemaData: GraphicDataStore.getSchemaArray(),
        schemaChanged: true
    });
    $(document).trigger({
        type: "TREE_ITEM_CLICKED",
        uiData: ""
    });
}

GetViewStructure.getAll = function(){
    Router.loadRequest("getAllSchema",true,GetViewStructure.onViewStructuresLoaded);
}

GetViewStructure.onViewStructuresLoaded = function(data){
    GraphicDataStore.setSchemaArray(data);
    GraphicDataStore.setDefaultSchema();
    $(document).trigger({
        type: "viewStructureLoaded",
        schemaData: GraphicDataStore.getSchemaArray(),
        schemaChanged: false
    });

}