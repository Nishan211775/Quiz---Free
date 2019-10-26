
import Model.Answer;
import Model.Question;
import Model.QuestionAnswer;
import Model.Result;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryController extends DbConnection {

    private PreparedStatement pst;
    private Connection conn;

    public HistoryController() {
        try {
            conn = super.getConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertIntoHistory(ArrayList<QuestionAnswer> questionAnswer) throws Exception {
        try {
            conn.setAutoCommit(false);
            int maxId = getMaxSets() + 1;

            for (QuestionAnswer qa : questionAnswer) {
                String getSql = "INSERT INTO userhist(questionid, "
                        + "givenanswerid, isCorrect, questionSet) "
                        + "VALUES (?,?,?,?)";

                pst = conn.prepareStatement(getSql);

                pst.setInt(1, qa.getQuestionId());
                pst.setInt(2, qa.getAnswerId());
                pst.setBoolean(3, isCorrect(qa.getQuestionId(), qa.getAnswerId()));
                pst.setInt(4, maxId);

                pst.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            conn.rollback();
            throw e;
        }
    }

    private int getMaxSets() throws SQLException {
        String sql = "SELECT MAX(questionSet) FROM userhist";
        int maxSets = 0;

        try {
            pst = conn.prepareStatement(sql);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                maxSets = res.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }

        return maxSets;
    }

    private boolean isCorrect(int questionId, int ansId) throws SQLException {
        String sql = "SELECT * FROM correctanswer where questionid = ? and answerid = ?";
        boolean isCorrect = false;

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, questionId);
            pst.setInt(2, ansId);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                isCorrect = true;
            }
            
        } catch (SQLException e) {
            throw e;
        }

        return isCorrect;
    }
    
    public int countBySet() {
        String sql = "SELECT COUNT(*) AS Total FROM userhist group by questionSet";
        int count = 0;

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                count = res.getInt("Total");
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return count;
    }
    
    public ArrayList<Result> getHistBySet(int set) throws SQLException {
        ArrayList<Result> list = new ArrayList();
            
        String sql = "SELECT \n" +
                        "	(CASE WHEN uh.isCorrect = '1' THEN 'Correct'\n" +
                        "    ELSE 'Wrong'\n" +
                        "    END) AS 'Status',\n" +
                        "	q.qdetails AS 'Question', \n" +
                        "    a.answer AS 'GivenAnswer',\n" +
                        "    aa.answer AS 'CorrectAnswer'\n" +
                        "    FROM userhist uh \n" +
                        "INNER JOIN questions q\n" +
                        "ON uh.questionid = q.id\n" +
                        "INNER JOIN answers a\n" +
                        "on uh.givenanswerid = a.id\n" +
                        "    INNER JOIN correctanswer ca\n" +
                        "    on uh.questionid = ca.questionid\n" +
                        "    	INNER join answers aa\n" +
                        "        on ca.answerid = aa.id\n" +
                        "WHERE questionset = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, set);
            
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Result ans = new Result();
                
                ans.setQuestion(res.getString("Question"));
                ans.setAnswerByUser(res.getString("GivenAnswer"));
                ans.setCorrectAnswer(res.getString("CorrectAnswer"));
                ans.setStatus(res.getString("Status"));
                
                list.add(ans);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        
        return list;
    }
    
    public ArrayList<Integer> getSets() {
        String sql = "SELECT DISTINCT questionSet FROM userhist";
        ArrayList<Integer> list = new ArrayList<>();

        try {
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet res = pst.executeQuery();
            while (res.next()) {
                int set = res.getInt("questionSet");
                list.add(set);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
}
