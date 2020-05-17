package in.com.online.exam.bean;

public class QuestionBean extends BaseBean {

		private String examName;
		private String questionName;
		private int questionType;
		private String option1;
		private String option2;
		private String option3;
		private String option4;
		private String correctAns;
		
		
	
	
	public String getExamName() {
			return examName;
		}
	

		public void setExamName(String examName) {
			this.examName = examName;
		}
		
		public int getQuestionType() {
			return questionType;
		}
		
		public void setQuestionType(int questionType) {
			this.questionType = questionType;
		}

		public String getQuestionName() {
			return questionName;
		}

		public void setQuestionName(String questionName) {
			this.questionName = questionName;
		}

		public String getOption1() {
			return option1;
		}

		public void setOption1(String option1) {
			this.option1 = option1;
		}

		public String getOption2() {
			return option2;
		}

		public void setOption2(String option2) {
			this.option2 = option2;
		}

		public String getOption3() {
			return option3;
		}

		public void setOption3(String option3) {
			this.option3 = option3;
		}

		public String getOption4() {
			return option4;
		}

		public void setOption4(String option4) {
			this.option4 = option4;
		}

		public String getCorrectAns() {
			return correctAns;
		}

		public void setCorrectAns(String correctAns) {
			this.correctAns = correctAns;
		}


	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
