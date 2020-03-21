package SpellingCheker.Spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class AutoCompleteDictionaryTrie implements Dictionary,AutoComplete{

    private TrieNode root;
    private int size;

    public AutoCompleteDictionaryTrie() {
        this.root = new TrieNode();
    }
    
    @Override
    public boolean addWords(String word) {
        if(isWord(word))return false;
        TrieNode trie = root;
        char [] chars = word.toLowerCase().toCharArray();
    
        int counter = 0 ;
        while(counter < chars.length){
            Character ch = chars[counter];
            Set children = trie.getValidNextCharacters();
            
            if(!children.contains(ch)){
                trie.insert(ch);
                if(counter == chars.length - 1){
                    trie.getChild(ch).setEndsWord(true);size++;return true;
                }
            trie = trie.getChild(ch);
            if (trie.getText().equals(word) && !isWord(trie.toString())) {
                trie.setEndsWord(true);size++;return true;
                    
                }
            }
        }
    return false;
    }

    @Override
    public boolean isWord(String S) {
        TrieNode trie = findNode(S);
        if (trie == null) {return false;}
        return findNode(S).endsWord();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<String> predictCompletions(String prefix, int numCompletions) {
        if(numCompletions == 0) return new ArrayList<>();
        
        TrieNode trie = findNode(prefix);
        if(trie == null) return new ArrayList<>();
        
        List completions = new ArrayList();
        LinkedList <TrieNode> queue = new LinkedList<>();
        queue.addLast(trie);
        
        while(!queue.isEmpty() && completions.size() != numCompletions){
        
            TrieNode removed = queue.removeFirst();
            
            if (removed.endsWord()) {
                completions.add(removed.getText());
            }
            for (Character c : removed.getValidNextCharacters()) {
                queue.addLast(removed.getChild(c));
            }
        }return completions;
        
    }
    
    private TrieNode findNode(String s){
    
        if (s.isEmpty())
            return root;
        TrieNode currentNode = root;
        for (Character c : s.toLowerCase().toCharArray()) {
            if(currentNode.getChild(c) == null)
                return null;
        }
        return currentNode;
    }
    
    // for debug
    
    public void printTree()
 	{
 		printNode(root);
 	}
    
    public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
}
