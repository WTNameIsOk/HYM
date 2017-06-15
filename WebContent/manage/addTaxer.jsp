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
            <div title="税务人员信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">添加税务人员</span></div>
                <form>
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <td class="kv-label">工号</td>
                        <td class="kv-content"><input class="easyui-validatebox" data-options="required:true,validType:'num'" type="text" name="taxerCode" placeholder="税务人员工号"></td>
                        <td class="kv-label">姓名</td>
                        <td class="kv-content"><input class="easyui-validatebox" data-options="required:true" type="text" name="taxerName" placeholder="税务人员名称"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">电话</td>
                        <td class="kv-content"><input class="easyui-validatebox" data-options="required:true,validType:'telephone'" type="text" name="mobile" placeholder="税务人员电话"></td>
                        <td class="kv-label">性别</td>
                        <td class="kv-content">
                            <select name="sex">
                                <option value="男">男</option>                         
                                <option value="女">女</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">邮箱</td>
                        <td class="kv-content"><input class="easyui-validatebox" data-options="required:true,validType:'email'" type="text" name="email" placeholder="税务人员邮箱" ></td>
                        <td class="kv-label">地址</td>
                        <td class="kv-content"><input type="text" name="address" placeholder="税务人员地址" ></td>
                    </tr>
                    <tr>
                    	<td class="kv-label">出生日期</td>
                        <td class="kv-content"><input type="text" class="easyui-datebox" name="birthday" placeholder="出生日期"></td>
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
                                <option value="0" >无效</option>                         
                                <option value="1" >有效</option>                         
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">系统权限</td>
                        <td class="kv-content">
                            <select name="admin">
                                <option value="0" >普通</option>                         
                                <option value="1" >管理员</option>                         
                            </select>
                        </td>
                        <td class="kv-label">录入人员</td>
                        <td class="kv-content">
                            <select id="recordUser" name="recordUserId">
                                <option>请选择办税专员</option>
                            </select>
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
		//ajax请求获取外键值
		$.get("getTaxerServlet.do",{},function(data){
			var mgr = $("#selectMgr")
			var recordUser = $("#recordUser")
			$.each(data,function(index, val){
				mgr.append("<option value='"+val.id+"'>"+val.taxerName+"</option>");
				recordUser.append("<option value='"+val.id+"'>"+val.taxerName+"</option>");
			})
		},"json")
		$.get("getOrganServlet.do",{},function(data){
			var organ = $("#selectOrgan")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.id+"'>"+val.organName+"</option>")
			})
		},"json")
	
		//ajax提交数据
		var execute = function() {
			if ($('form').form("validate")) {
				$.post("taxer.do",$("form").serialize(),function(result){
					if (result) {
						parent.$.messager.alert('提示','添加失败');
					} else {
						parent.$.messager.alert('提示','添加成功','info',function(){
							top.frames[3].$('#dg').datagrid('load');//刷新数据
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
    	
    	//重置按钮
	
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


