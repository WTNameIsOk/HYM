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
       <table id="dg" style="width:100%;"  data-options="
                rownumbers:true,
                singleSelect:false,
                autoRowHeight:false,
                pagination:true,
                fitColumns:true,
                striped:true,
                checkOnSelect:false,
                selectOnCheck:false,
                toolbar:'#tb',
                pageSize:10">
        </table>
      <div id="tb" style="padding:0 30px;">
        纳税人识别号: <input class="easyui-textbox" type="text" id="payerCode" style="width:166px;height:35px;line-height:35px;"/>
        纳税人名称: <input class="easyui-textbox" type="text" id="payerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
        <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加纳税人</a>
      </div>
    </div>
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
    </script>
    <script type="text/javascript">
		  //easyUI数据网格
			$('#dg').datagrid({
		        url:'manage/taxPayer/list',
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
		    		{field:'legalPerson',title:'法人代表',align:'center'},
		    		{field:'legalIdCard',title:'法人身份证号码',align:'center'},
		    		{field:'finaceName',title:'主管财务',align:'center'},
		    		{field:'finaceIdCard',title:'财务身份证号码',align:'center'},
		    		{field:'taxerName',title:'办税人员',align:'center'},
		    		{field:'recordDate',title:'录入日期',align:'center'},
		    		{field:'operation',title:'操 &nbsp; &nbsp; &nbsp; &nbsp; 作',align:'center',
						formatter: function(value,row,index){
							return "<a href='javascript:' onclick='edit("+row.id+")'>修改</a>"+
							"|<a href='javascript:' onclick='deleteTesk("+row.id+")'>删除</a>"+
							"|<a href='javascript:' onclick='addTesk("+row.id+")'>新增任务</a>";
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
	                url : "manage/taxPayer/edit.do?id="+id,
	                
	            })
	    	}
	    	//提交删除请求
	    	var deleteOperation = function(id){
	    		$.post("manage/taxPayer/delete.do",{"id":id},function(result){
	    			if (result){
	    				parent.$.messager.alert('提示','删除失败');
	    			} else {
	    				$.messager.alert('提示','删除成功','',function(){
	    					//top.frames[3].$('#dg').datagrid('load');
	    					top.$('.easyui-tabs1').tabs('getSelected').find('iframe')[0].contentWindow.$('#dg').datagrid('load');
	    					//$('.easyui-tabs1').tabs('getSelected').find('iframe').contents()当前窗口jQuery对象
	    				});
	    			}
	    		})
	    	}
	    	//删除操作
	    	var deleteTesk = function(id){
	    		parent.$.messager.confirm('提示','确认删除？',function(r){
	    		    if (r){
	    		    	//当前数据是否有其他数据连接
	    		    	$.get("manage/taxer/delete.do",{"id":id},function(result){
			    			if (result > 0){
			    				parent.$.messager.confirm('警告','当前数据被占用，删除该数据则会导致其他数据(共'+result+'条)一并删除。<hr/>请确认是否执行删除操作？',function(re){
			    					if (re) {
					    				deleteOperation(id);
			    					}
			    				})
			    			} else {
			    				deleteOperation(id);
			    			}
			    		})
	    			}
	   		    })
	    	}
    </script>
    <script type="text/javascript">
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
	  		$(function(){//页面加载之后才能定义
	  			$("input").keydown(function() {
		            if (event.keyCode == "13") {//keyCode=13是回车键
		            	$('#searchBtn').click();
		            }
	       		});
	  		})
        //为添加纳税人添加事件处理函数
       $(function(){
           $("#addBtn").on("click",function(e){
              openTopWindow({
                  width : 750,
                  height : 600,
                  title : "新增纳税人",
                  url : "manage/taxPayer/addTaxpayer.jsp"
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

       });
    </script>
</body> 
</html>
