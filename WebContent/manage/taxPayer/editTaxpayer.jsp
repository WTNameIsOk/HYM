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
    <title>修改纳税人</title>
    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link href="static/css/edit.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="content">
            <div title="纳税人信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">修改纳税人信息</span></div>
                <form>
	                <table class="kv-table">
	                    <tbody>
	                    <tr>
	                        <td class="kv-label">纳税人识别号<input type="hidden" name="id" value="${payer.id }"/></td>
	                        <td class="kv-content"><input type="text" name="payerCode" placeholder="纳税人识别号" value="${payer.payerCode }"></td>
	                        <td class="kv-label">纳税人名称</td>
	                        <td class="kv-content"><input type="text" name="payerName" placeholder="纳税人名称" value="${payer.payerName }"></td>
	                    </tr>
	                    <tr>
	                        <td class="kv-label">生产经营地址</td>
	                        <td class="kv-content"><input type="text" name="bizAddress" placeholder="生产经营地址" value="${payer.bizAddress }"></td>
	                        <td class="kv-label">生成经营地电话</td>
	                        <td class="kv-content"><input type="text" name="bizAddressPhone" placeholder="生产经营地电话" value="${payer.bizAddressPhone }"></td>
	                    </tr>
	                    <tr>
	                        <td class="kv-label">所属税务机关</td>
	                        <td class="kv-content">
	                            <select id="selectOrgan" name="taxOrganId">
	                                <option value="-1">请选择所属税务机关</option>
	                                <option value="1">北京市国税朝阳分局</option>
	                            </select>
	                        </td>
	                        <td class="kv-label">行业</td>
	                        <td class="kv-content">
	                            <select id="selectIndustry" name="industryId">
	                                <option value="-1">请选择纳税人行业</option>
	                                <option value="1">交通运输行业</option>
	                            </select>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="kv-label">生产经营范围</td>
	                        <td class="kv-content">
	                            <input type="text" name="bizScope" placeholder="生产经营范围" value="${payer.bizScope }">
	                        </td>
	                        <td class="kv-label">票种核定</td>
	                        <td class="kv-content">
	                            <select name="invoiceType">
	                                <option value="-1">请选择发票种类</option>
	                                <option value="增值税普通发票" ${payer.invoiceType == '增值税普通发票' ? "selected='selected'" : '' }>增值税普通发票</option>
	                                <option value="增值税专用发票" ${payer.invoiceType == '增值税专用发票' ? "selected='selected'" : '' }>增值税专用发票</option>
	                            </select>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="kv-label">法人代表人</td>
	                        <td class="kv-content">
	                            <input type="text" name="legalPerson" placeholder="法人姓名" value="${payer.legalPerson }">
	                        </td>
	                        <td class="kv-label">法人身份证号</td>
	                        <td class="kv-content">
	                            <input type="text" name="legalIdCard" placeholder="法人代表身份证号码" value="${payer.legalIdCard }">
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="kv-label">主管财务</td>
	                        <td class="kv-content">
	                            <input type="text" name="finaceName" placeholder="主管财务" value="${payer.finaceName }">
	                        </td>
	                        <td class="kv-label">财务身份证号</td>
	                        <td class="kv-content">
	                            <input type="text" name="finaceIdCard" placeholder="财务身份证号" value="${payer.finaceIdCard }">
	                        </td>
	                    </tr>
	                    <tr>
	                        <td class="kv-label">办税专员</td>
	                        <td class="kv-content">
	                            <input type="text" name="taxerName" value="${payer.taxerName}" placeholder="办税专员">
	                        </td>
	                        <td class="kv-label">录入日期</td>
	                        <td class="kv-content">
	                        	<input type="text" name="recordDate" value="${payer.recordDate }">
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
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript">
    /* $("input[name=executeTime]").datebox({
        formatter: easyUIFormater,
        parser: easyUIparser
    }); */
    $(function(){
		//ajax请求获取外键值
		//请求行业的数据
		$.get("getIndustry.do",{},function(data){
			var industry = $("#selectIndustry")
			$.each(data,function(index, val){
				industry.append("<option value='"+val.id+"' "+(val.id == ${payer.industryId } ? "selected='selected'" : '' )+">"+val.industryName+"</option>")
			})
		},"json")
		//请求税务单位的数据
		$.get("getOrgans.do",{},function(data){
			var organ = $("#selectOrgan")
			$.each(data,function(index, val){
				organ.append("<option value='"+val.id+"' "+(val.id == ${payer.taxOrganId } ? "selected='selected'" : '' )+">"+val.organName+"</option>")
			})
		},"json")
		/* //请求税务员的数据
		$.get("getTaxers.do",{},function(data){
			var mgr = $("#selectTaxer")
			$.each(data,function(index, val){
				mgr.append("<option value='"+val.taxerName+"' "+(val.taxerName == '${payer.taxerName }' ? "selected='selected'" : '' )+">"+val.taxerName+"</option>");
			})
		},"json") */

		//ajax提交数据
		var execute = function() {
			if ($('form').form("validate")) {
				$.post("manage/taxPayer/edit.do",$("form").serialize(),function(result){
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
</script>
</html>