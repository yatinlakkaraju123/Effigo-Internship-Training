<%@ include file="common/header.jspf"   %>
			<%@ include file="common/navigation.jspf"   %>
			<div class="container"><h1>Welcome to Login Page </h1>
				<pre>${msg}</pre>
				<form method="post">
				Name:<input type="text" name="name"/><br/><br/><br/>
				Password:<input type="password" name="password"/>
				<button type="submit">Submit</button>	
				</form></div>
				<%@ include file="common/footer.jspf"   %>