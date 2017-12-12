package vo;


import java.util.ArrayList;
import java.util.List;

public class UserInterest {
    //user编号
    private int userId;
    //已阅读的ID号
    private List<Integer> readId;

    public UserInterest(int userId, String readString) {
        this.userId = userId;
        ArrayList<Integer> readId = new ArrayList<>();
        for (int i = 0; i < readString.length(); i++) {
            if (readString.charAt(i) == '1')
                readId.add(i + 1);
        }
        this.readId = readId;
    }

    public int getUserId() {
        return userId;
    }

    public List<Integer> getReadId() {
        return readId;
    }
}
