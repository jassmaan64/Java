<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Registration Page</h2>
	
	<%
		String error = (String) request.getAttribute("error");
		if(error != null){
			out.println(error);
		}

	%>
	<form action = "register" method = "post">
		Username: <input type = "text" name ="username" required><br>
		Password: <input type = "password" name = "password" required><br>
		Email: <input type = "text" name ="email" required><br>
		Status: <input type = "text" name ="status" required><br>
		
		<input type = "submit" value ="register">
	</form>
</body>
</html>