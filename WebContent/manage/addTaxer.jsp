<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                        <td class="kv-label">税务人员工号</td>
                        <td class="kv-content"><input type="text" name="taxerCode" placeholder="税务人员工号"></td>
                        <td class="kv-label">税务人员名称</td>
                        <td class="kv-content"><input type="text" name="taxerName" placeholder="税务人员名称"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">税务人员电话</td>
                        <td class="kv-content"><input type="text" name="mobile" placeholder="税务人员电话"></td>
                        <td class="kv-label">税务人员性别</td>
                        <td class="kv-content">
                            <input type="text" name="sex" placeholder="生产经营范围">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">税务人员地址</td>
                        <td class="kv-content"><input type="text" name="address" placeholder="税务人员地址"></td>
                    	<td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="organId">
                                <option value="-1" id="selectOrgan">请选择所属税务机关</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">有效标志</td>
                        <td class="kv-content">
                            <select name="state">
                                <option value="0" id="selectOrgan" >无效</option>                         
                                <option value="1" id="selectOrgan" >有效</option>                         
                            </select>
                        </td>
                        <td class="kv-label">系统权限</td>
                        <td class="kv-content">
                            <select name="state">
                                <option value="0" id="selectOrgan" >普通</option>                         
                                <option value="1" id="selectOrgan" >管理员</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <select name="recordUserId">
                                <option value="-1">请选择办税专员</option>
                                <option value="1">张三</option>
                                <option value="2">李四</option>
                            </select>
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content">2017-01-20 </td>
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
	
	
	
	})
</script>
</html>


