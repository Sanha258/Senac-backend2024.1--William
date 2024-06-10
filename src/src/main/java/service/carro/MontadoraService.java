package service.carro;


import java.util.ArrayList;

import exception.CarroException;

import model.entity.carros.Montadora;
import model.repository.carros.MontadoraRepository;

public class MontadoraService {
	
	private MontadoraRepository repository = new MontadoraRepository();
		
		public void salvar(Montadora novaMontadora) throws CarroException {
			if (novaMontadora.getNome() == null || novaMontadora.getNome().isEmpty()) {
	            throw new CarroException("Nome da montadora é obrigatório.");
	        }
			
			 if (novaMontadora.getPaisFundacao() == null || novaMontadora.getPaisFundacao().isEmpty()) {
		            throw new IllegalArgumentException("País de fundação da montadora é obrigatório.");
		        }

		     if (novaMontadora.getNomePresidente() == null || novaMontadora.getNomePresidente().isEmpty()) {
		            throw new IllegalArgumentException("Nome do presidente da montadora é obrigatório.");
		        }

		     if (novaMontadora.getDataFundacao() == null) {
		            throw new IllegalArgumentException("Data de fundação da montadora é obrigatória.");
		        }
		     
		
		}
		
		public ArrayList<Montadora> consultarTodas() {
			return repository.consultarTodos();
		}

		
}
