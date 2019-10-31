var loginFlag = false;
var v_daoHangHtml = '<nav class="navbar navbar-inverse">\n' +
    '            <div class="container-fluid">\n' +
    '                <!-- Brand and toggle get grouped for better mobile display -->\n' +
    '                <div class="navbar-header" id="genjiedian">\n' +
    '                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">\n' +
    '                        <span class="sr-only">Toggle navigation</span>\n' +
    '                        <span class="icon-bar"></span>\n' +
    '                        <span class="icon-bar"></span>\n' +
    '                        <span class="icon-bar"></span>\n' +
    '                    </button>\n' +
    '                    <a class="navbar-brand" href="/">飞狐电商后台管理</a>\n' +
    '                </div>\n' +
    '                <!-- Collect the nav links, forms, and other content for toggling -->\n' +
    '                <!--<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">-->\n' +
    '                    <ul class="nav navbar-nav" id="caidan">\n' +
    '\n' +
    '                    </ul>\n' +
    '\n' +
    '                    <ul class="nav navbar-nav navbar-right" ><li><a href="/cart.html"><span class="glyphicon glyphicon-shopping-cart">购物车</span></a></li>\n' +
    '                        <li id="aaa"><a href="/login.html">登录</a></li>\n' +
    '                        <li><a href="/addMember.html">注册</a></li>\n' +
    '                        <!--<%&#45;&#45;<li class="dropdown">\n' +
    '                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>\n' +
    '                        <ul class="dropdown-menu">\n' +
    '                            <li><a href="#">Action</a></li>\n' +
    '                            <li><a href="#">Another action</a></li>\n' +
    '                            <li><a href="#">Something else here</a></li>\n' +
    '                            <li role="separator" class="divider"></li>\n' +
    '                            <li><a href="#">Separated link</a></li>\n' +
    '                        </ul>\n' +
    '                    </li>&#45;&#45;%>-->\n' +
    '                    </ul>\n' +
    '                <!--</div>--><!-- /.navbar-collapse -->\n' +
    '            </div><!-- /.container-fluid -->\n' +
    '        </nav>';
var v_loginInfoHtml = '<li><a href="#">欢迎##userName##</a></li>\n' +
    '        <li><a href="#" onclick="out()">退出</a></li>';
var loginHtml = '<form class="form-horizontal">\n' +
    '        <div class="form-group">\n' +
    '            <label class="col-md-2 control-label">用户名</label>\n' +
    '            <div class="col-md-4">\n' +
    '                <input type="text" class="form-control" id="loginUserName" placeholder="请输入用户名...">\n' +
    '            </div>\n' +
    '        </div>\n' +
    '        <div class="form-group">\n' +
    '            <label class="col-md-2 control-label">密码</label>\n' +
    '            <div class="col-md-4">\n' +
    '                <input type="text" class="form-control" id="loginPassWord"  placeholder="请输入密码...">\n' +
    '            </div>\n' +
    '        </div>\n' +
    '    </form>';
var liebiao = '<ul class="goods-list yui3-g">\n' +
    '              <li class="yui3-u-1-24">\n' +
    '                <input type="checkbox" name="" id="aaa" value="##id##"/>\n' +
    '              </li>\n' +
    '              <li class="yui3-u-11-24">\n' +
    '                <div class="good-item">\n' +
    '                  <div class="item-img"><img src="##img##" style="width: 90px"/></div>\n' +
    '                  <div class="item-msg">##itmeName##</div>\n' +
    '                </div>\n' +
    '              </li>\n' +
    '\n' +
    '              <li class="yui3-u-1-8"><span class="price">##price##</span></li>\n' +
    '              <li class="yui3-u-1-8">\n' +
    '                <a href="javascript:void(0)" class="increment mins" onclick="updateCart(\'##id##\',-1)">-</a>\n' +
    '                <input autocomplete="off" type="text" value="##count##" minnum="1" class="itxt" />\n' +
    '                <a href="javascript:void(0)" class="increment plus" onclick="updateCart(\'##id##\',1)">+</a>\n' +
    '              </li>\n' +
    '              <li class="yui3-u-1-8"><span class="sum">##sum##</span></li>\n' +
    '              <li class="yui3-u-1-8">\n' +
    '                <a href="#none" onclick="deleItme(\'##id##\')">删除</a><br />\n' +
    '                <a href="#none">移到我的关注</a>\n' +
    '              </li>\n' +
    '            </ul>';
$(function() {
  $.ajaxSetup({
    beforeSend: function(xhr) {
      var x_fh = $.cookie('x_fh');
      xhr.setRequestHeader('x_fh', x_fh);
    }
  });
  initDaoHang();
  getLoginInfo();
});

function out() {
  $.ajax({
    url: 'http://localhost:8083/members/out.jhtml',
    type: 'get',
    success: function(result) {
      if (result.code == 200) {
        $.cookie('x_fh', null);
        location.href = '/';
      }
    }
  });
}

function initDaoHang() {
  $('#daoHangDiv').html(v_daoHangHtml);
}

function getLoginInfo() {
  $.ajax({
    url: 'http://localhost:8083/members/userInfo.jhtml',
    type: 'get',
    async: false,
    success: function(result) {
      if (result.code == 200) {
        loginFlag = true;
        $('#aaa').after(v_loginInfoHtml.replace('##userName##', result.data));
        $('#aaa').hide();
      }
    }
  });
}
