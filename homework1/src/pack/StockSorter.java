package pack;

import vo.StockInfo;

import java.util.*;

/**
 * Created by ISSUSER on 2017/10/12.
 */
public class StockSorter implements StockSort {

    public List<StockInfo> BubbleSort(List<StockInfo> info) {
        for (int i = 0; i < info.size() - 1; i++) {
            for (int j = 0; j < info.size() - i - 1; j++) {
                String s1 = info.get(j).getANSWER();        //获取第j个stock的answer
                String s2 = info.get(j + 1).getANSWER();     //获取第j+1个stock的answer
                if (StrCompare(s1, s2))                     //若第j个answer的长度大于第j+1个，则交换两个stock
                    StoSwap(info.get(j), info.get(j + 1));
            }
        }
        return info;
    }

    public List<StockInfo> BubbleSort(List<StockInfo> info, boolean order) {
        for (int i = 0; i < info.size() - 1; i++) {
            for (int j = 0; j < info.size() - i - 1; j++) {
                String s3 = info.get(j).getANSWER();
                String s4 = info.get(j + 1).getANSWER();
                if (order)                  //升序排列
                {
                    if (StrCompare(s3, s4))
                        StoSwap(info.get(j), info.get(j + 1));
                } else                      //降序排列
                {
                    if (!StrCompare(s3, s4))
                        StoSwap(info.get(j), info.get(j + 1));
                }
            }
        }
        return info;
    }

    public List<StockInfo> SelectionSort(List<StockInfo> stocks) {
        for (int i = 0; i < stocks.size() - 1; i++) {
            StockInfo min = new StockInfo();
            min.setStockInfoByStockInfo(stocks.get(i));            //假定待排序列中第一个元素为最小元素
            int index = i;                              //记录下最小元素的索引值
            for (int j = i + 1; j < stocks.size(); j++)         //每次扫描待排序列stocks.size()-i-1次
            {
                if (stocks.get(j).getANSWER().length() < min.getANSWER().length()) {
                    min.setStockInfoByStockInfo(stocks.get(j));    //若dij个stock的answer长度小于最小值，则将第j个元素作为最小元素
                    index = j;                  //记录下第j个元素的索引值
                }
            }
            StoSwap(stocks.get(i), stocks.get(index));      //最小元素与待排序列的最小元素交换
        }
        return stocks;
    }

    public List<StockInfo> SelectionSort(List<StockInfo> stocks, boolean order) {
        for (int i = 0; i < stocks.size() - 1; i++) {
            StockInfo min = new StockInfo();
            min.setStockInfoByStockInfo(stocks.get(i));
            StockInfo max = new StockInfo();
            max.setStockInfoByStockInfo(stocks.get(i));
            int index = i;
            for (int j = i + 1; j < stocks.size(); j++) {
                if (order)              //升序排列
                {
                    if (stocks.get(j).getANSWER().length() < min.getANSWER().length()) {
                        min.setStockInfoByStockInfo(stocks.get(j));
                        index = j;
                    }
                } else                  //降序排列
                {
                    if (stocks.get(j).getANSWER().length() > max.getANSWER().length()) {
                        max.setStockInfoByStockInfo(stocks.get(j));
                        index = j;
                    }
                }
            }
            StoSwap(stocks.get(i), stocks.get(index));
        }
        return stocks;
    }

    public List<StockInfo> InsertionSort(List<StockInfo> stocks) {
        for (int i = 1; i < stocks.size(); i++) {
            int j = i;
            StockInfo tmp = new StockInfo();
            tmp.setStockInfoByStockInfo(stocks.get(i));            //待插入元素存到临时变量
            while (j > 0 && StrCompare(stocks.get(j - 1).getANSWER(), tmp.getANSWER()))     //从后向前查找插入位置
            {
                stocks.get(j).setStockInfoByStockInfo(stocks.get(j - 1));      //第j-1个元素后移一位
                j--;              //j指针前移
            }
            stocks.get(j).setStockInfoByStockInfo(tmp);            //将待插入元素存入找到的插入位置
        }
        return stocks;
    }

