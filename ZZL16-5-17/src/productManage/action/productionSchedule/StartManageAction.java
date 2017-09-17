package productManage.action.productionSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import productManage.action.PageAction;
import productManage.service.ListManage.StartManageService;
@Controller
public class StartManageAction extends PageAction {
	@Autowired
	private StartManageService startmanageservice;
	
	public String showAllOutSources(){
		this.pageBean = startmanageservice.getAllOutSource(this.page, this.rowsPerPage);
		return "success";	
	}
	
	public String changeProductionState(){
		String outSourceID_select = request.getParameter("selectedCode");
		String[] selects = outSourceID_select.split(",");
		for(int i=0;i<selects.length;i++){
			startmanageservice.changeProductionState(Integer.parseInt(selects[i]));
		}
		this.pageBean = startmanageservice.getAllOutSource(this.page, this.rowsPerPage);
		return "success";
//		int outsourceID = Integer.parseInt(this.outsourceID);
//		if(startmanageservice.changeProductionState(outsourceID)){
//		this.pageBean = startmanageservice.getAllOutSource(this.page, this.rowsPerPage);
//		return "success";
//		}
//		else{
//			return "false";
//		}
	}


	public String inquire(){
		String designCode = request.getParameter("designCode");
		String designName = request.getParameter("designCode");
		String outsourceCode = request.getParameter("designCode");
		String outSource_date = request.getParameter("designCode");
		
		// 生成sql语句
				StringBuffer hql = new StringBuffer();
				hql.append(
						"select o from OutSource o,Production p where o.production.productionID = p.productionID and p.productionState ='未排单'");
				if (null != designCode && !"".equals(designCode)) {
					hql.append(" and o.design.designCode like '%" + designCode + "%'");
				}
				if (null != designName && !"".equals(designName)) {
					hql.append(" and o.design.designName like '%" + designName + "%'");
				}
				if (null != outSource_date && !"".equals(outSource_date)) {
					hql.append(" and o.referenceOutDate <='" + outSource_date + "'");
				}
				if (null != outsourceCode && !"".equals(outsourceCode)) {
					hql.append(" and o.outsourceCode like '%" + outsourceCode + "%'");
				}
	this.pageBean = startmanageservice.inquireOutSourceInOutward(hql.toString(),this.page, this.rowsPerPage);		
		
		return "success";
	}
	
}

