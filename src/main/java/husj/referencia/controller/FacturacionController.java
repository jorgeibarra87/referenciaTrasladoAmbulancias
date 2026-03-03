package husj.referencia.controller;

import husj.referencia.model.dto.request.FacturacionRequestDTO;
import husj.referencia.model.dto.response.FacturacionResponseDTO;
import husj.referencia.service.FacturacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FacturacionController {

    private final FacturacionService facturacionService;

    @PostMapping
    public ResponseEntity<FacturacionResponseDTO> crear(@Valid @RequestBody FacturacionRequestDTO request) {
        FacturacionResponseDTO respuesta = facturacionService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        FacturacionResponseDTO respuesta = facturacionService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<FacturacionResponseDTO>> listarTodos() {
        List<FacturacionResponseDTO> lista = facturacionService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturacionResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody FacturacionRequestDTO request
    ) {
        FacturacionResponseDTO respuesta = facturacionService.actualizar(id, request);
        return ResponseEntity.ok(respuesta);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<FacturacionResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(facturacionService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
