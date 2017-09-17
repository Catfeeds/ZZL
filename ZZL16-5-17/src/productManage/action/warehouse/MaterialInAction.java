package productManage.action.warehouse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import productManage.action.PageAction;
import productManage.model.User;
import productManage.model.lhj.Bom;
import productManage.model.lhj.Material;
import productManage.model.lhj.Supply;
import productManage.model.lhj.Vendor;
import productManage.model.lhj.WareHouse;
import productManage.model.tms.Design;
import productManage.model.tyc.Purchase;
import productManage.model.wjx.MaterialInput;
import productManage.model.wjx.MaterialOutput;
import productManage.model.wjx.Store;
import productManage.model.yk.Orders;
import productManage.model.zky.Production;
import productManage.service.material.MaterialService;
import productManage.service.material.VendorService;
import productManage.service.purchase.PurchaseService;
import productManage.service.technique.DesignService;
import productManage.service.warehouse.WarehouseManagerService;
import productManage.vo.warehouse.MaterialAndVendor;


/**
 * @author WHQ
 * @date 创建时间2016-3-28
 */
public class MaterialInAction extends PageAction{
	
	@Autowired
	private WarehouseManagerService warehouseservice;
	@Autowired
	private MaterialService materialservice;
	@Autowired
	private PurchaseService purchaseservice;
	@Autowired
	private DesignService designservice;
	@Autowired
	private VendorService vendorservice;
	
	private String date="";
	private String materialCode;
	private String materialCodeA;
	private String materialType;
	private String designCode;
	private String purchaseCode;
	private String location;
	private int materialInputVol;
	
    /**
     * 用于ajax的参数传递：根据日期获取该日期下的详细出库单
     */
	private String dateAjax;
	private String inputDateDetailAjax;
	private String materialCodeAjax;
	private String vendorNameAjax;
	private String materialDetailAjax;
	private String locationAjax;
	private String locationDetailAjax;
	public String showAllMaterials(){
		System.out.println("enter showAllMaterials");
		this.pageBean=warehouseservice.getAllInStores(this.page,this.rowsPerPage);
		List<Object[]> queryResults=pageBean.getList();
		List<Material> m=new ArrayList<Material>();
		for(Object o:queryResults){
			m.add(((Store) o).getMaterial());
		}
		List<MaterialAndVendor> mnv=materialConversion(m);
		this.pageBean.setList(mnv);
		return "success";
	}
	
