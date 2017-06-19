<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"> 
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>河南省金税三期税源信息管理系统</title>
    <link rel="stylesheet" href="static/css/base.css">
    <link rel="stylesheet" href="static/css/platform.css">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
</head>
<body>
    <div class="container">
        <div id="pf-hd">
            <div class="pf-logo">
               <div class="system-name">税源信息管理系统</div>
            </div>
            <!--顶部用户信息-->
            <div class="pf-user">
                <!--用户头像-->
                <div class="pf-user-photo">
                    <img src="static/images/main/user.png" alt="">
                </div>
                <!--用户名-->
                <h4 class="pf-user-name ellipsis">${user.username }</h4>
                <i class="iconfont xiala">&#xe607;</i>
                <!-- 头部用户信息 下拉菜单 -->
                <div class="pf-user-panel">
                    <ul class="pf-user-opt">
                        <li class="userMsg">
                            <a href="javascript:void(0);">
                                <i class="iconfont">&#xe60d;</i>
                                <span class="pf-opt-name">用户信息</span>
                            </a>
                        </li>
                        <li class="pf-modify-pwd mdfPwd">
                            <a href="javascript:void(0)">
                                <i class="iconfont">&#xe634;</i>
                                <span class="pf-opt-name">修改密码</span>
                            </a>
                        </li>
                        <li class="pf-logout exit">
                            <a href="javascript:void(0);">
                                <i class="iconfont">&#xe60e;</i>
                                <span class="pf-opt-name">退出</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div><!-- 头部用户信息结束 -->

        <!-- 左侧边侧滑菜单 -->
        <div id="pf-bd">
            <div id="pf-sider">
                <!-- 系统菜单 标题-->
                <h2 class="pf-model-name">
                    <span class="iconfont">&#xe64a;</span>
                    <span class="pf-name">系统菜单</span>
                    <span class="toggle-icon"></span>
                </h2>
                <!-- 侧滑菜单开始 -->
                <ul class="sider-nav">
                     <!--<li class="current">-->
                     <li>
                        <a href="javascript:addTab('办税专员管理','manage/taxer/listTaxer.jsp');">
                            <span class="iconfont sider-nav-icon">&#xe620;</span>
                            <span class="sider-nav-title">办税专员管理</span>
                            <i class="iconfont">&#xe642;</i>
                        </a>
                     </li>
                    <li>
                        <a href="javascript:addTab('纳税人管理','manage/taxPayer/listTaxPayer.jsp');">
                            <span class="iconfont sider-nav-icon">&#xe620;</span>
                            <span class="sider-nav-title">纳税人管理</span>
                            <i class="iconfont">&#xe642;</i>
                        </a>
                     </li>
                     <li>
                        <a href="javascript:;">
                            <span class="iconfont sider-nav-icon">&#xe625;</span>
                            <span class="sider-nav-title">调查任务管理</span>
                            <i class="iconfont">&#xe642;</i>
                        </a>
                        <ul class="sider-nav-s">
                            <li class="active"><a href="javascript:addTab('任务查询','manage/tesk/searchTask.jsp')">调查任务查询</a></li>
                            <li><a href="javascript:addTab('调查任务录入','manage/tesk/addTask.jsp')">调查任务录入</a></li>
                            <li><a href="javascript:addTab('未调查企业统计','manage/tesk/statistical.jsp')">未被调查企业统计</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="javascript:;">
                            <span class="iconfont sider-nav-icon">&#xe62f;</span>
                            <span class="sider-nav-title">系统管理</span>
                            <i class="iconfont">&#xe642;</i>
                        </a>
                        <ul class="sider-nav-s">
                           <li class="active"><a href="#">用户管理</a></li>
                           <li><a href="#">角色管理</a></li>
                           <li><a href="#">模块管理</a></li>
                           <li><a href="#">字典管理</a></li>
                           <li><a href="#">登录日志</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="javascript:;">
                            <span class="iconfont sider-nav-icon">&#xe647;</span>
                            <span class="sider-nav-title">组织管理</span>
                            <i class="iconfont">&#xe642;</i>
                        </a>
                        <ul class="sider-nav-s">
                           <li class="active"><a href="#">组织结构</a></li>
                           <li><a href="#">区域管理</a></li>
                           <li><a href="#">岗位管理</a></li>
                           <li><a href="#">员工管理</a></li>
                        </ul>
                     </li>
                     <li>
                        <a href="javascript:;">
                            <span class="iconfont sider-nav-icon">&#xe611;</span>
                            <span class="sider-nav-title">个人信息</span>
                            <i class="iconfont">&#xe642;</i>
                        </a>
                         <ul class="sider-nav-s">
                             <li class="active userMsg"><a href="javascript:">个人基本信息</a></li>
                             <li><a href="javascript:" class="mdfPwd">密码修改</a></li>
                             <li ><a href="javascript:void(0);" class="exit">退出系统</a></li>
                         </ul>
                     </li>
                 </ul>
            </div>

            <div id="pf-page">
                <div class="easyui-tabs1" style="width:100%;height:100%;">
                  <div title="首页" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="manage/home.jsp" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                  </div>
                </div>
            </div>
        </div>

        <div id="pf-ft">
            <div class="system-name">
              <i class="iconfont">&#xe6fe;</i>
              <span>税源信息管理系统&nbsp;v1.0</span>
            </div>
            <div class="copyright-name">
              <span>CopyRight&nbsp;2017&nbsp;&nbsp;zhidisoft.com&nbsp;版权所有</span>
              <i class="iconfont" >&#xe6ff;</i>
            </div>
        </div>
    </div>
    <div id="topWindow"></div>
    <!-- <script type="text/javascript" src="js/menu.js"></script> -->
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/js/main.js"></script>
    <!--[if IE 7]>
      <script type="text/javascript">
        $(window).resize(function(){
          $('#pf-bd').height($(window).height()-76);
        }).resize();
        
      </script>
    <![endif]-->  

    
    <script type="text/javascript">
        //退出按钮注册监听事件
        $(function(){
            $(".exit").on("click",function(e){
                $.messager.confirm("警告","确定退出税源信息管理系统？",function(data){
                    if(data){
                        //发送退出系统请求(清除session)
                        $.get("logout.do");
                        //退出成功重定向到登录页面
                        window.location.href="login.jsp"
                    }
                });
            });
            //个人基本信息页面
            $("#userMsg").window('close');
            $(".userMsg").click(function(){
            	$("#userMsg").window('open');
            })
            //密码修改页面
            $("#mdfPwd").window('close');
            $(".mdfPwd").click(function(){
            	$("#mdfPwd").window('open');
            })
        });

    $('.easyui-tabs1').tabs({
      tabHeight: 44,
      onSelect:function(title,index){
        var currentTab = $('.easyui-tabs1').tabs("getSelected");
        if(currentTab.find("iframe") && currentTab.find("iframe").size()){
            currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
        }
      }
    });
    function addTab(title, url){
        if ($('.easyui-tabs1').tabs('exists', title)){
            $('.easyui-tabs1').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
            $('.easyui-tabs1').tabs('add',{
                title:title,
                content:content,
                closable:true
            });
        }
    }
    $('.sider-nav-s li a').on('click',function(e){
        var parent = $(this).parent();
        parent.addClass("active").siblings().removeClass("active");

    });
    $(window).resize(function(){
          $('.tabs-panels').height($("#pf-page").height()-46);
          $('.panel-body').height($("#pf-page").height()-76)
    }).resize();

    var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;

    if(pages === 0){
      $('.pf-nav-prev,.pf-nav-next').hide();
    }
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){


      if($(this).hasClass('disabled')) return;
      if($(this).hasClass('pf-nav-next')){
        page++;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == pages){
          $(this).addClass('disabled');
          $('.pf-nav-prev').removeClass('disabled');
        }else{
          $('.pf-nav-prev').removeClass('disabled');
        }
        
      }else{
        page--;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == 0){
          $(this).addClass('disabled');
          $('.pf-nav-next').removeClass('disabled');
        }else{
          $('.pf-nav-next').removeClass('disabled');
        }
        
      }
    })

    // setTimeout(function(){
    //    $('.tabs-panels').height($("#pf-page").height()-46);
    //    $('.panel-body').height($("#pf-page").height()-76)
    // }, 200)
    </script>
    <div id="mdfPwd" class="easyui-window" title="密码修改" style="width:600px;height:400px" data-options="iconCls:'icon-save',modal:true">
		<iframe scrolling="no" src="manage/modifyPassword.jsp" frameborder="no"   border="no" height="100%" width="100%" ></iframe>
	</div>
	<div id="userMsg" class="easyui-window" title="基本信息" style="width:800px;height:300px" data-options="iconCls:'icon-save',modal:true">
    	<iframe scrolling="no" src="manage/userMsg" frameborder="no"   border="no" height="100%" width="100%" ></iframe>
	</div>
</body> 
</html>
