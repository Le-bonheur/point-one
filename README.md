# 推荐用户列表服务
程序包含两个API接口，分别为
/postWeibo 与
/suggest
## 1. /postWeibo
此接口接收两个参数，分别为 "**userID**"用户的ID 和 "**userAt**"被@的用户的ID集合，前者是字符串类型，后者是集合，
若返回 "success" 则说明数据保存成功，无返回或其他都表示数据没有被正确保存。
#### 注：用户ID为10位字符串（数字），数据库中现在有1000000000~1000010000的用户ID，但是不一定要使用现存的数据，程序中没有校验用户是否存在

## 2. /suggest
此接口接收一个参数，输入一个userID, 返回推荐的用户列表，参数名为 "**userID**" ，类型为字符串。

####推荐机制：在关系更强的放在前面的前提下，时间更早的放前面

比如 userA在一条微博上@了userB和userC，userA在另外一条微博上@了userD，那么如果看useB的个人主页就推荐 [userD, userC]，因为他们都被userA@过。
由于userB和userC之间的关系强过userD(因为userB和userC是在同一条微博被@的)，所以userC会在userD前面。在这个前提下，再按推文发送时间排序。
