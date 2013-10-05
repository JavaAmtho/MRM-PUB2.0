var DropDownPresenter = function(){

    this.design = function(id)
    {
        $(document).bind("viewStructureLoaded", function onSchemaLoadedHandler(e){
            if(!e.schemaChanged){
                var data = e.schemaData;
                var dropdownObj = document.getElementById(id);
                if(dropdownObj){
                    var dropdown = ElementFactory.getDropDown();
                    dropdown.createDropDown(dropdownObj,data);
                }
            }
        });
        DropDownPresenter.getAllViewStructure();
    }

}

DropDownPresenter.getInstance = function(){
    return new DropDownPresenter();
}

DropDownPresenter.getAllViewStructure = function(structId){
    GetViewStructure.getAll();
}

DropDownPresenter.getViewStructureById = function(structId){
    GetViewStructure.get(structId);
}














/*$('#selectLbl').text("Select View");
  $('#schemaDropDown').hide();
  $('#mainAnimationContainer').animate({height: actualHeight}, 500);*/


