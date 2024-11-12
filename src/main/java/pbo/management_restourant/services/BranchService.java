package pbo.management_restourant.services;

import pbo.management_restourant.dto.branch.request.BranchRequest;
import pbo.management_restourant.dto.branch.response.BranchResponse;

public interface BranchService {

    BranchResponse branchResponse(BranchRequest request);
}
