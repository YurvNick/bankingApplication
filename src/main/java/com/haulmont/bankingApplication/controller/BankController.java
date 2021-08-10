package com.haulmont.bankingApplication.controller;

import com.haulmont.bankingApplication.repository.BankRepository;
import com.haulmont.bankingApplication.service.CrudEntityService;
import com.haulmont.bankingApplication.service.impl.BankServiceImpl;
import com.haulmont.bankingApplication.models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/banks")
public class BankController {

    private final BankServiceImpl bankService;

    public BankController(BankServiceImpl bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/create")
    public String createBank() {
        return "banks/bank_create";
    }

    @PostMapping("/create")
    public String createBank(@RequestParam(name = "name") String name) {
        bankService.create(new Bank(name));
        return "redirect:";
    }
    @GetMapping("/{uuid}/credits")
    public String getBankCredit(@PathVariable String uuid, Model model) {
        model.addAttribute("bank", bankService.getByUuid(UUID.fromString(uuid)));
        return "banks/bank_credits";
    }

    @GetMapping("/{uuid}/clients")
    public String getBankClient(@PathVariable String uuid, Model model) {
        model.addAttribute("bank", bankService.getByUuid(UUID.fromString(uuid)));
        return "banks/bank_clients";
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("banks", bankService.getAll() );
        return "banks/banks";
    }

    @GetMapping("/{uuid}/update")
    public String updateBank(@PathVariable String uuid, Model model) {
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        model.addAttribute("bank", bankService.getByUuid(UUID.fromString(uuid)));
        return "banks/bank_update";
    }

    @PostMapping("/{uuid}/update")
    public String updateBank(@RequestParam(name = "uuid") String uuid,
                           @RequestParam(name = "name") String name) {
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        bank.setName(name);
        bankService.create(bank);
        return "redirect:/banks/";
    }

    @GetMapping("/{uuid}/delete")
    public String deleteBank(@PathVariable String uuid, Model model) {
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        model.addAttribute("bank", bank);
        return "/banks/bank_delete";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteBank(@PathVariable String uuid) {
        Bank bank = bankService.getByUuid(UUID.fromString(uuid));
        bankService.delete(bank);
        return "redirect:/banks/";
    }
}

