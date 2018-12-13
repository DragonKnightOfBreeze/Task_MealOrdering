# 第一部分 Web开发前奏

## 第1章 JavaWeb开发简介

### Web发展历程

### 企业开发架构

### Java EE架构

### Java EE核心设计模式

### Struts开发框架



## 第2章 Html、JavaScript简介

### 服务器与浏览器

### Html简介

#### Html元素概览

#### 创建显示Web页

#### 创建表单Web页

### Javascript简介

#### Javascript的基本语法

#### 事件处理

#### window对象

### ［开发实战练习］



## 第3章 Xml简介

### 认识Xml

### Xml解析

#### dom解析操作

```
DOM包中的相关类和接口的继承关系
Node，接口，代表了DOM树的一个节点，节点中可能包含子节点、元素等。
    Document，接口，可代表一个XML文档，表示整棵DOM树的根。
    Element，接口，代表了一个元素，元素中可能包含属性。包含许多操作属性和命名空间的方法。
    Entity，接口，代表了一个已知的实体，无论是否被解析。只读。
    EntityReference，接口。
    Attr，接口，代表了一个属性。
    Commment，接口，代表了一条注释（的内容？），注释形如：<!--comment-->。
    DocumentFragment，接口，可代表一个轻量的、中型的的XML文档。
    DocumentType，接口，代表了一个为文档而定义的实体列表。只读。大多数方法返回NamedNodeMap。
    Notation，接口，代表了一个在DTD中声明的符号，可通过一个名字或未解析的实体的格式声明。只读。
    ProcessingInstruction，接口。
    CharacterData，接口，代表了一个属性和方法的集合。没有任何DOM对象直接对应于这个接口。
        Text，接口，代表了一条文本内容，视为一个节点（可能是唯一的）。
    
NodeList，接口，代表了一个节点的有序集合的抽象，可通过索引到达其中的每一项
NamedNodeMap，接口，代表了一个节点的集合的抽象，可通过名字到达其中的每一项。
NameList，接口，代表了名字和命名空间的有序集合的抽象，可通过索引到达其中的每一项。
TypeInfo，接口，代表了一个元素或熟悉的类型信息。
UserDataHandler，接口，用于处理UserData。
    
DOMConfiguration，接口，代表了一个文档的配置，维护认可的参数。
DOMError，接口。
DOMException，异常。
DOMImplementation，接口，提供了一些独立于任何特定的文档对象模型实例的操作方法。
DOMImplementationList，接口。
DOMImplementationSource，接口。
DOMLocator，接口，描述错误的发生位置。
DOMStringList，接口。

```

##### @范例：使用dom解析xml文件

要解析的XML文件：DOMDemoDoc01.xml

```xml
<AddressList>
    <Name>索拉尔</Name>
</AddressList>
```

Java文件：DOMDemo01.java

```java
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMDemo01 {
	//不处理异常
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//读取指定路径的XML文件
		Document doc = builder.parse("DOMDemoDoc01.xml");

		//查找name的节点
		NodeList nodeList = doc.getElementsByTagName("Name");

		//输出NodeList中第一个子节点中文本节点的内容
		//方法1：得到指定节点列表（name节点列表）下的第一个子节点（name节点本身）的第一个子节点（name节点的文本节点）的值（文本节点的文本内容）
		String name = nodeList.item(0).getFirstChild().getNodeValue();
		//方法2：直接得到指定节点列表（name节点列表）下的第一个子节点（Name节点本身）的文本内容
		//String name2 = nodeList.item(0).getTextContent();
		
		System.out.println("姓名："+name);
	}
}
```

##### @范例：使用dom生成并输出XML文件

要生成的XML文件格式：DOMDemoDoc02.xml

```xml
<AddressList>
    <Person>
        <Name>索拉尔</Name>
        <Contact>白标蜡记石</Contact>
    </Person>
</AddressList>
```

Java文件：examples_ebook.Unit_2.DOMDemo02.java

```java
//生成的文件没有格式排列（适当的缩进）

package Test.unit_1_html5;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DOMDemo02 {
	public static void main(String[] args) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//创建一个新文档
		Document doc = builder.newDocument();

		//建立各个元素
		Element addressList = doc.createElement("AddressList");
		Element person = doc.createElement("Person");
		Element name = doc.createElement("Name");
		Element contact = doc.createElement("Contact");
		//设置元素的文本内容，即为每一个元素添加文本节点
		name.setTextContent("索拉尔");
		contact.setTextContent("白标蜡记石");
		//设置节点关系
		person.appendChild(name);
		person.appendChild(contact);
		addressList.appendChild(person);
		doc.appendChild(addressList);

		//输出到文件中
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		//设置编码（单条Property）
		transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		//建立DOM资源
		DOMSource source = new DOMSource(doc);
		//指定输出的位置
		StreamResult result = new StreamResult(new File("DOMDemo02Doc.xml"));
		//进行输出，不处理错误
		transformer.transform(source,result);
	}
}
```

