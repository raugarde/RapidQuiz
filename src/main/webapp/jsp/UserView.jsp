<%@page import="in.com.online.exam.controller.UserCtl"%>
<%@page import="in.com.online.exam.util.DataUtility"%>
<%@page import="in.com.online.exam.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 13px ;margin-top: 68px" >
<h2 style="color: white; font-style:normal;">User</h2></div>
<br>
<div class="container">
    <h1 class="well">User</h1>
   <b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br><br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=ORSView.USER_CTL%>"  method="post" >
				
				<jsp:useBean id="bean" class="in.com.online.exam.bean.UserBean"
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
								<label>First Name</label>
								<input type="text" name="firstName" placeholder="Enter First Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getFName())%>" >
								<b><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></b>
							</div>
							<div class="col-sm-6 form-group">
								<label>Last Name</label>
								<input type="text" name="lastName" placeholder="Enter Last Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getLName())%>">
								<b><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></b>
							</div>
						</div>					
							
					<div class="form-group">
						<label>Student Id(Email)</label>
						<input type="text" name="login" placeholder="Enter Email Address Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getLogin())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></b>
					</div>
					
					<div class="form-group">
						<label>Password</label>
						<input type="password" name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></b>
					</div>
						
					<div class="form-group">
						<label>User Type</label>
						<select name="role_Id" class="form-control">
						  	<%
						  		int role_value = (int) bean.getRole_Id();
						  		if(role_value < 0){
						  			role_value = 1;
						  		}
						  		int role_opt1 = 2;
						  		int role_opt2 = 3;
						  		String role_main_str = "Admin";
						  		String role_opt1_str = "Student";
						  		String role_opt2_str = "Teacher";
						  		if(role_value==2){
						  			role_opt1 = 1;
						  			role_opt2 = 3;
							  		role_main_str = "Student";
						  			role_opt1_str = "Admin";
						  			role_opt2_str = "Teacher";
						  		}
						  		else if(role_value==3){
						  			role_opt1 = 1;
						  			role_opt2 = 2;
						  			role_main_str = "Teacher";
						  			role_opt1_str = "Admin";
						  			role_opt2_str = "Student";
						  		}
						  		else {
						  			role_opt1 = 3;
						  			role_opt2 = 2;
						  			role_main_str = "Admin";
						  			role_opt1_str = "Teacher";
						  			role_opt2_str = "Student";
						  		}
						  	
						  		
						  	
						  	%>
							<option value="<%=role_value%>"><%=role_main_str%></option>
  							<option value="<%=role_opt1%>"><%=role_opt1_str%></option>
  							<option value="<%=role_opt2%>"><%=role_opt2_str%></option>
						</select>
						<b><font color="red"><%=ServletUtility.getErrorMessage("role_Id", request)%></font></b>
					</div>
						
					<div class="form-group">
						<label>Date Of Birth</label>
						<input type="text" name="dob" placeholder="Enter Date Of Birth Here.." class="form-control" value="<%=DataUtility.getDateString(bean.getDob())%>" id="datepicker">
						<b><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></b>
					</div>
				
					<input type="submit" name="operation" class="btn btn-lg btn-info"
						value="<%=UserCtl.OP_SAVE%>">					
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