package productManage.service.material;

import java.util.Map;

import productManage.model.lhj.Bom;
import productManage.model.tms.Design;
import productManage.vo.PageBean;

public interface BomService {
	
	public void addDesign(Design design);
	
	public void addBom(Bom bm);
	
	public PageBean queryDesign(int pageSize, int page, Map<String, Object> params);
	
	public Design getDesignByCode(String Code);
	
}
