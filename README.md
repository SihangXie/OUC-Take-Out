# 听海餐厅(瑞吉外卖)

*谢斯航*

------

<div align=center><img src="https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/logo-login.png" width="400"></div>


# 前言
- 本项目《听海餐厅》是基于黑马《瑞吉外卖》改名而来，感谢黑马贡献的高质量视频教程。
- 本项目将短信登录改造成了邮箱登录。
- 为了避免各位小伙伴面试时被面试官嘲讽【你们项目组还挺大啊】的尴尬场面，将原项目改名成了《听海餐厅》。

> - 黑马《瑞吉外卖》视频教程连接：[黑马程序员Java项目实战《瑞吉外卖》，轻松掌握springboot + mybatis plus开发核心技术的真java实战项目](https://www.bilibili.com/video/BV13a411q753?p=1&vd_source=ff1f70c9f263d908963cefc562648f80)
> - 本文项目完整源码链接：[https://github.com/SihangXie/OUC-Take-Out](https://github.com/SihangXie/OUC-Take-Out)





# 一、 软件开发整体介绍

- 本项目是一个单体架构，没有使用微服务。



## 1. 软件开发流程

![image-20220928210512515](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220928210512515.png)



## 2. 角色分工

- 项目经理：对整个项目负责，任务分配、把控进度。
- 产品经理：进行需求调研，输出需求调研文档、产品原型等。
- UI设计师：根据产品原型输出界面效果图。
- 架构师：项目整体架构设计、技术选型等。
- 开发工程师：代码实现。
- 测试工程师：编写测试用例，输出测试报告。
- 运维工程师：软件环境搭建、项目上线。



## 3. 软件环境

- 开发环境 (development)：开发人员在开发阶段使用的环境，一般外部用户无法访问。
- 测试环境 (testing)：专门给测试人员使用的环境，用于测试项目，一般外部用户无法访问。
- 生产环境 (production)：即线上环境，正式提供对外服务的环境。





# 二、 项目介绍



## 1. 项目介绍



### 1.1 B端和C端

- 本项目 (瑞吉外卖) 是专门为餐饮企业 (餐厅、饭店) 定制的一款软件产品，包括系统管理后台和移动端应用两部分。
  - 其中系统管理后台主要提供给餐饮企业内部员工使用，可以对餐厅的菜品、套餐、订单等进行管理维护。
  - 移动端应用主要提供给消费者使用,可以在线浏览菜品、添加购物车、下单等。



### 1.2 开发周期

- 本项目共分为3期进行开发：
- 第一期主要实现基本需求，其中移动端应用通过 H5 实现，用户可以通过手机浏览器访问。
- 第二期主要针对移动端应用进行改进，使用微信小程序实现，用户使用起来更加方便。
- 第三期主要针对系统进行优化升级，提高系统的访问性能。





## 2. 产品原型展示



### 2.1 后台管理登录界面
![在这里插入图片描述](https://img-blog.csdnimg.cn/9ed9e4659c62477ca33e7e706273cda2.png)
<br/>

### 2.2 后台管理界面展示
![在这里插入图片描述](https://img-blog.csdnimg.cn/3e4c0849349c4c8cb79654b78aaaf69b.png)

<br/>

### 2.3 用户端登录界面展示
![在这里插入图片描述](https://img-blog.csdnimg.cn/92821f1b6ade42c6b2e1967dda162aa5.png)
<br/>

### 2.4 邮件验证码展示
![在这里插入图片描述](https://img-blog.csdnimg.cn/a79ac48c531a478ebdce9d42fffa93e0.png)



### 2.5 用户端界面展示
![在这里插入图片描述](https://img-blog.csdnimg.cn/aaa4156f8f554123be7849792af4c918.png)



## 3. 技术选型

![image-20220928212632409](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220928212632409.png)



## 4. 功能架构

![image-20220928212937701](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220928212937701.png)





## 5. 角色

- 后台系统管理员：登录后台管理系统，拥有后台系统中的所有操作权限。
- 后台系统普通员工：登录后台管理系统，对菜品、套餐、订单等进行管理。
- C端用户：登录移动端应用，可以浏览菜品、添加购物车、设置地址、在线下单等。



# 三、 开发环境搭建



## 1. 数据库环境搭建



### 1.1 数据库设计

- 本项目不涉及到微服务架构，所有模块的数据表都统一放在一个数据库中。

- 因此只需创建一个数据库，命名为 `reggie` 。

  ```sql
  CREATE DATABASE IF NOT EXISTS reggie CHARACTER SET 'utf8';
  ```



### 1.2 数据表设计

- 根据功能划分为不同模块，我根据模块的不同创建不同的数据表。

  |     数据表     | 描述 |             难点             |
  | :------------: | :--: | :--------------------------: |
  | 后台系统用户表 |      | 该数据库的root用户就是管理员 |
  |   C 端用户表   |      |                              |
  |     订单表     |      |                              |
  |     菜品表     |      |                              |
  |     地址表     |      |                              |
  |     套餐表     |      |                              |

- 很不幸，我想破脑袋也只能想到划分为 6 张表。但老师对该项目却划分出 11 张表。

- 导入老师设计好的数据表：

  ![image-20220929084924257](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929084924257.png)

  ![image-20220929085033314](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929085033314.png)

- 老师设计数据表的思路：

  |    数据表     |                    描述                    |
  | :-----------: | :----------------------------------------: |
  | address_book  |                  地址簿表                  |
  |   category    | 菜品和套餐的分类表 (荤菜、素菜、周一套餐 ) |
  |     dish      |                   菜品表                   |
  |  dish_flavor  |               菜品口味关系表               |
  |   employee    |             后台系统普通员工表             |
  | order_detail  |                 订单明细表                 |
  |    orders     |                   订单表                   |
  |    setmeal    |                   套餐表                   |
  | setmeal_dish  |               套餐菜品关系表               |
  | shopping_cart |        购物车表 (感觉增删会很频繁)         |
  |     user      |                 C 端用户表                 |





## 2. Spring Boot项目搭建

- 本项目使用的 IDEA 版本为 2022.2.2 Ultimate 。



### 2.1 检查Maven目录与本地仓库

- 创建前首先确保自己的 Maven 软件和 Maven 仓库已经与 IDEA 关联好：

  ![image-20220929091458351](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929091458351.png)

  ![image-20220923082511428](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220923082511428.png)



### 2.2 创建Spring Boot项目

- 按下图设置：

  ![image-20220929092714447](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929092714447.png)

- 导入依赖，有些依赖如 MyBatis-Plus 和 Druid 依赖没有被官方收录，我们先导入下图的三个：

  ![image-20220929093310474](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929093310474.png)

  

### 2.3 整合第三方依赖

- 打开项目的 `pom.xml` 文件，加入以下依赖：

  ```xml
  <!-- MyBatis-Plus -->
  <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-boot-starter</artifactId>
      <version>3.4.3</version>
  </dependency>
  
  <!-- Lombok官方收录无需写版本号 -->
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
  </dependency>
  
  <!-- Fastjson：将对象转成json -->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.76</version>
  </dependency>
  
  <!-- 通用语言包 -->
  <dependency>
      <groupId>commons-lang</groupId>
      <artifactId>commons-lang</artifactId>
      <version>2.6</version>
  </dependency>
  
  <!-- Druid数据库连接池 -->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.2.12</version>
  </dependency>
  ```

- 牢记每次修改完 `pom.xml` 配置文件都必须按 Shift + Ctrl + O 来刷新 Maven，Maven 才能帮你下载并导入依赖。

  ![image-20220929094701954](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929094701954.png)



### 2.4 编写Spring Boot配置文件

- 把 Spring Boot 默认的配置文件 `application.properties` 修改为 `application.yml` ：

  ![image-20220929095004437](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929095004437.png)

- 在 `application.yml` 中添加如下配置：

  ```yaml
  # 配置服务器端口
  server:
    port: 8080
  
  # 配置Druid数据库连接池
  spring:
    application:
      # 应用名称 (可选)
      name: reggie_take_out
    datasource:
      druid:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/reggie?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
        username: root
        password: 你的数据库密码
  
  # 配置Mybatis-Plus
  mybatis-plus:
    configuration:
      # 在映射实体或者属性时，将数据库中表名和字段名中的下划线去掉，按照驼峰命名法映射
      # 例如：属性名映射 user_name --> userName
      # 例如：类名映射 address_book --> AddressBook
      map-underscore-to-camel-case: true
       # 开启MP运行日志
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    global-config:
      db-config:
        id-type: ASSIGN_ID
  ```



### 2.5 运行Spring Boot启动类

- 打开 `src/main/java/edu/ouc/ReggieTakeOutApplication.java` ，添加 Lombok 提供的 `@Slf4j` 注解，方便输出日志来调试：

  ```java
  @Slf4j  // 日志
  @SpringBootApplication  // Spring Boot启动类
  public class ReggieTakeOutApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(ReggieTakeOutApplication.class, args);
          // 打印Slf4j日志
          log.info("项目启动成功");
      }
  }
  ```

- 运行 `ReggieTakeOutApplication.java`  ：

  ![image-20220929100739228](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929100739228.png)

- 看到如图所示的输出，就说明你的 Spring Boot 开发环境已经搭建好了。



### 2.6 导入前端代码

- 本项目以后端开发为主要学习目标，前端代码已经提供好，直接加载即可。

  ![image-20220929101229109](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929101229109.png)

- 粘贴到 `src/main/resources/static` 目录下：

  ![image-20220929101454164](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929101454164.png)

- 重启项目，在浏览器中输入 `http://localhost:8080/backend/index.html` :

  <img src="https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929101731807.png" alt="image-20220929101731807" style="zoom:50%;" />

- 看到此页面说明前端代码已成功导入。



# 四、 后台登录退出功能开发



## 1. 后台登录功能开发

- 本章从后台管理页面的登录功能开始。



### 1.1 需求分析



#### ① 请求分析

- 先来查看后台登录的前端页面，浏览器地址栏输入 `http://localhost:8080/backend/page/login/login.html` ：

  ![image-20220929103500230](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929103500230.png)

- 按 F12 打开浏览器的控制台，点击 “登录” 按钮，查看浏览器是以何种方式向服务器发送请求的：

  ![image-20220929103839662](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929103839662.png)

- 可以看到请求方式是 POST ，请求 URL 是 `http://localhost:8080/employee/login` 。由于我们还没写对应的 Controller ，报 404 是很正常的。

- 按照 Spring Boot 的开发思路，我们应该按照：数据层 (Mapper) –> 服务层 (Service) –> 表现层 (Controller) 三步走来开发。

  ![image-20220929104250376](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929104250376.png)



#### ② 数据库分析

- 后台登录功能对应的数据表为 `employee` ，其表结构如下：

  ```sql
  DESC employee;
  ```

  ![image-20220929105036254](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929105036254.png)

- 对数据表中的字段进行逐一分析：

  |    字段     |                             作用                             |
  | :---------: | :----------------------------------------------------------: |
  |     id      | 员工编号，主键，为什么不用自增？可能使用了MyBatis-Plus的雪花算法 |
  |    name     |                           员工姓名                           |
  |  username   |          登录账号，加了唯一约束，登录账号不允许重复          |
  |  password   |                           登录密码                           |
  |    phone    |                           手机号码                           |
  |     sex     |                             性别                             |
  |  id_number  |                          身份证号码                          |
  |   status    |                     员工状态 (禁用/可用)                     |
  | create_time |                           创建时间                           |
  | update_time |                           修改时间                           |
  | create_user |                   创建人是谁，以员工ID记录                   |
  | update_user |                   修改人是谁，以员工ID记录                   |

- 目前数据表 `employee` 仅有一条记录，就是后台管理员的记录：

  ![image-20220929191621378](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929191621378.png)



#### ③ 前端代码分析

- 打开 `src/main/resources/static/backend/page/login/login.html` ，这是后台管理系统的登录页面。

- 其中，我们后端工程师需要关心的最核心的东西就是：**前后端数据交换的统一格式** (也称前后端协议) 。具体来说，是后端响应给前端的数据格式。就是下面这段代码：

  ```javascript
  methods: {
      async handleLogin() {
          this.$refs.loginForm.validate(async (valid) => {
              if (valid) {
                  this.loading = true
                  let res = await loginApi(this.loginForm)
                  if (String(res.code) === '1') { // code：状态，1表示登录成功
                      localStorage.setItem('userInfo', JSON.stringify(res.data))	// data：数据，这里指的是账号和密码
                      window.location.href = '/backend/index.html'
                  } else {	//登录失败
                      this.$message.error(res.msg)	// msg：登录失败提示信息
                      this.loading = false
                  }
              }
          })
      }
  }
  ```

- 从上面这段登录代码中可以看出来，这里和前端工程师约定好的前后端数据交换的统一格式应当包含 3 部分：

  | 协议 |          描述           |
  | :--: | :---------------------: |
  | code |    状态，1 表示成功     |
  | data |       传递的数据        |
  | msg  | 操作失败/成功的提示信息 |

  json 格式如下所示：

  ```json
  res{
      "code":1,
      "data":{
      	"username":"admin",
          "password":"123456"
  	},
      "msg":"登录成功/登录失败"
  }
  ```

  



### 2. 代码编写



#### 2.1 创建实体类

- 创建数据表 `employee` 的实体类 `Employee.java` 。并用 Lombok 快速生成 Getter 、Setter 、toString() 、equals() 等。

- 其中的属性与数据表 `employee` 的字段一一对应。

- 创建 `src/main/java/edu/ouc/entity/Employee.java` ：

  ```java
  @Data
  public class Employee {
      private static final Long serialVersionUID = 1L;// 序列化ID
      private Long id;
      private String name;
      private String username;
      private String password;
      private String phone;
      private String sex;
      private String idNumber;
      private Integer status;
      private LocalDateTime createTime;
      private LocalDateTime updateTime;
      
      @TableField(fill = FieldFill.INSERT)
      private Long createUser;
      
      @TableField(fill = FieldFill.INSERT)
      private Long updateUser;
  }
  ```



#### 2.2 数据层开发

- 登录只涉及到对数据库的查询操作。即，根据账户名 `username` 查询密码 `password` 。

- 创建数据层、服务层和表现层对应的文件夹。

  ![image-20220929131314364](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929131314364.png)



##### ① 创建数据层接口

- 创建 `src/main/java/edu/ouc/mapper/EmployeeMapper.java` 。

- 继承 MyBatis-Plus 的 `BaseMapper<T>`  泛型接口，添加 `@Mapper` 注解。就能获取父类 `BaseMapper<T>`  中已经写好的增删改查方法。

  ```java
  @Mapper
  public interface EmployeeMapper extends BaseMapper<Employee> {
  }
  ```



#### 2.3 服务层开发

##### ① 创建服务层接口

- 创建 `src/main/java/edu/ouc/service/IEmployeeService.java` 。

- 我们可以让服务层接口继承 MyBatis-Plus 的 `IService<T>` 泛型接口来进行快速开发。

  ```java
  public interface IEmployeeService extends IService<Employee> {
  }
  ```

- 继承 `IService<T>` 后能获取很多通用的增删改查方法：

  ![image-20220929132949762](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929132949762.png)



##### ②创建服务层接口实现类

- 创建 `src/main/java/edu/ouc/service/impl/IEmployeeServiceImpl.java` 。
- 我们可以让其先继承 MyBatis-Plus 的 `ServiceImpl<M,T>` ，再实现 `IEmployeeService.java` 。其中，`<M,T>` 中的 M 指的是对应的 DAO 接口，T 指的是实体类。这样，我们就无需实现 `IEmployeeService.java` 中全部的方法，而是根据需要，既可以使用提供的基础 CRUD 方法，也可以自定义新的方法。



#### 2.4 表现层开发



##### ① 创建前后端统一格式协议类R

- 创建 `src/main/java/edu/ouc/common/R.java` 。

  ```java
  @Data
  public class R<T> {
      private Integer code;
      private T data;
      private String msg;
      private Map map = new HashMap();// 动态数据
  
      public R() {
      }
  
      // 适用于成功
      public static <T> R<T> success(T object) {
          R<T> r = new R<>();
          r.code = 1;
          r.data = object;
          return r;
      }
  
      // 适用于失败
      public static <T> R<T> error(String msg) {
          R<T> r = new R<>();
          r.code = 0;
          r.msg = msg;
          return r;
      }
  
      // 操作动态数据的
      public R<T> add(String key, Object value) {
          this.map.put(key, value);
          return this;
      }
  }
  ```

- 在表现层所有方法的返回值类型都为 `R` ，这是与前端约定好的响应格式。

  | 协议 |                描述                 |
  | :--: | :---------------------------------: |
  | code | 状态，1 表示成功，0和其他数字为失败 |
  | data |             传递的数据              |
  | msg  |         操作失败的提示信息          |



##### ② 登录方法的逻辑

- 编写代码前，一定要把逻辑理顺了再开始敲代码。

- 我的逻辑：

  1. 前端发送 POST 请求过来的表单，其中的登录账号 `usernamr` 和密码 `password` 封装成一个 Employee 对象。
  2. employee 对象作为入参传入表现层的 `login()` 方法。
  3. 根据传入的 employee 的 username 属性去数据库中查询。如果存在，则返回该对象；如果不存在，说明用户不存在，登陆失败。
  4. 对数据库查询获得的对象的密码先进行 md5 解密。比较两个密码是否相同，如果相同，登陆成功；如果不相同，登录失败。

- 老师的逻辑：

  1. 将页面提交的密码 password 进行 md5 加密处理。
  2. 根据页面提交的用户名 username 查询数据库。
  3. 如果没有查询到，则返回登录失败结果。
  4. 密码比对，如果不一致，则返回登录失败结果。
  5. 查看员工状态，如果为已禁用状态，则返回员工已禁用结果。
  6. 登录成功，将员工 id 存入 Session 保存作用域，并返回登录成功结果。

  ![image-20220929154420537](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929154420537.png)



##### ③ 创建Controller

- 创建 `src/main/java/edu/ouc/controller/EmployeeController.java` 。

- 由于前端页面登录时，向后端请求的 URL 地址是 `http://localhost:8080/employee/login` ：

  ![image-20220929103839662](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929103839662.png)

- 因此，表现层 `EmployeeController` 的类上面要添加请求映射注解 `@RequestMapping("/employee")` ，登录的方法上添加 POST 请求映射注解 `@PostMapping("/login")` 。

- `EmployeeController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/employee")
  public class EmployeeController {
  
      // 注入业务层
      @Autowired
      private IEmployeeService empService;
  
      // 后台员工登录登录方法
      @PostMapping("/login")
      public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
          String username = employee.getUsername();
          String password = employee.getPassword();
          // 1.对前端传入的密码进行md5加密
          password = DigestUtils.md5DigestAsHex(password.getBytes());
          // 2.根据前端传入的账号去数据库中查询
          // 2.1 创建查询条件对象
          LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
          // 2.2 使用MP的等值查询eq
          lqw.eq(Strings.isNotEmpty(username), Employee::getUsername, username);
          // 2.3 根据lqw的条件进行等值查询
          Employee emp = employeeService.getOne(lqw);
  
          // 3.判断查询到的员工是否为空
          if (emp == null) {
              return R.error("用户不存在，登录失败");
          }
          // 4.密码比对
          if (!password.equals(emp.getPassword())) {
              return R.error("密码错误，登录失败");
          }
          // 5.查看员工状态是否禁用
          if (emp.getStatus() != 1) {
              return R.error("该账号已禁用");
          }
          // 6.将员工ID存放在Session保存作用域中
          request.getSession().setAttribute("employee", emp.getId());
          return R.success(emp);
      }
  }
  ```





### 3. 功能测试



#### 3.1 修改前端超时时间

- 由于我们经常需要 Debug ，把因此要把前端请求超时时间设置得大一些，以方便我们调试。

- 打开 `src/main/resources/static/backend/js/request.js` 。第 8 行，单位是毫秒，默认值 10000 ，即 10s 。修改为 1000000 ：

  ```javascript
  // 超时
  timeout: 1000000
  ```



#### 3.2 测试登录

- 启动服务，浏览器访问 `http://localhost:8080/backend/page/login/login.html` ：

  ![image-20220929193639043](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929193639043.png)

  ![image-20220929193712700](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929193712700.png)

- 登录成功。



#### 3.3 查看Session保存作用域中的数据

- 我们在登录方法 `login()` 中将查询到的员工 ID 保存在Session保存作用域中。我们可以按 F12 来查看保存作用域中的内容：

  ![image-20220929194526273](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929194526273.png)



### 4. 个人想法

- 后台登录页面仍需补充的功能有：
- 手机短信验证码登录；
- 注册账号；
- 忘记密码；





## 2. 后台退出登录功能开发



### 1. 需求分析



#### 1.1 请求分析

- 登录成功后，进入后台管理的主页，当点击右上角的图标后，就会退出登录：

  ![image-20220930084930923](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930084930923.png)

- 按 F12 打开浏览器的控制台，点击退出图标，查看浏览器是以何种方式向服务器发送请求的：

  ![image-20220930085037396](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930085037396.png)

- 可以看到请求方式是 POST ，请求 URL 是 `http://localhost:8080/employee/logout` 。由于我们还没写对应的 Controller 中的退出登录方法，报 404 是很正常的。

- 可是，退出登录不需要提交任何表单啊，为什么用 POST 请求方式呢？



#### 1.2 数据库分析

- 退出登录不需要与数据库交互？



#### 1.3 前端代码分析

- 打开 `src/main/resources/static/backend/index.html` ，这是后台管理系统的主页。
  ```javascript
  logout() {
      logoutApi().then((res)=>{
          if(res.code === 1){ // 当后端返回code=1时退出登录成功
              localStorage.removeItem('userInfo') // 删除保存作用域中的数据
              window.location.href = '/backend/page/login/login.html' // 跳转回登录页面
          }
      })
  }
  ```

- 因此，我们因该在表现层的 `logout()` 方法中返回一个属性 code = 1 的 R 。
  



### 2. 代码实现

- 由于针对 Employee 的数据层、业务层和表现层都已经在上一节写好了。因此直接在表现层 `EmployeeController` 写退出登录的方法即可。

- `EmployeeController.java` ：

  ```java
  // 后台员工退出登录方法
  @PostMapping("/logout")
  public R<String> logout(HttpServletRequest request) {
      // 1.清除Session保存作用域中保存的数据
      request.getSession().removeAttribute("employee");
      // 2.返回结果
      return R.success("退出成功");
  }
  ```



### 3. 功能测试

- 重启服务，浏览器访问 `http://localhost:8080/backend/index.html` ：

  ![image-20220930091724786](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930091724786.png)

- 点击右上角的图标，退出登录：

  ![image-20220930084930923](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930084930923.png)

- 自动跳转回登录页，且 Session 保存作用域中的数据被删除：

  ![image-20220930091847999](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930091847999.png)

  



## 3. 完善登录功能



### 1. 需求分析

- 目前登录有个 Bug ，就是即使用户不登陆，浏览器地址栏直接输入 `http://localhost:8080/backend/index.html` 也能直接访问后台管理的主页。这是极其不合理的。
- 我们需要完善成：必须登录才能进入主页，没有登录就强制跳转到登录页面。
- 这个功能使用过滤器或者拦截器来实现，在过滤器或者拦截器中判断用户是否已经完成登录，如果没有登录就强制跳转到登录页面。



### 2. 代码实现

- 这里我们选择过滤器，其实现步骤为：
  1. 创建自定义过滤器 `LoginCheckFilter` ；
  2. 在启动类上加入 Servlet 组件扫描注解 `@ServletComponentScan` ；
  3. 完善过滤器的处理逻辑。



#### 2.1 创建过滤器

- 我们先不实现过滤器的具体处理逻辑，先把上述前 2 个步骤先搭建起来。

- 创建 `src/main/java/edu/ouc/filter/LoginCheckFilter.java` 。

  ```java
  // 过滤器注解
  // filterName：过滤器名称，可以随便起
  // urlPatterns：想要拦截的URL地址，/*表示拦截所有请求
  @WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
  @Slf4j
  public class LoginCheckFilter implements Filter {
      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          // 强转
          HttpServletRequest request = (HttpServletRequest) servletRequest;
          HttpServletResponse response = (HttpServletResponse) servletResponse;
          // 打印日志
          log.info("拦截到请求：{}", request.getRequestURI());
          // 放行
          filterChain.doFilter(request, response);
      }
  }
  ```

- 然后在启动类 `ReggieTakeOutApplication.java` 中加入 Servlet 组件扫描注解 `@ServletComponentScan` ：

  ```java
  @Slf4j  // 日志
  @SpringBootApplication  // Spring Boot启动类
  @ServletComponentScan   // Servlet组件扫描，扫描过滤器
  public class ReggieTakeOutApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(ReggieTakeOutApplication.class, args);
          // 打印Slf4j日志
          log.info("项目启动成功");
      }
  }
  ```

- 重启服务，浏览器访问 `http://localhost:8080/backend/index.html` ，IDEA 控制台上就能看到所有拦截到的请求：

  ![image-20220930100830426](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930100830426.png)



#### 2.2 完善过滤器处理逻辑

1. 获取本次请求的 URL ；
2. 判断本次请求是否需要处理；
3. 如果不需要处理，则直接放行；
4. 判断登录状态，如果已登录，则直接放行；
5. 如果未登录，则返回未登录结果。

![image-20220930102004978](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930102004978.png)



##### ① 分析前端拦截器

- 每个 HTML 页面都引入了 `src/main/resources/static/backend/js/request.js` 这个 JS 文件。里面定义了一个前端的拦截器：

  ```javascript
  // 响应拦截器
  service.interceptors.response.use(res => {
          if (res.data.code === 0 && res.data.msg === 'NOTLOGIN') {// 返回登录页面
              console.log('---/backend/page/login/login.html---')
              localStorage.removeItem('userInfo')
              window.top.location.href = '/backend/page/login/login.html'
          } else {
              return res.data
          }
      },
      error => {...}
  )
  ```

- 当这个前端拦截器接收到从后端发来的 R 格式数据，如果 R 的属性 `code` 等于 0 且 `msg` 为 “NOTLOGIN” 时，就会跳转至登录页面。



##### ② 后端过滤器

- 打开 `src/main/java/edu/ouc/filter/LoginCheckFilter.java` ：

  ```java
  // 过滤器注解
  // filterName：过滤器名称，可以随便起
  // urlPatterns：想要拦截的URL地址，/*表示拦截所有请求
  @WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
  @Slf4j
  public class LoginCheckFilter implements Filter {
  
      // Spring框架提供的路径匹配器，支持通配符
      public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
  
      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
          // 强转
          HttpServletRequest request = (HttpServletRequest) servletRequest;
          HttpServletResponse response = (HttpServletResponse) servletResponse;
  
          // 1.获取本次请求的URL
          String requestURI = request.getRequestURI();    // /backend/index.html
  
          // 2.定义不需要拦截的URL地址数组
          String[] urls = new String[]{
                  "/employee/login",  // 登录页面
                  "/employee/logout", // 退出登录
                  "/backend/**",      // 后台页面的页面的静态资源
                  "/front/**"         // 移动端页面的静态资源
          };
  
          // 3.判断本次请求URL是否需要拦截
          Boolean check = check(urls, requestURI);
  
          // 4.如果check为true则不需要处理，直接放行
          if (check) {
              filterChain.doFilter(request, response);
              return;
          }
  
          // 5.如果需要处理，则判断登录状态
          if (request.getSession().getAttribute("employee") != null) {
              // 能进入说明已经登录，直接放行
              filterChain.doFilter(request, response);
              return;
          }
  
          // 6.走到这里就是没登录
          // 向浏览器响应一个流，让前端读到R里面的数据
          response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
      }
  
      // 核查请求URL是否在放行URL数组中，检查本次请求是否需要放行
      private Boolean check(String[] urls, String requestURI) {
          for (String url : urls) {
              boolean match = PATH_MATCHER.match(url, requestURI);
              if (match) {
                  return true;
              }
          }
          // 循环完了都匹配不上就返回false
          return false;
      }
  }
  ```



### 3. 功能测试

略。



# 五、 员工管理业务开发



## 1. 新增员工



### 1. 需求分析

#### 1.1 请求分析

- 在后台主页，右上方有 ”+添加员工“ 的按钮，点击直接进入添加员工的页面：

  ![image-20220930124631255](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930124631255.png)

- 随便填点数据，点击 ”保存“ 查看前端是以何种请求方式请求后端，并查看是请求后端哪一个 URL 。

  ![image-20220930124933509](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930124933509.png)
  
  ![image-20220930125015690](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930125015690.png)
  
- 可以看到请求方式是 POST 请求，请求URL 是 `/employee` 。
  
- 向后端传递的数据如下所示：

  ![image-20220930125634784](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930125634784.png)



#### 1.2 数据库分析

- 需要向数据库插入一条记录。

  ![image-20220929105036254](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220929105036254.png)

- 其中，字段 `id` 由 MyBatis-Plus 的雪花算法自动生成，不需要我们设置；字段 `username` 有唯一约束不能重复，在后端代码中注意返回用户名已存在的提示；字段 `status` 默认值为 1 不用设置。

- 前端传送来的数据只有字段 `name` 、`username` 、`phone` 、`sex` 、`id_number` 。因此，剩下没有涉及到的字段都需要我们在表现层的 `EmployeeController.java` 的添加员工方法中设置。

- 其中，字段 `password` 我使用默认密码为身份证后 6 位为初始密码，这也非常常用。



#### 1.3 前端代码分析

- 添加员工的页面在 `src/main/resources/static/backend/page/member/add.html` 。

  ```javascript
  submitForm(formName, st) {
      this.$refs[formName].validate((valid) => {
          if (valid) {
              if (this.actionType === 'add') {
                  const params = {
                      ...this.ruleForm,
                      sex: this.ruleForm.sex === '女' ? '0' : '1'
                  }
                  addEmployee(params).then(res => {
                      if (res.code === 1) {
                          this.$message.success('员工添加成功！')
                          if (!st) {
                              this.goBack()
                          } else {
                              this.ruleForm = {...}
                          }
                      } else {
                          this.$message.error(res.msg || '操作失败')
                      }
                  }).catch(err => {
                      this.$message.error('请求出错了：' + err)
                  })
                  ...
  ```

- 当后端返回 `code` = 1 时，才向用户表示添加成功。



### 2. 代码实现



#### 2.1 我的代码

- `EmployeeController.java` 。我自己写的第一版代码：

  ```java
  // 新增员工功能
  @PostMapping
  public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
  
      // 1.检验账号username是否已存在
      String username = employee.getUsername();
      LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
      lqw.eq(Employee::getUsername, username);
      Employee emp = empService.getOne(lqw);
      if (emp != null) {
          return R.error("账号已存在，添加失败");
      }
  
      // 2.设置创建人ID
      employee.setCreateUser((Long) request.getSession().getAttribute("employee"));
  
      // 3.设置最后修改人ID
      employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
  
      // 4.设置初始密码为身份证后6位，并经过MD5加密
      String idNumber = employee.getIdNumber();
      String password = DigestUtils.md5DigestAsHex(idNumber.substring(idNumber.length() - 6).getBytes());
      employee.setPassword(password);
  
      // 5.设置创建时间
      employee.setCreateTime(LocalDateTime.now());
  
      // 6.设置创建时间
      employee.setUpdateTime(LocalDateTime.now());
  
      // 7.调用业务层保存到数据库中
      empService.save(employee);
      return R.success("添加成功");
  }
  ```

- 但是老师是使用异常处理器来进行全局异常捕获。



#### 2.2 异常处理器

- 对于字段 `username` 唯一索引，我的代码是直接在代码中判断数据库中是否已经有相同 `suername` 的记录。

- 但是这样会增多一次向数据库查询的次数，其实效率是很低的。

- 因此，老师在这里使用的是异常处理器进行全局异常捕获。

- 全局异常处理器底层是基于代理，来代理 Controller ，通过 AOP 把里面如 `save()` 这些方法拦截到，其中的异常统一汇总到全局异常处理器。

- 创建 `src/main/java/edu/ouc/common/GlobalExceptionHandler.java` 。

  ```java
  // Spring提供的注解，拦截所有注解是@RestController和@Controller的Controller
  @ControllerAdvice(annotations = {RestController.class, Controller.class})
  @ResponseBody   // 最终要返回json数据
  @Slf4j
  public class GlobalExceptionHandler {
  
      // 异常处理器注解，括号里是该方法要处理的异常类型
      @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
      public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
          log.info(ex.getMessage());  // Duplicate entry '111' for key 'employee.idx_username'
  
          // 1.判断异常信息是否包含违反唯一约束的前面两个字
          if (ex.getMessage().contains("Duplicate entry")) {
  
              // 2.按空格把异常信息分割成String数组
              String[] split = ex.getMessage().split(" ");
  
              // 3.取数组索引为2的字符串，即重复的username
              String msg = split[2] + "账号已存在，添加失败";
  
              // 4.返回错误提示信息
              return R.error(msg);
          }
          // 5.如果不是则返回未知错误
          return R.error("未知错误");
      }
  }
  ```



### 3. 功能测试



#### 3.1 测试账号重复

- 目前 `employee` 数据表中记录情况如图：

  ![image-20220930161113587](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930161113587.png)

- 输入相同的账号 `lindaiyu` ：

  ![image-20220930161750177](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930161750177.png)

- 点击 ”保存“ ，提示账号已存在：

  ![image-20220930162317037](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930162317037.png)



#### 3.2 测试正常添加

- 修改一下账号，再点击保存：

  ![image-20220930162610192](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930162610192.png)

- 保存成功：

  ![image-20220930162735625](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930162735625.png)





## 2. 员工分页查询



### 1. 需求分析



#### 1.1 请求分析

1. 页面发送 ajax 请求，将分页查询参数 (page、pageSize、name) 提交到服务端；
2. 服务端 Controller 接收页面提交的数据并调用 Service 查询数据；
3. Service调用Mapper操作数据库，查询分页数据；
4. Controller 将查询到的分页数据响应给页面；
5. 页面接收到分页数据并通过 Element UI 的 Table 组件展示到页面上。



- 只要登录了后台管理的主页，就会向后端发送 GET 请求：

  ![image-20220930171135459](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20220930171135459.png)

- 请求的 URL 是 `/employee/page` ，并把当前页码 `page=1` 和 一页显示几行 `pageSize=10` 也一并提交到后端。



#### 1.2 数据库分析

- 向数据库以分页的方式查询所有的记录。



#### 1.3 前端代码分析

- 打开 `src/main/resources/static/backend/page/member/list.html` 。

  ```javascript
  await getMemberList(params).then(res => {
    if (String(res.code) === '1') {
      this.tableData = res.data.records || []
      this.counts = res.data.total
    }
  })
  ```

- 可以看到前端代码会自动读取 Page 对象里的各种属性和信息。



### 2. 代码实现



#### 2.1 创建MP配置类

-  MP 需要我们手动设置拦截器，往预编译 SQL 语句中最后追加 LIMIT 分页语句。

- 创建 `src/main/java/edu/ouc/config/MPConfig.java` ，定义用于分页功能的 MP 拦截器：

  ```java
  @Configuration  // 声明为配置类，以便被Spring扫描到，读取配置
  public class MPConfig {
  
      @Bean   // /Spring第三方Bean注解，以便被Spring管理
      public MybatisPlusInterceptor mybatisPlusInterceptor() {
          // 1.创建MP的拦截器容器
          MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
          
          // 2.往MP拦截器容器中添加分页拦截器
          interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
          
          // 3.返回拦截器容器
          return interceptor;
      }
  }
  ```



#### 2.2 添加分页查询功能

- 在确定分页查询的入参时，员工页面中还有一个按员工姓名来查询的搜索框，我们来查看这个搜索框向后端发送的请求与 URL 是怎么样的：

  ![image-20221001101756356](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001101756356.png)

  ![image-20221001101817960](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001101817960.png)

- 可以看到，按员工姓名进行条件查询的请求 URL 与分页查询相比仅多了一个参数 `name` 。于是我们干脆也把该参数写在 Controller 分页查询的入参中。



#### 2.3 业务层开发

- 打开 `src/main/java/edu/ouc/service/IEmployeeService.java` ：

  ```java
  public interface IEmployeeService extends IService<Employee> {
  
      // 分页查询+条件查询
      Page<Employee> getPage(Long currentPage, Long pageSize, String name);
  }
  ```

- 然后实现业务层接口 `src/main/java/edu/ouc/service/impl/IEmployeeServiceImpl.java` ：

  ```java
  @Service
  public class IEmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
  
      // 自动注入数据层
      @Autowired
      private EmployeeMapper employeeDAO;
  
  
      // 分页查询+条件查询
      @Override
      public Page<Employee> getPage(Long currentPage, Long pageSize, String name) {
  
          // 1.创建新的条件查询构造器，用于根据员工姓名搜索
          LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
  
          // 2.添加过滤条件：根据员工姓名查询
          lqw.like(Strings.isNotEmpty(name), Employee::getName, name);
  
          // 3.添加排序条件：按最后修改时间降序排序
          lqw.orderByDesc(Employee::getUpdateTime);
  
          // 4.分页构造器
          Page<Employee> page = new Page<>(currentPage, pageSize);
  
          // 5.把分页和条件查询包装器传入数据层的selectPage()方法
          return employeeDAO.selectPage(page, lqw);
      }
  }
  ```



#### 2.4 表现层开发

- 打开 `src/main/java/edu/ouc/controller/EmployeeController.java` ：

  ```java
  // 分页查询+根据员工姓名查询功能
  @GetMapping("/page")
  public R<Page<Employee>> getPage(Long page, Long pageSize, String name) {
      return R.success(empService.getPage(page, pageSize, name));
  }
  ```





### 3. 功能测试



#### 3.1 修改前端分页参数

- 我们数据量有限，可以在 `src/main/resources/static/backend/page/member/list.html` 修改成每页展示 5 条数据：

  ```html
  <el-pagination
          class="pageList"
          :page-sizes="[5, 10, 20, 30, 40]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="counts"
          :current-page.sync="page"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
  ></el-pagination>
  ```

  ```javascript
  data() {
      return {
          input: '',
          counts: 0,
          page: 1,
          pageSize: 5,	// 每页展示 5 条数据
          tableData: [],
          id: '',
          status: '',
      }
  }
  ```



#### 3.2 分页功能展示

![image-20221001112453402](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001112453402.png)



#### 3.3 按员工姓名搜索功能测试

![image-20221001112542726](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001112542726.png)

![image-20221001112602202](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001112602202.png)





## 3. 启用/禁用员工账号



### 1. 需求分析



#### 1.1 请求分析

- 点击 “禁用” ，查看前端发送的请求方式和 URL 地址：

  ![image-20221001123349393](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001123349393.png)

  ![image-20221001123421081](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001123421081.png)

  ![image-20221001123517339](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001123517339.png)

  ![image-20221001162234661](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221001162234661.png)

- 采用 PUT 请求，URL 是 `/employee` 。405 是没有 PUT 请求的 Servlet 。

- 此外，可以看见点击 “禁用” 按钮的同时，前端会发送要修改的员工 ID (这个 ID 会有问题，后面细说) 和要修改成的状态码 ( 0 表示要禁用) 给后端。在写后端相应接口时要接收这两个入参。



#### 1.2 数据库分析

- 因为都是修改操作，不妨把修改员工状态与编辑员工资料合并到一个接口实现。



#### 1.3 前端代码分析

- 需要注意，只有管理员 (admin用户) 可以对其他普通用户进行添加员工、启用、禁用操作，所以普通用户登录系统后，添加员工、启用、禁用按钮不显示。在 `src/main/resources/static/backend/page/member/list.html` 实现的方式：

- 思路就是 Vue 初始化的时候，从 Session 保存作用域中获取当前登录用户的账号：

  ```javascript
  created() {
      this.init()
      this.user = JSON.parse(localStorage.getItem('userInfo')).username
  }
  ```
  
- 然后判断当前用户的账号是否为 “admin” ，如果是的话才显示员工启用/禁用按钮。当 `status` 为 1 (启用状态) 时显示 “禁用” 按钮，反之显示 “启用” 按钮。

  ```html
  <el-button
             type="text"
             size="small"
             class="delBut non"
             @click="statusHandle(scope.row)"
             v-if="user === 'admin'"
             >
      {{ scope.row.status == '1' ? '禁用' : '启用' }}
  </el-button>
  ```



- 但是我觉得老师的不合理，普通员工绝对不允许添加员工、编辑和操作其他员工的信息。因此我增加了一条是否是管理员的判断，普通员工登录后台时，添加员工、和整个 “操作” 一列都不会显示。

- `src/main/resources/static/backend/page/member/list.html` ：

  ```html
  <el-table-column
          <!--> 我增加的对管理员的判断 <-->
          v-if="user === 'admin'"
          label="操作"
          width="160"
          align="center"
  >
      <template slot-scope="scope">
          <el-button
                  type="text"
                  size="small"
                  class="blueBug"
                  @click="addMemberHandle(scope.row.id)"
                  :class="{notAdmin:user !== 'admin'}"
          >
              编辑
          </el-button>
          <el-button
                  type="text"
                  size="small"
                  class="delBut non"
                  @click="statusHandle(scope.row)"
                  v-if="user === 'admin'"
          >
              {{ scope.row.status == '1' ? '禁用' : '启用' }}
          </el-button>
      </template>
  </el-table-column>
  ```

  ```html
  <el-button
          v-if="user === 'admin'"
          type="primary"
          @click="addMemberHandle('add')"
  >
      + 添加员工
  </el-button>
  ```

- 非管理员员工登录页面效果：

  ![image-20221002105429318](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002105429318.png)





### 2. 代码实现

#### 2.1 禁用员工账号逻辑思路

1. 从前端获取要修改员工的 ID 和要修改成的状态 `status` ；
2. 



#### 2.2 ID末尾2位精度丢失

- 修改状态失败的根本原因是：js 对 Long 型数据处理时丢失末位 2 位的精度，导致传输给后端的 ID 的最后两位全部变成 “00” ，与数据库中对于的 ID 不一致。

- **解决方案一**：全局配置在服务端给前端响应 JSON 数据时进行处理，将 Long 型数据统一转为 String 字符串 (使用消息转换器) 。

  ```java
  @Configuration
  @EnableWebMvc
  public class WebMvcConfig implements WebMvcConfigurer {
  	@Override
      public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
          MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
          ObjectMapper objectMapper = new ObjectMapper();
          // 全局配置序列化返回 JSON 处理
          SimpleModule simpleModule = new SimpleModule();
          // JSON Long ==> String  将所有的 Long类型 转换成 String类型 返回 
          simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
          objectMapper.registerModule(simpleModule);
          jackson2HttpMessageConverter.setObjectMapper(objectMapper);
          converters.add(jackson2HttpMessageConverter);
      }
  }
  ```

  

- **解决方案二 (推荐)** ：在要转换成 String 字符串的实体类的 Long 型 ID 属性上添加注解 `@JsonSerialize(using = ToStringSerializer.class)` 。可以帮助我们实现字段值的序列化和反序列化。

- `src/main/java/edu/ouc/entity/Employee.java` ：

  ```java
  @Data
  public class Employee {
      private static final Long serialVersionUID = 1L;// 序列化ID
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
      ...
  }
  ```

  

#### 2.4 业务层接口开发

```java
// 修改员工信息
Boolean update(HttpServletRequest request, Employee employee);
```



#### 2.5 业务层实现类开发

```java
// 修改员工信息
@Override
public Boolean update(HttpServletRequest request, Employee employee) {

    // 1.设置修改人
    employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));

    // 2.设置修改时间
    employee.setUpdateTime(LocalDateTime.now());

    // 3.调用数据层的更新操作
    return updateById(employee);
}
```



#### 2.6 表现层开发

```java
// 修改员工信息
@PutMapping
public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
    if (empService.update(request, employee)) {
        return R.success("修改成功");
    }
    return R.error("修改失败");
}
```



### 3. 功能测试

略。



## 4. 编辑员工信息



### 1. 需求分析



#### 1.1 请求分析

- 编辑功能是先查后修改。先把要编辑的员工的信息查询出来回显到编辑页面，用户修改完成后，点击 “保存” 再调用修改操作方法。

- 点击 “编辑” 按钮：

  ![image-20221002102136507](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002102136507.png)

- 打开浏览器控制台查看请求方式与请求 URL ：

  ![image-20221002102245747](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002102245747.png)

- 可见，编辑按钮采用 GET 请求，请求 URL 是 `/employee/待编辑员工ID` 。

- 注意这个 URL 的 ID 是随待编辑的员工不同而动态变化的。这就需要在表现层 Controller 的 GET 请求映射注解中用花括号把 ID 括起来，即  `@GetMapping("/{id}")` 。此外，方法的入参还需要用 `@PathVariable` 注释修饰。



- 然后是用户修改完，点击 “保存” 时的请求方式与 URL ：

  ![image-20221002104654249](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002104654249.png)

- 由于上一节已经编写完通用的修改方法，因此点击保存直接就成功保存了。无需再额外实现修改方法：

  ![image-20221002104807555](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002104807555.png)
  
  



#### 1.2 数据库分析

- 对待编辑员工先根据 ID 查询，再调用上一节写好的通用 `update()` 方法。



#### 1.3 前端代码分析

- 编辑页面复用了添加员工的页面。





### 2. 代码实现



#### 2.1 查询待编辑员工的信息并回显到前端

- 上面说到，编辑按钮的请求 URL 是 `/employee/待编辑员工ID` 。

- 注意这个 URL 的 ID 是随待编辑的员工不同而动态变化的。这就需要在表现层 Controller 的 GET 请求映射注解中用花括号把 ID 括起来，即  `@GetMapping("/{id}")` 。此外，方法的入参还需要用 `@PathVariable` 注释修饰。

- `EmployeeController.java` ：

  ```java
  // 根据ID查询员工信息
  @GetMapping("/{id}")
  public R<Employee> getById(@PathVariable Long id) {
      Employee employee = empService.getById(id);
      // 当查询结果不为空时才返回employee
      if (employee != null) {
          return R.success(employee);
      }
      return R.error("查询员工不存在");
  }
  ```



### 3. 功能测试

- 用户修改完，点击 “保存” 时的请求方式与 URL ：

  ![image-20221002104654249](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002104654249.png)

- 由于上一节已经编写完通用的修改方法，因此点击保存直接就成功保存了。无需再额外实现修改方法：

  ![image-20221002104807555](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002104807555.png)





# 六、 分类管理业务开发





## 1. 公共字段自动填充



### 1.1 问题分析

- 前面员工表 `emoloyee` 中有 4 个字段属于公共字段：

  ![image-20221002112606922](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002112606922.png)

- 这 4 个字段不仅在员工表中存在，在很多其他表中也存在。为了避免对每一张数据表都要重复维护这些公共字段，MP 框架提供的公共字段自动填充功能，能帮助我们简化开发。



### 1.2 代码实现

- Mybatis-Plus 公共字段自动填充，也就是在插入或者更新的时候为指定字段赋予指定的值，使用它的好处就是可以统一对这些字段进行处理，避免了重复代码。
- 实现步骤：
  1. 在实体类的属性上加入 `@TableField` 注解，指定自动填充的策略；
  2. 按照 Mybatis-Plus 框架要求编写元数据对象处理器，在此类中统一为公共字段赋值，此类需要实现 `MetaObjectHandler` 接口。



#### ① 实体类加注解

- `src/main/java/edu/ouc/entity/Employee.java` ：

  ```java
  @Data
  public class Employee {
      private static final Long serialVersionUID = 1L;// 序列化ID
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
      private String name;
      private String username;
      private String password;
      private String phone;
      private String sex;
      private String idNumber;
      private Integer status;
  
      @TableField(fill = FieldFill.INSERT)    // 插入时填充字段
      private LocalDateTime createTime;
  
      @TableField(fill = FieldFill.INSERT_UPDATE) // 插入和更新时填充字段
      private LocalDateTime updateTime;
  
      @TableField(fill = FieldFill.INSERT)    // 插入时填充字段
      private Long createUser;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入和更新时填充字段
      private Long updateUser;
  }
  ```



#### ② 创建元数据对象处理器

- 创建 `src/main/java/edu/ouc/common/MyMetaObjectHandler.java` 。

  ```java
  @Component  // 让Spring管理
  @Slf4j
  public class MyMetaObjectHandler implements MetaObjectHandler {
  
      // 当执行插入数据时自动填充
      @Override
      public void insertFill(MetaObject metaObject) {
          metaObject.setValue("createTime", LocalDateTime.now());
          metaObject.setValue("updateTime", LocalDateTime.now());
          metaObject.setValue("createUser", new Long(1));
          metaObject.setValue("updateUser", new Long(1));
      }
  
      // 当执行更新数据时自动填充
      @Override
      public void updateFill(MetaObject metaObject) {
          metaObject.setValue("updateUser", new Long(1));
          metaObject.setValue("updateTime", LocalDateTime.now());
      }
  }
  ```

- 这个类写好以后，不光是员工表 `employee` ，所有含有这 4 个字段的数据表都可以自动填充。



#### ③ 重构新增员工功能

- 有了自动填充后，新增员工的方法中就再也不需要手动设置这 4 个公共字段了。

- `src/main/java/edu/ouc/controller/EmployeeController.java` ：

  ```java
  // 新增员工功能
  @PostMapping
  public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
  
      // 1.设置初始密码为身份证后6位，并经过MD5加密
      String idNumber = employee.getIdNumber();
      String password = DigestUtils.md5DigestAsHex(idNumber.substring(idNumber.length() - 6).getBytes());
      employee.setPassword(password);
  
      // 2.调用业务层保存到数据库中
      empService.save(employee);
      return R.success("添加成功");
  }
  ```



#### ④ 重构修改员工功能

- 有了自动填充后，修改员工的方法中就再也不需要手动设置这 2 个公共字段了。业务层的修改方法全删掉，直接在表现层写即可。

- `src/main/java/edu/ouc/controller/EmployeeController.java` ：

  ```java
  // 修改员工信息
  @PutMapping
  public R<String> update(@RequestBody Employee employee) {
      if (empService.updateById(employee)) {
          return R.success("修改成功");
      }
      return R.error("修改失败");
  }
  ```



### 1.3 功能测试

略。



### 1.4 功能完善ThreadLocal

- 前面我们已经完成了公共字段自动填充功能的代码开发，但是还有一个问题没有解决：就是我们在自动填充 `createUser` 和 `updateUser` 时设置的用户 `id` 是固定值。
- 现在我们需要改造成**动态获取**当前登录用户的 `id` 。
- 注意：我们在 `MyMetaObjectHandler` 类中是**不能获得 Http Session 对象**的，不能从 Session 保存作用域中获取当前登录用户的 ID 。所以我们需要通过其他方式来获取登录用户 `id` 。
- 可以使用 `ThreadLocal` 来解决此问题，它是 JDK 中提供的一个类。



#### ① 服务端处理HTTP请求线程流程

- 在学习 `ThreadLocal` 之前，我们需要先确认一个事情：就是客户端发送的每次 http 请求，对应的在服务端都会分配一个**新的线程**来处理，在处理过程中，涉及到下面类中的方法都**属于相同的一个线程**：

1. `LoginCheckFilter.java` 的 `doFilter()` 方法；
1. `EmployeeController.java` 的 `update()` 方法；
1. `MyMetaObjectHandler.java` 的 `updateFill()` 方法。
- 可以在上面的三个方法中，分别加入下面代码来获取当前线程 id 。以此证明它们同属一个线程：

  ```java
  // 查看当前线程的ID
  long id = Thread.currentThread().getId();
  log.info("线程ID为：{}", id);
  ```

  输出：

  ```
  INFO 15412 --- [nio-8080-exec-3] edu.ouc.filter.LoginCheckFilter          : 线程ID为：34
  INFO 15412 --- [nio-8080-exec-3] edu.ouc.controller.EmployeeController    : 线程ID为：34
  INFO 15412 --- [nio-8080-exec-3] edu.ouc.common.MyMetaObjectHandler       : 线程ID为：34
  ```

- 可以看到，证明了这三个方法确实同在一个线程内。



#### ② ThreadLocal简介

- `ThreadLocal` 并不是一个 `Thread` ，而是 `Thread` 的一个局部变量。
- 当使用`ThreadLocal` 维护变量时，`ThreadLocal` 为每个使用该变量的线程提供独立的**变量副本**，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
- `ThreadLocal` 为**每个线程**提供单独一份存储空间，具有**线程隔离**的效果，只有在线程内才能获取到对应的值，线程外则不能访问。
- 因此我们可以使用 `ThreadLocal` 来存储当前登录用户的 ID 。



#### ③ ThreadLocal常用方法

|           方法           |                作用                |
| :----------------------: | :--------------------------------: |
| public void set(T value) |   设置当前线程的线程局部变量的值   |
|      public T get()      | 获取当前线程对应的线程局部变量的值 |



#### ④ 优化逻辑思路

- 我们可以在 `LoginCheckFilter.java` 的 `doFilter()` 方法中获取当前登录用户的 `id` ，并调用 `ThreadLocal` 的 `set()` 方法来保存当前线程的线程局部变量的值 (用户 `id` ) ；
- 然后在 `MyMetaObjectHandler.java` 的 `updateFill()` 方法中调用`ThreadLocal` 的 `get()` 方法来获取当前线程所对应的线程局部变量的值 (用户 `id` ) 。



#### ⑤ 实现步骤

1. 编写 `BaseContext` 工具类，基于 `ThreadLocal` 封装的工具类;

- 创建 `src/main/java/edu/ouc/common/BaseContext.java` ：

  ```java
  public class BaseContext {
  
      private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
  
      // 保存当前登录用户的ID
      public static void setCurrentUserId(Long id) {
          threadLocal.set(id);
      }
  
      // 获取当前登录用户的ID
      public static Long getCurrentUserId() {
          return threadLocal.get();
      }
  }
  ```

2. 在`LoginCheckFilter.java` 的 `doFilter()` 方法中调用 `BaseContext` 来设置当前登录用户的 `id` ；

- 打开 `src/main/java/edu/ouc/filter/LoginCheckFilter.java` 第68行：

  ```java
  // 5.如果需要处理，则判断登录状态
  if (request.getSession().getAttribute("employee") != null) {
      // 能进入说明已经登录，直接放行
      Long id = (Long) request.getSession().getAttribute("employee");
      log.info("用户{}已登录", id);
  
      // 把当前登录用户的ID保存到ThreadLocal中
      BaseContext.setCurrentUserId(id);
  
      filterChain.doFilter(request, response);
      return;
  }
  ```

3. 在 `MyMetaObjectHandler.java` 的方法中调用 `BaseContext` 获取登录用户的 `id` 。

- 打开 `src/main/java/edu/ouc/common/MyMetaObjectHandler.java` ：

  ```java
  @Component  // 让Spring管理
  @Slf4j
  public class MyMetaObjectHandler implements MetaObjectHandler {
  
      // 当执行插入数据时自动填充
      @Override
      public void insertFill(MetaObject metaObject) {
          metaObject.setValue("createTime", LocalDateTime.now());
          metaObject.setValue("updateTime", LocalDateTime.now());
  
          // 获取当前线程的登录用户的ID
          Long id = BaseContext.getCurrentUserId();
  
          metaObject.setValue("createUser", id);
          metaObject.setValue("updateUser", id);
      }
  
      // 当执行更新数据时自动填充
      @Override
      public void updateFill(MetaObject metaObject) {
  
          // 获取当前线程的登录用户的ID
          Long id = BaseContext.getCurrentUserId();
  
          metaObject.setValue("updateUser", id);
          metaObject.setValue("updateTime", LocalDateTime.now());
      }
  }
  ```

- 至此，以后所有数据表中的公共字段都可以自动填充了，无需重复手动设置。





## 2. 新增分类



### 2.1 需求分析



#### ① 功能分析

- 分类管理页面同时管理两种分类，一种是菜品分类 (粤菜、川菜、鲁菜) ；另一种则是套餐分类 (情人节限时优惠套餐、超值午餐精选) 。

  ![image-20221002142248329](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142248329.png)

  ![image-20221002142317143](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142317143.png)

  ![image-20221002142336657](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142336657.png)

- 菜品分类和套餐分类会在 “菜品管理” 和 “套餐管理” 上分别充当属性：

  ![image-20221002142502981](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142502981.png)

  ![image-20221002142535653](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142535653.png)



#### ② 请求分析

- 点击 ”确定“ 时，查看浏览器控制台：

  ![image-20221002145217229](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002145217229.png)

  ![image-20221002145239837](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002145239837.png)

  ![image-20221002145257303](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002145257303.png)

- 可以看到，新增分类时，前端以 POST 方式向后端发起请求，请求 URL 是 `/category` ，请求负载把分类名称 `name` 、分类的类别 `type` 和展示顺序 `sort` 都发送到后端。





### 2.2 数据模型

- 分类管理对应的数据表就是 `category` ：

  ![image-20221002142642441](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142642441.png)

- 相应的字段介绍：

  |    字段     |            描述             |
  | :---------: | :-------------------------: |
  |     id      |           分类ID            |
  |    type     |   1菜品分类 or 2套餐分类    |
  |    name     |     分类名称 (唯一约束)     |
  |    sort     | 展示顺序 (移动端展示的顺序) |
  | create_time |        创建日期时间         |
  | update_time |        修改日期时间         |
  | create_user |          创建人ID           |
  | update_user |          修改人ID           |

  

### 2.3 代码开发



#### ① 创建实体类

- 创建 `src/main/java/edu/ouc/entity/Category.java` ：

  ```java
  @Data
  public class Category {
      private static final Long serialVersionUID = 123L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
      private Integer type;
      private String name;
      private Integer sort;
      
      @TableField(fill = FieldFill.INSERT)    // 插入时填充字段
      private LocalDateTime createTime;
  
      @TableField(fill = FieldFill.INSERT_UPDATE) // 插入和更新时填充字段
      private LocalDateTime updateTime;
  
      @TableField(fill = FieldFill.INSERT)    // 插入时填充字段
      private Long createUser;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)  // 插入和更新时填充字段
      private Long updateUser;
  }
  ```



#### ② 数据层开发

- 创建 `src/main/java/edu/ouc/mapper/CategoryMapper.java` ：

  ```java
  @Mapper
  public interface CategoryMapper extends BaseMapper<Category> {
  }
  ```



#### ③ 业务层开发

- 创建业务层接口 `src/main/java/edu/ouc/service/ICategoryService.java` ：

  ```java
  public interface ICategoryService extends IService<Category> {
  }
  ```

- 创建业务层实现类 `src/main/java/edu/ouc/service/impl/CategoryServiceImpl.java` ：

  ```java
  @Service
  public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
      
      @Autowired
      private CategoryMapper categoryDAO;
  }
  ```



#### ④ 表现层开发

- 创建 `src/main/java/edu/ouc/controller/CategoryController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/category")
  public class CategoryController {
  
      @Autowired
      private ICategoryService categoryService;
      
      // 新增分类
      @PostMapping
      public R<String> save(@RequestBody Category category) {
          if (categoryService.save(category)) {
              return R.success("添加成功");
          }
          return R.error("添加失败");
      }
  }
  ```



### 2.4 功能测试

- 点击 ”新增菜品分类“ ：

  ![image-20221002150453611](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002150453611.png)

- 查看数据库：

  ![image-20221002150529060](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002150529060.png)

- 点击 ”新增套餐分类“ ：

  ![image-20221002150623768](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002150623768.png)

- 查看数据库：

  ![image-20221002150652158](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002150652158.png)





## 3. 分类信息分页查询



### 3.1 需求分析

#### ① 请求分析

- 分页查询以 GET 请求方式向后端请求，请求 URL 为 `/category/page` 。同时携带两个参数：当前页码 `page` 和 每页记录数 `pageSize` 。

  ![image-20221002160542998](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002160542998.png)



### 3.2 代码开发



#### ① 业务层开发

- 打开业务层接口 `src/main/java/edu/ouc/service/ICategoryService.java` ：

  ```java
  public interface ICategoryService extends IService<Category> {
  
      // 分页查询
      Page<Category> getPage(Long currentPage, Long pageSize);
  }
  ```

- 打开业务层接口的实现类 `src/main/java/edu/ouc/service/impl/IEmployeeServiceImpl.java` ：

  ```java
  @Service
  public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
  
      @Autowired
      private CategoryMapper categoryDAO;
  
      // 分页查询
      @Override
      public Page<Category> getPage(Long currentPage, Long pageSize) {
  
          // 1.创建条件查询构造器，用于按展示顺序排序
          LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
  
          // 2.根据展示顺序排序
          lqw.orderByAsc(Category::getSort);
  
          // 3.创建分页构造器
          Page<Category> page = new Page<>(currentPage, pageSize);
  
          // 4.调用数据层的分页查询
          return page(page, lqw);
      }
  }
  ```



#### ② 表现层开发

- 打开表现层 `src/main/java/edu/ouc/controller/CategoryController.java` ：

  ```java
  // 分页查询
  @GetMapping("/page")
  public R<Page<Category>> getPage(Long page, Long pageSize) {
      return R.success(categoryService.getPage(page, pageSize));
  }
  ```



### 3.3 功能测试

- 点击分类管理页面：

  ![image-20221002162500543](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002162500543.png)





## 4. 删除分类



### 4.1 需求分析

#### ① 功能分析

- **注意**：如果要删除的分类下关联了菜品，是不允许被删除的。



#### ② 请求分析

- ”删除“ 按钮以 DELETE 请求方式向后端请求，请求 URL 为 `/category` 。同时携带要删除的分类的 ID 。

  ![image-20221002162642211](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002162642211.png)



### 4.2 代码开发

- 功能太简单，直接写在表现层。


#### ① 表现层开发

- 打开表现层 `src/main/java/edu/ouc/controller/CategoryController.java` ：

  ```java
  // 删除分类
  @DeleteMapping
  public R<String> deleteById(Long ids) {
      if (categoryService.removeById(ids)) {
          return R.success("删除成功");
      }
      return R.error("删除失败");
  }
  ```



### 4.3 功能测试

![image-20221002163153622](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002163153622.png)

![image-20221002163214502](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002163214502.png)

![image-20221002163300467](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002163300467.png)



### 4.4 功能完善

- 前面说到删除不能直接删，要确保该分类下没有关联任何菜品和套餐才能删。

- 这就需要调用菜品和套餐的业务层，根据要删除的分类 ID 进行查询，如果查询结果不为空，则删除失败，如果查询结果为空，则可以删除。

- 首先需要把菜品和套餐的实体类、数据层和业务层全部搭起来。我提前在第七和第八章已经搭好了。

  ![image-20221003103229267](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003103229267.png)

#### ① 业务层开发

- 在业务层接口中自定义分类删除抽象方法。

- `ICategoryService.java` ：

  ```java
  public interface ICategoryService extends IService<Category> {
  
      // 分页查询
      Page<Category> getPage(Long currentPage, Long pageSize);
      
      // 完善的删除分类操作
      Boolean remove(Long id);
  }
  ```

- 在业务层接口的实现类中实现具体的删除功能。

- `CategoryServiceImpl.java` ：

  ```java
  @Service
  @Slf4j
  public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
  
      // 注入菜品业务层
      @Autowired
      private DishServiceImpl dishService;
  
      // 注入套餐业务层
      @Autowired
      private SetmealServiceImpl setmealService;
  
      // 分页查询
      @Override
      public Page<Category> getPage(Long currentPage, Long pageSize) {
  
          // 1.创建条件查询构造器，用于按展示顺序排序
          LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
  
          // 2.根据日期时间排序
          lqw.orderByAsc(Category::getSort);
  
          // 3.创建分页构造器
          Page<Category> page = new Page<>(currentPage, pageSize);
  
          // 4.调用数据层的分页查询
          return page(page, lqw);
      }
  
      // 完善的删除分类操作
      // 根据id删除分类，删除之前判断分类下是否有关联的菜品或套餐
      @Override
      public Boolean remove(Long id) {
  
          // 1.查询当前分类是否关联了菜品，如果已经关联，则抛出一个业务异常
  
          // 1.1 创建条件构造器
          LambdaQueryWrapper<Dish> dishLqw = new LambdaQueryWrapper<>();
  
          // 1.2 添加查询条件，按照分类ID进行查询
          dishLqw.eq(Dish::getCategoryId, id);
  
          // 1.3 返回查询结果的总数
          int dishCount = dishService.count(dishLqw);
  
          // 1.4 如果查询结果大于0，说明该分类已经关联菜品，抛出一个自定义的业务异常
          if (dishCount > 0) {
              throw new CustomException("该分类有关联菜品，无法删除");
          }
  
  
          // 2.查询当前分类是否关联了套餐，如果已经关联，则抛出一个业务异常
  
          // 2.1 创建条件构造器
          LambdaQueryWrapper<Setmeal> setmealLqw = new LambdaQueryWrapper<>();
  
          // 2.2 添加查询条件，按照分类ID进行查询
          setmealLqw.eq(Setmeal::getCategoryId, id);
  
          // 2.3 返回查询结果的总数
          int setmealCount = setmealService.count(setmealLqw);
  
          // 2.4 如果查询结果大于0，说明该分类已经关联套餐，抛出一个自定义的业务异常
          if (setmealCount > 0) {
              throw new CustomException("该分类有关联套餐，无法删除");
          }
  
          // 3.如果都没有关联，就可以正常删除
          return removeById(id);
      }
  }
  ```

  

#### ② 自定义业务异常类

- 创建 `src/main/java/edu/ouc/common/CustomException.java` ：

  ```java
  public class CustomException extends RuntimeException {
  
      static final Long serialVersionUID = 32424L;
  
      public CustomException() {
      }
  
      public CustomException(String message) {
          super(message);
      }
  }
  ```



#### ③ 在全局异常处理类中捕获自定义业务异常

- 打开 `src/main/java/edu/ouc/common/GlobalExceptionHandler.java` ：

  ```java
  // 捕获删除分类失败的业务异常
  @ExceptionHandler(CustomException.class)
  public R<String> customerExceptionHandler(CustomException ce) {
      return R.error(ce.getMessage());
  }
  ```



#### ④ 完善表现层

- 打开 `CategoryController.java` ：

  ```java
  // 完善的删除分类操作
  // 根据id删除分类，删除之前判断分类下是否有关联的菜品或套餐
  @DeleteMapping
  public R<String> deleteById(Long ids) {
      if (categoryService.remove(ids)) {
          return R.success("删除成功");
      }
      return R.error("删除失败");
  }
  ```



#### ⑤ 功能测试

- “川菜” 这一类别已经关联了菜品，我们尝试来删除它。

  ![image-20221003115635574](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003115635574.png)

  ![image-20221003120009786](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003120009786.png)

- 删除一个没有关联菜品和套餐的分类：

  ![image-20221003120107221](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003120107221.png)

  ![image-20221003120156777](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003120156777.png)





## 5. 修改分类



### 5.1 需求分析

#### ① 请求分析

- ”修改“ 按钮以 PUT 请求方式向后端请求，请求 URL 为 `/category` 。请求负载与新增分类的表单一致。

  ![image-20221002163552562](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002163552562.png)

  ![image-20221002163617963](C:\Users\92490\AppData\Roaming\Typora\typora-user-images\image-20221002163617963.png)



### 5.2 代码开发

- 功能太简单，直接写在表现层。


#### ① 表现层开发

- 打开表现层 `src/main/java/edu/ouc/controller/CategoryController.java` ：

  ```java
  // 删除分类
  @DeleteMapping
  public R<String> deleteById(Long ids) {
      if (categoryService.removeById(ids)) {
          return R.success("删除成功");
      }
      return R.error("删除失败");
  }
  ```



### 5.3 功能测试

![image-20221002164044764](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002164044764.png)

![image-20221002164057920](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002164057920.png)





# 七、 菜品管理业务开发



## 1. 文件上传下载



### 1.1 文件上传简介



#### ①文件上传必须满足的前端要求

|             代码              |            描述             |
| :---------------------------: | :-------------------------: |
|         method="post"         |    采用 POST方式提交数据    |
| enctype="multipart/form-data" | 采用 multipart 格式上传文件 |
|          type="file"          |  使用 input 的file控件上传  |





### 1.2 文件下载简介

略。



### 1.3 文件上传代码实现



#### ① 配置文件保存路径

- 在配置文件中统一管理文件保存路径。

- 打开 `application.yml` ：

  ```yaml
  # 文件保存路径
  reggie:
    path: G:\img\
  ```



#### ② 创建公共功能Controller

- 创建 `src/main/java/edu/ouc/common/CommonController.java` ：

  在下面下载功能实现完再一并展示。

  



### 1.4 文件下载代码实现

- 需要把用户刚上传的图片回显到浏览器上。如果不回显，浏览器上就会是这种：

  ![image-20221003153453294](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003153453294.png)

- 请求方式是 GET ，请求 URL 是 `/common/download` 。

  ![image-20221003153041711](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003153041711.png)

- 打开  `src/main/java/edu/ouc/common/CommonController.java` ：

  ```java
  @RestController
  @RequestMapping("/common")
  @Slf4j
  public class CommonController {
  
      // 获取配置文件中的存储路径
      @Value("${reggie.path}")
      private String basePath;
  
      // 文件上传
      @PostMapping("/upload")
      public R<String> upload(MultipartFile file) { // 入参名必须是file
          // file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
  
          // 获取原始文件名
          String originalFilename = file.getOriginalFilename();   // 原始名称.jpg
  
          // 截取原始名称的后缀
          String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));  //.jpg
  
          // 使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
          String fileName = UUID.randomUUID().toString() + suffix;    // sdfaiourei.jpg
  
          // 创建一个目录对象
          File dir = new File(basePath);
          // 判断当前目录是否存在
          if (!dir.exists()) {
              // 目录不存在需要创建
              dir.mkdir();
          }
  
          try {
              // 转存
              file.transferTo(new File(basePath + fileName));
          } catch (IOException e) {
              e.printStackTrace();
          }
          // 返回文件名称即可
          return R.success(fileName);
      }
  
      // 文件下载
      @GetMapping("/download")
      public void download(String name, HttpServletResponse response) {   // 入参名字必须是name
          FileInputStream fis = null;
          ServletOutputStream os = null;
  
          try {
              // 输入流，通过输入流读取文件内容
              fis = new FileInputStream(new File(basePath + name));
  
              // 输出流，通过输出流将文件写回浏览器，在浏览器展示图片
              os = response.getOutputStream();
  
              // 下面都是JavaSE中IO流的内容
              byte[] buffer = new byte[1024];
              int len = 0;
              while ((len = fis.read(buffer)) != -1) {
                  os.write(buffer, 0, len);
                  os.flush();
              }
  
          } catch (IOException e) {
              throw new RuntimeException(e);
          } finally {
              // 关闭流资源
              try {
                  if (fis != null)
                      fis.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
              try {
                  if (os != null)
                      os.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  ```





## 2. 新增菜品



### 2.1 需求分析



#### ① 功能分析

- 菜品管理页面：

  ![image-20221003093033324](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003093033324.png)

  

- 菜品分类会在 “菜品管理” 新建菜品时充当属性：

  ![image-20221002142502981](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142502981.png)



#### ② 请求分析

- 首先要获取菜品分类，这要去 `CategoryController.java` 中补充相关方法。

  ![image-20221003162655530](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003162655530.png)

  ![image-20221003162716222](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003162716222.png)

- 点击 ”保存“ 时，查看浏览器控制台：

  ![image-20221003093710183](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003093710183.png)

  ![image-20221003163915738](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003163915738.png)

  ![image-20221003163936736](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003163936736.png)

- 可以看到，新增菜品时，前端以 POST 方式向后端发起请求，请求 URL 是 `/dish` ，请求负载把菜品相关的属性都发送到后端。





### 2.2 数据模型

- 菜品管理对应的数据表就是 `dish` ：

  ![image-20221003101300192](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003101300192.png)

- 相应的字段介绍：

  |    字段     |            描述             |
  | :---------: | :-------------------------: |
  |     id      |           菜品ID            |
  |    name     |     菜品名称 (唯一约束)     |
  | category_id |           分类ID            |
  |    price    |            价格             |
  |    code     |           商品码            |
  |    image    |          菜品图片           |
  | description |          菜品描述           |
  |   status    |   售卖状态：0停售；1起售    |
  |    sort     | 展示顺序 (移动端展示的顺序) |
  | create_time |        创建日期时间         |
  | update_time |        修改日期时间         |
  | create_user |          创建人ID           |
  | update_user |          修改人ID           |
  | is_deleted  |         是否已删除          |




- 还涉及到菜品口味表 `dish_flavor` 。

  ![image-20221003161505469](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003161505469.png)

- 相应的字段介绍：

  |    字段     |       描述       |
  | :---------: | :--------------: |
  |     id      |    菜品口味ID    |
  |   dish_id   |      菜品ID      |
  |    name     |     口味名称     |
  |    value    | 对应口味的可选值 |
  | create_time |   创建日期时间   |
  | update_time |   修改日期时间   |
  | create_user |     创建人ID     |
  | update_user |     修改人ID     |
  | is_deleted  |    是否已删除    |





### 2.3 代码开发



#### ① 创建实体类

- 创建 `src/main/java/edu/ouc/entity/Dish.java` 。这里有关价格很关键，MySQL 中为了精度无丢失采用了 DECIMAL 定点数据类型，那么实体类对应的 Java 实体类的属性中应该用 `BigDecimal` 数据类型：

  ```java
  @Data
  public class Dish {
      private static final Long serialVersionUID = 329847832957L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
  
      private String name;
      
      @JsonSerialize(using = ToStringSerializer.class)
      private Long categoryId;
      
      private BigDecimal price;
      private String code;
      private String image;
      private String description;
      private Integer status;
      private Integer sort;
      private Integer isDeleted;
  
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;
  
      @TableField(fill = FieldFill.INSERT)
      private Long createUser;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long updateUser;
  }
  ```
  
- 创建 `src/main/java/edu/ouc/entity/DishFlavor.java` ：

  ```java
  @Data
  public class DishFlavor {
      private static final Long serialVersionUID = 313L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long dishId;
      
      private String name;
      private String value;
      private Integer isDeleted;
  
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;
  
      @TableField(fill = FieldFill.INSERT)
      private Long createUser;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long updateUser;
  }
  ```



#### ② 数据层开发

- 创建 `src/main/java/edu/ouc/mapper/DishMapper.java` ：

  ```java
  @Mapper
  public interface DishMapper extends BaseMapper<Dish> {
  }
  ```
  
- 创建 `src/main/java/edu/ouc/mapper/DishFlavorMapper.java` ：

  ```java
  @Mapper
  public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
  }
  ```



#### ③ 业务层开发

- 创建菜品口味业务层接口 `src/main/java/edu/ouc/service/IDishFlavorService.java` ：

  ```java
  public interface IDishFlavorService extends IService<DishFlavor> {
  }
  ```

- 创建菜品口味业务层实现类 `src/main/java/edu/ouc/service/impl/DishFlavorServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements IDishFlavorService {
  }
  ```




- 创建菜品业务层接口 `src/main/java/edu/ouc/service/IDishService.java` ：

  ```java
  public interface IDishService extends IService<Dish> {
  
      // 新增菜品，同时插入菜品对应的口味数据
      Boolean saveWithFlavor(DishDto dishDto);
  }
  ```

- 创建菜品业务层实现类 `src/main/java/edu/ouc/service/impl/DishServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
  
      @Autowired
      private DishFlavorServiceImpl dishFlavorService;
  
      // 新增菜品，同时插入菜品对应的口味数据
      @Override
      @Transactional  // 涉及到多张表操作，开启事务
      public Boolean saveWithFlavor(DishDto dishDto) {
  
          // 1.保存菜品的基本信息
          this.save(dishDto);
  
          // 2.获取菜品的ID
          Long dishId = dishDto.getId();
  
          // 3.遍历菜品口味并逐一赋上菜品ID值，然后保存
          List<DishFlavor> flavors = dishDto.getFlavors();
          // 3.1 使用Stream处理集合，结果赋回给它自己
          // peek()是stream的中间操作，是对象的时候才能修改
          flavors = flavors.stream().peek(flavor -> flavor.setDishId(dishId)).collect(Collectors.toList());
  
          // 4.调用批量保存
          return dishFlavorService.saveBatch(flavors);
      }
  }
  ```

- 涉及到多表操作，要在启动类中开启 Spring 事务注解管理。

- `src/main/java/edu/ouc/ReggieTakeOutApplication.java` ：

  ```java
  @Slf4j  // 日志
  @SpringBootApplication  // Spring Boot启动类
  @ServletComponentScan   // Servlet组件扫描，扫描过滤器
  @EnableTransactionManagement    // 开启Spring事务注解管理
  public class ReggieTakeOutApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(ReggieTakeOutApplication.class, args);
          // 打印Slf4j日志
          log.info("项目启动成功");
      }
  }
  ```





#### ④ 表现层开发

- 打开 `CategoryController.java` ，补充获取菜品分类的方法：

  ```java
  // 添加菜品或套餐时获取分类信息
  @GetMapping("/list")
  public R<List<Category>> list(Category category) {
      LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
      lqw.eq(category.getType() != null, Category::getType, category.getType());
      // 双重排序条件
      lqw.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
      return R.success(categoryService.list(lqw));
  }
  ```

- 创建 `src/main/java/edu/ouc/controller/DishController.java` 。菜品口味相关操作也一并放在 `DishController.java` 下。

- 注意：前端提交的表单中既有菜品 `dish` ，又有菜品口味 `dishFlavor` ，我们需要保存这 2 个不同的实体类对象。

- 解决方法：创建 DTO (Data Transfer Object) ，即数据传输对象，一般用于表现层与服务层之间的数据传输。

- 创建 DTO `src/main/java/edu/ouc/dto/DishDto.java` ：

  ```java
  @Data
  public class DishDto extends Dish {// 继承Dish，就拥有了Dish的全部属性和方法
  
      // 封装菜品口味
      private List<DishFlavor> flavors = new ArrayList<>();
  
      // 菜品分类名称
      private String categoryName;
  
      // 副本
      private Integer copies;
  }
  ```
  
- 打开 `DishController.java` ：
  
  ```java
  @Slf4j
  @RestController
  @RequestMapping("/dish")
  public class DishController {
  
      @Autowired
      private DishServiceImpl dishService;
  
      @Autowired
      private DishFlavorServiceImpl dishFlavorService;
  
      // 添加菜品，同时插入菜品对应的口味数据
      @PostMapping
      public R<String> save(@RequestBody DishDto dishDto) {
          if (dishService.saveWithFlavor(dishDto)) {
              return R.success("保存成功");
          }
          return R.error("保存失败");
      }
  }
  ```



### 2.4 功能测试

- 添加一道鲁菜 ”九转大肠“ 。

  ![image-20221003203622452](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003203622452.png)

- 数据表 `dish` 中保存成功：

  ![image-20221003204040409](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003204040409.png)

- 菜品口味表中保存成功：

  ![image-20221003204156620](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003204156620.png)







## 3. 菜品信息分页查询



### 3.1 需求分析

#### ① 请求分析

- 分页查询以 GET 请求方式向后端请求，请求 URL 为 `/dish/page` 。同时携带两个参数：当前页码 `page` 和 每页记录数 `pageSize` 。

  ![image-20221003204439630](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003204439630.png)

  ![image-20221003204514338](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003204514338.png)

#### ② 功能分析

- 菜品的分页查询还是挺复杂的。

- 需要展示菜品图片，这意味着需要调用下载方法 `download()` 显示到浏览器上。

- 此外，还需要显示 ”菜品分类“ 的名称，但实体类 `Dish` 中只记录了菜品分类的 ID `categoryId` ，因此，需要去 `DishDto` 中获取菜品分类名称 `categoryName` 。

  ![image-20221003210009803](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003210009803.png)

- `DishDto` ：

  ```java
  @Data
  public class DishDto extends Dish {// 继承Dish，就拥有了Dish的全部属性和方法
  
      // 封装菜品口味
      private List<DishFlavor> flavors = new ArrayList<>();
  
      // 菜品分类名称
      private String categoryName;
  
      // 副本
      private Integer copies;
  }
  ```



### 3.2 代码开发

- 本节的重难点在于使用 `DTO` 显示出菜品的分类名称。

#### ① 业务层开发

- 在菜品业务层接口 `IDishService.java` 中添加分页+按菜品名称查询方法：

  ```java
  public interface IDishService extends IService<Dish> {
  
      // 新增菜品，同时插入菜品对应的口味数据
      Boolean saveWithFlavor(DishDto dishDto);
  
      // 分页查询+按菜品名称查询
      List<DishDto> getPage(Long page, Long pageSize, String name);
  }
  ```

- 菜品业务层接口的实现类 `DishServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
  
      @Autowired
      private DishFlavorServiceImpl dishFlavorService;
  
      @Autowired
      private CategoryServiceImpl categoryService;
  
      // 新增菜品，同时插入菜品对应的口味数据
      @Transactional  // 涉及到多张表操作，开启事务
      public Boolean saveWithFlavor(DishDto dishDto) {
  
          // 1.保存菜品的基本信息
          this.save(dishDto);
  
          // 2.获取菜品的ID
          Long dishId = dishDto.getId();
  
          // 3.遍历菜品口味并逐一赋上菜品ID值，然后保存
          List<DishFlavor> flavors = dishDto.getFlavors();
          // 3.1 使用Stream处理集合，结果赋回给它自己
          // peek()是stream的中间操作，是对象的时候才能修改
          flavors = flavors.stream().peek(flavor -> flavor.setDishId(dishId)).collect(Collectors.toList());
  
          // 4.调用批量保存
          return dishFlavorService.saveBatch(flavors);
      }
  
      // 分页查询+按菜品名称查询
      @Override
      public Page<DishDto> getPage(Long page, Long pageSize, String name) {
          // 1.创建分页构造器
          Page<Dish> dishPage = new Page<>(page, pageSize);
          // 1.1 用于解决浏览器显示菜品分类名称，但需要给分页构造器赋值，采用复制dishPage的方法
          Page<DishDto> dishDtoPage = new Page<>();
  
          // 2.创建查询条件构造器
          LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
          // 3.添加过滤添加，按菜品名称查询
          lqw.like(Strings.isNotEmpty(name), Dish::getName, name);
          // 4.添加排序条件，按菜品更新时间降序排列
          lqw.orderByDesc(Dish::getUpdateTime);
          // 5.调用数据层的分页查询方法，此时dishPage中已经有值了
          page(dishPage, lqw);
  
          // 6.对象拷贝
          // 6.1 把dishPage中的属性值复制到dishDtoPage中，但要忽略dishPage中的records
          // 这是因为records中的数据是真正展示到浏览器上的，我们要处理一下records中的数据
          BeanUtils.copyProperties(dishPage, dishDtoPage, "records");
  
          // 7.使用stream处理dishRecords集合，目的是处理成dishDtoRecords的集合
          // 7.1 获取dishPage的records
          List<Dish> dishRecords = dishPage.getRecords();
          // 7.2 通过stream流处理dishRecords，目的是要根据分类ID查询分类表，最终获得分类名称
          List<DishDto> dishDtoRecords = dishRecords.stream().map(dish -> { // 遍历dishRecords集合中的每个dish，进行如下操作
  
              // 7.2.1 获取每个Dish的分类id
              Long categoryId = dish.getCategoryId();
              // 7.2.2 根据分类ID查询分类表，最终获得分类名称
              String categoryName = categoryService.getById(categoryId).getName();
              // 7.2.3 创建DishDto对象
              DishDto dishDto = new DishDto();
              // 7.2.4 先把Dish对象的所有属性拷贝到DishDto对象，然后再设置刚刚获得的分类名称
              BeanUtils.copyProperties(dish, dishDto);
              // 7.2.5 设置分类名称
              dishDto.setCategoryName(categoryName);
              // 7.2.6 返回全部赋值完成的dishDto
              return dishDto;
  
          }).collect(Collectors.toList());    // stream流的终止操作：返回一个List集合
  
          // 8.把处理好的dishDtoRecords赋回dishDtoPage中
          dishDtoPage.setRecords(dishDtoRecords);
  
          return dishDtoPage;
      }
  }
  ```




#### ② 表现层开发

- 打开表现层 `src/main/java/edu/ouc/controller/CategoryController.java` ：

  ```java
  // 分页查询+按菜品名称查询
  @GetMapping("/page")
  public R<Page<DishDto>> getPage(Long page, Long pageSize, String name) {
      return R.success(dishService.getPage(page, pageSize, name));
  }
  ```



#### ③ 解决依赖循环

- 这里我遇到了一个难题：`categoryServiceImpl` 中依赖了 `dishServiceImpl` ，而 `dishServiceImpl` 又依赖了 `categoryServiceImpl` ，这种相互依赖称为依赖循环。
- `categoryServiceImpl` –> `dishServiceImpl` –> `categoryServiceImpl` …
- 依赖循环是低质量代码的体现，官方从 Spring Boot 2.6 就开始禁止依赖循环。

```
The dependencies of some of the beans in the application context form a cycle:

   categoryController (field private edu.ouc.service.ICategoryService edu.ouc.controller.CategoryController.categoryService)
┌─────┐
|  categoryServiceImpl (field private edu.ouc.service.impl.DishServiceImpl edu.ouc.service.impl.CategoryServiceImpl.dishService)
↑     ↓
|  dishServiceImpl (field private edu.ouc.service.impl.CategoryServiceImpl edu.ouc.service.impl.DishServiceImpl.categoryService)
└─────┘
```

- 解决办法 (治标不治本) ：在相互依赖的两个 Bean 上分别加上 `@Lazy` 注解即可。

- `DishServiceImpl` ：

  ```java
  // 注入分类业务层依赖
  @Autowired
  @Lazy
  private CategoryServiceImpl categoryService;
  ```

- `CategoryServiceImpl` ：

  ```java
  // 注入菜品业务层
  @Autowired
  @Lazy
  private DishServiceImpl dishService;
  ```

- 真正的治本的方法是重新设计依赖。



### 3.3 功能测试

- 能正常显示出菜品的分类名称：

  ![image-20221004132155507](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221004132155507.png)





## 4. 修改菜品



### 4.1 需求分析

#### ① 功能分析

- 菜品修改首先要获取待修改菜品的信息，并回显到浏览器上；
- 第二步就是常规的以 PUT 请求向后端发送修改信息。



#### ② 请求分析

- 首先是待修改菜品信息回显，”修改“ 按钮以 GET 请求方式向后端请求，请求 URL 为 `/dish/菜品ID` 。

  ![image-20221007103259988](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007103259988.png)

  ![image-20221007103451270](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007103451270.png)

- 然后是把修改好的菜品信息以 PUT 请求方式，把修改后的信息传输到后端：

  ![image-20221007124909553](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007124909553.png)

  ![image-20221007125009781](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007125009781.png)

  ![image-20221007125102443](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007125102443.png)

  





### 4.2 代码开发

- 第一步：菜品信息回显；
- 第二步：修改：更新菜品基本信息；先清后加菜品口味表；

#### ① 业务层开发

- 业务层接口 `IDishService.java` ：

  ```java
  public interface IDishService extends IService<Dish> {
  
      // 新增菜品，同时插入菜品对应的口味数据
      Boolean saveWithFlavor(DishDto dishDto);
  
      // 分页查询+按菜品名称查询
      Page<DishDto> getPage(Long page, Long pageSize, String name);
  
      // 根据菜品ID查询菜品信息和对应的口味信息
      DishDto getByIdWithFlavor(Long id);
      
      // 修改菜品，同时修改菜品对应的口味数据
      Boolean updateWithFlavor(DishDto dishDto);
  }
  ```

- 业务层接口的实现类：

  ```java
  // 根据菜品ID查询菜品信息和对应的口味信息
  @Override
  public DishDto getByIdWithFlavor(Long id) {
  
      // 1.根据ID查询菜品基本
      Dish dish = getById(id);
  
      // 2.查询菜品口味
      LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();
      lqw.eq(DishFlavor::getDishId, id);
      List<DishFlavor> flavors = dishFlavorService.list(lqw);
  
      // 3.创建要返回的DishDto对象
      DishDto dishDto = new DishDto();
  
      // 4.把dish复制到dishDto中
      BeanUtils.copyProperties(dish, dishDto);
  
      // 5.把菜品口味封装到DishDto中
      dishDto.setFlavors(flavors);
  
      return dishDto;
  }
  
  
  // 修改菜品，同时修改菜品对应的口味数据
  @Override
  @Transactional  // 涉及到多张表操作，开启事务
  public Boolean updateWithFlavor(DishDto dishDto) {
  
      // 1.删除菜品口味表中对应菜品的口味
      LambdaQueryWrapper<DishFlavor> lqw = new LambdaQueryWrapper<>();
      lqw.eq(DishFlavor::getDishId, dishDto.getId());
      dishFlavorService.remove(lqw);
  
      // 2.截取dishDto中的菜品口味信息
      List<DishFlavor> flavors = dishDto.getFlavors();
  
      // 3.使用stream流操作为菜品口味集合设置对应的菜品ID
      flavors = flavors.stream().peek(flavor -> flavor.setDishId(dishDto.getId())).collect(Collectors.toList());
  
      // 4.批量添加修改后的菜品口味到菜品口味表中
      dishFlavorService.saveBatch(flavors);
  
      // 5.更新菜品基本信息
      return this.updateById(dishDto);
  }
  ```

  

#### ② 表现层开发

- 【注意】凡是涉及到多表联查，都要使用 DTO 的实体类。

- 打开表现层 `src/main/java/edu/ouc/controller/DishController.java` ：

  ```java
  // 根据菜品ID查询菜品信息和对应的口味信息
  @GetMapping("/{id}")
  public R<DishDto> getById(@PathVariable Long id) {
      return R.success(dishService.getByIdWithFlavor(id));
  }
  
  // 修改菜品，同时修改菜品对应的口味数据
  @PutMapping
  public R<String> update(@RequestBody DishDto dishDto) {
      if (dishService.updateWithFlavor(dishDto)) {
          return R.success("修改成功");
      }
      return R.error("修改失败");
  }
  ```



### 4.3 功能测试

![image-20221007132141375](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007132141375.png)

![image-20221007132211828](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007132211828.png)



## 5. 菜品启售/停售



### 5.1 需求分析

#### ① 功能分析

- 单个菜品停售与批量停售并没有什么区别，只是携带的菜品 ID 是一个还是多个而已。因此就把单个菜品与多个菜品的停售/启售都合并到一个方法里。

- 按 “停售” ，修改当前菜品的售卖状态。

  ![image-20221008153750674](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008153750674.png)

- 注意：已经是启售状态的菜品，再点击 “批量启售” 要不要给予相应的提示呢？



#### ② 业务分析

- 【注意】如果一个菜品停售，那么其关联的套餐 `setmeal` 也要停售。
- 要停售一个菜品之前，首先去 `setmeal_dish` 表中查询该菜品是否已经关联套餐。如果关联了套餐，则无法停售，抛出业务异常，提示用户必须先停售关联套餐，才能停售当前菜品。



#### ③ 请求分析

- ”停售“ 按钮以 POST 请求方式向后端请求，请求 URL 为 `/dish/status/0` 。同时携带要停售的菜品的 ID 。

- 单个菜品停售与批量停售并没有什么区别，只是携带的菜品 ID 是一个还是多个而已。

  ![image-20221008154004674](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008154004674.png)

  ![image-20221008154026244](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008154026244.png)



- ”启售“ 按钮以 POST 请求方式向后端请求，请求 URL 为 `/dish/status/1` 。同时携带要启售的菜品的 ID 。

  ![image-20221008154628608](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008154628608.png)

  ![image-20221008154657897](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008154657897.png)



### 5.2 代码开发

- 把单个菜品与多个菜品的停售/启售都合并到一个方法里。



#### ① 业务层开发

- 【业务逻辑】如果要停售则必须检查关联套餐是否停售。

- 为了实现这一点，先去 `Setmeal` 的业务层添加一个功能：根据菜品IDs集合，查询对应套餐Ids集合。

-  `Setmeal` 的业务层接口 `ISetmealService.java` ：

  ```java
  // 根据菜品IDs集合，查询对应套餐Ids集合
  Set<Long> getIdsByDishId(List<Long> dishIds);
  ```

-  `Setmeal` 的业务层接口实现类 `SetmealServiceImpl.java` ：

  ```java
  // 根据菜品IDs集合，查询对应套餐Ids集合
  @Override
  public Set<Long> getIdsByDishId(List<Long> dishIds) {
      // 1.创建setmeal_dish的过滤条件封装器
      LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
      // 2.添加过滤条件：IN()
      lqw.in(SetmealDish::getDishId, dishIds);
      // 3.查询对应的SetmealDish实体类集合
      List<SetmealDish> setmealDishes = setmealDishService.list(lqw);
      // 4.获取其setmealId的去重集合：Set集合是无序不可重复的
      return setmealDishes.stream().map(SetmealDish::getSetmealId).collect(Collectors.toSet());
  }
  ```



- 菜品 `Dish` 业务层接口 `IDishService.java` ：

  ```java
  // (批量)停售/启售菜品
  Boolean updateStatus(Integer status, List<Long> ids);
  ```

- 菜品 `Dish` 业务层接口实现类 `DishServiceImpl.java` ：

  ```java
  // (批量)停售/启售菜品
  @Override
  public Boolean updateStatus(Integer status, List<Long> ids) {
      // 业务逻辑：如果要停售则必须检查关联套餐是否停售
      if (status == 0) {
          // 根据菜品IDs获取其关联的套餐IDs
          Set<Long> setmealIds = setmealService.getIdsByDishId(ids);
          // 如果该菜品关联的套餐IDs不为空，才继续进行下一步
          if (!setmealIds.isEmpty()) {
              // 创建setmeal的过滤条件封装器
              LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();
              // 添加过滤条件：IN()套餐IDs
              lqw.in(Setmeal::getId, setmealIds);
              // 添加过滤条件：在售套餐
              lqw.eq(Setmeal::getStatus, 1);
              // 如果满足的setmeal记录数大于0，则说明关联套餐在售，抛出业务异常
              if (setmealService.count(lqw) > 0) {
                  throw new CustomException("停售失败，菜品所关联套餐仍在售，请停售相关套餐");
              }
          }
      }
      
      // 目的：尽量减少与MySQL通信的次数
      // 1.根据菜品ID集合批量查询菜品
      List<Dish> dishes = this.listByIds(ids);
  
      // 2.使用集合的stream流修改售卖状态
      dishes = dishes.stream().peek(dish -> {
          dish.setStatus(status);
      }).collect(Collectors.toList());
  
      // 3.批量update
      return this.updateBatchById(dishes);
  }
  ```




#### ② 表现层开发

- 打开表现层 `src/main/java/edu/ouc/controller/DishController.java` ：

  ```java
  // (批量)停售/启售菜品
  @PostMapping("/status/{status}")
  public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
      if (dishService.updateStatus(status, ids)) {
          return R.success("修改状态成功");
      }
      return R.error("修改状态失败");
  }
  ```



### 5.3 功能测试

![image-20221008200314305](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008200314305.png)

![image-20221008200337220](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008200337220.png)



## 6. 删除菜品



### 6.1 需求分析

#### ① 功能分析

- 菜品删除功能涉及到两张数据表，分别是 `dish` 和 `dish_flavor` 。用户点击 “删除” 按钮后，后端首先根据菜品 ID 删除 `dish` 中的关联菜品，再删除 `dish_flavor` 中对应的口味记录。

  ![image-20221008201908756](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008201908756.png)




#### ② 业务逻辑分析

- 【售卖状态】先判断要删除的菜品是否在售卖，如果在售则不能删除。应在页面弹出对话框是否要先停售，再删除？当用户点击 “确认” 时，执行停售和删除菜品。
- 【套餐绑定】如果一个菜品将要被删除，先去判断该菜品是否已经绑定了套餐。如果已经绑定了套餐，则无法删除，抛出一个业务异常，提示用户只有删除了改套餐才能删除菜品；如果没有绑定套餐，且该菜品已经停售，则可以直接删除 `dish` 数据表中的记录，然后删除 `dish_flavor` 中的记录。



#### ③ 请求分析

- ”删除“ 按钮以 DELETE请求方式向后端请求，请求 URL 为 `/dish` 。同时携带要删除的菜品的 ID 。

- 单个菜品删除与批量删除并没有什么区别，只是携带的菜品 ID 是一个还是多个而已。

  ![image-20221008203442439](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008203442439.png)

  ![image-20221008203500835](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221008203500835.png)

  



### 6.2 代码开发



- 把单个菜品与多个菜品的删除都合并到一个方法里。



#### ① 业务层开发

- 删除菜品业务层需要依赖套餐管理的业务层 `SetmealServiceImpl.java` 。

- 在套餐业务层接口 `ISetmealService.java` 添加根据菜品IDs集合，返回对应套餐 IDs 集合的功能。

  ```java
  // 根据菜品IDs集合，查询对应套餐Ids集合
  Set<Long> getIdsByDishId(List<Long> dishIds);
  ```

- 套餐业务层接口实现类 `SetmealServiceImpl.java` ：

  ```java
  // 根据菜品IDs集合，查询对应套餐Ids集合
  @Override
  public Set<Long> getIdsByDishId(List<Long> dishIds) {
      // 1.创建setmeal_dish的过滤条件封装器
      LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
      // 2.添加过滤条件：IN()
      lqw.in(SetmealDish::getDishId, dishIds);
      // 3.查询对应的SetmealDish实体类集合
      List<SetmealDish> setmealDishes = setmealDishService.list(lqw);
      // 4.获取其setmealId的去重集合：Set集合是无序不可重复的
      return setmealDishes.stream().map(SetmealDish::getSetmealId).collect(Collectors.toSet());
  }
  ```



- 菜品业务层接口 `IDishService.java` ：

  ```java
  // 删除(批量删除)菜品
  Boolean removeWithFlavor(List<Long> ids);
  ```

- 菜品业务层接口实现类 `DishServiceImpl.java` ：

  ```java
  // 删除(批量删除)菜品
  @Override
  public Boolean removeWithFlavor(List<Long> ids) {
      // 1.判断待删除菜品是否正在售卖
      // 1.1 创建dish的条件封装器
      LambdaQueryWrapper<Dish> dishLqw = new LambdaQueryWrapper<>();
      // 1.2 添加过滤条件：IN()-菜品ID要在入参ids中
      dishLqw.in(Dish::getId, ids);
      // 1.3 添加过滤条件：等值-菜品在售
      dishLqw.eq(Dish::getStatus, 1);
      // 1.4 调用数据层，根据条件封装器dishLqw的条件查询dish构成的集合
      List<Dish> dishes = this.list(dishLqw);
      // 1.5 如果dish集合不为空，说明待删除菜品中有在售菜品，抛出业务异常
      if (!dishes.isEmpty()) {
          // 1.6 获取在售菜品名称，方便用户停售对应菜品
          List<String> dishNames = dishes.stream().map(Dish::getName).collect(Collectors.toList());
          // 1.7 把在售菜品名称也返回到前端页面，方便用户停售对应菜品
          throw new CustomException(dishNames + "正在售卖，删除失败，请停售后重试");
      }
  
      // 2.判断待删除菜品关联套餐是否在售
      // 2.1 根据菜品IDs获取所关联的所有套餐IDs
      Set<Long> setmealIds = setmealService.getIdsByDishId(ids);
      // 2.2 创建setmeal的条件封装器
      LambdaQueryWrapper<Setmeal> setmealLqw = new LambdaQueryWrapper<>();
      // 2.3 添加过滤条件：IN()-套餐ID要在setmealIds中
      setmealLqw.in(Setmeal::getId, setmealIds);
      // 2.4 调用数据层，根据条件封装器setmealLqw的条件查询setmeal构成的集合
      List<Setmeal> setmeals = setmealService.list(setmealLqw);
      // 2.5 如果setmeal集合不为空，说明待删除菜品已绑定套餐，抛出业务异常
      if (!setmeals.isEmpty()) {
          // 2.6 获取绑定套餐名称，方便用户删除对应套餐
          List<String> setmealNames = setmeals.stream().map(Setmeal::getName).collect(Collectors.toList());
          // 2.7 把绑定套餐名称也返回到前端页面，方便用户删除对应套餐
          throw new CustomException(setmealNames + "套餐正在售卖该菜品，删除失败，请删除套餐后重试");
      }
  
      // 批量删除菜品
      this.removeByIds(ids);
  
      // 删除菜品口味
      LambdaQueryWrapper<DishFlavor> dishFlavorLqw = new LambdaQueryWrapper<>();
      dishFlavorLqw.in(DishFlavor::getDishId, ids);
      return dishFlavorService.remove(dishFlavorLqw);
  }
  ```




#### ② 表现层开发

- 打开表现层 `src/main/java/edu/ouc/controller/DishController.java` ：

  ```java
  // 删除(批量删除)菜品
  @DeleteMapping
  public R<String> remove(@RequestParam List<Long> ids) {
      if (dishService.removeWithFlavor(ids)) {
          return R.success("删除成功");
      }
      return R.error("删除失败");
  }
  ```



### 6.3 功能测试







## 7. 多表联查心得

- 多表联查，根据联查所 SELECT 的字段不同，你要创建不同的 DTO 实体类，去封装原本的数据表和联查数据表的字段。
- 涉及多张表的新增、修改操作，一定要在对应的业务层实现类方法上添加事务注解 `@Transactional` 。





# 八、 套餐管理业务开发



## 1. 新增套餐



### 1.1 需求分析



#### ① 功能分析

- 套餐管理页面：

  ![image-20221003100811478](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003100811478.png)

- 套餐分类会在 “套餐管理” 新建套餐时充当属性：

  ![image-20221002142535653](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221002142535653.png)
  
- 要选择该套餐应该包含哪些菜品：

  ![image-20221012111909935](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221012111909935.png)

  



#### ② 业务分析

- 当用户点击 “+添加菜品” 按钮时，不能展示停售的菜品。
- 当用户点击 “保存” 按钮时，要把 `SetmealDishes` 集合中的每个 `setmealDish` 逐一取出，通过 stream 流给每个 `setmealDish` 设置绑定套餐 ID `setmeal_id` 。



#### ③ 请求分析

- 当用户点击 “新建套餐” 按钮后，进入添加套餐的页面，前端立马以 GET 方式向后端 URL 为 `/dish/list` 发起请求，并携带一个分类 ID `categoryId` ：

  ![image-20221012121113703](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221012121113703.png)

- 可以发现，该请求携带的分类 ID `categoryId` 正是 category 数据表中 `sort` 字段为 1 的分类，我这里是粤菜排第一，所以发送的是粤菜这个分类的 ID 给后端。因为当用户点击 “+添加菜品” 按钮时，需要把系统里已有的菜品信息展示给用户选择。

  ![image-20221012121314211](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221012121314211.png)

- 因此，需要回到菜品控制层、菜品业务层去添加展示分类所有菜品的功能。





- 当用户填写好新增套餐信息，点击 ”保存“ 时，查看浏览器控制台：

  ![image-20221012150526592](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221012150526592.png)

  ![image-20221012150606592](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221012150606592.png)

  ![image-20221012150631735](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221012150631735.png)

  

- 可以看到，新增套餐时，前端以 POST 方式向后端发起请求，请求 URL 是 `/setmeal` ，并把套餐信息都发送到后端。





### 1.2 数据模型

- 套餐管理涉及到的数据表就是 `setmeal` 和 `setmeal_dish` 。

- `setmeal` 是单纯记录有什么套餐：

  ![image-20221003101225209](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221003101225209.png)

- 相应的字段介绍：

  |    字段     |          描述          |
  | :---------: | :--------------------: |
  |     id      |         套餐ID         |
  | category_id |       套餐分类ID       |
  |    name     |  套餐名称 (唯一约束)   |
  |    price    |        套餐价格        |
  |   status    | 售卖状态，0停售，1启售 |
  |    code     |         商品码         |
  | description |        套餐描述        |
  |    image    |        套餐图片        |
  | create_time |      创建日期时间      |
  | update_time |      修改日期时间      |
  | create_user |        创建人ID        |
  | update_user |        修改人ID        |
  | is_deleted  |       是否已删除       |

- 而 `setmeal_dish` 则是记录每个套餐所关联的菜品：

  ![image-20221012110857970](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221012110857970.png)

- 相应的字段介绍：
  
  |    字段     |            描述            |
  | :---------: | :------------------------: |
  |     id      | 这张表自己的套餐菜品ID主键 |
  | setmeal_id  |  `setmeal` 数据表的套餐ID  |
  |   dish_id   |   `dish` 数据表的菜品ID    |
  |    name     |          菜品名称          |
  |    price    |          菜品价格          |
  |   copies    |          菜品份数          |
  |    sort     |            排序            |
  | create_time |        创建日期时间        |
  | update_time |        修改日期时间        |
  | create_user |          创建人ID          |
  | update_user |          修改人ID          |
  | is_deleted  |         是否已删除         |





### 1.3 代码开发



#### ① 创建实体类

- 创建 `src/main/java/edu/ouc/entity/Setmeal.java` ：

  ```java
  @Data
  public class Setmeal {
      
      private static final Long serialVersionUID = 123L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
  	@JsonSerialize(using = ToStringSerializer.class)
      private Long categoryId;
      private String name;
      private BigDecimal price;
      private Integer status;
      private String code;
      private String description;
      private String image;
      private Integer isDeleted;
  
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;
  
      @TableField(fill = FieldFill.INSERT)
      private Long createUser;
  
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long updateUser;
  }
  ```
  
- 创建数据表 `setmeal_dish` 的实体类 `src/main/java/edu/ouc/entity/SetmealDish.java` ：

  ```java
  @Data
  public class SetmealDish {
      private static final Long serialVersionUID = 38L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
      @JsonSerialize(using = ToStringSerializer.class)
      private Long setmealId;
      @JsonSerialize(using = ToStringSerializer.class)
      private Long dishId;
  
      // 菜品名称（冗余字段）
      private String name;
      // 菜品原价
      private BigDecimal price;
      // 菜品份数
      private Integer copies;
      // 排序
      private Integer sort;
      private Integer isDeleted;
  
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;
      @TableField(fill = FieldFill.INSERT)
      private Long createUser;
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long updateUser;
  }
  ```



#### ② 创建SetmealDto

- 由于涉及到两张数据表的多表操作，因此必须创建 DTO 实体类来封装两张表的字段信息。在继承 Setmeal 类的基础上，扩展了 2 个属性：套餐菜品集合 `setmealDishes` 和套餐所属的类别名称 `categoryName` 。

- 创建 `src/main/java/edu/ouc/dto/SetmealDto.java` ：

  ```java
  @Data
  public class SetmealDto extends Setmeal {
      private List<SetmealDish> setmealDishes;
      private String categoryName;
  }
  ```

  

#### ② 数据层开发

- 创建 `src/main/java/edu/ouc/mapper/SetmealMapper.java` ：

  ```java
  @Mapper
  public interface SetmealMapper extends BaseMapper<Setmeal> {
  }
  ```
  
- 创建数据表 `setmeal_dish` 的 Mapper `src/main/java/edu/ouc/mapper/SetmealDishMapper.java` ：

  ```java
  @Mapper
  public interface SetmealDishMapper extends BaseMapper<SetmealDish> {
  }
  ```



#### ③ 业务层开发

- 菜品表 `dish` 业务层接口添加根据分类 ID 展示所有的菜品。此处有一个技巧，前端虽然传输过来的是 分类 ID ，但是我们却可以通过 Dish 类来接收，以增强通用性。

- 此外，获取菜品列表时还有一个业务逻辑：停售的菜品不应展示在页面上，因此要在条件过滤器上添加这一条件。

- `src/main/java/edu/ouc/service/IDishService.java` ：

  ```java
  // 根据指定过滤条件查询菜品
  List<Dish> list(Dish dish);
  ```

- 菜品表 `dish` 业务层接口实现类 `src/main/java/edu/ouc/service/impl/DishServiceImpl.java` ：

  ```java
  // 根据指定过滤条件查询菜品
  @Override
  public List<Dish> list(Dish dish) {
      // 1.创建条件过滤器
      LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
      // 2.添加过滤条件：根据分类ID查询菜品
      lqw.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
      // 3.添加排序条件：根据sort字段升序排列菜品，再根据最后修改时间降序排列
      lqw.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
      // 4.条件过滤条件：只查询启售的菜品
      lqw.eq(Dish::getStatus, 1);
      // 5.调用数据层的查询方法
      return this.list(lqw);
  }
  ```



- 接下来是用户点击 “保存” 按钮之后的逻辑，主要任务是把前端传输来的信息保存到两张数据表 `setmeal` 和 `setmeal_dish` 中。
- 【注意】多表操作切记添加事务注解 `@Transactional` ！！！
- 因为涉及到多表的插入，因此用 setmeal 的数据传输对象 `SetmealDto` 来接收。
- 当用户点击 “保存” 按钮时，要把 `SetmealDishes` 集合中的每个 `setmealDish` 逐一取出，通过 stream 流给每个 `setmealDish` 设置绑定套餐 ID `setmeal_id` 。

- 套餐表 `setmeal` 创建业务层接口 `src/main/java/edu/ouc/service/ISetmealService.java` ：

  ```java
  public interface ISetmealService extends IService<Setmeal> {
      // 新增套餐，同时插入套餐对应菜品
      Boolean saveWithSetmealDishes(SetmealDto setmealDto);
  }
  ```

- 套餐表 `setmeal` 创建业务层实现类 `src/main/java/edu/ouc/service/impl/SetmealServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {
      
      @Autowired
      private ISetmealDishService setmealDishService;
      
      @Autowired
      private ICategoryService categoryService;
  
      // 新增套餐，同时插入套餐对应菜品
      @Override
      @Transactional  // 多表操作切记添加事务注解
      public Boolean saveWithSetmealDishes(SetmealDto setmealDto) {
          // 1.保存套餐的基本信息
          this.save(setmealDto);
  
          // 2.获取套餐菜品集合
          List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
  
          // 3.以stream流的方式设置每个套餐菜品的setmeal_id
          setmealDishes = setmealDishes.stream().peek(setmealDish -> {
              setmealDish.setSetmealId(setmealDto.getId());
          }).collect(Collectors.toList());
  
          // 4.批量保存套餐菜品
          return setmealDishService.saveBatch(setmealDishes);
      }
  }
  ```



- 套餐菜品关系表 `setmeal_dish` 创建套餐菜品关联表的业务层接口 `src/main/java/edu/ouc/service/ISetmealDishService.java` ：

  ```java
  public interface ISetmealDishService extends IService<SetmealDish> {
  }
  ```

- 套餐菜品关系表 `setmeal_dish` 创建套餐菜品关联表的业务层接口的实现类 `src/main/java/edu/ouc/service/impl/SetmealDishServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements ISetmealDishService {
  }
  ```



#### ④ 表现层开发

- 菜品表 `dish` 表现层 `src/main/java/edu/ouc/controller/DishController.java` ：

  ```java
  // 根据指定过滤条件查询菜品
  @GetMapping("/list")
  public R<List<Dish>> listByCategoryId(Dish dish) {
      return R.success(dishService.list(dish));
  }
  ```



- controller 层只需要创建套餐的 controller 层即可。

- 创建 `src/main/java/edu/ouc/controller/SetmealController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/setmeal")
  public class SetmealController {
  
      @Autowired
      private ISetmealService setmealService;
  
      @Autowired
      private ISetmealDishService setmealDishService;
  }
  ```



### 1.4 功能测试

略。





## 2. 套餐信息分页查询



### 2.1 需求分析

#### ① 功能分析

- 本节要实现的功能是：当用户点击 ”套餐管理“ 后，把套餐都分页展示出来。

- 此外，还需要把套餐的分类名称显示出来：

  ![image-20221014143836058](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221014143836058.png)

- 需要查询的数据表有 `setmeal` 和 `categoyr` 。因此传输的实体类对象应该是 `SetmealDto` 。只是涉及到多表查询，不需要加上事务注解。



#### ② 请求分析

- 当用户点击 ”套餐管理“ 后，页面以 GET 请求方式向后端请求，请求 URL 为 `/setmeal/page` 。并把当前页码和每页记录数发送到后端。

  ![image-20221014141753478](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221014141753478.png)

  ![image-20221014141903281](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221014141903281.png)

- 当用户搜索套餐名字时，会再添加多一个查询条件 `name` ：

  ![image-20221014142235000](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221014142235000.png)

  ![image-20221014142251213](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221014142251213.png)

  ![image-20221014142303780](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221014142303780.png)





### 2.2 代码开发

- 和之前的分页查询一样，也是把分页查询和搜索合并在一个方法里。

#### ① 业务层开发

- 套餐 `setmeal` 业务层接口 `ISetmealService.java` ：

  ```java
  public interface ISetmealService extends IService<Setmeal> {
  
      // 新增套餐，同时插入套餐对应菜品
      Boolean saveWithSetmealDishes(SetmealDto setmealDto);
  
      // 带搜索功能的分页查询
      Page<SetmealDto> getPage(Long currentPage, Long pageSize, String name);
  }
  ```

- 套餐 `setmeal` 业务层接口的实现类 `SetmealServiceImpl.java` ：

  ```java
  // 带搜索功能的分页查询
  @Override
  public Page<SetmealDto> getPage(Long currentPage, Long pageSize, String name) {
      // 1.创建Setmeal的分页构造器
      Page<SetmealDto> dtoPage = new Page<>(currentPage, pageSize);
      // 2.创建SetmealDto的分页构造器
      Page<Setmeal> page = new Page<>(currentPage, pageSize);
  
      // 3.创建Setmeal的条件查询过滤器
      LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();
      // 4.条件过滤条件：根据套餐名称模糊匹配
      lqw.like(name != null, Setmeal::getName, name);
      // 5.调用数据层的分页查询+条件查询
      this.page(page, lqw);
  
      // 6.复制分页构造器：除了记录其他都复制
      BeanUtils.copyProperties(page, dtoPage, "records");
  
      // 7.获取Setmeal Page的记录集合
      List<Setmeal> setmeals = page.getRecords();
  
      // 8.通过集合stream流逐一给SetmealDtos赋上类别名
      List<SetmealDto> setmealDtos = setmeals.stream().map(setmeal -> {
          // 8.1 创建SetmealDto对象
          SetmealDto setmealDto = new SetmealDto();
          // 8.2 把setmeal中的信息复制到setmealDto中
          BeanUtils.copyProperties(setmeal, setmealDto);
          // 8.3 获取类别名称
          String categoryName = categoryService.getById(setmeal.getCategoryId()).getName();
          // 8.4 给setmealDto设置类别名称
          setmealDto.setCategoryName(categoryName);
          // 8.5 返回setmealDto对象
          return setmealDto;
      }).collect(Collectors.toList());
  
      // 给设置Records
      dtoPage.setRecords(setmealDtos);
  
      return dtoPage;
  }
  ```

  

#### ② 表现层开发

- 【注意】凡是涉及到多表联查，都要使用 DTO 的实体类。

- 打开表现层 `src/main/java/edu/ouc/controller/SetmealController.java` ：

  ```java
  // 带搜索功能的分页查询
  @GetMapping("/page")
  public R<Page<SetmealDto>> getPage(Long page, Long pageSize, String name) {
      return R.success(setmealService.getPage(page, pageSize, name));
  }
  ```



### 2.3 功能测试

![image-20221014153232815](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221014153232815.png)





## 3. 修改套餐



### 3.1 需求分析

#### ① 功能分析

- 套餐修改首先要获取待修改套餐的信息，并回显到浏览器上；
- 第二步就是常规的以 PUT 请求向后端发送修改信息。



#### ② 业务分析





#### ③ 请求分析

- 首先是待修改套餐信息回显，”修改“ 按钮以 GET 请求方式向后端请求，请求 URL 为 `/setmeal/套餐ID` 。

  ![image-20221007103259988](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221007103259988.png)

  ![image-20221016163758767](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221016163758767.png)

- 然后是把修改好的套餐信息以 PUT 请求方式，把修改后的信息传输到后端：

  ![image-20221016171403893](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221016171403893.png)

  ![image-20221016171438030](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221016171438030.png)

  ![image-20221016171524854](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221016171524854.png)

  



### 3.2 代码开发

- 第一步：套餐信息回显；涉及到 `setmeal` 和 `setmeal_dish` 两张表，应当以 `SetmealDto` 类返回。
- 第二步：修改：更新套餐基本信息；先清后加套餐-菜品关系表；涉及到更新 `setmeal` 和 `setmeal_dish` 两张表，牢记一定要使用事务注解。

#### ① 业务层开发

- 套餐业务层接口 `ISetmealService.java` ：

  ```java
  public interface ISetmealService extends IService<Setmeal> {
  
      // 新增套餐，同时插入套餐对应菜品
      Boolean saveWithSetmealDishes(SetmealDto setmealDto);
  
      // 带搜索功能的分页查询
      Page<SetmealDto> getPage(Long currentPage, Long pageSize, String name);
  
      // 根据套餐ID查询单个带套餐菜品的套餐信息
      SetmealDto getByIdWithDishes(Long id);
      
      // 修改套餐信息和套餐关联菜品
      Boolean updateWithDishes(SetmealDto setmealDto);
  }
  ```

- 回显功能套餐业务层接口的实现类 `SetmealServiceImpl.java` ：

  ```java
  // 根据套餐ID查询单个带套餐菜品的套餐信息
  @Override
  public SetmealDto getByIdWithDishes(Long id) {
      // 1.获取套餐的基本信息
      Setmeal setmeal = this.getById(id);
  
      // 2.获取套餐所有关联菜品构成的列表
      LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
      lqw.eq(SetmealDish::getSetmealId, id);
      lqw.orderByDesc(SetmealDish::getUpdateTime);
      List<SetmealDish> setmealDishes = setmealDishService.list(lqw);
  
      // 3.把套餐基本信息和套餐菜品封装到SetmealDto对象中
      SetmealDto setmealDto = new SetmealDto();
      BeanUtils.copyProperties(setmeal, setmealDto);
      setmealDto.setSetmealDishes(setmealDishes);
  
      return setmealDto;
  }
  ```

- 修改功能套餐业务层接口的实现类 `SetmealServiceImpl.java` ：

  ```java
  // 修改套餐信息和套餐关联菜品
  @Override
  @Transactional  // 修改两张表不能忘记事务注解
  public Boolean updateWithDishes(SetmealDto setmealDto) {
      // 1.修改setmeal基本信息
      this.updateById(setmealDto);
  
      // 2.把setmeallDish信息抽取出来
      List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
  
      // 3.通过集合的stream流给套餐关联的菜品逐一赋上套餐ID
      setmealDishes = setmealDishes.stream().peek(setmealDish -> {
          setmealDish.setSetmealId(setmealDto.getId());
      }).collect(Collectors.toList());
  
      // 4.把setmeal_dish表中原有的套餐菜品删除掉
      LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
      lqw.eq(SetmealDish::getSetmealId, setmealDto.getId());
      setmealDishService.remove(lqw);
  
      // 5.把新的套餐菜品插入到setmeal_dish表中
      return setmealDishService.saveBatch(setmealDishes);
  }
  ```



#### ② 表现层开发

- 【注意】凡是涉及到多表联查，都要使用 DTO 的实体类。

- 打开表现层 `src/main/java/edu/ouc/controller/DishController.java` ：

  ```java
  // 根据菜品ID查询菜品信息和对应的口味信息
  @GetMapping("/{id}")
  public R<DishDto> getById(@PathVariable Long id) {
      return R.success(dishService.getByIdWithFlavor(id));
  }
  
  // 修改菜品，同时修改菜品对应的口味数据
  @PutMapping
  public R<String> update(@RequestBody DishDto dishDto) {
      if (dishService.updateWithFlavor(dishDto)) {
          return R.success("修改成功");
      }
      return R.error("修改失败");
  }
  ```



#### ③ 前端Bug修复

- 修改功能基本完成后，回显时前端有个 Bug ，原本已选的菜品名称无法显示，但是新选的菜品则可以正常显示。

  ![image-20221016202745934](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221016202745934.png)

- 解决办法是在 `src/main/resources/static/backend/page/combo/add.html` 的第 335 行代码，把代码从原来的：

  ```html
  <span>{{ item.dishName }}</span>
  ```

  改成：

  ```html
  <span>{{ item.name }}</span>
  ```

  即可。

- 因为菜品实体类 `Dish` 中菜品名称的属性是用 `name` 表示而不是 `dishName` ：

  ![image-20221016203126870](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221016203126870.png)

- 修改后，重启服务，就能正确显示了：

  ![image-20221016203224577](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221016203224577.png)



### 3.3 功能测试

略。





## 4. 套餐启售/停售



### 4.1 需求分析

#### ① 功能分析

- 单个套餐停售与批量停售并没有什么区别，只是携带的套餐 ID 是一个还是多个而已。因此就把单个套餐与多个套餐的停售/启售都合并到一个方法里。

- 按 “停售” ，修改当前套餐的售卖状态。

  ![image-20221017102945684](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221017102945684.png)

- 注意：已经是启售状态的套餐，再点击 “批量启售” 要不要给予相应的提示呢？



#### ② 业务分析

- 如果一个套餐停售，其实并不会影响其关联菜品的售卖状态。其关联菜品仍然可以作为一个单体继续售卖。
- 如果一个套餐启售，需要确认：
  - 第一，其关联菜品必须处于 “启售” 状态；
  - 第二，其关联菜品没有被删除。




#### ③ 请求分析

- ”停售“ 按钮以 POST 请求方式向后端请求，请求 URL 为 `/setmeal/status/0` 。同时携带要停售的套餐的 ID 。

- 单个套餐停售与批量停售并没有什么区别，只是携带的套餐 ID 是一个还是多个而已。

  ![image-20221017103630741](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221017103630741.png)

  ![image-20221017103717044](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221017103717044.png)



- ”启售“ 按钮以 POST 请求方式向后端请求，请求 URL 为 `/setmeal/status/1` 。同时携带要启售的套餐的 ID 。

  ![image-20221017103819049](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221017103819049.png)

  ![image-20221017103833681](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221017103833681.png)



### 4.2 代码开发

- 把单个菜品与多个菜品的停售/启售都合并到一个方法里。

- 【注意】多个 ID 要使用集合接收时，其前面要加上 `@RequestParam` 注解。

#### ① 业务层开发

- 业务层接口 `ISetmealService.java` ：

  ```java
  // (批量)套餐启售/停售
  Boolean updateStatus(Integer status, List<Long> ids);
  ```

- 如果一个套餐停售，其实并不会影响其关联菜品的售卖状态。其关联菜品仍然可以作为一个单体继续售卖。

- 如果一个套餐启售，需要确认：

  - 第一，其关联菜品必须处于 “启售” 状态；
  - 第二，其关联菜品没有被删除。

- 业务层接口实现类 `SetmealServiceImpl.java` ：

  ```java
  // (批量)套餐启售/停售
  @Override
  public Boolean updateStatus(Integer status, List<Long> ids) {
  
      // 启售套餐前，应检查套餐关联菜品是否存在且在售
      if (status == 1) {
          // 根据套餐IDs从setmeal_dish表查询对应的菜品IDs
          Set<Long> dishIds = setmealDishService.getDishIdsBySetmealId(ids);
  
          // 创建dish的条件包装器
          LambdaQueryWrapper<Dish> lqw1 = new LambdaQueryWrapper<>();
          // 添加过滤条件：查询停售的dish
          lqw1.eq(Dish::getStatus, 0);
          // 根据条件封装器lqw查询满足条件的dish实体类对象构成的集合
          List<Dish> dishes = dishService.list(lqw1);
          // 如果dish集合不为空，说明套餐菜品中有停售菜品，套餐不能启售，抛出业务异常
          if (!dishes.isEmpty()) {
              // 获取已停售菜品的名称
              List<String> dishNames = dishes.stream().map(Dish::getName).collect(Collectors.toList());
              // 向前端页面展示已停售菜品的名称，方便用户启售对应菜品
              throw new CustomException("套餐所关联菜品" + dishNames + "已停售，启售失败");
          }
  
          // 检查菜品有没有被删除
          LambdaQueryWrapper<Dish> lqw2 = new LambdaQueryWrapper<>();
          lqw2.in(Dish::getId, dishIds);
          List<Dish> dishes1 = dishService.list(lqw2);
          // 如果套餐菜品个数与dish数据表中根据菜品ID查询回来的个数不相等，说明dish数据表中有菜品被删除了
          if (dishes1.size() != dishIds.size()) {
              throw new CustomException("套餐中有菜品被删除，启售失败");
          }
      }
  
      // 1.根据套餐ID集合批量查询套餐
      List<Setmeal> setmeals = this.listByIds(ids);
  
      // 2.使用stream流逐一修改其售卖状态
      setmeals = setmeals.stream().peek(setmeal -> setmeal.setStatus(status)).collect(Collectors.toList());
  
      // 3.批量修改
      return this.updateBatchById(setmeals);
  }
  ```




#### ② 表现层开发

- 打开表现层 `SetmealController.java` ：

  ```java
  // (批量)套餐启售/停售
  @PostMapping("/status/{status}")
  public R<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
      if (setmealService.updateStatus(status, ids)) {
          return R.success("修改成功");
      }
      return R.error("修改失败");
  }
  ```



### 4.3 功能测试

略。





## 5. 删除套餐



### 5.1 需求分析

#### ① 功能分析

- 套餐删除功能涉及到两张数据表，分别是 `setmeal` 和 `setmeal_dish` 。用户点击 “删除” 按钮后，后端首先根据套餐 ID 删除 `setmeal_dish` 中的关联菜品，再删除 `setmeal` 中对应的套餐记录。

  ![image-20221019165339588](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221019165339588.png)



#### ② 业务逻辑分析

- 删除套餐前，必须确保待删除套餐处于停售状态。不然就会出现 C 端客户正在点套餐呢，突然点了的套餐就没了的情况。即，先判断待删除套餐的售卖状态：如果套餐在售，则抛出一个业务异常；如果套餐停售，则可以删除。
- 删除套餐并不影响其关联菜品的售卖。因此，一个套餐删除后，其关联的菜品不会受到影响。



#### ③ 请求分析

- ”删除“ 按钮以 DELETE请求方式向后端请求，请求 URL 为 `/dish` 。同时携带要删除套餐的 ID 。

- 单个套餐删除与批量删除并没有什么区别，只是携带的套餐 ID 是一个还是多个而已。

  ![image-20221019165553369](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221019165553369.png)

  ![image-20221019165707580](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221019165707580.png)

  



### 5.2 代码开发



- 把单个菜品与多个菜品的删除都合并到一个方法里。



#### ① 业务层开发

- 业务层接口 `ISetmealService.java` ：

  ```java
  // 根据套餐ID删除(批量删除)套餐
  Boolean removeWithDish(List<Long> ids);
  ```

- 【我的思路】关于业务层接口实现类 `SetmealServiceImpl.java` ，我先按自己的思路写了一版：就是在判断是否存在在售套餐时，采用了集合的 stream 流逐一判断：

  ```java
  // 根据套餐ID删除(批量删除)套餐
  @Override
  @Transactional // 删除两张表必须添加事务注解
  public Boolean removeWithDish(List<Long> ids) {
  
      // 1.首先判断当前套餐的售卖状态
      // 1.1 获取待删除套餐构成的集合
      List<Setmeal> setmeals = this.listByIds(ids);
      // 1.2 使用stream流逐一检查集合内的套餐元素售卖状态
      boolean isRemovable = setmeals.stream().anyMatch(setmeal -> 1 == setmeal.getStatus());
      // 1.3 如果存在在售的套餐，则抛出业务异常，无法删除
      if (isRemovable) {
          throw new CustomException("所删除套餐中有在售套餐，删除失败，请停售后再删除");
      }
  
      // 2.能走到这里，就是已经全部停售了。首先根据套餐ID删除数据表setmeal_dish中的关联菜品
      // 2.1创建过滤条件封装器
      LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
      // 2.2添加过滤条件：IN()
      lqw.in(SetmealDish::getSetmealId, ids);
      // 2.3删除setmeal_dish表中所有的套餐关联菜品
      setmealDishService.remove(lqw);
  
  
      // 3.删除setmeal表中的套餐记录
      return this.removeByIds(ids);
  }
  ```
  
  代码功能实现没问题。但弊端是，如果第 8 行返回的集合中套餐很多，逐一检查其售卖状态是一种低效的方式。
  
- 【老师思路】业务层接口实现类 `SetmealServiceImpl.java` 。老师的思路巧妙之处在于，两个查询条件：① 根据入参 `ids` ；② `status` = 1 。如果查询的 `COUNT` > 0 ，说明待删除套餐中有在售套餐，不能删除，抛出业务异常。

  ```java
  // 根据套餐ID删除(批量删除)套餐
  @Override
  @Transactional // 删除两张表必须添加事务注解
  public Boolean removeWithDish(List<Long> ids) {
      // 1.首先判断当前套餐的售卖状态
      // 1.1 创建过滤条件封装器
      LambdaQueryWrapper<Setmeal> wq = new LambdaQueryWrapper<>();
      // 1.2 添加包含条件IN()，待删除套餐的ID
      wq.in(Setmeal::getId, ids);
      // 1.3 添加过滤条件：在售的套餐
      wq.eq(Setmeal::getStatus, 1);
      // 1.4 如果查询结果记录数大于0，说明待删除套餐中包含在售套餐，直接抛出业务异常
      if (this.count(wq) > 0) {
          throw new CustomException("所删除套餐中包含在售套餐，删除失败，请停售后再删除");
      }
  
      // 2.能走到这里，就是已经全部停售了。首先删除setmeal表中的套餐记录
      this.removeByIds(ids);
  
      // 3.根据套餐ID删除数据表setmeal_dish中的关联菜品
      // 3.1创建过滤条件封装器
      LambdaQueryWrapper<SetmealDish> lqw = new LambdaQueryWrapper<>();
      // 3.2添加过滤条件：IN()
      lqw.in(SetmealDish::getSetmealId, ids);
      // 3.3删除setmeal_dish表中所有的套餐关联菜品
      return setmealDishService.remove(lqw);
  }
  ```




#### ② 表现层开发

- 打开表现层 `SetmealController.java` ：

  ```java
  // 根据套餐ID删除(批量删除)套餐
  @DeleteMapping
  public R<String> removeWithDishes(@RequestParam List<Long> ids) {
      if (setmealService.removeWithDish(ids)) {
          return R.success("删除成功");
      }
      return R.error("删除失败");
  }
  ```



### 5.3 功能测试

- 删除在售套餐：

  ![image-20221019212618655](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221019212618655.png)

- 删除失败：

  ![image-20221019212638989](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221019212638989.png)







# 九、 邮箱验证码登录(替换短信)

> - 由于我不想花钱开通手机验证码登录，因此我使用邮箱验证码登录来替换短信验证码。



## 1. 邮箱准备

- 这里笔者使用 QQ 邮箱，首先要开启 POP3/STMP 服务，获取一个 16 位的授权码。**zqvh****crhm****ebjg****bedb**

  ![image-20221022094654638](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221022094654638.png)

  ![image-20221022094543835](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221022094543835.png)





## 2. 邮箱验证码登录



### 2.1 需求分析



#### ① 功能分析

- 为了方便用户登录，移动端通常都会提供通过手机验证码登录的功能。手机验证码登录的优点：
  - 方便快捷，无需注册，直接登录。
  - 使用短信验证码作为登录凭证，无需记忆密码。
  - 安全
- 登录流程：输入手机号>获取验证码>输入验证码>点击登录>登录成功
- 注意：通过手机验证码登录,手机号是区分不同用户的标识。

> 把上述手机短信换成邮箱即可。




#### ② 业务逻辑分析

- 



#### ③ 请求分析

- 获取邮箱验证码的请求分析，详见[《前端代码开发》](#① 用户登录页面修改)。

  


### 2.2 数据模型

- 用户登录涉及到数据表 `user` ，笔者这里把字段 `phone` 重命名成了 `email` ，没有改变字段的数据类型，以对应用户的邮箱。

  ```sql
  ALTER TABLE `user`
  CHANGE phone email VARCHAR(100);
  ```

- 修改后 `user` 表字段：

  ![image-20221022100234590](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221022100234590.png)

  |   字段    |        描述        |
  | :-------: | :----------------: |
  |    id     |    用户ID，主键    |
  |   name    |      用户姓名      |
  |   email   |        邮箱        |
  |    sex    |    性别：0女1男    |
  | id_number |     身份证号码     |
  |  avatar   |      用户头像      |
  |  status   | 状态：0禁用；1正常 |

> 【注意】重命名了字段后，前端代码原来的手机地方可能会出 Bug ，稍后要花点时间去修。
>
> - 手机号（邮箱）是区分不同用户的标识，在用户登录的时候判断所输入的手机号（邮箱）是否存储在表中。如果不在表中，说明该用户为一个新的用户，将该用户自动保在user表中。





### 2.3 搭架子

- 接下来就针对数据表 `user` 把对应的实体类、数据层、业务层和控制层的架子搭起来。

#### ① 实体类User

- 创建 `src/main/java/edu/ouc/entity/User.java` ：

  ```java
  @Data
  public class User implements Serializable {
      private static final Long serialVersionUID = 2L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
  
      private String name;
      private String email;
      private String sex;
      private String idNumber;
      private String avatar;
      private Integer status;
  }
  ```



#### ② 数据层

- 创建 `src/main/java/edu/ouc/mapper/UserMapper.java` ：

  ```java
  @Mapper
  public interface UserMapper extends BaseMapper<User> {
  }
  ```



#### ③ 业务层接口

- 创建 `src/main/java/edu/ouc/service/IUserService.java` ：

  ```java
  public interface IUserService extends IService<User> {
  }
  ```



#### ④ 业务层接口实现类

- 创建 `src/main/java/edu/ouc/service/impl/UserServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
  }
  ```



#### ⑤ 控制层

- 创建 `src/main/java/edu/ouc/controller/UserController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/user")
  public class UserController {
      @Autowired
      private UserServiceImpl userService;
  }
  ```



#### ⑥ 导入maven依赖坐标

- 导入邮箱验证码登录所需的依赖坐标：

  ```xml
  <!-- https://mvnrepository.com/artifact/javax.activation/activation -->
  <dependency>
      <groupId>javax.activation</groupId>
      <artifactId>activation</artifactId>
      <version>1.1.1</version>
  </dependency>
  
  <!-- https://mvnrepository.com/artifact/javax.mail/mail -->
  <dependency>
      <groupId>javax.mail</groupId>
      <artifactId>mail</artifactId>
      <version>1.4.7</version>
  </dependency>
  
  <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-email -->
  <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-email</artifactId>
      <version>1.4</version>
  </dependency>
  ```



#### ⑦ 工具类

- 然后编写一个工具类 `src/main/java/edu/ouc/utils/MailUtils.java` ，用于发送邮件验证码。

  ```java
  public class MailUtils {
  
      // 发送邮件验证码
      public static void sendTestMail(String email, String code) throws MessagingException {
          // 1.创建Properties 类用于记录邮箱的一些属性
          Properties pros = new Properties();
          // 1.1 表示SMTP发送邮件，必须进行身份验证
          pros.put("mail.smtp.auth", "true");
          // 1.2 此处填写SMTP服务器
          pros.put("mail.smtp.host", "smtp.qq.com");
          // 1.3 端口号，QQ邮箱端口587
          pros.put("mail.smtp.port", "587");
          // 1.4 此处填写，写信人的账号
          pros.put("mail.user", "sihangxie@qq.com");
          // 1.5 此处填写16位STMP口令
          pros.put("mail.password", "zqvhcrhmebjgbedb");
  
          // 2.构建授权信息，用于进行SMTP进行身份验证
          Authenticator authenticator = new Authenticator() {
              protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                  // 2.1 用户名
                  String userName = pros.getProperty("mail.user");
                  // 2.2 16位STMP口令
                  String password = pros.getProperty("mail.password");
                  return new javax.mail.PasswordAuthentication(userName, password);
              }
          };
  
          // 3.使用环境属性和授权信息，创建邮件会话
          Session mailSession = Session.getInstance(pros, authenticator);
          // 4.创建邮件消息对象
          MimeMessage message = new MimeMessage(mailSession);
          // 4.1 设置发件人
          InternetAddress from = new InternetAddress(pros.getProperty("mail.user"));
          message.setFrom(from);
          // 4.2 设置收件人
          InternetAddress to = new InternetAddress(email);
          message.setRecipient(Message.RecipientType.TO, to);
          // 4.3 设置邮件标题
          message.setSubject("【瑞吉外卖】邮箱登录验证码");
          // 4.4 设置邮件的正文
          message.setContent("尊敬的用户：您好！\r\n您的登录验证码为：" + code + "（有效期为一分钟，请勿告知他人）", "text/html;charset=UTF-8");
  
          // 5.最后，发送邮件
          Transport.send(message);
      }
  
      // 获取六位随机验证码
      public static String getCode() {
          // 由于数字 1 、 0 和字母 O 、l 有时分不清楚，所以，没有数字 1 、 0
          String[] beforeShuffle = {"2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                  "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                  "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                  "w", "x", "y", "z"};
          // 将数组转换成集合
          List<String> list = Arrays.asList(beforeShuffle);
          // 打乱集合顺序，以达到随机的效果
          Collections.shuffle(list);
          // 创建StringBuilder，不是线程安全的
          StringBuilder sb = new StringBuilder();
          // 将集合转变成StringBuilder字符串
          for (String s : list) {
              sb.append(s);
          }
          // 返回sb字符串中第10~17位的5位验证码，这个区间其实随便设的
          return sb.substring(10, 16);
      }
  }
  ```



#### ⑧ 修改拦截器

- 修改 `src/main/java/edu/ouc/filter/LoginCheckFilter.java` 中第 44 行代码，添加不需要拦截的地址：

  ```java
  // 2.定义不需要拦截的URL地址数组
  String[] urls = new String[]{
          "/employee/login",  // 登录页面
          "/employee/logout", // 退出登录
          "/backend/**",      // 后台页面的页面的静态资源
          "/front/**",        // 移动端页面的静态资源
          "/user/login",      // 用户登录
          "/user/sendMsg"     // 发送登录验证码
  };
  ```

- 并再添加一段代码，用于判断 C 端用户是否已经登录：

  ```java
  // 6.如果需要处理，判断C端用户是否登录
  if (request.getSession().getAttribute("user") != null) {
      // 能进入说明已经登录，直接放行
      Long userId = (Long) request.getSession().getAttribute("user");
      log.info("手机用户{}已登录", userId);
  
      // 把当前登录用户的ID保存到ThreadLocal中
      BaseContext.setCurrentUserId(userId);
      
      // 放行
      filterChain.doFilter(request, response);
      return;
  }
  ```





### 2.4 前端代码开发

- 在开发代码之前，需要梳理一下登录时前端页面和服务端的交互过程：
  - 1、在登录页面 (front/page/login.html) 输入电子邮箱，点击【获取验证码】按钮，页面发送 ajax 请求，在服务端调用邮箱服务 API 给指定邮箱发送验证码邮件；
  - 2、在登录页面输入验证码，点击【登录】按钮，发送 ajax 请求，在服务端处理登录请求。
- 开发邮箱验证码登录功能，其实就是在服务端编写代码去处理前端页面发送的这 2 次请求即可。



#### ① 用户登录页面修改

- 打开 `src/main/resources/static/front/api/login.js` ，添加获取邮箱验证码的函数：

  ```javascript
  function sendMsgApi(data) {
      return $axios({
          'url': '/user/sendMsg',
          'method': 'post',
          data
      })
  }
  ```

- 这样，用户点击【获取验证码】时，前端就以 POST 方式向后端 URL 为 `/user/sendMsg` 发送请求，负载是用户的电子邮箱。

  ![image-20221022143418643](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221022143418643.png)

  ![image-20221022143456986](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221022143456986.png)



- 由于我们把手机号码登录替换成邮箱登录，因此我们需要对原来的登录界面作一些修改。打开 `src/main/resources/static/front/page/login.html` 。

- 修改第 25 行代码为：

  ```html
  <el-input placeholder=" 请输入电子邮箱" v-model="form.email"  maxlength='50'/></el-input>
  ```

- 修改第 39 行代码为：这是关于登录按钮颜色转换的

  ```javascript
  <el-button type="primary"
                 :class="{btnSubmit:1===1,btnNoPhone:!form.code||!form.email,btnPhone:form.code&&form.email}"
                 @click="btnLogin">
  ```

- 修改第 82 行代码的正则表达式为：

  ```javascript
  const regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; // 邮箱正则表达式
  ```

- 修改第 73 行代码为：

  ```javascript
  sendMsgApi({email:this.form.email})
  ```

- 修改第 108 行代码为：

  ```javascript
  const res = await loginApi(this.form)	// 向后端发送POST请求
  ```

- 其他地方，中文把 “手机号码” 改成 “电子邮箱“ ；英文把 ”phone“ 改成 ”email“ 即可。



- 此外，为了方便调试，还需把前端请求的超时时间设置得长一点。打开 `src/main/resources/static/front/js/request.js` ，修改第 8 行代码为：

  ```javascript
  // 超时
  timeout: 1000000
  ```



#### ② 验证码倒计时开发

- 黑马的源代码中，用户点击完【获取验证码】后，仍然可以不断点击【获取验证码】来频繁获取邮箱验证码。这会给邮箱服务器带来极大的负担。

- 因此，我主动加上了验证码获取倒计时功能。用户必须等待 60 秒后才能再一次获取验证码邮件，可以极大降低邮箱服务器的负担。

- 打开 `src/main/resources/static/front/page/login.html` ，再第 32 行代码后添加倒计时的显示区域：

  ```html
  <!--用户点击完【获取验证码】后倒计时60s-->
  <span v-show="!show" class="count">重新获取（{{count}} s）</span>
  ```

- 第 59 行，Vue 的数据模型中，添加`show` 、`count` 、`timer`  3 个数据模型：

  ```javascript
  data() {
      return {
          show: true, // 控制【获取验证码】以及倒计时的显示开关
          count: '',  // 倒计时时长
          timer: null,    // 计时器
          form: {
              email: '',
              code: ''
          },
          msgFlag: false,
          loading: false
      }
  },
  ```

- 第 77 行，在方法 `getCode()` 中添加相关代码：

  ```javascript
  getCode() {
      const TIME_COUNT = 60;  // 验证码倒计时时长60s
      this.form.code = ''
      // const regex = /^(13[0-9]{9})|(15[0-9]{9})|(17[0-9]{9})|(18[0-9]{9})|(19[0-9]{9})$/;  // 手机号正则表达式
      const regex = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; // 邮箱正则表达式
      if (regex.test(this.form.email)) {
          this.msgFlag = false
          // this.form.code = (Math.random() * 1000000).toFixed(0)
          const res = sendMsgApi({email: this.form.email})    // 向后端发送用户的邮箱，让后端给用户发送验证码邮件
          this.$notify({type: 'success', message: '验证码发送成功'})
  
          if (!this.timer) {
              this.count = TIME_COUNT;    // 设置倒计时时长
              this.show = false;  // 不显示【获取验证码】，显示倒计时
              this.timer = setInterval(() => {
                  if (this.count > 0 && this.count <= TIME_COUNT) {
                      this.count--;
                  } else {    // 倒计完了
                      this.show = true;   // 不显示倒计时，显示【获取验证码】
                      clearInterval(this.timer);	// 清空
                      this.timer = null;	// 清空
                  }
              }, 1000)
          }
      } else {
          this.msgFlag = true
      }
  },
  ```

- 同时，倒计时区域的样式是 `class="count"` ，我想让它显示为浅灰色。打开 `src/main/resources/static/front/styles/login.css` 添加 Class 选择器：

  ```css
  #login .divContainer .count {
      position: absolute;
      right: 20rem;
      top: 20rem;
      cursor: pointer;
      opacity: 1;
      font-size: 12rem;
      font-family: PingFangSC, PingFangSC-Regular;
      font-weight: 400;
      text-align: left;
      color: #D8D8D8;	/*浅灰色*/
      letter-spacing: 0px;
  }
  ```




#### ③ 前端登录逻辑优化

- 打开 `src/main/resources/static/front/page/login.html` ，第 106 行的 `btnLogin()` 方法，我修改成：

  ```javascript
  async btnLogin() {
      if (this.form.email && this.form.code) {
          this.loading = true
          const res = await loginApi(this.form)
          this.loading = false
          if (res.code === 1) {
              localStorage.setItem('userInfo', JSON.stringify(res.data)) // 存储后端返回的用户对象
              sessionStorage.setItem("userEmail", this.form.email)
              window.requestAnimationFrame(() => {
                  this.$notify({type: 'success', message: res.data})
                  window.location.href = '/front/index.html'
              })
          } else {
              this.$notify({type: 'warning', message: res.msg});
          }
      }
      if (!this.form.email) {
          this.$notify({type: 'warning', message: '请输入电子邮箱'});
      } else if (!this.form.code) {
          this.$notify({type: 'warning', message: '请输入验证码'});
      }
  }
  ```





### 2.5 后端代码开发



####  ① 验证码功能业务层开发

- 用户 `user` 业务层接口 `IUserService.java` ：

  ```java
  // 发送邮箱验证码
  Boolean sendMsg(User user, HttpSession session) throws MessagingException;
  ```

- 用户 `user` 业务层接口实现类 `UserServiceImpl.java` ：

  ```java
  // 发送邮箱验证码
  @Override
  public Boolean sendMsg(User user, HttpSession session) throws MessagingException {
      // 1.获取前端传来的用户邮箱
      String email = user.getEmail();
      // 2.如果邮箱不为空才进行下一步操作
      if (!email.isEmpty()) {
          // 2.1 随机生成六位数验证码
          String code = MailUtils.getCode();
          // 2.2 发送验证码邮件
          MailUtils.sendMail(email, code);
          // 2.3 把获得的验证码存入session保存作用域，方便后面拿出来比对
          session.setAttribute(email, code);
          return true;
      }
      return false;
  }
  ```



#### ② 登录功能业务层开发

- 登录时的请求分析，当用户输入完邮箱和验证码，点击【登录】时，前端以 POST 方式向后端 URL 为 `/user/login` 的地址发送请求。携带的数据是用户的邮箱和验证码。

  ![image-20221022172721998](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221022172721998.png)

  ![image-20221022204721172](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221022204721172.png)

- 控制层接收有两种方式：

  - 一是像以前一样通过 DTO 类扩展 `code` 属性；
  - 二是直接使用 Map 来接收，这里更推荐第二种。

- 【难点】如果把登录验证码的有效性控制在一分钟？



- 用户 `user` 业务层接口 `IUserService.java` ：

  ```java
  // 移动端用户登录
  User login(Map<String, String> map, HttpSession session);
  ```

- 用户 `user` 业务层接口实现类 `UserServiceImpl.java` ，下面代码都是我独立构思完成的，和老师几乎一样：

  ```java
  // 移动端用户登录登录
  @Override
  public User login(Map<String, String> map, HttpSession session) {
      // 获取前端传送来的用户邮箱
      String email = map.get("email");
      // 获取前端传送来的验证码
      String code = map.get("code");
      // 验证邮箱和验证码是否为空，如果为空则直接登录失败
      if (email.isEmpty() || code.isEmpty()) {
          throw new CustomException("邮箱或验证码不能为空");
      }
  
      // 如果邮箱和验证码不为空，前往调用数据层查询数据库有无该用户
      // 获取之前存在session保存作用域中的正确验证码
      String trueCode = (String) session.getAttribute(email);
  
      // 比对用户输入的验证码和真实验证码，错了直接登录失败
      if (!code.equals(trueCode)) {
          throw new CustomException("验证码错误");
      }
  
      // 验证码匹配，开始调用数据库查询
      LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
      lqw.eq(User::getEmail, email);
      User user = this.getOne(lqw);
  
      // 如果数据库中没有该用户，就是新用户，要添加新用户
      if (user == null) {
          // 添加新用户
          user = new User();
          user.setEmail(email);
          this.save(user);
      }
      // 最后把这个登录用户存到session保存作用域中，表示已登录，让拦截器放行
      session.setAttribute("user", user.getId());
      return user;
  }
  ```



#### ③ 验证码一分钟有效性开发

- 我的独立构思是：只要程序调用了在 `MailUtils.java` 的发送邮件方法 `sendMail()` ，就会同时开启一个新线程，该线程先 `sleep()` 60 秒，时间到后调用 `getCode()` 方法获取新验证码，并覆盖写入原来的 session 保存作用域中。

- 难点是，Thread 新线程应该写在哪里？以及从哪里开始 `start()` ？

- 最后，我把下面这段代码写到了 `UserServiceImpl.java` 的 `sendMsg()` 方法中，大概在 39 行左右：

  ```java
  // 启动多线程来限定验证码的时效性
  new Thread(() -> {
      try {
          // 验证码的有效时长
          Thread.sleep(60000L);
          // 更换新验证码
          session.setAttribute(email, MailUtils.getCode());
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }).start();
  ```

- 经过测试，功能是没有问题的。






####  ④ 控制层开发

- 打开用户 `user` 表现层 `UserController.java` ：

  ```java
  // 发送邮箱验证码
  @PostMapping("/sendMsg")
  public R<String> sendMsg(@RequestBody User user, HttpSession session) throws MessagingException {
      if (userService.sendMsg(user, session)) {
          return R.success("验证码发送成功");
      }
      return R.error("验证码发送失败");
  }
  ```
  
  ```java
  // 移动端用户登录登录
  @PostMapping("/login")
  public R<User> login(@RequestBody Map<String, String> map, HttpSession session) {
      User user = userService.login(map, session);
      return R.success(user);
  }
  ```





### 2.6 功能测试

略。





## 3. 退出登录



### 3.1 请求分析

- 用户点击【退出登录】时，会退出当前用户，并返回登录界面。清空本地存储和会话存储的用户信息存储。

  ![image-20221027171449264](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027171449264.png)

- 前端以 POST 方式向后端 URL 为 `/user/loginout` 的地址发送请求。

  ![image-20221027171602757](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027171602757.png)

  

### 3.2 业务层开发

- 用户 `user` 业务层接口 `IUserService.java` ：

  ```java
  // 移动端用户退出登录
  Boolean logout(HttpSession session);
  ```

- 用户 `user` 业务层接口实现类 `UserServiceImpl.java` ：

  ```java
  // 移动端用户退出登录
  @Override
  public Boolean logout(HttpSession session) {
      Long userId = BaseContext.getCurrentUserId();
      User user = this.getById(userId);
      String email = user.getEmail();
      // 清除Session保存作用域中保存的数据
      session.removeAttribute("user");
      session.removeAttribute(email);
      return true;
  }
  ```



### 3.3 控制层开发

- 用户 `user` 表现层 `UserController.java` ：

  ```java
  // 移动端用户退出登录
  @PostMapping("/loginout")
  public R<String> logout(HttpSession session) {
      if (userService.logout(session)) {
          return R.success("退出成功");
      }
      return R.error("退出失败");
  }
  ```







# 十、 用户端开发



## 1. 用户地址簿



### 1.1 需求分析



#### ① 功能分析

- 地址簿，指的是移动端消费者用户的地址信息，用户登录成功后可以维护自己的地址信息。同一个用户可以有多个地址信息，但是只能有一个默认地址。



### 1.2 数据模型

- 地址簿涉及到数据表 `address_book` ，各个字段的详情如下所示：

  ![image-20221023151111055](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023151111055.png)

  |     字段      |           描述           |
  | :-----------: | :----------------------: |
  |      id       |       地址ID，主键       |
  |    user_id    |       C 端用户 ID        |
  |   consignee   |          联系人          |
  |      sex      |           性别           |
  |     phone     |         联系电话         |
  | province_code |         省份编码         |
  | province_name |         省份名称         |
  |   city_code   |         城市编码         |
  |   city_name   |          城市名          |
  | district_code |         区县编码         |
  | district_name |         区县名称         |
  |    detail     |       详细地址信息       |
  |     label     |   标签：公司、家、学校   |
  |  is_default   | 是否是默认地址：0不是1是 |
  |  create_time  |       创建日期时间       |
  |  update_time  |       修改日期时间       |
  |  create_user  |         创建人ID         |
  |  update_user  |         修改人ID         |
  |  is_deleted   |         逻辑删除         |



### 1.3 搭架子

- 接下来就针对数据表 `address_book` 把对应的实体类、数据层、业务层和控制层的架子搭起来。

#### ① 实体类AddressBook

- 创建 `src/main/java/edu/ouc/entity/AddressBook.java` ：

  ```java
  @Data
  public class AddressBook implements Serializable {
  
      private static final long serialVersionUID = 3L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
  
      //用户id
      private Long userId;
  
      //收货人
      private String consignee;
  
      //手机号
      private String phone;
  
      //性别 0 女 1 男
      private String sex;
  
      //省级区划编号
      private String provinceCode;
  
      //省级名称
      private String provinceName;
  
      //市级区划编号
      private String cityCode;
  
      //市级名称
      private String cityName;
  
      //区级区划编号
      private String districtCode;
  
      //区级名称
      private String districtName;
  
      //详细地址
      private String detail;
  
      //标签
      private String label;
  
      //是否默认 0 否 1是
      private Integer isDefault;
  
      //创建时间
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;
  
      //更新时间
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;
  
      //创建人
      @TableField(fill = FieldFill.INSERT)
      private Long createUser;
  
      //修改人
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private Long updateUser;
  
      //是否删除
      private Integer isDeleted;
  }
  ```



#### ② 数据层

- 创建 `src/main/java/edu/ouc/mapper/AddressBookMapper.java` ：

  ```java
  @Mapper
  public interface AddressBookMapper extends BaseMapper<AddressBook> {
  }
  ```



#### ③ 业务层接口

- 创建 `src/main/java/edu/ouc/service/IAddressBookService.java` ：

  ```java
  public interface IAddressBookService extends IService<AddressBook> {
  }
  ```



#### ④ 业务层接口实现类

- 创建 `src/main/java/edu/ouc/service/impl/AddressBookServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {
  }
  ```



#### ⑤ 控制层

- 创建 `src/main/java/edu/ouc/controller/UserController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/addressBook")
  public class AddressBookController {
      @Autowired
      private AddressBookServiceImpl addressBookService;
  }
  ```



### 1.4 新增地址



#### ① 请求分析

- 用户填写好信息后，点击【保存地址】：

  ![image-20221023155022702](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023155022702.png)

- 前端以 POST 方式向后端 URL 为 `/addressBook` 的地址发送请求。携带的数据是联系人姓名、联系电话、详细地址、标签和性别。

  ![image-20221023155145433](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023155145433.png)

  ![image-20221023155200414](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023155200414.png)



#### ② 业务层开发

- 地址簿 `AddressBook` 业务层接口 `IAddressBookService.java` ：

  ```java
  // 新增地址
  AddressBook saveAdd(AddressBook addressBook);
  ```

- 地址簿 `AddressBook` 业务层接口实现类 `AddressBookServiceImpl.java` ：

  ```java
  // 新增地址
  @Override
  public AddressBook saveAdd(AddressBook addressBook) {
      // 获取当前登录用户的ID，并设为当前地址的userId
      addressBook.setUserId(BaseContext.getCurrentUserId());
      // 调用数据层保存新地址
      this.save(addressBook);
      return addressBook;
  }
  ```



#### ③ 控制层开发

- 地址簿 `AddressBook` 表现层 `AddressBookController.java` ：

  ```java
  // 新增地址
  @PostMapping
  public R<AddressBook> save(@RequestBody AddressBook addressBook) {
      return R.success(addressBookService.saveAdd(addressBook));
  }
  ```





### 1.5 地址展示



#### ① 请求分析

- 前端以 GET 方式向后端 URL 为 `/addressBook/list` 的地址发送请求。

  ![image-20221023161938762](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023161938762.png)

  



#### ② 业务层开发

- 地址簿 `AddressBook` 业务层接口 `IAddressBookService.java` ：

  ```java
  // 地址展示
  List<AddressBook> getList();
  ```

- 地址簿 `AddressBook` 业务层接口实现类 `AddressBookServiceImpl.java` ：

  ```java
  // 地址展示
  @Override
  public List<AddressBook> getList() {
      // 获取当前用户ID
      Long userId = BaseContext.getCurrentUserId();
      // 查询这个用户所有的地址信息
      LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
      lqw.eq(userId != null, AddressBook::getUserId, userId);
      lqw.orderByDesc(AddressBook::getUpdateTime);
      return this.list(lqw);
  }
  ```



#### ③ 控制层开发

- 地址簿 `AddressBook` 表现层 `AddressBookController.java` ：

  ```java
  // 地址展示
  @GetMapping("/list")
  public R<List<AddressBook>> getList() {
      return R.success(addressBookService.getList());
  }
  ```



### 1.6 设置默认地址



#### ① 请求分析

- 前端以 PUT 方式向后端 URL 为 `/addressBook/default` 的地址发送请求。负载是将要设置为默认地址的地址 ID 。

  ![image-20221023170016759](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023170016759.png)

  ![image-20221023170040574](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023170040574.png)

  



#### ② 业务层开发

- 默认地址只能设置一个，这个功能的难点在于，同一用户的前提下，如果始终保持地址簿上只有一个地址是被勾选为默认地址，且数据库中始终只有一个地址的 `is_default` 字段为 1 ，其余为 0 。

- 地址簿 `AddressBook` 业务层接口 `IAddressBookService.java` ：

  ```java
  // 设为默认地址
  AddressBook setDefault(AddressBook addressBook);
  ```



- 地址簿 `AddressBook` 业务层接口实现类 `AddressBookServiceImpl.java` ：

  ```java
  // 【我的思路】(不推荐，用LambdaQueryWrapper有点蠢)
  // 设为默认地址
  @Override
  public AddressBook setDefault(AddressBook addressBook) {
      // 获取想要设为默认地址的地址ID
      Long id = addressBook.getId();
      // 获取当前登陆的用户ID
      Long userId = BaseContext.getCurrentUserId();
  
      // 先查询当前用户的地址里字段is_default为1的记录
      LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
      lqw.eq(AddressBook::getIsDefault, 1);
      lqw.eq(AddressBook::getUserId, userId);
      AddressBook defaultAdd = this.getOne(lqw);
  
      // 如果已经存在默认地址，先把它设为0
      if (defaultAdd != null) {
          // 取消它的默认地址
          defaultAdd.setIsDefault(0);
          // 更新到数据库
          this.updateById(defaultAdd);
      }
  
      // 现在可以直接设为默认地址
      LambdaQueryWrapper<AddressBook> newLqw = new LambdaQueryWrapper<>();
      newLqw.eq(AddressBook::getUserId, userId);
      newLqw.eq(AddressBook::getId, id);
      defaultAdd = this.getOne(newLqw);
      defaultAdd.setIsDefault(1);
      this.updateById(defaultAdd);
  
      return defaultAdd;
  }
  ```

  ```java
  // 【老师思路】(推荐，用LambdaUpdateWrapper)
  // 1.先把当前用户的所有地址的is_default字段设为0
  // 1.1 创建更新条件封装器
  LambdaUpdateWrapper<AddressBook> luw = new LambdaUpdateWrapper<>();
  // 1.2 添加更新条件：指定当前登录用户ID
  luw.eq(AddressBook::getUserId, BaseContext.getCurrentUserId());
  // 1.3 添加更新添加：把所有记录的is_default字段设为0
  luw.set(AddressBook::getIsDefault, 0);
  // 1.4 调用数据层更新方法，入参是更新条件封装器luw
  this.update(luw);
  
  // 2.再把当前传入的地址设为默认地址
  // 2.1 把传入的地址对象的isDefault属性设为1
  addressBook.setIsDefault(1);
  // 2.2 调用数据层的更新方法，注意null不会参与更新，只会更新不为null的字段，详见下面的SQL语句
  this.updateById(addressBook);   //UPDATE address_book SET is_default=?, update_time=?, update_user=? WHERE id=?
  return addressBook;
  ```

  



#### ③ 控制层开发

- 地址簿 `AddressBook` 表现层 `AddressBookController.java` ：

  ```java
  // 设为默认地址
  @PutMapping("/default")
  public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
      return R.success(addressBookService.setDefault(addressBook));
  }
  ```



### 1.7 编辑地址



#### ① 请求分析

- 【回显】前端以 GET 方式向后端 URL 为 `/addressBook/{id}` 的地址发送请求。

  ![image-20221023190539263](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023190539263.png)

- 【更新】前端以 PUT 方式向后端 URL 为 `/addressBook` 的地址发送请求。携带的是 AddressBook 对象信息。

  ![image-20221023191520396](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023191520396.png)



#### ② 业务层开发

- 地址簿 `AddressBook` 业务层接口 `IAddressBookService.java` ：

  ```java
  无
  ```

- 地址簿 `AddressBook` 业务层接口实现类 `AddressBookServiceImpl.java` ：

  ```java
  无
  ```



#### ③ 控制层开发

- 地址簿 `AddressBook` 表现层 `AddressBookController.java` ：

  ```java
  // 查询单个地址的信息，用于编辑回显
  @GetMapping("/{id}")
  public R<AddressBook> getAdd(@PathVariable Long id) {
      AddressBook addressBook = addressBookService.getById(id);
      if (addressBook != null) {
          return R.success(addressBook);
      }
      return R.error("没有找到该对象");
  }
  ```

  ```java
  // 更新
  @PutMapping
  public R<String> updateAdd(@RequestBody AddressBook addressBook) {
      if (addressBookService.updateById(addressBook)) {
          return R.success("保存成功");
      }
      return R.success("保存失败");
  }
  ```

  

#### ④ 前端Bug修复

- 修复 `address-edit.html` 第 111 行代码：

  ```javascript
  this.activeIndex = this.labelList.indexOf(this.form.label); // 修复回显标签错误的bug
  ```



### 1.8 删除地址



#### ① 请求分析

- 前端以 DELETE 方式向后端 URL 为 `/addressBook` 的地址发送请求。负载是要删除的地址 ID 。

  ![image-20221023195527080](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023195527080.png)

  ![image-20221023195641177](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221023195641177.png)



#### ② 业务层开发

- 地址簿 `AddressBook` 业务层接口 `IAddressBookService.java` ：

  ```java
  无
  ```

- 地址簿 `AddressBook` 业务层接口实现类 `AddressBookServiceImpl.java` ：

  ```java
  无
  ```



#### ③ 控制层开发

- 地址簿 `AddressBook` 表现层 `AddressBookController.java` ：

  ```java
  // 删除
  @DeleteMapping
  public R<String> remove(@RequestParam Long ids) {
      if (addressBookService.removeById(ids)) {
          return R.success("删除成功");
      }
      return R.error("删除失败");
  }
  ```

  



## 2. 菜品展示



### 2.1 需求分析



#### ① 功能分析

- 右侧展示不出来分类页面，是因为前端代码规定了必须把分类列表和购物车列表同时查询成功才能成功显示。

  ![image-20221024113327280](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024113327280.png)

- 前端代码中如下：

  ```javascript
  Promise.all([categoryListApi(), cartListApi({})]).then(res => {
      ...
  }
  ```

- 而查询购物车列表我们还没写，因此会报 404 错误：

  ![image-20221024113501952](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024113501952.png)



#### ② 流程梳理

- 在开发代码之前,需要梳理一下前端页面和服务端的交互过程：
  - 1、页面 (`front/index.html` ) 发送 ajax 请求，获取分类数据 (菜品分类和套餐分类)；
  - 2、页面发送 ajax 请求，获取第一个分类下的菜品或者套餐；
  
- 开发菜品展示功能，其实就是在服务端编写代码去处理前端页面发送的这 2 次请求即可。

- 【注意】首页加载完成后，还发送了一次 ajax 请求用于加载购物车数据。如果获取购物车数据失败，则整个页面都是展示失败的。为了开发方便，我们先提供一份 JSON 假数据：

  ```json
  {"code": 1, "msg": null, "data": [], "map": {}}
  ```

- 然后修改 `src/main/resources/static/front/api/main.js` 中 `cartListApi()` 函数的 URL 请求地址为：

  ```javascript
  //获取购物车内商品的集合
  function cartListApi(data) {
      return $axios({
          // 'url': '/shoppingCart/list',
          'url': '/front/cartData.json',	// 先暂时用假数据
          'method': 'get',
          params: {...data}
      })
  }
  ```

- 修改之后，现在菜品就能成功展示了：

  ![image-20221024115902063](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024115902063.png)



### 2.2 重构获取菜品列表方法

#### ① 分析

- 菜品业务层实现类 `DishServiceImpl.java` 中的菜品列表方法 `list()` 有个 Bug ，就是列表元素 `dish` 不包含菜品口味：

  ![image-20221024120141110](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024120141110.png)

- 而客户下单是要选择菜品口味的，因此集合的泛型应该重构为 `DishDto` 。



#### ② 业务层重构

- 菜品 `dish` 业务层接口 `IDishService.java` ：

  ```java
  // 根据指定过滤条件查询菜品
  List<DishDto> list(Dish dish);
  ```

- 菜品 `dish` 业务层接口实现类 `DishServiceImpl.java` ：

  ```java
  // 根据指定过滤条件查询菜品
  @Override
  public List<DishDto> listWithFlavor(Dish dish) {
      // 1.创建条件过滤器
      LambdaQueryWrapper<Dish> lqw = new LambdaQueryWrapper<>();
      // 2.添加过滤条件：根据分类ID查询菜品
      lqw.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
      // 3.添加排序条件：根据sort字段升序排列菜品，再根据最后修改时间降序排列
      lqw.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
      // 4.条件过滤条件：只查询启售的菜品
      lqw.eq(Dish::getStatus, 1);
      // 5.调用数据层的查询方法
      List<Dish> dishes = this.list(lqw);
  
      // 6.使用集合的stream流逐一把dish封装成dishDto
      return dishes.stream().map(dishItem -> {
          // 6.1 创建dishDto对象
          DishDto dishDto = new DishDto();
          // 6.2 把dish的所有属性值复制到dishDto对象中
          BeanUtils.copyProperties(dishItem, dishDto);
          // 6.3 创建DishFlavor的查询条件封装器
          LambdaQueryWrapper<DishFlavor> dfLqw = new LambdaQueryWrapper<>();
          // 6.4 添加查询条件：按dishId查询
          dfLqw.eq(DishFlavor::getDishId, dishItem.getId());
          // 6.5 根据查询条件查询菜品的口味集合
          List<DishFlavor> dishFlavors = dishFlavorService.list(dfLqw);
          // 6.6 把查询到的菜品口味集合设置到dishDto对象中
          dishDto.setFlavors(dishFlavors);
          // 6.7 返回封装好的dishDto对象
          return dishDto;
      }).collect(Collectors.toList());
  }
  ```



#### ③ 控制层重构

- 菜品 `dish` 控制层 `DishController.java` ：

  ```java
  // 根据指定过滤条件查询菜品
  @GetMapping("/list")
  public R<List<DishDto>> listByCategoryId(Dish dish) {
      return R.success(dishService.listWithFlavor(dish));
  }
  ```



#### ④ 功能测试

- 重构完成之后，用户菜品页面就能显示展示规格了：

  ![image-20221024131741165](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024131741165.png)

  ![image-20221024131758823](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024131758823.png)

  



### 2.3 套餐展示

#### ① 分析

- 在用户点击套餐时，前端以 GET 方式向后端 URL 为 `/setmeal/list` 发送请求。携带的参数是套餐 ID 和 启售。

  ![image-20221024133318084](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024133318084.png)

  ![image-20221024134954999](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024134954999.png)



#### ② 业务层重构

- 套餐 `setmeal` 业务层接口 `IDishService.java` ：

  ```java
  // 根据条件查询套餐集合
  List<Setmeal> list(Setmeal setmeal);
  ```

- 套餐 `setmeal` 业务层接口实现类 `DishServiceImpl.java` ：

  ```java
  // 根据条件查询套餐集合
  @Override
  public List<Setmeal> list(Setmeal setmeal) {
      // 1.创建查询条件封装器
      LambdaQueryWrapper<Setmeal> lqw = new LambdaQueryWrapper<>();
      // 2.添加查询条件：根据类别ID查询
      lqw.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
      // 3.添加查询条件：根据售卖状态查询
      lqw.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
      // 4.调用数据层返回套餐对象构成的集合
      return this.list(lqw);
  }
  ```



#### ③ 控制层重构

- 套餐 `setmeal` 控制层 `DishController.java` ：

  ```java
  // 根据条件查询套餐集合
  @GetMapping("/list")
  public R<List<Setmeal>> list(Setmeal setmeal) {
      return R.success(setmealService.list(setmeal));
  }
  ```



#### ④ 功能测试

- 完成后，套餐页面可以正常展示了。

  ![image-20221024143942785](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024143942785.png)





## 3. 购物车



### 3.1 需求分析



#### ① 功能分析

- 移动端用户可以将菜品或者套餐添加到购物车。对于菜品来说，如果设置了口味信息，则需要选择规格后才能加入购物车；对于套餐来说，可以直接点击 `+` 将当前套餐加入购物车。在购物车中可以修改菜品和套餐的数量，也可以清空购物车。



#### ② 流程梳理

- 在开发代码之前，需要梳理一下购物车操作时，前端页面和服务端的交互过程：
  - 1、点击【加入购物车】或者【+】按钮，页面发送 ajax 请求，请求服务端，将菜品或者套餐添加到购物车；
  - 2、点击购物车图标，页面发送 ajax 请求，请求服务端查询购物车中的菜品和套餐；
  - 3、点击清空购物车按钮，页面发送 ajax 请求，请求服务端来执行清空购物车操作。
- 开发购物车功能，其实就是在服务端编写代码去处理前端页面发送的这 3 次请求即可。





### 3.2 数据模型

- 购物车涉及到数据表 `shopping_cart` ，各个字段的详情如下所示：

  ![image-20221024144956207](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024144956207.png)

  |    字段     |         描述          |
  | :---------: | :-------------------: |
  |     id      | 购物车中商品 ID，主键 |
  |    name     |   菜品名称/套餐名称   |
  |    image    |       商品图片        |
  |   user_id   |      C 端用户 ID      |
  |   dish_id   |        菜品 ID        |
  | setmeal_id  |        套餐 ID        |
  | dish_flavor |       菜品口味        |
  |   number    |       商品份数        |
  |   amount    |        总金额         |
  | create_time |     创建日期时间      |



### 3.3 搭架子

- 接下来就针对数据表 `shopping_cart` 把对应的实体类、数据层、业务层和控制层的架子搭起来。

#### ① 实体类ShoppingCart

- 创建 `src/main/java/edu/ouc/entity/ShoppingCart.java` ：

  ```java
  @Data
  public class ShoppingCart {
      private static final Long serialVersionUID = 4L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
      
      private String name;
      
      private String image;
      
      @JsonSerialize(using = ToStringSerializer.class)
      private Long userId;
      
      @JsonSerialize(using = ToStringSerializer.class)
      private Long dishId;
      
      @JsonSerialize(using = ToStringSerializer.class)
      private Long setmealId;
      
      private String dishFlavor;
      
      private Integer number;
      
      private BigDecimal amount;
      
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;
  }
  ```



#### ② 数据层

- 创建 `src/main/java/edu/ouc/mapper/ShoppingCartMapper.java` ：

  ```java
  @Mapper
  public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
  }
  ```



#### ③ 业务层接口

- 创建 `src/main/java/edu/ouc/service/IShoppingCartService.java` ：

  ```java
  public interface IShoppingCartService extends IService<ShoppingCart> {
  }
  ```



#### ④ 业务层接口实现类

- 创建 `src/main/java/edu/ouc/service/impl/ShoppingCartServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {
  }
  ```



#### ⑤ 控制层

- 创建 `src/main/java/edu/ouc/controller/ShoppingCartController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/shoppingCart")
  public class ShoppingCartController {
      
      @Autowired
      private ShoppingCartServiceImpl shoppingCartService;
  }
  ```





### 3.4 添加购物车



#### ① 请求分析

- 用户选择好菜品后，点击【加入购物车】：

  ![image-20221024151314398](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024151314398.png)

- 前端以 POST 方式向后端 URL 为 `/shoppingCart/add` 的地址发送请求。携带的数据是购物车数据。

  ![image-20221024151355896](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024151355896.png)

  ![image-20221024151410606](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024151410606.png)



#### ② 自动填充修改

- 由于购物车的实体类 `ShoppingCart` 中需要自动填充的字段只有 `createTime` ，其他 3 个都没有。因此要对 `src/main/java/edu/ouc/common/MyMetaObjectHandler.java` 稍作修改：

  ```java
  @Component  // 让Spring管理
  @Slf4j
  public class MyMetaObjectHandler implements MetaObjectHandler {
  
      // 当执行插入数据时自动填充
      @Override
      public void insertFill(MetaObject metaObject) {
  
          // 实体类中创建了这个属性才会自动填充
          if (metaObject.hasSetter("createTime")) {
              metaObject.setValue("createTime", LocalDateTime.now());
          }
          if (metaObject.hasSetter("updateTime")) {
              metaObject.setValue("updateTime", LocalDateTime.now());
          }
  
          // 获取当前线程的登录用户的ID
          Long id = BaseContext.getCurrentUserId();
  
          if (metaObject.hasSetter("createUser")) {
              metaObject.setValue("createUser", id);
          }
          if (metaObject.hasSetter("updateUser")) {
              metaObject.setValue("updateUser", id);
          }
      }
  
      // 当执行更新数据时自动填充
      @Override
      public void updateFill(MetaObject metaObject) {
  
          // 获取当前线程的登录用户的ID
          Long id = BaseContext.getCurrentUserId();
  
          if (metaObject.hasSetter("updateUser")) {
              metaObject.setValue("updateUser", id);
          }
          if (metaObject.hasSetter("updateTime")) {
              metaObject.setValue("updateTime", LocalDateTime.now());
          }
      }
  }
  ```



#### ② 业务层开发

- 购物车 `ShoppingCart` 业务层接口 `IShoppingCartService.java` ：

  ```java
  // 添加菜品到购物车
  ShoppingCart add(ShoppingCart shoppingCart);
  ```

- 购物车 `ShoppingCart` 业务层接口实现类 `ShoppingCartServiceImpl.java` ：

  ```java
  // 添加菜品到购物车
  @Override
  public ShoppingCart add(ShoppingCart shoppingCart) {
      // 获取当前登录用户的 ID
      Long userId = BaseContext.getCurrentUserId();
      // 给传入的购物车菜品设置用户ID
      shoppingCart.setUserId(userId);
  
      // 查询一下是否是首次添加
      LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
      // 添加查询条件：根据用户ID查询
      lqw.eq(ShoppingCart::getUserId, userId);
      // 判断用户添加的是菜品还是套餐
      if (shoppingCart.getDishId() != null) {
          // 用户添加的是菜品，添加菜品ID作为查询条件
          lqw.eq(ShoppingCart::getDishId, shoppingCart.getDishId());
      } else {
          // 否则，用户添加的是套餐，添加套餐ID作为查询条件
          lqw.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
      }
      // 调用数据层查询购物车
      ShoppingCart shoppingCartSel = this.getOne(lqw);
  
      // 如果查询结果为空，则是第一次添加，把number字段设为1，插入
      if (shoppingCartSel == null) {
          shoppingCart.setNumber(1);
          // 调用数据层插入购物车数据
          this.save(shoppingCart);
          // 返回已经写入用户ID的对象
          return shoppingCart;
      }
  
      // 否则就不是第一次加入购物车，就直接在number字段上加1，更新
      shoppingCartSel.setNumber(shoppingCartSel.getNumber() + 1);
      // 更新
      this.updateById(shoppingCartSel);
      return shoppingCartSel;
  }
  ```



#### ③ 控制层开发

- 购物车 `ShoppingCart` 表现层 `ShoppingCartController.java` ：

  ```java
  // 添加菜品到购物车
  @PostMapping("/add")
  public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {
      return R.success(shoppingCartService.add(shoppingCart));
  }
  ```



### 3.5 查看购物车



#### ① 复原购物车前端页面

- 在开发菜品展示功能时，为了不让购物车报错修改了购物车前端页面代码，现在要将其修改回来。打开 `src/main/resources/static/front/api/main.js` ：

  ```javascript
  //获取购物车内商品的集合
  function cartListApi(data) {
      return $axios({
          'url': '/shoppingCart/list',
          'method': 'get',
          params: {...data}
      })
  }
  ```

- 再把 `cartData.json` 假数据删掉。



#### ② 请求分析

- 用户点击购物车的小骑手图标：

  ![image-20221024194513078](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024194513078.png)

- 前端以 GET 方式向后端 URL 为 `/shoppingCart/list` 的地址发送请求。

  ![image-20221024192912275](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024192912275.png)




#### ③ 业务层开发

- 购物车 `ShoppingCart` 业务层接口 `IShoppingCartService.java` ：

  ```java
  // 查询当前用户的购物车中所有信息
  List<ShoppingCart> getUserList();
  ```

- 购物车 `ShoppingCart` 业务层接口实现类 `ShoppingCartServiceImpl.java` ：

  ```java
  // 查询当前用户的购物车中所有信息
  @Override
  public List<ShoppingCart> getUserList() {
      // 获取当前用户ID
      Long userId = BaseContext.getCurrentUserId();
      // 创建查询条件封装器
      LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
      lqw.eq(userId != null, ShoppingCart::getUserId, userId);
      // 按时间升序排
      lqw.orderByAsc(ShoppingCart::getCreateTime);
      // 查询当前用户的所有购物车信息
      return this.list(lqw);
  }
  ```



#### ④ 控制层开发

- 购物车 `ShoppingCart` 表现层 `ShoppingCartController.java` ：

  ```java
  // 查询当前用户的购物车中所有信息
  @GetMapping("/list")
  public R<List<ShoppingCart>> list() {
      return R.success(shoppingCartService.getUserList());
  }
  ```





### 3.6 清空购物车



#### ① 请求分析

- 用户打开购物车后，点击【清空】按钮：

  ![image-20221024194609115](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024194609115.png)

- 前端以 DELETE 方式向后端 URL 为 `/shoppingCart/clean` 的地址发送请求。

  ![image-20221024194642346](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024194642346.png)




#### ② 业务层开发

- 购物车 `ShoppingCart` 业务层接口 `IShoppingCartService.java` ：

  ```java
  // 清空购物车
  Boolean clean();
  ```

- 购物车 `ShoppingCart` 业务层接口实现类 `ShoppingCartServiceImpl.java` ：

  ```java
  // 清空购物车
  @Override
  public Boolean clean() {
      // 获取当前用户ID
      Long userId = BaseContext.getCurrentUserId();
      // 创建查询条件封装器
      LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
      lqw.eq(userId != null, ShoppingCart::getUserId, userId);
      // 按用户ID删除所有记录
      return this.remove(lqw);
  }
  ```



#### ③ 控制层开发

- 购物车 `ShoppingCart` 表现层 `ShoppingCartController.java` ：

  ```java
  // 清空购物车
  @DeleteMapping("/clean")
  public R<String> clean() {
      if (shoppingCartService.clean()) {
          return R.success("清空成功");
      }
      return R.error("清空失败");
  }
  ```





### 3.7 菜品移出购物车



#### ① 请求分析

- 用户点击商品的【-】号：

  ![image-20221024195835511](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024195835511.png)

- 前端以 POST 方式向后端 URL 为 `/shoppingCart/sub` 的地址发送请求。负载是菜品 ID 或套餐 ID 。

  ![image-20221024195912273](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024195912273.png)

  ![image-20221024195925310](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221024195925310.png)



#### ② 业务逻辑

- 份数减到 1 时再往下减时，该商品就会从购物车中删除。



#### ③ 业务层开发

- 购物车 `ShoppingCart` 业务层接口 `IShoppingCartService.java` ：

  ```java
  // 购物车商品减一
  Boolean sub(ShoppingCart shoppingCart);
  ```

- 购物车 `ShoppingCart` 业务层接口实现类 `ShoppingCartServiceImpl.java` ：

  ```java
  // 购物车商品减一
  @Override
  public Boolean sub(ShoppingCart shoppingCart) {
      // 1.获取当前用户ID
      Long userId = BaseContext.getCurrentUserId();
      // 2.创建查询条件封装器
      LambdaQueryWrapper<ShoppingCart> lqw = new LambdaQueryWrapper<>();
      // 2.1 添加查询添加：按用户ID查询
      lqw.eq(userId != null, ShoppingCart::getUserId, userId);
  
      // 3.判断传来的是菜品还是套餐
      if (shoppingCart.getDishId() != null) {
          // 3.1 删除的是菜品
          lqw.eq(ShoppingCart::getDishId, shoppingCart.getDishId());
      } else {
          // 3.2 删除的是套餐
          lqw.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
      }
  
      // 4.查询当前购物车里该菜品/套餐的数量
      ShoppingCart shoppingCartSel = this.getOne(lqw);
      if (1 < shoppingCartSel.getNumber()) {
          // 4.1 数量大于一，直接减一
          shoppingCartSel.setNumber(shoppingCartSel.getNumber() - 1);
          // 4.2更新到数据库
          return this.updateById(shoppingCartSel);
      }
  
      // 5.其他情况直接从购物车删除此商品
      return this.remove(lqw);
  }
  ```



#### ④ 控制层开发

- 购物车 `ShoppingCart` 表现层 `ShoppingCartController.java` ：

  ```java
  // 购物车商品减一
  @PostMapping("/sub")
  public R<String> sub(@RequestBody ShoppingCart shoppingCart) {
      if (shoppingCartService.sub(shoppingCart)) {
          return R.success("删除成功");
      }
      return R.error("删除失败");
  }
  ```



- 至此，购物车功能就开发完毕了。







## 4. 下单



### 4.1 需求分析



#### ① 功能分析

- 移动端用户将菜品或者套餐加入购物车后，可以点击购物车中的【去结算】按钮，页面跳转到订单确认页面，点击【去支付】按钮则完成下单操作。



#### ② 流程梳理

- 在开发代码之前，需要梳理一下用户下单操作时前端页面和服务端的交互过程：
  - 1、在购物车中点击【去结算】按钮，页面跳转到订单确认页面。
  - 2、在订单确认页面，发送 ajax 请求，请求服务端获取当前登录用户的默认地址。
  - 3、在订单确认页面，发送 ajax 请求，请求服务端获取当前登录用户的购物车数据。
  - 4、在订单确认页面点击去支付按钮，发送 ajax 请求，请求服务端完成下单操作开发用户下单功能，其实就是在服务端编写代码去处理前端页面发送的请求即可。






### 4.2 数据模型

- 购物车涉及到订单表 `orders` 和订单明细表 `order_detail` ，各个字段的详情如下所示：

- 订单表 `orders` ：

  ![image-20221026213140493](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221026213140493.png)

  |      字段       |                         描述                          |
  | :-------------: | :---------------------------------------------------: |
  |       id        |                     订单 ID，主键                     |
  |     number      |                        订单号                         |
  |     status      | 订单状态：1待付款；2待派送；3已派送；4已完成；5已取消 |
  |     user_id     |                      C 端用户 ID                      |
  | address_book_id |                       地址簿 ID                       |
  |   order_time    |                       下单时间                        |
  |  checkout_time  |                    支付完成的时间                     |
  |   pay_method    |                       支付方式                        |
  |     amount      |                       订单金额                        |
  |     remark      |                         备注                          |
  |      phone      |                      用户手机号                       |
  |     address     |                     详细地址信息                      |
  |    user_name    |                        用户名                         |
  |    consignee    |                       收货人名                        |

- 订单明细表 `order_detail` ：

  ![image-20221026213758074](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221026213758074.png)

  |    字段     |       描述        |
  | :---------: | :---------------: |
  |     id      | 订单明细 ID，主键 |
  |    name     | 菜品名称/套餐名称 |
  |    image    |     商品图片      |
  |  order_id   |      订单 ID      |
  |   dish_id   |      菜品 ID      |
  | setmeal_id  |      套餐 ID      |
  | dish_flavor |     菜品口味      |
  |   number    |     商品份数      |
  |   amount    |      总金额       |







### 4.3 搭架子

- 接下来就针对数据表 `orders` 和 `order_detail` 把对应的实体类、数据层、业务层和控制层的架子搭起来。

#### ① 实体类

- 创建订单表 `orders` 实体类 `src/main/java/edu/ouc/entity/Order.java` ：

  ```java
  @Data
  public class Order {
  
      private static final Long serialVersionUID = 5L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
  
      // 订单号
      private String number;
  
      // 订单状态
      private Integer status;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long userId;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long addressBookId;
  
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime orderTime;
  
      // 支付时间
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime checkoutTime;
  
      private Integer payMethod;
  
      private BigDecimal amount;
  
      private String remark;
  
      private String phone;
  
      private String address;
  
      private String userName;
  
      // 收获联系人
      private String consignee;
  }
  ```
  
- 创建订单明细表  `order_detail` 实体类 `src/main/java/edu/ouc/entity/OrderDetail.java` ：

  ```java
  @Data
  public class OrderDetail {
  
      private static final Long serialVersionUID = 8L;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long id;
  
      private String name;
  
      private String image;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long orderId;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long dishId;
  
      @JsonSerialize(using = ToStringSerializer.class)
      private Long setmealId;
  
      private String dishFlavor;
  
      private Integer number;
  
      private BigDecimal amount;
  }
  ```

- 添加要自动填充的下单时间字段和支付时间字段，打开 `src/main/java/edu/ouc/common/MyMetaObjectHandler.java` ：

  ```java
  // 如果实体类中有下单时间，才会自动填充
  if (metaObject.hasSetter("orderTime")) {
      metaObject.setValue("orderTime", LocalDateTime.now());
  }
  if (metaObject.hasSetter("checkoutTime")) {
      metaObject.setValue("checkoutTime", LocalDateTime.now());
  }
  ```



#### ② 数据层

- 创建订单表 `orders` 数据层 `src/main/java/edu/ouc/mapper/OrderMapper.java` ：

  ```java
  @Mapper
  public interface OrderMapper extends BaseMapper<Order> {
  }
  ```
  
- 创建订单明细表  `order_detail` 数据层 `src/main/java/edu/ouc/mapper/OrderDetailMapper.java` ：

  ```java
  @Mapper
  public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
  }
  ```



#### ③ 业务层接口

- 创建订单表 `orders` 业务层接口 `src/main/java/edu/ouc/service/IOrderService.java` ：

  ```java
  public interface IOrderService extends IService<Order> {
  }
  ```
  
- 创建订单明细表 `order_detail` 业务层接口 `src/main/java/edu/ouc/service/IOrderDetailService.java` ：

  ```java
  public interface IOrderDetailService extends IService<OrderDetail> {
  }
  ```



#### ④ 业务层接口实现类

- 创建订单表 `orders` 业务层接口实现类 `src/main/java/edu/ouc/service/impl/OrderServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
  }
  ```
  
- 创建订单明细表 `order_detail` 业务层接口实现类 `src/main/java/edu/ouc/service/impl/OrderDetailServiceImpl.java` ：

  ```java
  @Slf4j
  @Service
  public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {
  }
  ```
  
  

#### ⑤ 控制层

- 创建订单表 `orders` 控制层`src/main/java/edu/ouc/controller/OrderController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/order")
  public class OrderController {
      @Autowired
      private OrderServiceImpl orderService;
  }
  ```
  
- 创建订单明细表 `order_detail` 控制层`src/main/java/edu/ouc/controller/OrderDetailController.java` ：

  ```java
  @Slf4j
  @RestController
  @RequestMapping("/orderDetail")
  public class OrderDetailController {
      @Autowired
      private OrderDetailServiceImpl orderDetailService;
  }
  ```





### 4.4 获取当前用户的默认地址



#### ① 请求分析

- 用户点击【去结算】后，需要获取当前用户的默认地址展示在结算页面上。

- 前端以 GET 方式向后端 URL 为 `/addressBook/default` 的地址发送请求。

  ![image-20221027093901772](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027093901772.png)

  



#### ② 业务层开发

- 地址簿 `AddressBook` 业务层接口 `IAddressBookService.java` ：

  ```java
  // 获取当前用户的默认地址
  AddressBook getDefault();
  ```



- 地址簿 `AddressBook` 业务层接口实现类 `AddressBookServiceImpl.java` ：

  ```java
  // 获取当前用户的默认地址
  @Override
  public AddressBook getDefault() {
      // 1.获取当前用户ID
      Long userId = BaseContext.getCurrentUserId();
      // 2.创建查询条件封装器
      LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
      // 3.添加查询条件：根据用户ID进行查询
      lqw.eq(userId != null, AddressBook::getUserId, userId);
      // 4.添加查询条件：查询是默认地址的地址
      lqw.eq(AddressBook::getIsDefault, 1);
      // 5.调用数据层根据查询条件封装器查询
      return this.getOne(lqw);
  }
  ```

  

#### ③ 控制层开发

- 地址簿 `AddressBook` 表现层 `AddressBookController.java` ：

  ```java
  // 获取当前用户的默认地址
  @GetMapping("/default")
  public R<AddressBook> getDefault() {
      return R.success(addressBookService.getDefault());
  }
  ```



#### ④ 功能测试

- 在订单结算页面显示用户的默认地址：

  ![image-20221027112129997](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027112129997.png)

   



### 4.5 提交订单



#### ① 请求分析

- 用户点击【去支付】后，就会提交当前订单。

- 前端以 POST 方式向后端 URL 为 `/order/submit` 的地址发送请求。

  ![image-20221027112410935](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027112410935.png)

- 负载是，备注信息 `remark` 、支付方式 `paymethod` 、地址ID `addressBookId` 。

  ![image-20221027112557230](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027112557230.png)



#### ② 业务层开发

- 订单提交的时候也会同时把订单详情插入到订单明细表 `order_detail` 中。

- 订单表 `orders` 业务层接口 `IOrderService.java` ：

  ```java
  // 提交(添加)订单
  Boolean submit(Orders orders);
  ```



- 订单表 `orders` 业务层接口实现类 `OrderServiceImpl.java` ：

  ```java
  @Autowired
  private OrderDetailServiceImpl orderDetailService;
  @Autowired
  private ShoppingCartServiceImpl shoppingCartService;
  @Autowired
  private AddressBookServiceImpl addressBookService;
  @Autowired
  private UserServiceImpl userService;
  
  // 提交(添加)订单
  @Override
  @Transactional  // 涉及到两张表的插入操作需要打开事务控制
  public Boolean submit(Orders orders) {
  
      // 1.获取当前登录用户ID
      Long userId = BaseContext.getCurrentUserId();
  
  
      // 2.调用购物车ShoppingCart业务层的获取购物车信息
      LambdaQueryWrapper<ShoppingCart> shoppingCartLqw = new LambdaQueryWrapper<>();
      shoppingCartLqw.eq(userId != null, ShoppingCart::getUserId, userId);
      List<ShoppingCart> shoppingCarts = shoppingCartService.list(shoppingCartLqw);
      // 如果购物车为空，则抛出业务异常
      if (shoppingCarts.isEmpty()) {
          throw new CustomException("购物车为空，无法结算");
      }
  
  
      // 3.调用地址簿AddressBook业务层获取当前派送的地址信息
      AddressBook addressBook = addressBookService.getById(orders.getAddressBookId());
      // 如果地址信息为空，则抛出业务异常
      if (addressBook == null) {
          throw new CustomException("地址信息为空，无法下单");
      }
  
  
      // 4.调用用户业务层user表获取用户信息
      User user = userService.getById(userId);
  
  
      // 5.为订单对象的属性一一赋值
      long orderId = IdWorker.getId();
      // 使用AtomicInteger计算商品总金额，保证高并发下的线程安全
      AtomicInteger amount = new AtomicInteger(0);
  
      // 6.新增订单明细，用购物车的stream流复制
      List<OrderDetail> orderDetails = shoppingCarts.stream().map(shoppingCart -> {
          OrderDetail orderDetail = new OrderDetail();
          // 复制属性值
          orderDetail.setOrderId(orderId);
          orderDetail.setNumber(shoppingCart.getNumber());
          orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
          orderDetail.setDishId(shoppingCart.getDishId());
          orderDetail.setSetmealId(shoppingCart.getSetmealId());
          orderDetail.setName(shoppingCart.getName());
          orderDetail.setImage(shoppingCart.getImage());
          orderDetail.setAmount(shoppingCart.getAmount());
          // 计算订单总金额
          amount.addAndGet(shoppingCart.getAmount().multiply(new BigDecimal(shoppingCart.getNumber())).intValue());
          return orderDetail;
      }).collect(Collectors.toList());
  
      // 生成并设置订单号
      orders.setNumber(String.valueOf(orderId));
      // 设置下单用户ID
      orders.setUserId(userId);
      // 设置订单状态为待派送
      orders.setStatus(2);
      // 设置商品总金额
      orders.setAmount(new BigDecimal(amount.get()));
      // 设置订单客户手机号
      orders.setPhone(addressBook.getPhone());
      // 设置收货人姓名
      orders.setConsignee(addressBook.getConsignee());
      // 设置用户名
      orders.setUserName(user.getName());
      // 设置地址详情，包含省市区
      orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                        + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                        + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                        + addressBook.getDetail());
  
      // 7.调用订单数据层新增订单
      this.save(orders);
  
  
      // 8.批量新增订单明细
      orderDetailService.saveBatch(orderDetails);
  
      // 9.下单完成后清空购物车数据
      return shoppingCartService.remove(shoppingCartLqw);
  }
  ```
  
  

#### ③ 控制层开发

- 订单表 `orders` 表现层 `OrderController.java` ：

  ```java
  // 提交(添加)订单
  @PostMapping("/submit")
  public R<String> submit(@RequestBody Orders orders) {
      if (orderService.submit(orders)) {
          return R.success("下单成功");
      }
      return R.error("下单失败");
  }
  ```



### 4.6 订单分页展示



#### ① 请求分析

- 用户点击【个人中心】后，就会展示最近订单信息。

- 前端以 GET 方式向后端 URL 为 `/order/userPage` 的地址发送请求。

  ![image-20221027161150666](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027161150666.png)

- 负载是，当前页码 `page` 、每页多少条记录 `pageSize` 。

  ![image-20221027161206060](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221027161206060.png)



#### ② DTO开发

- 由于查询分页订单的同时要返回订单明细，因此要创建订单 DTO 类 `OrderDto` 来把订单 `Orders` 和订单明细 `OrderDetail` 封装在一起。

- 创建 `src/main/java/edu/ouc/dto/OrderDto.java` ：

  ```java
  @Data
  public class OrderDto extends Orders {
      private List<OrderDetail> orderDetails;
  }
  ```





#### ② 业务层开发

- 

- 订单表 `orders` 业务层接口 `IOrderService.java` ：

  ```java
  // 获取订单分页展示
  Page<OrderDto> getPage(Long page, Long pageSize);
  ```



- 订单表 `orders` 业务层接口实现类 `OrderServiceImpl.java` ：

  ```java
  // 获取订单分页展示
  @Override
  public Page<OrderDto> getPage(Long page, Long pageSize) {
      // 1.创建分页封装器
      Page<Orders> ordersPage = new Page<>(page, pageSize);
      // 2.创建OrderDto的分页封装器
      Page<OrderDto> dtoPage = new Page<>();
  
      // 3.创建Orders的查询条件封装器
      LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
      // 3.1 添加查询条件：按下单时间降序排列
      lqw.orderByDesc(Orders::getOrderTime);
      // 3.2 条件查询条件：按当前用户ID查询
      Long userId = BaseContext.getCurrentUserId();
      lqw.eq(userId != null, Orders::getUserId, userId);
      // 4.Orders分页查询
      this.page(ordersPage, lqw);
  
      // 5.除了Record都复制
      BeanUtils.copyProperties(ordersPage, dtoPage, "records");
  
      // 6.获取当前用户所有的order对象
      List<Orders> orders = this.list(lqw);
      // 7.通过stream流逐一包装成OrderDto对象
      List<OrderDto> orderDtos = orders.stream().map(order -> {
          // 7.1 创建OrderDto对象
          OrderDto orderDto = new OrderDto();
          // 7.2 拷贝属性
          BeanUtils.copyProperties(order, orderDto);
          // 7.3 调用OrderDetail业务层获取订单明细集合
          LambdaQueryWrapper<OrderDetail> orderDetailLqw = new LambdaQueryWrapper<>();
          orderDetailLqw.eq(OrderDetail::getOrderId, order.getNumber());
          List<OrderDetail> orderDetails = orderDetailService.list(orderDetailLqw);
          // 7.4 设置orderDto的订单明细属性
          orderDto.setOrderDetails(orderDetails);
          // 7.5 返回orderDto
          return orderDto;
      }).collect(Collectors.toList());
  
      // 8.设置dtoPage的records属性
      dtoPage.setRecords(orderDtos);
      return dtoPage;
  }
  ```

  

#### ③ 控制层开发

- 订单表 `orders` 表现层 `OrderController.java` ：

  ```java
  // 获取订单分页展示
  @GetMapping("/userPage")
  public R<Page<OrderDto>> getPage(Long page, Long pageSize) {
      return R.success(orderService.getPage(page, pageSize));
  }
  ```





# 十一、 后台订单管理业务



## 1. 订单分页与订单查询



### 1.1 需求分析



#### ① 功能分析

- 后台的订单分页展示与订单搜索可以合并成一个方法。



#### ② 请求分析

- 用户点击【订单明细】或者输入订单搜索条件点击【查询】后，前端以 GET 方式向后端 URL 为 `/order/page` 的地址发送请求。

  ![image-20221028140616673](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221028140616673.png)

- 负载为分页信息与订单号查询、按下单时间范围查询：

  ![image-20221028140720875](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221028140720875.png)



### 1.2 代码开发

- 订单 Orders 的架子已经在开发客户端的时候已经搭起来了，只需要往上添加功能即可。



#### ① 业务层开发



- 订单表 `orders` 业务层接口 `IOrderService.java` ：

  ```java
  // 后台管理端获取订单分页展示
  Page<OrderDto> getAllPage(Long page, Long pageSize, String number, String beginTime, String endTime);
  ```



- 订单表 `orders` 业务层接口实现类 `OrderServiceImpl.java` ：

  ```java
  // 后台管理端获取订单分页展示
  @Override
  public Page<OrderDto> getAllPage(Long page, Long pageSize, String number, String beginTime, String endTime) {
      // 1.创建分页封装器
      Page<Orders> ordersPage = new Page<>(page, pageSize);
      // 2.创建OrderDto的分页封装器
      Page<OrderDto> dtoPage = new Page<>();
  
      // 3.创建Orders的查询条件封装器
      LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
      // 3.1 添加查询条件：按下单时间降序排列
      lqw.orderByDesc(Orders::getOrderTime);
      // 3.2 添加查询条件：按订单号查询
      lqw.like(number != null, Orders::getNumber, number);
      // 3.3 添加查询条件：  动态SQL-字符串使用StringUtils.isNotEmpty这个方法来判断
      lqw.gt(StringUtils.isNotEmpty(beginTime), Orders::getOrderTime, beginTime);
      lqw.lt(StringUtils.isNotEmpty(endTime), Orders::getOrderTime, endTime);
      // 4.Orders分页查询
      this.page(ordersPage, lqw);
  
      // 5.除了Record都复制
      BeanUtils.copyProperties(ordersPage, dtoPage, "records");
  
      // 6.获取当前用户所有的order对象
      List<Orders> orders = this.list(lqw);
      // 7.通过stream流逐一包装成OrderDto对象
      List<OrderDto> orderDtos = orders.stream().map(order -> {
          // 7.1 创建OrderDto对象
          OrderDto orderDto = new OrderDto();
          // 7.2 拷贝属性
          BeanUtils.copyProperties(order, orderDto);
          // 7.3 调用OrderDetail业务层获取订单明细集合
          LambdaQueryWrapper<OrderDetail> orderDetailLqw = new LambdaQueryWrapper<>();
          orderDetailLqw.eq(OrderDetail::getOrderId, order.getNumber());
          List<OrderDetail> orderDetails = orderDetailService.list(orderDetailLqw);
          // 7.4 设置orderDto的订单明细属性
          orderDto.setOrderDetails(orderDetails);
          // 7.5 返回orderDto
          return orderDto;
      }).collect(Collectors.toList());
  
      // 8.设置dtoPage的records属性
      dtoPage.setRecords(orderDtos);
      return dtoPage;
  }
  ```

  

#### ② 控制层开发

- 订单表 `orders` 表现层 `OrderController.java` ：

  ```java
  // 后台管理端获取订单分页展示
  @GetMapping("/page")
  public R<Page<OrderDto>> page(Long page, Long pageSize, String number, String beginTime, String endTime) {
      return R.success(orderService.getAllPage(page, pageSize, number, beginTime, endTime));
  }
  ```





## 2. 订单状态修改



### 2.1 需求分析



#### ① 功能分析

- 后台用户点击【派送】后，把订单状态由 2 改成了 3。



#### ② 请求分析

- 用户点击【派送】后，前端以 PUT 方式向后端 URL 为 `/order` 的地址发送请求。

  ![image-20221028143915293](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221028143915293.png)

- 负载是订单号和要修改的状态：

  ![image-20221028144004907](https://raw.githubusercontent.com/SihangXie/pic-bed/master/img/image-20221028144004907.png)



### 2.2 代码开发

- 订单 Orders 的架子已经在开发客户端的时候已经搭起来了，只需要往上添加功能即可。



#### ① 业务层开发



- 订单表 `orders` 业务层接口 `IOrderService.java` ：

  ```java
  // 修改订单状态
  Boolean update(Orders order);
  ```



- 订单表 `orders` 业务层接口实现类 `OrderServiceImpl.java` ：

  ```java
  // 修改订单状态
  @Override
  public Boolean update(Orders order) {
      return this.updateById(order);
  }
  ```

  

#### ② 控制层开发

- 订单表 `orders` 表现层 `OrderController.java` ：

  ```java
  // 修改订单状态
  @PutMapping
  public R<String> update(@RequestBody Orders order) {
      if (orderService.update(order)) {
          return R.success("修改成功");
      }
      return R.error("修改失败");
  }
  ```





- 至此，海大送餐基础功能就全部开发完毕了。完结撒花！
