package husj.referencia.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrasladoRequestDTO {

    @NotNull(message = "La fecha de traslado es obligatoria")
    private LocalDateTime fechaTraslado;

    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String nomPaciente;

    @NotBlank(message = "El número de documento es obligatorio")
    private String documento;

    @NotBlank(message = "El número de ingreso es obligatorio")
    private String ingreso;

    @NotBlank(message = "La EPS es obligatoria")
    private String eps;

    @NotBlank(message = "El tipo de traslado es obligatorio")
    private String tipoTraslado;

    @NotBlank(message = "El servicio es obligatorio")
    private String servicio;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    private String autorizacion;

    private String auxiliarReferencia;

    private String auxiliarAmbulancia;

    private List<String> medicamentos;

    @NotNull(message = "La fecha de archivo es obligatoria")
    private LocalDateTime fechaArchivo;
}
