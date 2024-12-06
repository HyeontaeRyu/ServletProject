<%@ page import="com.member.ZipCodeDTO" %>
<%@ page import="java.util.Vector" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 12. 6.
  Time: 오후 3:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="dao" class="com.member.StudentDAO"/>
<%
    request.setCharacterEncoding("UTF-8");
    String check = request.getParameter("check");
    String dong = request.getParameter("dong");

    Vector<ZipCodeDTO> v = dao.zipCodeRead(dong);

    int totalList = v.size();

%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../jdbc/style.css">
    <script src="script.js"></script>
</head>
<body bgcolor="#ffffcc">
<div align="center">
    <b>우편번호 찾기</b>
    <form action="zipCheck.jsp" method="post" name="zipForm">
        <table>
            <tr>
                <td>동 이름 입력 : <input type="text" name="dong">
                    <input type="button" value="검색" onclick="dongCheck()">
                </td>
            </tr>
        </table>
        <input type="hidden" name="check" value="n">
    </form>
    <table>
        <%
            if (check.equals("n")) {
                if (v.isEmpty()) {
        %>
        <tr>
            <td align='center'><br> 검색 결과가 없습니다.</td>
        </tr>
        <%
        } else {
        %>
        <tr>
            <td align='center'><br> 검색 후 우편번호를 클릭하면 자동으로 입력됩니다.</td>
        </tr>
        <%
            for (int i = 0; i < totalList; i++) {
                ZipCodeDTO dto = v.elementAt(i);

                String tempZipcode = dto.getZipcode();
                String tempSido = dto.getSido();
                String tempGugun = dto.getGugun();
                String tempDong = dto.getDong();
                String tempRi = dto.getRi();
                String tempBunji = dto.getBunji();

                if (tempRi == null) {
                    tempRi = "";
                }
                if (tempBunji == null) {
                    tempBunji = "";
                }

        %>
        <tr>
            <td>

                <a href="javascript:sendAddress('<%=tempZipcode%>', '<%=tempSido%>', '<%=tempGugun%>', '<%=tempDong%>', '<%=tempRi%>', '<%=tempBunji%>');">
                    <%=tempZipcode%>&nbsp;<%=tempSido%>&nbsp;<%=tempGugun%>&nbsp;<%=tempDong%>&nbsp;<%=tempRi%>&nbsp;<%=tempBunji%>
                </a><br>
            </td>
        </tr>
        <%
                    }
                }
            }
        %>
        <tr>
            <td align="left"><a href="javascript:this.close();">닫기</a></td>
        </tr>
    </table>
</div>
</body>
</html>
