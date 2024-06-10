package controller.carros;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.carros.Carro;
import seletor.carro.CarroSeletor;
import service.carro.CarroService;

@Path("/carro")

public class CarroController {

		private CarroService service = new CarroService();
		
		@POST
		@Path("/filtro")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public ArrayList<Carro> consultarComFiltros(CarroSeletor seletor) {
			return service.consultarComFiltros(seletor);
		}

}
