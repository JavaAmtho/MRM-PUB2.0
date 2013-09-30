function HomePresenter(){

}

HomePresenter.date = new Date();
HomePresenter.units = "months";

HomePresenter.createProductsJSON = function(){
    var jsonData = {};
    var columnName = "products";
    jsonData[columnName] = GraphicDataStore.getProdcutsArr();
    var columnName = "id";
    jsonData[columnName] = GraphicDataStore.getCurrentAssortment().id;
    UpdateAssortment.update(GraphicDataStore.getCurrentAssortment(),jsonData,HomePresenter.hideAssortPanel);
}

HomePresenter.dayView = function (){
    HomePresenter.units = "days";
    Grids[0].ChangeZoom("days");
}

HomePresenter.monthView = function (){
    HomePresenter.units="months";
    Grids[0].ChangeZoom("months");
}

HomePresenter.weekView = function (){
    HomePresenter.units="weeks";
    Grids[0].ChangeZoom("weeks");
}

HomePresenter.yearView = function (){
    HomePresenter.units="years"
    Grids[0].ChangeZoom("years");
}

HomePresenter.viewTree = function(){
    var show = new Array("name");
    var hide = new Array("startDate","endDate","ganttChart","manager","budgetOwner","budget");
    Grids[0].ChangeColsVisibility(show,hide,0);
    var ganttElements = document.getElementsByClassName("GanttProperties");
    for(var i = 0; i < ganttElements.length; i++) {
        ganttElements[i].style.visibility = "hidden";
    }
    $("#treeGantt").removeClass("calendarButtonPressed") ;


}

HomePresenter.viewTreeAndFields = function(){
    var show = new Array("name","startDate","endDate","manager","budgetOwner","budget");
    var hide = new Array("ganttChart");
    Grids[0].ChangeColsVisibility(show,hide,0);
    var ganttElements = document.getElementsByClassName("GanttProperties");
    for(var i = 0; i < ganttElements.length; i++) {
        ganttElements[i].style.visibility = "hidden";
    }
    $("#treeGantt").removeClass("calendarButtonPressed") ;
}

HomePresenter.viewTreeAndGantt = function(){
    var show = new Array("name","ganttChart");
    var hide = new Array("startDate","endDate","manager","budgetOwner","budget");
    Grids[0].ChangeColsVisibility(show,hide,0);
    var ganttElements = document.getElementsByClassName("GanttProperties");
    for(var i = 0; i < ganttElements.length; i++) {
        ganttElements[i].style.visibility = "visible";
    }
    HomePresenter.date = new Date(Grids[0].GetGanttDate(0));
    $("#treeGantt").addClass("calendarButtonPressed");
}

HomePresenter.scrollNext = function(){
    switch(HomePresenter.units){

        case "years" :
            var year = HomePresenter.date.getFullYear();
            year++;
            HomePresenter.date.setFullYear(year);
            HomePresenter.date.setDate(1);
            HomePresenter.date.setMonth(1);
            break;
        case "months" :
            var month = HomePresenter.date.getMonth();
            month++;
            HomePresenter.date.setMonth(month);
            HomePresenter.date.setDate(1);
            break;
        case "weeks" :
            var year = HomePresenter.date.getW();
            year++;
            HomePresenter.date.setYear(year);
            break;
        case "days" :
            var day = HomePresenter.date.getDate();
            day++;
            HomePresenter.date.setDate(day);
            break;

    }
    Grids[0].ScrollToDate(HomePresenter.date,"Left");
}

HomePresenter.scrollPrev = function(){
    switch(HomePresenter.units){

        case "years" :
            var year = HomePresenter.date.getFullYear();
            year--;
            HomePresenter.date.setFullYear(year);
            HomePresenter.date.setDate(1);
            HomePresenter.date.setMonth(1);
            break;
        case "months" :
            var month = HomePresenter.date.getMonth();
            month--;
            HomePresenter.date.setMonth(month);
            HomePresenter.date.setDate(1);
            break;
        case "weeks" :
            var year = HomePresenter.date.getW();
            year--;
            HomePresenter.date.setYear(year);
            break;
        case "days" :
            var day = HomePresenter.date.getDate();
            day--;
            HomePresenter.date.setDate(day);
            break;

    }
    Grids[0].ScrollToDate(HomePresenter.date,"Left");
}