#### sax解析操作

##### @范例：编写自己的SAX解析器

Java文件：MySAXParser.java（输出到屏幕）
```java
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class MySAXParser extends DefaultHandler {
	/**文档开始*/
	@Override
	public void startDocument()  {
		System.out.println("<?xml version = \"1.0\" encoding = \"UTF-8\" ?>");
	}
	
	/**文档结束*/
	@Override
	public void endDocument()  {
		System.out.println("\n文档读取结束。");
	}
	
	/**元素开始*/
    @Override
    public void startElement(String url, String localName, String name, Attributes attributes)  {
		System.out.print("<");
		//输出元素名称
		System.out.print(name);
		//取得全部属性
		if(atrributes != null) {
			for(int x = 0; x < attributes.getLength(); x++) {
				System.out.print(" "+attributes.getQName(x) + "=\""+attributes.getValue(x) + "\"");
			}
		}
		System.out.print(">");
    }	
    
    /**元素结束*/
    @Override
    public void endElement(String url, String localName, String name)  {
    	//输出元素名称
    	System.out.print("</" +name + ">");
    }
    
    /**取得元素内容*/
    @Override
    public void characters(char[] ch,int start, int length)  { 
    	System.out.print(new String(ch, start, length));
    }
}   
```

##### @范例：使用自己的SAX解析器解析XML文件

要读取的XML文件：SAXDemoDoc.xml

```xml
<AddressList>
    <Person>
        <Name>索拉尔</Name>
        <Contact>白标蜡记石</Contact>
    </Person>
</AddressList>
```

Java文件：SAXDemo.java

```java
import java.io.*;
import javax.xml.parsers.*;
public class SAXDemo {
	public static void main(String[] args) throws Exception {
	  //建立SAX解析工厂
	  SAXParserFactory factory = SAXParserFactory.newInstance();
	  //构造解析器
	  SAXParser parser = factory.newSAXParser();
	  //解析XML
	  parser.parse("SAXDemoDoc.xml",new MySAXParser());
	}
}
```

#### xml解析的好帮手：jdom

##### @范例：使用jdom读取和写入XML文件

准备的XML文件：JDOMDoc.xml

```xml

```

XML读取：JDOMReader.xml

```java

```

XML生成：JDOMWriter.xml

```java

```

#### 最出色的解析工具：dom4j

##### @范例：使用dom4j读取和写入XML文件

准备的XML文件：DOM4JDoc.xml

```xml

```

XML读取：DOM4JReader.xml

```java

```

XML生成：DOM4JWriter.xml

```java

```

### 使用Javascript操作dom



### ［开发实战练习］


## 第4章 tomcat服务器的安装与配置

### Web容器简介

### tomcat简介

### tomcat服务器的下载及配置

#### tomcat下载

#### tomcat安装

#### 服务器配置

### 编写第一个jsp文件

### 交互性



# 第二部分 Web基础开发

## 第5章 jsp基础语法

### jsp注释

### scriptlet

#### 第一种scriptlet（%%）

#### 第二种scriptlet（%!%）

#### 第三种scriptlet（%=%）

### scriptlet标签

### page命令

#### 设置页面的mime

#### 设置文件编码

#### 错误页的设置

#### 数据库连接操作

### 包含命令

#### 静态包含

#### 动态包含

### 跳转命令

### ［实例：用户登录程序实现——基于jsp和jdbc］

#### 创建数据库表



#### 程序实现思路

#### 程序实现


### ［开发实战练习］



## 第6章 jsp内置对象

### jsp内置对象概览

### 4种属性范围

#### page属性范围（pagecontext）

#### request属性范围

#### session属性范围

#### application属性范围

#### 深入研究page属性范围

### request对象

#### 乱码解决

#### 接收请求参数

#### 显示全部的头信息

#### 角色验证

#### 其他操作

### response对象

#### 设置头信息

#### 页面跳转

#### 操作cookie

### session对象

#### 取得session id

#### 登录与注销

#### 判断新用户

#### 取得用户的操作时间

### application对象

#### 取得虚拟目录对应的绝对地址

#### ［范例：网站计数器］

#### 查看application范围的属性

### web安全性与config对象

#### web安全性

#### config对象

### out对象

### pagecontext对象

### ［开发实战练习］



## 第7章 javabean

### javabean简介

### 在jsp中使用javabean

#### web开发的标准目录结构

