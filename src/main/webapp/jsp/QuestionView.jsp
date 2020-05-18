<%@page import="in.com.online.exam.controller.QuestionCtl"%>
<%@page import="in.com.online.exam.util.HTMLUtility"%>

<%@page import="java.util.List"%>
<%@page import="in.com.online.exam.util.DataUtility"%>
<%@page import="in.com.online.exam.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Question</title>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 13px ;margin-top: 68px" >
<h2 style="color: white; font-style:normal;">Manage Question</h2></div>
<br>
<div class="container">
    <h1 class="well">Manage Question</h1>
   <b><font class="text-center" color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
	<b><font class="text-center" color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
    <hr>
    <br>
	<div class="col-lg-12 well">
	<div class="row">
				<form action="<%=ORSView.QUESTION_CTL%>"  method="post" >
				<% List examlist=(List)request.getAttribute("ExamList"); %>
				
				<jsp:useBean id="bean" class="in.com.online.exam.bean.QuestionBean"
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
					<div class="form-group">
						<label>Exam Name</label>
						<%=HTMLUtility.getList("examName", String.valueOf(bean.getExamName()), examlist)%>
						<b><font color="red"><%=ServletUtility.getErrorMessage("examName", request)%></font></b>
					</div>
					<div class="form-group">
						<label>Question Type</label>
						<select name="questionType" class="form-control" id="qtype_select">
						  	<option value="1">MCQ</option>
  							<option value="2">Subjective</option>
						</select>
						<b><font color="red"><%=ServletUtility.getErrorMessage("q_type", request)%></font></b>
					</div>
					<div class="form-group subjective">
						<label>Question Name</label>
						<input type="text" name="questionName" placeholder="Enter Question Name Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getQuestionName())%>">
						<b><font color="red"><%=ServletUtility.getErrorMessage("questionName", request)%></font></b>
					</div>
						<div class="row">
							<div class="col-sm-6 form-group optional">
								<label>Option 1</label>
								
								<input type="text" name="option1" placeholder="Enter Option1 Here.." class="form-control subjective" value="<%=DataUtility.getStringData(bean.getOption1())%>" >
								<b><font color="red"> <%=ServletUtility.getErrorMessage("option1", request)%></font></b>
							</div>
							<div class="col-sm-6 form-group optional">
								<label>Option 2</label>
								<input type="text" name="option2" placeholder="Enter Option 2 Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getOption2())%>">
								<b><font color="red"> <%=ServletUtility.getErrorMessage("option2", request)%></font></b>
							</div>
						</div>						
						<div class="row">
							<div class="col-sm-6 form-group optional">
								<label>Option 3</label>
								<input type="text" name="option3" placeholder="Enter Option 3 Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getOption3())%>">
								<b><font color="red"><%=ServletUtility.getErrorMessage("option3", request)%></font></b>
							</div>
							<div class="col-sm-6 form-group optional">
								<label>Option 4</label>
								<input type="text" name="option4" placeholder="Enter Option 4 Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getOption4())%>">
								<b><font color="red"> <%=ServletUtility.getErrorMessage("option4", request)%></font></b>
							</div>
						</div>	
						
					<div class="form-group">
						<label  class="optional">Correct Answer</label>
						<label id="ans">Answer</label>
						<input type="text" name="correct" placeholder="Enter Correct Ans Here.." class="form-control" value="<%=DataUtility.getStringData(bean.getCorrectAns())%>" >
						<b><font color="red"> <%=ServletUtility.getErrorMessage("correct", request)%></font></b>
					</div>
				
					<input type="submit" name="operation" class="btn btn-lg btn-info"
						value="<%=QuestionCtl.OP_SAVE%>">					
					</div>
				</form> 
				</div>
	</div>
	</div>
	<br><br>
	<hr>
	<br><br>


<%@include file="Footer.jsp"%>
		<!-- Script -->
<script type="text/javascript">
$('#qtype_select').on('change', function() {
	$('.optional').css('display','none');
    if ( this.value == '2')
    {
        $('.subjective').css('display','block');
        $('.subjective').css('width','650px');
        $('#ans').css('display','block');
    }
    else
    {
    	$('.optional').css('display','block');
        $('.subjective').css('width','100%');
        $('#ans').css('display','none');
    }
  });
</script>
</body>
</html>