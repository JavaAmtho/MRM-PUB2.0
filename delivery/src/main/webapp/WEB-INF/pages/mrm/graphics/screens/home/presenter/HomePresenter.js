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

    /*var data = [
        {
            "pubId":"1",
            "pubName":"Publication 1",
            "pubDesc":"This is the publication 1",
            "previewImg":"../../../graphics/screens/home/images/img/Chrysanthemum.jpg"
        },
        {
            "pubId":"2",
            "pubName":"Publication 2",
            "pubDesc":"This is the publication 2",
            "previewImg":"../../../graphics/screens/home/images/img/Desert.jpg"
        },
        {
            "pubId":"3",
            "pubName":"Publication 3",
            "pubDesc":"This is the publication 3",
            "previewImg":"../../../graphics/screens/home/images/img/Hydrangeas.jpg"
        },
        {
            "pubId":"4",
            "pubName":"Publication 4",
            "pubDesc":"This is the publication 4",
            "previewImg":"../../../graphics/screens/home/images/img/Jellyfish.jpg"
        },
        {
            "pubId":"5",
            "pubName":"Publication 5",
            "pubDesc":"This is the publication 5",
            "previewImg":"../../../graphics/screens/home/images/img/Koala.jpg"
        }
    ];*/

    GraphicDataStore.setCommChannelDetails(publications);
    var details = GraphicDataStore.getCommChannelDetails();

    for(var i=0; i< details.length; i++){
        var img = $(document.createElement('img'))
        img.attr('id', details[i].id);
        img.attr('src',details[i].previewImg);
        img.attr('alt',details[i].name);
        img.appendTo('#myImageFlow');
    }
    /* Create ImageFlow instances when the DOM structure has been loaded */
    var instanceOne = new ImageFlow();
    instanceOne.init({
        ImageFlowID:'myImageFlow',// Div id which needs to be converted into the coverflow
        imageCursor: 'pointer',// Cursor used for images
        //imageFocusM: 1.1,// Focused image margin from other images
        xStep: 200,
        opacity: true,
        onClick: function(){
            var url = EngineDataStore.getPubUrl()+"?pubId="+this.id;
            console.log(url);
            window.open(url,"_blank");
        },
        buttons: true,
        reflections: false,
        reflectionP: 0.0
    });
}

