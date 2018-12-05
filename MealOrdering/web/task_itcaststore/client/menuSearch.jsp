<%@ page contentType="text/html; charset=UTF-8" %>

<script>
    /**
     * my_click和my_blur均是用于前台页面搜索框的函数
     */

    /**
     * 鼠标点击搜索框时执行。
     */
    function my_click(obj, myId) {
        //点击时，如果取得的值和搜索框默认value值相同，则将搜索框清空
        if (document.getElementById(myId).value === document.getElementById(myId).defaultValue) {
            document.getElementById(myId).value = '';
            obj.style.color = '#000';
        }
    }

    /**
     * 鼠标离开搜索框时执行。
     */
    function my_blur(obj, myId) {
        //鼠标失焦时，如果搜索框没有输入值，则用搜索框的默认value值填充
        if (document.getElementById(myId).value === '') {
            document.getElementById(myId).value = document.getElementById(myId).defaultValue;
            obj.style.color = '#999';
        }
    }

    /**
     * 点击搜索按钮执行的函数
     */
    function search() {
        document.getElementById("searchForm").submit();
    }
</script>

<!--分类菜单-->
<div id="div-menu">
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=文学">文学</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=生活">生活</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=计算机">计算机</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=外语">外语</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=经营">经管</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=励志">励志</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=社科">社科</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=学术">学术</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=少儿">少儿</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=艺术">艺术</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=原版">原版</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=科技">科技</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=考试">考试</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage?category=生活百科">生活百科</a>
    <a href="${pageContext.request.contextPath}/showProductsByPage" style="color:#b4d76d">全部商品目录</a>
</div>

<!--分类搜索-->
<div id="div-search">
    <form id="searchForm" action="${pageContext.request.contextPath}/menuSearch" method="post">
        <table style="width:100%;border-collapse:collapse">
            <tr>
                <td style="text-align:right; padding-right:220px">
                    搜索
                    <input type="text" id="textfield" name="textfield" class="input-table" value="请输入..."
                           onmouseover="this.focus();"
                           onclick="my_click(this, 'textfield');"
                           onBlur="my_blur(this, 'textfield');"/>
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/client/images/searchButton.gif"
                             style="margin-bottom:-4px" onclick="search()"/>
                    </a>
                </td>
            </tr>
        </table>
    </form>
</div>
