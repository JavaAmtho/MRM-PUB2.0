var GanttChart = function(){

    var currentPath;
    var parentNode;
    var newNode
    var currentRow;
    var dropTargetType

    var input;
    var cellOldValue;



    this.createGanttChart = function(id){
        TreeGrid({Layout:{Url:EngineDataStore.getBaseURL()+"graphics/screens/home/scripts/inHouseScripts/js/Def_temp.xml"},
        Data:{Script:"myData"}},id);
    }



    Grids.OnGanttStart = function(G){

        setTimeout(function() {
            $( "#mrmGanttChart" ).resizable({
                maxHeight: 400,
                minHeight: 150
            });

            HomePresenter.viewTreeAndGantt();

        }, 1000);



    }


    Grids.OnGetMenu = function(G,row,col){
        var possibleDim=[];
        possibleDim  = GraphicDataStore.getPossibleChild(row.type);
        var menuItems = [];
        var menu = {Items:menuItems};

        if(possibleDim != ""){
            for(var i=0; i< possibleDim.length; i++){
                //This is to create possible dimensions on click of currentNode
                var item = {};
                var keyName = "Name";
                item[keyName] = possibleDim[i];
                var keyName = "Text";
                item[keyName] = "Create "+possibleDim[i];
                menuItems.push(item);
            }

        }

        if(row.type != "root"){
            //This is to delete the currentNode
            var deleteItem = {};
            var keyName = "Name";
            deleteItem[keyName] = "Delete";
            var keyName = "Text";
            deleteItem[keyName] = "Delete";
            menuItems.push(deleteItem);
        }
        return menu;
    }

    Grids.OnGanttMenu = function(grid,row,col,Menu,GanttXY) {
        return true;
    }

    GanttChart.onDeleteSuccess=function(){
        Grids[0].DeleteRow(currentRow,2);
    }

    Grids.OnContextMenu = function(G,row,col,name){
        currentRow = row;
        if(name == "Delete"){

            var r=confirm("Are you sure you want to delete "+ row.name+" ?");
            if (r==true)            {
                var input=new Object();
                input.id=row.id;
                input.name=row.name;
                input.type=row.type;
                input.groupId=row.groupId;
                var prefix=getUrlPrefix(row.type,"delete");
                GanttChartPresenter.deleteDimension(prefix,row.type,input,GanttChart.onDeleteSuccess);
            }

        }else{
             showPopUp(G,row,col,name);
        }
    }

    Grids.OnCanRowDelete = function(grid,row,type){
        if(type==1){
            var r=confirm("Are you sure you want to delete "+ row.name+" ?");
            if (r==true)            {
                var input=new Object();
                input.id=row.id;
                input.name=row.name;
                input.type=row.type;
                input.groupId=row.groupId;
                var prefix=getUrlPrefix(row.type,"delete");
                GanttChartPresenter.deleteDimension(prefix,row.type,input,GanttChart.onDeleteSuccess);
                return 2;
            }
        }
        if(type == 2){
            return 2;
        }
    }


    Grids.OnStartDrag = function(grid,row,col){
        //To suppress the dragging as per the dimension type
        if(row.type == "root") {
            return true;
        }
        return false;
    }

    Grids.OnCanDrop = function(grid,row,togrid,torow,type,copy){
        dropTargetType = GraphicDataStore.getPossibleDropParent(row.type);
        var dropTargetFound = $.inArray(torow.type, dropTargetType)
        if(dropTargetFound != -1){
            return 2;
        }
        return 0;

    }

    Grids.OnAfterValueChanged = function(grid,row,col,val){
        //cellOldValue = row[col];
        if(row.id != "AR1"){
            var prefix;
            prefix =getUrlPrefix(row.type,"update");
            GanttChartPresenter.updateDimension(prefix,row,GanttChartPresenter.onUpdate)
            //Make an api call with val
        }else{
        }

    }

     Grids.OnExpand = function(grid,row){
        /* if(row.Level === 5){
             var arr = [];
             var length =  row.childNodes.length;
             if(length>0){
                 arr.push(row.firstChild);
                 var currentRow = row.firstChild;
                 for(var i=1; i< length-1; i++){
                     var nextRow = Grids[0].GetNext(currentRow);
                     arr.push(nextRow);
                     currentRow = nextRow;
                 }
                 if(length!=1){
                     arr.push(row.lastChild);
                 }
             }
             HomePresenter.createFlow(arr);
             return true;
         }*/
     }

    Grids.OnGetGanttHtml = function(G,row,col,width,comp,crit){
        switch(row.type){
            case "MarketingInitiative":
                Grids[0].SetValue(row,"nameIcon","cal1.png",1);
                return "<div style=\"background: #6e6e6e;color:white;padding:2px; height: 11px; border:1px solid #6e6e6e; border-radius: 4px;  box-shadow: 2px 2px 3px rgba(255, 255, 255, .9) inset; \"></div>";
            case "Campaign":
                Grids[0].SetValue(row,"nameIcon","cal2.png",1);
                return "<div style=\"background: #7a8b8b;color:white;padding:2px; height: 11px; border:1px solid  #7a8b8b ; border-radius: 4px;  box-shadow: 2px 2px 3px rgba(255, 255, 255, .9) inset\"></div>";
            case "SubCampaign":
                Grids[0].SetValue(row,"nameIcon","cal1.png",1);
                return "<div style=\"background: #b4cdcd;color:white;padding:2px; height: 11px; border:1px solid #b4cdcd; border-radius: 4px;  box-shadow: 2px 2px 3px rgba(255, 255, 255, .9) inset\"></div>";
            case "CommunicationPlan":
                Grids[0].SetValue(row,"nameIcon","cal2.png",1);
                return "<div style=\"background: #b0e0e6;color:white;padding:2px; height: 11px; border:1px solid #b0e0e6; border-radius: 4px;  box-shadow: 2px 2px 3px rgba(255, 255, 255, .9) inset\"></div>";
            case "CommunicationChannel":
                Grids[0].SetValue(row,"nameIcon","cal2.png",1);
                return "<div style=\"background: #60affe;color:white;padding:2px; height: 11px; border:1px solid #60affe; border-radius: 4px;  box-shadow: 2px 2px 3px rgba(255, 255, 255, .9) inset\"></div>";
            default:
                return null;
        }

    }

    Grids.OnGanttMenu = function(grid,row,col,Menu,GanttXY) {
        return true;
    }

    Grids.OnEndDrag = function(grid,row,togrid,torow,type,X,Y,copy){
       if(type === 2){
           var oldPathForChild = row.path;
           var newChildNode = row;
           var parentNode = torow;
           newChildNode.path = parentNode.path +","+parentNode.title;
           var newPathForChild = newChildNode.path;

           if(newPathForChild.indexOf("-1")==0)
               newPathForChild = newPathForChild.match(/([^,]*),(.*)/)[2];   //To remove -1 root folder

           var flag=isFolder(newChildNode.type);
           var prefix;
           prefix =getUrlPrefix(row.type,"move");
           prefix = prefix+row.type;
           GanttChartPresenter.dragAndDropDimensions(prefix,row,oldPathForChild,flag,newPathForChild,onDropSuccess);

       }
    }

    //SetEvent("OnClick","g1",function(){ alert("G1 clicked");} );

    Grids.OnClick = function(grid,row,col,x,y){

        if(row.id != "Header"){
            if(col === "name"){
                if(row.type === "CommunicationChannel"){
                    //Call to server to get the publications of this Communication Channel
                    GanttChartPresenter.getPublications(row,Grids.onPublicationHandler);
                }
                else{
                    HomePresenter.hideCoverflow();
                }
            }
            if(col != "ganttChart") {
                Grids[0].ScrollToDate(row.startDate,"Center");
            }
        }
    }

    Grids.onPublicationHandler = function(data){
        HomePresenter.createFlow(data);
    }

    Grids.OnEndDragGantt = function(grid, row, col, name, start, end,
        oldstart, oldend, dir, XY, keyprefix, clientX, clientY, ToRow){

            row.startDate = start;
            row.endDate = end;
            var prefix;
            prefix =getUrlPrefix(row.type,"update");
            GanttChartPresenter.updateDimension(prefix,row,GanttChartPresenter.onUpdate)
    }


    GanttChartPresenter.onUpdate = function(data){
        //alert(JSON.stringify(data))
    }



    function onDropSuccess(){
       /* if(draggedNode.data.type == "Assortment"){
            var cb = draggedNode.toDict(true, function(dict){
                //dict.title = "Copy of " + dict.title;
                delete dict.key; // Remove key, so a new one will be created
            });
            droppedSrcNode.addChild(cb);
        }
        else{
            draggedNode.move(droppedSrcNode, dragHitMode);
        }*/
    }

    function addNode(data){
        Grids[0].AddRow(currentRow,null,1);
        Grids[0].SetValue(currentRow.lastChild,"name",data.name,1);
        Grids[0].SetValue(currentRow.lastChild,"title",data.title,1);
        Grids[0].SetValue(currentRow.lastChild,"path",data.path,1);
        Grids[0].SetValue(currentRow.lastChild,"id",data.id,1);
        Grids[0].SetValue(currentRow.lastChild,"groupId",data.groupId,1);
        Grids[0].SetValue(currentRow.lastChild,"type",data.type,1);
        Grids[0].SetValue(currentRow.lastChild,"budgetOwner",data.budgetOwner,1);
        Grids[0].SetValue(currentRow.lastChild,"budget",data.budget,1);
        Grids[0].SetValue(currentRow.lastChild,"startDate",data.startDate,1);
        Grids[0].SetValue(currentRow.lastChild,"endDate",data.endDate,1);
        Grids[0].SetValue(currentRow.lastChild,"manager",data.manager,1);
        Grids[0].SetValue(currentRow.lastChild,"Items",data.Items,1);
        switch(data.type){
            case "MarketingInitiative":
                Grids[0].SetValue(currentRow.lastChild,"nameIcon","cal1.png",1);
                break;
            case "Campaign":
                Grids[0].SetValue(currentRow.lastChild,"nameIcon","cal1.png",1);
                break;
            case "SubCampaign":
                Grids[0].SetValue(currentRow.lastChild,"nameIcon","cal1.png",1);
                break;
            case "CommunicationPlan":
                Grids[0].SetValue(currentRow.lastChild,"nameIcon","cal2.png",1);
                break;
            case "CommunicationChannel":
                Grids[0].SetValue(currentRow.lastChild,"nameIcon","cal2.png",1);
                break;
            default:
                Grids[0].SetValue(currentRow.lastChild,"nameIcon","cal2.png",1);
                break;
        }

        Grids[0].ScrollToDate(data.startDate,"Left");
         //Grids[0].Recalculate(currentRow,"startDate",1);
    }

    function checkRegexp( o, regexp, n ) {
        if ( !( regexp.test( o.val() ) ) ) {
            o.addClass( "ui-state-error" );
            updateTips( n );
            return false;
        } else {
            return true;
        }
    }
    function updateTips( t ) {
        tips
            .text( t )
            .addClass( "ui-state-highlight" );
        setTimeout(function() {
            tips.removeClass( "ui-state-highlight", 1500 );
        }, 500 );
    }

    function checkNull(obj){
        if(obj.val()==""){
            obj.addClass( "ui-state-error") ;
            return false;
        }
        else{
            obj.removeClass("ui-state-error")   ;
            return true;
        }
    }
    var ck_alpha = /^[A-Za-z]+$/;
    function checkAlpha(obj){
        console.log(obj.val());
        if (!ck_alpha.test(obj.val())) {
            obj.addClass( "ui-state-error") ;
           console.log('false')
           return false;
        }
        else{
            obj.removeClass("ui-state-error")   ;
            return true;
            console.log('true')
        }
    }




    var ck_date = /^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$/;
    function checkDate(obj){
        console.log(obj.val());
        if (!ck_date.test(obj.val())) {
            obj.addClass( "ui-state-error") ;
            console.log('false')
            return false;
        }
        else{
            obj.removeClass("ui-state-error")   ;
            return true;
            console.log('true')
        }
    }



    function showPopUp(G,row,col,name){
        $( "#dialog-form" ).dialog({
            height: 490,
            width: 500,
            modal: true,
            resizable: false,
            show: {
                effect: "clip",
                duration: 500
            },
            hide: {
                effect: "clip",
                duration: 500
            },
            buttons: {
            "Create ": function() {

                var errorMsg = "";
                var popupValid = true;
                var bValid = true;
                var dimensionName = $( "#name" ),
                    manager = $( "#manager" ),
                    startdate = $( "#startdate" ),
                    enddate = $( "#enddate" ),
                    budgetowner = $( "#budgetOwner" ),
                    budgetamount = $( "#budget"),
                    currency = $( "#currency" );



              /*  if((dimensionName.val()=="") || (manager.val()=="") || (startdate.val()=="") || (enddate.val()=="") || (budgetowner.val()=="") || (budgetamount.val()=="")) {
                    errorMsg += "Fields should not be EMPTY!";
                    popupValid =  false;
                }*/

                popupValid = checkNull(dimensionName);
                popupValid = popupValid && checkNull(manager);
                popupValid = popupValid && checkNull(startdate);
                popupValid = popupValid && checkNull(enddate);
                popupValid = popupValid && checkNull(budgetowner);
                popupValid = popupValid && checkNull(budgetamount);

                popupValid &= checkAlpha(manager);
                popupValid &= checkAlpha(budgetowner);

                popupValid &= checkDate(startdate);
                popupValid &= checkDate(enddate);
                if(!popupValid){
                    errorMsg += "Fields should not be EMPTY!";
                }

                if(startdate.val()>enddate.val()){
                    errorMsg += "Please verify start and end dates!";
                    popupValid =  false;
                    startdate.addClass( "ui-state-error") ;
                    enddate.addClass( "ui-state-error") ;
                }
                if(!$.isNumeric( budgetamount.val() )){
                    errorMsg += "Please verify budget!";
                    popupValid =  false;
                    budgetamount.addClass( "ui-state-error") ;
                }
                updateTips(errorMsg);

                if(popupValid == true){


                input = new Object();
                input.name=dimensionName.val();
                input.managerName=manager.val();
                input.startDate=startdate.val();
                input.endDate=enddate.val();
                input.budgetOwner = budgetowner.val();
                if(budgetamount.val() != "")
                input.budget = budgetamount.val() + " " + currency.val();
                input.type = name;
                input.Items = [];
                if(input.name != null && input.name !=""){
                    parentNode = row;
                    if(parentNode.type == "root"){
                        currentPath = "-1";
                    }
                    else{
                        currentPath = parentNode.path+","+ parentNode.title;
                        if(currentPath.indexOf("-1")==0)
                            currentPath = currentPath.match(/([^,]*),(.*)/)[2];   //To remove -1 root folder
                    }

                    input.path=currentPath;

                    var flag = isFolder(name);
                    var prefix=getUrlPrefix(name,"create");
                   // newNode = createNewRow(input.name,name,currentPath,"cal1.png");
                    if(name == "Assortment"){
                        GanttChartPresenter.createAssortment(prefix,name,input.name,currentPath,flag,addNode);
                    }else{
                        GanttChartPresenter.createDimension(prefix,name,input,currentPath,flag,addNode);
                    }
                }

                $( this ).dialog( "close" );
                }
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        },
        close: function() {
            //allFields.val( "" ).removeClass( "ui-state-error" );
            $( "#name" ).removeClass("ui-state-error") ;
            $( "#manager" ).removeClass("ui-state-error");
            $( "#startdate" ).removeClass("ui-state-error");
            $( "#enddate" ).removeClass("ui-state-error");
            $( "#budgetOwner" ).removeClass("ui-state-error");
            $( "#budget").removeClass("ui-state-error");
            $( "#currency" ).removeClass("ui-state-error");
            clearForm();
        },
        autoOpen :true,
        changeTitle: document.getElementById("ui-id-1").innerHTML="Create New " + name,
        changeLabel: document.getElementById("dimensionName").innerHTML=name + " Name",
        datePicker: $(function() {
                        $( '.datePicker' ).datepicker({
                            showOn: 'both',
                            duration: "slow",
                            buttonImage: 'calendar-icon.png',
                            buttonImageOnly: true,
                            //changeMonth: true,
                            changeYear: true,
                            showAnim: 'blind',
                            showButtonPanel: true
                        });
                    })
        });
    }

    function  createNewRow(name,type,path,icon){
        var newRowNode = {
            "id": "",
            "title": name,
            "type": type,
            "path": path,
            "nameIcon":icon,
            "Items": []
        }
        return newRowNode;
    }

    function onFilter(){
        alert(123);
    }

    function isFolder(dim){
        var flag =true;
        /*if(dim == "Page" || dim == "Assortment"){
            flag = false;
        }*/
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


}




function clearForm(form)
{
    $(":input", form).each(function()
    {
        var type = this.type;
        var tag = this.tagName.toLowerCase();
        if (type == 'text')
        {
            this.value = "";
        }
    });


};

