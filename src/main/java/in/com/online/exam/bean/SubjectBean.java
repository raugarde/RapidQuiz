package in.com.online.exam.bean;


public class SubjectBean extends BaseBean{

	private String description;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return Long.toString(id);
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return this.description;
	}
}