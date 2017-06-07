<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>学生注册</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath}/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/javascripts/layer/layer.js" type="text/javascript"></script>
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
	<script type="text/javascript">
	function validate(){
		var courseNo=$("#courseNo").val();
		$.post("courseservice!queryByNo.action",{'no':courseNo},function(result){
			if(result.stateCode==1){
				layer.msg('匹配到结果！', {icon: 4});
				$("#courseName").val(result.course.name);
				$("#teacherName").val(result.course.teacherName);
			}
			else
			{
				$("#courseName").val("");
				$("#teacherName").val("");
			}
		},"json");
	}
	function submitData(){
		var courseNo=$("#courseNo").val();
		var courseName=$("#courseName").val();
		var stuNo=$("#stuNo").val();
		var stuName=$("#stuName").val();
		
		if(stuNo==null || stuNo==''){
			layer.msg('请输入学号！', {icon: 2});
		}else if(stuName==null || stuName==''){
			layer.msg('请输入姓名！', {icon: 2});
		}else if(courseNo==null || courseNo==''){
			layer.msg('请输入正确的课程编号！', {icon: 2});
		}else if(courseName==null || courseName==''){
			layer.msg('请输入正确的课程编号！', {icon: 2});
		}else{
			$.post("regservice!add.action",{'reg.no':stuNo,'reg.name':stuName,'reg.courseNo':courseNo},function(result){
				if(result.stateCode==1){
					layer.msg(result.message, {icon: 4});
					resetValue();
				}else{
					layer.msg(result.message, {icon: 2});
				}
			},"json");
		}
	}
	function resetValue(){
		$("#courseNo").val("");
		$("#courseName").val("");
		$("#stuNo").val("");
		$("#stuName").val("");
		$("#teacherName").val("");
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
                    
                </ul>
               <a class="brand" href="index.html"><span class="first">通识课</span> <span class="second">计算机与信息社会</span></a>
            </div>
        </div>
    </div>
    

    <div class="container-fluid">
        
        <div class="row-fluid">
    <div class="span4 offset4 dialog">
        <div class="block">
            <div class="block-heading">注册</div>
            <div class="block-body">
                <form>
                    <label>学号</label>
                    <input type="text" class="span12" name="stuNo" id="stuNo">
                    <label>姓名</label>
                    <input type="text" class="span12" name="stuName" id="stuName">
                    <label>选修课程号</label>
                    <input type="text" class="span12" name="courseNo" id="courseNo" onblur="validate()">
                    <label>选修课程</label>
                    <input type="text" class="span12" name="courseName" id="courseName" disabled="disabled">
                    <label>任课教师</label>
                    <input type="text" class="span12" name="teacherName" id="teacherName" disabled="disabled">
                    <a href="#" class="btn btn-primary pull-right" onclick="submitData();">申请</a>
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
       
    </div>
</div>


    

    

    

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
    
    
    
    
    
    
    
    
    
    
    

  </body>
</html>


