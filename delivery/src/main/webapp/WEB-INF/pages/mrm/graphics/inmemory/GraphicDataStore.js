var GraphicDataStore = function(){
    var schemaArray=[];
    var currentSchema;
    var commChannelDetails=[];
}

GraphicDataStore.setSchemaArray = function(schemaData){
    this.schemaArray = schemaData;
}

GraphicDataStore.getSchemaArray = function(){
    return this.schemaArray;
}

GraphicDataStore.setCommChannelDetails = function(channelDetails){
    this.commChannelDetails = channelDetails;
}

GraphicDataStore.getCommChannelDetails = function(){
    return this.commChannelDetails;
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
    }else{
        for(var i=0; i< this.currentSchema.structure.length; i++){
            if(dim === this.currentSchema.structure[i].name){
                return this.currentSchema.structure[i].possibleChild;
            }
        }
    }
}

GraphicDataStore.getPossibleDropParent = function(dim){
    for(var i=0; i< this.currentSchema.structure.length; i++){
        if(dim === this.currentSchema.structure[i].name){
            return this.currentSchema.structure[i].possibleDropParent;
        }
    }
}

GraphicDataStore.setCurrentFocusedItem = function(item){
    var arr =   GraphicDataStore.getCommChannelDetails();
    for(var i=0; i< arr.length; i++){
        if(item.content.id == arr[i].id){
            GraphicDataStore.currentFocusedItem = GraphicDataStore.getCommChannelDetails()[i];
        }
    }
}

var currentFocusedItem;

/*
GraphicDataStore.setSchemaLabel = function(){
    $("#txt").show();
    $("#txt").text(this.currentSchema.name);
}*/
