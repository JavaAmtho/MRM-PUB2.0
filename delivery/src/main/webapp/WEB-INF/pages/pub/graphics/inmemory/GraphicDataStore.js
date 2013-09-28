/**
 * Created by Rohan H. Dani
 * User: CS13
 * Date: 11/8/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */

var GraphicDataStore = function(){
    var schemaArray=[];
    var currentSchema;
    var prodcutsArr=[];
    var currentAssortment;
    var pageRulesArr;
}

GraphicDataStore.getPageRuleById = function(id){
    console.log(this.pageRulesArr);
    if(this.pageRulesArr){
        return this.pageRulesArr[id];
    }
    else{
        return undefined;
    }
}

GraphicDataStore.addToPageRules = function(rule){
    if(!this.pageRulesArr){
        this.pageRulesArr = {};
    }
    this.pageRulesArr[rule.logicalPageID] = rule;
    console.log(this.pageRulesArr)
}

GraphicDataStore.setCurrentAssortment = function(obj){
    this.currentAssortment = obj;
}

GraphicDataStore.getCurrentAssortment = function(){
    return this.currentAssortment;
}

GraphicDataStore.setProdcutsArr = function(arr){
    this.prodcutsArr = arr;
}

GraphicDataStore.addProdcut = function(item){
    this.prodcutsArr.push(item);
}

GraphicDataStore.getProdcutsArr = function(){
    return this.prodcutsArr;
}

GraphicDataStore.setSchemaArray = function(schemaData){
    this.schemaArray = schemaData;
}

GraphicDataStore.getSchemaArray = function(){
    return this.schemaArray;
}

GraphicDataStore.setDefaultSchema = function(){
    for(var i=0; i< this.schemaArray.length; i++){
        if(this.schemaArray[i].default == "true"){
            GraphicDataStore.setCurrentSchema(this.schemaArray[i]);
        }
    }
}

GraphicDataStore.setCurrentSchema = function(schema){
    this.currentSchema = schema;
}

GraphicDataStore.getCurrentSchema = function(schema){
    return this.currentSchema;
}


GraphicDataStore.getFirstDimension = function(){
    return this.currentSchema.structure[0].name+"s";
}

GraphicDataStore.getPossibleChild = function(dim){
    if(dim === 'root'){
        var arr = [];
        arr.push(this.currentSchema.structure[0].name)
        return arr;
    }
    else{
        for(var i=0; i< this.currentSchema.structure.length; i++){
            if(dim === this.currentSchema.structure[i].name){
                return this.currentSchema.structure[i].possibleChild;
            }
        }
    }

}

GraphicDataStore.setSchemaLabel = function(){
    $("#txt").show();
    $("#txt").text(this.currentSchema.name);
}