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
import in.com.online.exam.bean.ExamBean;
import in.com.online.exam.bean.QuestionBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.model.ExamModel;
import in.com.online.exam.model.QuestionModel;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.DataValidator;
import in.com.online.exam.util.PropertyReader;
import in.com.online.exam.util.ServletUtility;

/**
 * Servlet implementation class QuestionCtl
 */
@WebServlet(name="QuestionCtl",urlPatterns={"/ctl/QuestionCtl"})
public class QuestionCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	
	private static Logger log=Logger.getLogger(QuestionCtl.class);
	
	 @Override
	    protected void preload(HttpServletRequest request) {
	    	log.debug("StudentCtl preload method start");
	    	ExamModel model = new ExamModel();
	        try {
	            List l = model.list();
	            request.setAttribute("ExamList", l);
	        } catch (ApplicationException e) {
	            log.error(e);
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
        
        if (DataValidator.isNull(request.getParameter("questionType"))) {
            request.setAttribute("questionType", PropertyReader.getValue("error.require", "Question Type"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("questionName"))) {
            request.setAttribute("questionName", PropertyReader.getValue("error.require", "Question Name"));
            pass = false;
        }
        //Condition to check the question type
        if(DataUtility.getInt(request.getParameter("questionType"))==1) {
            
            if (DataValidator.isNull(request.getParameter("option1"))) {
                request.setAttribute("option1", PropertyReader.getValue("error.require", "Option First"));
                pass = false;
            }
            
            if (DataValidator.isNull(request.getParameter("option2"))) {
                request.setAttribute("option2", PropertyReader.getValue("error.require", "Option Secound"));
                pass = false;
            }
            
            if (DataValidator.isNull(request.getParameter("option3"))) {
                request.setAttribute("option3", PropertyReader.getValue("error.require", "Option Third"));
                pass = false;
            }
            
            if (DataValidator.isNull(request.getParameter("option4"))) {
                request.setAttribute("option4", PropertyReader.getValue("error.require", "Option Forth"));
                pass = false;
            }
        	
        }

        
        
        if (DataValidator.isNull(request.getParameter("correct"))) {
            request.setAttribute("correct", PropertyReader.getValue("error.require", "Correct Ans"));
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
		bean.setQuestionType(DataUtility.getInt(request.getParameter("questionType")));
		bean.setQuestionName(DataUtility.getString(request.getParameter("questionName")));
		bean.setOption1(DataUtility.getString(request.getParameter("correct")));
		if(bean.getQuestionType()==2) {
			
			bean.setOption2("Not Applicable");
			bean.setOption3("Not Applicable");
			bean.setOption4("Not Applicable");
		}
		else
		{
			
			bean.setOption2(DataUtility.getString(request.getParameter("option2")));
			bean.setOption3(DataUtility.getString(request.getParameter("option3")));
			bean.setOption4(DataUtility.getString(request.getParameter("option4")));
		}
		
		bean.setCorrectAns(DataUtility.getString(request.getParameter("correct")));
		
		populateDTO(bean, request);
		// TODO Auto-generated method stub
		log.debug("SubjectCtl populateBean method end");
	return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("SubjectCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
        
	       QuestionModel model = new QuestionModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            QuestionBean bean;
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
		log.debug("SubjectCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		QuestionBean bean=(QuestionBean)populateBean(request);
		QuestionModel model=new QuestionModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		if(OP_SAVE.equalsIgnoreCase(op)){
			try {
				if(id>0){
					/*model.update(bean);*/
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);
				}else {
				long pk=model.add(bean);
				ServletUtility.setSuccessMessage("Data is Successfully Saved", request);
				ServletUtility.forward(getView(), request, response);
				}
				  ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Subject is Already exist", request);
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("SubjectCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.QUESTION_VIEW;
	}

}
