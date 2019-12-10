### Cluster模式搭建
---
##### 基于Ubuntu 16.0.4
1. **进入准备安装的文件夹:**<br>`cd /usr/local/redis`<br>
2. **通过wget下载redis5.0.7压缩包:** <br>`wget http://download.redis.io/releases/redis-5.0.7.tar.gz`<br>
3. **解压:** <br>`tar xzf redis-5.0.7.tar.gz`
4. **进入文件夹:** <br>`cd redis-5.0.7`
5. **编译:** <br>`make`
6. **启动集群模式:** <br>`./runtest-cluster`
7. **进入redis命令:** <br>`redis-cli -c -p (port)`
