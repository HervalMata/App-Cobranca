$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')) {
		action += '/';
	}
	action += codigoTitulo;
	form.attr('action', action);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo +'</strong>?');
});
//fecha a mensagem altomatica
$('.alert-dismissible').fadeTo(2000, 500).slideUp(500, function(){
    $('.alert-dismissible').alert('close');
});

$(function() {
	//configuraçao do tooltip
	$('[rel="tooltip"]').tooltip();
	
	//configuraçao do plugin de moeda
	$('.js-currency').maskMoney({decimal:',', thousands: '.', allowZero: true});
	
	
	//atualizar status do titulo
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT',
			
		});
		
		response.done(function(response) {
			var codigoTitulo = botaoReceber.data('codigo');
			$('[data-role=' + codigoTitulo + ']').html('<span class="label label-success">' + response + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(response) {
			console.log(response);
		});
		
	});
	
	
	//opçao para configuração do plugin de calendário
//	$('.ik-datepicker').datepicker({
//	    format: 'dd/mm/yyyy',                
//	    autoclose: true,
//	    language:'pt-BR',
//	    todayHighlight:true,
//	    title:'Selecione uma data'
//	});
});