package pbo.management_restourant.services;

import pbo.management_restourant.dto.request.BranchRequest;
import pbo.management_restourant.dto.response.CommonResponse;

import java.util.List;

public interface BranchService {

    CommonResponse addBranch(BranchRequest request);

//    List<CommonResponse> findByKeyword(String keyword);

    CommonResponse getBranchById(String id);

    List<CommonResponse> getAllBranch();

    CommonResponse updateBranch(BranchRequest request);

    CommonResponse deleteBranchById(String id);
}
