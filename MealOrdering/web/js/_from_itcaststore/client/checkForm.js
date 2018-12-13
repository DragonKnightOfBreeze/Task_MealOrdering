/**
 * 表单验证的Js脚本
 */
document.writeln("<script src='../../task_itcaststore.utils/js/myUtils.js'></script>");


/**
 * //验证整个表单。返回false后, 事件将被取消。
 * @param isLogin {boolean}
 * @returns {boolean}
 */
function checkForm(isLogin) {
    if (isLogin) {
        return checkUserName() && checkPassword();
    } else {
        return checkEmail() && checkUserName() && checkPassword() && checkRePassword();
    }
}

/**
 * 验证用户名。
 * @returns {boolean}
 */
function checkUserName() {
    var elem = $("#userName");
    var elemMsg = $("#userNameMsg");
    var regex = /^[a-zA-Z_]\w{0,9}$/; //1到10位的字母数字下划线, 不能是数字开头
    var nullMsg = "用户名必须填写！";
    var illegalMsg = "用户名不合法！";

    return checkByRegex(elem, elemMsg, regex, nullMsg, illegalMsg);
}

/**
 * 验证邮箱。
 * @returns {boolean}
 */
function checkEmail() {
    var elem = $("#email");
    var elemMsg = $("#emailMsg");
    var regex = /^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/;
    var nullMsg = "邮箱必须填写！";
    var illegalMsg = "邮箱格式不合法！";

    return checkByRegex(elem, elemMsg, regex, nullMsg, illegalMsg);
}

/**
 * 验证密码。
 * @returns {boolean}
 */
function checkPassword() {
    var elem = $("#password");
    var elemMsg = $("#passwordMsg");
    var regex = /^.{6,16}$/; //6到16位的任意字符
    var nullMsg = "密码必须填写！";
    var illegalMsg = "密码格式不合法！";

    return checkByRegex(elem, elemMsg, regex, nullMsg, illegalMsg);
}

/**
 * 验证确认密码
 * @returns {boolean}
 */
function checkRePassword() {
    var reElem = $("#rePassword");
    var reElemMsg = $("#rePasswordMsg");
    var elem = $("#password");
    var nullMsg = "确认密码必须填写！";
    var illegalMsg = "密码必须保持一致！";

    return checkByRepeat(reElem, reElemMsg, elem, nullMsg, illegalMsg);
}

