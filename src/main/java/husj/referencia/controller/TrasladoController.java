package husj.referencia.controller;

import husj.referencia.model.dto.request.TrasladoRequestDTO;
import husj.referencia.model.dto.response.TrasladoResponseDTO;
import husj.referencia.service.TrasladoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traslados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrasladoController {

    private final TrasladoService trasladoService;

    @PostMapping
    public ResponseEntity<TrasladoResponseDTO> crear(@Valid @RequestBody TrasladoRequestDTO request) {
        TrasladoResponseDTO respuesta = trasladoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrasladoResponseDTO> obtenerPorId(@PathVariable Long id) {
        TrasladoResponseDTO respuesta = trasladoService.obtenerPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<TrasladoResponseDTO>> listarTodos() {
        List<TrasladoResponseDTO> lista = trasladoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrasladoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TrasladoRequestDTO request
    ) {
        TrasladoResponseDTO respuesta = trasladoService.actualizar(id, request);
        return ResponseEntity.ok(respuesta);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<TrasladoResponseDTO> cambiarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(trasladoService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        trasladoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
