/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */

/**
 * 验证某个jQuery对象对应的参数（验证是否为空/空格）。
 * @param elem
 * @param elemMsg
 * @param nullMsg {string} 参数为空时的提示
 * @param trueStyle {JSON} 参数正确时的Css格式
 * @param falseStyle {JSON} 参数错误时的Css格式
 * @returns {boolean}
 */
function checkByNull(elem, elemMsg, nullMsg, trueStyle, falseStyle) {
    nullMsg = nullMsg || "参数不能为空！";
    trueStyle = trueStyle || {"color": "black"};
    falseStyle = falseStyle || {"color": "red"};


    let elemValue = elem.val().trim();
    let msg = "";
    //验证
    if(!elemValue) {
        msg = nullMsg;
    }
    //将提示消息放入span
    elemMsg.text(msg);
    //根据消息结果改变tr的颜色，并返回验证结果
    elemMsg.parent().parent().css(msg === "" ? trueStyle : falseStyle);
    return msg === "";
}

/**
 * 根据正则验证某个jQuery对象对应的参数。
 * @param elem
 * @param elemMsg
 * @param regex {RegExp} 正则
 * @param nullMsg {string} 参数为空时的提示
 * @param illegalMsg {string} 参数非法时的提示
 * @param trueStyle {JSON} 参数正确时的Css格式
 * @param falseStyle {JSON} 参数错误时的Css格式
 * @returns {boolean}
 */
function checkByRegex(elem, elemMsg, regex, nullMsg, illegalMsg, trueStyle, falseStyle) {
    nullMsg = nullMsg || "参数不能为空！";
    illegalMsg = illegalMsg || "参数非法！";
    trueStyle = trueStyle || {"color": "black"};
    falseStyle = falseStyle || {"color": "red"};

    let elemValue = elem.val().trim();
    let msg = "";
    //验证
    if(!elemValue) {
        msg = nullMsg;
    } else if(!regex.test(elemValue)) {
        msg = illegalMsg;
    }
    //将提示消息放入span
    elemMsg.text(msg);
    //根据消息结果改变tr的颜色，并返回验证结果
    elemMsg.parent().parent().css(msg === "" ? trueStyle : falseStyle);
    return msg === "";
}

/**
 * 根据重复输入验证某个jQuery对象对应的参数。
 * @param elem
 * @param reElemMsg
 * @param reElem
 * @param nullMsg {string} 参数为空时的提示
 * @param illegalMsg {string} 参数非法时的提示
 * @param trueStyle {JSON} 参数正确时的Css格式
 * @param falseStyle {JSON} 参数错误时的Css格式
 * @returns {boolean}
 */
function checkByRepeat(reElem, reElemMsg, elem, nullMsg, illegalMsg, trueStyle, falseStyle) {
    nullMsg = nullMsg || "确认参数不能为空！";
    illegalMsg = illegalMsg || "参数不一致！";
    trueStyle = trueStyle || {"color": "black"};
    falseStyle = falseStyle || {"color": "red"};

    let reElemValue = reElem.val().trim();
    let elemValue = elem.val().trim();
    let msg = "";
    //验证
    if(!reElemValue || !elemValue) {
        msg = nullMsg;
    } else if(reElemValue !== elemValue) {
        msg = illegalMsg;
    }
    //将提示消息放入span
    reElemMsg.text(msg);
    //根据消息结果改变tr的颜色，并返回验证结果
    reElemMsg.parent().parent().css(msg === "" ? trueStyle : falseStyle);
    return msg === "";
}

/**
 * 弹出确认窗口。
 * @param msg {string} 确认信息
 * @returns {boolean}
 */
function confirmWarn(msg) {
    return confirm(msg) === true;
}


var interval;

/**
 * 开始倒计时，并在结束后跳转。
 * @param elem
 * @param url {string} 跳转到的地址（相对于Web项目）
 */
function countDown(elem, url) {
    interval = window.setInterval(function() {
        var sValue = parseInt(elem.text());
        if(sValue !== 0) {
            elem.text(sValue - 1);
        } else {
            window.clearInterval(interval);
            //得到目录地址名，例如：/client
            var pathName = window.location.pathname.substring(1);
            //得到目录名，例如：client
            var webName = pathName === '' ? '' : pathName.split('/', 1)[0];
            //得到完整的用于访问的url，例如：http://localhost:8080/bookstore/index.jsp
            location.href = `${window.location.protocol}//${window.location.host}/${webName}/${url}`;
        }
    }, 1000);
}

export {checkByNull, checkByRegex, checkByRepeat, confirmWarn, countDown};
