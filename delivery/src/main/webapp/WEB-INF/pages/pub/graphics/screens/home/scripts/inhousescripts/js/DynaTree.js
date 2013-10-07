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
            anchor.textContent = "Create "+options[i];
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
        if(data){
            parentNode.addChild(newNode).activate();
             var node_expand = parentNode.isExpanded();
             if(node_expand == false)
             parentNode.expand();

             if(parentNode.data.children==null){
             parentNode.data.children=[];
             }
             parentNode.data.children.push(newNode);
        }
        else{
            alert("Duplicate names are not allowed");
        }
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
        console.log(draggedNode + "draggedNode")
        console.log(droppedSrcNode + "droppedSrcNode NEW")
        console.log(oldParentNode + "oldParentNode OLD")

        if(draggedNode.data.type == "Assortment"){
            var cb = draggedNode.toDict(true, function(dict){
                //dict.title = "Copy of " + dict.title;
                delete dict.key; // Remove key, so a new one will be created
            });
            droppedSrcNode.addChild(cb);
        }
        else{
            draggedNode.move(droppedSrcNode, dragHitMode);
            for(var i=0; i< oldParentNode.data.children.length; i++){
                if(draggedNode.data.title == oldParentNode.data.children[i].title){
                    oldParentNode.data.children.splice(i,1);
                }
            }
        }

        droppedSrcNode.data.children.push(draggedNode.data);
        droppedSrcNode.activate();
        droppedSrcNode.expand();
    }

    var draggedNode;
    var dragHitMode;
    var droppedSrcNode;
    var oldParentNode;

    this.createTree = function(treeObj,data){

        $(document).bind("expandParentNode", function onExpandParentNode(e){
            var pNode = searchFolderNodeWithName(e.currentId,null)
            pNode.parent.activate();
            pNode.parent.expand()
        });

        if(temp != null){
            temp.removeChildren();
            temp.addChild(data);
        }else{
            $(treeObj).dynatree({
                children: data,
                onCreate: function(node, span){
                    bindContextMenu(span,node.data.type);
                },
                onExpand : function(flag, node){
                	console.log(flag);
                	if(!flag)
                		return;
                    if(node.data.type == "Assortment"){
                        nodeType = "Assortment";
                        GraphicDataStore.setCurrentAssortment(node.data);
                    }else{
                    }
                },
                onActivate: function(node) {
                    var nodeType = "Dimension";
                    var data;
                    if(node.data.type == "Assortment"){
                        $('#showAllPagesBtn').addClass('hidden');

                        nodeType = "Assortment";
                        GraphicDataStore.setCurrentAssortment(node.data);
                        data = node.data.products;//HomePresenter.getProductsForSelectedNode(node);
                    }else{



                        GraphicDataStore.setCurrentView(node.data.title);
                        data = HomePresenter.getChildrenForSelectedNode(node)

                       if(node.data.type == "Publication"){

                          $('#showAllPagesBtn').removeClass('hidden');
                          /* if(data.length>0){
                               var me = findPagesForPub(data);
                               alert(JSON.stringify(me.length));
                           }*/
                       }
                        else{
                           $('#showAllPagesBtn').addClass('hidden');

                       }


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
                            oldParentNode = node.parent;
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

           /* var myPagesColl;

            function findPagesForPub(data){
                 if(data.length>0){
                     for(var i=0; i< data.length; i++){
                         if(data[i].type == "Page"){
                             myPagesColl.push(data[i]);
                         }
                         else{
                             findPagesForPub(data[i].children);
                         }
                     }

                 }
                return myPagesColl;
            }*/

            function searchFolderNodeWithName(name, searchFrom) {
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
            
            temp = $(treeObj).dynatree("getRoot");
            if(pubIdToOpen){
                var manode = searchFolderNodeWithName(pubIdToOpen,null)
                manode.activate();
                manode.expand()
            }

            //$('.colmask').jqxSplitter({ width: '100%', height: 700, splitBarSize:8, panels: [{ size: '20%'}, { size: '80%'}] });
            $('.colmask').split({
                orientation: 'vertical',
                limit: 30,
                position: '20%'
            });
//            $('.colmask').splitter({splitVertical:true,A:$('#leftPanel'),B:$('#rightPanel'),closeableto:100});
            //$('#coverMain').fadeIn(600);

               // alert(pubIdToOpen);
            /*var manode = $(treeObj).dynatree("getTree");
            console.log(manode)*/
        }
    }

}


