package pbo.management_restourant.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pbo.management_restourant.dto.request.MenuRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.services.MenuService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public CommonResponse creatMenu(@RequestBody MenuRequest request) {
        return menuService.addMenu(request);
    }

    @GetMapping(value = "/{code}")
    public CommonResponse getMenuByCode(@PathVariable String code) {
        return menuService.getMenuByCode(code);
    }

    @GetMapping
    public List<CommonResponse> getAllMenu() {
        return menuService.getAllMenu();
    }

    @PutMapping
    public CommonResponse updateMenu(@RequestBody MenuRequest request) {
        return menuService.updateMenu(request);
    }

    @DeleteMapping(value = "/{code}")
    public CommonResponse deleteMenu(@PathVariable String code) {
        return menuService.deleteMenuByCode(code); 
    }
}
