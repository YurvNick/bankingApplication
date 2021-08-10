package com.haulmont.bankingApplication.controller;

import com.haulmont.bankingApplication.models.Client;
import com.haulmont.bankingApplication.service.impl.ClientServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/create")
    public String createClient() {
        return "clients/client_create";
    }

    @PostMapping("/create")
    public String appendClient(@RequestParam(name = "name") String fullName,
                            @RequestParam(name = "phone") String phoneNumber,
                            @RequestParam(name = "email") String email,
                            @RequestParam(name = "passport") String passportNumber) {
        clientService.create(new Client(fullName, phoneNumber, email, passportNumber));
        return "redirect:";
    }

    @GetMapping
    public String getAllClients(Model model) {
        model.addAttribute("clients", clientService.getAll() );
        return "clients/clients";
    }

    @GetMapping("/{uuid}/update")
    public String updateClient(@PathVariable String uuid, Model model) {
        model.addAttribute("client", clientService.getByUuid(UUID.fromString(uuid)));
        return "clients/client_update";
    }

    @PostMapping("/{uuid}/update")
    public String updateClient(@RequestParam(name = "uuid") String uuid,
                               @RequestParam(name = "name") String fullName,
                               @RequestParam(name = "phone") String phoneNumber,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "passport") String passportNumber) {
        Client client = clientService.getByUuid(UUID.fromString(uuid));
        client.setFullName(fullName);
        client.setPhoneNumber(phoneNumber);
        client.setEmail(email);
        client.setPassportNumber(passportNumber);
        clientService.create(client);
        return "redirect:/clients/";
    }

    @GetMapping("/{uuid}/delete")
    public String deleteClient(@PathVariable String uuid, Model model) {
        model.addAttribute("client", clientService.getByUuid(UUID.fromString(uuid)));
        return "/clients/client_delete";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteCredit(@PathVariable String uuid) {
        clientService.delete(clientService.getByUuid(UUID.fromString(uuid)));
        return "redirect:/clients/";
    }
}
