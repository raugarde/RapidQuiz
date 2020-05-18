package in.com.online.exam.bean;

import java.util.Date;

public class ExamBean extends BaseBean{
	
	private String examName;
	private Date examDate;
	private String examCategory;
	
	private Integer subject_id;
	private String subject_description;
	
	
	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}

	public String getSubject_description() {
		return subject_description;
	}

	public void setSubject_description(String subject_description) {
		this.subject_description = subject_description;
	}

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


	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return examName;
	}

}
