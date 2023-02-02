<%
HttpSession sessao = request.getSession();
String mensagem = (String) sessao.getAttribute("mensagem");
if (mensagem != null) {
	String msg = mensagem;
	out.println("<script>var mensagem = '" + mensagem + "';</script>");
	session.removeAttribute("mensagem");
}
%>