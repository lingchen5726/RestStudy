#!/bin/bash
source /etc/profile
V_HOST=`hostname`

rabbitmqctl -n rabbit@$V_HOST report  >/tmp/rabbit.txt

grep -A48 'Status of node rabbit@'$V_HOST /tmp/rabbit.txt > /tmp/rabbit_1.txt
