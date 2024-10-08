import java.sql.Connection;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ClientDAO clientDAO = new ClientDAO();

        //Add client
        Client newClient = new Client();
        newClient.setCpf("12345678910");
        newClient.setNome("Ruan Carlos");
        newClient.setEndereco1("Rua colombo");
        newClient.setEndereco2("Rua Jacinto Machado");
        newClient.setBairro("Joao costa");
        newClient.setCidade("Joinville");
        newClient.setEstado("sc");
        newClient.setCep("12345678");
        newClient.setIdade("19");
        newClient.setPrimeiraCompra("1");
        newClient.setDataNascimento("2005-04-06");

        clientDAO.createClient(newClient);

        // Update client
        newClient.setEndereco1("Teste");
        newClient.setEndereco2("Teste2");
        newClient.setBairro("Bairro");

        clientDAO.updateClientAdreess(newClient);

        // Delete client
        clientDAO.deleteClient(newClient);

        // List clients 
        for(Client client : clientDAO.listClients()){
            System.out.println("CPF: "+client.getCpf());
            System.out.println("Nome: "+client.getNome());
            System.out.println("Endereço 1: "+client.getEndereco1());
            System.out.println("Endereço 2: "+client.getEndereco2());
            System.out.println("Bairro: "+client.getBairro());
            System.out.println("Cidade: "+client.getCidade());
            System.out.println("Estado: "+client.getEstado());
            System.out.println("CEP: "+client.getCep());
            System.out.println("Idade: "+client.getIdade());
            System.out.println("Primeira compra: "+client.getPrimeiraCompra());
            System.out.println("Data de Nascmento: "+client.getDataNascimento());
            System.out.println("=================================================");
        }


        /*
        StateDAO dao = new StateDAO();

        // Add state
        State newState = new State();
        newState.setSigla("SP");
        newState.setDescicao("São Paulo");
        dao.createState(newState);

        // List states
        for(State state : dao.listStates()){
            System.out.println(state.getSigla()+" - "+state.getDescricao());
        }

        // Update state
        newState.setDescicao("São Paulo - Atualizado");
        dao.updateState(newState);

        // Delete state
        boolean returnFunction = dao.deleteState("SP");
        if(returnFunction){
            System.out.println("Deleted with success!");
        }

        for(State state : dao.listStates()){
            System.out.println(state.getSigla()+" - "+state.getDescricao());
        }
         */

        /*
        try {
            Connection connection = ConexaoDB.getConnection();
            System.out.println(connection);
        } catch (Exception error) {
            System.out.println("Error to connect: " + error.toString());
        }
         */
    }
}
