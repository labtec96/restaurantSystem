package com.example.controllers;

import dto.RestaurantTableDto;
import dto.UserDto;
import error.UserAlreadyExistException;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import model.RestaurantTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repositories.RestaurantTableRepository;
import services.RestaurantTableService;

import javax.validation.Valid;

/**
 * Created by ch on 2020-05-15
 */
@Slf4j
@Controller
public class TableController {

    @Autowired
    RestaurantTableService restaurantTableService;

    @GetMapping("/admin/table")
    public String table(Model model) {
        log.info("Get request /admin/table");
        model.addAttribute("tables", restaurantTableService.findAll());
        return "admin/table";
    }

    @GetMapping("/admin/table/{id}")
    @ResponseBody
    public RestaurantTable getTable(@PathVariable String id, Model model) {
        log.info("Get request /admin/table/{id}");
        try {
            return restaurantTableService.findById(Long.valueOf(id));
        }catch (ObjectNotFoundException ex){
            //Todo
            return null;
            //esultTable.rejectValue("errors", null, uaeEx.getMessage());
        }
    }

    @PostMapping("/admin/table/new")
    public String newTable(Model model, @ModelAttribute("table") @Valid RestaurantTableDto restaurantTableDto, BindingResult resultTable) {
        log.info("Post request /admin/table/new:");
        restaurantTableService.newTable(restaurantTableDto);
        return "redirect:/admin/table";
    }

    @PostMapping("/admin/table/update")
    public String update(Model model, @ModelAttribute("table") @Valid RestaurantTableDto restaurantTableDto, BindingResult resultTable) {
        log.info("Post request /admin/table/update: for id" + restaurantTableDto.getId());
        try {
            RestaurantTable updatedTable = restaurantTableService.update(restaurantTableDto);
            log.info("Updated table with id:" + updatedTable.getId());
        } catch (ObjectNotFoundException uaeEx) {
            resultTable.rejectValue("errors", null, uaeEx.getMessage());
        }

        return "redirect:/admin/table";
    }

    @GetMapping("/admin/table/{id}/delete")
    public String deleteTable(@PathVariable String id, Model model) {
        log.info("Get Delete Table: Deleting ingredient id " + id);
        restaurantTableService.deleteById(Long.valueOf(id));
        return "redirect:/admin/table";
    }
}
