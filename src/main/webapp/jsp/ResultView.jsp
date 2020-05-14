<%@page import="in.com.online.exam.bean.ResultBean"%>
<%@page import="in.com.online.exam.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
 <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script type="text/javascript">
        $("#btnPrint").live("click", function () {
            var divContents = $("#container").html();
            var printWindow = window.open('', '', 'height=400,width=800');
            printWindow.document.write('<html><head><title>DIV Contents</title>');
            printWindow.document.write('</head><body >');
            printWindow.document.write(divContents);
            printWindow.document.write('</body></html>');
            printWindow.document.close();
            printWindow.print();
        });
    </script>
</head>
<body>
<%@include file="Header.jsp"%>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 13px ;margin-top: 68px" >
<h2 style="color: white; font-style:normal;">Result</h2></div>
<br>
<hr>
<div class="container" id="container">
	<div class="col-lg-12 well">
	<div class="row">
				
				
			
			
				
			<div class="col-sm-12">	
						
			<jsp:useBean id="bean" class="in.com.online.exam.bean.UserBean"
            scope="request"></jsp:useBean>

					<%UserBean ubean=(UserBean) session.getAttribute("user");
							ResultBean rBean=(ResultBean)request.getAttribute("rBean");
							int count =(int)request.getAttribute("count");
							int size=(int)request.getAttribute("size");
						
					%>
              

						<div class="form-group">
							<label for="login">Name:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=ubean.getfName().toUpperCase()+" "+ubean.getLName().toUpperCase()%></h3>
								</span> 
							</div>
						</div>
						<div class="form-group">
							<label for="login">Exam Name:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=rBean.getExaminationName()%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Exam Date :-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=DataUtility.getDateString(rBean.getExaminationDate())%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Question:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=size%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Correct:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=count%></h3>
								</span> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="login">Result:-<font color="red">*</font></label>
							<div class="input-group">
								<h3><%=rBean.getResult()%></h3>
								</span> 
							</div>
						</div>	
						

			</div>
			
	</div>
	</div>
	 <input type="button" value="Print Result" id="btnPrint" />
	</div>
	
	
  
    
    				
	
	<br><br>
	<hr>
	<br><br>
<%@include file="Footer.jsp"%>
</body>
</html>