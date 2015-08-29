Virtual Campus
===

### TOC

* [目录](#toc)
* [任务单](#任务单)
    * [8.30日前完成各自负责模块基础界面](#830日前完成各自负责模块基础界面)
* [基本命名规范](#基本命名规范)
* [代码书写规范](#代码书写规范)
* [注释规范](#注释规范)
* [git 使用方法](#git-使用方法)

===

### 任务单

#### 每天提交日报

#### 8.30日前完成各自负责模块基础界面

包括个人负责模块的所有界面，包括相关按钮的弹出效果等等，业务逻辑与美化不需考虑。

在所有模块合并时会将当前登录用户身份作为参数传递，共有三种身份 学生、教师、管理员，设计界面时需要全部考虑。


===

### 基本命名规范

* 避免难懂的名称，如 xxK8

* 类命名使用 Pascal 大小写处理 (CalculateInvoiceTotal)，其中每个单词的第一个字母都是大写的。

* 函数 & 变量命名，使用 camel 大小写处理 (documentFormatType)，其中除了第一个单词外每个单词的第一个字母都是大写的。构造函数命名与类命名相同。

* 常量命名，不要使用原义数字或原义字符串，而是使用命名常数（MAX_VALUE） ，以便于维护和理解。

### 代码书写规范


* 在括号对齐方式使用以下两种均可

```java
for(i=0; i<100; i++) {

    // code block

}

for(i=0; i<100; i++)
{

    // code block

}

```

* 沿逻辑结构行缩进


```java
if(expression){
    if(expression){

        // code block

    } else {

        // code block

    }
}
```


### 注释规范

**所有 Java 代码，请至少在编写时写明以下注释**

* 某个类之前

```java
/**
 * 类的简单介绍
 * @author      类作者
 */
public class Test {

    // class body

}
```

* 某个方法（函数）前：

```java
/**
 * 函数功能介绍
 * @param  传入参数
 * @return  返回值
 */
public void test(){

    // function body

}
```
具体参照 [javadoc](http://en.wikipedia.org/wiki/Javadoc)


===

### git 使用方法

安装 & 配置教程 [github help](https://help.github.com/articles/set-up-git)

这里有一篇关于如何使用 github 的教程 [GotGitHub](http://www.worldhello.net/gotgithub/index.html) 其中第 [4.1 Fork + Pull模式](http://www.worldhello.net/gotgithub/04-work-with-others/010-fork-and-pull.html) 基本就是我们需要使用的模式。


.gitignore 里为不需要同步至 repo 的配置文件，需要自己手动添加不想同步的文件（不需要提交的文件包括但不
限于编译好的 .class 文件、 .project 等 eclipse 配置文件等等，一句话就是 **只提交自己编写的代码**。）。
