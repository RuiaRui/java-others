package vo;

import java.util.*;
import java.lang.String;

/**
 * Created by ISSUSER on 2017/10/11.
 */
public class StockInfo {
    private String ID;
    private String TITLE;
    private String AUTHOR;
    private String DATE;
    private String LASTUPDATE;
    private String CONTENT;
    private String ANSWERAUTHOR;
    private String ANSWER;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getLASTUPDATE() {
        return LASTUPDATE;
    }

    public void setLASTUPDATE(String LASTUPDATE) {
        this.LASTUPDATE = LASTUPDATE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getANSWERAUTHOR() {
        return ANSWERAUTHOR;
    }

    public void setANSWERAUTHOR(String ANSWERAUTHOR) {
        this.ANSWERAUTHOR = ANSWERAUTHOR;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    /*将一个StockInfo对象赋值给当前StockInfo对象*/
    public void setStockInfoByStockInfo(StockInfo s){
        this.setID(s.getID());
        this.setTITLE(s.getTITLE());
        this.setAUTHOR(s.getAUTHOR());
        this.setDATE(s.getDATE());
        this.setLASTUPDATE(s.getLASTUPDATE());
        this.setCONTENT(s.getCONTENT());
        this.setANSWERAUTHOR(s.getANSWERAUTHOR());
        this.setANSWER(s.getANSWER());
    }

}
