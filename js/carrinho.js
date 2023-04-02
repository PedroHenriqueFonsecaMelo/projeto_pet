const removeProductButton= document.getElementsByClassName("remove-product-button")
console.log(removeProductButton)
for (var i = 0; i< removeProductButton.length; i++){
removeProductButton[i].addEventListener("click", function(event){
    console.log(event.target)
})
}


const plus = document.getElementsByClassName("bx-plus"),
minus = document.getElementsByClassName("bx-minus"),
num = document.getElementsByClassName("num");


for (let i = 0; i < plus.length; i++) {
    plus[i].addEventListener("click", (e)=>{
       
       let a = parseInt(num[i].innerHTML);
       a++;
        
        a = (a < 10) ? "0" + a : a;
        num[i].innerText = a;

        newFunction(a);

        
    });
}

for (let i = 0; i < minus.length; i++) {
    minus[i].addEventListener("click", (e)=>{
        let a = parseInt(num[i].innerHTML);
        if(a > 1){
          
          a--;
          
          a = (a < 10) ? "0" + a : a;
          num[i].innerText = a;
      
          newFunction(a);
        }
    });
}




function newFunction(a) {
    td = document.getElementsByTagName("td");
    precoBase = parseFloat(td[1].innerText.substring(3));
    td[3].innerText = precoBase * a;

    subtotal = document.getElementById("sutotal");
    frete = document.getElementById("frete");
    total =  document.getElementById("total");

    subtotal.innerText = "Valor R$: " + td[3].innerText;
    frete.innerText = "Valor R$: " + (parseFloat(td[3].innerText)*(2/100)).toFixed(2);
    total.innerText = "Valor R$: " + (parseFloat(subtotal.innerHTML.substring(9))+parseFloat(frete.innerHTML.substring(9))).toFixed(2);

}

