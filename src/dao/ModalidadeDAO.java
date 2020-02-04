package dao;

import connection.JDBCConnection;
import domain.Modalidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModalidadeDAO implements DAO<Modalidade> {

    private Connection conexao = null;
    private PreparedStatement ps = null;

    @Override
    public void salvar(Modalidade domain) {
        try {
            conexao = JDBCConnection.getConnection();
            String sql = "INSERT INTO modalidade(nome) VALUES (?);";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, domain.getNome());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void atualizar(Modalidade domain) {
        try {
            conexao = JDBCConnection.getConnection();
            String sql = "UPDATE modalidade SET nome = ? WHERE id_modalidade = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, domain.getNome());
            ps.setInt(2, domain.getIdModalidade());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public void deletar(Modalidade domain) {
        try {
            conexao = JDBCConnection.getConnection();
            String sql = "DELETE FROM modalidade WHERE id_modalidade = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, domain.getIdModalidade());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public List<Modalidade> listarTodos() {
        List<Modalidade> modalidades = null;
        try {
            conexao = JDBCConnection.getConnection();
            String sql = "SELECT * FROM modalidade";
            ps = conexao.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            modalidades = new ArrayList<>();
            while (resultSet.next()) {
                Modalidade modalidade = new Modalidade();
                modalidade.setIdModalidade(resultSet.getInt("id_modalidade"));
                modalidade.setNome(resultSet.getString("nome"));
                modalidades.add(modalidade);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close();
        }
        return modalidades;
    }

    private void close() {
        try {
            if (conexao != null) {
                conexao.close();
            }

            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
