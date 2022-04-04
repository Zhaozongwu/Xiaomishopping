# 整合ssm项目

## 1、如果遇到tomacat终端报404的问题：

（1）当前界面下WEB-INF下的缺少lib文件，将其添加。
![image](https://user-images.githubusercontent.com/40204192/161497756-dc2cea06-25c6-46d8-83a6-81dc2620046f.png)

（2）网络资源文件夹配置错误
![image](https://user-images.githubusercontent.com/40204192/161498194-4c4acc28-c92c-4f8d-878e-e136285d06d3.png)

2、遇到IDEA单元测试报错：Class not found

![image](https://user-images.githubusercontent.com/40204192/161498269-cc163fd4-226b-4157-ab65-2858b5a559bb.png)

## 2、常见报500的错误

版本对应问题
![image](https://user-images.githubusercontent.com/40204192/161498324-719341a1-82aa-4b12-843f-c77ea346727c.png)


## 3、整合过程中的模糊知识概念

#### 1、model.addAttribute() 与request.setAttribute()

二者不同在于方法，但是作用都是：往前台传数据，可以传对象，可以传List,前端通过el表达式${}获取得到，个人偏向于request.setAttribute()，不会发生错误。

### 2、监听器（商品类型为啥放入监听器中）

**作用**：在启动Web容器时，自动装配spring  applicationContext.xml 的配置信息，启动容器时，默认执行它实现的方法。

（监听web服务器的运行，当发生特定的事件时，采取预先设定的处理措施的组件）

比较详细的解释：

[(20条消息) ssm整合之web.xml中监听器的作用_凌晨的路灯的博客-CSDN博客_web.xml监听器的作用](https://blog.csdn.net/qq_47759220/article/details/108773642)

