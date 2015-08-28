<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="head.jsp"%>
<decorator:head />
</head>
<body class="">
	<div class="container" id="paroquias-app">
		<br>
		<%@include file="cabecalho.jsp"%>

		<div class="row">

			<%@include file="erro.jsp"%>

			<c:choose>
				<c:when test="${not empty usuarioSession.user}">
					<%@include file="menuEncontro.jsp"%>
					<div class="col-md-10">
				</c:when>
				<c:otherwise>
					<div class="col-md-12">
				</c:otherwise>
			</c:choose>


			<decorator:body />
		</div>
	</div>
	<%@include file="rodape.jsp"%>
	</div>
</body>
<%@include file="script.jsp"%>
</html>