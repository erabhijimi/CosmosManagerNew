package com.cosmos.controller;

import com.cosmos.model.Inventory;
import com.cosmos.pojo.InventoryGist;
import com.cosmos.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @GetMapping
    public InventoryGist getInventoryGist(){
        return inventoryService.getInventoryGist();
    }
    @PostMapping
    public Inventory saveInventory(@RequestBody Inventory inventory){
        return inventoryService.saveInventory(inventory);
    }
}
