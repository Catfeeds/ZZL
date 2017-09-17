package productManage.action.technique;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;


import productManage.action.PageAction;
import productManage.service.technique.DesignService;
import productManage.vo.technique.TechniqueConstants;;

/**
 * @author TMS
 * @date 2016-3-01
 */
public class ManageDesignAction extends PageAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private DesignService designService;


	private String designCode;
	private String putawayDate;
	public String showAllDesigns(){
		this.pageBean = designService.getDesignsByPage(this.rowsPerPage, this.page);
		setDesignCode("");
		setPutawayDate("");
		return SUCCESS;
	}
	


	public String execute() throws ServletException, IOException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(TechniqueConstants.SEARCH_TECHNIQUE_PARAMS[0],designCode);
		params.put(TechniqueConstants.SEARCH_TECHNIQUE_PARAMS[1], putawayDate);
		this.pageBean = designService.searchDesignsByPage(this.rowsPerPage, this.page, params);
		return SUCCESS;
	}

	
	public String getDesignCode() {
		return designCode;
	}

	public void setDesignCode(String designCode) {
		this.designCode = designCode;
	}

	public String getPutawayDate() {
		return putawayDate;
	}

	public void setPutawayDate(String putawayDate) {
		this.putawayDate = putawayDate;
	}
	public Date string_to_Date(String techDate){
		return null;
	}


}
