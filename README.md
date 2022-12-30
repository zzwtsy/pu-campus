# pu-campus

## TODO

- [x] 用户登陆

- [x] 获取新活动列表

- [ ] 获取待签到活动列表

- [ ] 获取待签退活动列表

- [x] 获取某一天活动列表

- [ ] 获取活动详细信息

- [x] 获取学分详情

- [ ] ~~定时推送活动信息~~

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