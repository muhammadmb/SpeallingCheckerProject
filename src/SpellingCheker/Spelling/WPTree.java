package SpellingCheker.Spelling;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

// Return a path from word1 to word2 through dictionary words
public class WPTree implements WordPath{

    private WPTreeNode root;
    private NearbyWords NW;

    public WPTree() {
        this.root = null;
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "data/dict.txt");
        this.NW = new NearbyWords(d);
}
    //This constructor will be used by the grader code
    public WPTree(NearbyWords NW) {
        this.root = null;
        this.NW = NW;
    }

    @Override
    public List<String> findPath(String word1, String word2) {

        LinkedList <WPTreeNode> queue = new LinkedList<>(); // to explore
        HashSet <String> visited = new HashSet<>();
        root = new WPTreeNode(word1, null);
        
        queue.add(root);
        visited.add(word1);
            while (!queue.isEmpty() && !root.getWord().equals(word2)) {            
            WPTreeNode curr = queue.removeFirst();
                for (String s: NW.distanceOne(curr.getWord(), true)){
                    if(!visited.contains(s)){
                        curr.addChild(s);
                        visited.add(s);
                        queue.addLast(new WPTreeNode(s,curr));
                        if(s.equals(word2)){
                            return queue.getLast().buildPathToRoot();
                        }
                    }
                }
        }
        return null;
    }
    
    // Method to print a list of WPTreeNodes (useful for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
}
    
    class WPTreeNode{
    
    
        
        private String word;
        private List <WPTreeNode> children;
        private WPTreeNode parent;

        public WPTreeNode(String w,WPTreeNode p) {
            this.word = w;
            this.children = new LinkedList<WPTreeNode>();
            this.parent = p;
        }
    
        public WPTreeNode addChild(String w){
            WPTreeNode child = new WPTreeNode(w, this);
            this.children.add(child);
            return child;
        }

        public List<WPTreeNode> getChildren() {
            return this.children;
        }
        
        public List <String> buildPathToRoot(){
        
            WPTreeNode curr = this;
            List <String> path = new LinkedList<>();
                while(curr != null){
                    path.add(0, curr.getWord());
                    curr = curr.parent;
                }
            return path;
        }

        public String getWord() {
            return word;
        }

    @Override
    public String toString() {
        String ret = String.format("word : %s and it's parent is : ", word);
        
        if(parent == null){
            ret += "null.\n";
        }else{
            ret += this.parent.getWord() + "\n";
        }
        ret += "[";
            for(WPTreeNode C : children){
                ret += C + ", ";
            }
        ret += "]\n";
        return ret;
    }
    
    //testing
    public static void main(String[] args) {
        
        WPTree a = new WPTree();
        
        System.out.println(a.findPath("love", "view"));
        
    }
        
}
