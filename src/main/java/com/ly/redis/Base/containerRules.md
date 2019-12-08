#### 容器型数据结构的通用规则

---
list、set、hash、sortedSet为容器型数据类型,共享下面这两条通用规则
1. **create if noe exists:** 如果容器不存在,那就创建一个在进行操作。
2. **drop if no elements:** 如果容器里的元素没有了,那么立即删除容器,释放内存。