import Model.Answer;
import Model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionController extends DbConnection {
    private PreparedStatement pst;
    private Connection conn = null;
    
    public QuestionController() {
        try {
            conn = super.getConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Question getQuestion(int row) throws SQLException{
        ArrayList<Answer> list = new ArrayList();
        Question que = new Question();
            
        row = row - 1;
        String sql = "SELECT q.id as QuestionId,\n" +
                    "	q.qdetails AS Question,\n" +
                    "    a.id AS AnswerId,\n" +
                    "    a.answer AS Answer \n" +
                    "FROM (SELECT * FROM questions\n" +
                    "ORDER BY id\n" +
                    "LIMIT ?, 1) q\n" +
                    "INNER JOIN answers a\n" +
                    "ON q.id = a.qid";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, row);
            
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Answer ans = new Answer();
                
                que.setId(res.getInt("QuestionId"));
                que.setQuestion(res.getString("Question"));
                
                ans.setId(res.getInt("AnswerId"));
                ans.setAnswer(res.getString("Answer"));
                
                list.add(ans);
            }
            
            que.setAnswer(list);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        
        return que;
    }
    
    public int getTotalQuestion() throws SQLException {
        String sql = "SELECT COUNT(*) AS Total FROM questions";
        int count = 0;

        try {
            pst = conn.prepareStatement(sql);

            ResultSet res = pst.executeQuery();
            while (res.next()) {
                count = res.getInt("Total");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return count;
    }
}
