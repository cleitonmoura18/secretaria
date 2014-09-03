<head>
	<title>participacao [new]</title>
</head>
<body>
<ul>
<c:forEach items="${errors}" var="error">
  <li>${error.category} - ${error.message}</li>
</c:forEach>
</ul>
 <div id="mostrarResposta"  class="alert alert-success" hidden="">
 

 </div>
	<%@include file="form.jsp"%>
</body>