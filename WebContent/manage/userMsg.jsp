<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基本信息</title>
    <link rel="stylesheet" type="text/css" href="static/css/base.css" >
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" type="text/css" href="static/css/edit.css">
   
  </head>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
  <body>
    <div class="container">
    <div class="content">
        <div title="纳税人信息" data-options="closable:false" class="basic-info">
            <div class="column"><span class="current">修改密码</span></div>
            <table class="kv-table">
                <tbody>
                <tr>
                    <td class="kv-label">编号</td>
                    <td class="kv-content">${user.id}</td>
                    <td class="kv-label">姓名</td>
                    <td class="kv-content">${user.username}</td>
                </tr>
                <tr>
                    <td class="kv-label">员工编号</td>
                    <td class="kv-content">${user.taxerId}</td>
                    <td class="kv-label">用户权限</td>
                    <td class="kv-content">${user.permissionId}<br>(1. 超级管理员。 2. 普通管理员。3. 普通员工)</td>
                </tr>
                <tr>
                    <td class="kv-label">用户状态</td>
                    <td class="kv-content">${user.state}<br>(1 表示正常使用 0  表示禁用 -1 表示离职 -2 表示退休 -3 表示休假)</td>
                    <td class="kv-label">邮箱</td>
                    <td class="kv-content">${user.email}</td>
                </tr>       
                </tbody>
       </table>
       </div>
  </body>
  <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="static/js/calendar.js"></script>
    <script type="text/javascript">
    $(function(){
	
    })
  </script>
</html>