#### 使用jsp的page指令导入所需要的javabean

#### 使用jsp:usebean指令

### javabean与表单

### 设置属性（jsp:setproperty）

#### 设置指定的属性

#### 指定设置属性的参数

#### 为属性设置具体内容

### 取得属性（jsp:getproperty）

### javabean的保存范围

#### page范围的javabean

#### request范围的javabean

#### session范围的javabean

#### application范围的javabean

### javabean的删除

### ［实例：注册验证］

### dao设计模式

#### dao设计模式简介

#### dao开发

#### jsp调用dao

### ［开发实战练习］



## 第8章 文件上传

### smartupload上传组件

#### 上传单个文件

#### 混合表单

#### 为上传文件自动命名

#### 批量上传

### fileupload

#### 使用fileupload接收上传内容

#### 保存上传内容

#### 开发fileupload组件的专属操作类

#### ［开发实战练习］



# 第三部分 Web高级开发

## 第9章 servlet程序开发

### servlet简介

### 第一个servlet程序

### servlet与表单

### servlet生命周期

### 取得初始化配置信息

### 取得其他内置对象

#### 取得httpsession实例

#### 取得servletcontext实例

### servlet跳转

#### 客户端跳转

#### 服务器端跳转

### web开发模式：mode i与mode ii

#### mode i

#### mode ii：model-view-controller

### ［实例：MVC设计模式应用］

### 过滤器

#### 过滤器的基本概念

#### 实现过滤器

#### 过滤器的应用

### 监听器

#### 对application监听

#### 对session监听

#### 对request监听

#### ［实例：监听器实例——在线人员统计］

### ［开发实战练习］



## 第10章 表达式语言

### 表达式语言简介

### 表达式语言的内置对象

#### 访问4种属性范围的内容

#### 调用内置对象操作

#### 接收请求参数

### 集合操作

### 在mvc中应用表达式语言

### 运算符

### ［开发实战练习］



## 第11章 tomcat数据源

### 数据源操作原理

### 在tomcat中使用数据库连接池

### 查找数据源



## 第12章 jsp标签编程

### 标签编程简介

### 定义一个简单的标签——空标签

### 定义一个有属性的标签

### tagsupport类

### 定义有标签体的标签库

### 开发迭代标签

### bodytagsupport类

### tagextrainfo类和variableinfo类

### 使用bodytagsupport开发迭代输出

### 简单标签

### dynamicattributes接口



## 第13章 jsp标准标签库

### jstl简介

### 安装jstl 1.2

### 核心标签库

#### c:out标签

#### c:set标签

#### c:remove标签

#### c:catch标签

#### c:if标签

#### c:choose、c:when、c:otherwise标签

#### c:foreach标签

#### c:fortokens标签

#### c:import标签

#### c:url标签

#### c:redirect标签

### 国际化标签库

#### fmt:setlocale标签

#### fmt:requestencoding标签

#### 读取资源文件

#### 数字格式化标签

#### 日期时间格式化标签

#### 设置时区

### sql标签库

#### sql:setdatasource标签

#### 数据库操作标签

#### 事务处理

### xml标签库

#### xpath简介

#### x:parse标签

#### x:out标签

#### x:set标签

#### x:if标签

#### x:choose、x:when、x:otherwise标签

#### x:foreach标签

### 函数标签库

### ［开发实战练习］



## 第14章 ajax开发技术

### ajax技术简介

### xmlhttprequest对象

### 第一个ajax程序

### 异步验证

### 返回xml数据

### ［开发实战练习］



# 第四部分 框架开发

## 第15章 structs基础开发

### struts简介

### 配置structs开发环境

### 开发第一个structs程序

### structs工作原理

### 深入structs应用

### ［开发实战练习］



## 第16章 structs常用标签库

### structs标签库简介

### bean标签

#### bean:define标签

#### bean:size标签

#### 资源访问标签

#### bean:write标签

#### bean:include标签

#### bean:include标签

#### 国际化与bean:message标签

### logic标签

#### logic:present和logic:notpersent标签

#### logic:empty和logic:notempty标签

#### 关系运算标签

#### logic:iterate标签

#### logic:redirect标签（重定向）

### html标签

#### html:form标签

#### html:text和html:password标签

#### html:radio标签

#### html:textarea标签

#### html:hidden标签

#### 按钮标签

#### ［实例：编写基本表单］

#### 复选框标签

#### 下拉列表框

### ［开发实战练习］



## 第17部分 structs高级开发

### structs多人开发

### token

### 文件上传

### 动态actionform

### action深入

#### forwardaction

#### includeaction

#### dispatchaction

### 验证框架

### ［开发实战练习］
