package husj.referencia.repository;

import husj.referencia.model.entity.Facturacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturacionRepository extends JpaRepository<Facturacion, Long> {
}
