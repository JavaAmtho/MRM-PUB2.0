/**
 * Created by Rohan H. Dani
 * User: CS13
 * Date: 11/8/13
 * Time: 2:04 PM
 * Created for Events mapping.
 */
function HtmlEventDesigner(){

}

HtmlEventDesigner.addEvents = function(id,event,func,flag){
    var htmlElement = this.getElementBy(id);
    htmlElement.addEventListener(event,func,flag);
}

HtmlEventDesigner.getElementBy = function(id){
    return  document.getElementById(id);
}