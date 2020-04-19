package in.com.online.exam.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



import in.com.online.exam.bean.BaseBean;
import in.com.online.exam.bean.ExamBean;
import in.com.online.exam.exeption.ApplicationException;
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.model.ExamModel;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.DataValidator;
import in.com.online.exam.util.PropertyReader;
import in.com.online.exam.util.ServletUtility;

/**
 * Servlet implementation class ExamCtl
 */
@WebServlet(name="ExamCtl",urlPatterns={"/ctl/ExamCtl"})
public class ExamCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(ExamCtl.class);
       
	@Override
    protected boolean validate(HttpServletRequest request) {

		log.debug("SubjectCtl validate method start");
      
        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("examName"))) {
            request.setAttribute("examName", PropertyReader.getValue("error.require", "Exam Name"));
            pass = false;
        }else if (!DataValidator.isName(request.getParameter("examName"))) {
			request.setAttribute("examName",PropertyReader.getValue("error.name", "Exam Name"));
			pass = false;
		}

        if (DataValidator.isNull(request.getParameter("examDate"))) {
            request.setAttribute("examDate",PropertyReader.getValue("error.require", "Exam Date"));
            pass = false;
        }
        
        if (DataValidator.isNull(request.getParameter("category"))) {
            request.setAttribute("category", PropertyReader.getValue("error.require", "Category"));
            pass = false;
        }
        
    

        log.debug("SubjectCtl validate method end");
        return pass;
    }
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("SubjectCtl populateBean method start");
		ExamBean bean=new ExamBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setExamName(DataUtility.getString(request.getParameter("examName")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		bean.setExamCategory(DataUtility.getString(request.getParameter("category")));
		
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
		log.debug("SubjectCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		ExamBean bean=(ExamBean)populateBean(request);
		ExamModel model=new ExamModel();
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
				ServletUtility.setErrorMessage("Exam  is Already exist", request);
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("SubjectCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.EXAM_VIEW;
	}

}
