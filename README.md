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
5.  前段框架采用VUE

## 小组成员
ZY ZZM YLJ NRJ OY 
## Version 1 平台的基本搭建
### 前端开发
1. 用户信息查询：（1）完成所有用户信息的查询与展示；（2）按类目展示，按照ID查询；
2.  用户登录、注册界面的开发；
3. 用户密码修改、找回界面的开发；

### 后端开发
1.  用户管理功能介绍（1）用户注册、登录；（2）用户信息查询；（3）密码修改、找回；（4）用户生成公私钥对，在区块链内写入公钥信息。
3.  设备管理
4.  网关管理
### 区块链客户端
#### 1.  区块链交互
（1）使用Java-SDK实现与链码交互：链码交互分为query和invoke两种，传入参数为两部分：链码函数的名称；链码参数，args[].
（2）链码参数的传入方式：将参数编码为string传入，推荐Base64编码。
#### 区块链部署与开发
1.  Hyperledger Fabric 链码开发
2.  区块链配置






### 区块链浏览器

## Version 2 PUFs技术的服务平台
1. 基于BR PUFs的设备认证：（1）接受物联网节点的TCP请求；（2）部署Python代码，该代码由李宜博士组实现，提供了PUF认证所需的节本功能；（3）通过后端服务，调用python程序的功能，将处理结果，直接返回给物联网节点。
2. 部署基本的密码学算法
3.  实现基于PUFs的设备认证协议
4. 开发管理界面

## Version3  基于区块链的设备管理平台

## Version4  NAS平台的交互

## 使用说明