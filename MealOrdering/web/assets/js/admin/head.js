let user = null;

$(function() {
    //如果不是管理员，则跳转到权限错误页面，否则显示管理员名字
    //QUESTION 既然此目录下的所有页面都包含这个HTML片段，过滤器是否还有必要设置？
    $.post("/mealordering/getUser",
        /** @property data.user */
        function(data) {
            if(data.user == null || data.user.type !== "管理员") {
                location.href = "../error/identity-error.html";
            }
            $("#_admin-name").text("管理员" + user.userName);
        }, "json"
    );

    $("#_btn-logout").click(function() {
        return confirm("你确定要注销当前账户吗？");
    })
});
