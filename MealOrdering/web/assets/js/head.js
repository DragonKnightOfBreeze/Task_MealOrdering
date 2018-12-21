import * as utils from "./utils";

let user = null;

//1到10位的字母数字下划线
let userNameRegex = /^[a-zA-Z_]\w{0,9}$/;
//验证邮箱
//let emailRegex = /^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/;
//6到16位的任意字符
let passwordRegex = /^.{6,16}$/;


$(function() {
    //尝试得到当前登录的用户，显示不同的用户菜单，如果是管理员则跳转到管理页
    //QUESTION 既然此目录下的所有页面都包含这个HTML片段，过滤器是否还有必要设置？
    $.post("/mealordering/getUser",
        /** @property data.user*/
        function(data) {
            if(data.user == null)
                return;
            if(data.user.type === "管理员") {
                location.href = "../../admin/welcome.html";
            } else {
                user = data.user;
                $("#_visitor-menu").hide();
                $("#_user-menu").show();
            }
        }, "json"
    );

    //DONE 按情况显示注册/登录分页，默认显示登录页面
    let $pageLogin = $("#_page-login");
    let $pageRegister = $("#_page-register");
    $("#_btn-login").click(function() {
        utils.toggleActive($pageLogin, $pageRegister);
    });
    $("#_btn-register").click(function() {
        utils.toggleActive($pageRegister, $pageLogin);
    });

    //DONE 注销确认
    $("#_btn-logout").click(function() {
        return confirm("你确定要注销当前账户吗？");
    });

    //DONE 拦截管理员登录，验证输入
    $("#_form-login-admin").submit(function() {
        let $userName = $("#_user-name-admin");
        let $password = $("#_password-admin");
        let $alert = $("#_alert-login-admin");
        let result = utils.checkSpace($userName, $alert, "名字不能包含空格！") &&
            utils.checkSpace($password, $alert, "密码不能包含空格！");
        //如果格式正确，则post到Servlet，检查是否存在这个管理员
        if(result) {
            $.post("/mealordering/admin/login",
                {userName: $userName, password: $password},
                /** @property data.status */
                function(data) {
                    if(data.status === "notFound") {
                        $alert.text("管理员不存在！");
                        result = false;
                    } else if(data.status === "error") {
                        $alert.text("发生错误，请重新登录！");
                        result = false;
                    }
                }, "json"
            );
        }
        if(!result) {
            $alert.collapse("show");
        }
        return result;
    });

//DONE 拦截用户登录，验证输入
    $("#_form-login").submit(function(e) {
        let $userName = $("#_user-name");
        let $password = $("#_password");
        let $alert = $("#_alert-login");
        let result = utils.checkSpace($userName, $alert, "用户名不能包含空格！") &&
            utils.checkSpace($password, $alert, "密码不能包含空格！");
        //如果格式正确，则post到Servlet，检查是否存在这个用户
        if(result) {
            $.post("/mealordering/login",
                {userName: $userName, password: $password},
                /** @property data.status */
                function(data) {
                    result = result && data.status;
                }, "json"
            );
        }
        if(result) {
            let rememberLogin = $("#_rememberLogin").prop("checked");
            //TODO post到Servlet，记住登录状态
            if(rememberLogin) {
                $.post("/mealordering/rememberLogin");
            }
        } else {
            $alert.collapse("show");
        }
        return result;
    });

//DONE 拦截用户注册，验证输入
    $("#_form-register").submit(function() {
        let $userName = $("#_user-name-reg");
        let $password = $("#_password-reg");
        let $rePassword = $("#_re-password-reg");
        let $alert = $("#_alert-register");
        let result = utils.checkSpace($userName, $alert, "用户名不能包含空格！") &&
            utils.checkByRegex($userName, $alert, userNameRegex, "用户名格式不合法！") &&
            utils.checkSpace($password, $alert, "密码不能包含空格！") &&
            utils.checkByRegex($userName, $alert, passwordRegex, "密码格式不合法！") &&
            utils.checkByRepeat($rePassword, $password, $alert, "两次输入的密码不一致！");
        //如果格式正确，即可以提交
        if(!result) {
            $alert.collapse("show");
        }
        return result;
    });
});


