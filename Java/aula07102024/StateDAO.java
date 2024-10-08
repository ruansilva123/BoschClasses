import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StateDAO {

    public boolean createState(State state) {
        String sql = "INSERT INTO tbestado (sigla, descricao) VALUES (?,?)";

        try (Connection connection = ConexaoDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, state.getSigla());
            stmt.setString(2, state.getDescricao());

            stmt.executeUpdate();
            stmt.close();

            return true;
        } catch (SQLException error) {
            System.out.println("Error to create state: " + error.toString());
            return false;
        }
    }

    public List<State> listStates() {
        List<State> states = new ArrayList<>();
        String sql = "SELECT * FROM tbestado";

        try {
            Connection connection = ConexaoDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                State state = new State();
                state.setSigla(rs.getString("sigla"));
                state.setDescicao(rs.getString("descricao"));
                states.add(state);
            }

        } catch (SQLException error) {
            System.out.println("Error to create state: " + error.toString());
            return null;
        }
        return states;
    }

    public boolean updateState(State state){
        String sql = "UPDATE tbestado SET descricao = ? WHERE sigla = ?";

        try{
            Connection connection = ConexaoDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, state.getDescricao());
            stmt.setString(2, state.getSigla());
            stmt.executeUpdate();
            connection.close();
            stmt.close();
            return true;
        }catch(SQLException error){
            System.out.println("Error to update state: "+error.toString());
        }
        return false;
    }

    public boolean deleteState(String sigla){
        String sql = "DELETE FROM tbestado WHERE sigla = ?";

        try{
            Connection connection = ConexaoDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sigla);
            stmt.executeUpdate();
            connection.close();
            stmt.close();
            return true;
        }catch(SQLException error){
            System.out.println("Error to delete state: "+error.toString());
        }
        return false;
    }
}
