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

    public Integer getParent(Integer element){
       AVLNode aux = searchNode(element, root);

       if (aux == null) {
        return null;
       }

       if(aux.left == null){
        return null;
       }

       if (aux.right == null) {
            return null;
       }
    
       return aux.element;

    }

    public int height(){
        return calculoHeight(root);
    }

    private int calculoHeight(AVLNode n){
        if (n == null) {
            return 1;
        }

        int alturaLeft = calculoHeight(n.left);
        int alturaRight = calculoHeight(n.right);
        return 1+ Math.max(alturaLeft, alturaRight);

      
    }
}