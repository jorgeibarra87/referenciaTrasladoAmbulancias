package husj.referencia.controller;

import husj.referencia.model.dto.response.TrasladoCompletoResponseDTO;
import husj.referencia.service.TrasladoCompletoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/traslados-completos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrasladoCompletoController {

    private final TrasladoCompletoService trasladoCompletoService;

    @GetMapping
    public ResponseEntity<List<TrasladoCompletoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(trasladoCompletoService.listarTodos());
    }
}