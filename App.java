public class App {
    public static void main(String[] args) {
        AVLTree a = new AVLTree();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(9);
        
        System.out.println("Altura da árvore: " + a.height());
        a.GeraDOT();
        a.clear();

        a.add(9);
        a.add(8);
        a.add(7);
        a.add(6);
        a.add(5);
        a.add(4);
        a.add(3);
        a.add(2);
        a.add(1);

        System.out.println("Elementos da árvore: \n" + a.positionsCentral());
        
        AVLTree clone = a.clone();
        clone.GeraDOT(); 
     }
}
