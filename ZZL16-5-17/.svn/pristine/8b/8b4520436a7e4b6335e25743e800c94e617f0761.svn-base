package productManage.action.inquiry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import productManage.action.PageAction;
import productManage.model.yk.Inquiry;
import productManage.service.inquiry.InquiryService;

public class InquiryAction extends PageAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private InquiryService inquiryService;

	private int inquiryID;
	private Inquiry inquiry;
	public int getInquiryID() {
		return inquiryID;
	}

	public void setInquiryID(int inquiryID) {
		this.inquiryID = inquiryID;
	}
	
	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}


	public String getInquiryByID(){
		this.inquiry = inquiryService.getInquiryByID(inquiryID); 
		
		if (inquiry==null) {
			return ERROR;
		}
		return SUCCESS;
	}

	
}
