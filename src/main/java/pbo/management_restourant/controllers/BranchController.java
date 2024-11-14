package pbo.management_restourant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pbo.management_restourant.dto.request.BranchRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.services.BranchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/branch")
public class BranchController {

    private final BranchService branchService;

    @PostMapping
    public CommonResponse creatBranch(@RequestBody BranchRequest request) {
        return branchService.addBranch(request);
    }

    @GetMapping(value = "/{id}")
    public CommonResponse getBranchById(@PathVariable String id) {
        return branchService.getBranchById(id);
    }

    @GetMapping
    public List<CommonResponse> getAllBranch() {
        return branchService.getAllBranch();
    }

    @PutMapping
    public CommonResponse updateBranch(@RequestBody BranchRequest request) {
        return branchService.updateBranch(request);
    }

    @DeleteMapping(value = "/{id}")
    public CommonResponse deleteBranch(@PathVariable String id) {
        return branchService.deleteBranchById(id);
    }
}
