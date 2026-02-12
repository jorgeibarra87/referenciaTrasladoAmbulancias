package husj.referencia.controller;

import husj.referencia.model.dto.request.CuentaMedicaRequestDTO;
import husj.referencia.model.dto.response.CuentaMedicaResponseDTO;
import husj.referencia.service.CuentaMedicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas-medicas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CuentaMedicaController {

    private final CuentaMedicaService cuentaMedicaService;

    @PostMapping
    public ResponseEntity<CuentaMedicaResponseDTO> crear(@Valid @RequestBody CuentaMedicaRequestDTO request) {
        CuentaMedicaResponseDTO respuesta = cuentaMedicaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaMedicaResponseDTO> obtenerPorId(@PathVariable Long id) {
        CuentaMedicaResponseDTO respuesta = cuentaMedicaService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<CuentaMedicaResponseDTO>> listarTodos() {
        List<CuentaMedicaResponseDTO> lista = cuentaMedicaService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaMedicaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CuentaMedicaRequestDTO request
    ) {
        CuentaMedicaResponseDTO respuesta = cuentaMedicaService.actualizar(id, request);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaMedicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
