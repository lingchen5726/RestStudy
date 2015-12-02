1、将fastdfs.sh放在/root目录下面；

2、在fastdfs.sh文件中，需要将LOCALIP改为本机IP；

3、定时执行fastdfs.sh脚本：

	crontab -e
	然后加入下面语句，保存并退出。
	*/1 * * * *   sh /root/fastdfs.sh >> /root/log.fastdfs
