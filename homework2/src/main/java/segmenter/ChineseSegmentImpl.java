package segmenter;


import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import vo.StockInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ChineseSegmentImpl implements ChineseSegment {

    /**
     * this func will get chinese word from a list of stocks. You need analysis stocks' answer and get answer word.
     * And implement this interface in the class : ChineseSegmentImpl
     * Example: 我今天特别开心 result : 我 今天 特别 开心
     *
     * @param stocks stocks info
     * @return chinese word
     * @see ChineseSegmentImpl
     */
    @Override
    public  List<String> getWordsFromInput(StockInfo[] stocks) {
        //TODO: write your code here
        String[] originalAnswer = new String[stocks.length];//原始answer
        List<String> answer= new ArrayList<>();             //处理后answer

        for (int i = 0; i < stocks.length; i++) {
            originalAnswer[i]= stocks[i].getAnswer();
        }

       return answer =cutword(originalAnswer);



    }

    @Override
    public  List<String> getContentFromInput(StockInfo[] stocks) {
        String[] originalContent = new String[stocks.length];//原始content
        List<String> content= new ArrayList<>();             //处理后content

        for (int i = 0; i < stocks.length; i++) {
            originalContent[i]= stocks[i].getAnswer();
        }

        return content =cutword(originalContent);

    }


     private static List<String> cutword(String[] words){

         List<String> results= new ArrayList<>();

         Set<String> expectedNature = new HashSet<String>() {{
             add("n");add("v");add("vd");add("vn");add("vf");
             add("vx");add("vi");add("vl");add("vg");
             add("nt");add("nz");add("nw");add("nl");
             add("ng");add("userDefine");add("wh");
         }};


         for(int  i=0;i<words.length;i++){

             Result result = ToAnalysis.parse(String.valueOf(words[i])); //分词结果的一个封装，主要是一个List<Term>的terms
             //System.out.println(result.getTerms());



             List<Term> terms = result.getTerms(); //拿到terms
             //System.out.println(terms.size());

             for(int j=0; j<terms.size(); j++) {
                 String word = terms.get(j).getName(); //拿到词
                 String natureStr = terms.get(j).getNatureStr(); //拿到词性
                 if (expectedNature.contains(natureStr)) {
                     results.add(word);
                 }//存入words
             }
         }
         return results;
     }

}
