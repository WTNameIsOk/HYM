<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
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
                    <td class="kv-label">密码</td>
                    <td class="kv-content"><input type="password" id="oldPassword" name="oldPassword"placeholder="密码"></td>
                </tr>
                <tr>
                    <td class="kv-label">新密码</td>
                    <td class="kv-content"><input type="password" id="newPassword" name="newPassword" placeholder="新密码"></td>
                </tr> 
                <tr>                     
                    <td class="kv-label">确认密码</td>
        			<td class="kv-content">
        				<input type="password" id="validatePassword" name="validatePassword" placeholder="确认密码">
       				</td>
                </tr>       
                </tbody>
                <tfoot>
                <tr>
                	<td id="error" colspan="2"  class="alert alert-error" style="padding-left: 20px;">
					        <i class="iconfont">&#xe62e;</i>
							<span id="info"></span>
					</td>
				</tr>
				</tfoot>
       </table>
       </div>
        <div class="btn-selection">
            <a href="javascript:void(0);" class="easyui-linkbutton save-btn" id="modify_pwd" data-options="selected:true">保存</a>
            <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" data-options="selected:true">重置</a>
        </div>
       </div>
       </div>
  </body>
  <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
  <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="static/js/calendar.js"></script>
    <script type="text/javascript">
    $(function(){
    	$('#error').hide();
    	//定义重置按钮
    	var reset = function(){
    		$("input").val('');
    	}
    	$('a:contains(重置)').click(function(){
    		reset();
	    	$('#error').hide();
    	})
    	//定义回车键执行
  		$("input").keydown(function() {
            if (event.keyCode == "13") {//keyCode=13是回车键
            	execute();
            }
       	});
    	//表单验证
    	$('input').focus(function(){
	    	$('#error').hide();
    	})
    	//提交ajax请求
    	var execute = function(){
    		var oldPassword = $('#oldPassword').val();
    		var newPassword = $('#newPassword').val();
    		var rePassword = $('#validatePassword').val();
    		if (oldPassword == ''){
    			$('#error').show();
    			$('#info').text('请输入原密码').css("color","red");
    			return;
    		}else if (newPassword == ''){
    			$('#error').show();
    			$('#info').text('请输入新密码').css("color","red");
    			return;
    		}else if (newPassword != rePassword) {
    	    	$('#error').show();
    			$('#info').text('两次密码输入不同').css("color","red");
    			return;
    		} else {
    	    	$('#error').hide();
    		}
    		$.messager.confirm('提示','确认修改？',function(r){
    		    if (r){
    		    	$.get("modifyPassword",{"oldPassword":oldPassword,"newPassword":newPassword},function(result){
	        			if (result){
	            	    	$('#error').show();
	            			$('#info').text('密码错误').css("color","red");
	        			} else {
	        				parent.$.messager.alert('提示','密码修改成功\r\n下次登录请使用新密码','warning');
	        	    		reset();
	        				parent.$("#mdfPwd").window('close');
	        			}
	        		})
    		    }
    		});
    	}
    	
    	$('a:contains(保存)').click(function(){
    		execute();
    	})
    })
    </script>
</html>