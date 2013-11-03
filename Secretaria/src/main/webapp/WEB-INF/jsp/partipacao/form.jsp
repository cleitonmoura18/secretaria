<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/partipacaos" method="post">
  
	<c:if test="${not empty partipacao.id}">
		<input type="hidden" name="partipacao.id" value="${partipacao.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/partipacaos">Back</a>
