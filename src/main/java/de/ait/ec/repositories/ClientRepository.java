package de.ait.ec.repositories;

import ch.qos.logback.core.net.server.Client;

import java.util.List;

public interface ClientRepository {
    default List<Client> findAll() {
        return null;
    }
}
