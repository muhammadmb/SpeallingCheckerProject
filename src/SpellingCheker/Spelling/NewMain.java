package SpellingCheker.Spelling;

public class NewMain {

   
    public static void main(String[] args) {
      
        DictionaryLL o = new DictionaryLL();
        o.addWords("yy");
        Boolean e = o.isWord("yy");
        System.out.println(e);
    }
    
}
