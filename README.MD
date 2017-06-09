通识课管理系统
----

##参与者
+ 1.过客
+ 2.注册学生
+ 3.教师
+ 4.管理员

##功能需求

|角色|功能|
| --- | ---|
|过客|浏览：浏览网站各个静态栏目</br>注册：申请注册，待教师审批批准可以成为注册学生（只有选课学生可注册）|
|注册学生|登录：登录到网站</br>浏览：浏览网站各个栏目，下载课件、资料</br>做作业：下载作业，浏览作业，提交作业，撤销作业</br>听课：在线听课，课堂视频、微课等|
|教师|登录：登录到网站</br>浏览：浏览网站各个栏目，下载课件、资料</br>布置作业：发布作业</br>批改作业：浏览作业提交情况，下载作业，发布作业成绩</br>课程建设：发布和修改教学大纲、课件、资料、课程视频、微课等</br>听课：在线听课，课堂视频、微课等</br>新闻发布：在新闻栏发布各种有关课程、教学的新闻和通知</br>审批注册学生：对申请注册的学生进行审批，批准后发给学生消息：用户名和缺省密码|
|管理员|登录：登录到网站</br>教师管理：添加、删除教师</br>系统维护：系统维护|

##使用的技术

|技术|说明|
| --- | --- |
|Spring + Struts2 + Hibernate3|web框架|
|Bootstrap|前段框架|
|Jquery|前段框架|
|Layer|前段框架（弹出层）|
|uploadify|文件上传工具（基于JQuery）|
|ueditor|百度文本编辑器|
|AJAX|异步加载数据|

##配置说明

文件存储路径：D:\workspace-jee\taskManager\WebContent\upload

IDE:eclipse workspace位置：D:\workspace-jee\

系统平台：win7

数据库管理系统:mysql-installer-community-5.7.17.0.msi


eclipse版本:eclipse-jee-neon-3-RC3-win32-x86_64.zip


jdk版本:jdk-8u121-windows-x64.exe


服务器:apache-tomcat-8.5.11

其他工具：navicatformysql

数据库名称：taskmanager 

创建完数据库 运行脚本scripts.sql -》并使用数据库taskmanager   运行脚本taskmanager.sql

设置tomcat编码问题 

在Servers中找打服务器 在server.xml 中找到
 <Connector URIEncoding="UTF-8" connectionTimeout="20000" port="8081" protocol="HTTP/1.1" redirectPort="8443"/>

添加内容是 URIEncoding="UTF-8" 
###界面


