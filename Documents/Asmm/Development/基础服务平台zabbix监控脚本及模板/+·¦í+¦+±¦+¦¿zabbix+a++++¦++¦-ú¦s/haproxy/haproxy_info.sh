#!/bin/bash
#This script is used for getting haproxy info such as version ,uptime and number of processes etc
 
metric=$1
stats_socket=/var/lib/haproxy/stats
info_file=/tmp/haproxy_info.csv
echo "show info"|/usr/bin/sudo /usr/local/bin/socat   unix-connect:$stats_socket  stdio > $info_file
grep $metric $info_file|awk '{print $2}'
