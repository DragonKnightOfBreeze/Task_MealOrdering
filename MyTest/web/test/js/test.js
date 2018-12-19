//导入
import * as imp from "testImp";

function warn2() {
    imp.warn();
}

function test() {
    alert("hello world");
}

function test2() {
    let w = "world";
    alert`hello ${w}`;
}


let myMap = new Map([["name", "sc"], ["age", "20"]]);

for(let i = 0; i < myMap.size; i++) {
    alert(myMap.key);
}

for(let e in myMap) {
    alert(e.key);
}

//myMap.forEach(function(value){
//    alert(value);
//});

myMap.forEach(value => alert(value));
//const
for(let entry of myMap) {
    alert(entry.key);
}

