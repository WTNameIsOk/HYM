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
                <div class="column"><span class="current">修改税务人员信息</span></div>
                <form>
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <td class="kv-label">工号
                    	<input type="hidden" name="id" value="${taxer.id }"/>
                        </td>
                        <td class="kv-content"><input type="text" name="taxerCode" placeholder="税务人员工号" readonly="readonly" value="${taxer.taxerCode }"></td>
                        <td class="kv-label">姓名</td>
                        <td class="kv-content"><input type="text" name="taxerName" placeholder="税务人员名称" readonly="readonly" value="${taxer.taxerName }"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">电话</td>
                        <td class="kv-content"><input type="text" name="mobile" placeholder="税务人员电话" value="${taxer.mobile }"></td>
                        <td class="kv-label">性别</td>
                        <td class="kv-content">
                            <input type="text" name="sex" placeholder="税务人员性别" readonly="readonly" value="${taxer.sex }">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">邮箱</td>
                        <td class="kv-content"><input type="text" name="email" placeholder="邮箱" value="${taxer.email }"></td>
                        <td class="kv-label">地址</td>
                        <td class="kv-content"><input type="text" name="address" placeholder="税务人员地址" value="${taxer.address }"></td>
                    </tr>
                    <tr>
                    	<td class="kv-label">出生日期</td>
                        <td class="kv-content"><input type="text" name="birthday" placeholder="出生日期,格式:1900-01-01" value="${taxer.birthday }"></td>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select id="selectOrgan" class="easyui-validatebox" data-options="validType:'selected'" name="organId">
                                <option value="-1" id="selectOrgan">请选择所属税务机关</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                    	<td class="kv-label">上级领导</td>
                        <td class="kv-content">
                            <select id="selectMgr" class="easyui-validatebox" data-options="validType:'selected'" name="mgr">
                                <option value="-1" >请选择上级领导</option>                         
                            </select>
                        </td>
                        <td class="kv-label">有效标志</td>
                        <td class="kv-content">
                            <select name="state">
                                <option value="0" ${0 eq taxer.admin ? "selected='selected'" : '' }>无效</option>                         
                                <option value="1" ${1 eq taxer.admin ? "selected='selected'" : '' }>有效</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">系统权限</td>
                        <td class="kv-content">
                            <select name="admin">
                                <option value="0" ${0 eq taxer.admin ? "selected='selected'" : '' }>普通</option>                         
                                <option value="1" ${1 eq taxer.admin ? "selected='selected'" : '' }>管理员</option>                         
                            </select>
                        </td>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <select id="recordUserId" name="recordUserId">
                                <option value="-1" disabled="disabled">请选择办税专员</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content">
							<input type="text" name="recordDate" placeholder="录入日期,格式:1900-01-01" value="${taxer.recordDate }">
						</td>
                    </tr>
                    </tbody>
                </table>
                </form>
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
		//请求上级领导的数据
		$.get("getTaxers.do",{},function(data){
			var mgr = $("#selectMgr")
			$.each(data,function(index, val){
				mgr.append("<option value='"+val.id+"' "+(val.id == ${taxer.mgr } ? "selected='selected'" : '' )+">"+val.taxerName+"</option>");
			})
		},"json")
		//请求单位的数据
		$.get("getOrgans.do",{},function(data){
			var organ = $("#selectOrgan")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.id+"' "+(val.id == ${ taxer.organId } ? "selected='selected'" : '' )+">"+val.organName+"</option>")
			})
		},"json")
		//请求录入人员的数据
		$.get("getUsers.do",{},function(data){
			var recordUserId = $("#recordUserId")
			$.each(data,function(index, val){
				recordUser.append("<option value='"+val.id+"' "+(val.id == ${taxer.recordUserId } ? "selected='selected'" : '' )+">"+val.username+"</option>");
			})
		},"json")
	
		/* $("select").click(function(){
			$("option:contains(请选择)").attr('disable','disable')
		}) */

		//ajax提交数据
		var execute = function() {
			if ($('form').form("validate")) {
				$.post("manage/taxer/edit.do",$("form").serialize(),function(result){
					if (result) {
						parent.$.messager.alert('提示','修改失败');
					} else {
						parent.$.messager.alert('提示','修改成功','info',function(){
							//刷新数据
							top.$('.easyui-tabs1').tabs('getSelected').find('iframe')[0].contentWindow.$('#dg').datagrid('load');
						});
	    				parent.$("#topWindow").window('close');
					}
				})
			}
		}

    	//定义回车键执行
  		$("input").keydown(function() {
            if (event.keyCode == "13") {//keyCode=13是回车键
            	execute();
            }
       	});

    	//定义保存按钮执行
  		$('a:contains(保存)').click(function(){
    		execute();
    	})
    	
    	//为重置按钮添加事件处理函数
		$('.reset-btn').click(function(){
			$('form').form('reset');
		})
	
	})
	//自定义easyUI表单验证
	$.extend($.fn.validatebox.defaults.rules,{  
	  selected : {
	    	 validator : function(value) {
			       return value != -1;
		      	},
		      	message : "改选项为必选项."
	     }
  	});
</script>
</html>


