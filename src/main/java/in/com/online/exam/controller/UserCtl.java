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
import in.com.online.exam.exeption.DuplicateRecordException;
import in.com.online.exam.model.UserModel;
import in.com.online.exam.util.DataUtility;
import in.com.online.exam.util.DataValidator;
import in.com.online.exam.util.PropertyReader;
import in.com.online.exam.util.ServletUtility;




/**
 * Servlet implementation class UserCtl
 */

/**
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
@ WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);
	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	*/
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Email Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login",
					PropertyReader.getValue("error.email", "Email Id"));
			pass = false;
		}
		
		

		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob",
					PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(dob)) {
			request.setAttribute("dob","Min Age Must be 17 years");
			pass = false;
		}

		
		
		
		

		
			log.debug("UserRegistrationCtl Method validate Ended");
		return pass;
	}
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserRegistrationCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		
		bean.setFName(DataUtility.getString(request
				.getParameter("firstName")));

		bean.setLName(DataUtility.getString(request.getParameter("lastName")));

			bean.setLogin(DataUtility.getString(request.getParameter("login")));
	
			
	
			bean.setDob(DataUtility.getDate(request.getParameter("dob")));
			
			populateDTO(bean, request);
	
			log.debug("UserRegistrationCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        
		UserModel model = new UserModel();
        
		long id = DataUtility.getLong(request.getParameter("id"));
       
		if (id > 0 || op != null) {
          
            
			UserBean bean;
            try {
                bean = model.findByPK(id);
             
                ServletUtility.setBean(bean, request);
            
            } catch (ApplicationException e) {
                log.error(e);
            
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
        log.debug("UserCtl Method doGet Ended");
	}

	

/**
 * Contains Submit logics
 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserCtl Method doPost Started");
        String op = DataUtility.getString(request.getParameter("operation"));
        // get model
        UserModel model = new UserModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        if (OP_SAVE.equalsIgnoreCase(op)) {
            UserBean bean = (UserBean) populateBean(request);
            
            try {
                if (id > 0) {
                	UserBean ubean=model.findByPK(bean.getId());
                	bean.setAddress(ubean.getAddress());
                	bean.setPassword(ubean.getPassword());
                	bean.setMobileNo(ubean.getMobileNo());
                	bean.setGender(ubean.getGender());
                	bean.setRole_Id(ubean.getRole_Id());
                	bean.setRoleName(ubean.getRoleName());
                    model.update(bean);
                
                    ServletUtility.setSuccessMessage("Data is successfully Updated", request);
                } else {
                    long pk = model.add(bean);
                   // bean.setId(pk);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }
                
                ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
              
               
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Login id already exists", request);
            }
            return;
        } else if (OP_DELETE.equalsIgnoreCase(op)) {

            UserBean bean = (UserBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(ORSView.USER_LIST_CTL, request,
                        response);
                return;
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
              
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
        	
        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(ORSView.USER_CTL, request, response);
    		return;
    }
    				
    		
        ServletUtility.forward(getView(), request, response);
        

        log.debug("UserCtl Method doPostEnded");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}

}
