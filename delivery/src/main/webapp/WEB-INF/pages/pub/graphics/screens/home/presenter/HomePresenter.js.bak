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
                ref.typeID = "P" + index + 1;
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
var stillWorking = true;
var mamFileID;
var json


HomePresenter.openWhiteBoard = function(divReference,event){


    jQuery.getJSON("http://192.168.135.104/CS13.0Trunk/admin/rest/whiteboard/3/"+ $(divReference).children('.inner').children('.templateName').html(),function(data){
        console.log("WBD created "+data);
        mamFileID = data;
        console.log("Wbd stillWorking : "+stillWorking);
        if(stillWorking){
            stillWorking = false;
        }
        else{


            HomePresenter.createMergeList(mamFileID, json,$(divReference).children('.inner'));
        }
    });
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
    });
    $(divReference).children('.inner').children('.loading-overlay').toggle();
    $(divReference).children('.inner').children('.loading-message').toggle();


}

HomePresenter.expandPages = function(div, event) {
    var $container = $isotopeContainer;
    if (!$(div).hasClass('large') && !$(div).hasClass('childPages')) {

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
                $masterTemplate = $values[0].value;
                $assortment = $values[1].value;


                var newDiv = document.createElement("div");
                newDiv.setAttribute('ondblclick',"HomePresenter.openWhiteBoard(this,event)");
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
                    "<img ondblclick='event.stopPropagation()' src='../../../graphics/screens/home/images/load.gif' class='loading-message'/>"
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
                content += "<div class='childPageName' >" + $($values[0]).find(":selected").text() + "</div>";
                content += "<p class='data templateName' >" + $masterTemplate + "</p>";
                content += "<p class='data assortment' >" + $assortment + "</p>";
                content += "</div>";
                newDiv.innerHTML = newDiv.innerHTML + content;

                $itemsToInsert[i] = newDiv;
                /*$(div).addClass('isotope-hidden');
                 //opacity: 0, scale: 0.001
                 $(div).css('opacity','0');
                 $(div).css('scale','0.001');
                 $container.isotope('reLayout');*/
            }
            $container.isotope('insert', $($itemsToInsert), $(div));
        }
        else {
            $(div).children('.expand').html("+");
            $container.isotope('remove', $('.' + $(div)[0].id));
            $(div).toggleClass('opened');
        }

    }
}

HomePresenter.createMergeList = function(mamFileID, json, $loading) {
    console.log(json);
    jQuery.post("http://192.168.135.104/CS13.0Trunk/admin/rest/whiteboard/4/" + mamFileID, json, function (data) {
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

    }, "json");


}



HomePresenter.openRules = function(div, event) {

    if (!$(div).hasClass('childPages') && !$(div).hasClass('opened')) {
        $(div).toggleClass('rules-opened');
        $isotopeContainer.isotope('reLayout');
        $(div).children(".open").toggle();
        $(div).children(".rule").toggle();
        $(div).children(".name").toggle();
        $(div).children(".type").toggle();
        var $thenChildren = $(div).children('.rule').children('.then').children('.thenChild');
        if ($thenChildren.length > 0) {
            $(div).children(".expand").toggle();
        }

    }
    else if ($(div).hasClass('opened')) {
        HomePresenter.expandPages(div, event);
        $(div).toggleClass('rules-opened');
        $isotopeContainer.isotope('reLayout');
        $(div).children(".expand").toggle();
        $(div).children(".open").toggle();
        $(div).children(".rule").toggle();
        $(div).children(".name").toggle();
        $(div).children(".type").toggle();

    }
}


