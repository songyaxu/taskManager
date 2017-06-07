<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	int i=0;
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>查看</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
	<script src="${pageContext.request.contextPath}/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/javascripts/layer/layer.js" type="text/javascript"></script>
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
	<script type="text/javascript">
		window.id=0;
		function load404(){
			window.location.href='${pageContext.request.contextPath}/404.html';
		}
		function confirm(id){
			window.id=id;
		}
		function deleteinfo()
		{
			$.post("infoService!delete.action",{'id':window.id},function(result){
				if(result.stateCode==0){
					layer.msg('删除失败！'+result.message, {icon: 2});
					load404();
				}else if(result.stateCode==1){
					layer.msg('删除成功！', {icon: 4});
					window.location.reload();
				}else if(result.stateCode==2){
					layer.msg('删除失败：'+result.message, {icon: 2});
				}else{
					layer.msg('删除失败：'+result.message, {icon: 2});
				}
			},"json");
		}
	</script>
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
            <h1 class="page-title">查看</h1>
<div class="well">
    <table class="table">
      <thead>
        <tr>
          <th>#</th>
          <th style="width:40%">标题</th>
          <th style="width:20%">发布人</th>
          <th style="width:20%">发布时间</th>
          <th style="width: 26px;"></th>
        </tr>
      </thead>
      <tbody>
      	<s:iterator value="#session.infos" id="infos">
      		<tr>
	      		<td><%=++i%></td>
	      		<td><a href="infoService!detail.action?id=<s:property value="#infos.id" />"><s:property value="#infos.name" /></a></td>
				<td><s:property value="#infos.author" /></td>
	          	<td><s:property value="#infos.time" /></td>
	          	<td>
		              <a href="#myModal" role="button" data-toggle="modal" onclick="confirm(<s:property value="#infos.id" />)"><i class="icon-remove"></i></a>
          		</td>
			</tr>
		</s:iterator>
      </tbody>
    </table>
    <hr>
    
    <div class="block-body">
                <div class="row-fluid" style="text-align: center;">
                    <div class="pull-left span4 unstyled">
                        <p>总记录数:<s:property value="infoPage.totalCount" /></p>
                    </div>
                    <div class="pull-left span4 unstyled">
                        <p>每页记录数：<s:property value="infoPage.everyPage" /></p>
                    </div>
                    <div>
                        <p>总页数：<s:property value="infoPage.totalPage" /></p>
                    </div>
                    </div>
                    <div class="clearfix"></div>
	</div>
	<div class="pagination">
    <ul>
		<s:if test="#request.infoPage.hasPrePage==true">
		<li><a href="scaninfo!frontPage.action?currentPage=<s:property value="infoPage.currentPage" />&style=1" class="btu">上一页</a></li>
		</s:if>
		<li><a href="#" style="color:#000;"><s:property value="infoPage.currentPage" /></a></li>
		<s:if test="#request.infoPage.currentPage+1<=#request.infoPage.totalPage">
		<li><a href="scaninfo!nextPage.action?currentPage=<s:property value="infoPage.currentPage" />&style=1" class="btu"><s:property value="infoPage.currentPage+1" /></a></li>
		</s:if>
		<s:if test="#request.infoPage.currentPage+2<=#request.infoPage.totalPage">
		<li><a href="scaninfo!nextPage.action?currentPage=<s:property value="infoPage.currentPage+1" />&style=1" class="btu"><s:property value="infoPage.currentPage+2" /></a></li>
		</s:if>
		<s:if test="#request.infoPage.currentPage+3<=#request.infoPage.totalPage">
		<li><a href="scaninfo!nextPage.action?currentPage=<s:property value="infoPage.currentPage+2" />&style=1" class="btu"><s:property value="infoPage.currentPage+3" /></a></li>
		</s:if>
		<s:if test="#request.infoPage.hasNextPage==true">
		<li><a href="scaninfo!nextPage.action?currentPage=<s:property value="infoPage.currentPage" />&style=1" class="btu">下一页</a></li>
		</s:if>
	</ul>
</div>

<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">警告</h3>
    </div>
    <div class="modal-body">
        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>你确定要删除这条记录吗?</p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
        <button class="btn btn-danger" data-dismiss="modal" onclick="deleteinfo();">确定</button>
    </div>
</div>

        </div>
    </div>
    </div>

    
    <footer>
        <hr>
        <p class="pull-right">Copyright <a title="内蒙古大学" target="_blank">内蒙古大学</a></p>
        <p>&copy; 2017 <a>内蒙古大学</a></p>
    </footer>
    

    

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
    
    
    
    
    
    
    
    
    
    
    
    

  </body>
</html>
