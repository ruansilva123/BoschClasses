import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void createClient(Client client){
        String sql = "INSERT INTO tbcliente (cpf, nome, endereco1, endereco2, bairro, cidade, estado, cep, " +
                "idade, primeira_compra, data_nascimento) values(?,?,?,?,?,?,?,?,?,?,?)";

        try{
            Connection connection = ConexaoDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, client.getCpf());
            stmt.setString(2, client.getNome());
            stmt.setString(3, client.getEndereco1());
            stmt.setString(4, client.getEndereco2());
            stmt.setString(5, client.getBairro());
            stmt.setString(6, client.getCidade());
            stmt.setString(7, client.getEstado());
            stmt.setString(8, client.getCep());
            stmt.setString(9, client.getIdade());
            stmt.setString(10, client.getPrimeiraCompra());
            stmt.setString(11, client.getDataNascimento());

            stmt.executeUpdate();
            connection.close();
            stmt.close();
            System.out.println("Client cadastred with success!");
        }catch (SQLException error){
            System.out.println("Error to create client!"+ error.toString());
        }
    }

    public void updateClientAdreess(Client client){
        String sql = "UPDATE tbcliente SET endereco1 = ?, endereco2 = ?, bairro = ? WHERE cpf = ?";

        try{
            Connection connection = ConexaoDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, client.getEndereco1());
            stmt.setString(2, client.getEndereco2());
            stmt.setString(3, client.getBairro());
            stmt.setString(4, client.getCpf());

            stmt.executeUpdate();
            connection.close();
            stmt.close();
            System.out.println("Adreess updated with success!");
        }catch (SQLException error){
            System.out.println("Error to uopdate datas: "+error.toString());
        }
    }

    public void deleteClient(Client client){
        String sql = "DELETE FROM tbcliente WHERE cpf = ?";

        try{
            Connection connection = ConexaoDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, client.getCpf());

            stmt.executeUpdate();
            connection.close();
            stmt.close();
            System.out.println("Client deleted with success!");
        }catch (SQLException error){
            System.out.println("Error to delete client: "+error.toString());
        }
    }

    public List<Client> listClients(){
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM tbcliente";

        try {
            Connection connection = ConexaoDB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet result = stmt.executeQuery();
            while (result.next()){
                Client client = new Client();
                client.setCpf(result.getString("cpf"));
                client.setNome(result.getString("nome"));
                client.setEndereco1(result.getString("endereco1"));
                client.setEndereco2(result.getString("endereco2"));
                client.setBairro(result.getString("bairro"));
                client.setCidade(result.getString("cidade"));
                client.setEstado(result.getString("estado"));
                client.setCep(result.getString("cep"));
                client.setIdade(result.getString("idade"));
                client.setPrimeiraCompra(result.getString("primeira_compra"));
                client.setDataNascimento(result.getString("data_nascimento"));

                clients.add(client);
            }
            stmt.close();
            connection.close();
        } catch (SQLException error) {
            System.out.println("Error to list all clients: "+error.toString());
        }
        return clients;
    }
}
