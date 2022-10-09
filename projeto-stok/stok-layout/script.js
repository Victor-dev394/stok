$(document).ready(function(){

    $('#form').submit(function(e) {
        e.preventDefault();

        var name = $('#inputNome').val();
        var desc = $('#inputDescricao').val();
        var forn = $('#inputFornecedor').val();
        var cat = $('#select').val();

        $.ajax({
            url: 'http://localhost:8080/produto',
            contentType: 'application/json',
            method: 'POST',
            data: JSON.stringify({
                "nome": name,
                "descricao": desc,
                "fornecedor": forn,
                "categoria" : cat
              }),
            dataType: 'json',
            success: function(result) {
                console.log(result);
                $('#inputNome').val("");
                $('#inputDescricao').val("");
                $('#inputFornecedor').val("");
                $('#select').val("");

                var box_comm = document.querySelector('#table');
                while(box_comm.firstChild){
                    box_comm.firstChild.remove();
                }

                getProdutos();
            }
        });
    });

    
    function getProdutos() {
        
        $.ajax({
            url: 'http://localhost:8080/produto',
            contentType: 'application/json',
            method: 'GET',
            dataType: 'json',
            success: function(result) {
                $.each(result, function(i) {
                    var tr = $("<tr></tr>");
                    var tdId = $("<td></td>");
                    var tdNome = $("<td></td>");
                    var tdDesc = $("<td></td>");
                    var tdData = $("<td></td>");
                    var tdForn = $("<td></td>");
                    var tdCat = $("<td></td>");
                    var tdExcluir = $("<td></td>");
                    var tdAlterar = $("<td></td>");

                    var btnExcluir = $("<button class='btn btn-danger'>Excluir</button>");
                    var btnAlterar = $("<button id='btnAlterar' class='btn btn-primary' data-toggle='modal' data-target='#modalAlterar'>Alterar</button>");

                    tdExcluir.append(btnExcluir);
                    tdAlterar.append(btnAlterar);

                    tdId.text(result[i].id);
                    tdNome.text(result[i].nome);
                    tdDesc.text(result[i].descricao);
                    tdData.text(result[i].dataCadastro);
                    tdForn.text(result[i].fornecedor);
                    tdCat.text(result[i].categoria);

                    tr.append(tdId);
                    tr.append(tdNome);
                    tr.append(tdDesc);
                    tr.append(tdData);
                    tr.append(tdForn);
                    tr.append(tdCat);
                    tr.append(tdExcluir);
                    tr.append(tdAlterar);

                    $("#table").append(tr);

                    btnExcluir.click(function(event) {
                        exClick(result[i].id, tr);
                    });

                    btnAlterar.click(function(event) {
                        $("#altId").val(result[i].id);
                        $("#altNome").val(result[i].nome);
                        $("#altDescricao").val(result[i].descricao);
                        $("#altFornecedor").val(result[i].fornecedor);
                        $("#selectAlt").val(result[i].categoria);
                    });
                })
            }
        });
    }

    function exClick(idparam, tr) {

        $.ajax({
            url: 'http://localhost:8080/produto/'+idparam,
            contentType: 'application/json',
            method: 'DELETE',
            dataType: 'json',
        });
        console.log(idparam);
        tr.remove();
    }

    $('#formAlt').submit(function(e) {
        e.preventDefault();

        var id = $('#altId').val();
        var name = $('#altNome').val();
        var desc = $('#altDescricao').val();
        var forn = $('#altFornecedor').val();
        var cat = $('#selectAlt').val();

        $.ajax({
            url: 'http://localhost:8080/produto/'+id,
            contentType: 'application/json',
            method: 'PUT',
            data: JSON.stringify({
                "nome": name,
                "descricao": desc,
                "fornecedor": forn,
                "categoria" : cat
              }),
            dataType: 'json',
            success: function(result) {
                console.log(result);

                $("#modalAlterar").hide();

                var box_comm = document.querySelector('#table');
                while(box_comm.firstChild){
                    box_comm.firstChild.remove();
                }

                getProdutos();
            }
        });

    });

    getProdutos();

});

