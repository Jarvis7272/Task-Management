<%
String DangerMessage = (String) session.getAttribute("DangerMessage");
if (DangerMessage != null) {
%>
<div class="alert alert-danger alert-dismissible fade show"
	role="alert">
	<strong><%=DangerMessage%></strong>
	<button type="button" class="btn-close" data-bs-dismiss="alert"
		aria-label="Close"></button>
</div>
<%
session.removeAttribute("DangerMessage");
}
%>