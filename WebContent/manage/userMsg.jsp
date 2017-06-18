<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <div title="用户信息" data-options="closable:false" class="basic-info">
            <div class="column"><span class="current">修改密码</span></div>
            <table class="kv-table">
                <tbody>
                <tr>
                    <td class="kv-label">员工编号</td>
                    <td class="kv-content"><fmt:formatNumber value="${msg.taxerId}" pattern="000000"></fmt:formatNumber></td>
                    <td class="kv-label">用户权限</td>
                    <%-- <td class="kv-content">${msg.admin}</td>1.超级管理员。2.普通管理员。3.普通员工 --%>
                    <c:if test="${msg.permissionId == 1}">
                    <td class="kv-content">超级管理员</td>
                    </c:if>
                    <c:if test="${msg.permissionId == 2}">
                    <td class="kv-content">普通管理员</td>
                    </c:if>
                    <c:if test="${msg.permissionId == 3}">
                    <td class="kv-content">普通员工</td>
                    </c:if>
                    <td class="kv-label">用户状态</td>
                    <%-- <td class="kv-content">${user.state}<br>(1 表示正常使用 0  表示禁用 -1 表示离职 -2 表示退休 -3 表示休假)</td> --%>
                    <c:if test="${msg.status == 1}">
                    <td class="kv-content">正常</td>
                    </c:if>
                    <c:if test="${msg.status == 0}">
                    <td class="kv-content">禁用</td>
                    </c:if>
                    <c:if test="${msg.status == -1}">
                    <td class="kv-content">离职</td>
                    </c:if>
                    <c:if test="${msg.status == -2}">
                    <td class="kv-content">退休</td>
                    </c:if>
                    <c:if test="${msg.status == -3}">
                    <td class="kv-content">休假</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="kv-label">税务人员工号</td>
                    <td class="kv-content">${msg.taxerCode}</td>
                    <td class="kv-label">姓名</td>
                    <td class="kv-content">${msg.taxerName}</td>
                    <td class="kv-label">电话</td>
                    <td class="kv-content">${msg.mobile}</td>
                </tr>
                <tr>
                    <td class="kv-label">地区</td>
                    <td class="kv-content">${msg.address}</td>
                    <td class="kv-label">性别</td>
                    <td class="kv-content">${msg.sex}</td>
                    <td class="kv-label">出生日期</td>
                    <td class="kv-content">${msg.birthday}</td>
                </tr>
                <tr>
                    <td class="kv-label">邮箱</td>
                    <td class="kv-content">${msg.email}</td>
                    <td class="kv-label">税务机关</td>
                    <td class="kv-content">${msg.organName}</td>
                    <td class="kv-label">税务状态</td>
                    <%-- <td class="kv-content">${msg.state}</td>1:有效 0:无效 默认0 --%>
                    <c:if test="${msg.state == 1}">
                    <td class="kv-content">正常</td>
                    </c:if>
                    <c:if test="${msg.state == 0}">
                    <td class="kv-content">无效</td>
                    </c:if>
                </tr>
                <tr>
                    <td class="kv-label">上级主管</td>
                    <td class="kv-content">${not empty msg.mgrName ? msg.mgrName : "无"}</td>
                    <td class="kv-label">税务管理员标志</td>
                    <%-- <td class="kv-content">${msg.admin}</td>1代表是 0代表不是 默认为0 --%>
                    <c:if test="${msg.admin == 1}">
                    <td class="kv-content">管理员</td>
                    </c:if>
                    <c:if test="${msg.admin == 0}">
                    <td class="kv-content">普通</td>
                    </c:if>
                </tr>       
                </tbody>
       </table>
       </div>
  	</div>
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