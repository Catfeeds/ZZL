package productManage.action.material;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import productManage.action.PageAction;
import productManage.model.lhj.Bom;
import productManage.model.lhj.Material;
import productManage.model.tms.Design;
import productManage.model.wjx.Store;
import productManage.service.material.BomService;
import productManage.service.material.MaterialService;
import productManage.service.technique.DesignService;
import productManage.vo.PageBean;
import productManage.vo.material.BomForMaterial;
import productManage.vo.material.MaterialConstants;
import productManage.vo.purchase.bomVO;
import productManage.vo.technique.TechniqueConstants;

public class BomAction extends PageAction{

	private static final long serialVersionUID = 1L;
	@Autowired
	private BomService bomservice;
	
	@Autowired
	private MaterialService materialservice;
	
	@Autowired
	private DesignService designservice;
	
	private String designCode="";

	private String putawayDate="";
	
	public String showDesignList(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(TechniqueConstants.SEARCH_TECHNIQUE_PARAMS[0],designCode);
		params.put(TechniqueConstants.SEARCH_TECHNIQUE_PARAMS[1], putawayDate);
		try {
			this.pageBean = bomservice.queryDesign(this.rowsPerPage, this.page, params);
		} catch (Exception e) {
			e.printStackTrace();
			this.pageBean=new PageBean();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getDesignCode() {
		return designCode;
	}

	public String getPutawayDate() {
		return putawayDate;
	}

	public void setDesignCode(String designCode) {
		this.designCode = designCode;
	}

	public void setPutawayDate(String putawayDate) {
		this.putawayDate = putawayDate;
	}
	
	private Design design;
	private List materialMes;
	
	public List getMaterialMes() {
		return materialMes;
	}

	public void setMaterialMes(List materialMes) {
		this.materialMes = materialMes;
	}

	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public String getBom(){
		this.design = bomservice.getDesignByCode(request.getParameter("designCode"));
		
		List materialMes = new ArrayList();
		
		Iterator<Bom> materialItr = design.getBoms().iterator();	
		while(materialItr.hasNext()){
			Bom bom = materialItr.next();
			Material material = bom.getMaterial();

			BomForMaterial bomTem = new BomForMaterial();
			bomTem.setColorCode(material.getColorCode());
			bomTem.setMaterialCode(material.getMaterialCode());
			bomTem.setMaterialName(material.getMaterialName());
			bomTem.setMaterialPosition(bom.getMaterialPosition());
			bomTem.setMaterialType(material.getMaterialType());
			bomTem.setUnitPrice(material.getUnitPrice());
			bomTem.setVolPerDesign(bom.getVolPerDesign());
			materialMes.add(bomTem);
			
			System.out.println(bom.getMaterial().getMaterialCode());
		}

		this.materialMes = materialMes;
		return SUCCESS;
	}
	


	private String materialCode="";
	private String volPerDesign="";
	private String materialPosition="";
	
	public String getMaterialCode() {
		return materialCode;
	}

	public String getVolPerDesign() {
		return volPerDesign;
	}

	public String getMaterialPosition() {
		return materialPosition;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public void setVolPerDesign(String volPerDesign) {
		this.volPerDesign = volPerDesign;
	}

	public void setMaterialPosition(String materialPosition) {
		this.materialPosition = materialPosition;
	}
	
	public String addBom(){

		Material m = materialservice.getMaterialByCode(materialCode);
		Design d = designservice.getByCode(designCode);
		float vol = Float.parseFloat(volPerDesign);
		
		Bom bom = new Bom(d,m,vol,materialPosition);
		
		bomservice.addBom(bom);
		return SUCCESS;
	}
	
	public String showAddDesign(){
		
		return SUCCESS;
	}
	
	public String addDesign(){
		Design design = new Design();
		
		String designCode = request.getParameter("designCode");
		String designName = request.getParameter("designName");
		String designProcessPrice = request.getParameter("designProcessPrice");
		String designTechProcedure = request.getParameter("designTechProcedure");
		String designPutawayDate = request.getParameter("designPutawayDate");
		String designColorDescription = request.getParameter("designColorDescription");
		String designsewninCode = request.getParameter("designsewninCode");
		String designMainLabelCode = request.getParameter("designMainLabelCode");
		String designHangTagCode = request.getParameter("designHangTagCode");
		String designType = request.getParameter("designType");
		String designComment = request.getParameter("designComment");
		
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = fmt.parse(designPutawayDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		design.setDesignCode(designCode);
		design.setDesignName(designName);
		design.setDesignProcessPrice(Float.parseFloat(designProcessPrice));
		design.setDesignTechProcedure(designTechProcedure);
		design.setDesignPutawayDate(date);
		design.setDesignColorDescription(designColorDescription);
		design.setDesignsewninCode(designsewninCode);
		design.setDesignMainLabelCode(designMainLabelCode);
		design.setDesignHangTagCode(designHangTagCode);
		design.setDesignType(designType);
		design.setDesignComment(designComment);
		
		design.setDesignFlagShipURL("");
		design.setDesignPicURL("");
		
	//		System.out.println(designCode);
	//		System.out.println(designColorDescription);
	//		System.out.println(designComment);
		
		bomservice.addDesign(design);
		return SUCCESS;
	}
	
	
	//==========================================================================
	
	private String materialName;
	
	private String materialListAjax;

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	
	public String getMaterialListAjax() {
		return materialListAjax;
	}

	public void setMaterialListAjax(String materialListAjax) {
		this.materialListAjax = materialListAjax;
	}
	
	public String getMaterialList(){
		System.out.println("enter bomManage getMaterialList");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(MaterialConstants.SEARCH_MATERIAL_PARAMS[0], this.materialName);
		
		List<Material> materialList = materialservice.getMaterialList(params);

		JSONArray json = new JSONArray();
		for(Material material:materialList){
			JSONObject jo = new JSONObject();
			try {
				jo.put("materialCode",material.getMaterialCode());
				jo.put("materialName", material.getMaterialName());
				jo.put("materialType", material.getMaterialType());
				jo.put("materialIngredient",material.getMaterialIngredient());
				json.put(jo);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setMaterialListAjax(json.toString());
		System.out.println(json.toString());
		return SUCCESS;
	}
}
