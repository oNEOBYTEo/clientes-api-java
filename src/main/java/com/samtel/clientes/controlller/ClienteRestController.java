package com.samtel.clientes.controlller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.samtel.clientes.dto.ClienteDTO;
import com.samtel.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteRestController {
  private final ClienteService service;

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> getAll() {
    try {
      List<ClienteDTO> responseData = service.getAll();

      if (responseData.isEmpty()) {
        return ResponseEntity.noContent().build();
      }

      return ResponseEntity.ok().body(responseData);

    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {

    try {
      ClienteDTO responseData = service.getClienteByid(id);
      if (responseData == null) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  public ResponseEntity<ClienteDTO> createCliente(@RequestBody @Valid ClienteDTO body) {
    try {
      service.crearCliente(body);
      return ResponseEntity.ok().body(body);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id,
      @RequestBody ClienteDTO cliente) {
    try {
      ClienteDTO responseData = service.actualizarCliente(id, cliente);
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable Long id) {
    try {
      ClienteDTO responseData = service.borrarCliente(id);
      return ResponseEntity.ok().body(responseData);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }
}
