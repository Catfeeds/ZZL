<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    
    <!-- css,js 引包 -->
    <jsp:include page="header.jsp"></jsp:include>

    <title>智造链</title>
</head>

<body class="skin-blue" style="vertical-align:middle;">
	<s:if test="#request.session.account!=null">
	<!-- 人物头像 -->
	<jsp:include page="headSculpture.jsp"></jsp:include>
	<!-- 人物头像 -->
    <div class="wrapper row-offcanvas row-offcanvas-left" style="background-color:#f9f9f9;">
        <!-- 左边导航栏  -->
        <jsp:include page="navigation.jsp"></jsp:include>
        
        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    Dashboard
                    <small>Control panel</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">Dashboard</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
            </section>
        </aside>
    </div>
	</s:if>
	<s:else>
		<jsp:include page="login.jsp"></jsp:include>
	</s:else>
</body>
</html>