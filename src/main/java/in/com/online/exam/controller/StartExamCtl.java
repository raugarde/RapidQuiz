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

import in.com.online.exam.bean.BaseBean;
import in.com.online.exam.bean.ExamBean;
import in.com.online.exam.bean.QuestionBean;
import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.bean.UserBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.model.ExamModel;
import in.com.online.exam.model.QuestionModel;
import in.com.online.exam.model.ResultModel;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.DataValidator;
import in.com.online.exam.util.PropertyReader;
import in.com.online.exam.util.ServletUtility;

/**
 * Servlet implementation class StartExamCtl
 */
@WebServlet(name = "StartExamCtl", urlPatterns = { "/ctl/StartExamCtl" })
public class StartExamCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(StartExamCtl.class);
	
	 @Override
	    protected void preload(HttpServletRequest request) {
	    	log.debug("StudentCtl preload method start");
	    	ExamModel model = new ExamModel();
	    	String cat=DataUtility.getString(request.getParameter("ct"));
	    	if(cat!=null&& cat.length()>0) {
	    	ExamBean bean=new ExamBean();
	    	bean.setExamCategory(cat);
	        try {
	            List l = model.search(bean);
	            System.out.println("==============preload"+l.size());
	            request.setAttribute("ExamList", l);
	        } catch (ApplicationException e) {
	            log.error(e);
	        }
	    	}else {
	    		
	    		
	    		try {
		            List l = model.list();
		            request.setAttribute("ExamList", l);
		        } catch (ApplicationException e) {
		            log.error(e);
		        }
	    		
	
	    	}
	        log.debug("StudentCtl preload method end");
	    }
	 
	 @Override
	    protected boolean validate(HttpServletRequest request) {

			log.debug("SubjectCtl validate method start");
	      
	        boolean pass = true;

	    	if ("-----Select-----".equalsIgnoreCase(request.getParameter("examName"))) {
				request.setAttribute("examName",
						PropertyReader.getValue("error.require", "Exam Name"));
				pass = false;
			}

	        
	    

	        log.debug("SubjectCtl validate method end");
	        return pass;
	    }

	 @Override
		protected BaseBean populateBean(HttpServletRequest request) {
			log.debug("SubjectCtl populateBean method start");
			QuestionBean bean=new QuestionBean();
			bean.setId(DataUtility.getLong(request.getParameter("id")));
			bean.setExamName(DataUtility.getString(request.getParameter("examName")));
			
			
			populateDTO(bean, request);
			// TODO Auto-generated method stub
			log.debug("SubjectCtl populateBean method end");
		return bean;
		}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
        
	       ExamModel model = new ExamModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            ExamBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setOpration("Edit", request);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(getView(), request, response);
	        log.debug("SubjectCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
List list = null;
		
		System.out.println("================================= List Qustion Ctl -------");
		
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? 1 : pageSize;
		
		int count=0;

		QuestionBean bean = (QuestionBean) populateBean(request);
		ExamModel eModel=new ExamModel();

		String op = DataUtility.getString(request.getParameter("operation"));

		QuestionModel model = new QuestionModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SUBMIT.equalsIgnoreCase(op)) {
					
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} 

			} 
			
			ExamBean eBean=	eModel.findByPK(DataUtility.getLong(bean.getExamName()));
			System.out.println("Exam Exam Name======="+eBean.getExamName());
			bean.setExamName(eBean.getExamName());

			list = model.search(bean, pageNo, pageSize);
			
			System.out.println("Exam List Size======="+model.search(bean).size());
			/*
			 * CollegeBean be =(CollegeBean) list;
			 * System.out.println(be.getName());
			 */

			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			request.setAttribute("size",model.search(bean).size());
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			request.setAttribute("count",count);
			ServletUtility.forward(ORSView.QUESTION_LIST_VIEW, request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("CollegeListCtl  doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.EXAM_START_VIEW;
	}

}
