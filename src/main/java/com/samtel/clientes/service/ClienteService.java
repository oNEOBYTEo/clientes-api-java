package com.samtel.clientes.service;

import java.util.List;
import com.samtel.clientes.dto.ClienteDTO;

public interface ClienteService {
  List<ClienteDTO> getAll();

  ClienteDTO getClienteByid(Long id);

  ClienteDTO crearCliente(ClienteDTO cliente);

  ClienteDTO actualizarCliente(Long id, ClienteDTO cliente);

  ClienteDTO borrarCliente(Long id);
}
