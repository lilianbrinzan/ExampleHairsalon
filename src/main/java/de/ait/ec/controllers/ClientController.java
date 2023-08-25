package de.ait.ec.controllers;

import ch.qos.logback.core.net.server.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service){
        this.service = service;
    }

    @GetMapping                  // ultimul punct
    public String getAllClients(Model model){
        List<Client> clients = service.findAll();
        model.addAttribute("clients", clients);
        return "clients_view";
    }
}
