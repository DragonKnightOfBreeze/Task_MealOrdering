//当商品数量发生变化时触发该方法
function changeProductNum(count, totalCount, id) {
    count = parseInt(count);
    totalCount = parseInt(totalCount);
    //如果数量为0，判断是否要删除商品
    if (count == 0) {
        var flag = window.confirm("确认删除商品吗?");

        if (!flag) {
            count = 1;
        }
    }
    if (count > totalCount) {
        alert("已达到商品最大购买量");
        count = totalCount;
    }
    location.href = "${pageContext.request.contextPath}/changeCart?id="
        + id + "&count=" + count;
}

//删除购物车中的商品
function cart_del() {
    var msg = "您确定要删除该商品吗？";
    return confirm(msg) === true;
}
