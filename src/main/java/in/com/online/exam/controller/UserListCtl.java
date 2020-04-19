package in.com.online.exam.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.BaseBean;
import in.com.online.exam.bean.UserBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.model.UserModel;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.PropertyReader;
import in.com.online.exam.util.ServletUtility;





/**
 * Servlet implementation class UserListCtl
 */

/**
 *
 * @author Navigable set
 * @version 1.0
 * @Copyright (c) Navigable Set
 *
 */
@WebServlet(name = "UserListCtl", urlPatterns = { "/ctl/UserListCtl" })
public class UserListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(UserListCtl.class);

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("UserListCtl populateBean method start");
		UserBean bean = new UserBean();

	

		bean.setFName(DataUtility.getString(request.getParameter("name")));
		log.debug("UserListCtl populateBean method end");
		return bean;
	}

	/**
	 * Contains Display logics
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("UserListCtl doGet Start");
		List list = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		UserBean bean = (UserBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list

		String ids = request.getParameter("ids");

		UserModel model = new UserModel();
		
			
		
		try {
			
			if (ids != null ) {
				UserBean deletebean = new UserBean();
				
					deletebean.setId(DataUtility.getInt(ids));
					model.delete(deletebean);
				
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} 

			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("UserListCtl doPOst End");
	}

	/**
	 * Contains Submit logics
	 */
	@Override

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.debug("UserListCtl doPost Start");
		
		
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		
		UserBean bean = (UserBean) populateBean(request);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get the selected checkbox ids array for delete list
		
		String idds = DataUtility.getString(request.getParameter("iddd")) ;
		
		System.out.println("user List Ctl ids======"+idds);
		
		UserModel model = new UserModel();
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			} /*else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null ) {
					UserBean deletebean = new UserBean();
					
						deletebean.setId(DataUtility.getInt(ids));
						model.delete(deletebean);
					
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}*/ else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;

			}

			list = model.search(bean, pageNo, pageSize);
			
			
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("UserListCtl doGet End");

	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.USER_LIST_VIEW;
	}
}
