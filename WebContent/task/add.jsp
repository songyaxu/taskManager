<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>布置作业</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">
    <link href="${pageContext.request.contextPath}/lib/uploadify/uploadify.css" rel="stylesheet" type="text/css" />  
	<script src="${pageContext.request.contextPath}/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<link href="${pageContext.request.contextPath}/lib/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jQuery-Timepicker/i18n/jquery-ui-timepicker-zh-CN.js"></script>
	<link href="${pageContext.request.contextPath}/lib/jQuery-Timepicker/jquery-ui-timepicker-addon.min.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/lib/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/lib/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/javascripts/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/ueditor/ueditor.all.min.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
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
	<script type="text/javascript">  
        $(function() {  
        	$.datepicker.regional['zh-CN'] = {
   				changeMonth: true,
   				changeYear: true,
   				clearText: '清除',
   				clearStatus: '清除已选日期',
   				closeText: '关闭',
   				closeStatus: '不改变当前选择',
   				prevText: '<上月',
   				prevStatus: '显示上月',
   				prevBigText: '<<',
   				prevBigStatus: '显示上一年',
   				nextText: '下月>',
   				nextStatus: '显示下月',
   				nextBigText: '>>',
   				nextBigStatus: '显示下一年',
   				currentText: '今天',
   				currentStatus: '显示本月',
   				monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
   				monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
   				monthStatus: '选择月份',
   				yearStatus: '选择年份',
   				weekHeader: '周',
   				weekStatus: '年内周次',
   				dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
   				dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
   				dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
   				dayStatus: '设置 DD 为一周起始',
   				dateStatus: '选择 m月 d日, DD',
   				dateFormat: 'yy-mm-dd',
   				firstDay: 1,
   				initStatus: '请选择日期',
   				isRTL: false
   			};
           	$('#startTime').prop("readonly", true).datetimepicker({
				timeText: '时间',
				hourText: '小时',
				minuteText: '分钟',
				secondText: '秒',
				currentText: '现在',
				closeText: '完成',
				showSecond: true, //显示秒  
				timeFormat: 'HH:mm:ss' //格式化时间  
			});
        	$('#endTime').prop("readonly", true).datetimepicker({
				timeText: '时间',
				hourText: '小时',
				minuteText: '分钟',
				secondText: '秒',
				currentText: '现在',
				closeText: '完成',
				showSecond: true, //显示秒  
				timeFormat: 'HH:mm:ss' //格式化时间  
			});
        	$('#link').val("");
            $('#portrait').uploadify({  
                'swf'            : '${pageContext.request.contextPath}/lib/uploadify/uploadify.swf',  
                'uploader'       : 'Uploadify',  
                'queueID'        : 'fileQueue',  
                'auto'           : false,  
                'multi'          : true,  
                'queueSizeLimit' : 3,  
                'fileTypeDesc'   : '文件',  
                //'fileTypeExts'   : '', //控制可上传文件的扩展名  
                'buttonText'     : '选择上传文件',  
                'fileSizeLimit' : '50MB',  
                'fileObjName' : 'portrait',  
                'method' : 'post',
                'removeCompleted' : true,
                'onUploadSuccess' : function(file, data, response){
                		var obj=$.parseJSON(data);
            	    	$('#link').val($('#link').val()+obj.fName+':');
            	    	$("<label class='fnlist'>"+obj.portraitFileName+"</label>").insertAfter("#filelist");
                },  
                'onQueueComplete' : function(){
                	layer.msg("上传成功！", {icon: 4});
                }  
            });  
        });  
        </script> 
    <script type="text/javascript">
	
	function submitData(){
		var title=$("#title").val();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var courseNo=$("#courseNo").val();
		var content=UE.getEditor('editor').getContent();
		var attach=$("#link").val().substring(0,$("#link").val().length-1);
		
		if(title==null || title==''){
			layer.msg('请输入标题！', {icon: 2});
		}else if(content==null || content==''){
			layer.msg('请输入内容！', {icon: 2});
		}else if(startTime==null || startTime==''){
			layer.msg('请输入起始时间！', {icon: 2});
		}else if(endTime==null || endTime==''){
			layer.msg('请输入结束时间！', {icon: 2});
		}else if(courseNo==null || courseNo==''){
			layer.msg('发布失败：可能因为您还没有建设课程！', {icon: 2});
		}else{
			$.post("taskservice!add.action",{'task.name':title,'task.attach':attach,'task.content':content,'start':startTime,'end':endTime},function(result){
				if(result.stateCode==1){
					layer.msg(result.message, {icon: 4});
					window.setTimeout(window.location.reload(), 5000); 
				}else{
					layer.msg(result.message, {icon: 2});
				}
			},"json");
		}
	}
	
	</script>    
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
	                  <li class="active"><a href="taskservice!edit.action">布置作业</a></li>
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
            <h1 class="page-title">布置作业</h1>
<div class="btn-toolbar">
    <button class="btn btn-primary" onclick="submitData()"><i class="icon-save"></i> 保存</button>
    <a href="#myModal" data-toggle="modal" class="btn">取消</a>
  <div class="btn-group">
  </div>
</div>
<div class="well">
    <ul class="nav nav-tabs">
      <li class="active"><a href="#home" data-toggle="tab">基本信息</a></li>
      <li><a href="#profile" data-toggle="tab">详细内容</a></li>
      <li><a href="#attach1" data-toggle="tab">上传附件</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="home">
    <form id="tab">
        <label>标题</label>
        <input type="text" name="info.name" id="title" class="input-xlarge">
        <label>课程编号</label>
        <input type="text" name="task.courseNo" id="courseNo" class="input-xlarge" value='<s:property value="#request.course.no"/>' disabled="disabled">
        <label>课程名称</label>
        <input type="text" name="task.courseName" id="courseName" class="input-xlarge" value='<s:property value="#request.course.name"/>' disabled="disabled">
        <label>开始时间</label>
        <input type="text" name="task.startTime" id="startTime" class="input-xlarge">
        <label>结束时间</label>
        <input type="text" name="task.endTime" id="endTime" class="input-xlarge">
    </form>
      </div>
      <div class="tab-pane fade" id="profile">
      		<script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
      </div>
      <div class="tab-pane fade" id="attach1">
    	<form id="tab2">
	        <label>已上传附件列表</label>
	        <div id="filelist">
	        	
	        </div>
	        <div id="fileQueue"></div>  
	        <input type="file" name="xxx" id="portrait" />
	        <input type="hidden" name="info.attach" id="link">  
	        <p>  
	        <a class="btn btn-primary" href="javascript:jQuery('#portrait').uploadify('upload','*')">开始上传</a>   
	        <a class="btn" href="javascript:jQuery('#portrait').uploadify('cancel')">取消所有上传</a>  
	        </p>  
    	</form>
      </div>
  </div>

</div>
			<script type="text/javascript">
		       	//实例化编辑器
		        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		      	var ue = UE.getEditor('editor');
			</script>
<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">警告</h3>
  </div>
  <div class="modal-body">
    
    <p class="error-text"><i class="icon-warning-sign modal-icon"></i>你确定要取消编辑?</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
    <button class="btn btn-danger" data-dismiss="modal">确定</button>
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