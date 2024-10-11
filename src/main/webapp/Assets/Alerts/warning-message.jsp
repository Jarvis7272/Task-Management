
<%
String WarningMessage = (String) session.getAttribute("WarningMessage");
if (WarningMessage != null) {
%>
<div class="alert alert-warning alert-dismissible fade show"
	role="alert">
	<strong><%=WarningMessage%></strong>
	<button type="button" class="btn-close" data-bs-dismiss="alert"
		aria-label="Close"></button>
</div>
<%
session.removeAttribute("WarningMessage");
}
%>