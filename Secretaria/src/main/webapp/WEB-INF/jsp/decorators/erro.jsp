	<c:if test="${not empty errors}">
	<div class="alert alert-danger text-center">
		<c:forEach items="${errors}" var="error">
		 <strong>- ${error.message}</strong>
		</c:forEach>
	</div>
	</c:if>