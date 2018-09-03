# 梦呓单词

## 项目介绍
该系统使用Spring Boot框架进行开发，使用Maven进行包管理，整合使用了模板引擎Thymeleaf，持久层框架Mybais，PageHelper等插件，数据库使用的是Mysql80。
该系统的定位是一款学习类的网站系统，提供用户丰富的功能以提高其学习英语的兴趣和效率。



## 1.功能介绍
## 1.1    注册账户

进入首页后，点击导航栏右侧的注册按钮：

![img](https://github.com/LastNight1997/myword/raw/master/img/注册按钮.png)

出现如下注册窗口：

![img](https://github.com/LastNight1997/myword/raw/master/img/注册.png)



 

输入，用户名，密码和邮箱账号，用户名和密码不应少于6位。

 

 

## 1.2    用户登录

点击上述导航栏右侧的登录按钮，出现如下窗口：

![img](https://github.com/LastNight1997/myword/raw/master/img/登录.png)

输入用户名和密码，进行登录。

 

## 1.3    设置背诵计划

在首次登录用户时，会出现下述向导页面，可在此页面进行背诵计划的设置：

![img](https://github.com/LastNight1997/myword/raw/master/img/背诵计划.png)


之后，你可以通过导航狼右侧用户名的下拉菜单中，再次进去向导页面修改背诵计划：

![img](https://github.com/LastNight1997/myword/raw/master/img/更改背诵计划.png)






​                  

## 1.4    用户首页

![img](https://github.com/LastNight1997/myword/raw/master/img/用户首页.png)


## 1.5    背诵新词

在首页点击背单词，或者点击导航栏中的背单词标签，进入背单词页面：

![img](https://github.com/LastNight1997/myword/raw/master/img/背诵新词.png)
背诵完后，点击下一个按钮，获取要背诵的下一个单词知道完成今日的背诵任务。同时，你可以点击右上角的爱心按钮，将该单词收藏至你自定义的单词集中。

 

 

## 1.6    复习单词

在首页点击复习单词，或者点击导航栏中的复习标签，进入复习单词页面：

![img](https://github.com/LastNight1997/myword/raw/master/img/复习单词.png)
通过点击认识和不认识按钮，表示你对这次复习的反馈，服务端会将你每次复习的结果记录到数据库中，并根据每个单词被复习的数目、认识的次数、不认识的次数选择下一个要被复习的单词。复习次数越少，不认识次数越多的单词，优先被挑选出来。

 

 

## 1.7    单词考试

在首页点击考试，或者点击导航栏中的考试标签，进入单词考试页面：

![img](https://github.com/LastNight1997/myword/raw/master/img/单词考试.png)


在输入框中输入该单词的正确答案，点击下一个，若输入错误，会提示：

![img](https://github.com/LastNight1997/myword/raw/master/img/错误.png)
若忘记了该单词，您可以通过点击答案按钮，获取该题的答案：

![img](https://github.com/LastNight1997/myword/raw/master/img/提示.png)




​                                       

 

 

## 1.8    查看收藏的单词

点击导航栏的收藏标签，显示收藏的单词：

![img](https://github.com/LastNight1997/myword/raw/master/img/收藏单词.png)


可以点击删除按钮，删除收藏的单词。同时可以通过下方的输入框和添加按钮，添加想要添加的单词。

每页显示的单词数量最多为10个，可以通过点击首页、上一页、下一页、末页切换页面。

 

## 1.9    搜索单词

通过导航栏的搜索按钮，搜索需要查询的单词并显示其介绍：

![img](https://github.com/LastNight1997/myword/raw/master/img/搜索单词.png)
