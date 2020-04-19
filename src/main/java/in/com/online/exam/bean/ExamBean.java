package in.com.online.exam.bean;

import java.util.Date;

public class ExamBean extends BaseBean{
	
	private String examName;
	private Date examDate;
	private String examCategory;
	
	
	
	
	

	public String getExamCategory() {
		return examCategory;
	}

	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return examName;
	}

}
