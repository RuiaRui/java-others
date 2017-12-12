package pack;

import vo.StockInfo;

import java.util.*;

public interface StockSort{
    /**
     *
     * @param info stock information
     * @return sorted information
     */
    List<StockInfo> BubbleSort(List<StockInfo> info);

    /**
     *
     * @param info stock information
     * @param order true:ascending,false:disascending
     * @return sorted stock
     */
    List<StockInfo> BubbleSort(List<StockInfo> info,boolean order);

    /**
     * SelectionSort unstable
     *
     * @param stocks stock information
     * @return sorted stocks
     */
    List<StockInfo> SelectionSort(List<StockInfo> stocks);

    /**
     * SelectionSort unstable
     *
     * @param stocks stocks information
     * @param order  true:ascending,false:disascending
     * @return sorted stocks
     */
    List<StockInfo> SelectionSort(List<StockInfo> stocks, boolean order);

    /**
     * InsertionSort  stable
     *
     * @param stocks stocks information
     * @return sorted stocks
     */
    List<StockInfo> InsertionSort(List<StockInfo> stocks);

    /**
     * InsertionSort  stable
     *
     * @param stocks stocks information
     * @param order  true:ascending,false:disascending
     * @return sorted stocks
     */
    List<StockInfo> InsertionSort(List<StockInfo> stocks, boolean order);

    /**
     * MergeSort stable
     *
     * @param stocks stocks information
     * @return
     */
    List<StockInfo> MergeSort(List<StockInfo> stocks);

    /**
     * MergeSort stable
     *
     * @param stocks
     * @param order
     * @return sorted stocks
     */
    List<StockInfo> MergeSort(List<StockInfo> stocks, boolean order);

    /**
     * QuickSort
     * unstable
     * @param stocks stocks information
     * @return sorted stocks
     */
    List<StockInfo> QuickSort(List<StockInfo> stocks);

    /**
     * QuickSort
     * unstable
     * @param stocks stocks information
     * @param order true:ascending,false:disascending
     * @return
     */
    List<StockInfo> QuickSort(List<StockInfo> stocks, boolean order);

}