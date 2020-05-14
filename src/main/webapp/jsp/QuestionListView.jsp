<%@page import="in.com.online.exam.model.QuestionModel"%>
<%@page import="in.com.online.exam.controller.QuestionListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.com.online.exam.bean.QuestionBean"%>
<%@page import="java.util.List"%>
<%@page import="in.com.online.exam.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Question</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 13px ;margin-top: 68px" >
<h2 style="color: white; font-style:normal;">Question</h2></div>
<br><br>
<form action="<%=ORSView.QUESTION_LIST_CTL%>" method="post">

<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
									</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
									</font></b>
<table class="table">
			<thead class="thead-dark">
			<%
							int count=(int)request.getAttribute("count");
			int size=(int)request.getAttribute("size");
							int pageNo=ServletUtility.getPageNo(request);
							int pageSize=ServletUtility.getPageSize(request);
							int index=((pageNo-1)*pageSize)+1;
							List list=ServletUtility.getList(request);
							QuestionBean bean=null;
							Iterator<QuestionBean> iterator=list.iterator();
							while(iterator.hasNext()){
								 bean=iterator.next();
			%>
				<tr>
					<th scope="col"><input type="hidden" name="question" value="<%=bean.getQuestionName()%>">Ques.<%=bean.getQuestionName()%></th>
				</tr>
			</thead>
			 
			<tbody>
				<tr>
					<td scope="row"><input type="checkbox" name="ans" value="<%=bean.getOption1()%>"><%="   "+bean.getOption1()%></td>
				</tr>
				<tr>
					<td scope="row"><input type="checkbox" name="ans" value="<%=bean.getOption2()%>"><%="   "+bean.getOption2()%></td>
				</tr>	
				<tr>	
					<td scope="row"><input type="checkbox" name="ans" value="<%=bean.getOption3()%>"><%="   "+bean.getOption3()%></td>
				</tr>
				<tr>
					<td scope="row"><input type="checkbox" name="ans" value="<%=bean.getOption4()%>"><%="   "+bean.getOption4()%></td>
				</tr>	
				
				<input type="hidden" name="examName" value="<%=bean.getExamName() %>">
<%
					}
				%>
			</tbody>
		</table>
		
		<table width="99%">
				<tr>
					<td><input type="submit" name="operation" class="btn btn-success"
						value="<%=QuestionListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
						
						
						
						<%if(size==pageNo){ %>
						<td><input type="submit" name="operation" class="btn btn-success"
						value="<%=QuestionListCtl.OP_SUBMIT%>"></td>
						<%} %>
				
					<%
						QuestionModel model = new QuestionModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-success"
						value="<%=QuestionListCtl.OP_NEXT%>"
						
						<%=((list.size() < pageSize) || size==pageNo) ? "disabled" : ""%>></td>

				</tr>
			</table>
		
							<input type="hidden" name="count" value="<%=count%>">
							<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
							<input type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<br><br><br>
	<%@ include file="Footer.jsp"%>
</body>
</html>