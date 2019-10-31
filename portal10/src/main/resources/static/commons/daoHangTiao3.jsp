<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2019/8/28
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
    .dropdown-submenu {
        position: relative;
    }

    .dropdown-submenu > .dropdown-menu {
        top: 0;
        left: 100%;
        margin-top: -6px;
        margin-left: -1px;
        -webkit-border-radius: 0 6px 6px 6px;
        -moz-border-radius: 0 6px 6px;
        border-radius: 0 6px 6px 6px;
    }

    .dropdown-submenu:hover > .dropdown-menu {
        display: block;
    }

    .dropdown-submenu > a:after {
        display: block;
        content: " ";
        float: right;
        width: 0;
        height: 0;
        border-color: transparent;
        border-style: solid;
        border-width: 5px 0 5px 5px;
        border-left-color: #ccc;
        margin-top: 5px;
        margin-right: -10px;
    }

    .dropdown-submenu:hover > a:after {
        border-left-color: #fff;
    }

    .dropdown-submenu.pull-left {
        float: none;
    }

    .dropdown-submenu.pull-left > .dropdown-menu {
        left: -100%;
        margin-left: 10px;
        -webkit-border-radius: 6px 0 6px 6px;
        -moz-border-radius: 6px 0 6px 6px;
        border-radius: 6px 0 6px 6px;
    }
</style>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" id="genjiedian">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">飞狐电商后台管理</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎${user.realName}</a></li>
                <li><a href="#">今天是第${user.loginCount}次登录</a></li>
                <c:if test="${user.lastLoginDate!=null}">
                    <li><a href="#">上次登录时间为<fmt:formatDate value="${user.lastLoginDate}"
                                                           pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></a></li>
                </c:if>
                <li><a href="/user/loginOut.jhtml">退出登录</a></li>
                <li><a href="/user/toPassword.jhtml">修改密码</a></li>
                <%--<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>--%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</body>
<script>
  var resource;
  $(function() {
    $.ajaxSetup({
      complete: function(res) {
        if (res.responseJSON.code && res.responseJSON.code != 200) {
          bootbox.alert({
            message: "<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\">" + res.responseJSON.msg + "！",
            size: 'small',
            title: "提示信息"
          })
        }
      }
    })
    a();
  });

  function a() {
    $.post({
      url: '/resource/getResources.jhtml',
      success: function(result) {
        if (result.code == 200) {
          resource = result.data;
          var hTml = initRes(1, 1);
          $("#bs-example-navbar-collapse-1").append(hTml);
        } else {
          alert(1)
        }

      }
    })
  }

  function initRes(id, level) {
    var v_TopMenu = getTopMenu(id);
    var hTml = '';
    if (level == 1) {
      hTml += '<ul class="nav nav-tabs" >';
    } else {
      hTml += '<ul class="dropdown-menu">';
    }
    for (var i = 0; i < v_TopMenu.length; i++) {
      var boolean = judge(v_TopMenu[i].id);
      if (boolean) {
        if (level == 1) {
          hTml += '<li class="dropdown" ><a href="#" class="dropdown-toggle" data-toggle="dropdown">' + v_TopMenu[i].menuName + '<span class="caret"></span></a>';
        } else {
          hTml += '<li class="dropdown-submenu"><a href="' + v_TopMenu[i].resourceUrl + '">' + v_TopMenu[i].menuName + '</a>';
        }
        hTml += initRes(v_TopMenu[i].id, level + 1);
      } else {
        hTml += '<li ><a href="' + v_TopMenu[i].resourceUrl + '">' + v_TopMenu[i].menuName + '</a></li>';
      }
      hTml += '</li>';
    }
    hTml += '</ul>';
    return hTml;
  }

  function getTopMenu(id) {
    var TopMenuArr = [];
    for (var i = 0; i < resource.length; i++) {
      if (resource[i].fatherId == id) {
        TopMenuArr.push(resource[i]);
      }
    }
    return TopMenuArr;
  }

  function judge(id) {
    var boolean = false;
    for (var i = 0; i < resource.length; i++) {
      if (resource[i].fatherId == id) {
        boolean = true;
        break;
      }
    }
    return boolean;
  }
</script>
