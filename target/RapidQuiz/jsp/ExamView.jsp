<%@page import="in.com.online.exam.controller.ExamCtl"%>
<%@page import="in.com.online.exam.util.DataUtility"%>
<%@page import="in.com.online.exam.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Exam</title>


</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 13px ;margin-top: 68px" >
<h2 style="color: white; font-style:normal;">Manage Exam</h2></div>
<br>
<div class="container">
    <h1 class="well">Manage Exam</h1>
   <b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=ORSView.EXAM_CTL%>"  method="post" >
				
				<jsp:useBean id="bean" class="in.com.online.exam.bean.ExamBean"
			scope="request"></jsp:useBean>
			
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				
			<div class="col-sm-12">
				<div class="row">						
				<div class="col-sm-6 form-group">			
					<div class="form-group">
						<label>Category</label>
						<input type="text" name="category" placeholder="Enter Category Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getExamCategory())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("category", request)%></font></b>
					</div>
				</div>
					
					<div class="col-sm-6 form-group">
					<div class="form-group">
						<label>Exam Name</label>
						<input type="text" name="examName" placeholder="Enter Exam Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getExamName())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("examName", request)%></font></b>
					</div>
				</div>
						
				</div>		
						
					<div class="form-group">
						<label>Exam Date</label>
						<input type="text" name="examDate" id="datepicker" placeholder="Enter Exam  Date(dd/MM/yyyy)  Here.." class="form-control" value="<%=DataUtility.getDateString(bean.getExamDate())%>" id="datepicker">
						<b><font color="red"> <%=ServletUtility.getErrorMessage("examDate", request)%></font></b>
					</div>
				
					<input type="submit" name="operation" class="btn btn-lg btn-info"
						value="<%=ExamCtl.OP_SAVE%>">					
					</div>
				</form> 
				</div>
	</div>
	</div>
	<br><br>
	<hr>
	<br><br>
<%@include file="Footer.jsp"%>
</body>
</html>