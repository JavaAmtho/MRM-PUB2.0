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
    var masterTemplateList;
    var currentPublication;
    var assortmentsList;
    var loadingRulesList;
}

GraphicDataStore.checkIfRuleLoading = function(ruleID){
    if(!this.loadingRulesList){
        this.loadingRulesList = {};
    }
    if(this.loadingRulesList[ruleID] == 'loading'){
        return true;
    }
    else{
        return false;
    }
}

GraphicDataStore.stopLoadingStatus = function(ruleID){
    if(!this.loadingRulesList){
        this.loadingRulesList = {};
    }
    this.loadingRulesList[ruleID] = "";
}

GraphicDataStore.addRuleToLoadingList = function(ruleID){
    if(!this.loadingRulesList){
        this.loadingRulesList = {};
    }
    this.loadingRulesList[ruleID] = "loading"
}

GraphicDataStore.pushToAssortmentsList = function(pageID,assortments){
    if(!this.assortmentsList){
        this.assortmentsList = {};
    }
    this.assortmentsList[pageID] = assortments;
}

GraphicDataStore.getAssortmentsByID = function(pageID){
    if(this.assortmentsList){
        return this.assortmentsList[pageID];
    }
    else{
        return null;
    }
}

GraphicDataStore.setCurrentPublication = function(obj){
    this.currentPublication = obj;
}

GraphicDataStore.getCurrentPublication = function(){
    return this.currentPublication;
}

GraphicDataStore.setMasterTemplateList = function(obj){
    this.masterTemplateList = obj;
}

GraphicDataStore.getMasterTemplateList = function(){
    return this.masterTemplateList;
}


GraphicDataStore.getPageRuleById = function(id){
    if(this.pageRulesArr){
        return this.pageRulesArr[this.currentPublication + "." + id];
    }
    else{
        return undefined;
    }
}

GraphicDataStore.addAllPageRules = function(rules){
    if(!this.pageRulesArr){
        this.pageRulesArr = {};
    }
    if(rules != null && rules.length > 0){
        for(var i = 0 ; i < rules.length ; i++){
            if(rules[i] != null){
                this.pageRulesArr[rules[i].id] = rules[i].pageRules;
            }
        }
    }
}

GraphicDataStore.addToPageRules = function(rule){
    if(!this.pageRulesArr){
        this.pageRulesArr = {};
    }
    this.pageRulesArr[this.currentPublication + "." + rule.logicalPageID] = rule.pageRules;
}


GraphicDataStore.addAdditionalInformationToPageRules = function(additionalInfo, ruleID, logicalPageID){
    console.log(this.pageRulesArr[logicalPageID]);
    if(this.pageRulesArr){
        var pageRules = this.pageRulesArr[logicalPageID];
        if(pageRules){
            for(var i = 0 ; i < pageRules.length ; i++){
                if(pageRules[i].ruleID == ruleID){
                    pageRules[i].additionalInformation.mamFileID =  additionalInfo.mamFileID;
                    pageRules[i].additionalInformation.editUrl =  additionalInfo.editorURL;
                }
            }
        }
    }
    console.log(this.pageRulesArr[logicalPageID]);
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