package service.carro;

import java.util.ArrayList;
import java.util.List;

import exception.CarroException;
import model.entity.carros.Carro;
import seletor.carro.CarroSeletor;


public class CarroService {
	
	
	private MontadoraService repository = new MontadoraService();

    public ArrayList<Carro> consultarComFiltros(CarroSeletor seletor) {
        ArrayList<Carro> carros = consultarTodas();

        if (validarFiltros(seletor)) {
            StringBuilder whereClause = new StringBuilder(" WHERE 1=1");

            if (seletor.getNomeMarca() != null && !seletor.getNomeMarca().trim().isBlank()) {
                whereClause.append(" AND m.nome LIKE '%" + seletor.getNomeMarca().trim() + "%'");
            }

            if (seletor.getModelo() != null && !seletor.getModelo().trim().isBlank()) {
                whereClause.append(" AND c.modelo LIKE '%" + seletor.getModelo().trim() + "%'");
            }

            if (seletor.getAnoInicial() != null && seletor.getAnoFinal() != null) {
                whereClause.append(" AND c.ano BETWEEN " + seletor.getAnoInicial() + " AND " + seletor.getAnoFinal());
            }


            String query = "SELECT c.* FROM carro c INNER JOIN montadora m ON c.idmontadora = m.id" + whereClause.toString();
           
        } else {
            System.out.println("filtro invalido!");
        }

        return carros;
    }

    private boolean validarFiltros(CarroSeletor seletor) {
        boolean peloMenosUmFiltroPreenchido = false;
        boolean anoIncompleto = false;
        boolean valorIncompleto = false;

        if (seletor.getNomeMarca() != null && !seletor.getNomeMarca().trim().isBlank()) {
            peloMenosUmFiltroPreenchido = true;
        }

        if (seletor.getModelo() != null && !seletor.getModelo().trim().isBlank()) {
            peloMenosUmFiltroPreenchido = true;
        }

        if (seletor.getAnoInicial() != null) {
            anoIncompleto = seletor.getAnoFinal() == null;
        }

        if (seletor.getAnoFinal() != null) {
            anoIncompleto = seletor.getAnoInicial() == null;
        }

        return peloMenosUmFiltroPreenchido && !anoIncompleto && !valorIncompleto;
    }


	public ArrayList<Carro> consultarTodas() {
		return consultarTodas();
	}
		

	public int consultarEstoqueCarros(int idMontadora) throws CarroException {
		ArrayList<Carro> carros = consultarTodas();
		ArrayList<Carro> montadoras = consultarTodas();
		
		boolean montadoraCadastrada = montadoras.stream().anyMatch(montadora -> montadora.getId().equals(idMontadora));
		
		if(!montadoraCadastrada) {
			throw new CarroException("Montadora não encontrada. Id informado: " + idMontadora); 
		}
		
		List<Carro> carrosDaMontadoraSelecionada = carros.stream().filter(c -> c.getMontadora().getId().equals(idMontadora)).toList();

		return carrosDaMontadoraSelecionada.size();
	}

}
