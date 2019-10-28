package model;

public class WordOccurrenceContainer implements Comparable<WordOccurrenceContainer> {
    private Integer occurrences;
    private String word;
    WordOccurrenceContainer(int pOccurrence, String pWord){
        occurrences = pOccurrence;
        word = pWord;
    }
    public void incOccurrence(){
        occurrences++;
    }
    public Integer getOccurrences(){
        return occurrences;
    }
    public String getWord(){
        return word;
    }
    @Override
    public int compareTo(WordOccurrenceContainer wordOccurrenceContainer) {
        return occurrences.compareTo(wordOccurrenceContainer.getOccurrences());
    }
}
