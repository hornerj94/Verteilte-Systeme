<html>
<head>
<title>Suche von Buchtiteln</title>
</head>
<body bgcolor="white">
	<h1>
		Suche von Buchtiteln<br> (mit Hilfe eines Web Services
	</h1>
	<h2>
		Geben Sie bitte eine ISBN-Nummer ein<br> (eine von: 0130895601,
		0130895717, 0130293636, 0130284173, 0130923613)
	</h2>
	<form method="get">
		<input type="text" name="isbn-nummer" size="25">
		<p></p>
		<input type="submit" value="Submit"> <input type="reset"
			value="Reset">
	</form>

	<%
	    String isbn = request.getParameter("isbn-nummer");
	    if (isbn != null && isbn.length() > 0) {
	%>
	<%@include file="AntwortBuchtitel.jsp"%>
	<%
	    }
	%>
</body>
</html>

