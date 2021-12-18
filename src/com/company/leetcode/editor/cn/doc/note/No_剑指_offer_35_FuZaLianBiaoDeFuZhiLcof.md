# 最初解法：map存对应关系

一开始想的很朴素：存旧节点和index之间的对应关系，再存新节点和index的关系，因为index是不变的，一个正查，一个反查，就能赋值random了

              if (head == null) {
                  return null;
              }
              Map<Integer, Integer> oldNode2Index = new HashMap<>();
              Map<Integer, Node> index2newNode = new HashMap<>();
              //遍历一遍老链表，储存每个节点Hash对应的下标索引 - oldNode2Index 反查
              Node p = head;
              Integer idx = 0;
              while (p != null) {
                  Integer oldNodeHashCode = p.hashCode();
                  oldNode2Index.put(oldNodeHashCode, idx);
                  idx++;
                  p = p.next;
              }
              //复制新链表，创建新链表下标索引和节点地址的关系 index2newNode，此时random还没有被赋值
              Node newHead = new Node(-1);
              p = head;
              Node cur = newHead;
              idx = 0;
              while (p != null) {
                  cur.next =  new Node(p.val);
                  cur = cur.next;
                  index2newNode.put(idx, cur);//创建索引
                  idx++;
                  p = p.next;
              }
              //根据反查表和正查表，填充random
              Node p1 = head, p2 = newHead.next;
              while (p1 != null) {
                  if (p1.random == null) {
                      p2.random = null;
                  } else {
                      Integer newIdx = oldNode2Index.get(p1.random.hashCode());
                      Node newRandom = index2newNode.get(newIdx);
                      p2.random = newRandom;
                  }
                  p1 = p1.next;
                  p2 = p2.next;
              }
              return newHead.next;

但是没有想到：其实oldNode2Index和index2newNode可以整合成oldNode2newNode