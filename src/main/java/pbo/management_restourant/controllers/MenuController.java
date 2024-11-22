package pbo.management_restourant.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import pbo.management_restourant.dto.request.BranchRequest;
import pbo.management_restourant.dto.request.MenuRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.services.BranchService;
import pbo.management_restourant.services.MenuService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping(value = "/create")
    public CommonResponse creatBranch(@RequestBody MenuRequest request) {
        return menuService.addMenu(request);
    }

    @GetMapping(value = "/{keyword}")
    public CommonResponse getBranchByKeywords(@PathVariable String keyword) {
        return menuService.getAllMenuByKeyword(keyword);
    }

    @PostMapping(value = "/get")
    public CommonResponse getBranchByKeyword(@RequestBody MenuRequest request) {
        return menuService.getAllMenuByKeyword(request.getKeyword());
    }

    @GetMapping
    public CommonResponse getAllBranch() {
        return menuService.getAllMenu();
    }

    @PutMapping(value = "/update")
    public CommonResponse updateBranch(@RequestBody MenuRequest request) throws BadRequestException {
        return menuService.updatedMenuById(request);
    }

    @DeleteMapping(value = "/{id}")
    public CommonResponse deleteBranch(@PathVariable String id) {
        return menuService.deleteMenuById(id);
    }
}
