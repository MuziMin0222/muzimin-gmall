# 环境的搭建

## Linux虚拟机搭建

* virtualbox官网：https://www.virtualbox.org
  * 下载

* vagrant官网：https://www.vagrantup.com/
  * 下载

* vagrant官方镜像：https://app.vagrantup.com/boxes/search
  * 查找
* 在D盘创建新的目录，在该目录下创建Vagrantfile,执行

```
vagrant init centos/7
```

* 启动虚拟机

```
vagrant up
```

* 虚拟机网络配置

  * 查看本机中的网关
  * 修改vagrantfile，这个IP是与本机网卡保持一致的IP

  ```
    config.vm.network "private_network",ip: "192.168.56.10"
  ```

  * 重启虚拟机

  ```
  vagrant reload
  ```


* 进入虚拟机

```
vagrant ssh
```

* 切换到root用户

```
sudo -i
```

* 设置root用户的账号和密码

```
vi /etc/ssh/sshd_config
```

* 将下列值进行注释放开

```
LoginGraceTime 2m
PermitRootLogin yes
StrictModes yes

## 这个值从no转为yes
PasswordAuthentication yes
```

* ssh服务重启，退出虚拟机

```
service sshd restart
```

* 虚拟机重启

```
vagrant reload
```



## docker安装

```text
## 卸载Docker
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
# 安装 yum-utils 包
sudo yum install -y yum-utils

# 设置稳定镜像仓库
sudo yum-config-manager \
--add-repo \
https://download.docker.com/linux/centos/docker-ce.rep

## 配置国内的docker镜像地址
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

## 更新软件包索引
yum makecache fast

## 
yum-config-manager --save --setopt=download.docker.com_linux_centos_docker-ce.rep.skip_if_unavailable=true

## 安装docker-ce
yum install docker-ce docker-ce-cli containerd.io

## 查看版本
docker -v

## 启动dockers
sudo systemctl start docker

## 设置开启自启动docker
sudo systemctl enable docker

## 查看状态
systemctl status docker
## 运行hello world验证
sudo docker run hello-world 
```

## docker 安装mysql

```
docker run -p 3306:3306 --name mysql \
 -v /opt/mysql/log:/var/log/mysql \
 -v /opt/mysql/data:/var/lib/mysql \
 -v /opt/mysql/conf:/etc/mysql \
 -e MYSQL_ROOT_PASSWORD=root \
 -d mysql:5.7
 
###
参数：
● -p 3306:3306：将容器的3306端口映射到主机的3306端口
● --name：给容器命名
● -v /opt/mysql/log:/var/log/mysql：将配置文件挂载到主机/opt/..
● -e MYSQL_ROOT_PASSWORD=root：初始化root用户的密码为root
 
 ## 修改mysql的配置
 vi /opt/mysql/conf/my.cnf
 
 ## 内容如下
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve

## 重启mysql
docker restart mysql

## 设置mysql跟随docker启动而启动
docker update mysql --restart=always
```

## Docker安装redis

```
mkdir -p /opt/redis/conf
vi /opt/redis/conf/redis.conf
## 内容如下
appendonly yes

## 拉取redis并启动
docker run -p 6379:6379 --name redis \
-v /opt/redis/data:/data \
-v /opt/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf

## 设置redis跟随docker启动而启动
docker update redis --restart=always
```

## docker安装nacos

```
docker run -d \
--name nacos \
-p 8848:8848  -p 9848:9848 -p 9849:9849 \
--privileged=true \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e MODE=standalone \
-v /opt/nacos/logs/:/home/nacos/logs \
-v /opt/nacos/conf/:/home/nacos/conf/ \
--restart=always \
nacos/nacos-server
```



# 服务搭建

## 版本对应

| Spring cloud Alibaba | Spring Cloud Version   | Spring Boot Version |
|------------------------| ------------------- | ------------------- |
| 2021.0.4.0 | Spring Cloud 2021.0.4 | 2.6.11              |
