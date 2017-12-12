package vo;

import java.util.*;


public class StockInfo {
    private String id;
    private String title;
    private String author;
    private String date;
    private String lastUpdate;
    private String content;
    private String answerAuthor;
    private String answer;

    public StockInfo(String s) {
        String[] args = s.split("\t");

        this.id = args[0];
        this.title = args[1];
        this.author = args[2];
        this.date = args[3];
        this.content = args[4];
        this.lastUpdate = args[5];
        this.answerAuthor = args[6];
        this.answer = args[7];
    }

    public StockInfo() {

    }


    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getAnswerAuthor() {
        return answerAuthor;
    }

    public void setAnswerAuthor(String answerAuthor) {
        this.answerAuthor = answerAuthor;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    //将某一个StockInfo对象赋值给当前StockInfo对象
    public void setStockInfoByStockInfo(StockInfo s) {
        this.setID(s.getID());
        this.setTitle(s.getTitle());
        this.setAuthor(s.getAuthor());
        this.setDate(s.getDate());
        this.setLastUpdate(s.getLastUpdate());
        this.setContent(s.getContent());
        this.setAnswerAuthor(s.getAnswerAuthor());
        this.setAnswer(s.getAnswer());
    }
}
