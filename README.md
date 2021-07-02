# ArtWorld

### Acknowledge

#### 图片来源

@灵雨_Yui 

>   https://weibo.com/u/6700407503
>
>   https://space.bilibili.com/500794220

@重回汉唐旗舰店

#### login页面背景滚动实现：

https://blog.csdn.net/lzw398756924/article/details/106101545

https://github.com/ziwenL/SrcScrollFrameLayout

#### 服务器端搭建实现登录、注册

1.  租用云服务器（镜像：Ubuntu）

2.  云服务器内安装jdk、tomcat、MySQL

    MySQL 需要grant all privilege to 'root'@'%' identified by 123456

    root：登录名  123456：登录密码

    %：所有主机

3.  本机电脑安装Royal tsx（或者Termius也可以，但是我的Termius到期了……，Royal tsx传输文件是免费的！）连接云服务器，也可以从本地直接向云服务器丢文件。

    >   我的电脑是Mac，所以镜像装的是Ubuntu，并且装了远程连接。但如果是Windows的话，镜像貌似可以直接装成Windows，然后直接丢文件，不需要远程连接🤔。

4.  注册、登录功能实现

    >   https://blog.csdn.net/qq_41117896/article/details/109017557

