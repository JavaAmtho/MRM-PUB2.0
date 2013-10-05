function ElementFactory(){

}

ElementFactory.getTree = function(){
    return new DynaTree();
}

ElementFactory.getLazyTree = function(){
    return new DynaLazyTree();
}

ElementFactory.getDropDown = function(){
    return new DropDownJS();
}
