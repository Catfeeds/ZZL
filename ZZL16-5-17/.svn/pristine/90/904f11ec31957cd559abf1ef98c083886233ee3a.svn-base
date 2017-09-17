package productManage.action.design_order;

import java.text.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import productManage.model.User;
import productManage.model.cs.OutSourceDetail;
import productManage.model.lhj.Material;
import productManage.model.lhj.Supply;
import productManage.model.lhj.Vendor;
import productManage.model.tms.Design;
import productManage.model.tyc.Purchase;
import productManage.model.yk.OrderDetail;
import productManage.model.yk.OrderMaterialDetail;
import productManage.model.yk.Orders;
import productManage.model.yrd.Customer;
import productManage.model.zky.Production;
import productManage.service.designOrder.DesignOrderService;
import productManage.service.material.BomService;
import productManage.service.orders.OrderDetailService;
import productManage.service.orders.OrdersService;
import productManage.action.PageAction;
import productManage.dao.zky.ProductionDao;
import productManage.service.qc.QcService;
import productManage.vo.PageBean;
import productManage.vo.production.ProductionConstants;
import productManage.vo.technique.TechniqueConstants;

@Controller
public class DesignOrderAction extends PageAction{

	private static final long serialVersionUID = 1L;
	@Autowired
	private BomService bomservice;
	@Autowired
	private DesignOrderService designOrderService;
	@Autowired
	private QcService qcService;
	@Autowired
	private ProductionDao productionDao;
	@Autowired
	private OrderDetailService orderDetailService;
    @Autowired
    private OrdersService ordersService;
	
	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public OrderDetailService getOrderDetailService() {
		return orderDetailService;
	}

