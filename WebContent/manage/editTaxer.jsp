<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加纳税人</title>
    <link rel="stylesheet" type="text/css" href="static/css/base.css" >
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" type="text/css" href="static/css/edit.css">
</head>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript"></script>
<body>
    <div class="container">
        <div class="content">
            <div title="税务人员信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">添加税务人员</span></div>
                <table class="kv-table">
                    <tbody>
                    <tr>
                    	<input type="hidden" value="${taxer.id }"/>
                        <td class="kv-label">税务人员工号</td>
                        <td class="kv-content"><input type="text" name="taxerCode" placeholder="税务人员工号" readonly="readonly" value="${taxer.taxerCode }"></td>
                        <td class="kv-label">税务人员名称</td>
                        <td class="kv-content"><input type="text" name="taxerName" placeholder="税务人员名称" readonly="readonly" value="${taxer.taxerName }"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">税务人员电话</td>
                        <td class="kv-content"><input type="text" name="mobile" placeholder="税务人员电话" value="${taxer.mobile }"></td>
                        <td class="kv-label">税务人员性别</td>
                        <td class="kv-content">
                            <input type="text" name="sex" placeholder="税务人员性别" readonly="readonly" value="${taxer.sex }">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">邮箱</td>
                        <td class="kv-content"><input type="text" name="email" placeholder="邮箱" value="${taxer.email }"></td>
                        <td class="kv-label">税务人员地址</td>
                        <td class="kv-content"><input type="text" name="address" placeholder="税务人员地址" value="${taxer.address }"></td>
                    </tr>
                    <tr>
                    	<td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="organId">
                                <option value="-1" id="selectOrgan">请选择所属税务机关</option>                         
	                            <c:forEach var="organ" items="${organs }">
	                                <option value="${organ.id }" ${organ.id eq taxer.organId ? "selected='selected'" : '' }>${organ.organName }</option>                         
	                            </c:forEach>
                            </select>
                        </td>
                    	<td class="kv-label">上级领导</td>
                        <td class="kv-content">
                            <select name="mgr">
                                <option value="-1" id="selectOrgan" >请选择上级领导</option>                         
	                            <c:forEach var="mgr" items="${taxers }">
	                                <option value="${mgr.id }" ${mgr.id eq taxer.mgr ? "selected='selected'" : '' }>${mgr.taxerName }</option>                         
	                            </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">有效标志</td>
                        <td class="kv-content">
                            <select name="state">
                                <option value="0" id="selectOrgan" ${0 eq taxer.admin ? "selected='selected'" : '' }>无效</option>                         
                                <option value="1" id="selectOrgan" ${1 eq taxer.admin ? "selected='selected'" : '' }>有效</option>                         
                            </select>
                        </td>
                        <td class="kv-label">系统权限</td>
                        <td class="kv-content">
                            <select name="state">
                                <option value="0" id="selectOrgan" ${0 eq taxer.admin ? "selected='selected'" : '' }>普通</option>                         
                                <option value="1" id="selectOrgan" ${1 eq taxer.admin ? "selected='selected'" : '' }>管理员</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <select name="recordUserId">
                                <option value="-1" disabled="disabled">请选择办税专员</option>                         
	                            <c:forEach var="recordUser" items="${taxers }">
	                                <option value="${recordUser.id }" ${recordUser.id eq taxer.recordUserId ? "selected='selected'" : '' }>${recordUser.taxerName }</option>                         
	                            </c:forEach>
                            </select>
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content">
							<input type="date" name="recordDate" placeholder="系统管理员标志" value="${taxer.recordDate }">
						</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="btn-selection">
                <a href="javascript:void(0);" class="easyui-linkbutton save-btn" data-options="selected:true">保存</a>
                <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" data-options="selected:true">重置</a>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	$(function(){
	/**
		ajax请求获取外键值
		$.post("getIndustry.do",{},function(data){
			var industry = $("#selectIndustry")
			$.each(data,function(index, val){
				industry.append("<option value='"+val.industryId+"'>"+val.industryName+"</option>")
			})
		},"json")
		$.post("getOrganServlet.do",{},function(data){
			var organ = $("#selectOrgan")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.organId+"'>"+val.organName+"</option>")
			})
		},"json")
	**/
		/* $("select").click(function(){
			$("option:contains(请选择)").attr('disable','disable')
		}) */
	
	
	})
</script>
</html>


