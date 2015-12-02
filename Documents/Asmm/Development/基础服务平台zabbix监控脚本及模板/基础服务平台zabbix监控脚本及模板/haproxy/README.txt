1、
注：如果没有安装gcc等编译环境，首先执行 yum groupinstall "Development Tools"

[root@haproxy-01 ~]# cd /usr/local/src


2、下载socat-1.7.1.3.tar.gz，下载地址：http://www.dest-unreach.org/socat/download/socat-1.7.1.3.tar.gz

将socat-1.7.1.3.tar.gz拷到/usr/local/src目录下

[root@haproxy-01 ~]# tar xzf socat-1.7.1.3.tar.gz
[root@haproxy-01 ~]# cd socat-1.7.1.3/
[root@haproxy-01 ~]# ./configure
[root@haproxy-01 ~]# make
[root@haproxy-01 ~]# make install


3、mkdir -p /usr/local/zabbix/bin

将haproxy_info.sh、haproxy_pool_discovery.py和haproxy_stat.sh三个脚本拷到/usr/local/zabbix/bin目录下

4、
visudo

在 #Defaults    requiretty 下面一行加入 
zabbixagent ALL=(root) NOPASSWD:/usr/local/bin/socat

