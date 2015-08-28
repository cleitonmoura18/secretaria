<head>
<title>Usuario [edit]</title>
</head>
<body>
	<c:forEach items=${errors } var="error">
		<li>${error.category}-${error.message}</li>
	</c:forEach>
	<%@include file="form.jsp"%>
</body>