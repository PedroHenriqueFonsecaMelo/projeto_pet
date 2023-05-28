function logar(){
    var usuario = document.getElementById('usuario').value;
    var senha = document.getElementById('senha').value;

    if((usuario=="admin" && senha=="admin") && (usuario!= null || senha!=null)){
        alert('usuario logado');
        location.href="carrinho.html";   
    }
    else{
        alert('usuario ou senha incorreto!!')
        location.href="cadastro.html";
    }

}