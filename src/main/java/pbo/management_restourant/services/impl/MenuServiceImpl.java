package pbo.management_restourant.services.impl;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pbo.management_restourant.dto.request.MenuRequest;
import pbo.management_restourant.dto.response.MenuResponse;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.models.MenuModel;
import pbo.management_restourant.repositories.MenuRepository;
import pbo.management_restourant.services.MenuService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Transactional
    public CommonResponse addMenu(MenuRequest request) {
        CommonResponse response = new CommonResponse();

//        try {
        Optional<MenuModel> getMenu = menuRepository.findByCode(request.getMenuCode());

        if (getMenu.isEmpty()) {

            MenuModel menuModel = new MenuModel();
            menuModel.setmenuCode(request.getmenuCode());
            menuModel.setmenuName(request.getmenuName());
            menuModel.setcategory(request.getcategory());
            menuModel.setamount(request.getamount());
            MenuRepository.save(MenuModel);


            MenuResponse menuResponse = new MenuResponse();
            menuResponse.setmenuCode(request.getmenuCode());
            menuResponse.setmenuName(request.getmenuName());
            menuResponse.setcategory(request.getcategory());
            menuResponse.setamount(request.getamount());

//            menuResponse.setCreatedAt();

            response.setData(menuResponse);
            response.setMessage("New Menu Successfully added");

        } else {
            response.setData(request);
            response.setMessage("Failed Add a New Menu ");
        }

//        } catch (Exception e) {
//            log.error("Created Menu Failed: ", e);
//        }
        return response;
    }

//    @Override
//    public List<CommonResponse> findByKeyword(String keyword) {
//        List<MenuResponse> getMenuByKeyword = menuRepository.findByKeyword(keyword);
//
//        CommonResponse response = new CommonResponse();
//
//        response.setData(getMenuByKeyword);
//        response.setMessage("Found " + getMenuByKeyword.size() + " menues");
//
//        return Collections.singletonList(response);
//    }

    @Override
    public CommonResponse getMenuByCode(String code) {
        CommonResponse response = new CommonResponse();

        Optional<MenuModel> getMenu = menuRepository.findByCode(code);
//        if (getMenu != null) {
        response.setData(getMenu);
        response.setMessage("Menu Successfully retrieved");
//        } else {
//            response.setData(null);
//            response.setMessage("Failed Retrieving Menu");
//        }
        return response;
    }

    @Override
    public List<CommonResponse> getAllMenu() {
        CommonResponse response = new CommonResponse();

        List<MenuModel> allMenu = menuRepository.findAll();

        response.setData(allMenu);
        response.setMessage("All Menu Successfully retrieved");

        return Collections.singletonList(response);
    }

    public CommonResponse updateMenu(MenuRequest request) {
        CommonResponse response = new CommonResponse();
        return null;
    }

    @Override
    public CommonResponse deleteMenuByCode(String code) {
        CommonResponse response = new CommonResponse();
        Optional<MenuModel> getMenu = menuRepository.findByCode(code);
        if (getMenu.isPresent()) {

            menuRepository.deleteByCode(code);
            response.setData(getMenu);
            response.setMessage("Menu Successfully deleted");
        } else {
            response.setData(getMenu);
            response.setMessage("Menu Not Found");
        }
        return response;
    }
}
