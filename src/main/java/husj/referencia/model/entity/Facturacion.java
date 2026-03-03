package husj.referencia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "facturacion")
@Getter
@Setter
public class Facturacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FACTURACION")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_TRASLADO", referencedColumnName = "ID_TRASLADO")
    private Traslado traslado;
    @Column(name = "FECHA_PREFACTURA", nullable = false)
    private LocalDateTime fechaPrefactura;
    @Column(name = "PREFACTURA", nullable = false, length = 255)
    private String prefactura;
    @Column(name = "PRODUCCION", nullable = false, length = 255)
    private String produccion;
    @Column(name = "FECHA_FACTURA", nullable = false)
    private LocalDateTime fechaFactura;
    @Column(name = "FACTURA", nullable = false, length = 255)
    private String factura;
    @Column(name = "VALOR", nullable = false)
    private Double valor;
    @Column(name = "NOMBRE_FACTURADOR", nullable = false, length = 255)
    private String nombreFacturador;
    @Column(name = "ESTADO", nullable = false)
    private String estado;
}
