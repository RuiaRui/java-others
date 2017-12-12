package util;

import com.sun.javafx.iio.ImageFormatDescription;
import vo.StockInfo;
import java.util.*;


public class StockSorterImpl implements StockSorter {
    /**
     * Accepting series of stock info, sorting them ascending according to their comment length.
     * List.sort() or Arrays.sort() are not allowed.
     * You have to write your own algorithms,Bubble sort、quick sort、merge sort、select sort,etc.
     *
     * @param  info information
     * @return sorted stock
     */

    @Override
    public  StockInfo[] Sort( StockInfo[] info) {

        for (int i = 0; i < info.length - 1; i++) {
            for (int j = 0; j < info.length - i - 1; j++) {
                String s1 = info[j].getAnswer();        //获取第j个stock的answer
                String s2 = info[j + 1].getAnswer();     //获取第j+1个stock的answer
                if (Compare(s1, s2))                     //若第j个answer的长度大于第j+1个，则交换两个stock
                {
                    Swap(info[j], info[j + 1]);
                }
            }
        }
        return info;
    }

    /**
     * Accepting series of stock info, sorting them ascending, descending order.
     *
     * @param info  stock information
     * @param order true:ascending,false:descending
     * @return sorted stock
     */
    @Override
    public  StockInfo[] Sort( StockInfo[]  info, boolean order) {
        //TODO: write your code here
        for (int i = 0; i < info.length - 1; i++) {
            for (int j = 0; j < info.length - i - 1; j++) {
                String s3 = info[j].getAnswer();
                String s4 = info[j + 1].getAnswer();
                if (order)                  //升序排列
                {
                    if (Compare(s3, s4))
                        Swap(info[j], info[j + 1]);
                } else                      //降序排列
                {
                    if (!Compare(s3, s4))
                        Swap(info[j], info[j + 1]);
                }
            }
        }
        return info;
    }

    public boolean Compare(String s1,String s2){
        if(s1.length()>s2.length()){
            return true;
        }else{
            return false;
        }
    }

    public void Swap(StockInfo stock1,StockInfo stock2){
        StockInfo temp=new StockInfo();
        temp.setStockInfoByStockInfo(stock1);
        stock1.setStockInfoByStockInfo(stock2);
        stock2.setStockInfoByStockInfo(temp);
    }
}
