[TOC]


# 前言

<font color=#999AAA >对于物联网的后端架构设计也是在不断地摸索中，业务划分的可能还不是很清晰，大家可根据实际情况进行取舍。</font>

| 目录                |
| ------------------- |
| 《DDD领域驱动设计》 |

# 一、概述

<font color=#999AAA >提示：这里可以添加本文要记录的大概内容</font>

## 1.1 功能需求

1. 实体设备接入及控制
2. 服务治理及监控
3. 业务权限控制
4. 高阶治理-机器学习数据预测、告警业务流处理等

## 1.2 业务流程

我们从大致的三个方面去进行分析

* 对于数据的提供者
* 对于数据的获取者及实际的业务操作用户
* 从业务安全角度出发

### 数据提供者

从南向业务出发，不谈接口技术，只谈业务情况，大致分为两个类别

1. 检测型设备
2. 控制型设备

对于检测型设备来说，是只提供数据传输功能而没有控制效果（或者说它的控制效果对于实际业务情况的帮助不大，例如单纯的开关控制），例如光照传感器设备、客流统计设备、温湿度检测设备。

而对于控制型设备，它传输的数据主要是来源于设备的实时状态，例如灯光、窗帘等，并且从数据传输来说，它是双向的，控制方传输控制指令，设备方返回状态值。

对于上述的两种情况，其实从业务处理中，可以浅划分成两种处理方式：

1. 实时性处理
2. 异步处理

![](https://csdn-pic-1301850093.cos.ap-guangzhou.myqcloud.com/csdn-pic/iot平台架构设计-业务流程-数据提供者数据处理方式.jpg)

这一举措的目的主要是两块

* 依赖解耦 - 大量的feign调用是需要提前引入各方的API-JAR，数量及业务过多且复杂的情况下稍不留神将导致依赖循环等恶行问题；
* 功能分离

### 业务操作人

对于实际业务操作人，也就是用户，需要考虑几个问题

1. 权限分离

   对于权限分离，例如管理人员、普通用户，是否可操作某个区域的设备？是否可查看某个区域的数据？此时我们就需要做到几个方面：

    * 用户操作记录保存
    * 操作业务的区分，控制 or 查询 ？
    * 权限划分数据表

2. 请求内容合法性

3. 请求来源端

   从Unity客户端，抑或是小程序等方向进行请求，在操作同一设备的相反功能情况下，势必会“打架”，是否需要做短延时/同步锁处理（某功能操作后延时2秒后才可以继续操作）？

### 业务安全

也还是从两个方面去说明

* 对外安全

  代码漏洞、0day攻击等；

* 对内安全

  业务所能承受的最大负载、人员误操作等方面；

## 1.3 中间件分析

<font color=#999AAA >针对各中间件对应实际的业务效果</font>

### 数据存储

MySQL

Apache IOT DB - 时序性数据库的选型

MongoDB

### 缓存

Redis

### 消息队列

MQTT

RabbitMQ

RocketMQ

### 负载均衡

LVS

Nginx

### 分布式应用协调

Zookeeper

## 1.4 整体架构设计

根据上述的内容总结后，大致的架构图如下

![](https://csdn-pic-1301850093.cos.ap-guangzhou.myqcloud.com/csdn-pic/iot平台架构设计-架构设计-1.jpg)

针对架构区域进行分区剖析

* 北向对接数据请求客户端（如Unity模型、小程序等），单向反馈数据与控制状态；
* 南向对接底层数据源，如部分中控厂家、单设备厂家（如施耐德、西门子）、MQTT、Modbus设备及云平台等，实时获取到设备数据与控制接口；
* 在服务治理上，根据实际需要补充如定时任务框架、线程池监控等第三方服务运维治理组件，保障系统运行稳定性及线上排障有效化；
* 同时东向对接数据存储源，本地MySQL、Redis缓存，或者云端虚拟化产品；
* 业务模块中，根据业务情况进行划分，考虑到划分的越细也同时会导致微服务架构的复杂化，要根据实际情况进行取舍；

# 二、实践落地

<font color=#999AAA >提示：这里可以添加本文要记录的大概内容</font>

## 2.1 包目录设计

源码在最后一个章节，已开源

```shell
├─.idea
├─blade-auth-------------------认证服务
├─blade-common--------------公共组件
├─blade-gateway---------------网关Gateway
├─blade-ops--------------------运维中心
│  ├─blade-develop-----------------------------------代码生成器
│  ├─blade-log-----------------------------------日志收集服务
│  ├─blade-report-----------------------------------报表管理
│  ├─blade-resource-----------------------------------资源管理
│  ├─blade-swagger-----------------------------------接口管理
│  └─blade-task------------------------------------------定时任务服务
├─blade-service----------------业务中心
│  ├─blade-advanced------------------------------------高级业务
│  ├─blade-basics------------------------------------------基础服务
│  │  ├─blade-desk------------------------------------------公告管理
│  │  ├─blade-office------------------------------------------办公管理
│  │  ├─blade-system------------------------------------------系统管理
│  │  └─blade-user------------------------------------------用户管理
│  ├─blade-device--------------设备运维
│  │  ├─blade-energy------------------------------------------能耗服务
│  │  ├─blade-environment-----------------------------------环境检测
│  │  └─blade-lamp------------------------------------------灯管理
│  └─blade-external--------------外部接口
│      ├─blade-benyuan-server----------------------------本原私定Socket协议服务
│      └─blade-modbus-client----------------------------Modbus客户端
├─blade-service-api
│  ├─blade-advanced-api
│  ├─blade-basics-api
│  │  ├─blade-desk-api
│  │  ├─blade-dict-api
│  │  ├─blade-office-api
│  │  ├─blade-scope-api
│  │  ├─blade-system-api
│  │  └─blade-user-api
│  ├─blade-device-api
│  │  ├─blade-energy-api
│  │  ├─blade-environment-api
│  │  └─blade-lamp-api
│  └─blade-external-api
├─doc--------------文档说明
├─my-config--------------配置文件目录
├─pic--------------图片
└─script
```

## 2.2 系统模块组设计

XMind确实是挺好用的，我种草了。

![](https://csdn-pic-1301850093.cos.ap-guangzhou.myqcloud.com/csdn-pic/iot平台架构设计-系统模块组设计-1.png)

最后大家可根据实际业务及功能需要进行扩展，目前设计的只针对与物联网行业，但实际上不管怎么变化，架构内核是不变的。

## 2.3 部署说明

相关的中间件及一些配置都在**my-config**目录中，例如nacos及MySQL的基础配置，导入即可。

