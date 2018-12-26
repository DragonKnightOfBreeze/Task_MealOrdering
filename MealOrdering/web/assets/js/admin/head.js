// let settings = null;
//
// $(function() {
//     //如果不是管理员，则跳转到权限错误页面，否则显示管理员名字
//     //QUESTION 既然此目录下的所有页面都包含这个HTML片段，过滤器是否还有必要设置？
//     $.post("/mealordering/getUser",
//         /** @property data.settings */
//         function(data) {
//             if(data.settings == null || data.settings.type !== "管理员") {
// 							location.href = "../../../error/identity-error.jsp";
//             }
// 					$("#mo_admin-name").text("管理员" + settings.userName);
//         }, "json"
//     );
//
// 	$("#mo_btn-logout").click(function() {
//         return confirm("你确定要注销当前账户吗？");
//     })
// });
