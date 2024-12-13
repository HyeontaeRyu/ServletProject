<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 13.
  Time: 오전 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%
    String realFolder = "";
    String saveFolder = "upload";
    String encType = "UTF-8";
    int maxSize = 10 * 1024 * 1024; //5MB
    ServletContext context = getServletContext();
    realFolder = context.getRealPath(saveFolder);

    ArrayList saveFiles = new ArrayList();
    ArrayList originFiles = new ArrayList();

    String paramUser = "";
    String paramTitle = "";
    String paramAbstract = "";
    try {
        MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType,
                new DefaultFileRenamePolicy());
        paramUser = multi.getParameter("txtUser");
        paramTitle = multi.getParameter("txtTitle");
        paramAbstract = multi.getParameter("txtAbstract");

        Enumeration files = multi.getFileNames();
        while (files.hasMoreElements()) {
            String name = (String) files.nextElement();
            saveFiles.add(multi.getFilesystemName(name));
            originFiles.add(multi.getOriginalFileName(name));
        }
%>
<html>
<head>
    <title>File Info Page</title>
</head>
<link href="style.css" type="text/css" rel="stylesheet">
<body>
<table width="75%" border="1" align="center" cellpadding="1"
       cellspacing="1" bordercolor="#660000" bgcolor="#FFFF99">
    <tr>
        <td width="10%" align="center" bgcolor="#ffcc00">
            <div align="right"><strong>user</strong></div>
        </td>
        <td width="30%">
            <%=paramUser%>
        </td>
        <td width="10%" bgcolor="ffcc00">
            <div align="right"><strong>title</strong></div>
        </td>
        <td width="30%">
            <%=paramTitle%>
        </td>
    </tr>
    <tr>
        <td width="10%" align="center" bgcolor="#ffcc00">
            <div align="right"><strong>abstract</strong></div>
        </td>
        <td width="50%" colspan="3">
            <textarea cols="50" rows="5"><%=paramAbstract%></textarea>
        </td>
    </tr>
    <tr>
        <td colspan="4" bgcolor="#ffffff">
            &nbsp;
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <strong>업로드 된 파일입니다.</strong>
        </td>
    </tr>
        <%
        for (int i = 0; i < saveFiles.size(); i++) {
    %>
    <tr bgcolor="#ffcc00">
        <td colspan="4">
            <a href="<%="/"+saveFolder+"/"+saveFiles.get(i)%>"><strong><%=originFiles.get(i)%>
            </strong></a>
        </td>
    </tr>
        <%
        }
    %>

        <%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>
