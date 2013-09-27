function CreateDimensions(){

}

CreateDimensions.createDim = function(prefix,action,input,currentPath,flag,callBack){
    var reqBody = new Object();
    reqBody.name=input.name;
    reqBody.managerName=input.managerName;
    reqBody.startDate=input.startDate;
    reqBody.endDate=input.endDate;
    reqBody.budgetOwner=input.budgetOwner;
    reqBody.type=input.type;
    if(input.budget)
    reqBody.budget=input.budget;
    //ONLY FOR MOCKS!!!!
    callBack(reqBody) ; //while deploying please remove!!
    Router.forwardWithPost(prefix+action+"/name/"+input.name+"/path/"+currentPath+"/folder/"+flag,true,reqBody,function(data){
        //PLEASE UNCOMMENT!!!!
        //callBack(data);
    });
}

