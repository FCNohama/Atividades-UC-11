
import com.mysql.cj.jdbc.ConnectionImpl;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public static void cadastrar(ProdutosDTO p) {
        conectaDAO conexao = new conectaDAO();

        try {
            conexao.conectar();

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            consulta.setString(1, p.getNome());
            consulta.setInt(2, p.getValor());
            consulta.setString(3, "A Venda");

            consulta.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto no banco de dados");
            e.printStackTrace();

        } finally {
            conexao.desconectar();
        }
    }

    public static List<ProdutosDTO> listarTodos() {
        conectaDAO conexao = new conectaDAO();

        List<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();

        try {
            conexao.conectar();

            String sql = "SELECT * FROM produtos";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);

            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getInt("valor"));
                p.setStatus(resposta.getString("Status"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar registros do banco de dados");
            e.printStackTrace();

        } finally {
            conexao.desconectar();
        }
        return lista;
    }
}
