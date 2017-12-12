package util;

import vo.StockInfo;
import java.util.*;
import java.io.*;

public class FileHandlerImpl implements FileHandler {

    /**
     * This func gets stock information from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return the Stock information array from the interfaces,or NULL
     */
    @Override
    public StockInfo[] getStockInfoFromFile(String filePath) throws FileNotFoundException {
        //TODO: write your code here
        try{
            Scanner getIn=new Scanner(new FileInputStream(filePath));
            StockInfo[] stocks=new StockInfo[60];
            getIn.nextLine();
            int i=0;
            while(getIn.hasNext()){
                stocks[i]=new StockInfo(getIn.nextLine());
                i++;
            }
            return stocks;

        }catch (FileNotFoundException fileNotFoundExceptionPara){
            fileNotFoundExceptionPara.printStackTrace();
            return null;
        }

    }





}
