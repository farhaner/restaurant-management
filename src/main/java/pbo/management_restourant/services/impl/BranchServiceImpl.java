package pbo.management_restourant.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import pbo.management_restourant.dto.request.BranchRequest;
import pbo.management_restourant.dto.response.BranchResponse;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.models.BranchModel;
import pbo.management_restourant.repositories.BranchRepository;
import pbo.management_restourant.services.BranchService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private static LocalDate LOCALDATE = LocalDate.now();

    @Transactional
    @Override
    public CommonResponse addBranch(BranchRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            BranchResponse branchResponse = new BranchResponse();
            validateBranchRequest(request);
            Optional<BranchModel> getBranch = branchRepository.findById(request.getBranchCode());
            if (getBranch.isEmpty()) {
                BranchModel branchModel = new BranchModel();

                branchModel.setBranchId(request.getBranchCode());
                branchModel.setBranchName(request.getBranchName());
                branchModel.setBranchNoTelp(request.getNoTelp());
                branchModel.setBranchLocation(request.getBranchAddres());
                branchModel.setBranchCreatedAt(LOCALDATE);
                branchModel.setStatus(request.getStatus());
                branchModel.setBranchUpdatedAt(LOCALDATE);
                branchRepository.save(branchModel);

                branchResponse.setBranchCode(request.getBranchCode());
                branchResponse.setBranchName(request.getBranchName());
                branchResponse.setNoTelp(request.getNoTelp());
                branchResponse.setStatus(request.getStatus());
                branchResponse.setBranchAddres(request.getBranchAddres());
                branchResponse.setCreatedAt(LOCALDATE);

                response.setData(branchResponse);
                response.setMessage("New Branch Successfully added");
            } else {
                branchResponse.setBranchCode(request.getBranchCode());
                branchResponse.setBranchName(request.getBranchName());
                branchResponse.setNoTelp(request.getNoTelp());
                branchResponse.setStatus(request.getStatus());
                branchResponse.setBranchAddres(request.getBranchAddres());
                branchResponse.setCreatedAt(LOCALDATE);

                response.setData(branchResponse);
                response.setMessage("Branch already exists");
            }
        } catch (IllegalArgumentException | BadRequestException ex) {
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public CommonResponse getAllBranch() {
        CommonResponse response = new CommonResponse();

        List<BranchModel> allListBranch = branchRepository.findAll();
        if (allListBranch.isEmpty()) {
            response.setData(null);
            response.setMessage("No Branch Found");
        }
        response.setData(allListBranch);
        response.setMessage("All Branches successfully retrieved");

        return response;
    }

    @Override
    public CommonResponse getAllBranchByKeyword(String keyword) {
        CommonResponse response = new CommonResponse();
        if (keyword == null || keyword.trim().isEmpty()) {
            response.setData(null);
            response.setMessage("Please enter the keyword");
        } else {
            List<BranchModel> allBranchByKeyword = branchRepository.getAllBranchByKeyword(keyword);
            if (allBranchByKeyword.isEmpty()) {
                response.setData(null);
                response.setMessage("Branch not found");
            } else {
                response.setData(allBranchByKeyword);
                response.setMessage("All Branches successfully retrieved for keyword: " + keyword);
            }
        }
        return response;
    }

    @Transactional
    @Override
    public CommonResponse updatedBranchById(BranchRequest request) throws BadRequestException {
        CommonResponse response = new CommonResponse();
        try {
            if (request.getBranchCode() == null || request.getBranchCode().isEmpty()) {
                throw new BadRequestException("Branch Code cannot be null or empty");
            }
            if (request.getNoTelp() == null || request.getNoTelp().isEmpty() || request.getNoTelp().length() > 15) {
                throw new BadRequestException("NoTelp cannot be null, empty and longer than 15 characters");
            }

            Optional<BranchModel> getId = branchRepository.findById(request.getBranchCode());
            if (getId.isPresent()) {

                Object[] updateBranch = branchRepository.updateBranch(
                        request.getBranchCode(),
                        request.getBranchAddres(),
                        request.getBranchName(),
                        request.getNoTelp(),
                        request.getStatus()
                );

                if (updateBranch != null) {
                    response.setData(updateBranch[0]);
                    response.setMessage("Branch Successfully updated");
                } else {
                    response.setData(null);
                    response.setMessage("Branch update failed");
                }
            } else {
                response.setData(null);
                response.setMessage("Branch with the given code not found");
            }
        } catch (BadRequestException ex) {
            response.setData(null);
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setData(null);
            response.setMessage("An unexpected error occurred: " + ex.getMessage());
        }
        return response;
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

    private void validateBranchRequest(BranchRequest request) throws BadRequestException {
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        if (request.getBranchCode() == null || request.getBranchCode().isEmpty()) {
            throw new BadRequestException("Branch Code cannot be null or empty");
        }
        if (request.getBranchName() == null || request.getBranchName().isEmpty()) {
            throw new BadRequestException("Branch Name cannot be null or empty");
        }
        if (request.getBranchAddres() == null || request.getBranchAddres().isEmpty()) {
            throw new BadRequestException("Branch Address cannot be null or empty");
        }
        if (request.getNoTelp() == null || request.getNoTelp().isEmpty() || request.getNoTelp().length() > 15) {
            throw new BadRequestException("NoTelp cannot be null, empty and longer than 15 characters");
        }
        if (request.getStatus() == null || request.getStatus().isEmpty()) {
            throw new BadRequestException("Status cannot be null or empty");
        }
    }

}
