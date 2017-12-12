package tf_idf;

import util.StockSorter;
import util.StockSorterImpl;
import vo.StockInfo;
import segmenter.*;

import javafx.util.Pair;
import java.util.*;

public class TF_IDFImpl implements TF_IDF {

    private static ChineseSegmentImpl segment = new ChineseSegmentImpl();
    private static StockSorter stockSorter = new StockSorterImpl();
    /**
     * this func you need to calculate words frequency , and sort by frequency.
     * you maybe need to use the sorter written by yourself in example 1
     *
     * @param words the word after segment
     * @return a sorted words
     * @see StockSorter
     */
    @Override
    public Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos) {
        List<List<String>> allWords = GetAllAnswerWords(stockInfos);

        HashMap<String, Double> TF = TFCalculator(words);
        HashMap<String, Double> IDF = IDFCalculator(TF, allWords);
        Pair<String, Double>[] TF_IDF = TF_IDFCalculator(TF, IDF);
        //排序
        TF_IDF = Sort(TF_IDF);

        return TF_IDF;
    }


    @Override
    public Pair<String, Double>[] getContentResult(List<String> words, StockInfo[] stockInfos) {
        List<List<String>> allWords = GetAllContentWords(stockInfos);

        HashMap<String, Double> TF = TFCalculator(words);
        HashMap<String, Double> IDF = IDFCalculator(TF, allWords);
        Pair<String, Double>[] TF_IDF = TF_IDFCalculator(TF, IDF);
        //排序
        TF_IDF = Sort(TF_IDF);

        return TF_IDF;
    }


    /**
     * get all information from the List of answer
     *
     * @param stockInfos
     * @return
     */
    private List<List<String>> GetAllAnswerWords(StockInfo[] stockInfos) {
        ArrayList<List<String>> allWords = new ArrayList<>();
        for (StockInfo s : stockInfos) {
            allWords.add(segment.getWordsFromInput(new StockInfo[]{s}));
        }
        return allWords;
    }


    /**
     * get all information from the List of content
     *
     * @param stockInfos
     * @return
     */
    private List<List<String>> GetAllContentWords(StockInfo[] stockInfos) {
        ArrayList<List<String>> allWords = new ArrayList<>();
        for (StockInfo s : stockInfos) {
            allWords.add(segment.getContentFromInput(new StockInfo[]{s}));
        }
        return allWords;
    }


    /**
     * calculate the TF value and return an hash map with key represented the words and value for the TF value
     *
     * @param words : List of all the words in one content
     * @return TF tf hash map
     */
    private static HashMap<String, Double> TFCalculator(List<String> words) {
        HashMap<String, Integer> count = new HashMap<>();
        HashMap<String, Double> TF = new HashMap<>();

        for (String word : words) {
            if (count.get(word) == null) {
                count.put(word, 1);
                // System.out.println(word);  //for debug
            } else {
                count.put(word, count.get(word) + 1);
                // System.out.println(word.toString());   //for debug
            }
        }

        Iterator iterator = count.entrySet().iterator(); //iterator for that get from TF
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            TF.put(entry.getKey().toString(), Double.valueOf(entry.getValue().toString()) / words.size());
        }
        return TF;
    }


    /**
     * calculate the IDF value and return an hash map with key represented the words and value for the IDF value
     *
     * @param tf       tf hash map
     * @param allWords all the words (content or answer)for one stock
     * @return IDF hash map
     */
    private HashMap<String, Double> IDFCalculator(HashMap<String, Double> tf, List<List<String>> allWords) {
        HashMap<String, Double> IDF = new HashMap<>();
        Iterator iter = tf.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String word = entry.getKey().toString();
            int counts = 0;
            for (List<String> l : allWords) {
                if (l.contains(word)) {
                    counts++;
                }
            }
            Double value = Math.log((double) allWords.size() / (counts + 1));
            IDF.put(word, value);
        }
        return IDF;
    }

    /**
     * calculate the IDF value and return a pair matrix with key represented the words and value for the TF-IDF value
     *
     * @param tf
     * @param idf
     * @return TF_IDF
     */
    private Pair<String, Double>[] TF_IDFCalculator(HashMap<String, Double> tf, HashMap<String, Double> idf) {
        ArrayList<Pair<String, Double>> arrayList = new ArrayList<>();
        Iterator iter1 = tf.entrySet().iterator();
        Iterator iter2 = idf.entrySet().iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            Map.Entry entry1 = (Map.Entry) iter1.next();
            Map.Entry entry2 = (Map.Entry) iter2.next();
            Pair<String, Double> pair =
                    new Pair<>(entry1.getKey().toString(), (Double) entry1.getValue() * (Double) entry2.getValue());
            arrayList.add(pair);
        }

        Pair<String, Double>[] TF_IDF = (Pair<String, Double>[]) new Pair[arrayList.size()];
        arrayList.toArray(TF_IDF);

        return TF_IDF;
    }

    public static Pair<String,Double>[] Sort(Pair<String,Double>[] pairs){
        for (int i = 0; i < pairs.length - 1; i++) {
            Pair<String,Double> max =pairs[i];
            int t = i;
            for (int j = i + 1; j < pairs.length; j++) {

                {
                    if (pairs[j].getValue() > max.getValue()) {
                        max = pairs[j];
                        t = j;
                    }
                }
            }
            Pair<String,Double> temp = pairs[i];
            pairs[i] = pairs[t];
            pairs[t] = temp;
        }

        return pairs;
    }

}
