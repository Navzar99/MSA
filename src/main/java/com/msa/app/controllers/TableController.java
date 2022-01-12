package com.msa.app.controllers;

import com.msa.app.dtos.TableDTO;
import com.msa.app.dtos.UserDTO;
import com.msa.app.entities.Table;
import com.msa.app.entities.User;
import com.msa.app.services.TableServices;
import com.msa.app.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/tables")
public class TableController {
    private final TableServices tableServices;

    public TableController(TableServices tableServices) {
        this.tableServices = tableServices;
    }

    @PostMapping(path="/addNewTable")
    public Table addNewTable(@RequestBody TableDTO tableDTO) {
        return tableServices.addTable(tableDTO);
    }

    @GetMapping(path = "/getTables")
    public List<Table> getTables(){
        return tableServices.getAllTables();
    }
}
