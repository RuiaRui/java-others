package pack;

import vo.StockInfo;

import java.util.*;

public interface FileHandler{
    //get the stock information from the file.
    List<StockInfo> getStockInfoFromFile(String filepath) throws Exception;

    //set the stock information
    void setStockInfoToFile(String filepath,List<StockInfo> stocks) throws Exception;

}