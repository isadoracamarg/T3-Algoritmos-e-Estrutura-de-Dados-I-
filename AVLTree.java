public class AVLTree{

    private static final class Node {
        public Node father;
        public Node left;
        public Node right;
        private Integer element;
        private int balance;

        public Node(Integer element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
            balance = 0;
        }
    }
    
    //Atributos
    private int count;
    private Node root;

    public AVLTree(){
        count = 0; 
        root = null;
    }

    /**
     * Adiciona um novo elemento na árvore.
     * @param element elemento que será adicionado.
     */
    public void add(Integer element) {
        root = add(root, element, null);
        count++;
        verificaBalanceamento(root);
    }
    private Node add(Node n, Integer element, Node father) {
        if (n == null) { // insere
            Node aux = new Node(element);
            aux.father = father;
            return aux;
        }
        
        // Senao, insere na subarvore da esq ou da dir
        if (n.element.compareTo(element) < 0) {
            n.right = add(n.right, element, n); // dir
        }
        else {
            n.left = add(n.left, element, n); // esq
        }
        return n;
    }

     /**
     * Método que retorna a raíz da árvore.
     * @return a raíz da árvore.
     */
    public Integer getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    /**
     * Método que retorna o pai de um nodo filho.
     * @param element nodo filho.
     * @return nodo pai.
     */
    public Integer getParent(Integer element){
        Node aux = searchNode(element, root);
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

    /**
     * Esvazia a árvore.
     */
    public void clear(){
        count = 0;
        root = null;
    }

    /**
     * Verifica se a árvore está vazia.
     * @return "true" se a árvore estiver vazia e "false" se não.
     */
    public boolean isEmpty(){
        return (root == null);
    }

    /**
     * Verifica o tamanho da árvore.
     * @return o tamanho da árvore.
     */
    public int size(){
        return count;
    }
    
    /**
     * Método que procura um elemento na árvore.
     * @param elem elemento que será buscado.
     * @param target nodo auxiliar.
     * @return nodo que possui "elem"
     */
    private Node searchNode(Integer elem, Node target){
        if (elem == null || target == null)
            return null;
        Node aux = target;
        int compare = elem.compareTo(aux.element);
        
        if (compare == 0) {
            return target;
        } else if (compare > 0) {
            return searchNode(elem, target.right);
        } else {
            return searchNode(elem, target.left);
        }
    }

    /**
     * Método que verifica se a árvore possui um determinado elemento.
     * @param elem elemento que será buscado.
     * @return "true" se a árvore possuir "elem" e "false" se não.
     */
    public boolean contains(Integer elem){
     Node aux = searchNode(elem, root);
        if (aux == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Método que retorna a altura (nível mais alto) da árvore.
     * @return a altura da árvore.
     */
    public int height(){
        return calculoHeight(root);
    }

    private int calculoHeight(Node n) {
        if (n == null) {
            return -1; // para árvore vazia
        }
    
        int alturaLeft = calculoHeight(n.left);
        int alturaRight = calculoHeight(n.right);
    
        return 1 + Math.max(alturaLeft, alturaRight);
    }

    /**
     * Método que calcula o balancemento de um nodo.
     * Se o atributo balance for -2, a esquerda ta pesada. Se for +2, a direita ta pesada.
     * @param n
     */
    public void calculaBalanceamento(Node n) {
        if (n.right == null && n.left == null) {
            n.balance = 0;
        } else if (n.right != null && n.left == null) {
            n.balance = calculoHeight(n.right) - 0;
        } else if (n.right == null && n.left != null) {
            n.balance = 0 - calculoHeight(n.left);
        } else {
            n.balance = calculoHeight(n.right) - calculoHeight(n.left);
        }
        if(n.left != null){
            calculaBalanceamento(n.left); 
        }
        if(n.right != null){
            calculaBalanceamento(n.right);
        }
    }

    /**
     * Método que verifica o balanceamento da árvore.
     * @param n nodo raiz da árvore.
     * @return a rotação adequada para cada nodo ficar balanceado.
     */
    public Node verificaBalanceamento(Node n) {
        if (n == null) {
            return null;
        }
        if(n.right != null){
            n.right= verificaBalanceamento(n.right);
        }
        if(n.left != null){
            n.left= verificaBalanceamento(n.left);
        }
        calculaBalanceamento(n);
        if (n.balance >= 2 || n.balance <= -2) {
            if (Math.abs(n.balance) >= 2) {
                if (n.balance > 0) {
                    if (n.right != null && n.right.balance >= 0) {
                        return rotacaoSimplesDireita(n);
                    } else {
                        return rotacaoDuplaDireita(n);
                    }
                } else {
                    if (n.left != null && n.left.balance <= 0) {
                        return rotacaoSimplesEsquerda(n);
                    } else {
                        return rotacaoDuplaEsquerda(n);
                    }
                }
            }
        }            
        return n;
    }

    public Node rotacaoSimplesDireita(Node n) {
        Node filhoDireito = n.right;
        Node filhoDoFilhoDireito = null;
        
        if (n.right != null) {
            if (n.right.left != null) {
                filhoDoFilhoDireito = n.right.left;
                filhoDoFilhoDireito.father = n;
            }
            
            filhoDireito.father = n.father;
            if (n.father != null) {
                if (n == n.father.left) {
                    n.father.left = filhoDireito;
                } else {
                    n.father.right = filhoDireito;
                }
            } else {
                root = filhoDireito;
            }
            
            filhoDireito.left = n;
            n.father = filhoDireito;
            n.right = filhoDoFilhoDireito;
    
            return filhoDireito;
        }
        return n;
    }
    
    public Node rotacaoSimplesEsquerda(Node n) {
        Node filhoEsquerdo = n.left;
        Node filhoDoFilhoEsquerdo = null;
    
        if (n.left != null) {
            if (n.left.right != null) {
                filhoDoFilhoEsquerdo = n.left.right;
                filhoDoFilhoEsquerdo.father = n;
            }
    
            filhoEsquerdo.father = n.father;
            if (n.father != null) {
                if (n == n.father.left) {
                    n.father.left = filhoEsquerdo;
                } else {
                    n.father.right = filhoEsquerdo;
                }
            } else {
                root = filhoEsquerdo;
            }
    
            filhoEsquerdo.right = n;
            n.father = filhoEsquerdo;
            n.left = filhoDoFilhoEsquerdo;
    
            return filhoEsquerdo;
        }
        return n;
    }
    
    public Node rotacaoDuplaDireita(Node n) {
        Node filhoDireito = n.right;
        Node filhoDoFIlhoDireito = n.right.left;
        Node novo = filhoDoFIlhoDireito.right;

        filhoDireito.left = novo; 
        filhoDoFIlhoDireito.right = filhoDireito;
        n.right = filhoDoFIlhoDireito;
        return rotacaoSimplesDireita(n);
    }

    public Node rotacaoDuplaEsquerda(Node n) {
        Node filhoEsquerdo = n.left;
        Node filhoDoFIlhoEsquerdo = n.left.right;
        Node novo = filhoDoFIlhoEsquerdo.left;

        filhoEsquerdo.right = novo; 
        filhoDoFIlhoEsquerdo.left = filhoEsquerdo;
        n.left = filhoDoFIlhoEsquerdo;
        return rotacaoSimplesEsquerda(n);
    }

    /**
     * Método que faz uma cópia da árvore.
     * @return uma cópia da árvore.
     */
    public AVLTree clone() {
        AVLTree c = new AVLTree();
        clone(root,c);
        return c;
    }
    private void clone(Node n, AVLTree c) {
        if (n!=null) {
            c.add(n.element); // visita a raiz
            clone(n.left, c); // percorre subarvore da esq
            clone(n.right, c); // percorre subarvore da dir
        }
    } 

    /**
     * Método que faz o caminhamento pré-fixado na árvore.
     * @return
     */
    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            res.add(n.element); //Visita o nodo
            positionsPreAux(n.left, res); //Visita a subárvore da esquerda
            positionsPreAux(n.right, res); //Visita a subárvore da direita
        }
    }

    /**
     * Método que faz o caminhamento central na árvore.
     * @return
     */
    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsCentralAux(n.left, res); //Visita a subárvore da esquerda
            res.add(n.element); //Visita o nodo
            positionsCentralAux(n.right, res); //Visita a subárvore da direita
        }
    }

    /**
     * Método que faz o caminhamento pós-fixado na árvore.
     * @return
     */
    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsPosAux(n.left, res); //Visita a subárvore da esquerda
            positionsPosAux(n.right, res); //Visita a subárvore da direita
            res.add(n.element); //Visita o nodo
        }
    }

    /**
     * Método que faz o caminhamento em largura na árvore.
     * @return
     */
    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        Node atual = null;
        LinkedListOfInteger res = new LinkedListOfInteger();
        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                if (atual.left != null) {
                    fila.enqueue(atual.left);
                }
                if (atual.right != null) {
                    fila.enqueue(atual.right);
                }
                res.add(atual.element);
            }
        }
        return res;
    }

    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" " + "\n");
        }
        //"[label = " << nodo->hDir << "]" <<endl;
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline 
    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    } 
}