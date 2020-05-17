
<%@page import="in.com.online.exam.controller.LoginCtl"%>
<%@page import="in.com.online.exam.controller.ORSView"%>
<%@page import="in.com.online.exam.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
		<link href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" rel="stylesheet"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
 <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="/RapidQuiz/css/bootstrap.css">
        <link rel="stylesheet" href="/RapidQuiz/vendors/linericon/style.css">
        <link rel="stylesheet" href="/RapidQuiz/css/font-awesome.min.css">
        <link rel="stylesheet" href="/RapidQuiz/vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="/RapidQuiz/vendors/lightbox/simpleLightbox.css">
        <link rel="stylesheet" href="/RapidQuiz/vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" href="/RapidQuiz/vendors/animate-css/animate.css">
        <link rel="stylesheet" href="/RapidQuiz/vendors/popup/magnific-popup.css">
        <!-- main css -->
        <link rel="stylesheet" href="/RapidQuiz/css/style.css">
        <link rel="stylesheet" href="/RapidQuiz/css/responsive.css">
        <link rel="stylesheet" href="/RapidQuiz/css/login.css">
</head>
<body>

<%
	UserBean userDto = (UserBean) session.getAttribute("user");

	boolean userLoggedIn = userDto != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		welcomeMsg += userDto.getFName().toUpperCase();
	} else {
		welcomeMsg += "Guest";
	}
%>
       <header class="header_area">
          	
            <div class="main_menu">
            	<nav class="navbar navbar-expand-lg navbar-light">
					<div class="container">
						<!-- Brand and toggle get grouped for better mobile display -->
						<a class="navbar-brand logo_h" href="index.html">RapidQuiz</a>
						
						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse offset" id="navbarSupportedContent">
							<ul class="nav navbar-nav menu_nav ml-auto">
								<li class="nav-item active"><a class="nav-link" href="<%=ORSView.WELCOME_CTL%>">Home</a></li>
				<%
				if (userLoggedIn) {
				%> 
				
				<%if(userDto.getRole_Id()==1){ %>
								<li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_LIST_CTL%>">Manage Users</a></li>
								<li class="nav-item submenu dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Teacher Options</a>
									<ul class="dropdown-menu">
										<li class="nav-item"><a class="nav-link" href="<%=ORSView.EXAM_CTL%>">Manage Exams</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=ORSView.QUESTION_CTL%>">Manage Questions</a></li>
										<li class="nav-item"><a class="nav-link" href="<%=ORSView.RESULT_LIST%>">Results</a></li>
									</ul>
								</li> 
								<li class="nav-item"><a class="nav-link" href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
				
				<%}
				if(userDto.getRole_Id()==3){ %>
				<li class="nav-item"><a class="nav-link" href="<%=ORSView.EXAM_CTL%>">Manage Exams</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=ORSView.QUESTION_CTL%>">Manage Questions</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=ORSView.RESULT_LIST%>">Results</a></li>
				<li class="nav-item"><a class="nav-link" href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>

				<%}
				if(userDto.getRole_Id()==2){%>
				
						<li class="nav-item"><a class="nav-link" href="<%=ORSView.CATEGORY_LIST_CTL%>">View Categories</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=ORSView.STARTEXAM_CTL%>">Take Exam</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=ORSView.RESULT_LIST_CTL%>">View Results</a></li>
						<li class="nav-item"><a class="nav-link" href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a></li>
						
					<%}} %>	
						<%
 					if (userLoggedIn) {
						 %>
				 
								
								<li class="nav-item"><a class="nav-link" href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a></li>
								
					<%}else{ %>			
								<li class="nav-item"><a class="nav-link" href="<%=ORSView.LOGIN_CTL%>">Login</a></li> 
								<li class="nav-item"><a class="nav-link" href="<%=ORSView.USER_REGISTRATION_CTL%>">Register</a></li>
							
					<%} %>		
							</ul>
						</div> 
					</div>
            	</nav>
            </div>
        </header>
</body>
</html>