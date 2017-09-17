<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../header.jsp"></jsp:include>
<title>智造链-出库</title>

<style type="text/css">
</style>


</head>
<body class="skin-blue">
	<header class="header"> <a href="index.html" class="logo">
		<!-- Add the class icon to your logo image or logo icon to add the margining -->
		智&nbsp造&nbsp链
	</a> <!-- Header Navbar: style can be found in header.less --> <nav
		class="navbar navbar-static-top" role="navigation"> </nav> </header>

	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->
		<jsp:include page="../../../navigation.jsp"></jsp:include>

		<!-- Right side column. Contains the navbar and content of the page -->
		<aside class="right-side"> <!-- Content Header (Page header) -->
		<section class="content-header">
		<h1>
			出库管理 <small>出库</small>
		</h1>
		</section> <!-- Main content --> <section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">基础信息</h3>
					</div>
					<form class="form-horizontal" role="form" method="post" action="<%=request.getContextPath()%>/Warehouse/materialOut_showStoresByFilter">
						<div class="form-group">
							<label class="col-md-2 control-label">物料编号</label>
							<div class="col-md-3">
								<input type="text" class="form-control" name="searchMaterialCode">
							</div>
							<label class="col-md-2 col-md-offset-1 control-label">物料名称</label>
							<div class="col-md-3">
								<input type="text" class="form-control" name="searchMaterialName">
							</div>
							<br/>
							<br/>
							<label class="col-md-2 control-label">款号</label>
							<div class="col-md-3">
								<input type="text" class="form-control" name="searchDesignCode">
							</div>
							<label class="col-md-2 col-md-offset-1 control-label">预定单号</label>
							<div class="col-md-3">
								<input type="text" class="form-control" name="searchMaterialApplyCode">
							</div>
							<br/><br/>
							<div class="col-md-offset-10 col-md-1">
								<button class="btn btn-primary" type="submit" style="float:right">查询</button>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-md-12" style="padding: 0px 20px 0px 20px;width:100%">
							<table class="table table-striped table-responsive" style="width:100%">
								<tr style="width:100%">
									<th style="width:8%">预定单号</th>
									<th>物料编码</th>
									<th>物料名称</th>
									<th>储存位置</th>
									<th style="width:6%">库存数</th>
									<th style="width:8%">可用库存数</th>
									<th style="width:8%">预定出仓数</th>
									<th style="width:8%">实际出仓数</th>
									<th style="width:6%">查看</th>
								</tr>
								<s:iterator value="pageBean.list" id="item">
										<tr style="cursor:pointer"
											onclick="showStoreInfo('<s:property value="#item.materialApplyCode" />','<s:property value="#item.materialCode" />','<s:property value="#item.materialName" />',<s:property value='#item.warehouseId' />,<s:property value="#item.remainVol" />)">
											<td><s:property value="#item.materialApplyCode" /></td>
											<td><s:property value="#item.materialCode" /></td>
											<td><s:property value="#item.materialName" /></td>
											<td><s:property value="#item.location" /></td>
											<td><s:property value="#item.remainVol" /></td>
											<td><s:property value="#item.remain" /></td>
											<td><s:property value="#item.materialApplyVol" /></td>
											<td><s:property value="#item.actualOutVol" /></td>
											<td><a data-toggle='modal' data-target='#detailModel'
												onclick="fillDetailInfo('<s:property value='#item.materialCode' />',<s:property value='#item.warehouseId' />)">查看</a></td>
										</tr>
								</s:iterator>

							</table>
						</div>
					</div>
					<div class="row" style="margin-left: 10px">
						<%@include file="../../../page.jsp"%>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../public/storeDetailModal.jsp"%>
		</section> <section class="content" style="padding-top:0px;display:none"
			id="storeInfoSection">
		<div class="row">
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">库存信息</h3>
					</div>
					<form class="form-horizontal" method="post" role="form" id="outMaterialForm"
						action="<%=request.getContextPath()%>/Warehouse/materialOut_doMaterialOut">
						<input name="materialApplyCode" type="text" style="display:none">
						<input name="materialCode" type="text" style="display:none">
						<input name="warehouseId" type="text" style="display:none">
						<div class="form-group">
							<label class="col-md-2 control-label">出库数量：</label>
							<div class="col-md-2">
								<input name="outVol" type="text" class="form-control">
							</div>
							<label class="col-md-7 control-label" style="color: red"
								id="outInfo"></label>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">备注：</label>
							<div class="col-md-9">
								<input name="comment" type="text" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-10 col-md-1">
								<button type="submit" class="btn btn-primary">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		</section> </aside>
	</div>
	<script type="text/javascript">
    	//导航栏设置
    	window.onload = changeTab("10200","10201");
    	
    	//显示库存信息
    	function showStoreInfo(materialApplyCode,materialCode,materialName,warehouse,remainVol){
    		//为隐藏的表单添加数据：
    		$("#outMaterialForm input[name='materialCode']").val(materialCode);
    		$("#outMaterialForm input[name='warehouseId']").val(warehouse);
    		$("#outMaterialForm input[name='materialApplyCode']").val(materialApplyCode);
    		$("#storeInfoSection").css("display","block");
    		content = "(预订单号："+materialApplyCode+"；物料名："+materialName+"；仓库号："+warehouse+"；剩余数："+remainVol+")";
    		$("#outInfo").html(content);
    		window.location.hash="#storeInfoSection";
    	}
    	
    	function fillDetailInfo(material, warehouse){
    		params = {
    				"materialCode":material,
    				"warehouseid":warehouse
    		};
    		jQuery.post("/ZZL/Warehouse/warehousePublicAjax_getStoreDetailInfoAjax",params,function(data){
    			obj = eval("("+data+")");
    			$("#materialName").html(obj.material.materialName);
    			$("#materialCode").html(obj.material.materialCode);
    			$("#materialType").html(obj.material.materialType);
    			$("#unitPrice").html(obj.material.unitPrice);
    			$("#colorCode").html(obj.material.colorCode);
    			$("#colorDescription").html(obj.material.colorDescription);
    			$("#materialIngredient").html(obj.material.materialIngredient);
    			$("#remain").html(obj.warehouse.remain);
    			$("#remainVol").html(obj.store.remainVol);
    			$("#unit").html(obj.material.unit);
    			$("#location").html(obj.warehouse.location);
    			vendors = obj.vendors;
    			content = "";
    			for(i =0;i<vendors.length;i++){
    				content += ("<tr><td>"+vendors[i].vendorId+"</td><td>"+vendors[i].vendorName+"</td><td>"+vendors[i].vendorMobileNum+"</td><td>"+vendors[i].vendorAddr+"</td></tr>");
    			}
    			$("#vendors").html(content);
    		},"json");
    	}
    </script>
</body>
</html>