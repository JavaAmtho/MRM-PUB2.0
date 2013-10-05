function GetMasterTemplateList(){

}

GetMasterTemplateList.get = function(callBack){
    Router.loadRequest("getMasterTemplateList",false,callBack);
}
