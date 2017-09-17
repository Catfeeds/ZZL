package productManage.action.material;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import productManage.action.PageAction;
import productManage.model.lhj.Vendor;
import productManage.service.material.VendorService;
import productManage.vo.PageBean;
import productManage.vo.material.MaterialConstants;
import productManage.vo.material.VendorConstants;

public class VendorAction extends PageAction{

	private static final long serialVersionUID = 1L;
	@Autowired
	private VendorService vendorservice;
	
	private String vendorName;
	
	public String addVendor(){
		Vendor vendor = new Vendor();
		vendor.setVendorId(Integer.parseInt(request.getParameter("new_Code")));
		vendor.setVendorName(request.getParameter("new_Name"));
		vendor.setVendorPhoneNum(request.getParameter("new_Tel"));
		vendor.setVendorMobileNum(request.getParameter("new_Mobile"));
		vendor.setVendorAddr(request.getParameter("new_Addr"));
		vendor.setVendorRank(request.getParameter("new_VendorLevel"));
		boolean tag = vendorservice.addVendor(vendor);
		if(!tag){
			return ERROR;		
		}
		return SUCCESS;
	}

	public String showVendorList(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(VendorConstants.SEARCH_VENDOR_PARAMS[0], this.vendorName);
		try {
			this.pageBean = vendorservice.queryVendor(this.rowsPerPage, this.page, params);
		} catch (Exception e) {
			e.printStackTrace();
			this.pageBean=new PageBean();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

}
