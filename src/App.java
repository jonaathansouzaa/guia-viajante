import connection.JDBCConnection;
import dao.ModalidadeDAO;
import domain.Modalidade;

public class App {

    public static void main(String[] args) {
        System.out.println(JDBCConnection.getConnection());

        for (Modalidade modalidade : new ModalidadeDAO().listarTodos()) {
            System.out.println(modalidade.getIdModalidade() + " / "
                    + modalidade.getNome());
        }

    }

}
