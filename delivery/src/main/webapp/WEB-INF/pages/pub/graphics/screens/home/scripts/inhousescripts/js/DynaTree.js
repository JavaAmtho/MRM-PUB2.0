var temp;

var DynaTree = function(){
    var currentPath;
    var parentNode;
    var newNode

    function menuExists(type){
        var contextMenusHolder = document.getElementById('menus')
        uls = contextMenusHolder.getElementsByTagName('ul');
        for (i=0; i<uls.length; i++) {
            if (uls[i].id == type) {
                return true;
            }
        }
        return false;
    }

    function createList(type,menuOptions){
        var contextMenusHolder = document.getElementById('menus');
        var options = menuOptions;
        var list = document.createElement("ul");
        list.id = type;
        list.setAttribute("class", "contextMenu");
        //list.addClass("contextMenu");
        for (var i in options) {
            var anchor = document.createElement("a");
            anchor.href = "#"+options[i];
            //anchor.innerText = "Create"+options[i];
            anchor.textContent = "Create"+options[i];
            var elem = document.createElement("li");
            elem.appendChild(anchor);
            list.appendChild(elem);
        }
        contextMenusHolder.appendChild(list);
    }

    // --- Contextmenu --------------------------------------------------
    function bindContextMenu(span,type) {

        var possibleDim=[];
        possibleDim  = GraphicDataStore.getPossibleChild(type);
        if(possibleDim != ""){
            //Check as per type of node if menu exists later
            var alreadyExists = menuExists(type)
            if(!alreadyExists){
                createList(type,possibleDim);
            }
            $(span).contextMenu({menu: type}, function(action, el, pos) {
                var name=prompt("Please enter "+action+" name","");
                if(name != null){
                    if(name != ""){
                        parentNode = $.ui.dynatree.getNode(el);
                        if(parentNode.data.type == "root"){
                            currentPath = "-1";
                        }
                        else{
                            currentPath = parentNode.data.path+","+ parentNode.data.title;
                            if(currentPath.indexOf("-1")==0)
                                currentPath = currentPath.match(/([^,]*),(.*)/)[2];   //To remove -1 root folder
                        }

                        var flag = isFolder(action);
                        var prefix=getUrlPrefix(action,"create");
                        if(action == "Assortment"){
                            newNode = createAssortmentNode(name,action,currentPath,flag);
                            TreePresenter.createAssortment(prefix,action,name,currentPath,flag,addNode);
                        }else{
                            newNode = createNode(name,action,currentPath,flag);
                            TreePresenter.createDimension(prefix,action,name,currentPath,flag,addNode);
                        }
                    }
                }
            });

        }
    }

    function addNode(data){
        parentNode.addChild(newNode).activate();
        var node_expand = parentNode.isExpanded();
        if(node_expand == false)
            parentNode.expand();

        if(parentNode.data.children==null){
            parentNode.data.children=[];
        }
        parentNode.data.children.push(newNode);
    }

    function createAssortmentNode(name,type,path,flag){
        var flag = isFolder(type);
        var newNode = {
            "id": "",
            "title": name,
            "type": type,
            "path": path,
            "isFolder": flag,
            "products": []
        }
        return newNode;
    }

    function createNode(name,type,path,flag){
        var flag = isFolder(type);
        var newNode = {
                        "id": "",
                        "title": name,
                        "type": type,
                        "path": path,
                        "isFolder": flag,
                        "children": []
                     }
        return newNode;
    }

    function isFolder(dim){
        var flag =true;
        if(dim == "Page" || dim == "Assortment"){
            flag = false;
        }
        return flag;
    }

    var getUrlPrefix=function(type,action){
        switch(type){
            case "Chapter":
                return  "/delivery/chapter/"+action+"/";
            case "Page":
               return  "/delivery/page/"+action+"/";
            case "Assortment":
                return  "/delivery/assortment/"+action+"/";
        }
        return "/delivery/dimension/"+action+"/";
    }

    function onDropSuccess(){
        if(draggedNode.data.type == "Assortment"){
            var cb = draggedNode.toDict(true, function(dict){
                //dict.title = "Copy of " + dict.title;
                delete dict.key; // Remove key, so a new one will be created
            });
            droppedSrcNode.addChild(cb);
        }
        else{
            draggedNode.move(droppedSrcNode, dragHitMode);
        }
    }

    var draggedNode;
    var dragHitMode;
    var droppedSrcNode;

    this.createTree = function(treeObj,data){
        if(temp != null){
            temp.removeChildren();
            temp.addChild(data);
        }else{
            $(treeObj).dynatree({
                children: data,
                onCreate: function(node, span){
                    bindContextMenu(span,node.data.type);
                },
                onActivate: function(node) {
                    var nodeType = "Dimension";
                    var data;
                    if(node.data.type == "Assortment"){
                        nodeType = "Assortment";
                        GraphicDataStore.setCurrentAssortment(node.data);
                        data = node.data.products;//HomePresenter.getProductsForSelectedNode(node);
                    }else{
                        data = HomePresenter.getChildrenForSelectedNode(node)
                    }
                    $(document).trigger({
                        type: "TREE_ITEM_CLICKED",
                        uiData: data,
                        nodeType: nodeType
                    });
                },
                dnd: {
                    preventVoidMoves: true, // Prevent dropping nodes 'before self', etc.
                    onDragStart: function(node) {
                        if(node.data.type == "Chapter"||node.data.type == "Page"||node.data.type == "Assortment" ) {
                            return true;
                        }
                        else{
                            return false;
                        }

                    },
                    onDragEnter: function(node, sourceNode) {
                        if(sourceNode.data.type == "Assortment"){
                            if(node.data.type == "Page"){
                                return ["over"];
                            }
                        }
                        if(node.data.type == "Publication" || node.data.type == "Chapter"){
                            return ["over"];
                        }
                        else{
                            return false;
                        }
                    },
                    onDrop: function(sourceNode, node, hitMode, ui, draggable) {
                        draggedNode = node;
                        droppedSrcNode = sourceNode;
                        dragHitMode = hitMode;

                        var parentNode = droppedSrcNode;
                        var newChildNode = draggedNode;
                        var oldPathForChild = draggedNode.data.path;

                        newChildNode.data.path = parentNode.data.path +","+parentNode.data.title;
                        var newPathForChild   = newChildNode.data.path;
                        var flag=isFolder(draggedNode.data.type);
                        var prefix;
                         if(draggedNode.data.type == "Assortment"){
                             prefix = getUrlPrefix(draggedNode.data.type,"copy");
                             TreePresenter.dragAndDropAssortment(prefix,draggedNode.data.title,newPathForChild,onDropSuccess);
                         }else{
                             prefix =getUrlPrefix(draggedNode.data.type,"move");
                             prefix = prefix+draggedNode.data.type;
                             TreePresenter.dragAndDropDimensions(prefix,draggedNode.data.title,oldPathForChild,flag,newPathForChild,onDropSuccess);
                         }

                    }
                }
            });
            temp = $(treeObj).dynatree("getRoot");
            if(pubIdToOpen){
                var manode = seachFolderNodeWithName(pubIdToOpen,null)
                manode.activate();
                manode.expand()
            }

            function seachFolderNodeWithName(name, searchFrom) {
                if (name == null) {
                    return undefined;
                }

                if (searchFrom == null) {
                    searchFrom = $(treeObj).dynatree("getRoot");
                }

                var match = undefined;

                searchFrom.visit(function (node) {
                    if (node.data.title === name) {
                        match = node;
                        return false; // Break if found
                    }
                });
                return match;
            };
               // alert(pubIdToOpen);
            /*var manode = $(treeObj).dynatree("getTree");
            console.log(manode)*/
        }
    }

}


