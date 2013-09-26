/**
 * Created by Ravi Sharma.
 * User: cs6
 * Date: 08/08/13
 * Time: 8:56 PM
 */

function MustacheWrapper(){

}

MustacheWrapper.createUI = function(templateURL, templateData, callBack){
    var str = "";
    if(templateURL == "")
        return;

    $(document).load(templateURL, function(templateStr){

        var tpl = templateStr;

        str = Mustache.to_html(tpl, templateData);
        str = str.replace(/&#x2F;/g, "/");

        callBack(str);
    });
}