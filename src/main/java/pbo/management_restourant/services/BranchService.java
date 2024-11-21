package pbo.management_restourant.services;

import org.apache.coyote.BadRequestException;
import pbo.management_restourant.dto.request.BranchRequest;
import pbo.management_restourant.dto.response.CommonResponse;

public interface BranchService {

    CommonResponse addBranch(BranchRequest request);

    CommonResponse getAllBranch();

    CommonResponse getAllBranchByKeyword(String keyword);

    CommonResponse updatedBranchById(BranchRequest request) throws BadRequestException;

    CommonResponse deleteBranchById(String id);
}
