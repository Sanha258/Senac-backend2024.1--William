package model.entity;

import java.util.ArrayList;

import exception.VacinacaoException;
import model.entity.Pais;
import model.entity.Vacinacao;
import model.repository.PaisRepository;
import model.repository.VacinacaoRepository;


public class PaisService {

	private PaisRepository repository = new PaisRepository();
	
		public Pais salvar(Pais novoPais) throws VacinacaoException {
			return repository.salvar(novoPais);
		}
		
		// Excluir alterar não faz parte
		public boolean alterar(Pais paisEditado) {
			return repository.alterar(paisEditado);
		}
		
		// Excluir excluir não faz parte
		public boolean excluir (int id) throws VacinacaoException {
			VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
			ArrayList<Vacinacao> paisComVacinacao = vacinacaoRepository.consultarPorIdVacina(id);
			
			if(!paisComVacinacao.isEmpty() || paisComVacinacao == null) {
				throw new VacinacaoException("Pais não pode ser Excluido!");
			}
			return repository.excluir(id); 
		}
		
		public Pais consultarPorId(int id) {
			return repository.consultarPorId(id);
		}
		
		public ArrayList<Pais> consultarTodos() {
			return repository.consultarTodos();
		}
		
}
