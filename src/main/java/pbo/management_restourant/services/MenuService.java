package pbo.management_restourant.services;

import org.apache.coyote.BadRequestException;
import pbo.management_restourant.dto.request.MenuRequest;
import pbo.management_restourant.dto.response.CommonResponse;

public interface MenuService {

    CommonResponse addMenu(MenuRequest request);

    CommonResponse getAllMenu();

    CommonResponse getAllMenuByKeyword(String keyword);

    CommonResponse updatedMenuById(MenuRequest request) throws BadRequestException;

    CommonResponse deleteMenuById(String id);
}
