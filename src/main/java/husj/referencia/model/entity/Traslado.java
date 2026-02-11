package husj.referencia.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "traslado")
@Getter
@Setter
public class Traslado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRASLADO")
    private Long id;
    @Column(name = "FECHA_TRASLADO", nullable = false)
    private LocalDateTime fechaTraslado;
    @Column(name = "NOM_PACIENTE", nullable = false, length = 150)
    private String nomPaciente;
    @Column(name = "INGRESO", nullable = false, length = 20)
    private String ingreso;
    @Column(name = "EPS", nullable = false, length = 150)
    private String eps;
    @Column(name = "TIPO_TRASLADO", nullable = false, length = 255)
    private String tipoTraslado;
    @Column(name = "SERVICIO", nullable = false, length = 255)
    private String servicio;
    @Column(name = "DESTINO", nullable = false, length = 255)
    private String destino;
    @Column(name = "CIUDAD", nullable = false, length = 255)
    private String ciudad;
    @Column(name = "AUTORIZACION", nullable = false, length = 255)
    private String autorizacion;
    @Column(name = "AUXILIAR_REFERENCIA", nullable = false, length = 255)
    private String auxiliarReferencia;
    @Column(name = "AUXILIAR_AMBULANCIA", nullable = false, length = 255)
    private String auxiliarAmbulancia;
    @Column(name = "MEDICAMENTOS", nullable = false, length = 255)
    private List<String> medicamentos;
    @Column(name = "FECHA_ARCHIVO", nullable = false)
    private LocalDateTime fechaArchivo;

}
