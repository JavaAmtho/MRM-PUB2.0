function LoginPresentor(){

}

LoginPresentor.loadScreen = function(callBack){
    TemplateLoader.loadTemplate("homeScreen",callBack);
}

LoginPresentor.makeLogoutBtnVisible = function(){
    $(".logout").css( "visibility", "visible" );
    $(".wrapper").css( "visibility", "visible" );
    $(".profileLogo").css( "visibility", "visible" );
}
