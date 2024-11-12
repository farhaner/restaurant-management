package pbo.management_restourant.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbo.management_restourant.dto.branch.request.BranchRequest;
import pbo.management_restourant.dto.branch.response.BranchResponse;
import pbo.management_restourant.models.BranchModel;
import pbo.management_restourant.repositories.BranchRepository;
import pbo.management_restourant.services.BranchService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    public BranchResponse branchResponse(BranchRequest request) {
        try {

            BranchModel branchModels = new BranchModel();
            branchModels.setBranchId(String.valueOf(UUID.randomUUID()));
            branchModels.setBranchName(request.getBranchName());
            branchModels.setBranchNoTelp(branchModels.getBranchNoTelp());
            branchModels.setBranchLocation(request.getBranchLocation());
            branchRepository.save(branchModels);
            System.out.print("haris jelek");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BranchResponse();
    }
}
