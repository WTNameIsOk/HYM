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
    <title>纳税人管理</title>

    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="static/css/taxpayer.css">
</head> 
<body>
    <div class="container">
       <table id="dg" style="width:100%;"></table>
      <div id="tb" style="padding:0 30px;">
        办税专员姓名: <input class="easyui-textbox" id="taxerName" type="text" name="payerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
        <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加办税专员</a>
      </div>
    </div>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
    	//easyUI数据网格
    	$('#dg').datagrid({
	        url:'listTaxer',
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
	    		{field:'taxerCode',title:'税务人员工号'},
	    		{field:'taxerName',title:'税务人员名称'},
	    		{field:'mobile',title:'税务人员电话'},
	    		{field:'address',title:'税务人员地址'},
	    		{field:'sex',title:'税务人员性别'},
	    		{field:'birthday',title:'出生日期'},
	    		{field:'email',title:'电子邮件'},
	    		{field:'organName',title:'所属税务机关'},
	    		{field:'state',title:'有效标志'},
	    		{field:'mgr',title:'上级领导'},
	    		{field:'admin',title:'系统管理员标志'},
	    		{field:'recordDate',title:'录入日期'},
	    		{field:'recordUserId',title:'录入人员'},
	    		{field:'operation',title:'操作',
					formatter: function(value,row,index){
						return "<a href='javascript:' onclick='edit("+row.id+")'>修改</a>|<a href='javascript:' onclick='deleteTaxer("+row.id+")'>删除</a>"
					}
				}
	        ]]
	    });
    	//修改操作
    	var edit = function(id){
    		openTopWindow({
                width : 750,
                height : 600,
                title : "修改税务人员信息",
                url : "editTaxer?id="+id,
                
            })
    	}
    	//删除操作
    	var deleteTaxer = function(id){
    		$.messager.confirm('提示','确认删除？',function(r){
    		    if (r){
		    		$.get("taxer.do",{"id":id},function(result){
		    			if (result){
		    				$.messager.alert('提示','删除失败');
		    			} else {
		    				parent.$.messager.alert('提示','删除成功');
		    				$('#setBtn').click();
		    			}
		    		})
    			}
   		    })
    	}
        //为查询按钮添加事件处理函数
        $('#searchBtn').click(function(){
        	var taxerName = $('#taxerName').val();
        	$('#dg').datagrid('load', {
        		name: taxerName
        	});
        })
        //为重置按钮添加事件处理函数
	    	//重置并刷新显示
			$('#setBtn').click(function(){
				$('#taxerName').textbox('setValue','');
				//$('.textbox-text').val('');无效
	        	$('#dg').datagrid('load', {});//刷新
			})
        //为添加税务专员添加事件处理函数
       		$(function(){
	           $("#addBtn").on("click",function(e){
	              openTopWindow({
	                  width : 750,
	                  height : 600,
	                  title : "新增税务人员",
	                  url : "manage/addTaxer.jsp"
	              });
	           });
       });
        /**
         *打开在父窗口中打开window
         */
        function openTopWindow(options){
            options = !options ? {} :options;
            options.width = !options.width ? 500 : options.width;
            options.height = !options.height ? 400 : options.height;
            options.url = !options.url ? "404.html" : options.url;
            options.title = !options.title ? "" : options.title;
            options.operation = !options.operation ? "" : options.operation;

            parent.$("#topWindow").window({
                title : options.title,
                width: options.width,
                height: options.height,
                content : "<iframe scrolling='no' frameborder='0' border='0' height='100%' width='100%' src='"+options.url+"'></iframe>",
                modal:true,
                resizable:false,
                collapsible:false
            });
        }

    </script>
</body> 
</html>
