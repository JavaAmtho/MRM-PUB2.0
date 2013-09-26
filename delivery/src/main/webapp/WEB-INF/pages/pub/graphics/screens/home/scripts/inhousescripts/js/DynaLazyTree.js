var DynaLazyTree = function(){

    this.createTree = function(treeObj,urls){
        $(treeObj).dynatree({
            initAjax: {
                url:  urls
            },
            onActivate: function(node) {
                HomePresenter.populateAssetsList(node.data.children[0])
            },
            onLazyRead: function(node){
                node.appendAjax({
                    url: EngineDataStore.getBaseURL()+"/delivery/lo/"+urls+"/"+node.data.id,//getChildURL(node.data.id),
                    success: function(node, data) {
                        //node.addChild(data);
                        HomePresenter.populateAssetsList(data);
                        if(node.data.children == null){
                            node.data.children = [];
                            node.data.children.push(data);
                        }
                    },
                    error: function(node, XMLHttpRequest, textStatus, errorThrown){
                        // Called on error, after error icon was created.
                    }
                });
            }
        });
        var temps = $(treeObj).dynatree("getTree")
        temps.reload();
    }

    $("#asSearch").keyup(function(e){
        HomePresenter.searchList(e);
    });

    function getChildURL(id){
        var url;
       if(id==62){
           url = EngineDataStore.getBaseURL()+"../testdrive/mocks/tree/PimAssets.json"
       }
        if(id==63){
           url = EngineDataStore.getBaseURL()+"../testdrive/mocks/tree/MamAssets.json"
       }
        if(id==91){
            url = EngineDataStore.getBaseURL()+"../testdrive/mocks/tree/PimAssets.json"
        }
       return url;
    }
};