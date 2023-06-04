const KEY_BD = '@produtoConsultestudo'


var listaRegistros = {
    ultimoIdGerado: 0,
    produtoConsult: []
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
    const tbody = document.getElementById('listaRegistrosBody');
    if (tbody) {
        var data = listaRegistros.produtoConsult;
        if (FILTRO.trim()) {
            const expReg = eval(`/${FILTRO.trim().replace(/[^\d\w]+/g, '.*')}/i`);
            data = data.filter(product => {
                return expReg.test(product.descricaoProduto) || expReg.test(product.observacao) || expReg.test(product.preco) || expReg.test(product.quantidade);
            });
        }
        data = data
            .sort((a, b) => {
                return a.descricaoProduto < b.descricaoProduto ? -1 : 1;
            })
            .map(product => {
                return `<tr>
                    <td>${product.id}</td>
                    <td>${product.descricaoProduto}</td>
                    <td>${product.observacao}</td>
                    <td>${product.preco}</td>
                    <td>${product.quantidade}</td>
                    <td>
                        <button onclick='vizualizar("cadastro",false,${product.id})'>Editar</button>
                        <button class='vermelho' onclick='perguntarSeDeleta(${product.id})'>Deletar</button>
                    </td>
                </tr>`;
            });
        tbody.innerHTML = data.join('');
    }
}


function insertProduto(descricaoProduto, observacao, preco, quantidade) {
    const id = listaRegistros.ultimoIdGerado + 1;
    listaRegistros.ultimoIdGerado = id;
    listaRegistros.produtoConsult.push({
        id, descricaoProduto, observacao, preco, quantidade
    })
    gravarBD()
    desenhar()
    vizualizar('lista')
}

function editProduto(id, descricaoProduto, observacao, preco, quantidade) {
    var product = listaRegistros.produtoConsult.find(product => product.id == id)
    product.descricaoProduto = descricaoProduto;
    product.observacao = observacao;
    product.preco = preco;
    product.quantidade = quantidade;
    gravarBD()
    desenhar()
    vizualizar('lista')
}

function deleteProduto(id) {
    listaRegistros.produtoConsult = listaRegistros.produtoConsult.filter(product => {
        return product.id != id
    })
    gravarBD()
    desenhar()
}

function perguntarSeDeleta(id) {
    if (confirm('Quer deletar o registro de id ' + id)) {
        deleteProduto(id)
    }
}


function limparEdicao() {
    document.getElementById('descricaoProduto').value = ''
    document.getElementById('observacao').value = ''
    document.getElementById('preco').value = ''
    document.getElementById('quantidade').value = ''
}

function vizualizar(pagina, novo = false, id = null) {
    document.body.setAttribute('page', pagina)
    if (pagina === 'cadastro') {
        if (novo)
            limparEdicao()
        if (id) {
            const product = listaRegistros.produtoConsult.find(product => product.id == id)
            if (product) {
                document.getElementById('id').value = product.id
                document.getElementById('descricaoProduto').value = product.descricaoProduto
                document.getElementById('observacao').value = product.observacao
                document.getElementById('preco').value = product.preco
                document.getElementById('quantidade').value = product.quantidade
            }
        }
        document.getElementById('descricaoProduto').focus()
}
}



function submeter(e) {
    e.preventDefault()
    const data = {
        id: document.getElementById('id').value,
        descricaoProduto: document.getElementById('descricaoProduto').value,
        observacao: document.getElementById('observacao').value,
        preco: document.getElementById('preco').value,
        quantidade: document.getElementById('quantidade').value,
    }
    if (data.id) {
        editProduto(data.id, data.descricaoProduto, data.observacao, data.preco, data.quantidade)
    } else {
        insertProduto(data.descricaoProduto, data.observacao, data.preco, data.quantidade)
    }
}


window.addEventListener('load', () => {
    lerBD()
    document.getElementById('cadastroRegistro').addEventListener('submit', submeter)
    document.getElementById('inputPesquisa').addEventListener('keyup', e => {
        pesquisar(e.target.value)
    })

})
