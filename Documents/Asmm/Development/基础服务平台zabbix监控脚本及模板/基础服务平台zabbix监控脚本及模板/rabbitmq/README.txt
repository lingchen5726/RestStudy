1、将zbx_export_templates_rabbitmq.xml导入到zabbix中
2、将userparameter_rabbitmq.conf拷至每个MySQL节点/etc/zabbix/zabbix_agentd.d/目录下
3、将rabbitmq.sh放在每个MySQL节点下/root/目录下
4、在每个MySQL节点将下命令加入contab计划任务执行
   */1 * * * * sh /root/rabbitmq.sh >>/root/log.rabbitmq