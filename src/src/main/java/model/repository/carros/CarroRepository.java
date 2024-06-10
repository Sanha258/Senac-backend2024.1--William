package model.repository.carros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.carros.Carro;
import model.repository.BaseRepository;
import model.repository.banco.Banco;

public class CarroRepository implements BaseRepository <Carro> {

	@Override
	public Carro salvar(Carro novoCarro) {
		String sql = " INSERT INTO carro (modelo, placa, montadora, ano, valor) "
				   + " VALUES(?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoCarro.getModelo() );
			stmt.setString(2, novoCarro.getPlaca());
			stmt.setObject(3, novoCarro.getMontadora());
			stmt.setInt(4, novoCarro.getAno());
			stmt.setDouble(5, novoCarro.getValor());
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoCarro.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo carro!");
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
	public boolean alterar(Carro entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Carro consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Carro> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
