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
    <title>任务查询</title>

<link href="static/css/base.css" rel="stylesheet">
<link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
<link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
<!-- <link rel="stylesheet" href="static/css/providers1.css"> -->
</head> 
<body>
    <div class="container">
       <table id="dg" style="width:100%;height:529px"></table>
      <div id="tb" style="padding:0 30px;">
	      <form>
	        <div class="conditions">
	            <span class="con-span">纳税人识别号: </span><input class="easyui-textbox" type="text" name="payerCode" style="width:166px;height:35px;line-height:35px;">
	            <span class="con-span">纳税人名称: </span><input class="easyui-textbox" type="text" name="payerName" style="width:166px;height:35px;line-height:35px;">
	            <a id="searchBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
	            <a id="setBtn" href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
	            <a href="javascript:void(0);" class="easyui-linkbutton more" iconCls="icon-more">更多</a>
	        </div>
	        <div class="conditions hide">
	            <span class="con-span">税务机关: </span><select id="organ" name="organ" class="easyui-combobox" style="width:166px;height:35px;line-height:35px;"></select>
	            <span class="con-span">任务开始时间: </span><input class="easyui-datebox" type="text" name="startTime" style="width:166px;height:35px;line-height:35px;">
	            <span class="con-span">任务结束时间: </span><input class="easyui-datebox" type="text" name="endTime" style="width:166px;height:35px;line-height:35px;">
	            <span class="con-span">行业: </span><select id="industry" name="industry" class="easyui-combobox" style="width:166px;height:35px;line-height:35px;"><option value="">请选择行业</option></select>
	        </div>
	        </form>
      </div>
    </div>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        $(function(){
        	$('#dg').datagrid({
    	        url:'manage/task/list',
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
                collapsible:true,
                toolbar:'#tb',
                pageSize:10,
    	        columns:[[
    	    		{field:'taskName',title:'任务名称',align:'center'},
    	    		{field:'payerCode',title:'纳税人识别号',align:'center'},//表连接tax_payer
    	    		{field:'payerName',title:'纳税人名称',align:'center'},
    	    		{field:'industryName',title:'行业',align:'center'},//表连接industry
    	    		{field:'bizScope',title:'经营范围',align:'center'},
    	    		{field:'organName',title:'税务机关',align:'center'},
    	    		{field:'approverId',title:'批准人',align:'center'},
    	    		{field:'executeId',title:'执行人员',align:'center'},
    	    		{field:'executeTime',title:'执行时间',align:'center'},
    	    		{field:'recordUserId',title:'录入人员',align:'center'},
    	    		{field:'recordDate',title:'录入日期',align:'center'},
    	    		{field:'overDays',title:'录入超时',align:'center'},//sql计算
    	    		{field:'operation',title:'操  &nbsp; 作',align:'center',
    					formatter: function(value,row,index){
    						return "<a href='javascript:' onclick='edit("+row.id+")'>修改</a>|<a href='javascript:' onclick='deleteTesk("+row.id+")'>删除</a>"
    					}
    				}
    	        ]]
    	    });
        })
        
        $(function(){
            //$('#dg').datagrid({data:getData()}).datagrid('clientPaging');
            //获取下拉框数据
            $('#organ').combobox({
                url:'getOrgans.do',
                valueField:'id',
                textField:'organName'
            });
            $('#industry').combobox({
                url:'getIndustry.do',
                valueField:'id',
                textField:'industryName'
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

        //为重置按钮添加事件处理函数
	    	//重置并刷新显示
			$('#setBtn').click(function(){
				$('form').form('reset');
	        	$('#dg').datagrid('load',{});//刷新
			})
        //为查询按钮添加事件处理函数
        $('#searchBtn').click(function(){
        	var payerCode = $('input[name="payerCode"]').val();
        	var payerName = $('input[name="payerName"]').val();
        	var organ = $('input[name="organ"]').val();
        	var startTime = $('input[name="startTime"]').val();
        	var endTime = $('input[name="endTime"]').val();
        	var industry = $('input[name="industry"]').val();
        	
        	$('#dg').datagrid('load', {
        		payerCode : payerCode,
        		payerName : payerName,
        		taxOrganId : organ,
        		startTime : startTime,
        		endTime : endTime,
        		industry : industry
        	});
        })
        //定义回车键执行查询
  		$(function(){
  			$("input").keydown(function() {
	            if (event.keyCode == "13") {//keyCode=13是回车键
	            	$('#searchBtn').click();
	            }
       		});
  		})
    	//修改操作
    	var edit = function(id){
    		openTopWindow({
                width : 1000,
                height : 600,
                title : "修改任务信息",
                url : "manage/task/edit.do?id="+id,
                
            })
    	}
	    	//提交删除请求
	    	var deleteOperation = function(id){
	    		$.post("manage/task/delete.do",{"id":id},function(result){
	    			if (result){
	    				parent.$.messager.alert('提示','删除失败');
	    			} else {
	    				$.messager.alert('提示','删除成功','',function(){
	    					top.$('.easyui-tabs1').tabs('getSelected').find('iframe')[0].contentWindow.$('#dg').datagrid('load');
	    				});
	    			}
	    		})
	    	}
	    	//删除操作
	    	var deleteTesk = function(id){
	    		parent.$.messager.confirm('提示','确认删除？',function(r){
	    		    if (r){deleteOperation(id);}
	   		    })
	    	}

		

        $(".more").click(function(){
            $(this).closest(".conditions").siblings().toggleClass("hide");
        });
    </script>
</body> 
</html>
