function HomePresenter(){

}

var rendererData;
var btnSelectionFlag = 0;
var onTarget=false;
var $isotopeContainer;

HomePresenter.handleViewChange = function(evt){
    switch(evt.currentTarget.id)
    {
        case "tileView":
            //console.log(":: Load Tile View Button Clicked ::");
            HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
            HomePresenter.btnFocus(".tileBtnCSS");
            break;

        case "listView":
            //console.log(":: Load List View Button Clicked ::");
            HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/ListViewRenderer.html");
            HomePresenter.btnFocus(".listBtnCSS");
            break;

        case "detailView":
            //console.log(":: Load Detail View Button Clicked ::");
            HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/DetailViewRenderer.html");
            HomePresenter.btnFocus(".detailBtnCSS");
            break;
    }
}

HomePresenter.loadViewItems = function(evt,currentTemplateView){
    $.each(evt.mydata, function (index, value) {
        var ref = value;
        if (ref != null) {
            var css = "";
            var stackcss = "";
            if (ref.type == "Page") {
                css = "masterPage";
                console.log("CSS : " + css);
                ref.typeCSS = css;
                ref.hiddenCSS = "";
            }
            else {
                ref.typeCSS = "dimension";
                ref.hiddenCSS = 'hidden';
            }
        }

    });

    MustacheWrapper.createUI(currentTemplateView,evt, function(currentViewStr){


        $('#viewHolder').html(currentViewStr);



        if ($isotopeContainer) {
            $isotopeContainer.isotope('destroy');
        }


        $isotopeContainer =  $('#viewHolder');

        $isotopeContainer.find('.masterPage').each(function (key,value) {
            var $this = $(this),
                number = key+1;
            if (number % 2 == 1) {
                $this.addClass('odd');
            }
            else {
                $this.addClass('even');
            }
        });

        $isotopeContainer.isotope();
    });
}

var currentPanelId;

HomePresenter.slidePanel = function(evt){
    currentPanelId = evt.currentTarget.id;

    var btnId = evt.currentTarget.id;
    if (btnSelectionFlag==0){
        $("#typeHolder").html(evt.currentTarget.name);
        $("#panel").animate({right:'30px'},"slow",function(){
            HomePresenter.createTree(btnId);
        });
        btnSelectionFlag=1;
    }
    else if (btnSelectionFlag == 1 && ($("#typeHolder").html()== evt.currentTarget.name)){
        $("#panel").animate({right:'-200px'},"slow");
        HomePresenter.reset();
        btnSelectionFlag=0;
    }
    else {
        $("#typeHolder").html(evt.currentTarget.name);
        HomePresenter.createTree(btnId);
    }
    HomePresenter.changeSelectedBtn(evt.currentTarget.id);
}


HomePresenter.clearList = function(){
    var contextMenusHolder = document.getElementById('menus');
    contextMenusHolder.innerHTML = "";
}

HomePresenter.btnFocus = function(btn){
    $('.tileBtnCSS').css("border","0px");
    $('.listBtnCSS').css("border","0px");
    $('.detailBtnCSS').css("border","0px");
    $(btn).css("border","1px solid black");
}

HomePresenter.createTree = function(btnId){
    var urls;
    urls= EngineDataStore.getBaseURL()+EngineDataStore.getApiMappingObject()[btnId];

    var treeObj = document.getElementById("assetsTree");
    var darkTree = ElementFactory.getLazyTree();
    darkTree.createTree(treeObj,urls);
}

HomePresenter.reset = function(){
    $("#btnMIM").css("background-image","url(/delivery/pages/pub/graphics/screens/home/images/icons/MIM.png)");
    $("#btnPIM").css("background-image","url(/delivery/pages/pub/graphics/screens/home/images/icons/PIM.png)");
    $("#btnMAM").css("background-image","url(/delivery/pages/pub/graphics/screens/home/images/icons/MAM.png)");
}

