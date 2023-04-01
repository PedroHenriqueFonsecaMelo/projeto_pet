const removeProductButton= document.getElementsByClassName("remove-product-button")
console.log(removeProductButton)
for (var i = 0; i< removeProductButton.length; i++){
removeProductButton[i].addEventListener("click", function(event){
    console.log(event.target)
})
}