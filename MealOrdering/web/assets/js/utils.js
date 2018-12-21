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


// /**
//  * 截取地址，得到参数（仅限Get请求）。
//  */
// function parseUrl(url){
//     if(url.indexOf("?") === -1) {
//         return {};
//     }
//     const query = url.split("?")[1];
//     const queryArr = query.split("&");
//     const obj = {};
//     for(const item of queryArr) {
//         const key = item.split("=")[0];
//         const value = item.split("=")[1];
//         obj[key] = decodeURIComponent(value);
//     }
//     return obj;
// }

/**
 * 截取地址中?后面的部分，得到Get请求参数
 * @return {{}}
 */
function getParamMap() {
    let str = window.location.search;
    let entrySet = str.split("&");
    let paramMap = {};
    for(let entry of paramMap) {
        let key = entry.split("=")[0];
        let value = entry.split("=")[1];
        paramMap[key] = decodeURIComponent(value);
    }
    return paramMap;
}


let interval;

/**
 * 开始倒计时，并在结束后跳转。
 * @param jElem
 * @param url {string} 跳转到的地址（相对于Web项目，不以/开头）
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
            //得到完整的用于访问的url，例如：http://localhost:8080/bookstore/index.html
            location.href = `${window.location.protocol}//${window.location.host}/${webName}/${url}`;
        }
    }, 1000);
}

/**
 * 切换激活
 * @param $elem 要激活的元素
 * @param $otherElem 要检查的其他元素，可以为类选择器
 */
function toggleActive($elem, ...$otherElem) {
    if($elem.hasClass("active"))
        return;
    for(let $other of $otherElem) {
        let $o = $other.filter(".active");
        if($o != null) {
            $o.removeClass("active");
            break;
        }
    }
    $elem.addClass("active");
}


export {checkEmpty, checkSpace, checkByRegex, checkByRepeat, countDown, getParamMap, toggleActive};
