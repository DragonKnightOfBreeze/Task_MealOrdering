# 网上订餐系统

学期项目设计。

# 一些问题

## SmartUpload中文乱码问题解决：
  
[360doc](http://www.360doc.com/content/08/1218/14/16915_2150839.shtml)

## 路径问题：
  
`redirect`：只能传入绝对地址  
示例：`reg.getContextPath() + /mealordering/index.jsp`

`requestDispatcher`：可以传入相对于contextPath的以`/`开头的地址，也可以传入相对于当前路径的不以`/`开头的地址  
示例`/mealordering/index.jsp`，`error/unexpected-error.jsp`

`url、href`：同`redirect`

`c:url`：同`requestDispatcher`

`jsp:formward`：同`requestDispatcher`

`location.href`：可以是绝对地址，相对于contextPath的以`/`开头的地址。

## 编码问题：

html、jsp、servlet都使用utf-8使，只需要设置:
```
req.setCharacterEncoding("utf-8");
resp.setCharacterEncoding("utf-8");
resp.setContentType("text/html;charset=utf-8");
```

## 参数传递问题：

只要存在对应参数，并且没有被设置为disable，也没有出现单选/多选但没有选择任一项的情况，  
在servlet中得到的参数（字符串）就不为null，至多也是空字符串。
