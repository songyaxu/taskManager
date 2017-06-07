<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath}/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

    <!-- Demo page code -->
    
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7"> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8"> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9"> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body> 
  <!--<![endif]-->
    
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <ul class="nav pull-right">
                    
                    <li id="fat-menu" class="dropdown">
                        <a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> ${user.name}
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                        	<s:if test="#session.user.type==3">
                        		<li><a tabindex="-1" href="adminservice!edit.action?id=<s:property value="#session.user.id" />">个人信息</a></li>
                        	</s:if>
                            <s:elseif test="#session.user.type==2">
                            	<li><a tabindex="-1" href="teacherservice!edit.action?id=<s:property value="#session.user.id" />">个人信息</a></li>
                            </s:elseif>
                            <s:elseif test="#session.user.type==1">
                            	<li><a tabindex="-1" href="studentservice!edit.action?id=<s:property value="#session.user.id" />">个人信息</a></li>
                            </s:elseif>
                            <li class="divider"></li>
                            <li><a tabindex="-1" href="Login!logout.action">注销</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="index.jsp"><span class="first">通识课</span> <span class="second">计算机与信息社会</span></a>
            </div>
        </div>
    </div>
    

    <div class="container-fluid">
        
        <div class="row-fluid">
            <div class="span3">
                <div class="sidebar-nav">
                  <div class="nav-header" data-toggle="collapse" data-target="#dashboard-menu"><i class="icon-dashboard"></i>资讯管理</div>
                    <ul id="dashboard-menu" class="nav nav-list collapse in">
                        <li><a href="scaninfo?style=1">查看资讯</a></li>
                        <s:if test="#session.user.type==2||#session.user.type==3">
                        	<li ><a href="${pageContext.request.contextPath}/info/add.jsp">发布资讯</a></li>
                        </s:if>
                    </ul>
                <div class="nav-header" data-toggle="collapse" data-target="#accounts-menu"><i class="icon-briefcase"></i>课程介绍</div>
                <ul id="accounts-menu" class="nav nav-list collapse in">
                  <li ><a href="scancourse">课程浏览</a></li>
                  <s:if test="#session.user.type==2">
                  	<li ><a href="courseservice!edit.action">课程建设</a></li>
                  </s:if>
                </ul>
				<div class="nav-header" data-toggle="collapse" data-target="#account-menu"><i class="icon-briefcase"></i>教师队伍</div>
                <ul id="account-menu" class="nav nav-list collapse in">
                  <s:if test="#session.user.type==3">
                  	<li ><a href="${pageContext.request.contextPath}/admin/addadmin.jsp">增加管理员</a></li>
                  	<li ><a href="scanadmin">管理员管理</a></li>
                  	<li ><a href="${pageContext.request.contextPath}/admin/addteacher.jsp">增加教师</a></li>
                  </s:if>
                  <li >
                  	<s:if test="#session.user.type==3">
                  		<a href="scanteacher">教师管理</a>
                  	</s:if>
                  	<s:else>
                  		<a href="scanteacher">教师队伍</a>
                  	</s:else>
                  </li>
                </ul>
                <div class="nav-header" data-toggle="collapse" data-target="#settings-menu"><i class="icon-exclamation-sign"></i>学习资料</div>
                <ul id="settings-menu" class="nav nav-list collapse in">
                  <li ><a href="scaninfo?style=4">教学资源</a></li>
                  <s:if test="#session.user.type!=0">
                  	  <s:if test="#session.user.type==2">
	                  <li ><a href="${pageContext.request.contextPath}/res/add.jsp">发布资源</a></li>
	                  </s:if>
	                  <li ><a href="scaninfo?style=2">教学课件</a></li>
	                  <s:if test="#session.user.type==2">
	                  <li ><a href="${pageContext.request.contextPath}/cw/add.jsp">发布课件</a></li>
	                  </s:if>
	                  <li><a href="scaninfo?style=3">课堂在线</a></li>
	                  <s:if test="#session.user.type==2">
	                  <li ><a href="${pageContext.request.contextPath}/cr/add.jsp">发布课堂</a></li>
	                  </s:if>
	              </s:if>
                </ul>
				<s:if test="#session.user.type==2||#session.user.type==1">
	                <div class="nav-header" data-toggle="collapse" data-target="#legal-menu"><i class="icon-legal"></i>作业</div>
	                <ul id="legal-menu" class="nav nav-list collapse in">
	                  <li ><a href="scantask">查看作业</a></li>
	                  <s:if test="#session.user.type==2">
	                  <li ><a href="taskservice!edit.action">布置作业</a></li>
	                  </s:if>
	                </ul>
                </s:if>
                <s:if test="#session.user.type==2">
	                <div class="nav-header" data-toggle="collapse" data-target="#legal-menus"><i class="icon-legal"></i>注册申请</div>
	                <ul id="legal-menus" class="nav nav-list collapse in">
	                  <li ><a href="scanreg">查看申请</a></li>
	                </ul>
                </s:if>
            </div>
            
        </div>
        <div class="span9">
            <script type="text/javascript" src="${pageContext.request.contextPath}/lib/jqplot/jquery.jqplot.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/javascripts/graphDemo.js"></script>

