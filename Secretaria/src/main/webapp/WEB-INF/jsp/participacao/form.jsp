<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="well">
<div class="text-right">
<button type="button" class="btn btn-warning" id="jovemButton">Inserir Joven</button>
<button type="button" class="btn btn-warning" id="padreButton">Inserir Padre</button>
<button type="button" class="btn btn-warning" id="tiosButton">Inserir Tios</button>
</div>
<form id="form" action="${pageContext.request.contextPath}/participacaos" method="post"
	>

	<c:if test="${not empty participacao.id}">
		<input type="hidden" name="participacao.id" value="${participacao.id}" />
		<input type="hidden" name="_method" value="put" />
	</c:if>

	<div class="form-group">
		<legend id="titulo">Jovem</legend>
	</div>
	<input type="hidden" name="participacao.equipe.id" value="${participacao.equipe.id}"/>
	<input type="hidden" name="participacao.pessoa.tipoPessoa" id="tipoPessoa" value="JOVEM" />
	 <input type="hidden" name="participacao.pessoa.id"	value="${participacao.pessoa.id}" />
	<div class="form-group">
		<label for="nome" class="col-sm-3 control-label">Papel Na
			Equipe:</label>
		<div class="col-sm-9">
			<select name="participacao.papelNaEquipe.id" id="papelNaequipe"
				class="form-control">
				<option value=""></option>
				<c:forEach items="${papeis}" var="papel">
					<option value="${papel.id}"
						<c:if test="${participacao.papelNaEquipe.id==papel.id}">selected="selected"</c:if>>${papel.nome}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="nome" class="col-sm-3 control-label" id="nomeLabel">Nome:</label>
		<div class="col-sm-9">
			<input type="text" name="participacao.pessoa.nome" id="nome"
				class="form-control" value="${participacao.pessoa.nome}"
				required="required">
		</div>
	</div>
	<div class="form-group" id="tiaDiv" hidden="">
	        	<label for="nomeConjugue" class="col-sm-3 control-label">Nome da Tia:</label>
	        	<div class="col-sm-9">
	        		<input type="text" name="participacao.pessoa.nomeConjugue" id="nomeConjugue" class="form-control" value="${participacao.pessoa.nomeConjugue}" required="required">
	        	</div>
	  </div>
	<div class="form-group">
		<label for="dataNascimento" class="col-sm-3 control-label" id="data">Data
			de Ordenação:</label>
		<div class="col-sm-9">
			<input id="dataNascimento" name="participacao.pessoa.dataNascimento"
				type="date" value="${participacao.pessoa.dataNascimento}"
				class="form-control" title="">
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-3 control-label">E-mail:</label>
		<div class="col-sm-9">
			<input id="email" name="participacao.pessoa.email" type="email"
				value="${participacao.pessoa.email}" placeholder="E-mail"
				class="form-control">
		</div>
	</div>
	<div class="form-group">
		<label for="endereco" class="col-sm-3 control-label">Endereço:</label>
		<div class="col-sm-9">
			<input id="endereco" name="participacao.pessoa.endereco" type="text"
				value="${participacao.pessoa.endereco}"
				placeholder="Logradouro com o Nº" class="form-control">
		</div>
	</div>
	<div class="form-group">
		<label for="bairro" class="col-sm-3 control-label">Bairro:</label>
		<div class="col-sm-9">
			<input id="bairro" name="participacao.pessoa.bairro" type="text"
				value="${participacao.pessoa.bairro}" class="form-control">
		</div>
	</div>

	<div class="control-group text-right">
		<label class="control-label" for="singlebutton"></label>
		<div class="controls">
			<button id="singlebutton" class="btn btn-primary">Salvar</button>
		</div>
	</div>
</form>
</div>
<a href="${pageContext.request.contextPath}/participacaos">Back</a>
<script type="text/javascript">
//Mudar de Form
$( "#jovemButton" ).click(function() {
	$("#tiaDiv").hide("slow");
	$("#nomeLabel").text("Nome:");
	$("#titulo").text("Jovem");
	$("#tipoPessoa").val("JOVEM");
	$("#data").text("Data de Nascimento:");
});
$( "#padreButton" ).click(function() {
	$("#tiaDiv").hide("slow");
	$("#nomeLabel").text("Nome:");
	$("#titulo").text("Padre");
	$("#data").text("Data de Ordenação:");
	$("#tipoPessoa").val("PADRE");
});
$( "#tiosButton" ).click(function() {
  $("#tiaDiv").show("slow");
  $("#nomeLabel").text("Nome do Tio:");
  $("#titulo").text("Tios");
  $("#tipoPessoa").val("CASAL");
  $("#data").text("Data	de Casamento:");
});
$('#singlebutton').click(function(event){
	 event.preventDefault();
	$.post("${pageContext.request.contextPath}/participacaos", $("#form").serialize()).done(function(data) {
		console.log(data);
	});
});

</script>
