package pbo.management_restourant.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import pbo.management_restourant.dto.request.BranchRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.services.BranchService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/branch")
public class BranchController {

    private final BranchService branchService;

    @PostMapping(value = "/create")
    public CommonResponse creatBranch(@RequestBody BranchRequest request) {
        return branchService.addBranch(request);
    }

    @GetMapping(value = "/{keyword}")
    public CommonResponse getBranchByKeywords(@PathVariable String keyword) {
        return branchService.getAllBranchByKeyword(keyword);
    }

    @PostMapping(value = "/get")
    public CommonResponse getBranchByKeyword(@RequestBody BranchRequest request) {
        return branchService.getAllBranchByKeyword(request.getKeyword());
    }

    @GetMapping
    public CommonResponse getAllBranch() {
        return branchService.getAllBranch();
    }

    @PutMapping(value = "/update")
    public CommonResponse updateBranch(@RequestBody BranchRequest request) throws BadRequestException {
        return branchService.updatedBranchById(request);
    }

    @DeleteMapping(value = "/{id}")
    public CommonResponse deleteBranch(@PathVariable String id) {
        return branchService.deleteBranchById(id);
    }
}
