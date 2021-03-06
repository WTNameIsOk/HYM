<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript"></script>
<body>
    <div class="container">
        <div class="content">
            <div title="纳税人信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">添加纳税人信息</span></div>
                <form>
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <td class="kv-label">纳税人识别号</td>
                        <td class="kv-content"><input type="text" title="识别号自动生成" readonly="readonly" name="payerCode" placeholder="纳税人识别号" value="100${taxPayerSize+1 }"></td>
                        <td class="kv-label">纳税人名称</td>
                        <td class="kv-content"><input type="text" class="easyui-validatebox" data-options="required:true" name="payerName" placeholder="纳税人名称"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营地址</td>
                        <td class="kv-content"><input type="text" class="easyui-validatebox" data-options="required:true" name="bizAddress" placeholder="生产经营地址"></td>
                        <td class="kv-label">经营地电话</td>
                        <td class="kv-content"><input type="text" class="easyui-validatebox" data-options="required:true,validType:'telephone'" name="bizAddressPhone" placeholder="生产经营地电话"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select class="easyui-validatebox" data-options="validType:'selected'" id="selectOrgan" name="taxOrganId">
                                <option value="-1">请选择所属税务机关</option>                         
                            </select>
                        </td>
                        <td class="kv-label">行业</td>
                        <td class="kv-content">
                            <select id="selectIndustry" name="industryId">
                                <option value="-1">请选择纳税人行业</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营范围</td>
                        <td class="kv-content">
                            <input type="text" name="bizScope" placeholder="生产经营范围">
                        </td>
                        <td class="kv-label">票种核定</td>
                        <td class="kv-content">
                            <select name="invoiceType">
                                <option value="-1">请选择发票种类</option>
                                <option value="增值税普通发票">增值税普通发票</option>
                                <option value="增值税专用发票">增值税专用发票</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">法人代表人</td>
                        <td class="kv-content">
                            <input type="text" name="legalPerson" placeholder="法人姓名">
                        </td>
                        <td class="kv-label">法人身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="legalIdCard" placeholder="法人代表身份证号码">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">主管财务</td>
                        <td class="kv-content">
                            <input type="text" name="finaceName" placeholder="主管财务">
                        </td>
                        <td class="kv-label">财务身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="finaceIdCard" placeholder="财务身份证号">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">办税专员</td>
                        <td class="kv-content">
                            <select class="easyui-validatebox" data-options="validType:'selected'" id="selectTaxerId" name="taxerName">
                                <option value="-1">请选择办税专员</option>
                            </select>
                        </td>
                        <td class="kv-label">办税专员身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="taxerIdCard" placeholder="财务身份证号">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <select class="easyui-validatebox" data-options="validType:'selected'" id="selectUserId" name="userId">
                                <option value="-1">请选择</option>
                            </select>
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content" title="当前日期"><input name="recordDate" value="<%=new SimpleDateFormat("y-MM-dd").format(new Date())%>"/></td>
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
		//ajax请求获取外键值
		//请求行业的数据
		$.get("getIndustry.do",{},function(data){
			var industry = $("#selectIndustry")
			$.each(data,function(index, val){
				industry.append("<option value='"+val.id+"'>"+val.industryName+"</option>")
			})
		},"json")
		//请求录入人员的数据
		$.get("getUsers.do",{},function(data){
			var recordUser = $("#selectUserId")
			$.each(data,function(index, val){
				recordUser.append("<option value='"+val.id+"' "+(val.id == ${user.id } ? "selected='selected'" : '' )+">"+val.username+"</option>");
			})
		},"json")
		//请求税务单位的数据
		$.get("getOrgans.do",{},function(data){
			var organ = $("#selectOrgan")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.id+"'>"+val.organName+"</option>")
			})
		},"json")
		//请求税务员的数据
		$.get("getTaxers.do",{},function(data){
			var mgr = $("#selectTaxerId")
			$.each(data,function(index, val){
				mgr.append("<option value='"+val.taxerName+"'>"+val.taxerName+"</option>");
			})
		},"json")
	
		//ajax提交数据
		var execute = function() {
			if ($('form').form("validate")) {
				$.post("manage/taxPayer/add.do",$("form").serialize(),function(result){
					if (result) {
						parent.$.messager.alert('提示','添加失败');
					} else {
						parent.$.messager.alert('提示','添加成功','info',function(){
							//top.frames[4].$('#dg').datagrid('load',{});//刷新数据
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


