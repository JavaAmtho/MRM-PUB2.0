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

HomePresenter.createFlow = function(publications){
    /*var publications = [
         {
         "id":"pub1",
         "name":"Publication 1",
         "pubDesc":"This is the publication 1",
         "imageUrl":"img/Chrysanthemum.jpg"
         },
         {
         "id":"pub12",
         "name":"Publication 2",
         "pubDesc":"This is the publication 2",
         "imageUrl":"img/Desert.jpg"
         },
         {
         "id":"pub13",
         "name":"Publication 3",
         "pubDesc":"This is the publication 3",
         "imageUrl":"img/Hydrangeas.jpg"
         },
         {
         "id":"pub14",
         "name":"Publication 4",
         "pubDesc":"This is the publication 4",
         "imageUrl":"img/Jellyfish.jpg"
         },
         {
         "id":"5",
         "name":"Publication 5",
         "pubDesc":"This is the publication 5",
         "imageUrl":"img/Koala.jpg"
         }
     ];*/
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

