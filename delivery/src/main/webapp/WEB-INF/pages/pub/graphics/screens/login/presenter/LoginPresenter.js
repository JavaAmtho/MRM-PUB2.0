function LoginPresenter(){

}

LoginPresenter.getLogin = function(){
    TemplateLoader.loadTemplate("homeScreen",LoginPresenter.makeLogoutBtnVisible);
}

LoginPresenter.makeLogoutBtnVisible = function(){
    $(".logout").css( "visibility", "visible" );
    $(".wrapper").css( "visibility", "visible" );
    $(".profileLogo").css( "visibility", "visible" );
}
