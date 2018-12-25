import * as utils from "./utils";

let user = null;

$(function() {
    //尝试得到当前登录的用户，显示不同的用户菜单，如果是管理员则跳转到管理页
    //QUESTION 既然此目录下的所有页面都包含这个HTML片段，过滤器是否还有必要设置？
    $.post("/mealordering/getUser",
        /** @property data.user*/
        function(data) {
            if(data.user == null)
                return;
            if(data.user.type === "管理员") {
                location.href = "/mealordering/admin/welcome.html";
            } else {
                user = data.user;
							$("#mo-visitor-menu").hide();
							$("#mo-user-menu").show();
            }
        }, "json"
    );

    //DONE 按情况显示注册/登录分页，默认显示登录页面
	let $pageLogin = $("#mo-page-login");
	let $pageRegister = $("#mo-page-register");
	$("#mo-btn-login").click(function() {
        utils.toggleActive($pageLogin, $pageRegister);
    });
	$("#mo-btn-register").click(function() {
        utils.toggleActive($pageRegister, $pageLogin);
    });

    //DONE 注销确认
	$("#mo-btn-logout").click(function() {
        return confirm("你确定要注销当前账户吗？");
    });

    //DONE 拦截管理员登录，验证输入
    //INFO 使用jQuery验证插件
	$("#mo-form-login-admin").validate({
        //验证通过后的回调函数
        submitHandler: function(form) {
            $(form).submit();
        },
        //验证规则
        rules: {
            userName: {
                required: true,
                //1到10位的字母、数字、下划线
							pattern: "[a-zA-Z_]\\w{0,9}",
                //使用Ajax进行异步验证，默认会提交当前验证的值到远程地址。远程地址只能输出true或false
                remote: {
                    url: "/mealordering/admin/login", type: "post", dataType: "json",
									data: {username: $("#mo-user-name-admin").val()}
                }
            },
            password: {
                required: true,
                //6到16位的任意非空白符字符
                pattern: ".{6,16}",
                //使用Ajax进行异步验证，默认会提交当前验证的值到远程地址。判断哪个参数为null来区分要验证的数据
                remote: {
                    url: "/mealordering/admin/login", type: "post", dataType: "json",
									data: {username: $("#mo-user-name-admin").val(), password: $("#mo-password-admin").val()}
                }
            }
        },
        //验证不通过时显示的信息
        messages: {
            userName: {
                required: "管理员名必须填写！",
                pattern: "管理员名格式不正确！",
                remote: "管理员不存在！"
            },
            password: {
                required: "管理员密码必须填写！",
                pattern: "管理员密码格式不正确！",
                remote: "管理员密码不正确！"
            }
        },
        //错误信息的插入位置（参数：错误信息对应元素，在验证的元素）
        errorPlacement: function(error, element) {
					error.appendTo($("#mo-msg-login-admin"));
        },
        //错误消息对应的元素类型，默认为label
        errorElement: "label",
        //错误消息对应的要切换显示的容器元素
		errorContainer: "#mo-alert-login-admin",
        //验证通过后的回调函数（参数：验证元素）或字符串（添加类到验证元素）
        success: "valid"
    });

    // $("#_form-login-admin").submit(function() {
    //     let $userName = $("#_user-name-admin");
    //     let $password = $("#_password-admin");
    //     let $alert = $("#_alert-login-admin");
    //     let result = utils.checkSpace($userName, $alert, "名字不能包含空格！") &&
    //         utils.checkSpace($password, $alert, "密码不能包含空格！");
    //     //如果格式正确，则post到Servlet，检查是否存在这个管理员
    //     if(result) {
    //         $.post("/mealordering/admin/login",
    //             {userName: $userName, password: $password},
    //             /** @property data.status */
    //             function(data) {
    //                 if(data.status === "notFound") {
    //                     $alert.text("管理员不存在！");
    //                     result = false;
    //                 } else if(data.status === "error") {
    //                     $alert.text("发生错误，请重新登录！");
    //                     result = false;
    //                 }
    //             }, "json"
    //         );
    //     }
    //     if(!result) {
    //         $alert.collapse("show");
    //     }
    //     return result;
    // });

    //DONE 拦截用户登录，验证输入
    //INFO 使用jQuery验证插件
	$("#mo-form-login").validate({
        submitHandler: function(form) {
            //如果勾选记住登录状态，则post到Servlet以添加Cookie，然后再提交
					let rememberLogin = $("#mo-rememberLogin").prop("checked");
            if(rememberLogin) {
                $.post("/mealordering/rememberLogin");
            }
            $(form).submit();
        },
        rules: {
            userName: {
                required: true,
                //1到10位的字母、数字、下划线
							pattern: "[a-zA-Z_]\\w{0,9}",
                remote: {
                    url: "/mealordering/login", type: "post",
									data: {username: $("#mo-user-name").val()}
                }
            },
            password: {
                required: true,
                //6到16位的任意非空白符字符
                pattern: ".{6,16}",
                remote: {
                    url: "/mealordering/login", type: "post",
									data: {username: $("#mo-user-name").val(), password: $("#mo-password").val()}
                }
            }
        },
        messages: {
            userName: {
                required: "用户名必须填写！",
                pattern: "用户名格式不正确！",
                remote: "用户不存在！"
            },
            password: {
                required: "用户密码必须填写！",
                pattern: "用户密码格式不正确！",
                remote: "用户密码不正确！"
            }
        },
        errorPlacement: function(error, element) {
					error.appendTo($("#mo-msg-login"));
        },
		errorContainer: "#mo-alert-login",
        success: "valid"
    });

    // $("#_form-login").submit(function(e) {
    //     let $userName = $("#_user-name");
    //     let $password = $("#_password");
    //     let $alert = $("#_alert-login");
    //     let result = utils.checkSpace($userName, $alert, "用户名不能包含空格！") &&
    //         utils.checkSpace($password, $alert, "密码不能包含空格！");
    //     //如果格式正确，则post到Servlet，检查是否存在这个用户
    //     if(result) {
    //         $.post("/mealordering/login",
    //             {userName: $userName, password: $password},
    //             /** @property data.status */
    //             function(data) {
    //                 result = result && data.status;
    //             }, "json"
    //         );
    //     }
    //     if(result) {
    //         let rememberLogin = $("#_rememberLogin").prop("checked");
    //         //post到Servlet，记住登录状态
    //         if(rememberLogin) {
    //             $.post("/mealordering/rememberLogin");
    //         }
    //     } else {
    //         $alert.collapse("show");
    //     }
    //     return result;
    // });

    //DONE 拦截用户注册，验证输入
    //INFO 使用jQuery验证插件
	$("#mo-form-register").validate({
            submitHandler: function(form) {
                //NOTE 提交到的就是register的Servlet
                $(form).submit();
            },
            rules: {
                userName: {
                    required: true,
                    //1到10位的字母、数字、下划线
									pattern: "[a-zA-Z_]\\w{0,9}"
                },
                password: {
                    required: true,
                    //6到16位的任意非空白符字符
                    pattern: ".{6,16}"
                },
                rePassword: {
                    required: true,
									equalTo: "#mo-password-reg"
                },
                email: {
                    required: true,
                    email: true
                },
                phoneNum: {
                    required: true,
                    rangelength: [11, 11]
                }
            },
            messages: {
                userName: {
                    required: "用户名必须填写！",
                    pattern: "用户名格式不正确！"
                },
                password: {
                    required: "用户密码必须填写！",
                    pattern: "用户密码格式不正确！"
                },
                rePassword: {
                    required: "请再次确认你的密码！",
                    equalTo: "两次输入的密码不一致！"
                },
                email: {
                    required: "用户邮箱必须填写！",
                    email: "邮箱格式不正确！"
                },
                phoneNum: {
                    required: "用户手机号码必须填写！",
                    rangelength: "手机号码格式不正确！"
                }
            },
            errorPlacement: function(error, element) {
							error.appendTo($("#mo-msg-register"));
            },
			errorContainer: "#mo-alert-register",
            success: "valid"
        }
    );

    // $("#_form-register").submit(function() {
    //     let $userName = $("#_user-name-reg");
    //     let $password = $("#_password-reg");
    //     let $rePassword = $("#_re-password-reg");
    //     let $alert = $("#_alert-register");
    //     let result = utils.checkSpace($userName, $alert, "用户名不能包含空格！") &&
    //         utils.checkByRegex($userName, $alert, userNameRegex, "用户名格式不合法！") &&
    //         utils.checkSpace($password, $alert, "密码不能包含空格！") &&
    //         utils.checkByRegex($userName, $alert, passwordRegex, "密码格式不合法！") &&
    //         utils.checkByRepeat($rePassword, $password, $alert, "两次输入的密码不一致！");
    //     //如果格式正确，即可以提交
    //     if(!result) {
    //         $alert.collapse("show");
    //     }
    //     return result;
    // });
});


