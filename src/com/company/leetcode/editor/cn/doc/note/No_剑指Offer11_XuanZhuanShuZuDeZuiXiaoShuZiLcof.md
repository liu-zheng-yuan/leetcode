# 每次更改上下界之后都要重新迭代二分一次
# 不能在一次迭代中改变两次上下界

### 第一次错误解法：没有考虑到有相同元素的情况
{
//特殊情况：只有一个数字。l-r = 1 假定了最少2个数字
if (numbers.length == 1) {
return numbers[0];
}
//特殊情况：如果没有旋转，最左一定小于最右。（只要旋转了一个，那最左一定大于最右）
if (numbers[0] < numbers[numbers.length - 1]) {
return numbers[0];
}
int l = 0;
int r = numbers.length - 1;
while (Math.abs(l - r) != 1) {
int mid = (l + r) / 2;
if (numbers[mid] >= l) {
l = mid;
continue;//每次更改上下界之后都要重新迭代二分一次，不能在一次迭代中改变两次上下界。不然就没有了二分的精神
}//如果mid<L:说明mid在后半序列上，但是此时不好判断左边界l，因为最小值一定在mid左边，所以不做处理
if (numbers[mid] <= r) {
r = mid;
continue;
}//如果mid > r：说明mid在前半序列上，但此时不好判断右边界r，因为最小值已经在mid右边，也不做处理
}
//当边界缩短到r-l = 1时，l一定是最大值-顶峰的坐标，r一定是最小值-低谷的坐标。
return Math.min(numbers[r], numbers[l]);
}

### 第二次
特殊情况：1 没有旋转 2.数组中有相同的值