    public List<StockInfo> InsertionSort(List<StockInfo> stocks, boolean order) {
        for (int i = 1; i < stocks.size(); i++) {
            int j = i;
            StockInfo tmp = new StockInfo();
            tmp.setStockInfoByStockInfo(stocks.get(i));
            if (order)            //ascending
            {
                while (j > 0 && StrCompare(stocks.get(j - 1).getANSWER(), tmp.getANSWER())) {
                    stocks.get(j).setStockInfoByStockInfo(stocks.get(j - 1));
                    j--;
                }
                stocks.get(j).setStockInfoByStockInfo(tmp);
            } else                //disascending
            {
                while (j > 0 && StrCompare(tmp.getANSWER(), stocks.get(j - 1).getANSWER())) {
                    stocks.get(j).setStockInfoByStockInfo(stocks.get(j - 1));
                    j--;
                }
                stocks.get(j).setStockInfoByStockInfo(tmp);
            }
        }
        return stocks;
    }

    public List<StockInfo> MergeSort(List<StockInfo> stocks) {
        int i1, i2;          //i1,i2是子序列1的上下界
        int j1, j2;          //j1,j2是子序列2的上下界
        int size = 1;       //子序列中元素个数，默认为1

        while (size < stocks.size()) {
            i1 = 0;
            while ((i1 + size) < stocks.size())      //若i1序列的长度没有超过列表长度
            {
                j1 = i1 + size;
                i2 = j1 - 1;
                if ((j1 + size - 1) > (stocks.size() - 1))     //若子序列二的长度超出列表长度
                    j2 = stocks.size() - 1;
                else
                    j2 = j1 + size - 1;
                merge(stocks, i1, i2, j1, j2);
                i1 = j2 + 1;
            }
            size *= 2;
        }
        return stocks;
    }

    public List<StockInfo> MergeSort(List<StockInfo> stocks, boolean order) {
        int i1, i2;          //i1,i2是子序列1的上下界
        int j1, j2;          //j1,j2是子序列2的上下界
        int size = 1;       //子序列中元素个数，默认为1

        while (size < stocks.size()) {
            i1 = 0;
            while ((i1 + size) < stocks.size())      //若i1序列的长度没有超过列表长度
            {
                j1 = i1 + size;
                i2 = j1 - 1;
                if ((j1 + size - 1) > (stocks.size() - 1))     //若子序列二的长度超出列表长度
                    j2 = stocks.size() - 1;
                else
                    j2 = j1 + size - 1;
                merge(stocks, i1, i2, j1, j2, order);
                i1 = j2 + 1;
            }
            size *= 2;
        }
        return stocks;
    }

    public List<StockInfo> QuickSort(List<StockInfo> stocks) {
        return QSort(stocks, 0, stocks.size() - 1);
    }

    public List<StockInfo> QuickSort(List<StockInfo> stocks, boolean order) {
        return QSort(stocks, 0, stocks.size() - 1, order);
    }