HomePresenter.changeSelectedBtn = function(btnId){
    HomePresenter.reset();
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

$(document).bind("TREE_ITEM_CLICKED", function itemClickedHandler(e){
    if(e.nodeType == "Assortment"){
        HomePresenter.showAssortmentPanel(e.uiData);
    }else{
        HomePresenter.hideAssortPanel();
        rendererData = {"mydata":e.uiData};
        HomePresenter.loadViewItems(rendererData, EngineDataStore.getBaseURL()+"graphics/screens/home/htmls/renderers/TileViewRenderer.html");
        HomePresenter.btnFocus(".tileBtnCSS");
    }

});

HomePresenter.getChildrenForSelectedNode = function(node){
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

HomePresenter.getProductsForSelectedNode = function(node){
    var nodeDetails = [];
    for(var i=0; i< node.data.products.length; i++){
        var obj = new ProductVO();
        obj.id = node.data.products[i].id;
        obj.title = node.data.products[i].title;
        obj.type = node.data.products[i].type;
        obj.path = node.data.products[i].path;
        nodeDetails.push(obj);
    }
    return nodeDetails;
}

HomePresenter.showAssortmentPanel = function(rendererData){


    console.log(rendererData)
    HomePresenter.unHideAssortPanel();
    GraphicDataStore.setProdcutsArr(rendererData);
    //Converting the div into the jqwidget list
   // var theme=getDemoTheme();
    /*$("#subtab1").jqxListBox({ selectedIndex: 3, source: rendererData, width: 500, height: 500,scrollBarSize:10

        *//*renderer: function (index, label, value) {
            var datarecord = rendererData[index];
            var imgurl = datarecord.image;
            var img = '<img height="30" width="40" src="' + imgurl + '"/>';
            var table = '<table class="assestsJQList" style="min-width: 130px;"><tr><td style="width: 40px;" rowspan="1">' + img + '</td><td>' + datarecord.title +  '</td></tr></table>';
            return table;
        }*//*
    });*/

    $('#subtab1').jqxListBox({ selectedIndex: 0, allowDrag:false, source: GraphicDataStore.getProdcutsArr(),  itemHeight: 70, height: 500, width: '100%',
        renderer: function (index, label, value) {
            console.log(rendererData)
            var datarecord = rendererData[index];

            if(datarecord)
            {
            console.log("Inside IF==>"+index);
            var imgurl = datarecord.image;
            var img = '<img height="50" width="40" src="' + imgurl + '"/>';
            var table = '<table style="min-width: 130px; height: 70px"><tr><td style="width: 40px;" rowspan="1">' + img + '</td><td>' + datarecord.title + " " + '</td></tr></table>';
            return table;
            }

        }

    });
    $('#subtab1').jqxListBox('refresh');

}

HomePresenter.populateAssetsList = function(data){
    //Converting the div into the jqwidget list with the renderer for that list
    $("#assetDetails").jqxListBox('beginUpdate');
    $("#assetDetails").jqxListBox({ source: data, displayMember: "title", valueMember: "description", width: 200, height: 250,scrollBarSize:20,
        renderer: function (index, label, value) {
            var datarecord = data[index];
            var imgurl = datarecord.image;
            var img = '<img height="30" width="40" src="' + imgurl + '"/>';
            var table = '<table class="assestsJQList" style="min-width: 130px;"><tr><td style="width: 40px;" rowspan="1">' + img + '</td><td>' + datarecord.title +  '</td></tr></table>';
            return table;
        }
    });
    $("#assetDetails").jqxListBox('endUpdate');
    $('#assetDetails').jqxListBox('refresh');

    //This will say that the assets list item needs to be dragged and the drop taget will be assortment panels div
    $(".jqx-listitem-element").jqxDragDrop({ dropTarget: $('#subtab1'), revert: true });
    //This will add all the necessary events for d&d operation
    HomePresenter.addEventListeners();

}

HomePresenter.addEventListeners = function(){

    $('.jqx-listitem-element').bind('dropTargetEnter', function (event) {
        onTarget = true;
        $(event.args.target).css('border', '2px solid #000');
        $(this).jqxDragDrop('dropAction', 'none');
    });
    $('.jqx-listitem-element').bind('dropTargetLeave', function (event) {
        onTarget = false;
        $(event.args.target).css('border', '2px solid #aaa');
        $(this).jqxDragDrop('dropAction', 'copy');
    });

    //Drag End
    $('.jqx-listitem-element').bind('dragEnd', function (event) {

        var existingItems = $("#subtab1").jqxListBox('getItems');
        if(onTarget){
            var exists = HomePresenter.productAlreadyExists(existingItems,event.args.actualData.title);
            /*alert(exists)
             if(!exists){
             /*$("#subtab1").jqxListBox('beginUpdate');*/
            GraphicDataStore.addProdcut(event.args.actualData);//Yet to decide what fields exactly needs to be added to this object
            $("#subtab1").jqxListBox('addItem', event.args.actualData );
           /* var source = $('#subtab1').jqxListBox('source');
            source.push(event.args.actualData)
*/
            /*$("#subtab1").jqxListBox('endUpdate');
             $('#subtab1').jqxListBox('refresh');*/
            $('#subtab1').css('border', '2px dashed #aaa');

            onTarget = false;
            /*}*/
        }

    });
    //Drag Start
    $('.jqx-listitem-element').bind('dragStart', function (event) {
        $('#subtab1').css('border', '2px solid #aaa');
        var items = $("#assetDetails").jqxListBox('getSelectedItems');
        $(this).jqxDragDrop('data', {
            actualData: items[0].originalItem
        });
    });
}

HomePresenter.searchList = function(e){
    console.log(e.currentTarget)
    if(e.keyCode == 13){
        if(currentPanelId == "btnPIM"){
            SearchPimAsset.search(e.currentTarget.value,HomePresenter.populateAssetsList);
        }
        if(currentPanelId == "btnMAM"){
            SearchPimAsset.search(e.currentTarget.value,HomePresenter.populateAssetsList);
        }

    }

}

HomePresenter.productAlreadyExists = function(existingItems,newLabel){
    if(existingItems){
        for(var i=0; i< existingItems.length; i++){
            if(existingItems[i].title === newLabel){
                return true
            }
        }
    }
    return false;
}

HomePresenter.hideAssortPanel = function(){
    $('#assortPanel').hide();
    $('#dim').show();
}

HomePresenter.createProductsJSON = function(){
    var jsonData = {};
    var columnName = "products";
    jsonData[columnName] = GraphicDataStore.getProdcutsArr();
    var columnName = "id";
    jsonData[columnName] = GraphicDataStore.getCurrentAssortment().id;
    UpdateAssortment.update(GraphicDataStore.getCurrentAssortment(),jsonData,HomePresenter.hideAssortPanel);
}

HomePresenter.unHideAssortPanel = function(){
    $("#dim").hide();
    $("#assortPanel").show();
}


var regions = ['Germany','India','USA'];
var targetGroups = ['Men','Women'];
var groupTypes = ['Region','Target Group'];
var assortments = ['Assortment1','Assortment2','Assortment3','Assortment4','Assortment5'];
var masterTemplateList = new Array();


HomePresenter.openURL = function(reference) {
    var urlToOpen = $(reference).children('.url').html();
    var screenParams = [
        'height=' + (screen.height - 100),
        'width=' + (screen.width - 100),
        'fullscreen=yes'
    ].join(',');
    window.open(urlToOpen, '_blank', screenParams); // <- This is what makes it open in a new window.
}


HomePresenter.openWhiteBoard = function (divReference, event) {

    var publicationID =  GraphicDataStore.getCurrentPublication();
    var assortmentID = $(divReference).children('.inner').children('.assortment');
    var templateID = $(divReference).children('.inner').children('.templateName').html();
    CreateWBD.createWBD(publicationID,assortmentID,templateID,function(url){

        $childPage = $(divReference).closest('.childPages');
        $childPage.addClass('urlInjected');
        $childPage.append("<p class='hidden url'>" + url + "</p>");
        $childPage.attr('onclick', "HomePresenter.openURL(this)");
        $childPage.attr('ondblclick', "");
        $childPage.animate({ opacity: 0.5 }, 1200, 'linear');
        $(divReference).children('.inner').children('.loading-overlay').toggle();
        $(divReference).children('.inner').children('.loading-message').toggle();

    });


    /*jQuery.getJSON("http://192.168.135.104/CS13.0Trunk/admin/rest/whiteboard/3/"+ $(divReference).children('.inner').children('.templateName').html(),function(data){
     console.log("WBD created "+data);
     mamFileID = data;
     console.log("Wbd stillWorking : "+stillWorking);
     if(stillWorking){
     stillWorking = false;
     }
     else{
     HomePresenter.createMergeList(mamFileID, json,$(divReference).children('.inner'));
     }
     console.log($(divReference).children('.inner').children('.assortment'))
     jQuery.getJSON("Data/"+$(divReference).children('.inner').children('.assortment').html()+".json",function(data) {
     console.log("Assortment Loaded "+data);
     var json1 = "["
     $.each( data, function( key, val ) {
     //console.log(val)
     json1 +="{\"id\":\"" + val.id +"\"}";
     if(key != data.length-1){
     json1+=","
     }
     })
     json1 +="]"
     //console.log(json1)
     //json = JSON.parse(json1);
     json = json1;
     console.log(json) ;
     console.log("Json stillWorking : "+stillWorking);
     if(stillWorking){
     stillWorking = false;
     console.log("Json stillWorking after : "+stillWorking);
     }
     else{
     HomePresenter.createMergeList(mamFileID, json,$(divReference).children('.inner'));
     }
     });*/
    $(divReference).children('.inner').children('.loading-overlay').toggle();
    $(divReference).children('.inner').children('.loading-message').toggle();
}

HomePresenter.expandPages = function (div, event) {

    var $container = $isotopeContainer;
    if (!$(div).hasClass('opened')) {
        if (!$(div).hasClass('opened')) {
            $(div).children('.expand').html("-");
            var $masterTemplate;
            var $assortment;
            var $itemsToInsert = new Array();
            var $results = $(div).children('.rule').children('.then').children('.thenChild');
            var $size = $results.length;
            if ($size > 0) {
                $(div).toggleClass('opened');
            }
            for (var i = 0; i < $size; i++) {
                $values = $($results[i]).children('.rulesText')
                $masterTemplate = $($results[i]).children('.template')[0].value;
                $assortment = $($results[i]).children('.assortment')[0].value;


                var newDiv = document.createElement("div");
                newDiv.setAttribute('ondblclick', "HomePresenter.openWhiteBoard(this,event)");
                var content = '';

                if ($(div).hasClass('odd')) {
                    $(newDiv).addClass('odd');
                    content += "<div class='childPages inner odd'>";
                }
                else {
                    $(newDiv).addClass('even');
                    content += "<div class='childPages inner even'>";
                }
                content += "<div class='loading-overlay' ondblclick='event.stopPropagation()'></div>" +
                    "<img ondblclick='event.stopPropagation()' src='../../../graphics/screens/home/images/load.gif' " +
                    "class='loading-message'/>"
                $(newDiv).addClass($(div)[0].id);
                $(newDiv).addClass('childPages');

                $dimensionValues = $($results[i]).children('.whenChild').children('.value');
                if ($dimensionValues.length > 0) {
//                        $(newDiv).removeClass('any');
                    for (var j = 0; j < $dimensionValues.length; j++) {
                        if (!$($dimensionValues[j]).hasClass('hidden')) {
                            $(newDiv).addClass($dimensionValues[j].value.toLowerCase());
                        }
                    }
                }
                else {
                    $(newDiv).addClass('any');
                }
                content += "<div class='childPageName' >" +
                    $($results[i]).children('.template').find(":selected").text()[0] + "</div>";
                content += "<p class='data templateName' >" + $masterTemplate + "</p>";
                content += "<p class='data assortment' >" + $assortment + "</p>";
                content += "</div>";
                newDiv.innerHTML = newDiv.innerHTML + content;

                $itemsToInsert[i] = newDiv;
            }
            $container.isotope('insert', $($itemsToInsert), $(div));
        }
    }
    else {

        $(div).children('.expand').html("+");
        $container.isotope('remove', $('.' + $(div)[0].id));
        $(div).toggleClass('opened');
    }
}

/*
HomePresenter.createMergeList = function (mamFileID, json, $loading) {
    jQuery.post("http://192.168.135.104/CS13.0Trunk/admin/rest/whiteboard/4/" + mamFileID, json, function (data){
        console.log("merge list prepared");

    jQuery.get("http://192.168.135.104/CS13.0Trunk/admin/rest/whiteboard/5/" + mamFileID, function (url) {
        $loading.children('.loading-overlay').toggle();
        $loading.children('.loading-message').toggle();
        url = url.replace("../admin", "http://192.168.135.104/CS13.0Trunk/admin");
        console.log(url);
        var screenParams = [
            'height=' + (screen.height - 100),
            'width=' + (screen.width - 100),
            'fullscreen=yes'
        ].join(',');

        window.open(url, '_blank', screenParams); // <- This is what makes it open in a new window.
    });
});

}, "json");



}
*/


HomePresenter.saveRulesData = function (div) {
    var $dirtyFields = $(div).find('.dataDirty');
    var isDirty = getDataDirtyFlag($dirtyFields);
    if (isDirty) {
        var $thenStatements = $(div).children('.rule').children('.then').children('.thenChild');

       var pageRuleArr = [];

        for (var i = 0; i < $thenStatements.length; i++) {

            var ruleResult = {};
            var columnName = "masterPageId";
            var masterPageID = $($thenStatements[i]).children('.template')[0].value;
            ruleResult[columnName] = masterPageID != 'Select' ? masterPageID:'true';
            var columnName = "assortmentId";
            var assortmentID = $($thenStatements[i]).children('.assortment')[0].value;
            ruleResult[columnName] = assortmentID != 'Select' ? assortmentID:'true';


            var condArray = [];
            var $whenConditions = $($thenStatements[i]).children('.whenChild');
            for (var j = 0; j < $whenConditions.length; j++) {
                var condition = {};
                var columnName = "variable";
                var variable = $($whenConditions[j]).children('.groupType')[0].value;
                condition[columnName] = variable != 'Choose' ? variable:'true';
                var columnName = "operator";
                condition[columnName] = $($whenConditions[j]).children('.operation')[0].value;
                var columnName = "value";
                var value = $($whenConditions[j]).children('.value')[0].value;
                condition[columnName] = value != 'Choose' ? value:'true';
                condArray.push(condition)
            }


            var pageRule = {};
            var columnName = "ruleResult";
            pageRule[columnName] = ruleResult;
            var columnName = "ruleConditions";
            pageRule[columnName] = condArray;
            pageRuleArr.push(pageRule)
        }


        var finalJson = {};

        var columnName = "logicalPageID";
        finalJson[columnName] = div.id;
        var columnName = "pageRules";
        finalJson[columnName] = pageRuleArr;

        //Sending Save call
        SavePageRules.save("saveRules",finalJson,HomePresenter.onSaveSuccess);
        GraphicDataStore.addToPageRules(finalJson);

        for (var i = 0; i < $dirtyFields.length; i++) {
            $dirtyFields[i].innerHTML = '0';
        }
    }
    else {
        alert("No changes detected. No save operation performed.");
    }
}

HomePresenter.onSaveSuccess = function(data){
    console.log(data);
}

HomePresenter.toggleRulesView = function (div) {
    $(div).toggleClass('rules-opened');
    $isotopeContainer.isotope('reLayout');
    $(div).children(".open").toggle();
    $(div).children(".rule").toggle();
    $(div).children(".name").toggle();
    $(div).children(".type").toggle();
}


function getDataDirtyFlag($dirtyFields) {
    var isDirty = false;
    for (var i = 0; i < $dirtyFields.length; i++) {
        var checkDirty = ($dirtyFields[i].innerHTML === '1');
        isDirty |= checkDirty;
    }
    return isDirty;
}



HomePresenter.setRules = function(div){

    var dataFromCS = GraphicDataStore.getPageRuleById(div.id);
    var pageRules = dataFromCS.pageRules;
    var $thenReference = $(div).children('.rule').children('.then');
    for (var i = 0; i < pageRules.length; i++) {
        var masterPageId = pageRules[i].ruleResult.masterPageId;
        var assortmentName = pageRules[i].ruleResult.assortmentId;

        /***********Then Div creation***********/
        //var data = ''//Get Data from data store or CS
        var newDiv = document.createElement("div");
        $(newDiv).addClass("thenChild");
        HomePresenter.getMAMFileNames()
        var pageNames = GraphicDataStore.getMasterTemplateList();
        console.log(pageNames)
        var content = "<select onclick='event.stopPropagation()' onchange='HomePresenter.makeDirty(this.parentNode)' " +
                "class='rulesText template'><option selected='selected' disabled='disabled'>Select</option>";
        for (var j = 0; j < pageNames.length; j++) {
           content += "<option value='" + pageNames[j].templateID + "'>" + pageNames[j].templateName + "</option>";
        }
        content += "</select>";
        newDiv.innerHTML = newDiv.innerHTML + content;

        var assortmentList = assortments;
        content = "<select onchange='HomePresenter.makeDirty(this.parentNode)' onclick='event.stopPropagation()' " +
            "class='rulesText assortment'><option selected='selected' disabled='disabled'>Select</option>";
        for(var j = 0 ; j < assortmentList.length ; j++){
            content += "<option>"+ assortmentList[j] +"</option>";
        }
        content += "</select>";
        newDiv.innerHTML = newDiv.innerHTML + content;

        content = "<span class='buttons remove' onclick='HomePresenter.removeNew(this.parentNode,event)'>-</span>"
        newDiv.innerHTML = newDiv.innerHTML + content;
        content = "<span class='buttons addCondition' onclick='HomePresenter.newWhen(this.parentNode,event)'>+</span>"
        newDiv.innerHTML = newDiv.innerHTML + content;
        content = "<p class='hidden dataDirty'>0</p>"
        newDiv.innerHTML = newDiv.innerHTML + content;

        $thenReference.append(newDiv);
        /*****************Setting drop down values************************/
        $(newDiv).children('.template').val(masterPageId);
        $(newDiv).children('.assortment').val(assortmentName);


            var ruleConditions = pageRules[i].ruleConditions;

            for (var j = 0; j < ruleConditions.length; j++) {
                var groupType = ruleConditions[j].variable;
                var operation = ruleConditions[j].operator;
                var value = ruleConditions[j].value;

                /***********when Div creation***********/

                var whenDiv = document.createElement("div");
                $(whenDiv).addClass("whenChild");

                var variablesList = groupTypes;
                content = "<select onchange='HomePresenter.toggleRegionTargetGroup(this)' " +
                    "onclick='event.stopPropagation()' class='rulesText groupType'>" +
                    "<option selected='selected' disabled='disabled' value='-1'>Choose</option>";
                for(var k = 0 ; k < variablesList.length ; k++){
                    content += "<option>" + variablesList[k] + "</option>";
                }
                content += "</select>";
                whenDiv.innerHTML = whenDiv.innerHTML + content;

                content = "<select onchange='HomePresenter.makeDirty(this.parentNode)' " +
                    "onclick='event.stopPropagation()' class='rulesText operation'>" +
                    "<option selected='selected'>=</option><option><=</option><option>>=</option></select>";
                whenDiv.innerHTML = whenDiv.innerHTML + content;

                content = "<select onclick='event.stopPropagation()' " +
                    "onchange='HomePresenter.addValue(this,event)'  class='input rulesText value' type='text'>" +
                    "<option selected='selected' disabled='disabled' value='-1'>Choose</option></select>";
                whenDiv.innerHTML = whenDiv.innerHTML + content;

                var content = "&nbsp;&nbsp;<span class='buttons remove' " +
                    "onclick='HomePresenter.removeNew(this.parentNode,event)'>-</span>";
                whenDiv.innerHTML = whenDiv.innerHTML + content;
                content = "<p class='hidden dataDirty'>0</p>"
                whenDiv.innerHTML = whenDiv.innerHTML + content;

                newDiv.appendChild(whenDiv);

                /******************Setting dropdown Values****************************/
                $(whenDiv).children('.groupType').val(groupType);
                HomePresenter.toggleRegionTargetGroup($(whenDiv).children('.groupType')[0],false)
                $(whenDiv).children('.operation').val(operation);
                $(whenDiv).children('.value').val(value);
            }

    }


}


HomePresenter.openRules = function (div, event) {

    if (!$(div).hasClass('opened')) {
        if ($(div).hasClass('rules-opened')) {
            var $dirtyFields = $(div).find('.dataDirty');
            var isDirty = getDataDirtyFlag($dirtyFields);
            if (isDirty) {
                $closeButton = $(div).find('.close');
                $(function () {
                    $("#dialog-confirm").dialog({
                        resizable: false,
                        height: 140,
                        modal: true,
                        buttons: {
                            "Save": function () {
                                $(this).dialog("close");
                                HomePresenter.saveRulesData(div);
                                var $thenChildren = $(div).children('.rule').children('.then').children('.thenChild');
                                if ($thenChildren.length > 0) {
                                    if ($(div).children(".expand").css('display') == 'none') {
                                        $(div).children(".expand").toggle();
                                    }
                                }
                                HomePresenter.toggleRulesView(div);
                            },
                            "Discard": function () {
                                $(this).dialog("close");
                                for (var i = 0; i < $dirtyFields.length; i++) {
                                    $dirtyFields[i].innerHTML = '0';
                                }
                                var $thenChildren = $(div).children('.rule').children('.then').children('.thenChild');
                                if ($thenChildren.length > 0) {
                                    if ($(div).children(".expand").css('display') == 'none') {
                                        $(div).children(".expand").toggle();
                                    }
                                }
                                HomePresenter.toggleRulesView(div);
                            },
                            "Cancel": function () {
                                $(this).dialog("close");
                            }
                        }
                    });
                });
            } else {
                var $thenChildren = $(div).children('.rule').children('.then').children('.thenChild');
                if ($thenChildren.length > 0) {
                    if ($(div).children(".expand").css('display') == 'none') {
                        $(div).children(".expand").toggle();
                    }
                }
                HomePresenter.toggleRulesView(div);
            }
        }
        else {
            if(GraphicDataStore.getPageRuleById(div.id)!=null){
                if(GraphicDataStore.getPageRuleById(div.id).pageRules.length < 1){
                    GetPageRules.get(div.id,function(data){
                        if(data != 'error'){
                            GraphicDataStore.addToPageRules(data);
                            HomePresenter.setRules(div);
                        }
                    });
                }
                else{
                    HomePresenter.setRules(div);
                }
            }
            else{
                GetPageRules.get(div.id,function(data){
                    if(data != 'error'){
                        GraphicDataStore.addToPageRules(data);
                        HomePresenter.setRules(div);
                    }
                });
            }
            if ($(div).children(".expand").css('display') == 'block') {
                $(div).children(".expand").toggle();
            }
            HomePresenter.toggleRulesView(div);
        }
    }
    else {
        HomePresenter.expandPages(div, event);
        $(div).children(".expand").toggle();
        HomePresenter.toggleRulesView(div);

    }
}


HomePresenter.addValue = function (text, event) {
    //get all values inside div
    $(text.parentNode).children('.dataDirty').html('1');
    var $values = $(text).closest('.then').children('.thenChild').children('.whenChild').children('.value');
    var dimension = $(text).siblings('.groupType')[0].value;
    var value = text.value;
    $parentDiv = $(text).closest('.rules-opened');
    var isOdd = $(text).closest('.rules-opened').hasClass('odd');
    var $filterClasses = '';
    for (var i = 0; i < $values.length; i++) {
        if (!$($values[i]).hasClass('hidden')) {
            $filterClasses = $filterClasses + ' ' + $values[i].value.toLowerCase();
        }
    }
    var basicClasses;
    if (isOdd) {
        basicClasses = 'masterPage odd large any';
    }
    else {
        basicClasses = 'masterPage even large any';
    }
    var $newClasses = basicClasses + ' ' + $filterClasses;
    $parentDiv.removeClass().addClass(basicClasses + ' ' + $filterClasses);
}

HomePresenter.toggleRegionTargetGroup = function (toggle,makeDirty) {
    if(makeDirty)
        $(toggle.parentNode).children('.dataDirty').html('1');
    var options = "<option disable='disabled' value='-1'>Select</option>";
    if (toggle.selectedIndex == 1) {
        var regionsList = regions;
        for(var i = 0 ; i < regionsList.length ; i++){
            options += "<option>"+ regionsList[i] +"</option>";
        }
    }
    else if (toggle.selectedIndex == 2) {
        var targetGroupsList = targetGroups;
        for(var i = 0 ; i < targetGroupsList.length ; i++){
            options += "<option>"+ targetGroupsList[i] +"</option>";
        }
    }
    $(toggle).siblings('.value').html(options);
}

HomePresenter.makeDirty = function (reference) {
    $(reference).children('.dataDirty').html('1');
}


HomePresenter.newWhen = function (reference, event) {

    $(reference).children('.dataDirty').html('1');
    //Set dirty flag to the then child

    var newDiv = document.createElement("div");
    $(newDiv).addClass("whenChild");

    var variablesList = groupTypes;
    content = "&nbsp;&nbsp;<select onchange='HomePresenter.toggleRegionTargetGroup(this,true)' " +
        "onclick='event.stopPropagation()' class='rulesText groupType' value='-1'>" +
        "<option selected='selected' disabled='disabled'>Choose</option>";
    for(var i = 0 ; i < variablesList.length ; i++){
        content += "<option>" + variablesList[i] + "</option>";
    }
        content += "</select>";
    newDiv.innerHTML = newDiv.innerHTML + content;

    content = "<select onchange='HomePresenter.makeDirty(this.parentNode)' " +
        "onclick='event.stopPropagation()' class='rulesText operation'><option selected='selected'>=</option>" +
        "<option><=</option><option>>=</option></select>";
    newDiv.innerHTML = newDiv.innerHTML + content;

    content = "<select onclick='event.stopPropagation()' " +
        "onchange='HomePresenter.addValue(this,event)'  class='input rulesText value' type='text'>" +
        "<option selected='selected' disabled='disabled' value='-1'>Choose</option></select>";
    newDiv.innerHTML = newDiv.innerHTML + content;

    var content = "&nbsp;&nbsp;<span class='buttons remove' " +
        "onclick='HomePresenter.removeNew(this.parentNode,event)'>-</span>";
    newDiv.innerHTML = newDiv.innerHTML + content;

    content = "<p class='hidden dataDirty'>0</p>"
    newDiv.innerHTML = newDiv.innerHTML + content;

    reference.appendChild(newDiv);
}

HomePresenter.getMAMFileNames = function () {
    if(!GraphicDataStore.getMasterTemplateList()){
        GetMasterTemplateList.get(function(data){
            GraphicDataStore.setMasterTemplateList(data);
            console.log(GraphicDataStore.getMasterTemplateList());
        });
    }
}


HomePresenter.newThen = function (reference, data) {
    $(reference).children('.dataDirty').html('1');
    var newDiv = document.createElement("div");
    $(newDiv).addClass("thenChild");
    var pageNames = HomePresenter.getMAMFileNames();


    var content = "<select onclick='event.stopPropagation()' onchange='HomePresenter.makeDirty(this.parentNode)' " +
        "class='rulesText template'><option selected='selected' disabled='disabled' value='-1'>Select</option>";
    for (var i = 0; i < pageNames.length; i++) {
        content += "<option value=" + pageNames[i].templateID + ">" + pageNames[i].templateName + "</option>";
    }
    content += "</select>";
    newDiv.innerHTML = newDiv.innerHTML + content;

    var assortmentList = assortments;
    content = "<select onchange='HomePresenter.makeDirty(this.parentNode)' onclick='event.stopPropagation()' " +
        "class='rulesText assortment'><option selected='selected' disabled='disabled' value='-1'>Select</option>";
    for(var i = 0 ; i < assortmentList.length ; i++){
        content += "<option>"+ assortmentList[i] +"</option>";
    }
    content += "</select>";
    newDiv.innerHTML = newDiv.innerHTML + content;

    content = "<span class='buttons remove' onclick='HomePresenter.removeNew(this.parentNode,event)'>-</span>"
    newDiv.innerHTML = newDiv.innerHTML + content;
    content = "<span class='buttons addCondition' onclick='HomePresenter.newWhen(this.parentNode,event)'>+</span>"
    newDiv.innerHTML = newDiv.innerHTML + content;
    content = "<p class='hidden dataDirty'>0</p>"
    newDiv.innerHTML = newDiv.innerHTML + content;
    reference.appendChild(newDiv);
}


HomePresenter.removeNew = function (reference, event) {
    $(reference).children('.dataDirty').html('1');
    $(reference.parentNode).children('.dataDirty').html('1');
    if ($(reference).hasClass('whenChild')) {
        $values = $(reference).find('.value');
        console.log($values[0].value);
        $(reference).closest('.rules-opened').removeClass($values[0].value.toLowerCase());
    }
    else if ($(reference).hasClass('thenChild')) {
        $values = $(reference).find('.value');
        console.log($values.length)
        for (var i = 0; i < $values.length; i++) {
            console.log($values[i].value)
            $(reference).closest('.rules-opened').removeClass($values[i].value.toLowerCase());
        }
    }
    reference.parentNode.removeChild(reference);
    event.stopPropagation();
    return false;
}

HomePresenter.edit = function (tagsClass, tagNo, reference, event) {
    console.log(reference.parentNode)
    var elements = reference.parentNode.getElementsByClassName(tagsClass);
    for (var i = 0; i < elements.length; i++) {
        var element = elements[i];
        console.log(element);
        element.disabled = false;
    }
    var okButtons = reference.parentNode.getElementsByClassName("done");
    for (var i = 0; i < okButtons.length; i++) {
        okButtons[i].style.visibility = 'visible';
    }
    var editButtons = reference.parentNode.getElementsByClassName("edit");
    for (var i = 0; i < okButtons.length; i++) {
        editButtons[i].style.visibility = 'hidden';
    }
    event.stopPropagation();
}

HomePresenter.done = function (tagsClass, tagNo, reference, event) {
    var elements = reference.parentNode.getElementsByClassName(tagsClass);
    for (var i = 0; i < elements.length; i++) {
        var element = elements[i];
        console.log(element);
        element.disabled = true;
    }
    var okButtons = reference.parentNode.getElementsByClassName("done");
    for (var i = 0; i < okButtons.length; i++) {
        okButtons[i].style.visibility = 'hidden';
    }
    var editButtons = reference.parentNode.getElementsByClassName("edit");
    for (var i = 0; i < okButtons.length; i++) {
        editButtons[i].style.visibility = 'visible';
    }
    event.stopPropagation();
}
