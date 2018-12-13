/**
 * 更改时间的Js脚本
 */

var interval;

/**
 * 从5倒计时到0，然后跳转到首页。
 */
function changeSecond() {
    interval = window.setInterval(function () {
        var second = $("#second");
        var sValue = parseInt(second.text());
        if (sValue !== 0) {
            second.text(sValue - 1);
        } else {
            window.clearInterval(interval);
            //得到目录地址名，例如：/client
            var pathName = window.location.pathname.substring(1);
            //得到目录名，例如：client
            var webName = pathName === '' ? '' : pathName.split('/', 1)[0];
            //得到完整的用于访问的url，例如：http://localhost:8080/bookstore/index.jsp
            location.href = window.location.protocol + '//' + window.location.host + '/' + webName + '/index.jsp';
        }
    }, 1000);
}