    /*比较两个字符串长度，若前者大于后者返回true，否则返回false*/
    public boolean StrCompare(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return true;
        } else
            return false;
    }

    /*交换两个StockInfo对象的内容*/
    public void StoSwap(StockInfo sto1, StockInfo Sto2) {
        StockInfo tmp = new StockInfo();
        tmp.setStockInfoByStockInfo(sto1);
        sto1.setStockInfoByStockInfo(Sto2);
        Sto2.setStockInfoByStockInfo(tmp);
    }

    /*快速排序*/
    public List<StockInfo> QSort(List<StockInfo> stocks, int left, int right) {
        int i, j;            //i,j为游动指针
        if (left < right)      //待排序列中元素个数大于1
        {
            i = left;
            j = right + 1;
            do {
                //i指针从左往右找第一个>=分割元素的值
                do i++; while (StrCompare(stocks.get(i).getANSWER(), stocks.get(left).getANSWER()));
                //j指针从左往右找第一个<=分割元素的值
                do j--; while (StrCompare(stocks.get(left).getANSWER(), stocks.get(j).getANSWER()));
                if (i < j)
                    StoSwap(stocks.get(i), stocks.get(j));
            } while (i < j);
            StoSwap(stocks.get(left), stocks.get(j));
            QSort(stocks, left, j - 1);         //对低端序列进行快速排序
            QSort(stocks, j + 1, right);         //对高端序列进行快速排序
        }
        return stocks;
    }

    /*快速排序，left为待排序列的左边界，right为待排序列的右边界，order为升降序选择*/
    public List<StockInfo> QSort(List<StockInfo> stocks, int left, int right, boolean order) {
        int i, j;            //i,j为游动指针
        if (left < right)      //待排序列中元素个数大于1
        {
            i = left;
            j = right + 1;
            do {
                if (order)          //升序排列
                {
                    //i指针从左往右找第一个>=分割元素的值
                    do i++; while (i < j && StrCompare(stocks.get(left).getANSWER(), stocks.get(i).getANSWER()));
                    //j指针从左往右找第一个<=分割元素的值
                    do j--; while (i < j && StrCompare(stocks.get(j).getANSWER(), stocks.get(left).getANSWER()));
                } else              //降序排列
                {
                    //i指针从左往右找第一个<=分割元素的值
                    do i++; while (i < j && StrCompare(stocks.get(i).getANSWER(), stocks.get(left).getANSWER()));
                    //j指针从左往右找第一个>=分割元素的值
                    do j--; while (i < j && StrCompare(stocks.get(left).getANSWER(), stocks.get(j).getANSWER()));
                }
                if (i < j)
                    StoSwap(stocks.get(i), stocks.get(j));
            } while (i < j);
            StoSwap(stocks.get(left), stocks.get(j));
            QSort(stocks, left, j - 1, order);
            QSort(stocks, j + 1, right, order);
        }
        return stocks;
    }

    /*归并排序*/
    public void merge(List<StockInfo> stocks, int i1, int i2, int j1, int j2) {
        List<StockInfo> temp = new ArrayList<>(j2 - i1 + 1);
        int i = i1;         //i是第一个子序列的游动指针
        int j = j1;         //j是第二个子序列的游动指针
        int k = 0;          //k是temp的游动指针
        while (i <= i2 && j <= j2)         //若两序列都不为空，则循环
        {
            //若第i个元素的answer长度小于等于第j个元素，将第i个元素存入temp，否则存入第j个元素
            if (!StrCompare(stocks.get(i).getANSWER(), stocks.get(j).getANSWER())) {
                temp.add(k, (stocks.get(i)));
                k++;
                i++;
            } else {
                temp.add(k, stocks.get(j));
                k++;
                j++;
            }
        }
        while (i <= i2)            //若第一个子序列中还有剩余元素，直接存入temp中
        {
            temp.add(k, stocks.get(i));
            k++;
            i++;
        }
        while (j <= j2)            //若第二个子序列中还有剩余元素，直接存入temp中
        {
            temp.add(k, stocks.get(j));
            k++;
            j++;
        }
        for (int x = 0; x < k; x++)       //将temp中的元素存入stocks
        {
            stocks.set(i1, temp.get(x));
            i1++;
        }
    }

    /*归并排序，i1,i2,j1,j2分别为两个子序列的上下界，order为升降序选择*/
    public void merge(List<StockInfo> stocks, int i1, int i2, int j1, int j2, boolean order) {
        List<StockInfo> temp = new ArrayList<>(j2 - i1 + 1);
        int i = i1;         //i是第一个子序列的游动指针
        int j = j1;         //j是第二个子序列的游动指针
        int k = 0;          //k是temp的游动指针
        while (i <= i2 && j <= j2)         //若两序列都不为空，则循环
        {
            if (order)               //升序排列
            {
                //若第i个元素的answer长度小于等于第j个元素，将第i个元素存入temp，否则存入第j个元素
                if (!StrCompare(stocks.get(i).getANSWER(), stocks.get(j).getANSWER())) {
                    temp.add(k, (stocks.get(i)));
                    k++;
                    i++;
                } else {
                    temp.add(k, stocks.get(j));
                    k++;
                    j++;
                }
            } else            //降序排列
            {
                //若第i个元素的answer长度大于等于第j个元素，将第i个元素存入temp，否则存入第j个元素
                if (stocks.get(i).getANSWER().length() >= stocks.get(j).getANSWER().length()) {
                    temp.add(k, (stocks.get(i)));
                    k++;
                    i++;
                } else {
                    temp.add(k, stocks.get(j));
                    k++;
                    j++;
                }
            }
        }
        while (i <= i2)            //若第一个子序列中还有剩余元素，直接存入temp中
        {
            temp.add(k, stocks.get(i));
            k++;
            i++;
        }
        while (j <= j2)            //若第二个子序列中还有剩余元素，直接存入temp中
        {
            temp.add(k, stocks.get(j));
            k++;
            j++;
        }
        for (int x = 0; x < k; x++)       //将temp中的元素存入stocks
        {
            stocks.set(i1, temp.get(x));
            i1++;
        }
    }

}


