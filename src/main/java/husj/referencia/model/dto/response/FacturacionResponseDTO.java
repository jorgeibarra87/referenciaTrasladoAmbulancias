package husj.referencia.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturacionResponseDTO {

    private Long id;
    private Long trasladoId;
    private LocalDateTime fechaPrefactura;
    private String prefactura;
    private String produccion;
    private LocalDateTime fechaFactura;
    private String factura;
    private Double valor;
    private String nombreFacturador;
}
