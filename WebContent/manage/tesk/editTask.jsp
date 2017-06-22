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
    <title>任务录入</title>
    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link href="static/css/edit.css" rel="stylesheet">
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div class="container">
    <div class="content">
        <div title="纳税人信息" data-options="closable:false" class="basic-info">
        	<form>
	            <div class="column"><span class="current">纳税人基本信息</span></div>
				<table id="payer" class="kv-table">
	                <tbody>
	                <tr>
	                    <td class="kv-label"><input type="hidden" name="id" value="${payer.id }"/>纳税人识别号</td>
	                    <td class="kv-content">${payer.payerCode }<input type="hidden" name="payerId" value="${payer.payerId }"/></td>
	                    <td class="kv-label">纳税人名称</td>
	                    <td class="kv-content">${payer.payerName }</td>
	                    <td class="kv-label">生产经营地址</td>
	                    <td class="kv-content">${payer.bizAddress }</td>
	                </tr>
	                <tr>
	                    <td class="kv-label">所属税务机关</td>
	                    <td class="kv-content">${payer.organName }</td>
	                    <td class="kv-label">行业</td>
	                    <td class="kv-content">${payer.industryName }</td>
	                    <td class="kv-label">经营范围</td>
	                    <td class="kv-content">${payer.bizScope }</td>
	                </tr>
	                <tr>
	                    <td class="kv-label">票种核定</td>
	                    <td class="kv-content">${payer.invoiceType }</td>
	                    <td class="kv-label">法人代表人</td>
	                    <td class="kv-content">${payer.legalPerson }</td>
	                    <td class="kv-label">法人身份证号</td>
	                    <td class="kv-content">${payer.legalIdCard }</td>
	                </tr>
	                <tr>
	                    <td class="kv-label">主管财务</td>
	                    <td class="kv-content">${payer.finaceName }</td>
	                    <td class="kv-label">财务身份证号</td>
	                    <td class="kv-content">${payer.finaceIdCard }</td>
	                    <td class="kv-label">税收管理员</td>
	                    <td class="kv-content">${payer.taxerName }</td>
	                </tr>
	                <tr>
	                    <td class="kv-label">办税人员</td>
	                    <td class="kv-content">${payer.taxerName }</td>
	                    <td class="kv-label">录入日期</td>
	                    <td class="kv-content">${payer.recordDate }</td>
	                    <td class="kv-label">录入人员</td>
	                    <td class="kv-content">${payer.username }
	                </td>
	                </tr>
	                </tbody>
	            </table>
	            <div class="column"><span class="current">任务信息</span></div>
	            <table class="kv-table">
	                <tbody>
	                <tr>
	                    <td class="kv-label">任务名称</td>
	                    <td class="kv-content"><input type="text" value="${payer.taskName }" name="taskName" placeholder="请输入任务名称"></td>
	                    <td class="kv-label">下达部门</td>
	                    <td class="kv-content">
		                    <select class="easyui-validatebox" data-options="validType:'selected'" id="selectOrgan" name="taxOrganId" >
		                    	<option value="-1">请选择下达部门</option>
		                    </select>
	                    </td>
	                    <td class="kv-label">批准人</td>
	                    <td class="kv-content">
	                    	<select class="easyui-validatebox" data-options="validType:'selected'" id="mgr" name="approverId">
		                    	<option value="-1">请选择批准人</option>
		                    </select>
		                </td>
	                </tr>
	                <tr>
	                    <td class="kv-label">执行人</td>
	                    <td class="kv-content">
	                    	<select class="easyui-validatebox" data-options="validType:'selected'" id="taxer" name="executeId">
		                    	<option value="-1">请选择执行人</option>
		                    </select>
	                    </td>
	                    <td class="kv-label">执行时间</td>
	                    <td class="kv-content"><input type="text" value="${payer.executeTime }" name="executeTime"></td>
	                    <td class="kv-label">风险登记</td>
	                    <td class="kv-content">
	                        <select class="easyui-validatebox" data-options="validType:'selected'" name="riskLevel">
	                            <option>请选择</option>
	                            <option value="2" ${payer.riskLevel == '2' ? "selected='selected'" :'' }>高</option>
	                            <option value="1" ${payer.riskLevel == '1' ? "selected='selected'" :'' }>中</option>
	                            <option value="0" ${payer.riskLevel == '0' ? "selected='selected'" :'' }>低</option>
	                        </select>
	                    </td>
	                </tr>
	                <tr>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <select class="easyui-validatebox" data-options="validType:'selected'" id="recordUserId" name="recordUserId">
                                <option value="-1" disabled="disabled">请选择办税专员</option>                         
                            </select>
                        </td>
	                    <td class="kv-label">任务执行情况</td>
	                    <td class="kv-content">
	                        <textarea name="taskState" rows="3" style="width: 90%;">${payer.taskState}
	                        </textarea>
	                    </td>
	                    <td class="kv-label">调查结论和意见</td>
	                    <td class="kv-content" colspan="3">
	                        <textarea name="idea" rows="3" style="width: 90%;">${payer.idea}
	                        </textarea>
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
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript">
	$(function(){
		//ajax获取外键
		//请求税务单位的数据
		$.get("getOrgans.do",{},function(data){
			var organ = $("#selectOrgan")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.id+"' "+(val.id == ${payer.taxOrganId } ? "selected='selected'" : '' )+">"+val.organName+"</option>")
			})
		},"json")
		//请求税务员的数据
		$.get("getTaxers.do",{},function(data){
			var mgr = $("#mgr")
			var taxer = $("#taxer")
			$.each(data,function(index, val){
				mgr.append("<option value='"+val.id+"' "+(val.id == ${payer.approverId } ? "selected='selected'" : '' )+">"+val.taxerName+"</option>");
				taxer.append("<option value='"+val.id+"' "+(val.id == ${payer.executeId } ? "selected='selected'" : '' )+">"+val.taxerName+"</option>");
			})
		},"json")
		//请求录入人员的数据
		$.get("getUsers.do",{},function(data){
			var recordUserId = $("#recordUserId")
			$.each(data,function(index, val){
				recordUserId.append("<option value='"+val.id+"' "+(val.id == ${payer.recordUserId } ? "selected='selected'" : '' )+">"+val.username+"</option>");
			})
		},"json")
	})

	//ajax提交数据
	var execute = function() {
		if ($('form').form("validate")) {
			$.post("manage/task/edit.do",$("form").serialize(),function(result){
				if (result) {
					parent.$.messager.alert('提示','更新失败');
				} else {
					parent.$.messager.alert('提示','更新成功','info',function(){
						var tab = top.$('.easyui-tabs1').tabs('getSelected');
						var tabWindow = tab.find('iframe')[0].contentWindow;
						tabWindow.$('#dg').datagrid('load');
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


	
	//自定义easyUI表单验证
	$.extend($.fn.validatebox.defaults.rules,{  
	   // 验证中文
	  	CN : {
		    validator : function(value) {
		    return /^[\u0391-\uFFE5]+$/.test(value);},
		    message : "只能输入汉字"
	   	}, 
	   	// 验证工号
	   	num : {
		    validator : function(value) {
		    return /^[0-9]+$/.test(value)&&value.length>=4&&value.length<=6;},
		    message : "请输入4-6位数字"
	   	},
	 	// 验证手机号码+固定电话
	 	// 联系电话(手机/电话皆可)验证
	    telephone : {
	      	validator : function(value) {
		       var cmccMobile = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		       var tel = /^\d{3,4}?\d{7,8}$/;
		       return tel.test(value) || (value.length == 11 && cmccMobile.test(value));
	      	},
	      	message : "请正确填写您的联系电话."
	     },
	     selected : {
	    	 validator : function(value) {
			       return value != -1;
		      	},
		      	message : "改选项为必选项."
	     }
  	});


</script>
</html>
