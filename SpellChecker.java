import java.util.*;
import java.io.*;

public class SpellChecker implements SpellCheckerInterface{

    private HashSet<String> dic = new HashSet<String>();

    public SpellChecker(String filename){
        try{
            File dictionary = new File(filename);
            Scanner scan = new Scanner(dictionary);

            while(scan.hasNextLine()){
                String word = scan.nextLine().replaceAll("\\p{Punct}", "");
                word = word.toLowerCase();
                dic.add(word);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("your file is no bueno");
        }
    }

    public List<String> getIncorrectWords(String filename){
        ArrayList<String> wrong = new ArrayList<String>();

        try{
            File unchecked = new File(filename);
            Scanner scan = new Scanner(unchecked);

            while (scan.hasNextLine()){
                //System.out.println("reached has next line scan");
                String line = scan.nextLine().replaceAll("\\p{Punct}", "");
                line = line.toLowerCase();
                //System.out.println(line);
                String [] arr = line.split(" ");

                for(int i = 0; i < arr.length; i++){
                    String word = arr[i].replaceAll("[^A-Za-z0-9]", "");

                    //System.out.println("word: " + word);
                    if(!dic.contains(word) && !word.equals("")){
                        wrong.add(word);
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("your file is no bueno");
        }
        return wrong;
    }

    public Set<String> getSuggestions(String word){
        HashSet<String> suggest = new HashSet<String>();

        //add char, using ascII #s 
        for(int i = 97; i < 123; i++){
            for(int j = 0; j < word.length()+1; j++){
                String str = "";
                for(int k = 0; k < word.length() + 1; k++){
                    //insert new letter
                    if(j == k){
                        str = str + String.valueOf((char) i);
                    }
                    //spell rest of the word
                    if(k < word.length()){
                        str = str + word.charAt(k);
                    }
                }
                if(dic.contains(str)){
                    suggest.add(str);
                }
            }
        }

        //remove char 
        for(int i = 0; i < word.length(); i++){
            String str = word.substring(0, i) + word.substring(i+1);
            if(dic.contains(str)){
                suggest.add(str);
            }
        }

        //swap char
        for(int i = 1; i < word.length(); i++){
            String str = "";
            for(int j = 0; j < i-1; j++){
                str = str + String.valueOf(word.charAt(j));
            }
            String letter = String.valueOf(word.charAt(i-1));
            String swap = String.valueOf(word.charAt(i));

            str = str + swap + letter;

            //rest of word
            for(int k = i + 1; k < word.length(); k++){
                str = str + String.valueOf(word.charAt(k));
            }

            if(dic.contains(str)){
                suggest.add(str);
            }
        }

        return suggest;
    }
}