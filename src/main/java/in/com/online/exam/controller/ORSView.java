package in.com.online.exam.controller;

public interface ORSView {
	
	public String APP_CONTEXT = "/RapidQuiz";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	
	public String QUESTION_VIEW = PAGE_FOLDER + "/QuestionView.jsp";
	public String CATEGORY_VIEW = PAGE_FOLDER + "/CatagoryView.jsp";
	

	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	
	public String EXAM_START_VIEW = PAGE_FOLDER + "/StatExamView.jsp";
	public String QUESTION_LIST_VIEW = PAGE_FOLDER + "/QuestionListView.jsp";

	public String RESULT_VIEW = PAGE_FOLDER + "/ResultView.jsp";
	
	
	public String EXAM_VIEW = PAGE_FOLDER + "/ExamView.jsp"; 
	public String RESULT_LIST_VIEW = PAGE_FOLDER + "/ResultListView.jsp";
	public String RESULT_LI_VIEW = PAGE_FOLDER + "/ResultLiView.jsp";
	
		
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW ="/WelcomeView.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	

	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String QUESTION_CTL = APP_CONTEXT + "/ctl/QuestionCtl";
	public String QUESTION_LIST_CTL = APP_CONTEXT + "/ctl/QuestionListCtl";
	
	public String STARTEXAM_CTL = APP_CONTEXT + "/ctl/StartExamCtl";
	public String CATEGORY_LIST_CTL = APP_CONTEXT + "/ctl/CategoryListCtl";
	
	
	
    public String EXAM_CTL = APP_CONTEXT + "/ctl/ExamCtl";
	public String RESULT_LIST_CTL = APP_CONTEXT + "/ctl/ResultListCtl";
	public String RESULT_LIST = APP_CONTEXT + "/ctl/ResultList";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";



}
