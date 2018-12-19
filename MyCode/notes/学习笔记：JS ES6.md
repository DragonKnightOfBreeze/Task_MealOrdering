# ES6笔记

**使用ES6实现观察者模式**

```js
// 定义 Set 集合
const queuedObservers = new Set();
// 把观察者函数都放入 Set 集合中
const observe = fn => queuedObservers.add(fn);
// observable 返回原始对象的代理，拦截赋值操作
const observable = obj => new Proxy(obj, {set});

function set(target, key, value, receiver) {
    // 获取对象的赋值操作
    const result = Reflect.set(target, key, value, receiver);
    // 执行所有观察者
    queuedObservers.forEach(observer => observer());
    // 执行赋值操作
    return result;
}
```

**使用模版字符串过滤 HTML 字符串，防止用户输入恶意内容。**

```js
function f(stringArr,...values){
    let result = "";
    for(let i=0;i<stringArr.length;i++){
        result += stringArr[i];
        if(values[i]){
            result += String(values[i]).replace(/&/g, "&amp;")
                .replace(/</g, "&lt;")
                .replace(/>/g, "&gt;");
        }
    }
    return result;
}
let name = '<Amy&MIke>';
f`<p>Hi, ${name}.I would like send you some message.</p>`;
// <p>Hi, &lt;Amy&amp;MIke&gt;.I would like send you some message.</p>
```

**修饰的用法**

```js
class Example {
    @logMethod(1)
    @logMethod(2)
    sum(a, b){
        return a + b;
    }
}
function logMethod(id) {
    console.log('evaluated logMethod'+id);
    return (target, name, descriptor) => alert('excuted logMethod '+id);
}
// evaluated logMethod 1
// evaluated logMethod 2
// excuted logMethod 2
// excuted logMethod 1
```

**模块的使用 export和import**

```js
/*-----export [test.js]-----*/
let myName = "Tom";
let myAge = 20;
let myfn = function(){
    return "My name is" + myName + "! I'm '" + myAge + "years old."
};
let myClass =  class myClass {
    static a = "yeah!";
};
export { myName, myAge, myfn, myClass }
```

```js
/*-----import [xxx.js]-----*/
import { myName, myAge, myfn, myClass } from "./test.js";
console.log(myfn());// My name is Tom! I'm 20 years old.
console.log(myAge);// 20
console.log(myName);// Tom
console.log(myClass.a );// yeah!
```

**模块的使用 as**

```js
/*-----export [test.js]-----*/
let myName = "Tom";
export { myName as exportName }

/*-----import [xxx.js]-----*/
import { exportName } from "./test.js";
console.log(exportName);// Tom

//使用 as 重新定义导出的接口名称，隐藏模块内部的变量
/*-----export [test1.js]-----*/
let myName = "Tom";
export { myName }
/*-----export [test2.js]-----*/
let myName = "Jerry";
export { myName }
/*-----import [xxx.js]-----*/
import { myName as name1 } from "./test1.js";
import { myName as name2 } from "./test2.js";
console.log(name1);// Tom
console.log(name2);// Jerry
```
