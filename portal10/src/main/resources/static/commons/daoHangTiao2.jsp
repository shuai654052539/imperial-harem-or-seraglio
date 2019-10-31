<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2019/8/28
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
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
            <ul class="nav navbar-nav" id="caidan">

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎${user.realName}</a></li>
                <li><a href="#">今天是第${user.loginCount}次登录</a></li>
                <c:if test="${user.lastLoginDate!=null}">
                    <li><a href="#">上次登录时间为<fmt:formatDate value="${user.lastLoginDate}"
                                                           pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></a></li>
                </c:if>
                <li><a href="/user/loginOut.jhtml">退出登录</a></li>
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
    a();
  });

  function a() {
    $.post({
      url: '/resource/getResources.jhtml',
      success: function(result) {
        if (result.code == 200) {
          resource = result.data;
          initRes();
        } else {
          alert(1)
        }

      }
    })
  }

  function initRes() {
    var v_TopMenuHTML = getTopMenuHTML();
    var v_TopMenuObj = $(v_TopMenuHTML);
    var v_TopMenuIdArr = getTopMenuIdArr();
    for (var i = 0; i < v_TopMenuIdArr.length; i++) {
      var v_childMenuArr = getChildMenuArr(v_TopMenuIdArr[i]);
      if (v_childMenuArr.length > 0) {
        var v_href = v_TopMenuObj.find("a[data-id='" + v_TopMenuIdArr[i] + "']");
        console.log(v_href)
        v_href.attr("data-toggle", "dropdown");
        v_href.append("<span class=\"caret\"></span>");
        console.log(v_childMenuArr)
        var v_childMenuHTML = getChildMenuHTML(v_childMenuArr);
        v_href.parent().append(v_childMenuHTML);
      }
    }
    $("#caidan").html(v_TopMenuObj);
  }

  function getTopMenuHTML() {
    var v_TopMenuHTML = "";
    for (var i = 0; i < resource.length; i++) {
      if (resource[i].fatherId == 1) {
        v_TopMenuHTML += "<li><a href=\"" + resource[i].resourceUrl + "\" data-id=\"" + resource[i].id + "\">" + resource[i].menuName + "</a></li>";
      }

    }
    return v_TopMenuHTML;
  }

  function getTopMenuIdArr() {
    var v_TopMenuIdArr = [];
    for (var i = 0; i < resource.length; i++) {
      if (resource[i].fatherId == 1) {
        v_TopMenuIdArr.push(resource[i].id)
      }
    }
    return v_TopMenuIdArr;
  }

  function getChildMenuArr(id) {
    var v_childMenuArr = [];
    for (var i = 0; i < resource.length; i++) {
      if (resource[i].fatherId == id) {
        v_childMenuArr.push(resource[i])
      }
    }
    return v_childMenuArr;
  }

  function getChildMenuHTML(childMenuArr) {
    var v_childMenuHTML = "<ul class=\"dropdown-menu\">";
    for (var i = 0; i < childMenuArr.length; i++) {
      v_childMenuHTML += "<li role=\"separator\" class=\"divider\"></li><li><a href=\"" + childMenuArr[i].resourceUrl + "\">" + childMenuArr[i].menuName + "</a></li>";
    }
    v_childMenuHTML += "</ul>";
    return v_childMenuHTML;
  }
</script>
