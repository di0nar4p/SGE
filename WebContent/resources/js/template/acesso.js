function validarAcesso(form){
	
	id = form.attr("id");
	if($("input[name='" + id + ":usuario']").val() == ""){
		alert("Preencha o usuário!");
		return false;
	}else if($("input[name='" + id + ":senha']").val() == ""){
		alert("Preencha a senha!");
		return false;
	}else{
		$("#"+id).submit();
	}
}
/**
* @description: oculta o alerta.
**/
$("#usuario, #senha").focus( function(){
	warning(false);
});

/**
* @description: captura a tecla ENTER caso seja pressionada.
**/
document.onkeydown = function(event){
	if(event.keyCode == 13){
		autenticar();
	}
};

/**
* @description: oculta o alerta.
**/
function autenticar(){
	warning(false);
	var erro = false;
	if($("#usuario").val() == '') {
		erro = true;
		erroMsg('Preencha o campo usuário');
	}
	if($("#senha").val() == '') {	
		erro = true;
		erroMsg('Preencha o campo senha');
	}
	if(!erro) {
		loader(true);
		$.ajax({
			url: "?ctrl=login/autenticar",
			type: "POST",
			data: $("#formAutenticacao").serialize(),
			dataType: "json",
			success: function(retorno) {							
				if(retorno.codigo == '200') {
					window.location.href = "?ctrl=home";
				}
				else {
					warning(retorno.descricao, "red");
					$("#senha").val('');
				}
				loader(false);
			}
		});	
	}
}
function erroMsg(t) {
	if($(".wb").text() == ''){
		$(".wb").text(t);
		warning($(".wb").text(), "red", "right");
		loader(false);
	}else{
		warning( ($(".wb").text() + "</br>" + t), "red" );
	}
}