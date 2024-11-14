package pbo.management_restourant.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pbo.management_restourant.dto.request.BranchRequest;
import pbo.management_restourant.dto.response.BranchResponse;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.models.BranchModel;
import pbo.management_restourant.repositories.BranchRepository;
import pbo.management_restourant.services.BranchService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Transactional
    public CommonResponse addBranch(BranchRequest request) {
        CommonResponse response = new CommonResponse();

        try {
            Optional<BranchModel> branchId = branchRepository.findById(request.getBranchCode());
            if (branchId.isPresent()) {
                BranchModel branchModel = new BranchModel();
                branchModel.setBranchId(request.getBranchCode());
                branchModel.setBranchName(request.getBranchName());
                branchModel.setBranchNoTelp(request.getNoTelp());
                branchModel.setBranchLocation(request.getBranchAddres());
                branchModel.setBranchCreatedAt(LocalDate.now());
                branchRepository.save(branchModel);


                BranchResponse branchResponse = new BranchResponse();
                branchResponse.setBranchCode(request.getBranchCode());
                branchResponse.setBranchName(request.getBranchName());
                branchResponse.setNoTelp(request.getNoTelp());
                branchResponse.setStatus(request.getStatus());
                branchResponse.setBranchAddres(request.getBranchAddres());

                response.setData(branchResponse);
                response.setMessage("New Branch Successfully added");

            } else {
                response.setData(request);
                response.setMessage("Failed Add a New Branch ");
            }

        } catch (Exception e) {
            log.error("Created Branch Failed: ", e);
        }
        return response;
    }

//    @Override
//    public List<CommonResponse> findByKeyword(String keyword) {
//        List<BranchResponse> getBranchByKeyword = branchRepository.findByKeyword(keyword);
//
//        CommonResponse response = new CommonResponse();
//
//        response.setData(getBranchByKeyword);
//        response.setMessage("Found " + getBranchByKeyword.size() + " branches");
//
//        return Collections.singletonList(response);
//    }

    @Override
    public CommonResponse getBranchById(String id) {
        CommonResponse response = new CommonResponse();

        Optional<BranchModel> getBranch = branchRepository.findById(id);
//        if (getBranch != null) {
        response.setData(getBranch);
        response.setMessage("Branch Successfully retrieved");
//        } else {
//            response.setData(null);
//            response.setMessage("Failed Retrieving Branch");
//        }
        return response;
    }

    @Override
    public List<CommonResponse> getAllBranch() {
        CommonResponse response = new CommonResponse();

        List<BranchModel> allBranch = branchRepository.findAll();

        response.setData(allBranch);
        response.setMessage("All Branch Successfully retrieved");

        return Collections.singletonList(response);
    }

    public CommonResponse updateBranch(BranchRequest request) {
        CommonResponse response = new CommonResponse();
        return null;
    }

    @Override
    public CommonResponse deleteBranchById(String id) {
        CommonResponse response = new CommonResponse();
        Optional<BranchModel> getBranch = branchRepository.findById(id);
        if (getBranch.isPresent()) {

            branchRepository.deleteById(id);
            response.setData(getBranch);
            response.setMessage("Branch Successfully deleted");
        } else {
            response.setData(getBranch);
            response.setMessage("Branch Not Found");
        }
        return response;
    }
}
