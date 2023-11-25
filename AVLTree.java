public class AVLTree{

    private static final class AVLNode {
        public AVLNode left;
        public AVLNode right;
        public AVLNode parent;
        private Integer element;

        public AVLNode(Integer element) {
            left = null;
            right = null;
            parent = null;
            this.element = element;
        }

        public AVLNode getLeft() {
            return left;
        }

        public void setLeft(AVLNode left) {
            this.left = left;
        }

        public AVLNode getRight() {
            return right;
        }

        public void setRight(AVLNode right) {
            this.right = right;
        }

        public AVLNode getParent() {
            return parent;
        }

        public void setParent(AVLNode parent) {
            this.parent = parent;
        }
    }
    
    private int count;
    private AVLNode root;

    public AVLTree(){
        count = 0; 
        root = null;
    }

    public void clear(){
        count = 0;
        root = null;
    }

    public boolean isEmpty(){
        return (root == null);
    }

    public int size(){
        return count;
    }
    
    private AVLNode searchNode (Integer elem, AVLNode target){
        AVLNode aux = target;
        int compare = elem.compareTo(aux.element);
        
        if (compare == 0) {
            return target;
        } else if(compare > 0) {
            return searchNode(elem, target.right);
        } else{
            return searchNode(elem, target.left);
        }
    }

    public boolean contains(Integer elem){
        AVLNode aux = searchNode(elem, root);

        if (aux == null) {
            return false;
        } else{
            return true;
        }
    }

    
}