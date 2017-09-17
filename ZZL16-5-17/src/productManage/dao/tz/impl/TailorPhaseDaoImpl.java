package productManage.dao.tz.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import productManage.dao.BaseDao;
import productManage.dao.tz.TailorPhaseDao;
import productManage.model.User;
import productManage.model.cs.OutSource;
import productManage.model.zky.Tailor;

@Repository
public class TailorPhaseDaoImpl implements TailorPhaseDao{

	@Autowired
	private BaseDao baseDao;
	
	@Override
	public boolean tailorAppoint(int outsourceID, int userID, String role) {//根据角色委派
	    //获取裁剪单列表
		Set<Tailor> tailors = getTailorByID(outsourceID);
		Iterator<Tailor> it = tailors.iterator();
		//获取用户
		User user = getUserByID(userID);
		while(it.hasNext()){
		if(role.equals("排版")){		
			Tailor tailor = it.next();
			tailor.setTailormodelmaker(user);
			updateTailor(tailor);
			return true;
		}
		if(role.equals("裁剪")){
			Tailor tailor = it.next();
			tailor.setTailor(user);
			updateTailor(tailor);
			return true;
		}
		}
		return false;
	}
	
	public Set<Tailor> getTailorByID(int outsourceID){
		OutSource outsource = (OutSource) baseDao.load(OutSource.class, outsourceID);
		return outsource.getProduction().getTailors();
	}
	
	public User getUserByID(int userId){
		return (User) baseDao.load(User.class, userId);
	}

	public void updateTailor(Tailor tailor){
		baseDao.update(tailor);
	}
}