	public String searchMaterial(){
		System.out.println("enter serachMaterial");
		List<Material> result1=new ArrayList<Material>();
		List<Material> result2=new ArrayList<Material>();
		List<Material> result3=new ArrayList<Material>();
		List<Material> result4=new ArrayList<Material>();
		
		Material m1=materialservice.getMaterialByCode(materialCode);

		if(null==m1){
			result1=null;
		}
		else{
			result1.add(m1);
		}
		
		Design d1=designservice.getByCode(designCode);
		if(null==d1){
			result2=null;
		}
		else{		
			Set<Bom> b1=d1.getBoms();
			for(Bom b:b1){
				result2.add(b.getMaterial());
			}
		}

		
		Purchase purchase=purchaseservice.getPurchaseByCode(purchaseCode);		
		if(null==purchase){
			result3=null;
		}
		else{
			Production production=purchase.getProduction();
			Set<Orders> orders=production.getOrders();
			System.out.println("orders length"+orders.size());
			for(Orders o:orders){
				Design d=o.getDesign();
					Set<Bom> boms=d.getBoms();
					for(Bom b:boms){
						Material m=b.getMaterial();
						result3.add(m);			
					}
			}
		}
		if(result1==null){
			if(result2==null){
				for(int i=0;i<result3.size();i++){
					if(result3.get(i).getMaterialType().equals(materialType)){
						result4.add(result3.get(i));
					}
				}
				System.out.println("根据采购单号查询");
			}
			else if(result3==null){
				for(int i=0;i<result2.size();i++){
					if(result2.get(i).getMaterialType().equals(materialType)){
						result4.add(result2.get(i));
					}
				}
				System.out.println("根据款号查询");
			}//end else if
			else {
				for(int i =0;i<result2.size();i++){
					for(int j=0;j<result3.size();j++){
						if(result2.get(i).getMaterialCode().equals(result3.get(j).getMaterialCode())){
							
							if(result2.get(i).getMaterialType().equals(materialType)){
								result4.add(result2.get(i));
								
							}
						}
					}
				}//end for
				System.out.println("根据款号和采购单号查询");
			}// end else
		}
		else if(result2==null){
			if(result3==null){
				for(int i=0;i<result1.size();i++){
					if(result1.get(i).getMaterialType().equals(materialType)){
						result4.add(result1.get(i));
					}
				}
				System.out.println("根据物料编号查询");
			}//end if
			else{
				for(int i=0;i<result1.size();i++){
					for(int j=0;j<result3.size();j++){
						if(result1.get(i).getMaterialCode().equals(result3.get(j).getMaterialCode())){
							if(result1.get(i).getMaterialType().equals(materialType)){
								result4.add(result1.get(i));
								
							}
						}
					}//end for
				}//end for
				System.out.println("根据物料编号和采购单号查询");
			}//end else
		}//end else if
		else if(result3==null){
			for(int i=0;i<result1.size();i++){
				for(int j=0;j<result2.size();j++){
					if(result1.get(i).getMaterialCode().equals(result2.get(j).getMaterialCode())){
						if(result1.get(i).getMaterialType().equals(materialType)){
							result4.add(result1.get(i));
							
						}
					}
				}//end for
			}//end for
			System.out.println("根据物料编号和款号查询");
		}//end else if
		else{
			for(int i=0;i<result1.size();i++){
				for(int j=0;j<result2.size();j++){
					for(int k=0;k<result3.size();k++){
						if((result1.get(i).getMaterialCode().equals(result2.get(j).getMaterialCode()))&&(result2.get(j).getMaterialCode().equals(result3.get(k).getMaterialCode()))){
							if(result1.get(i).getMaterialType().equals(materialType)){
								result4.add(result1.get(i));
								
							}						
						}
					}//end for
				}//end for
			}//end for
			System.out.println("根据物料编号和款号和采购单号查询");
		}//end else

			
			
		

		
		List<MaterialAndVendor> mnv=materialConversion(result4);
		this.pageBean.setList(mnv);
		return "success";
	}
	
	
	
	public  String materialDetail(){
		System.out.println("enter materialDetail");
		
		Material m=materialservice.getMaterialByCode(materialCodeAjax);
		Vendor v=vendorservice.getVendorByName(vendorNameAjax);

		System.out.println("materialname:  "+m.getMaterialName()+"  vendorname:  "+vendorNameAjax);
		JSONArray json = new JSONArray();
    	try{
    		JSONObject jo=new JSONObject();
    		JSONObject material=new JSONObject();
    		JSONObject vendor=new JSONObject();
   				
    		material.put("materialName", m.getMaterialName());
    		material.put("materialCode", m.getMaterialCode());
    		material.put("colorCode", m.getColorCode());
    		material.put("modificationDate",m.getModificationDate());
    		if(null==v){
    			vendor.put("vendorName","");
        		vendor.put("vendorMobile","");
        		vendor.put("vendorAddr","");  
    		}
    		else{
    		vendor.put("vendorName",v.getVendorName());
    		vendor.put("vendorMobile",v.getVendorMobileNum());
    		vendor.put("vendorAddr",v.getVendorAddr());    			
    		}
    		    	
    		Set<Store> store=m.getStores();
    		List<Store> allstore=warehouseservice.getAllStore();
    		List<String> location=new ArrayList<String>();
    		Store firststore = null;
    		for(Store s:store){
    			firststore=s;
    			break;
    		}
    		int maxVol=firststore.getWarehouse().getMaxCapacity();
    		int remainVol=(int) firststore.getRemainVol();
    		int totalVol=0;
    		int remain=0;
    		for(Store s:allstore){
    			if(s.getWarehouse().getLocation().equals(firststore.getWarehouse().getLocation())){
    				totalVol+=(int) s.getRemainVol();
    			}
    		}
    		remain=maxVol-totalVol;
    		
    		for(Store s:store){
    			location.add(s.getWarehouse().getLocation());
    		}
    		
	    	jo.put("material",material);
	    	jo.put("vendor",vendor);
	    	jo.put("location",location);
	    	jo.put("remainVol",remainVol);
	    	jo.put("remain",remain);
	    	json.put(jo);
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    	setMaterialDetailAjax(json.toString());
		return SUCCESS;
	}
	
	public String locationAjax(){
		System.out.println(locationAjax);
		int totalVol=0;
		int remainVol=0;
		int maxVol=0;
		int remain=0;
		Material m =materialservice.getMaterialByCode(materialCodeAjax);
		List<Store> allstore=warehouseservice.getAllStore();
		Set<Store> store=m.getStores();
		for(Store s:store){
			if(s.getWarehouse().getLocation().equals(locationAjax)){
				remainVol=(int) s.getRemainVol();
				maxVol=s.getWarehouse().getMaxCapacity();
				
			}
		}
		for(Store s:allstore){
			if(s.getWarehouse().getLocation().equals(locationAjax)){
				totalVol+=(int) s.getRemainVol();
			}
		}
		JSONArray json = new JSONArray();
    	try{
    		JSONObject jo=new JSONObject();
    		jo.put("remainVol", remainVol);
    		remain=maxVol-totalVol;
    		jo.put("remain",remain);
    		json.put(jo);
    	}catch (JSONException e) {
    		e.printStackTrace();
    	}
    	setLocationDetailAjax(json.toString());
		return SUCCESS;
	}
	
	public String addInMaterial(){
		System.out.println("enter addInMaterial action");
		System.out.println(materialCodeA);
		MaterialInput mai=new MaterialInput();
		mai.setUser(null);
		mai.setMaterialInputComment(null);
		mai.setMaterialInputVol(materialInputVol);
		Material m=materialservice.getMaterialByCode(materialCodeA);
		Set<Store> store=m.getStores();
		Store ms=null;
		for(Store s:store){
			if(s.getWarehouse().getLocation().equals(location)){
				mai.setWarehouse(s.getWarehouse());
				ms=s;
			}
		}
		mai.setMaterial(m);
		mai.setPurchase(null);
		mai.setSupplementMaterial(null);
    	Calendar now = Calendar.getInstance();
    	now.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH),now.get(Calendar.DATE));
    	mai.setMaterialInputDate(new java.sql.Date(now.getTimeInMillis()));
    	
