package productManage.dao.tz.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import productManage.dao.BaseDao;
import productManage.dao.tz.CreateManageDao;
import productManage.model.cs.OutSource;
import productManage.model.tz.TechniquePhase;
import productManage.model.zky.Production;
import productManage.model.zky.Tailor;
import productManage.vo.production.ProductionConstants;

@Repository
public class CreateManageDaoImpl implements CreateManageDao{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public boolean changeProductionState(int outsourceID) { //根据外发单编号修改排单状态
		// TODO Auto-generated method stub
		OutSource outsource = (OutSource) baseDao.load(OutSource.class, outsourceID);
		String designProcedure = outsource.getDesign().getDesignTechProcedure();   //工序
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		Production production = outsource.getProduction();
		production.setProductionState(getState(designProcedure));
		if(getState(designProcedure).equals("工艺")){
			TechniquePhase technique = new TechniquePhase();
			technique.setOutSource(outsource);
			baseDao.save(technique);
		}
		if(getState(designProcedure).equals("裁剪")){
			Tailor tailor = new Tailor();
			if(outsource.getProduction() !=null){
			tailor.setProduction(outsource.getProduction());
			}
//			if(outsource.getProduction().getOrders() != null){
//				Iterator<Orders> i = outsource.getProduction().getOrders().iterator();
//				if(i.hasNext()){
//					tailor.setOrderDetail(i.next().getOrderDetails().iterator().next());
//				}
//			}
			Timestamp ts = new Timestamp(date.getTime());
			tailor.setTailorStartingTime(ts);
			baseDao.save(tailor);	
		}
		baseDao.update(production);
		return true;
	}

	public String getState(String designProcedure){
		//找出第一个工序阶段
		if(designProcedure.contains("->")){
		return designProcedure.split("->")[0];
		}
		else{
			return "排单失败";
		}
	}
	
}
