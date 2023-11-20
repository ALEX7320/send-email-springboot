package send.email.template;

public class EmailTemplate {
	
	private String templateEmail;
	private String templateUsername;
	
	public EmailTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailTemplate(String templateEmail, String templateUsername) {
		super();
		this.templateEmail = templateEmail;
		this.templateUsername = templateUsername;
	}

	public String getTemplateEmail() {
		return templateEmail;
	}
	public void setTemplateEmail(String templateEmail) {
		this.templateEmail = templateEmail;
	}
	public String getTemplateUsername() {
		return templateUsername;
	}
	public void setTemplateUsername(String templateUsername) {
		this.templateUsername = templateUsername;
	}

}
