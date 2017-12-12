package main;

import pack.StockInfoHandler;
import pack.StockSorter;
import vo.StockInfo;

import java.util.List;

/**
 * Created by ISSUSER on 2017/10/12.
 */
public class Main {
    public static void main(String[] args) throws Exception
    {
        StockInfoHandler sih = new StockInfoHandler();
        System.out.println("正在读取文件……");
        List<StockInfo> si = sih.getStockInfoFromFile("data.txt");
        System.out.println("文件读取完毕……");

        StockSorter Ss = new StockSorter();
        System.out.println("正在排序……");
        si = Ss.QuickSort(si);
        sih.setStockInfoToFile("output.txt",si);
        System.out.println("文件排序完成……");
        for(int i=0;i<si.size();i++){
            System.out.println(si.get(i).getANSWER().length()+"\n");
        }
    }
}
