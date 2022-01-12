package com.msa.app.services;

import com.msa.app.dtos.TableDTO;
import com.msa.app.dtos.UserDTO;
import com.msa.app.entities.Table;
import com.msa.app.entities.User;
import com.msa.app.repositories.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// actions for users
@Service
public class TableServices {
    private final TableRepository tableRepository;

    public TableServices(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public Table addTable(TableDTO tableDTO) {
        return tableRepository.save(tableDTO.mapToTable());
    }

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }
}
