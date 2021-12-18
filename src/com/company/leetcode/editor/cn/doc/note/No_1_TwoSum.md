# 哈希表解法

本质是双重循环找符合条件的对象-用哈希表代替内循环-事先将可能符合条件的<key,对象>记录到哈希表上 再用一层循环生成trigger去找key(其实就是num[i])

哈希表里存的key：

1.可以是key：num[i] ，这样trigger 就要是 taget-num[j]，即“确定选择num[j]时，要配哪个num[i]才能达到target”

2.可以是key：taget-num[i],这样triggerr 就要是 num[j],即“确定选择num[i]时，要配哪个num[j]才能达到target”