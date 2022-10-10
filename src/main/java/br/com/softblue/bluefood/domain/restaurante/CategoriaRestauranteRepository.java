package br.com.softblue.bluefood.domain.restaurante;
/* interface que cria o repositorio que integra com o banco de dados facilidade do spring data*/
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRestauranteRepository extends JpaRepository<CategoriaRestaurante, Integer>{
	
	

}
