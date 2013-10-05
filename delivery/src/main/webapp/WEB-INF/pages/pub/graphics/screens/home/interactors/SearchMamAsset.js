function SearchMamAsset(){

}

SearchMamAsset.search = function(searchKey,callBack){
    Router.loadRequest("searchMamAssets",true,callBack,searchKey);
}

