# 错误解法

这里错误的以为按前序顺序序列化之后，除去根节点的list正好是左子树占一半，右子树占一般，其实【不是满二叉树】

    public class Codec {
        private StringBuilder sb = new StringBuilder();
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
    
            serializeRecursion(root);
            return sb.toString();
        }
    
        private void serializeRecursion(TreeNode root) {
            //边界
            if (root != null && root.left == null && root.right == null) {
    
            }
            if (root == null) {
                sb.append("#");
                sb.append(",");
                return;
            }
            //前序
            sb.append(root.val);
            sb.append(",");
            serializeRecursion(root.left);
            serializeRecursion(root.right);
        }
    
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }
            String[] chars = data.split(",");
            return deserializeRecursion(chars, 0,chars.length-1);
        }
    
        private TreeNode deserializeRecursion(String[] chars,int start,int end) {
            if (start == end) {
                String ch = chars[start];
                if (ch.equals("#")) {
                    return null;
                }
                return new TreeNode(Integer.parseInt(ch));
            }
            String ch = chars[start];
            if (ch.equals("#")) {
                return null;
            }
            //前序
            TreeNode root = new TreeNode(Integer.parseInt(ch));
            int len = (end-start) / 2;
            root.left = deserializeRecursion(chars, start + 1, start + len);
            root.right = deserializeRecursion(chars, start + len + 1, end);
            return root;
    }

}