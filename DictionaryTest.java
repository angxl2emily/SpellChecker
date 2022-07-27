import java.util.*;
public class DictionaryTest {

    public static void main(String[] args) {

        SpellChecker m = new SpellChecker("words.txt");
        List<String> n = m.getIncorrectWords("test.txt");
        for(int i = 0; i < n.size(); i++) {
            //System.out.println("reached traversing through incorrects");
            System.out.println("|" + n.get(i) + "|");
        }

        Object[] x = m.getSuggestions("recedig").toArray();
        for(int i = 0; i < x.length; i++) {
            System.out.println((String)x[i]);
        }
    }
}
