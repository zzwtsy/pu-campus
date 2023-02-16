# pu-campus

## 免责声明

本程序仅作为学习交流之用，使用者造成的任何后果由其自行承担  
使用本程序需要遵守 AGPL-3.0 开源协议

## TODO

- [x] 用户登陆

- [x] 获取新活动列表

- [x] 获取待签到活动列表(未经验证)

- [x] 获取待签退活动列表(未经验证)

- [x] 获取某一天活动列表

- [ ] 获取活动详细信息

- [x] 获取学分详情

- [x] 获取未发放学分活动列表

- [x] 定时推送活动信息

## 快速开始

1. 安装[MCL](https://github.com/iTXTech/mcl-installer)
2. 从 Release 下载 jar 包
3. 放入 Plugins 文件夹
4. 启动 MCL
5. 修改 setting.json 配置文件
6. 重新启动 MCL

## 配置文件说明

### setting.json

路径：config/cn.zzwtsy.pu/setting.yml
| 值 | 说明 |
| ------------- | -------------------------------------- |
| groupId | qq 群号 |
| adminId | 管理员 qq 号 |
| botId | 机器人 qq 号 |
| timedTaskTime | 定时任务时间【24 小时制】(示例：01:01) |
| emailSuffix | 学校邮箱后缀 |

# [**pu 校园邮箱后缀点击查看**](https://blog.yumdeb.top/tools/PuSchoolInfo.html)

### command.json

路径：config/cn.zzwtsy.pu/command.yml
| 值 | 默认示例 | 说明 |
| ----------------------------------- | ---------------------------------------------------- | -------------------------------------------------------- |
| commandPrefix |# | 命令前缀 |
| help | #help | 获取帮助信息 |
| **_以下为仅 qq 群可使用命令_** | **_以下为仅 qq 群可使用命令_** | **_以下为仅 qq 群可使用命令_** |
| queryUserCreditInfo | #学分 | 查询自己的学分 |
| querySignInEventList | #签到 | 获取需要签到的活动 |
| querySignOutEventList | #签退 | 获取需要签退的活动 |
| queryActivityDetailById | | 查询活动详细信息（还没实现） |
| getCalendarEventList | #活动 <今日 \| 今天 \| 明日 \| 明天 \| 昨日 \| 昨天 \| 03-03> | 根据日期获取可参加的活动信息 |
| queryUserEventEndUnissuedCreditList | #未发放 | 获取活动已经结束还没发放学分的活动 |
| **_以下为私聊可使用命令_** | **_以下为私聊可使用命令_** | **_以下为私聊可使用命令_** |
| login | #login <账号\|oauthToken> <密码\|oauthTokenSecret> | 登录 pu 账号 |
| **_以下为管理员命令（仅私聊环境）_** | **_以下为管理员命令（仅私聊环境）_** | **_以下为管理员命令（仅私聊环境）_** |
| deleteUser | #删除信息 | 删除自己的信息 |
| adminDeleteUser | #删除用户 <用户 qq 号> | 管理员删除指定用户信息 |
| addPublicToken | #添加tk <账号\|oauthToken> <密码\|oauthTokenSecret> | 添加公共 pu Token,用于定时任务，不设置则使用管理员
Token |
| timedTask | #定时任务 <时间*24 小时*(01:01)> <qq群号> | 设置定时任务 |

## 获取 pu oauthToken 和 oauthTokenSecret

- 注意：使用用户名密码登录后 pu 软件会登录失效，再次使用 pu 需重新登录。使用 Token 则不会出现此问题

1. 使用抓包软件抓取 pu 校园数据
2. 点击任意链接找到`oauth_token`和`oauth_token_secret`复制值
3. 将`oauth_token`和`oauth_token_secret`以 #login <oauth_token> <oauth_token_secret> 这样的形式发送给机器人即可完成登录


## 活动列表 JSON 解析

- title：活动标题

- id：活动 id

- sTime：活动开始时间（时间戳）

- eTime：活动结束时间（时间戳）

- startline：报名开始时间（时间戳）

- deadline：报名开始结束（时间戳）

- joinCount：已参加人数

- limitCount：剩余可参加人数

- address：活动地址

- description：活动简介

- credit_name：学分类型

- is_need_sign_out：

  - 0：无需签退

- eventStatus：当前活动状态

  - 4：活动未参加且正在进行中
  - 5：活动未参加且已结束
  - 8：活动已参加且已结束

- isAllowEvent：是否可报名

  - 0：当前不可报名
  - 1：当前可报名

- is_joined：是否已参加活动
  - 0：未参加当前活动
  - 1：已参加当前活动
