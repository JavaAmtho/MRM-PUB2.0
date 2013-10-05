var DropDownSelector = function(){

    this.design = function(id)
    {
        $(document).bind("viewStructureLoaded", function onSchemaLoadedHandler(e){
            if(!e.schemaChanged){
                var data = e.schemaData;
                var dropdownObj = document.getElementById(id);
                var dropdown = ElementFactory.getDropDown();
                dropdown.createDropDown(dropdownObj,data);
            }
        });
        GetViewStructure.getAll();
    }

}

DropDownSelector.getInstance = function(){
    return new DropDownSelector();
}



