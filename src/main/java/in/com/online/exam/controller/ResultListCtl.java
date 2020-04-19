package in.com.online.exam.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.QuestionBean;
import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.bean.UserBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.model.QuestionModel;
import in.com.online.exam.model.ResultModel;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.PropertyReader;
import in.com.online.exam.util.ServletUtility;

/**
 * Servlet implementation class ResultCtl
 */
@WebServlet(name = "ResultListCtl", urlPatterns = { "/ctl/ResultListCtl" })
public class ResultListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(ResultListCtl.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CourseListCtl  doGet method start");

		List list = null;
	
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		ResultModel model = new ResultModel();
		HttpSession session=request.getSession(true);
		UserBean bean=(UserBean)session.getAttribute("user");
		ResultBean rBean=new ResultBean();
		rBean.setUser_id(bean.getId());
		
		try {
			
			list = model.search(rBean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("CourseListCtl  doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
List list = null;
		

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		ResultBean bean = (ResultBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		
		ResultModel model = new ResultModel();
		HttpSession session=request.getSession(true);
		UserBean ubean=(UserBean)session.getAttribute("user");
		ResultBean rBean=new ResultBean();
		rBean.setUser_id(ubean.getId());

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SUBMIT.equalsIgnoreCase(op)) {
					
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} 

			} 

			list = model.search(bean, pageNo, pageSize);
			/*
			 * CollegeBean be =(CollegeBean) list;
			 * System.out.println(be.getName());
			 */

			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("CollegeListCtl  doPost method end");
	}
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.RESULT_LI_VIEW;
	}

}