    	warehouseservice.addMaterialInput(mai);
    	
    	ms.setRemainVol(ms.getRemainVol()+materialInputVol);
    	warehouseservice.updateStore(ms);
		return "success";
	}
	public List<MaterialAndVendor> materialConversion(List<Material> material){
		List<MaterialAndVendor> result=new ArrayList<MaterialAndVendor>();
		for(Material m:material){
			Set<Supply> supply=m.getSupplys();
			System.out.println(supply.size());
			if(supply.size()==0){
				MaterialAndVendor mnv=new MaterialAndVendor();
				mnv.setMaterialCode(m.getMaterialCode());				
				mnv.setMaterialName(m.getMaterialName());
				mnv.setMaterialType(m.getMaterialType());
				mnv.setColorCode(m.getColorCode());
				mnv.setColorDescription(m.getColorDescription());
				mnv.setVendor(null);
				result.add(mnv);
			}
			else{		
				for(Supply s:supply){
					MaterialAndVendor mnv=new MaterialAndVendor();
					mnv.setMaterialCode(m.getMaterialCode());				
					mnv.setMaterialName(m.getMaterialName());
					mnv.setMaterialType(m.getMaterialType());
					mnv.setColorCode(m.getColorCode());
					mnv.setColorDescription(m.getColorDescription());
					mnv.setVendor(s.getVendor());				
					result.add(mnv);
				}
			}
		}
		
		return result;
		
	}
	
	
	public String materialInManage(){
		System.out.println("enter materialInManage");
    	Calendar calendar = Calendar.getInstance();
    	System.out.println(getDate());
    	if(getDate().equals("")||getDate() == null){
    		calendar = null;
    	}else{
    		//时间用-分割
    		String[] dates = getDate().split("-");
    		calendar.set(Integer.parseInt(dates[0]), Integer.parseInt(dates[1])-1, Integer.parseInt(dates[2]));
    	}
    	this.pageBean = warehouseservice.getMaterialInputList(calendar, this.page, this.rowsPerPage);
    	setDate("");

		return "success";
	}
	
	public String materialInDetail(){
		Calendar cal = Calendar.getInstance();
    	String[] dates = getDateAjax().split("-");
    	cal.set(Integer.parseInt(dates[0]), Integer.parseInt(dates[1])-1, Integer.parseInt(dates[2]));
    	List<MaterialInput> results = warehouseservice.getMaterialInputList(cal);
    	System.out.println("in action ajax0"+getDateAjax());
    	JSONArray json = new JSONArray();
    	try {
	    	for(MaterialInput mo:results){
	    		Material materialT = mo.getMaterial();
	    		WareHouse warehouseT = mo.getWarehouse();
	    		User userT = mo.getUser();
	    		Store storeT = warehouseservice.getStore(mo.getMaterial().getMaterialCode(), mo.getWarehouse().getWarehouseid());
	    		JSONObject jo = new JSONObject();
	    		JSONObject material = new JSONObject();
	    		JSONObject warehouse = new JSONObject();
	    		JSONObject user = new JSONObject();
	    		JSONObject store = new JSONObject();
	    		jo.put("material", material);
	    		jo.put("warehouse", warehouse);
	    		jo.put("user", user);
	    		jo.put("store", store);
	    		material.put("materialName", materialT.getMaterialName());
	    		material.put("materialCode", materialT.getMaterialCode());
//	    		material.put("materialIngredient", materialT.getMaterialIngredient());
//	    		material.put("unitPrice", materialT.getUnitPrice());
//	    		material.put("materialType", materialT.getMaterialType());
//	    		material.put("colorCode", materialT.getColorCode());
//	    		material.put("colorDescription", materialT.getColorDescription());
	    		warehouse.put("warehouseid", warehouseT.getWarehouseid());
	    		warehouse.put("location", warehouseT.getLocation());
	    		warehouse.put("remain", warehouseT.getRemain());
	    		user.put("userName", userT.getUserName());
	    		store.put("remainVol", storeT==null?"":storeT.getRemainVol());
	    		jo.put("materialInputVol", mo.getMaterialInputVol());
	    		json.put(jo);
	    	}
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    	setInputDateDetailAjax(json.toString());
    	return SUCCESS;
	}
	
	

	public WarehouseManagerService getWarehouseservice() {
		return warehouseservice;
	}

	public void setWarehouseservice(WarehouseManagerService warehouseservice) {
		this.warehouseservice = warehouseservice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getDateAjax() {
		return dateAjax;
	}

	public void setDateAjax(String dateAjax) {
		this.dateAjax = dateAjax;
	}

	public String getInputDateDetailAjax() {
		return inputDateDetailAjax;
	}

	public void setInputDateDetailAjax(String inputDateDetailAjax) {
		this.inputDateDetailAjax = inputDateDetailAjax;
	}

	public String getMaterialCodeAjax() {
		return materialCodeAjax;
	}

	public void setMaterialCodeAjax(String materialCodeAjax) {
		this.materialCodeAjax = materialCodeAjax;
	}

	public String getMaterialDetailAjax() {
		return materialDetailAjax;
	}

	public void setMaterialDetailAjax(String materialDetailAjax) {
		this.materialDetailAjax = materialDetailAjax;
	}

	public MaterialService getMaterialservice() {
		return materialservice;
	}

	public void setMaterialservice(MaterialService materialservice) {
		this.materialservice = materialservice;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getDesignCode() {
		return designCode;
	}

	public void setDesignCode(String designCode) {
		this.designCode = designCode;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public PurchaseService getPurchaseservice() {
		return purchaseservice;
	}

	public void setPurchaseservice(PurchaseService purchaseservice) {
		this.purchaseservice = purchaseservice;
	}

	public String getVendorNameAjax() {
		return vendorNameAjax;
	}

	public void setVendorNameAjax(String vendorNameAjax) {
		this.vendorNameAjax = vendorNameAjax;
	}

	public DesignService getDesignservice() {
		return designservice;
	}

	public void setDesignservice(DesignService designservice) {
		this.designservice = designservice;
	}

	public VendorService getVendorservice() {
		return vendorservice;
	}

	public void setVendorservice(VendorService vendorservice) {
		this.vendorservice = vendorservice;
	}

	public String getLocationAjax() {
		return locationAjax;
	}

	public void setLocationAjax(String locationAjax) {
		this.locationAjax = locationAjax;
	}

	public String getLocationDetailAjax() {
		return locationDetailAjax;
	}

	public void setLocationDetailAjax(String locationDetailAjax) {
		this.locationDetailAjax = locationDetailAjax;
	}

	public int getMaterialInputVol() {
		return materialInputVol;
	}

	public void setMaterialInputVol(int materialInputVol) {
		this.materialInputVol = materialInputVol;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMaterialCodeA() {
		return materialCodeA;
	}

	public void setMaterialCodeA(String materialCodeA) {
		this.materialCodeA = materialCodeA;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
