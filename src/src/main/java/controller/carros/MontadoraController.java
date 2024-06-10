package controller.carros;

import java.util.ArrayList;

import exception.CarroException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.carros.Montadora;
import service.carro.CarroService;
import service.carro.MontadoraService;

@Path("/montadora")
public class MontadoraController {
	
	private CarroService carroService = new CarroService();
	private MontadoraService montadoraService = new MontadoraService();
	
	@GET
	@Path("/estoque-carros/{idMontadora}")
	public int consultarEstoqueCarros(@PathParam("idMontadora") int idMontadora) throws CarroException {
		return carroService.consultarEstoqueCarros(idMontadora);
	}
	
	@GET
	@Path("/todas")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Montadora> consultarTodas() {
		return montadoraService.consultarTodas();
	}


}
