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

$('.alert-dismissible').fadeTo(2000, 500).slideUp(500, function(){
    $('.alert-dismissible').alert('close');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
});