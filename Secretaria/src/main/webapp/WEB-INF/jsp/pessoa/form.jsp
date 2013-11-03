<body style="cursor: auto;">
<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>
<div class="well">
<form action="${pageContext.request.contextPath}/pessoas" method="POST" class="form-horizontal" role="form">
		<c:if test="${not empty pessoa.id}">
			<input type="hidden" name="pessoa.id" value="${pessoa.id}" />
			<input type="hidden" name="_method" value="put" />
		</c:if>
		<input type="hidden" name="pessoa.tipoPessoa" value="${pessoa.tipoPessoa }"/>
        <div class="form-group">
           <c:choose>
				<c:when test="${pessoa.tipoPessoa.descricao=='Casal'}">
					<legend>Tios</legend>
				</c:when>
				<c:when test="${pessoa.tipoPessoa.descricao=='Padre'}">
					<legend>Padre</legend>
				</c:when>
				<c:otherwise>
					<legend>Joven</legend>
				</c:otherwise>
			</c:choose>
        </div>
        <div class="form-group">
        	<label for="nome" class="col-sm-2 control-label">
        		<c:choose>
					<c:when test="${pessoa.tipoPessoa.descricao=='Casal'}">
			    	  	Nome do Tio:
					</c:when>
					<c:otherwise>
						Nome:
			      	</c:otherwise>
				</c:choose>
        	</label>
        	<div class="col-sm-10">
        		<input type="text" name="pessoa.nome" id="nome" class="form-control" vvalue="${pessoa.nome}" required="required" >
        	</div>
        </div>
        <c:if test="${pessoa.tipoPessoa.descricao=='Casal'}">
	        <div class="form-group">
	        	<label for="nomeConjugue" class="col-sm-2 control-label">Nome da Tia:</label>
	        	<div class="col-sm-10">
	        		<input type="text" name="pessoa.nomeConjugue" id="nomeConjugue" class="form-control" value=value="${pessoa.nomeConjugue}" required="required">
	        	</div>
	        </div>
        </c:if>
        <div class="form-group">
        	<label for="dataNascimento" class="col-sm-2 control-label">${pessoa.tipoPessoa.data }:</label>
        	<div class="col-sm-10">
        		<input id="dataNascimento" name="pessoa.dataNascimento" type="date"
						value="${pessoa.dataNascimento}" class="form-control" value=""  title="">
        	</div>
        </div>
        <div class="form-group">
        	<label for="email" class="col-sm-2 control-label">E-mail:</label>
        	<div class="col-sm-10">
        		<input id="email" name="pessoa.email" type="email"
						value="${ pessoa.email}" placeholder="E-mail" class="form-control" >
        	</div>
        </div>
       <div class="form-group">
          <label for="endereco" class="col-sm-2 control-label">Endereço:</label>
          <div class="col-sm-10">
            <input id="endereco" name="pessoa.endereco" type="text"
            value="${ pessoa.endereco}" placeholder="Logradouro com o Nº" class="form-control" >
          </div>
        </div>
          <div class="form-group">
          <label for="bairro" class="col-sm-2 control-label">Bairro:</label>
          <div class="col-sm-10">
            <input id="bairro" name="pessoa.bairro" type="text"
            value="${ pessoa.bairro}"  class="form-control" >
          </div>
        </div>
               
			<div class="control-group text-right">
				<label class="control-label" for="singlebutton"></label>
				<div class="controls">
					<button id="singlebutton" name="singlebutton"
						class="btn btn-primary">Salvar</button>
				</div>
			</div>
</form>    
</div>
<a href="${pageContext.request.contextPath}/pessoas">Back</a>
</body>
