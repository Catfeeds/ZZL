<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

    <jsp:include page="../../header.jsp"></jsp:include>

    <title>智造链-样衣信息</title>
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
        <!-- Left side column. contains the logo and sidebar -->
        <jsp:include page="../../navigation.jsp"></jsp:include> 
        
        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                   	样衣信息
                    <small>样衣入库</small>
                </h1>
            </section>

            <!-- Main content -->
            <section class="content">
            	<div class="row">
            		<div class="col-md-12">
            			<div class="box box-primary">
            				<div class="box-header">
            					<h3 class="box-title">样衣新增入库</h3>
            				</div>
            				<form class="form-horizontal" role="form" method="post" action="addInSample">
            					<div class="form-group">
	            					<label class="col-md-2 col-md-offset-1 control-label" >款号:</label>
	            					<div class="col-md-3">
	            						<input type="text" class="form-control" name="designID">
	            					</div>
	            					
	            					<label class="col-md-2  control-label">进出时间:</label>
	            					<div class="col-md-3">
	            						<input type="text" class="form-control" name="sampleTime">
	            					</div>
									<br/><br/><br/>
	            					<label class="col-md-2 col-md-offset-1 control-label">入库位置:</label>
	            					<div class="col-md-3">
	            						<input type="text" class="form-control" name="samplePlace">
	            					</div>
	            					
	            					<label class="col-md-2  control-label">操作:</label>
	            					<label class="col-md-3 control-label" style="text-align:left;color:red;">样衣入库</label>
	            					
	            					<br/><br/><br/>
	            					<label class="col-md-2 col-md-offset-1 control-label" >操作备注:</label>
	            					<div class="col-md-9">
	            						<textarea style="width:100%;" placeholder="备注格式：人名-事由" name="sampleOPComment"></textarea>
	            					</div>
	            					
	            					<br/><br/><br/>
	            					<div class="col-md-2 col-md-offset-1" style="text-align:right;">
	            					<button class="btn btn-primary" type="submit">保存</button>
	            					</div>
            					</div>
            					
            				</form>
            			</div>
            			
            		</div>
            	</div>
            </section>
        </aside>
    </div>


</body>
</html>