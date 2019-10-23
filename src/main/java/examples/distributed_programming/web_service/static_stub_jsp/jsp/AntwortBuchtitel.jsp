<%@ page import="javax.xml.rpc.Stub,javax.naming.*,bookwebclient.*"%>
<%
    String resp = null;
    try {
        Stub stub = (Stub) (new MyBookService_Impl().getBookIFPort());
        stub._setProperty(javax.xml.rpc.Stub.ENDPOINT_ADDRESS_PROPERTY,
                "http://localhost:8080/book-jaxrpc/book");
        BookIF book = (BookIF) stub;
        resp = book.getBookTitle(request.getParameter("isbn-nummer"));

    } catch (Exception ex) {
        resp = ex.toString();
    }
%>
<h2>
	<font color="black"><%=resp%>!</font>
</h2>















