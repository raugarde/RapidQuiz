<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.com.online.exam.model.ExamModel"%>
<%@page import="in.com.online.exam.bean.ExamBean"%>
<%@page import="in.com.online.exam.util.ServletUtility"%>
<%@page import="in.com.online.exam.controller.CategoryListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 13px ;margin-top: 68px" >
<h2 style="color: white; font-style:normal;">Category</h2></div>
<br><br>
<form action="<%=ORSView.CATEGORY_LIST_CTL%>" method="post">
<div class="container">
	<div class="col-lg-12 well">
	<div class="row">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								
								<input type="text" placeholder="Enter Category Here.." name="category" class="form-control"  >
								
							</div>
							<div class="col-sm-6 form-group">
								
								<input type="submit"
                        name="operation" class="btn btn-md btn-info" value="<%=CategoryListCtl.OP_SEARCH%>" >
							</div>
						</div>					
			</div>		
		</div>
	</div>
</div>
<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
									</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
									</font></b>
<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">S.No.</th>
					<th scope="col">Category</th>
					<th scope="col">Subject</th>
					<th scope="col">Click To Start</th>
				</tr>
			</thead>
			 <%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					ExamBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<ExamBean> i = list.iterator();
					while (i.hasNext()) {
						bean = i.next();
				%> 
			<tbody>
				<tr>
					<th scope="row"><%=index++%></th>
					<td><%=bean.getExamCategory()%></td>
					<td><%=bean.getSubject_description()%></td>
					<td><a href="StartExamCtl?ct=<%=bean.getExamCategory()%>"  class="btn btn-success" >StartExam</a>
				</tr>
<%
					}
				%>
			</tbody>
		</table>
		
		<table width="99%">
				<tr>
					<td><input type="submit" name="operation" class="btn btn-success"
						value="<%=CategoryListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>

				
					<%
						ExamModel model = new ExamModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-success"
						value="<%=CategoryListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>

				</tr>
			</table>
		
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
<br><br><br>
	<%@ include file="Footer.jsp"%>
</body>
</html>