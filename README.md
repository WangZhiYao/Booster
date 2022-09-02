# Booster

2021年国庆时答应朋友帮忙写的某国产 MMORPG 游戏代练记录工具，至今仍未写完（因为重构了好几次）（反正他也不急）

### 功能

1. 客户
   * [x] 创建客户
   * [x] 客户列表
   * [x] 客户详情
2. 角色
   * [x] 创建角色
   * [x] 客户的角色列表
   * [x] 角色详情页
   * [ ] 密码加密
3. 分类
   * [ ] 创建分类
   * [ ] 分类详情
4. 项目
   * [ ] 创建项目
   * [ ] 分类下的项目列表
5. 订单
   * [ ] 创建订单
   * [ ] 订单详情
   * [ ] 客户订单列表
   * [ ] 角色订单列表
6. 账单
   * [ ] 自动生成账单
7. 提醒
   * [ ] 创建提醒

### 项目使用到的技术

1. 整体项目使用 Kotlin + Flow + 协程，使用 [orbit-mvi](https://github.com/orbit-mvi/orbit-mvi) 来实现 MVI 架构 
2. 使用 [Jetpack](https://developer.android.com/jetpack) 中的 [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) 来实现依赖注入
3. 模块化，模块之间通过 Hilt 的依赖注入实现使用接口来进行模块间的通信，没有使用 [ARouter](https://github.com/alibaba/ARouter) 是因为该路由框架太重
4. 使用 [Room](https://developer.android.com/training/data-storage/room) + [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) 来存储本地数据
5. 使用 [Retrofit2](https://github.com/square/retrofit) + [OkHttp3](https://github.com/square/okhttp) + [Moshi](https://github.com/square/moshi) + 协程 请求网络数据
6. 使用 [ViewBinding](https://developer.android.com/topic/libraries/view-binding/)
