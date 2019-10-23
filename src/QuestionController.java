
import Model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nishan Dhungana
 */
public class QuestionController extends DbConnection {
    private PreparedStatement pst;
    private Connection con = null;
    
    public QuestionController() {
        try {
            con = super.getConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Question getQuestions() throws SQLException{
        String sql = "";
        ResultSet res = null;
        Question question = new Question();
        
        try {
            sql = "select * from questionanswer";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
             
            while(res.next()) {
                question.setQuestion(res.getString("question"));
                question.setFirstAnswer(res.getString("first_ans"));
                question.setSecondAnswer(res.getString("sec_ans"));
                question.setThirdAnswer(res.getString("third"));
                question.setFourthAnswer(res.getString("fourth"));
                question.setCorrectAnswer(res.getInt("correct_ans"));
            }
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        
        return question;
    }
}
