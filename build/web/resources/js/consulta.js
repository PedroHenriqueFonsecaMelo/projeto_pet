const KEY_BD = '@usuariosestudo'


var listaRegistros = {
    ultimoIdGerado: 0,
    usuarios: []
}


var FILTRO = ''


function gravarBD() {
    localStorage.setItem(KEY_BD, JSON.stringify(listaRegistros))
}

function lerBD() {
    const data = localStorage.getItem(KEY_BD)
    if (data) {
        listaRegistros = JSON.parse(data)
    }
    desenhar()
}


function pesquisar(value) {
    FILTRO = value;
    desenhar()
}


function desenhar() {
    const tbody = document.getElementById('listaRegistrosBody')
    if (tbody) {
        var data = listaRegistros.usuarios;
        if (FILTRO.trim()) {
            const expReg = eval(`/${FILTRO.trim().replace(/[^\d\w]+/g, '.*')}/i`)
            data = data.filter(usuario => {
                return expReg.test(usuario.nome) || expReg.test(usuario.email) || expReg.test(usuario.telefone)
                || expReg.test(usuario.sexo)|| expReg.test(usuario.senha)
            })
        }
        data = data
                .sort((a, b) => {
                    return a.nome < b.nome ? -1 : 1
                })
                .map(usuario => {
                    return `<tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nome}</td>
                        <td>${usuario.email}</td>
                         <td>${usuario.telefone}</td>
                            <td>${usuario.sexo}</td>
                            <td>${usuario.senha}</td>
                            <td>
                            <button onclick='vizualizar("cadastro",false,${usuario.id})'>Editar</button>
                            <button class='vermelho' onclick='perguntarSeDeleta(${usuario.id})'>Deletar</button>
                        </td>
                    </tr>`
                })
        tbody.innerHTML = data.join('')
    }
}

function insertUsuario(nome, email, telefone, sexo, senha) {
    const id = listaRegistros.ultimoIdGerado + 1;
    listaRegistros.ultimoIdGerado = id;
    listaRegistros.usuarios.push({
        id, nome, email, telefone, sexo, senha
    })
    gravarBD()
    desenhar()
    vizualizar('lista')
}

function editUsuario(id, nome, email, telefone, sexo, senha) {
    var usuario = listaRegistros.usuarios.find(usuario => usuario.id == id)
    usuario.nome = nome;
    usuario.email = email;
    usuario.telefone = telefone;
    usuario.sexo = sexo;
    usuario.senha = senha;
    gravarBD()
    desenhar()
    vizualizar('lista')
}

function deleteUsuario(id) {
    listaRegistros.usuarios = listaRegistros.usuarios.filter(usuario => {
        return usuario.id != id
    })
    gravarBD()
    desenhar()
}

function perguntarSeDeleta(id) {
    if (confirm('Quer deletar o registro de id ' + id)) {
        deleteUsuario(id)
    }
}


function limparEdicao() {
    document.getElementById('nome').value = ''
    document.getElementById('email').value = ''
    document.getElementById('telefone').value = ''
    document.getElementById('sexo').value = ''
    document.getElementById('senha').value = ''
}

function vizualizar(pagina, novo = false, id = null) {
    document.body.setAttribute('page', pagina)
    if (pagina === 'cadastro') {
        if (novo)
            limparEdicao()
        if (id) {
            const usuario = listaRegistros.usuarios.find(usuario => usuario.id == id)
            if (usuario) {
                document.getElementById('id').value = usuario.id
                document.getElementById('nome').value = usuario.nome
                document.getElementById('email').value = usuario.email
                document.getElementById('telefone').value = usuario.telefone
                document.getElementById('sexo').value = usuario.sexo
                document.getElementById('senha').value = usuario.senha
            }
        }
        document.getElementById('nome').focus()
}
}



function submeter(e) {
    e.preventDefault()
    const data = {
        id: document.getElementById('id').value,
        nome: document.getElementById('nome').value,
        email: document.getElementById('email').value,
        telefone: document.getElementById('telefone').value,
        sexo: document.getElementById('sexo').value,
        senha: document.getElementById('senha').value,
    }
    if (data.id) {
        editUsuario(data.id, data.nome, data.email, data.telefone, data.sexo, data.senha)
    } else {
        insertUsuario(data.nome, data.email, data.telefone, data.sexo, data.senha)
    }
}


window.addEventListener('load', () => {
    lerBD()
    document.getElementById('cadastroRegistro').addEventListener('submit', submeter)
    document.getElementById('inputPesquisa').addEventListener('keyup', e => {
        pesquisar(e.target.value)
    })

})
