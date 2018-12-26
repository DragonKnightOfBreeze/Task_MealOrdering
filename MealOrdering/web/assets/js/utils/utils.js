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

/**
 * 格式化日期
 * @param {string} time
 * @return {string}
 */
function formatDate(time) {
	let date = new Date(time);

	let year = date.getFullYear(),
		month = date.getMonth() + 1,//月份是从0开始的
		day = date.getDate(),
		hour = date.getHours(),
		min = date.getMinutes(),
		sec = date.getSeconds();
	return year + '-' +
		month + '-' +
		day + ' ' +
		hour + ':' +
		min + ':' +
		sec;
}

/**
 * 计时跳转
 * <br>NOTE 写在$()里面。
 * @param $elem 秒数对应的元素
 * @param {string} url 计时完毕后的跳转地址，相对于contextPath且以`/`开头
 * @param interval 定时调度对象
 */
function countDown($elem, url, interval) {
	interval = window.setInterval(function() {
		if($elem.text() != 0) {
			$elem.text($elem.text() - 1);
		} else {
			window.clearInterval(interval);
			location.href = url;
		}
	}, 1000);
}


export {checkEmpty, checkSpace, checkByRegex, checkByRepeat, getParamMap, toggleActive, formatDate, countDown};
