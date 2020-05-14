package in.com.online.exam.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.apache.log4j.Logger;

import in.com.online.exam.bean.BaseBean;
import in.com.online.exam.bean.ExamBean;
import in.com.online.exam.bean.QuestionBean;
import in.com.online.exam.bean.ResultBean;
import in.com.online.exam.bean.UserBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.model.ExamModel;
import in.com.online.exam.model.QuestionModel;
import in.com.online.exam.model.ResultModel;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.ServletUtility;

/**
 * Servlet implementation class QuestionListCtl
 */
@WebServlet(name = "QuestionListCtl", urlPatterns = {"/ctl/QuestionListCtl" })

public class QuestionListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private  static Logger log = Logger.getLogger(LoginCtl.class);
       
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("SubjectCtl populateBean method start");
		QuestionBean bean=new QuestionBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setExamName(DataUtility.getString(request.getParameter("examName")));
		bean.setOption1(DataUtility.getString(request.getParameter("ans")));
		
		
		populateDTO(bean, request);
		// TODO Auto-generated method stub
		log.debug("SubjectCtl populateBean method end");
	return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = null;
		
		System.out.println("================================= List Qustion Ctl  in wwww -------");
		
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		int count=DataUtility.getInt(request.getParameter("count"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		count = (count > 0) ? count : 0;

		pageSize = (pageSize == 0) ? 1 : pageSize;

		QuestionBean bean = (QuestionBean) populateBean(request);
		ExamModel eModel=new ExamModel();

		System.out.println("================bean==="+request.getParameter("ans"));
		System.out.println("================Question==="+request.getParameter("question"));
		String op = DataUtility.getString(request.getParameter("operation"));

		String result=null;
		
		HttpSession session=request.getSession(true);
		UserBean uBean=(UserBean)session.getAttribute("user");
		
		QuestionModel model = new QuestionModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)||OP_SUBMIT.equalsIgnoreCase(op)) {

				 if (OP_NEXT.equalsIgnoreCase(op)) {
					
					String ans=request.getParameter("ans");
					String question=request.getParameter("question");
					
					QuestionBean bean1=model.findByQuestionName(question);
					System.out.println("bdkgvsdgvd-------"+bean1.getCorrectAns());
						if(bean1.getCorrectAns().equalsIgnoreCase(ans)) {
							
								count++;
								System.out.println("aaaaaaaaaaaffffffff---------"+count);
						}
					
					
				 	
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} else if(OP_SUBMIT.equalsIgnoreCase(op)){
					
					String ans=request.getParameter("ans");
					String question=request.getParameter("question");
					
					QuestionBean bean1=model.findByQuestionName(question);
						if(bean1.getCorrectAns().equalsIgnoreCase(ans)) {
							
								count++;
								System.out.println("aaaaaaaaaaaffffffff---------"+count);
						}
					
						System.out.println("Submit----------------");
					ResultBean rBean=new ResultBean();
					rBean.setExaminationName(bean.getExamName());
					ExamModel exModel=new ExamModel();
					ExamBean exBean=exModel.findByExamName(bean.getExamName());
					rBean.setExaminationDate(exBean.getExamDate());
					int size=model.search(bean, pageNo, pageSize).size();
					
					if(count>(size/30)) {
						result="Pass";
					}else {
						result="Fail";
					}
					rBean.setResult(result);
					rBean.setUser_id(uBean.getId());
					
					ResultModel rModel=new ResultModel();
					rModel.add(rBean);
					
					request.setAttribute("rBean",rBean);
					request.setAttribute("count",count);
					request.setAttribute("size",model.search(bean).size());
					ServletUtility.forward(ORSView.RESULT_VIEW, request, response);
					return;
						
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
			request.setAttribute("count",count);
			request.setAttribute("size",model.search(bean).size());
			ServletUtility.forward(getView(), request, response);
		

		} catch (ApplicationException | DuplicateRecordException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("CollegeListCtl  doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.QUESTION_LIST_VIEW;
	}

}
