var rendererData;
var btnSelectionFlag = 0;

$(document).bind("TREE_ITEM_CLICKED", function itemClickedHandler(e){
    rendererData = {"mydata":e.uiData};
    if(e.nodeType == "Assortment"){
        showAssortmentPanel(e.uiData);
    }else{
        hideAssortPanel();
        loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
        btnFocus(".tileBtnCSS");
    }

});

function handleViewChange(evt){

    switch(evt.currentTarget.id)
    {
        case "tileView":
            //console.log(":: Load Tile View Button Clicked ::");
            loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
            btnFocus(".tileBtnCSS");
            break;

        case "listView":
            //console.log(":: Load List View Button Clicked ::");
            loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/ListViewRenderer.html");
            btnFocus(".listBtnCSS");
            break;

        case "detailView":
            //console.log(":: Load Detail View Button Clicked ::");
            loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/DetailViewRenderer.html");
            btnFocus(".detailBtnCSS");
            break;
    }
}

function clearList(){
    var contextMenusHolder = document.getElementById('menus');
    contextMenusHolder.innerHTML = "";
}

function btnFocus(btn){
    $('.tileBtnCSS').css("border","0px");
    $('.listBtnCSS').css("border","0px");
    $('.detailBtnCSS').css("border","0px");
    $(btn).css("border","1px solid black");
}

function getChildrenForSelectedNode(node){
    var nodeDetails = [];
    for(var i=0; i< node.data.children.length; i++){
        var obj = new TreeObjectVO();
        obj.id = node.data.children[i].id;
        obj.title = node.data.children[i].title;
        obj.type = node.data.children[i].type;
        obj.path = node.data.children[i].path;
        obj.children = node.data.children[i].children;
        nodeDetails.push(obj);
    }
    return nodeDetails;
}

function showAssortmentPanel(rendererData){
    $("#dim").hide();
    $("#assortPanel").show();
    $("#subtab1").jqxListBox({ allowDrop: true, allowDrag: true,dropAction:'copy', source: rendererData, width: 200, height: 250,
        dragEnd: function (event) {
            //alert(123)
        }
    });
}

function loadViewItems(evt,currentTemplateView){
     MustacheWrapper.createUI(currentTemplateView,evt, function(currentViewStr){
     $('#viewHolder').html(currentViewStr);
     });
}

function hideAssortPanel(){
    $('#assortPanel').hide();
    $('#dim').show();
}

function createTree(){
    var urls = "/pub-controller/testdrive/mocks/tree/AssetTree.json";
    var treeObj = document.getElementById("assetsTree");
    var darkTree = ElementFactory.getLazyTree();
    darkTree.createTree(treeObj,urls);
}

function slidePanel(evt){
    if (btnSelectionFlag==0){
       $("#typeHolder").html(evt.currentTarget.name);
       $("#panel").animate({right:'30px'},"slow",function(){
           createTree();
       });
       btnSelectionFlag=1;
    }
    else if (btnSelectionFlag == 1 && ($("#typeHolder").html()== evt.currentTarget.name)){
        $("#panel").animate({right:'-200px'},"slow");
        reset();
        btnSelectionFlag=0;
    }
    else {
       $("#typeHolder").html(evt.currentTarget.name);
    }
    changeSelectedBtn(evt.currentTarget.id);
}

function populateAssetsList(data){
    $("#assetDetails").jqxListBox({ source: data,allowDrop: false, allowDrag:true, displayMember: "title", valueMember: "CompanyName", width: 200, height: 250,
        dragStart: function (item) {
            //return true;
        },
        renderer: function (index, label, value) {
            var datarecord = data[index];
            var imgurl = datarecord.image;
            var img = '<img height="30" width="40" src="' + imgurl + '"/>';
            var table = '<table style="min-width: 130px;"><tr><td style="width: 40px;" rowspan="1">' + img + '</td><td>' + datarecord.title +  '</td></tr></table>';
            return table;
        }
    });

}

function reset(){
    $("#btnMIM").css("background-image","url(/pub-controller/pages/pub/graphics/screens/home/images/icons/MIM.png)");
    $("#btnPIM").css("background-image","url(/pub-controller/pages/pub/graphics/screens/home/images/icons/PIM.png)");
    $("#btnMAM").css("background-image","url(/pub-controller/pages/pub/graphics/screens/home/images/icons/MAM.png)");
}

function changeSelectedBtn(btnId){
    $('#btnMIM').css("background-image","url(/pub-controller/pages/pub/graphics/screens/home/images/icons/MIM.png)");
    $('#btnPIM').css("background-image","url(/pub-controller/pages/pub/graphics/screens/home/images/icons/PIM.png)");
    $('#btnMAM').css("background-image","url(/pub-controller/pages/pub/graphics/screens/home/images/icons/MAM.png)");
    var urls;
    if(btnId == "btnPIM"){
        urls= EngineDataStore.getBaseURL()+"graphics/screens/home/images/icons/PIMb.png";
    }
    if(btnId == "btnMAM"){
        urls= EngineDataStore.getBaseURL()+"graphics/screens/home/images/icons/MAMb.png";
    }
    if(btnId == "btnMIM"){
        urls= EngineDataStore.getBaseURL()+"graphics/screens/home/images/icons/MIMb.png";
    }
    $('#'+btnId).css("background-image",'url("' + urls + '")');
}