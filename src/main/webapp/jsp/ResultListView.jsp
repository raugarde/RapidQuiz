<%@page import="in.com.online.exam.model.UserModel"%>
<%@page import="in.com.online.exam.model.ResultModel"%>
<%@page import="in.com.online.exam.controller.ResultListCtl"%>
<%@page import="in.com.online.exam.model.QuestionModel"%>
<%@page import="in.com.online.exam.controller.QuestionListCtl"%>
<%@page import="in.com.online.exam.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.com.online.exam.bean.ResultBean"%>
<%@page import="java.util.List"%>
<%@page import="in.com.online.exam.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result List</title>
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
<%@ include file="Header.jsp" %>
<div style="width: 100%;background-color: #04091e;height: 69px; padding: 13px ;margin-top: 68px" >
<h2 style="color: white; font-style:normal;">Show Result</h2></div>
<br><br>
<div  id = "container">
<form action="<%=ORSView.RESULT_LIST%>" method="post">

<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
									</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
									</font></b>
			
<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">S.No.</th>
					<th scope="col">Name</th>
					<th scope="col">Exam Name</th>
					<th scope="col">Exam Date</th>
					<th scope="col">Result</th>
				</tr>
			</thead>
			 <%
			 int pageNo=ServletUtility.getPageNo(request);
				int pageSize=ServletUtility.getPageSize(request);
				int index=((pageNo-1)*pageSize)+1;
				List list=ServletUtility.getList(request);
				ResultBean bean=null;
				Iterator<ResultBean> iterator=list.iterator();
				while(iterator.hasNext()){
					 bean=iterator.next();
				%> 
			<tbody>
				<tr>
					<th scope="row"><%=index++%></th>
					<%	 UserModel model=new UserModel();
						UserBean uBean=model.findByPK(bean.getUser_id());
						
						%>
						
					<td><%=uBean.getFName()+" "+uBean.getLName()%></td>
					<td><%=bean.getExaminationName()%></td>
					<td><%=DataUtility.getDateString(bean.getExaminationDate())%></td>
					<td><%=bean.getResult()%></td>
					
					
				</tr>
<%
					}
				%>
			</tbody>
		</table>
		
		<table width="99%">
				<tr>
					<td><input type="submit" name="operation" class="btn btn-success"
						value="<%=ResultListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
                    	<td> <input type="button" value="Print Result" id="btnPrint" class="btn btn-success"/></td>
				
					<%
						ResultModel model = new ResultModel();
					%>
					<td align="right"><input type="submit" name="operation" class="btn btn-success"
						value="<%=ResultListCtl.OP_NEXT%>"
						<%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%>></td>
					

				</tr>
					
				
			</table>
		
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</div>
<br><br><br>
	<%@ include file="Footer.jsp"%>
</body>
</html>