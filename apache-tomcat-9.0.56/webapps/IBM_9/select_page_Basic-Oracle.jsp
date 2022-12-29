<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>

<HTML>
<HEAD>
<TITLE> 查詢員工資料 </TITLE>
</HEAD>
<BODY>
<%
       Context ctx = new javax.naming.InitialContext();
       DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
       Connection con =  ds.getConnection();
       Statement stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery("SELECT EMPNO 員工編號,  ENAME 員工姓名,  JOB 職位, HIREDATE 雇用日期,  SAL 薪水 from emp2");
            
       ResultSetMetaData rsmd = rs.getMetaData();
	     int numberOfColumns = rsmd.getColumnCount();
%>

 <table border="1" bordercolor="blue">

     <tr> 
       <% for (int i = 1; i <= numberOfColumns; i++) { %>
	         <th><%=rsmd.getColumnName(i)%></th>
	     <% } %>      
	   </tr>
   
   <% while (rs.next()) { %>
     <tr>
        <% for (int i = 1; i <= numberOfColumns; i++) { %> 
           <td><%=rs.getString(i)%></td>
        <% } %>
     </tr>
   <% } %>

 </table>

<%con.close(); %>

</BODY>
</HTML>