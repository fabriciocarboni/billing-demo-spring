$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget); //captura o botao que disparou o evento
	
	var codigoTitulo = button.data('codigo'); // captura o valor do codigo passado ao clicar no botao
	var descricaoTitulo = button.data('descricao') // captura descricao
		
	var modal = $(this);
	var form = modal.find('form'); //procura o form
	var action = form.data('url-base'); //pegar a string do action do form
	if (!action.endsWith('/')){ //se a string nao terminar com /
		action += '/';
	}
	
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o titulo <strong>' + descricaoTitulo + '</strong>?');
});


// executar algumas funcionalidades assim que terminar de carregar a pagina
$(function() {
	$('[rel="tooltip"]').tooltip() //procurar os componentes que sao tooltips e chame a funcao tooltip
});
