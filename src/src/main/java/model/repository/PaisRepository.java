package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pais;
import model.repository.banco.Banco;


public class PaisRepository implements BaseRepository<Pais> {

	@Override
	public Pais salvar(Pais novoPais) {
		String sql = " INSERT INTO pais (nome, sigla) "
				   + " VALUES(?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoPais.getNome());
			stmt.setString(2, novoPais.getSigla());
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoPais.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo país");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoPais;
	}

	public Pais consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Pais pais = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM pais WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				pais = new Pais();
				pais.setId(resultado.getInt("ID"));
				pais.setNome(resultado.getString("NOME"));
				pais.setSigla(resultado.getString("SIGLA"));
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar país com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pais;
	}

	@Override
	public boolean excluir(int id) {
		// william
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pais WHERE id = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir pais");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return excluiu;
	}

	@Override
	public boolean alterar(Pais paisEditado) {
		// William apagar tu
		boolean alterou = false;
		String query = "UPDATE pais" + "SET nome=?, sigla=?" + "WHERE id=?";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, paisEditado.getNome());
			stmt.setString(2, paisEditado.getSigla());
			
			stmt.setInt(3, paisEditado.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar Pais");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
			
		
		return alterou;
	}

	@Override
	public ArrayList<Pais> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ArrayList<Pais> paises = new ArrayList<Pais>();
		ResultSet resultado = null;
		String query = " SELECT * FROM pais";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Pais pais = new Pais();
				pais.setId(resultado.getInt("ID"));
				pais.setNome(resultado.getString("NOME"));
				pais.setSigla(resultado.getString("SIGLA"));
				paises.add(pais);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os países");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return paises;
	}
}
