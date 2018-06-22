package myzx.learn.word.domain;

public class Word {
    private int ID;
    private String Word;
    private String meaning;
    private String lx;

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "Word{" +
                "ID=" + ID +
                ", Word='" + Word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", lx='" + lx + '\'' +
                '}';
    }
}
