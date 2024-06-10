package model.repository.carros;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pais;
import model.entity.carros.Montadora;
import model.repository.BaseRepository;
import model.repository.banco.Banco;

public class MontadoraRepository implements BaseRepository <Montadora>{

	@Override
	public Montadora salvar(Montadora novaMontadora) {
		
		String sql = " INSERT INTO carro (nome, paisFundacao, nomePresidente, dataFundacao) "
				   + " VALUES(?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novaMontadora.getNome());
			stmt.setString(2, novaMontadora.getPaisFundacao());
			stmt.setObject(3, novaMontadora.getNomePresidente());
			//stmt.setDate(4, Date.valueOf(novaMontadora.getDataFundacao()));
			
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novaMontadora.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova Montadora!");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return null;
	}

	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean alterar(Montadora entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Montadora consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Montadora> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ArrayList<Montadora> montadoras = new ArrayList<Montadora>();
		ResultSet resultado = null;
		String query = " SELECT * FROM montadora";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Montadora montadora = new Montadora();
				montadora.setId(resultado.getInt("ID"));
				montadora.setNome(resultado.getString("NOME"));
				montadora.setNomePresidente(resultado.getString("NOMEPRESIDENTE"));
				montadora.setDataFundacao(resultado.getDate("DATAFUNDACAO"));
				montadoras.add(montadora);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as montadoras");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return null;
	}

}
