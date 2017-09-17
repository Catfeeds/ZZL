<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

    <%@ include file="../../header.jsp"%>
    <link href="../css/sampleOrders.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" media="screen">
    <title>智造链-样衣单</title>
</head>
<body class="skin-blue">
 	<!-- header logo: style can be found in header.less -->
    <header class="header">
        <a href="index.html" class="logo">
        <!-- Add the class icon to your logo image or logo icon to add the margining -->
            智&nbsp造&nbsp链
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
        </nav>
    </header>

    <div class="wrapper row-offcanvas row-offcanvas-left">
    	<!-- 左边导航栏  -->
        <jsp:include page="../../navigation.jsp"></jsp:include>

        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    NEW客户订单管理
                    <small>NEW样衣单管理</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">Dashboard</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="sampleCondition">
                    <a class="search-title">制版人：</a>
                    <input type="text" id="maker" class="input-panel" placeholder="" >                
               </div>  
               <div class="sampleCondition">
                    <a class="search-title">时间：</a>
                    <input type="text" id="sampleOrder_date" class="input-panel" placeholder="" >                
               </div> 
               <div class="sampleCondition" style="width:20%;">
                    <a class="search-title">进度：</a>
                    <select id="sampleOrderProgress" class="form-control">
                        <option>请选择</option>
                        <option>无</option>
                        <option>待制版</option>
                        <option>待制作样衣</option>
                        <option>待确认样衣</option>
                        <option>待放码</option>
                        <option>待审版</option>
                        <option>完结</option>
                    </select>
               </div>
               <div class="sampleCondition">
                    <button type="button" id="search-sampleOrders" class="btn btn-primary" style="padding:0px 15px;">查询</button>
                </div>
                <br/>
                <div class="label-search">
                    <a href="<%=request.getContextPath()%>/SampleOrders/search_sampleOrders?page=${PageBean.currentPage}&rowsPerPage=${PageBean.pageSize}&maker=&date=&sampleOrderProgress=待制版"><span class="label label-primary">待制版</span></a>
                    <a href="<%=request.getContextPath()%>/SampleOrders/search_sampleOrders?page=${PageBean.currentPage}&rowsPerPage=${PageBean.pageSize}&maker=&date=&sampleOrderProgress=待制作样衣"><span class="label label-primary">待制作样衣</span></a>
                    <a href="<%=request.getContextPath()%>/SampleOrders/search_sampleOrders?page=${PageBean.currentPage}&rowsPerPage=${PageBean.pageSize}&maker=&date=&sampleOrderProgress=待确认样衣"><span class="label label-primary">待确认样衣</span></a>
                    <a href="<%=request.getContextPath()%>/SampleOrders/search_sampleOrders?page=${PageBean.currentPage}&rowsPerPage=${PageBean.pageSize}&maker=&date=&sampleOrderProgress=待放码"><span class="label label-primary">待放码</span></a>
                    <a href="<%=request.getContextPath()%>/SampleOrders/search_sampleOrders?page=${PageBean.currentPage}&rowsPerPage=${PageBean.pageSize}&maker=&date=&sampleOrderProgress=待审版"><span class="label label-primary">待审版</span></a>
                    <a href="<%=request.getContextPath()%>/SampleOrders/search_sampleOrders?page=${PageBean.currentPage}&rowsPerPage=${PageBean.pageSize}&maker=&date=&sampleOrderProgress=完结"><span class="label label-primary">完结</span></a>
                </div>
                <br/><br/><br/>
                <div class="table-responsive">
                	<a style="color:#AAAAAA;">共<s:property value="PageBean.allRow"/>条样衣单</a>
                    <!-- .table - Uses sparkline charts-->
                    <table class="table table-striped">
                    	<thead>
	                        <tr>
	                            <th></th>
	                            <th>图片</th>
	                            <th>样衣单信息</th>
	                            <th>制版人</th>
	                            <th>进度</th>
	                        </tr>
                        </thead>
                        <tbody>
	                        <s:iterator value="PageBean.list" var="sampleOrder">
	                        <tr onclick="javascript:judgeSelected(this)">
	                            <td style="vertical-align:middle;"><input type="radio" name="select"></td>
	                            <td>
	                                <img src="../images/1.png" class="commdity-graph">
	                            </td>
	                            <td style="vertical-align:middle;">
	                                <a class="HoverUnderline" href="<%=request.getContextPath() %>/SampleOrders/sampleOrder?sampleOrderID=<s:property value='#sampleOrder.sampleOrderID'/>">样衣单号：<s:property value="#sampleOrder.sampleOrderCode"/></a><br/>
	                                <a>时间：<s:property value="#sampleOrder.sampleOrderTime"/></a>
	                            </td>
	                            <td style="vertical-align:middle;width:45%;">
	                                <font color="red"><s:property value="#sampleOrder.maker.userName"/></font>
	                            </td>
	                            <td style="vertical-align:middle;"><s:property value="#sampleOrder.sampleProcedure.sampleOrderProgress"/></td>
	                        </tr>  
	                        </s:iterator>  
                        </tbody>                   
                    </table><!-- /.table -->
                </div>
                <!-- 分页-->
	            <%@ include file="../../page.jsp"%>
	            <!-- 分页-->
            </section>
        </aside>  
    </div> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script type="text/javascript">
	 $('#sampleOrder_date').datetimepicker({
		 	language:  'zh-CN',
	        format : 'yyyy-mm-dd',
	        weekStart : 1,
	        todayBtn : 1,
	        autoclose : 1,
	        todayHighlight : 1,
	        startView : 2,
	        minView : 2,
	        maxView : 4,
	        forceParse : 0,
	    });
	</script>
    <script type="text/javascript">
    	window.onload = changeTab("11200","11202");
    	searchSampleOrders();
    	function searchSampleOrders(){
    		$("#search-sampleOrders").click(function(){
    			var maker = $("#maker").val();
                var date = $("#sampleOrder_date").val();;
                var sampleOrderProgress = $("#sampleOrderProgress option:selected").text();
                sampleOrderProgress = (sampleOrderProgress=="请选择")?"":sampleOrderProgress;
                
                var url = window.location.href.split("?")[0];
                url += "?page=1";
                var purl = location.search; //获取url中"?"符后的字串
                var paramsList = new Object();
                if (purl.indexOf("?") != -1) {
                   var str = purl.substr(1);
                   strs = str.split("&");
                   for(var i = 0; i < strs.length; i ++) {
                      paramsList[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
                   }
                }
                //第一次搜索和第N次搜索
                if (url.indexOf("search_sampleOrders")==-1) {
	                url = url.replace("sampleOrders","search_sampleOrders");
	                for (var j in paramsList){
	                	if(j=="rowsPerPage")
	                		url += "&rowsPerPage="+paramsList[j];
	                }
	                url += "&maker="+maker+"&date="+date+"&sampleOrderProgress="+sampleOrderProgress;
                }else{
                	//修改参数项
                    for (var j in paramsList){
                    	if(j=="rowsPerPage"){
                    		url+= "&rowsPerPage="+paramsList[j];
                    	}
                    	if(j=="maker"){
                    		paramsList[j] = maker;
                    		url+= "&maker="+paramsList[j];
                    	}
                    	if(j=="date"){
                    		paramsList[j] = date;
                    		url+= "&date="+paramsList[j];
                    	}
                    	if(j=="sampleOrderProgress"){
                    		paramsList[j] = sampleOrderProgress;
                    		url+= "&sampleOrderProgress="+paramsList[j];
                    	}
                    }
                }
                //alert(url);
				window.location.href = url;
    		});
    	}
    </script>
</body>
</html>