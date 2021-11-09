### 内容要求

1. **内容：开发一个简易的幻灯片制作与播放软件**

2. **基本功能：**

       1. 新建幻灯片，幻灯片由不少于一个页面组成
   
       2. 新建一个空白的幻灯片页面
   
       3. 打开或保存整个幻灯片
   
       4. 在幻灯片页面上绘制基本图形
   
       5. 在幻灯片页面上绘制任意直线
   
       6. 添加文字
   
       7. 设定颜色与文字风格
   
       8. 对幻灯片页面上已有的基本图形、线形、文字进行选取和移动
   
       9. 通过鼠标拖动完成上述绘制和添加文字等操作
   

3. **选作功能：**

       1. 幻灯片的全屏播放、翻页
   
       2. 设定画笔、插入图像、修改图像（使用橡皮擦）
   
       3. 图形填充、线型设置
   
       4. 对幻灯片页面上已有的基本图形、线性、文字进行修改
   
       5. 操作的撤销与重组
   
       6. 菜单
   
       7. 其他
   

### 帮助文档

   1. ##### 开发环境和插件选择：

      `Gradle + JavaFx + JPoneiX + JDK 1.8 + Spire.Presentation + IDEA 2020.3.3 (Ultimate Edition)`

      说明：

          1. 按照 `non-modular + idea` 使用 JavaFx，会带来配置修改问题
      
          2. 使用 `JPoneiX` 开源项目优化 UI 界面，更加美观和方便

          3. 使用`Gradle`管理项目更加方便，兼容性更好
      


   2. ##### JavaFX的基本操作指南：

      1\. 官方文档: <a>https://openjfx.io/</a>
         * 基本内容以及示例说明
         * JavaFX的doc文档说明 


      2\. WiKi教程: <a>https://iowiki.com/javafx/javafx_images.html</a>
      
      3\. JavaFX和FXML的关系阐述： <a>https://blog.csdn.net/theonegis/article/details/50181339</a>
      

   3. ##### Java操作PPT的免费组件:

      1\. 官方网址：<a>冰蓝科技 e-iceblue ｜ 您的办公文档开发技术专家 ｜ C#/VB.Net Excel, Word, PowerPoint, PDF, Barcode 组件](https://www.e-iceblue.cn/)</a>
      
      2\. 此组件目前没有免费开源，会带有水印，不过不影响整体的实现
         
      3\. 另外官方没有Gradle方式添加到工程，附上Gradle如何添加本地外包的方法：<a>[Gradle加载本地jar包_m1213642578的专栏-CSDN博客_gradle加载本地jar](https://blog.csdn.net/m1213642578/article/details/52763130?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-1.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-1.no_search_link)</a>
         


   4. ##### JavaFX实现绘图功能的项目：

      1\. GitHub链接：<a>https://github.com/FlyuZ/FYDrawing-JAVAFX</a>
      
      2\. 可以参考实现方法，按照需求更改
      

   5. ##### JPoneiX开源项目：

      1\. GitHub链接：https://github.com/sshahine/JFoenix
      
      2\. 此开源项目美化了JavaFx的组件UI，并且提供了许多便于使用和操作的组件
      