	public void setOrderDetailService(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

	public ProductionDao getProductionDao() {
		return productionDao;
	}

	public void setProductionDao(ProductionDao productionDao) {
		this.productionDao = productionDao;
	}

	public QcService getQcService() {
		return qcService;
	}

	public void setQcService(QcService qcService) {
		this.qcService = qcService;
	}

	public DesignOrderService getDesignOrderService() {
		return designOrderService;
	}

	public void setDesignOrderService(DesignOrderService designOrderService) {
		this.designOrderService = designOrderService;
	}
	private int designID;
	private Design design;
	public Design getDesign() {
		return design;
	}

	public void setDesign(Design design) {
		this.design = design;
	}

	public BomService getBomservice() {
		return bomservice;
	}

	public void setBomservice(BomService bomservice) {
		this.bomservice = bomservice;
	}

	public int getDesignID() {
		return designID;
	}

	public void setDesignID(int designID) {
		this.designID = designID;
	}
	private String designCode="";

	private String putawayDate="";
	

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
	private List<Supply> supplyList= new  ArrayList<Supply>();
	
	public String newOrder(){
		try {
        this.design = designOrderService.getDesignByID(designID);
        this.supplyList = designOrderService.getSupplyList(design);
        //System.out.println("supplyList aaaaaaaa"+supplyList.size());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public List<Supply> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<Supply> supplyList) {
		this.supplyList = supplyList;
	}
	private List<OrderDetail> details_add = new ArrayList<OrderDetail>();
	
	public List<OrderDetail> getDetails_add() {
		return details_add;
	}

	public void setDetails_add(List<OrderDetail> details_add) {
		this.details_add = details_add;
	}
	private List<OrderMaterialDetail> materialdetails_add = new ArrayList<OrderMaterialDetail>();
	

	public List<OrderMaterialDetail> getMaterialdetails_add() {
		return materialdetails_add;
	}

	public void setMaterialdetails_add(List<OrderMaterialDetail> materialdetails_add) {
		this.materialdetails_add = materialdetails_add;
	}

	public String addOrder(){
		//584AS4EP459NTQ8  WIK5753QE2979TBA6CER
		try {
	    String customerBrandName = request.getParameter("customerBrandName");
	   // String customerType = request.getParameter("customerType");
	    //String customerEMail = request.getParameter("customerEMail");
		int newdesignID = designID;
		String orderFinishDate = request.getParameter("orderFinishDate");
		String orderRefURL = request.getParameter("orderRefURL");
	
		String orderSampleYardage = request.getParameter("orderSampleYardage");
		String orderSewnInLabelReq = request.getParameter("orderSewnInLabelReq");
		String orderTechReq = request.getParameter("orderTechReq");
		String Comment = request.getParameter("Comment");
		
		Orders orders = new Orders();
	
		orders.setOrderMaker(null);
		Customer customer = designOrderService.getCustomer(customerBrandName);
		orders.setCustomer(customer);
		User user = new User();
		user = qcService.getUserByAccount("GU8IQ8NODL");
		
		orders.setOrderMaker(user);
		//Production production = designOrderService.getProduction(production);
		
		orders.setDesign(design);
		SimpleDateFormat fmt=new SimpleDateFormat("hhmmss_yyyyMMdd");
		Date nowDate = new Date();
		String orderCode =  "DD"+fmt.format(nowDate);
		orders.setOrderCode(orderCode);
		java.sql.Date date = java.sql.Date.valueOf(orderFinishDate);
		orders.setOrderFinishDate(date);
		orders.setOrderRefURL(orderRefURL);
		orders.setOrderSampleYardage(orderSampleYardage);
		orders.setOrderSewnInLabelReq(orderSewnInLabelReq);
		orders.setOrderTechReq(orderTechReq);
		orders.setOrderComment(Comment);
	
	/*	Set<OrderDetail> orderDetails=  new HashSet<OrderDetail>();   
		OrderDetail orderDetail = new OrderDetail();
		
		orderDetail.setOrderColor(orderColor1);
		orderDetail.setOrderXS(orderXS1);
		orderDetail.setOrderS(orderS1);
		orderDetail.setOrderM(orderM1);
		orderDetail.setOrderL(orderL1);
		orderDetail.setOrderXL(orderXL1);
		orderDetail.setOrderXXL(orderXXL1);
		orderDetail.setTotalNum(totalNum1);
		orderDetails.add(orderDetail);
		
		orders.setOrderDetails(orderDetails);*/
		
	
	
		
		/*Set<OrderMaterialDetail> orderMaterialDetails = new HashSet<OrderMaterialDetail>();
		OrderMaterialDetail orderMaterialDetail = new OrderMaterialDetail();
	
		orderMaterialDetail.setOrderComment(orderComment);
		orderMaterialDetail.setOrderMaterialAttr(orderMaterialAttr);
		orderMaterialDetail.setOrderVol(orderVol);
		Supply supply= new Supply(); 
		Material material = new Material();
		material.setColorCode(colorCode);
		material.setMaterialType(materialType);
		Vendor vendor = designOrderService.getVendor(vendorName);
		supply.setMaterial(material);
		supply.setVendor(vendor);
		orderMaterialDetail.setSupply(supply);
		orderMaterialDetails.add(orderMaterialDetail);
	
		orders.setOrderMaterialDetails(orderMaterialDetails);
		*/
		
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		orders.setOrderDate(sdf.format(new Date()));
		orders.setOrderPriority(request.getParameter("orderPriority"));
		orders.setOrderType(request.getParameter("orderType"));
		orders.setOrderProcessType(request.getParameter("orderProcessType"));    
		
		//details_add.remove(details_add.size()-1);
        //System.out.println(details_add.size());
		
		/*Set<OrderDetail> orderDetails=  new HashSet<OrderDetail>();   
		for(int i=0;i<details_add.size();i++){
			OrderDetail orderDetail = details_add.get(i);
			System.out.println("aa"+orderDetail.getOrderColor());
			orderDetails.add(orderDetail);
		}
	
		orders.setOrderDetails(orderDetails);*/
		//designOrderService.addOrderDetail(orders, details_add);
		
		designOrderService.save(orders,user);
		
		for(int i=0;i<details_add.size();i++){
			details_add.get(i).setTotalNum(details_add.get(i).getOrderL()+details_add.get(i).getOrderM()+details_add.get(i).getOrderS()+details_add.get(i).getOrderXL()+details_add.get(i).getOrderXS()+details_add.get(i).getOrderXXL());
		}
		for(int j=0;j<materialdetails_add.size();j++){
			String materialName = request.getParameter("materialdetails_add["+ j +"].materialName");
			String materialType = request.getParameter("materialdetails_add["+ j +"].materialType");
			String colorCode = request.getParameter("materialdetails_add["+ j +"].colorCode");
			String vendorName = request.getParameter("materialdetails_add["+ j +"].vendorName");
			OrderMaterialDetail od = new OrderMaterialDetail(); 
			od = designOrderService.getOD(materialdetails_add.get(j),materialName,materialType,colorCode,vendorName);
			materialdetails_add.set(j, od);
		}
		//details_add.remove(details_add.size()-1);
		//materialdetails_add.remove(materialdetails_add.size()-1);
        //System.out.println(details_add.size());
		Orders new_order = ordersService.getOrderByCode(orders.getOrderCode());
		designOrderService.addOrderDetail(new_order, details_add);
		designOrderService.addOrderMaterialdetail(new_order,materialdetails_add);
		
		
		//生成采购单在生成生产单之后
		/*Purchase purchase = new Purchase();
		purchase.setUser(user);
		purchase.setProduction(production);*/
		} catch (Exception e) {
			e.printStackTrace();
			
			return ERROR;
		}
		return SUCCESS;
	}
	
}
