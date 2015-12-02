#/usr/bin/python
#This script is used to discovery disk on the server
import subprocess
import json
args='''echo "show stat"|/usr/bin/sudo /usr/local/bin/socat stdio unix-connect:/var/lib/haproxy/stats|egrep -v '^#|^$'|awk -F',' '{print $1":"$2}' '''
 
t=subprocess.Popen(args,shell=True,stdout=subprocess.PIPE).communicate()[0]
pools=[]
for pool in t.split('\n'):
    if len(pool) != 0:
       pools.append({'{#POOL_NAME}':pool})
print json.dumps({'data':pools},indent=4,separators=(',',':'))
