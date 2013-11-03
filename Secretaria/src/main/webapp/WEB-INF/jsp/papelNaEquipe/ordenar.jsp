<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="mostrarResposta" hidden="" class="alert "></div>
<div class="panel panel-primary">
		<div class="panel-heading " align="center">
			<h3 class="panel-title text-center" style="position: relative">Ordenar Papeis de Equipes
			<button id="salvarOrdenacao" type="button" class="btn btn-success" style="position: absolute; right: -8px; top: -8px">Salvar Ordenação</button>
			</h3>
		</div>
		<div class="row grid span8">
			<c:forEach items="${encontro.papeisNaEquipe}" var="papelnaEquipe">
				<div class="well span2 tile" id="${papelnaEquipe.id}">${papelnaEquipe.nome}</div>
			</c:forEach>
		</div>

</div>
	
<script type="text/javascript">//<![CDATA[ 
//Função para ordenar as equipes
$(function () {
    $(".grid").sortable({
        tolerance: 'pointer',
        revert: 'valid',
        placeholder: 'span2 well placeholder tile',
        forceHelperSize: true,
        
    });
    $(".well.span2.tile").click(
    		function() {
    	    	$("#mostrarResposta").hide();
    	    	$("#mostrarResposta").removeClass();
    	    });
  
   
});
//Obtem os id dos objetos ordenados

	$(function() {
		$("#salvarOrdenacao").click(function() {
			var idsInOrder = $(".grid").sortable("toArray");
			//chamar aqui método post
			$.post("${pageContext.request.contextPath}/papelNaEquipes/salvar/ordem", {
				ids : idsInOrder.toString()
			}).done(function(data) {
				mostrarResposta(data);
			});
		})
	});
//Colocar div de sucesso ou erro com mensagem
function mostrarResposta(data){
	 $("#mostrarResposta").show("slow");
	 $("#mostrarResposta").text(data.mensagem.mensagem)
	 $("#mostrarResposta").addClass("alert")
	if(data.mensagem.enumMensagem=='OK'){
		  $("#mostrarResposta").addClass("alert-success")
	}else{
		  $("#mostrarResposta").addClass("alert-danger")
	}
}
	//]]>
</script>