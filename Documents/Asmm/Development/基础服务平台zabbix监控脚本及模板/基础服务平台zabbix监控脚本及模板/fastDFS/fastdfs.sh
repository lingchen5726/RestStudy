#!/bin/bash
source /etc/profile
/usr/bin/fdfs_monitor /data/fastdfs/install/conf/client.conf | grep -A 59  "ip_addr = LOCALIP" >/tmp/fdfs.txt