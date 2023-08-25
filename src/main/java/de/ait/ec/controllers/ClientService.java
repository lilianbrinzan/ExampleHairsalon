package de.ait.ec.controllers;

import ch.qos.logback.core.net.server.Client;
import de.ait.ec.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository repository;
    public ClientService(ClientRepository repository){
        this.repository = repository;
    }
    public List<Client> findAll() {
        return repository.findAll();
    }
}
