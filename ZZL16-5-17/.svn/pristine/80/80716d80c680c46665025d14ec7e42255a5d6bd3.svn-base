package productManage.action.qc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import productManage.action.PageAction;
import productManage.model.wcy.Qc;
import productManage.service.qc.QcService;
import productManage.vo.qc.QCVO;
@Controller
public class QCDetailAction extends PageAction{

	@Autowired
	private QcService qcService;
	private int  qcID ;
	private QCVO qcVO;
	private String modify;
	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

	public QCVO getQcVO() {
		return qcVO;
	}

	public void setQcVO(QCVO qcVO) {
		this.qcVO = qcVO;
	}

	

	public int getQcID() {
		return qcID;
	}

	public void setQcID(int qcID) {
		this.qcID = qcID;
	}

	public QcService getQcService() {
		return qcService;
	}

	public void setQcService(QcService qcService) {
		this.qcService = qcService;
	}
	public String getQcDetail(){
		modify = "no";
		try{
		Qc qc = qcService.getQcById(qcID);
		
		this.qcVO = qcService.getQCVO(qc);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return "success";
	}
	
	public String addResult(){
		//模糊查询、作废、判断质检环节
		if(qcID==0){
			return "success";
		}
		else{
		Qc qc = qcService.getQcById(qcID);
		String qcResult = request.getParameter("qcResult");
		
		qc.setQCResult(qcResult);
	    qc.setQCState("已完结");
	    if(qcService.addresult(qc).equals("success")){
	    this.qcVO = qcService.getQCVO(qc);
	    modify = "yes";
		return "success";
		}
		else
		return "error";
		}
	}
	
}