HomePresenter.addValue = function(text, event) {
    //get all values inside div
    var $values = $(text).closest('.then').children('.thenChild').children('.whenChild').children('.value');
    var dimension = $(text).siblings('.groupType')[0].value;
    var value = text.value;
    $parentDiv = $(text).closest('.large');
    var isOdd = $(text).closest('.large').hasClass('odd');
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

HomePresenter.toggleRegionTargetGroup = function(toggle) {
    if (toggle.selectedIndex == 1) {
        var options = "<option disable='disabled'>Select Region</option><option>Germany</option><option>India</option><option>USA</option>";
        $(toggle).siblings('.value').html(options);
    }
    else if (toggle.selectedIndex == 2) {
        var options = "<option disable='disabled'>Select Group</option><option>Men</option><option>Women</option>";
        $(toggle).siblings('.value').html(options);
    }
}


HomePresenter.newWhen = function (reference, event) {
    var newDiv = document.createElement("div");
    $(newDiv).addClass("whenChild");
    content = "&nbsp;&nbsp;<select onchange='HomePresenter.toggleRegionTargetGroup(this)' onclick='event.stopPropagation()' class='rulesText groupType'><option selected='selected' disabled='disabled'>Choose</option><option>Region</option><option>TargetGroup</option></select>";
    newDiv.innerHTML = newDiv.innerHTML + content;
    content = "<select onclick='event.stopPropagation()' class='rulesText operation" + reference.id + "><option selected='selected'>=</option><option><=</option><option>>=</option></select>";
    newDiv.innerHTML = newDiv.innerHTML + content;
    content = "<select onclick='event.stopPropagation()' onchange='HomePresenter.addValue(this,event)'  class='input rulesText value' type='text'><option selected='selected' disabled='disabled'>Choose</option></select>";
    newDiv.innerHTML = newDiv.innerHTML + content;
    var content = "&nbsp;&nbsp;<span class='buttons remove' onclick='HomePresenter.removeNew(this.parentNode,event)'>-</span>";
    newDiv.innerHTML = newDiv.innerHTML + content;
    reference.appendChild(newDiv);
    event.stopPropagation();
}

HomePresenter.getMAMFileNames = function (reference) {
   // var data = new Array(new Object({templateID: '1458', templateName: 'CSLive.All.indd'}), new Object({templateID: '2', templateName: 'Template2'}),
     //new Object({templateID: '3', templateName: 'Template3'}));
   /* jQuery.getJSON("http://192.168.135.104/CS13.0Trunk/admin/rest/whiteboard/2?callback=?",{},function( data ) {
        console.log(data)
        HomePresenter.fillMasterTemplateDropDown(reference, data);
    }).error(function(jqXHR, textStatus, errorThrown) { // Debug the error!!
            console.log("error " + textStatus);
            alert(123)
            console.log("error throw " + errorThrown);
            console.log(jqXHR);
        });*/

    /*jQuery.getJSON("http://192.168.135.104/CS13.0Trunk/admin/rest/whiteboard/2").done(
        function(data){
alert(data)
        })
        .fail(function(data){
            alert(JSON.stringify(data));
        });*/

     jQuery.ajax({
     url:"http://192.168.135.104/CS13.0Trunk/admin/rest/pim/list/",
     type:'GET',
         dataType:"json" ,
     success: function( data ) {
         alert(JSON.stringify(data))
     /*console.log(data);
      HomePresenter.fillMasterTemplateDropDown(reference, data);*/
     }
     });
}


HomePresenter.fillMasterTemplateDropDown = function(reference, data) {
    var newDiv = document.createElement("div");
    $(newDiv).addClass("thenChild");
    var pageNames = data;


    var content = "<select onclick='event.stopPropagation()' class='rulesText' id='template'" + reference.id + "><option selected='selected' disabled='disabled'>Select</option>";
    for (var i = 0; i < pageNames.length; i++) {
        content += "<option value=" + pageNames[i].templateID + ">" + pageNames[i].templateName + "</option>";
    }
    content += "</select>";
    newDiv.innerHTML = newDiv.innerHTML + content;
    content = "<select onclick='event.stopPropagation()' class='rulesText' id='assortment'" + reference.id + "><option selected='selected'>Assortment1</option><option>Assortment2</option><option>Assortment3</option><option>Assortment4</option><option>Assortment5</option></select>";
    newDiv.innerHTML = newDiv.innerHTML + content;
    content = "<span class='buttons remove' onclick='HomePresenter.removeNew(this.parentNode,event)'>-</span>"
    newDiv.innerHTML = newDiv.innerHTML + content;
    content = "<span class='buttons addCondition' onclick='HomePresenter.newWhen(this.parentNode,event)'>+</span>"
    newDiv.innerHTML = newDiv.innerHTML + content;
    reference.appendChild(newDiv);
}
HomePresenter.newThen = function (reference, event) {

    HomePresenter.getMAMFileNames(reference);

}


HomePresenter.removeNew = function (reference, event) {
    if ($(reference).hasClass('whenChild')) {
        $values = $(reference).find('.value');
        console.log($values[0].value);
        $(reference).closest('.large').removeClass($values[0].value.toLowerCase());
    }
    else if ($(reference).hasClass('thenChild')) {
        $values = $(reference).find('.value');
        console.log($values.length)
        for (var i = 0; i < $values.length; i++) {
            console.log($values[i].value)
            $(reference).closest('.large').removeClass($values[i].value.toLowerCase());
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
