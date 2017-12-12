package pack;

import vo.StockInfo;

import java.util.*;
import java.io.*;

/**
 * Created by ISSUSER on 2017/10/12.
 */
public class StockInfoHandler implements FileHandler {

    public List<StockInfo> getStockInfoFromFile(String filepath) throws Exception
    {
        Scanner _in = new Scanner(new FileInputStream(filepath));
        List<String> li = new ArrayList<>();                   //用于存储读取的每一行数据
        List<StockInfo> list = new ArrayList<>();               //用于存储最后的StockInfo对象
        _in.nextLine();                              //跳过第一行
        while (_in.hasNext()){
            li.add(_in.nextLine());                 //每读取一行，添加到String列表中
        }
        _in.close();

        for(int i=0;i<li.size();i++){
            StockInfo tempStock = new StockInfo();
            String tempLine = li.get(i);                //获取第i行数据
            String[] n = tempLine.split("\t");      //将第i行数据以 \t 为分隔符切分，n.length()==8
            SetStockInfoByString(tempStock,n);              //用n设置StockInfo对象的值
            list.add(tempStock);                        //将新生成的stock添加到list列表中
        }
        return list;
    }

    public void setStockInfoToFile(String filepath,List<StockInfo> stocks) throws Exception
    {
        File f = new File(filepath);
        PrintWriter _out = new PrintWriter(new FileWriter(f));
        for(int i=0;i<stocks.size();i++){
            _out.println(ToString(stocks.get(i)));      //获取列表中第i个元素对应的字符串并打印
        }
        _out.close();
    }

    /*用String数组设置StockInfo对象的值*/
    public void SetStockInfoByString(StockInfo sif, String[] contentlist){
        sif.setID(contentlist[0]);
        sif.setTITLE(contentlist[1]);
        sif.setAUTHOR(contentlist[2]);
        sif.setDATE(contentlist[3]);
        sif.setLASTUPDATE(contentlist[4]);
        sif.setCONTENT(contentlist[5]);
        sif.setANSWERAUTHOR(contentlist[6]);
        sif.setANSWER(contentlist[7]);
    }

    /*将stock转换为字符串*/
    public String ToString(StockInfo stock){
        String s = stock.getID()+"\t"+
                stock.getTITLE()+"\t"+
                stock.getAUTHOR()+"\t"+
                stock.getDATE()+"\t"+
                stock.getLASTUPDATE()+"\t"+
                stock.getCONTENT()+"\t"+
                stock.getANSWERAUTHOR()+"\t"+
                stock.getANSWER();
        return s;
    }
}
