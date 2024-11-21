package pbo.management_restourant.services;

import pbo.management_restourant.dto.request.MenuRequest;
import pbo.management_restourant.dto.response.CommonResponse;

import java.util.List;

public interface MenuService {

    CommonResponse addMenu(MenuRequest request);

//    List<CommonResponse> findByKeyword(String keyword);

    CommonResponse getMenuByCode(String code);

    List<CommonResponse> getAllMenu();

    CommonResponse updateMenu(MenuRequest request);

    CommonResponse deleteMenuByCode(String code);
}
