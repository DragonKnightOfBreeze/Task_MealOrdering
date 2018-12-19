/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */

/**
 * 验证表单参数是否为null、空。
 * @param elem
 * @param msgElem
 * @param msg {string} 警告信息
 * @returns {boolean}
 */
function checkEmpty(elem, msgElem, msg = "参数不能为空！") {
    if(!elem.val()) {
        msgElem.text(msg);
        return false;
    }
    return true;
}

/**
 * 验证表单参数是否包含空格。
 * @param elem
 * @param msgElem
 * @param msg {string} 警告信息
 * @returns {boolean}
 */
function checkSpace(elem, msgElem, msg = "参数不能包含空格！") {
    if(elem.val().indexOf(" ") !== -1) {
        msgElem.text(msg);
        return false;
    }
    return true;
}


/**
 * 根据正则验证表单参数。
 * @param elem
 * @param msgElem
 * @param regex {RegExp} 正则
 * @param msg {string} 警告信息
 * @returns {boolean}
 */
function checkByRegex(elem, msgElem, regex, msg = "参数非法！") {
    if(regex.test(elem.val())) {
        msgElem.text(msg);
        return false;
    }
    return true;
}

/**
 * 根据重复输入验证表单参数。
 * @param reElem
 * @param elem
 * @param msgElem
 * @param msg 警告消息
 * @returns {boolean}
 */
function checkByRepeat(reElem, elem, msgElem, msg = "参数不一致！") {
    if(reElem.val().trim() !== reElem.val()) {
        msgElem.text(msg);
        return false;
    }
    return true;
}

/**
 * 验证url。
 * @param url
 * @returns {boolean}
 */
function validURL(url) {
    try {
        url = decodeURIComponent(url);
    }
    catch(error) {
        return false;
    }
    const pos = url.indexOf(".html");
    if(pos === -1 || pos !== url.length - 5)
        return false;
    let allowNumber = false;
    let allowSep = false;
    let seenDot = false;
    for(let i = 0; i < url.length - 5; i++) {
        const ch = url.charAt(i);
        if('a' <= ch && ch <= 'z' ||
            'A' <= ch && ch <= 'Z' ||
            ch === '$' ||
            ch === '_' ||
            ch.charCodeAt(0) > 127) {
            allowNumber = true;
            allowSep = true;
        } else if('0' <= ch && ch <= '9'
            || ch === '-') {
            if(!allowNumber)
                return false;
        } else if(ch === '/' || ch === '.') {
            if(!allowSep)
                return false;
            allowNumber = false;
            allowSep = false;
            if(ch === '.')
                seenDot = true;
            if(ch === '/' && seenDot)
                return false;
        } else {
            return false;
        }
    }
    return true;
}


/**
 * 弹出确认窗口。
 * @param msg {string} 确认信息
 * @returns {boolean}
 */
function confirm(msg = "确定提交？") {
    return confirm(msg) === true;
}


let interval;

/**
 * 开始倒计时，并在结束后跳转。
 * @param jElem
 * @param url {string} 跳转到的地址（相对于Web项目）
 */
function countDown(jElem, url) {
    interval = window.setInterval(function() {
        if(jElem.text() !== 0) {
            jElem.text(jElem.text() - 1);
        } else {
            window.clearInterval(interval);
            //得到目录地址名，例如：/client
            const pathName = window.location.pathname.substring(1);
            //得到目录名，例如：client
            const webName = pathName === '' ? '' : pathName.split('/', 1)[0];
            //得到完整的用于访问的url，例如：http://localhost:8080/bookstore/index.jsp
            location.href = `${window.location.protocol}//${window.location.host}/${webName}/${url}`;
        }
    }, 1000);
}

export {checkEmpty, checkSpace, checkByRegex, checkByRepeat, confirm, countDown};
