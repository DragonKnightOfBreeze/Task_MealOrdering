/**
 * 订单验证的Js脚本
 */
document.writeln("<script src='../../task_itcaststore.utils/js/myUtils.js'></script>");


/**
 * 验证整个表单。如果验证成功，则提交。
 */
function checkOrder() {
    if (checkReceiverName() && checkReceiverAddress() && checkReceiverPhone()) {
        $("#orderForm").submit();
    } else {
        return false;
    }
}

/**
 * 验证收货地址。
 * @returns {boolean}
 */
function checkReceiverAddress() {
    var elem = $("#receiverAddress");
    var elemMsg = $("#receiverAddressMsg");
    var nullMsg = "收货地址必须填写！";

    return checkByNull(elem, elemMsg, nullMsg);
}

/**
 * 验证收货人。
 * @returns {boolean}
 */
function checkReceiverName() {
    var elem = $("#receiverName");
    var elemMsg = $("#receiverNameMsg");
    var nullMsg = "收货人必须填写！";

    return checkByNull(elem, elemMsg, nullMsg);
}

/**
 * 验证联系方式。
 * @returns {boolean}
 */
function checkReceiverPhone() {
    var elem = $("#receiverPhone");
    var elemMsg = $("#receiverPhoneMsg");
    var regex = /^1[358]\d{9}$/;	//以13、15、18开头的手机号
    var nullMsg = "联系方式必须填写！";
    var illegalMsg = "联系方式格式不正确！";

    return checkByRegex(elem, elemMsg, regex, nullMsg, illegalMsg);
}
