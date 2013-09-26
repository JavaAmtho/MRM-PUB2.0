function SearchPimAsset(){

}

SearchPimAsset.search = function(searchKey,callBack){
    Router.loadRequest("searchPimAssets",true,callBack,searchKey);
}

