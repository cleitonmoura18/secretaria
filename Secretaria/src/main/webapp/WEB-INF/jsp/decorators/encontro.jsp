<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>

<head>
<%@include file="head.jsp"%>
<decorator:head />
</head>

<body class="">
	<div class="container">
		<br>
		<%@include file="cabecalho.jsp"%>

		<div class="row">

			<%@include file="erro.jsp"%>
			<%@include file="menuEncontro.jsp"%>
			
			<div class="col-md-10">
				<decorator:body />
			</div>
		</div>
		<%@include file="rodape.jsp"%>
	</div>

</body>
<%@include file="script.jsp"%>


</html>