package com.samtel.clientes.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.samtel.clientes.dto.ClienteDTO;
import com.samtel.clientes.persistence.entity.ClienteEntity;
import com.samtel.clientes.persistence.respository.ClienteRepository;
import com.samtel.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImp implements ClienteService {

  private final ClienteRepository repository;

  @Override
  public List<ClienteDTO> getAll() {
    return repository.findAll().stream().map(ent -> {
      ClienteDTO dto = new ClienteDTO();
      dto.setId(ent.getId());
      dto.setNombreCliente(ent.getNombre());
      dto.setCorreoElectronico(ent.getCorreo());
      dto.setDocumento(ent.getDni());
      return dto;
    }).collect(Collectors.toList());

  }

  @Override
  public ClienteDTO getClienteByid(Long id) {
    Optional<ClienteEntity> clienteOptional = repository.findById(id);

    ClienteEntity cliente = clienteOptional.orElse(null);

    if (cliente == null) {
      return null; // O cualquier otro valor predeterminado
    }

    ClienteDTO dto = new ClienteDTO();
    dto.setId(cliente.getId());
    dto.setDocumento(cliente.getDni());
    dto.setCorreoElectronico(cliente.getCorreo());
    dto.setNombreCliente(cliente.getNombre());
    return dto;
  }

  @Override
  public ClienteDTO crearCliente(ClienteDTO cliente) {
    ClienteEntity entity = new ClienteEntity();

    entity.setNombre(cliente.getNombreCliente());
    entity.setDni(cliente.getDocumento());
    entity.setCorreo(cliente.getCorreoElectronico());

    repository.save(entity);
    cliente.setId(entity.getId());

    return cliente;
  }

  @Override
  public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteUpd) {
    Optional<ClienteEntity> clienteOptional = repository.findById(id);

    ClienteEntity clienteExistente = clienteOptional.orElse(null);

    if (clienteUpd == null) {
      return null;
    }

    clienteExistente.setCorreo(clienteUpd.getCorreoElectronico());
    clienteExistente.setDni(clienteUpd.getDocumento());
    clienteExistente.setNombre(clienteUpd.getNombreCliente());
    clienteExistente.setId(clienteExistente.getId());
    repository.save(clienteExistente);
    clienteUpd.setId(clienteExistente.getId());
    return clienteUpd;
  }

  @Override
  public ClienteDTO borrarCliente(Long id) {
    Optional<ClienteEntity> clienteOptional = repository.findById(id);

    ClienteEntity cliente = clienteOptional.orElse(null);

    if (cliente == null) {
      return null;
    }

    repository.delete(cliente);

    ClienteDTO dto = new ClienteDTO();
    dto.setId(cliente.getId());
    dto.setDocumento(cliente.getDni());
    dto.setCorreoElectronico(cliente.getCorreo());
    dto.setNombreCliente(cliente.getNombre());
    return dto;
  }

}
