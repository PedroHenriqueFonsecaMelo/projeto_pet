/* global labelSexo */

const password1 = document.getElementById('password');
const icon = document.getElementById('icon');
const confirmPassword1 = document.getElementById('confirmPassword');
const icon2 = document.getElementById('icon2');

let nome = document.getElementById('nomeCompleto');
let labelNome = document.getElementById('labelNomeCompleto');
let validNome = false;

let telefone = document.getElementById('telefone');
let labelTelefone = document.getElementById('labeltelefone');
let validTelefone = false;

let email = document.getElementById('email');
let labelemail = document.getElementById('Labelemail');
let validEmail = false; 

let sexo = document.getElementById('Sexo');
let labelsexo= document.getElementById('labelSexo');
let validSexo = false;

let password = document.getElementById('password');
let labelSenha = document.getElementById('labelSenha');
let validSenha = false;

let confirmarSenha = document.getElementById('confirmPassword');
let labelConfirmarSenha = document.getElementById('labelConfirmarSenha');
let validConfirmarSenha = false;

let msgError = document.getElementById('msgError');
let msgSuccess = document.getElementById('msgSuccess');

nome.addEventListener('keyup', () => {
    if (nome.value.length <= 1 ||nome===" ")&&(nome==="") {
        labelNome.setAttribute('style', 'color:red');
        labelNome.innerHTML = '*Digite o seu Nome';
        nome.setAttribute('style', 'border-color: red');
        validNome = false;
    } else {
        labelNome.setAttribute('style', 'color:green');
        labelNome.innerHTML = 'Nome';
        nome.setAttribute('style', 'border-color: green');
        validNome = true;
    }

});

telefone.addEventListener('keyup', () => {
    if (telefone.value.length <= 10) {
        labelTelefone.setAttribute('style', 'color:red');
        labelTelefone.innerHTML = '*Digite o seu Telefone';
        telefone.setAttribute('style', 'border-color: red');
        validTelefone= false;
    } else {
        labelTelefone.setAttribute('style', 'color:green');
        labelTelefone.innerHTML = 'Telefone';
        telefone.setAttribute('style', 'border-color: green');
        validTelefone= true;
    }
});

email.addEventListener('keyup', () => {
    if (email.value.length <= 10) {
        labelemail.setAttribute('style', 'color:red');
        labelemail.innerHTML = '*Digite o seu E-mail';
        email.setAttribute('style', 'border-color: red');
        validEmail = false;
    } else {
        labelemail.setAttribute('style', 'color:green');
        labelemail.innerHTML = 'Email';
        email.setAttribute('style', 'border-color: green');
        validEmail = true;
    }

});
if (validNome !== '' && validEmail !== '' && validTelefone !== '' && validSenha !== '' && validConfirmarSenha !== '' && validSexo !== '') 

sexo.addEventListener('change', () => {
    if (sexo.value === "") {
      labelSexo.style.color = 'red';
      labelSexo.innerHTML = '*Selecione seu sexo';
      sexo.style.borderColor = 'red';
      validSexo = false;
    } else {
      labelSexo.style.color = 'green';
      labelSexo.innerHTML = 'Sexo';
      sexo.style.borderColor = 'green';
      validSexo = true;
    }
  });

password.addEventListener('keyup', () => {
    if (password.value.length <= 5) {
        labelSenha.setAttribute('style', 'color:red');
        labelSenha.innerHTML = '*Digite a sua Senha';
        password.setAttribute('style', 'border-color: red')
        validSenha = false;
    } else {
        labelSenha.setAttribute('style', 'color:green');
        labelSenha.innerHTML = 'Senha';
        password.setAttribute('style', 'border-color: green');
        validSenha = true;
    }
});

confirmarSenha.addEventListener('keyup', () => {
    if (password.value != confirmarSenha.value) {
        labelConfirmarSenha.setAttribute('style', 'color:red');
        labelConfirmarSenha.innerHTML = '*As senhas não conferem';
        confirmarSenha.setAttribute('style', 'border-color: red');
        validConfirmarSenha = false;
    } else {
        labelConfirmarSenha.setAttribute('style', 'color:green');
        labelConfirmarSenha.innerHTML = 'Confirmar Senha';
        confirmarSenha.setAttribute('style', 'border-color: green');
        validConfirmarSenha = true;
    }

});

function showHidden() {
    if (password1.type === 'password') {
        password1.setAttribute('type', 'text');
        icon.classList.add('hide');
    } else {
        password1.setAttribute('type', 'password');
        icon.classList.remove('hide');

    }

}

function showHidden2() {
    if (confirmPassword1.type === 'password') {
        confirmPassword1.setAttribute('type', 'text');
        icon2.classList.add('hide2');

    } else {
        confirmPassword1.setAttribute('type', 'password');
        icon2.classList.remove('hide2');

    }
}


function cadastrar() {
    if (validNome !== '' && validTelefone !== '' && validSenha !== '' && validConfirmarSenha !== '' && validSexo !== '') {
    msgSuccess.setAttribute('style', 'display:block');
    msgSuccess.innerHTML = '<strong>Cadastrando Usuário...</strong>';
    msgError.setAttribute('style', 'display:none');
    msgError.innerHTML = '';

    // Redireciona para a página index.html
    location.href = "index.html";

} else {
    msgError.setAttribute('style', 'display:block');
    msgError.innerHTML = '<strong>Preencha todos os campos corretamente antes de cadastrar</strong>';
    msgSuccess.innerHTML = '';
    msgSuccess.setAttribute('style', 'display:none');
}
}