<div class="row-fluid">
    <div class="block span6">
        <div class="block-heading" data-toggle="collapse" data-target="#tablewidget">最新资讯</div>
        <div id="tablewidget" class="block-body collapse in">
            <table class="table">
              <tbody>
                <s:iterator value="#session.indexinfos" id="infos">
		      		<tr>
			      		<td style="width:70%;"><a href="infoService!detail.action?id=<s:property value="#infos.id" />"><s:property value="#infos.name" /></a></td>
			          	<td><s:property value="#infos.time" /></td>
					</tr>
				</s:iterator>
              </tbody>
            </table>
            <p><a href="scaninfo?style=1">更多..</a></p>
        </div>
    </div>
    <div class="block span6">
        <div class="block-heading" data-toggle="collapse" data-target="#widget1container">教师队伍</div>
        <div id="widget1container" class="block-body collapse in">
            <h2>师资队伍介绍</h2>
            <p>学院拥有一支职称结构合理、学历层次较高、敬业精神强的师资队伍。</p>
            <p>专任教师43人，其中高级职称的比例为44.18％，具有研究生学历的比例达到97.2％（其中具有博士学位的比例达到68%），</p>
            <p>青年教师中具有研究生学历的比例为100％。</p>
            <p>下图为学院专任教师职称分布饼状图。</p>
            <img src="${pageContext.request.contextPath}/images/pic_tcteam.jpg" />
        </div>
    </div>
</div>
<div class="copyrights">Collect from <a href="http://www.cssmoban.com/"  title="网站模板">网站模板</a></div>
<div class="row-fluid">
    <div class="block span6">
        <div class="block-heading" data-toggle="collapse" data-target="#widget2container">教学课件</div>
        <div id="widget2container" class="block-body collapse in">
            <table class="table">
              <tbody>
              	  <s:iterator value="#session.indexcw" id="indexcw">
		      		<tr>
		      			<td>
                          <p><i class="icon-user"></i><s:property value="#infos.author" /></p>
                      	</td>
                      	<td>
                          <p><a href="infoService!detail.action?id=<s:property value="#indexcw.id" />"><s:property value="#indexcw.name" /></a></p>
                        </td>
			      		<td>
                          <p><s:property value="#infos.time" /></p>
                        </td>
					</tr>
				  </s:iterator>	
              </tbody>
            </table>
             <p><a href="scaninfo?style=2">更多..</a></p>
        </div>
        
    </div>
    <div class="block span6">
        <p class="block-heading">教学资源</p>
        <div class="block-body">
            <s:iterator value="#session.indexres" id="res">
            	<p><a href="<s:property value="#res.content" />" target="_blank"><s:property value="#res.name" /></a></p>
			</s:iterator>
            <p><a class="btn btn-primary btn-large" href="scaninfo?style=4">更多 &raquo;</a></p>
        </div>
    </div>
</div>

        </div>
    </div>
    

    
    <footer>
        <hr>
        
        <p class="pull-right">Collect from <a href="#" title="网页模板" target="_blank">网页模板</a></p>
        
        
        <p>&copy; 2017 <a href="#">Portnine</a></p>
    </footer>
    

    

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
  

  </body>
</html>