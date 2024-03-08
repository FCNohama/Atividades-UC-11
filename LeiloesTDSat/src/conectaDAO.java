
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    private Connection conexao;

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/leiloes?useSSL=false", "root", "123456");
            System.out.println("Conectado com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Falha ao conectar com o BD: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Desconectado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desconectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