HomePresenter.scrollToday = function(){
    HomePresenter.date=new Date();
    Grids[0].ScrollToDate(HomePresenter.date);
}

var ck_alpha = /^[A-Za-z]+$/;
HomePresenter.checkAlpha1 = function(){
    if (!ck_alpha.test(event.currentTarget.value)) {
        $("#"+event.currentTarget.id).addClass( "ui-state-error") ;
        return false;
    }
    else{
        $("#"+event.currentTarget.id).removeClass("ui-state-error")   ;
        return true;
    }
}

var ck_date = /^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d$/;
HomePresenter.checkDate1 = function(){
    if (!ck_date.test(event.currentTarget.value)) {
        alert(123)
       // console.log(event.currentTarget.value)
        $("#"+event.currentTarget.id).addClass( "ui-state-error") ;
        return false;
    }
    else{
        $("#"+event.currentTarget.id).removeClass("ui-state-error");
        return true;
    }
}



HomePresenter.checkNum1 = function(){
    if( !$.isNumeric(event.currentTarget.value ) ){
        $("#"+event.currentTarget.id).addClass( "ui-state-error") ;
    }

    else{
        $("#"+event.currentTarget.id).removeClass("ui-state-error")   ;
        return true;
    }

}





HomePresenter.createFlow = function(publications){

   /* var publications = [
        {
            "id":"1",
            "name":"Publication 1",
            "previewImage":"../../../graphics/screens/home/images/img/Chrysanthemum.jpg",
            "actualImage": "../../../graphics/screens/home/images/img/Chrysanthemum.jpg",
            "previewType": "image"
        },
        {
            "id":"2",
            "name":"Publication 2",
            "previewImage": "http://192.168.135.112/CS13.0Trunk/admin/FileServer.php?src=F%3A%2FContentServ%2Fhtdocs%2FCS13.0Trunk%2FCSLive%2Fdata%2F.cs%2Fmam%2Fvolumes%2F75%2Ffiles%2F7547%2Fthumbs%2Fmaster.jpg&SecurityID=972146a8ada263573f41419871cc011f&rand=1378896763",
            "actualImage": "http://192.168.135.112/CS13.0Trunk/admin/forward.php?forward=../CSLive/playCSVideoPlayerUsingMamFile.php&mamFileNo=7547",
            "previewType": "video"
        },
        {
            "id":"3",
            "name":"Publication 3",
            "previewImage":"../../../graphics/screens/home/images/img/Hydrangeas.jpg",
            "actualImage": "../../../graphics/screens/home/images/img/Hydrangeas.jpg",
            "previewType": "image"
        },
        {
            "id":"4",
            "name":"Publication 4",
            "previewImage":"../../../graphics/screens/home/images/img/Jellyfish.jpg",
            "actualImage": "../../../graphics/screens/home/images/img/Jellyfish.jpg",
            "previewType": "image"
        },
        {
            "id":"5",
            "name":"Publication 5",
            "previewImage": "http://192.168.135.112/CS13.0Trunk/admin/FileServer.php?src=F%3A%2FContentServ%2Fhtdocs%2FCS13.0Trunk%2FCSLive%2Fdata%2F.cs%2Fmam%2Fvolumes%2F75%2Ffiles%2F7546%2Fthumbs%2Fmaster.jpg&SecurityID=972146a8ada263573f41419871cc011f&rand=1378896774",
            "actualImage": "http://192.168.135.112/CS13.0Trunk/admin/forward.php?forward=../CSLive/playCSVideoPlayerUsingMamFile.php&mamFileNo=7546",
            "previewType": "video"
        }
    ];
*/
    GraphicDataStore.setCommChannelDetails(publications);
    var details = GraphicDataStore.getCommChannelDetails();

    $(".flow").html("");

    for(var i=0; i< details.length; i++){
        var img = $(document.createElement('img'))
        img.attr('id', details[i].id);
        img.attr('src',details[i].previewImage);
        img.attr('class',"item");
        img.attr('alt',details[i].name);
        img.attr('title',details[i].name);
        img.appendTo('.flow');
    }

    var scriptTag = '<script type="text/javascript" src="../../../graphics/screens/home/scripts/alienscripts/js/contentFlow/contentflow.js" load="CSFlow"></script>';
    $("head").append(scriptTag);
    $('#coverMain').fadeIn(600);
}

