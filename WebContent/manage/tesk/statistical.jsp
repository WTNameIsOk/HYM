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
    <title>未调查企业统计</title>

    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="static/css/taxpayer.css">
</head> 
<body>
    <div class="container">
       <table id="dg" style="width:100%;"></table>
      <div id="tb" style="padding:0 30px;">
        纳税人识别号: <input class="easyui-textbox" type="text" id="payerCode" name="payerCode" style="width:166px;height:35px;line-height:35px;"/>
        纳税人名称: <input class="easyui-textbox" type="text" id="payerName" name="payerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
      </div>
    </div>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
  	//easyUI数据网格
	$('#dg').datagrid({
        url:'manage/statisticl',
        method: 'get',
        loadMsg:"数据加载中...",
        rownumbers:true,
        singleSelect:false,
        autoRowHeight:false,
        pagination:true,
        fitColumns:true,
        striped:true,
        checkOnSelect:false,
        selectOnCheck:false,
        toolbar:'#tb',
        pageSize:10,
        columns:[[
    		{field:'payerCode',title:'纳税人识别号',align:'center'},
    		{field:'payerName',title:'纳税人名称',align:'center'},
    		{field:'organName',title:'所属税务机关',align:'center'},//表联接
    		{field:'industryName',title:'所属行业',align:'center'},//表联接
    		{field:'bizScope',title:'经营范围',align:'center'},
    		{field:'legalPerson',title:'法人代表',align:'center'},
    		{field:'legalIdCard',title:'法人身份证号码',align:'center'},
    		{field:'finaceName',title:'主管财务',align:'center'},
    		{field:'finaceIdCard',title:'财务身份证号码',align:'center'},
    		{field:'taxerName',title:'办税人员',align:'center'},
    		{field:'recordDate',title:'录入日期',align:'center'},
        ]]
    });
    
       $(function(){
	    	//为搜索按钮添加事件处理函数
	        $('#searchBtn').click(function(){
	        	var payerCode = $('#payerCode').val();
	        	var payerName = $('#payerName').val();
	        	$('#dg').datagrid('load', {
	        		payerCode: payerCode,
	        		payerName: payerName
	        	});
	        })
	        //为重置按钮添加事件处理函数
		    	//重置并刷新显示
				$('#setBtn').click(function(){
					$('#payerCode').textbox('setValue','');
					$('#payerName').textbox('setValue','');
		        	$('#dg').datagrid('load',{});//刷新
		        	//$('#dg').datagrid('reload');
				})
	        //定义回车键执行查询
	  			$("input").keydown(function() {
		            if (event.keyCode == "13") {//keyCode=13是回车键
		            	$('#searchBtn').click();
		            }
	       		});
		})
    </script>
</body>
</html>
