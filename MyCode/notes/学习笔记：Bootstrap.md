# 代码片段

**移动设备优先**

为了确保适当的绘制和触屏缩放，需要在 <head> 之中添加 viewport 元数据标签。

```html
<meta name="viewport" content="width=device-width, initial-scale=1">
```

在移动设备浏览器上，通过为视口（viewport）设置 meta 属性为 user-scalable=no 可以禁用其缩放（zooming）功能。

```html
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
```

**排版与链接**

Bootstrap 排版、链接样式设置了基本的全局样式。分别是：

为 body 元素设置 background-color: #fff;
* 使用 @font-family-base、@font-size-base 和 @line-height-base 变量作为排版的基本参数
* 为所有链接设置了基本颜色 @link-color ，并且当链接处于 :hover 状态时才添加下划线
* 这些样式都能在 scaffolding.less 文件中找到对应的源码。

# 未整理

* 响应式类应该表现为div，包含需要实现响应式的元素（table等）。
* 如果要设置input、button等标签的宽度，应该把它们放到类为col-sm-X的div中。
* label和input标签应该成组地放到类为form-group的div中。
* input/textarea等标签应该设置类为form-control。
* 对于form标签，应该设置它们的类为form-inline/form-horizontal，角色为form。
* button应该设置类为btn btn-default/...
* checkbox应该连带文本放到label中，再放到类为checkbox的div中。如果是checkbox-inline则为内联。
* select标签应该设置类为form-control。
* 使用`<p class="form-control-static>...</p>`在表单标签后面放置纯文本。
* 使用placeholder属性为input标签设置鼠标悬停提示。
* 可以为input/fieldset设置disabled属性以禁用。
* 可以对父元素设置类为has-warning/has-error/has-success以启用验证状态（类为form-group的div标签）。
* 可以为input/select标签设置类为input-lg/input-sm来控制显示大小。
* 使用`<span class="help-block>...</span>`为输入框添加帮助文本。
* 非按钮的标签如果想设置为btn类，还需设置role为button。
* 可以在a/button/input标签上设置为btn类。
* 被类为btn-group的div包含的按钮可以实现按钮组。
* 如果想多行显示，单个button标签的外层也要加上这种div。
* 内嵌按钮组可以包含下拉菜单，设置button的类为dropdown-toggle，data-toggle为dropdown，插入类为caret的span，然后在下面插入列表，设置ul的类为dropdown-menu。
* sr-only类：除了屏幕阅读器外，在其他设备上隐藏元素
* sr-only-focusable类：在元素获得焦点时显示。可用于label标签。
* `<span class="caret"></span>`：显示下拉功能
* close类：关闭按钮，可用于button标签。
* `aria-hidden="true"`：主要是帮助残障人士（如失明）使用识读设备（自动读取内容并自动播放出来），播放到带此属性的内容时会自动跳过，以免残障人士混淆！
* 使用 class .show 和 .hidden 来强行设置元素显示或隐藏（包括屏幕阅读器）。

