import * as utils from "/mealordering/assets/js/utils";

$(function() {
    let $navLinks = $("#_np-reset-psw .nav-link");
    let $tabPanes = $("#_tc-reset-psw").children();

    //DONE 第一步：发送邮件
    //INFO 使用jQuery验证插件
    $("#_form-find-psw").validate({
        submitHandler: function(form) {
            //激活导航栏和分页面板，进入到下一步，而非提交表单
            $navLinks.removeClass("active").eq(1).addClass("active");
            $tabPanes.removeClass("active").eq(1).addClass("active");
        },
        rules: {
            email: {
                required: true,
                email: true
            },
        },
        messages: {
            email: {
                required: "请填写你的邮箱地址！",
                email: "邮箱地址格式不正确！"
            }
        },
        errorPlacement: function(error, element) {
            error.appendTo($("#_msg-find-psw"));
        },
        errorContainer: "#_alert-find-psw",
        success: "valid"
    });

    //第二步：修改密码
    //INFO 使用jQuery验证插件
    $("#_form-change-psw").validate({
        submitHandler: function(form) {
            //激活导航栏和分页面板，进入到下一步，而非提交表单
            $navLinks.removeClass("active").eq(2).addClass("active");
            $tabPanes.removeClass("active").eq(2).addClass("active");
        },
        rules: {
            password: {
                required: true,
                //6到16位的任意非空白符字符
                pattern: ".{6,16}",
            },
            rePassword: {
                required: true,
                equalTo: "_password-change-psw"
            }
        },
        messages: {
            password: {
                required: "请输入新密码！",
                pattern: "密码格式不正确！"
            },
            rePassword: {
                required: "请再次确认你的密码！",
                equalTo: "两次输入的密码格式不一致！"
            }
        },
        errorPlacement: function(error, element) {
            error.appendTo($("#_msg-find-psw"));
        },
        errorContainer: "#_alert-find-psw",
        success: "valid"
    });

    //DONE 第三步：开始倒计时跳转，回到首页
    if($("#_tp-success").hasClass("active")) {
        utils.countDown($("#_second"), "index.html");
    }
});
