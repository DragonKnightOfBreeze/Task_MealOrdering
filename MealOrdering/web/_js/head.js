//DONE 到点击注册时切换到注册表单
import * as utils from "./utils";

let user = null;

$(function() {
    //判断是否有用户登录，并显示不同的用户菜单，如果是管理员则跳转
    $.get("/mealordering/getUser",
        /**
         * @param data.user
         */
        function(data) {
            if(data.user != null && data.user.type === "管理员") {
                location.href = "mealordering/admin/welcome.jsp";
            } else if(data.user != null) {
                user = data.user;
                $("#_visitor-menu").hide();
                $("#_user-menu").show();
            }
        }, "json"
    )
});

$("#_btn-register").on("click", function() {
    $("#_page-login").removeClass("active");
    $("#_page-register").addClass("active");
});

//DONE 拦截用户登录，验证输入
$("#_form-login").on("submit", function(e) {
    let $userName = $("#_user-name");
    let $password = $("#_password");
    let $alert = $("#_alert-login");
    let result = utils.checkSpace($userName, $alert, "用户名不能包含空格！") &&
        utils.checkSpace($password, $alert, "密码不能包含空格！");
    $alert.collapse({show: !result});
    return result;
});

//DONE 拦截用户注册，验证输入
$("#_form-register").on("submit", function() {
    let $userName = $("#_user-name-reg");
    //1到10位的字母数字下划线
    let userNameRegex = /^[a-zA-Z_]\w{0,9}$/;
    //验证邮箱
    //let emailRegex = /^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/;
    let $password = $("#_password-reg");
    //6到16位的任意字符
    let passwordRegex = /^.{6,16}$/;
    let $rePassword = $("#_re-password-reg");
    let $alert = $("#_alert-register");
    let result = utils.checkSpace($userName, $alert, "用户名不能包含空格！") &&
        utils.checkByRegex($userName, $alert, userNameRegex, "用户名格式不合法！") &&
        utils.checkSpace($password, $alert, "密码不能包含空格！") &&
        utils.checkByRegex($userName, $alert, passwordRegex, "密码格式不合法！") &&
        utils.checkByRepeat($rePassword, $password, $alert, "两次输入的密码不一致！");
    $alert.collapse({show: !result});
    return result;
});

//DONE 拦截管理员登录，验证输入
$("#_form-login-admin").on("submit", function() {
    let $userName = $("#_user-name-admin");
    let $password = $("#_password-admin");
    let $alert = $("#_alert-login-admin");
    let result = utils.checkSpace($userName, $alert, "名字不能包含空格！") &&
        utils.checkSpace($password, $alert, "密码不能包含空格！");
    $alert.collapse({show: !result});
    return result;
});
