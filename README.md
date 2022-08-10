# management
## 项目目标
This project develops a management system for devices used in intelligent transportation.
## 主要功能与特点
1. PUFs技术的服务平台：在服务器端利用密码算法+CRPs管理，实现与底层物联网设备的交互，实现PUFs安全技术的应用。

2. 基于区块链的设备管理平台：利用区块链平台，安全、高效地管理物联网设备。

3. NAS平台的交互：利用NAS节点，分担服务器端的存储需求。
## 项目配置
1.. Java 1.8 
2.  SpringBooT 2.1.3
3.  Mysql
4.  Hyperledger Fabric 1.4+Java-sdk
5.  前段框架待定

## 小组成员
ZY ZZM YLJ NRJ OY 
## Version 1 平台的基本搭建
### 前端开发
### 后端开发
1.  用户管理
2.  设备管理
3.  网关管理
### 区块链客户端
#### 1.  区块链交互
（1）使用Java-SDK实现与链码交互：链码交互分为query和invoke两种，传入参数为两部分：链码函数的名称；链码参数，args[].
（2）链码参数的传入方式：将参数编码为string传入，推荐Base64编码。
#### 区块链部署与开发
1.  Hyperledger Fabric 链码开发
2.  区块链配置





## Version 2 PUFs技术的服务平台
任务一：部署基本的密码学算法
任务二： 实现基于PUFs的设备认证协议
任务三：开发管理界面

## Version3  基于区块链的设备管理平台

## Version4  NAS平台的交互

## 使用说明