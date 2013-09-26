function LoginService(){

}

LoginService.getLogin = function(){
    Router.loadTemplate("homeScreen");
    $(".logout").css( "visibility", "visible" );
    $(".wrapper").css( "visibility", "visible" );
    $(".profileLogo").css( "visibility", "visible" );
    $(".logout").click(function(){
        location.reload();
    })
}
