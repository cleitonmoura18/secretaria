<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="well">
<div class="text-right">
<button type="button" class="btn btn-warning" id="jovemButton">Inserir Joven</button>
<button type="button" class="btn btn-warning" id="padreButton">Inserir Padre</button>
<button type="button" class="btn btn-warning" id="tiosButton">Inserir Tios</button>
</div>
<form id="jovemForm"action="${pageContext.request.contextPath}/participacaos" method="post">
  
	<c:if test="${not empty participacao.id}">
		<input type="hidden" name="participacao.id" value="${participacao.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>
  	
        <div class="form-group">
			<legend>Jovem</legend>
        </div>
        <input type="hidden" name="participacao.pessoa.tipoPessoa" value="JOVEM"/>
        <input type="hidden" name="participacao.equipe.id" value="${participacao.equipe.id}"/>
        <input type="hidden" name="participacao.pessoa.id" value="${participacao.pessoa.id}"/>
        <div class="form-group">
        	<label for="nome" class="col-sm-3 control-label">Papel Na Equipe:</label>
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
        	<label for="nome" class="col-sm-3 control-label">Nome:</label>
        	<div class="col-sm-9">
        		<input type="text" name="participacao.pessoa.nome" id="nome" class="form-control" value="${participacao.pessoa.nome}" required="required" >
        	</div>
        </div>
        <div class="form-group">
        	<label for="dataNascimento" class="col-sm-3 control-label">Data de Nascimento:</label>
        	<div class="col-sm-9">
        		<input id="dataNascimento" name="participacao.pessoa.dataNascimento" type="date"
						value="${participacao.pessoa.dataNascimento}" class="form-control"  title="">
        	</div>
        </div>
        <div class="form-group">
        	<label for="email" class="col-sm-3 control-label">E-mail:</label>
        	<div class="col-sm-9">
        		<input id="email" name="participacao.pessoa.email" type="email"
						value="${participacao.pessoa.email}" placeholder="E-mail" class="form-control" >
        	</div>
        </div>
       <div class="form-group">
          <label for="endereco" class="col-sm-3 control-label">Endereço:</label>
          <div class="col-sm-9">
            <input id="endereco" name="participacao.pessoa.endereco" type="text"
            value="${participacao.pessoa.endereco}" placeholder="Logradouro com o Nº" class="form-control" >
          </div>
        </div>
          <div class="form-group">
          <label for="bairro" class="col-sm-3 control-label">Bairro:</label>
          <div class="col-sm-9">
            <input id="bairro" name="participacao.pessoa.bairro" type="text"
            value="${participacao.pessoa.bairro}"  class="form-control" >
          </div>
        </div>
               
			<div class="control-group text-right">
				<label class="control-label" for="singlebutton"></label>
				<div class="controls">
					<button id="singlebutton" 
						class="btn btn-primary">Salvar</button>
				</div>
			</div>
</form>
<form id="padreForm"
	action="${pageContext.request.contextPath}/participacaos" method="post"
	hidden="">

	<c:if test="${not empty participacao.id}">
		<input type="hidden" name="participacao.id" value="${participacao.id}" />
		<input type="hidden" name="_method" value="put" />
	</c:if>

	<div class="form-group">
		<legend>Padre</legend>
	</div>
	<input type="hidden" name="participacao.pessoa.tipoPessoa"
		value="PADRE" /> <input type="hidden" name="participacao.pessoa.id"
		value="${participacao.pessoa.id}" />
		  <input type="hidden" name="participacao.equipe.id" value="${participacao.equipe.id}"/>
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
		<label for="nome" class="col-sm-3 control-label">Nome:</label>
		<div class="col-sm-9">
			<input type="text" name="participacao.pessoa.nome" id="nome"
				class="form-control" value="${participacao.pessoa.nome}"
				required="required">
		</div>
	</div>
	
	<div class="form-group">
		<label for="dataNascimento" class="col-sm-3 control-label">Data
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
<form id="tiosForm"
	action="${pageContext.request.contextPath}/participacaos" method="post"
	hidden="">

	<c:if test="${not empty participacao.id}">
		<input type="hidden" name="participacao.id" value="${participacao.id}" />
		<input type="hidden" name="_method" value="put" />
	</c:if>

	<div class="form-group">
		<legend>Tios</legend>
	</div>
	  <input type="hidden" name="participacao.equipe.id" value="${participacao.equipe.id}"/>
	<input type="hidden" name="participacao.pessoa.tipoPessoa"
		value="CASAL" /> <input type="hidden" name="participacao.pessoa.id"
		value="${participacao.pessoa.id}" />
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
		<label for="nome" class="col-sm-3 control-label">Nome do Tio:</label>
		<div class="col-sm-9">
			<input type="text" name="participacao.pessoa.nome" id="nome"
				class="form-control" value="${participacao.pessoa.nome}"
				required="required">
		</div>
	</div>
	<div class="form-group">
	        	<label for="nomeConjugue" class="col-sm-3 control-label">Nome da Tia:</label>
	        	<div class="col-sm-9">
	        		<input type="text" name="participacao.pessoa.nomeConjugue" id="nomeConjugue" class="form-control" value="${participacao.pessoa.nomeConjugue}" required="required">
	        	</div>
	  </div>
	<div class="form-group">
		<label for="dataNascimento" class="col-sm-3 control-label">Data
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
  $("#padreForm").hide("slow");
  $("#tiosForm").hide("slow");
  $("#jovemForm").show("slow");
});
$( "#padreButton" ).click(function() {
  $("#jovemForm").hide();
  $("#tiosForm").hide("slow");
  $("#padreForm").show("slow");
});
$( "#tiosButton" ).click(function() {
  $("#jovemForm").hide("slow");
  $("#padreForm").hide("slow");
  $("#tiosForm").show("slow");
});
</script>
