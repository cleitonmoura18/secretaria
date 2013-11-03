
<form action="${pageContext.request.contextPath}/papelNaEquipes" method="post">
  
	<c:if test="${not empty papelNaEquipe.id}">
		<input type="hidden" name="papelNaEquipe.id" value="${papelNaEquipe.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Nome:<br />
	
		<input type="text" name="papelNaEquipe.nome" value="${papelNaEquipe.nome}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/papelNaEquipes">Back</a>
