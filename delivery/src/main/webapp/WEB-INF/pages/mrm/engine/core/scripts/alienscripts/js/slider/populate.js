function populateSlider() {
    //Note: If the slider container has been set as invisible(e.g. display:none;), make sure set it visible before reload the imageSlider
    setSliderMarkup();
    imageSlider.reload();
}
function setSliderMarkup() {
    var sliderFrame = document.getElementById("sliderFrame");
    console.log(sliderFrame);
    alert(12345678)
    sliderFrame.innerHTML = '<div id="slider"><img src="images/slider-1.jpg" />' +
        '<img src="images/image-slider-2.jpg" /><img src="images/image-slider-3.jpg"  />' +
        '<img src="images/image-slider-4.jpg"  /><img src="images/image-slider-5.jpg" /></div>';
}