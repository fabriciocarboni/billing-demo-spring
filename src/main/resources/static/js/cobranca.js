$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget); //captura o botao
	
	var codigoTitulo = button.data('codigo'); // captura o valor do codigo passado ao clicar no botao
		
	var modal = $(this);
	var form = modal.find('form'); //procura o form
	var action = form.attr('action'); //pegar a string do action do form
	if (!action.endsWith('/')){ //se a string nao terminar com /
		action += '/';
	}
	
	form.attr('action', action + codigoTitulo);
});



