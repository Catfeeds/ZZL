package productManage.service.sample.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import productManage.dao.tms.DesignDAO;
import productManage.dao.whq.SampleDao;
import productManage.dao.whq.SampleStorageDetailDao;
import productManage.model.tms.Design;
import productManage.model.whq.Sample;
import productManage.model.whq.SampleStorageDetail;
import productManage.service.PageService;
import productManage.service.sample.SampleManageService;
import productManage.vo.PageBean;

/**
 * @author WHQ
 * @date 2016-03-08
 */
@Service
public class SampleManageServiceImpl implements SampleManageService{

	@Autowired
	private SampleDao sampledao;
	@Autowired
	private SampleStorageDetailDao samplestoragedetaildao;
	@Autowired
	private DesignDAO designdao;
	@Autowired
	private PageService pageService;
	
	@Override
	public int SampleInput(String designID,String sampleTime,String samplePlace,String sampleOPComment){
		Design d;
//		d=designdao.findByCode("MAJG3R95805FMQFL5AHC");
//		Sample sample=new Sample(78,d,samplePlace,"a");
//		SampleStorageDetail spdetail=new SampleStorageDetail(88,sample,sampleOPComment,"samplein",sampleTime);
//		sampledao.addSample(sample);
//		samplestoragedetaildao.addSampleStorageDetail(spdetail);
		return 1;
	}

	@Override
	public PageBean showAllSamples(int rowsPerPage ,int page) {
		String hql = "select s, d.designCode from Sample as s, Design as d where d.designID = s.design.designID";
		PageBean result = pageService.queryForPage(hql, rowsPerPage, page);
		return result;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	@Override
	public PageBean showSamplesByFilter(int rowsPerPage, int page, Map filter) {
		String hql = "select s ,d.designCode from Sample as s, Design as d where d.designID = s.design.designID ";
		String samplePlace = (String)filter.get("samplePlace");
		String designCode = (String)filter.get("designCode");
		String sampleState = (String)filter.get("sampleState");
		if(!(samplePlace.equals("")||samplePlace==null)){
			hql+=" and s.samplePlace like '%"+samplePlace+"%' ";
		}
		if(!(designCode.equals("")||designCode==null)){
			hql+=" and d.designCode like '%"+designCode+"%' ";
		}
		if(!(sampleState.equals("")||sampleState==null)){
			hql+=" and s.sampleState like '%"+sampleState+"%' ";
		}
		System.out.println(hql);
		PageBean result = pageService.queryForPage(hql, rowsPerPage, page);
		System.out.println("搜索结果大小："+result.getList().size());
		return result;
	}

	@Override
	public Sample getSample(String location, String designCode) {
		return sampledao.getSample(location, designCode);
	}

	@Override
	public Sample addSample(String designCode, String samplePlace, String sampleState) {
		Sample sample = new Sample();
		sample.setSamplePlace(samplePlace);
		sample.setSampleState(sampleState);
		Design design = designdao.findByCode(designCode);
		sample.setDesign(design);
		return sampledao.addSample(sample);
	}

	@Override
	public int addSampleStorageDetail(
			SampleStorageDetail sampleStorageDetail) {
		return samplestoragedetaildao.addSampleStorageDetail(sampleStorageDetail);
	}

	@Override
	public String getExcelModel(){
		// TODO Auto-generated method stub
       String[] titles = {"样衣位置","样衣款号","样衣状态[在库/外借]","样衣出库时间","样衣入库时间","操作备注","加工户姓名","加工户电话"};
		//输出excel路径
		String path = getClass().getClassLoader().getResource("/").getPath()+"样衣表模板.xls";
		System.out.println(path);
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
			//创建excel工作簿
			 WritableWorkbook wwb;   
			 OutputStream os;
			try {
				os = new FileOutputStream(path);
	             wwb=Workbook.createWorkbook(os);  				         
	         //创建第一个工作表   样衣表
	         WritableSheet sheet = wwb.createSheet("样衣表", 0);   
	         Label label; 
	         for(int i=0;i<titles.length;i++){
	        	 label = new Label(i,0,titles[i]);
	        	 sheet.addCell(label);
	         }
	         // 写入数据  
	         wwb.write();  
	          // 关闭文件  
	          wwb.close();  
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
		return path;
	}
	
}
