/**
* @desc Conjunto de funções genéricas
*
* @author: Eduardo Marinho, Leonardo Costa
* @version class: 1.1 - 27/09/2018
**/
var menuOpen = false;
$(function(){

	//Ativa os scripts do Jquery
	//$(document).tooltip();

	//Exibi o botão de rolagem
	$(window).resize( function(){ upper();});
	
	/** 
	* @desc: Menu do usuário
	**/
	$(".user").click(function(){
		
		if(menuOpen==false){
			$(".user").css("background-color","rgba(255,255,255,1)");
			$("#containMenUsuario").toggle();
			menuOpen = true;
		}else{
			$(".user").css("background-color","rgba(255,255,255,0)");
			$("#containMenUsuario").toggle();
			menuOpen = false;
		}
	});
	$("#content-main,.title,.menu-navegacao").hover(function(){
		$("#content-main").css("filter", "opacity(100%)");
		$(".user").css("background","none");
		$("#containMenUsuario").slideUp();
	});

	/**
	* @desc: Caixas de informação
	**/
	$("button.help").click(function(){
		$("article.help").toggle("slow");
	});

	//Rolar a página para o topo
	$(".upper").click(function(){
		$("html, body").animate({scrollTop:0},100);
	});
});

/**
* @desc:marcar todas as checkbox
**/
function selectAllCheckbox(cbox,name){
	$("input[name='"+name+"']").prop('checked', cbox.checked);
}

/**
* @desc: apagar os itens selecionados.
**/
function apagarItens(name, controlador, acao){
	
	loader(true);
	//caso não tenha nenhum checkbox com o mesmo name selecionado
	if(jQuery("input[name='"+name+"']:checkbox:checked").map(function() {return this.value;}).get().length === 0){
		alert("Selecione um item");
		loader(false);
		return false;
	}else{
	//mostrando uma janela de confirmação
	if(confirm("Tem certeza que deseja apagar este item?")) {
	//caso confirma exclusao, apagar os intens selecionados via ajax
		$.ajax({
		url: "?ctrl="+controlador+"/"+acao,
		type: "POST",
		data: $("input[name='"+name+"']:checkbox:checked").serialize(),
		dataType: "html",
		success: function(retorno){
			//após executar o controlador/acao, recarrecar a página
			modal("teste", retorno, 5);
			//document.location.reload(true);
			loader();
			},
		}); 
		}
	}
	loader(false);
}

function warning(mensagem, cor, align){
	if(mensagem == false){
		$(".wb").slideUp("fast").empty();
	}else{
		if(typeof(aling) == "undefined"){ var align = "left";}
		$(".wb").html(mensagem).attr("class", "wb " + align + " " + cor).fadeIn('fast');	
	}
}
function redirect(path){
	loader(true);
	window.location.href= path + ".xhtml?faces-redirect=true";
}

/*======================================================*
* @desc: conjunto de SCRIPTS de gerenciamento de tela;
* @Tips: MODAL, LOADER	
*=======================================================*/

/**
* @desc: conjunto de SCRIPTS de gerenciamento de tela;
* @param: id (String), conteudo (MIXED), camada (integer), parent(String)	
**/
function modal(id, conteudo, camada, parent){
	
	var modal = document.createElement("DIV");
	modal.setAttribute("class", "modal");
	modal.setAttribute("id", id);
	if(parent!=null){
		document.getElementById(parent).appendChild(modal);
	}else{
		document.body.appendChild(modal);	
	}
	$("#" + id + "").html(conteudo);
	$("#"+id+"").css("z-index", camada);
}

/**
*	@desc: fecha o modal.
*	@param: id(String), o id do elemento que será fechado.
**/
function modalClose(id){
	var body = document.getElementById("body");
	body.removeChild(document.getElementById(id));
}

/**
*	@desc: finaliza todos os modais, passando o MODAL PAI.
*	@param: parent(String), o id do elemento pai.
**/
function modalDestroy(parent){

	var modal = document.getElementById(parent);
	modal.parentNode.removeChild(document.getElementById(parent));
}

/**
* @desc: carrega a tela de loader;
* @param: boolean TRUE or FALSE; 
**/
function loader(param){
	(param==true) ? $(".loader").fadeIn() : $(".loader").fadeOut();
}

/**
* @desc: Exibe o botão de rolagem
**/
function upper(){
	($(".main").height()) > 500 ? $(".upper").show() :  $(".upper").hide();
}

/**
* @desc: após clicar no botão, o elemento contento o contexto será exibido!
**/
function dropDown(id){
	document.getElementById(id).classList.toggle("show");
	return false;
}

/**
* @desc: fecha o dropDown caso perca o foco!
**/
window.onclick = function(event) {
	if(!event.target.matches('.dropbtn')){
    	var dropdowns = document.getElementsByClassName("dropdown-content");
    	var i;
    	for (i = 0; i < dropdowns.length; i++) {
      		var openDropdown = dropdowns[i];
      		if (openDropdown.classList.contains('show')) {
        		openDropdown.classList.remove('show');
      		}
    	}
  	}
}

/*======================================================*
* @desc: conjunto de SCRIPTS de gerenciamento de tela;
* @Tips: MODAL, LOADER	
*=======================================================*/

// função para realizar a paginação via ajax
function paginar(urlParam, limite, pagina) {
	$.ajax({
		url: urlParam+"&lim="+limite+"&pag="+pagina,
		type: "GET",
		dataType: "html",
		success: function(retorno){
			$("#conteudo-tabela").html(retorno);
		}
	});
}
//Exibe o botão de rolagem
function upper(){
	($(".main").height()) > 500 ? $(".upper").show() :  $(".upper").hide();
}