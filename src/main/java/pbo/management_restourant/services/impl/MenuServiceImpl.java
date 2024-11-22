package pbo.management_restourant.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import pbo.management_restourant.dto.request.MenuRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.dto.response.MenuResponse;
import pbo.management_restourant.models.MenuModel;
import pbo.management_restourant.repositories.MenuRepository;
import pbo.management_restourant.services.MenuService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private static LocalDate LOCALDATE = LocalDate.now();

    @Transactional
    @Override
    public CommonResponse addMenu(MenuRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            MenuResponse menuResponse = new MenuResponse();
            validateMenuRequest(request);
            Optional<MenuModel> getMenu = menuRepository.findById(request.getMenuId());
            if (getMenu.isEmpty()) {
                MenuModel menuModel = new MenuModel();

                menuModel.setMenuId(request.getMenuId());
                menuModel.setMenuName(request.getMenuName());
                menuModel.setCategory(request.getCategory());
                menuModel.setPrice(request.getPrice());
                menuModel.setQuantity(request.getQuantity());
                menuModel.setCreatedAt(LOCALDATE);
                menuModel.setUpdatedAt(LOCALDATE);
                menuRepository.save(menuModel);

                menuResponse.setMenuId(request.getMenuId());
                menuResponse.setMenuName(request.getMenuName());
                menuResponse.setCategory(request.getCategory());
                menuResponse.setPrice(request.getPrice());
                menuResponse.setQuantity(request.getQuantity());
                menuResponse.setCreatedAt(LOCALDATE);

                response.setData(menuResponse);
                response.setMessage("New Menu Successfully added");
            } else {

                response.setData(null);
                response.setMessage("Menu already exists");
            }
        } catch (IllegalArgumentException | BadRequestException ex) {
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public CommonResponse getAllMenu() {
        CommonResponse response = new CommonResponse();

        List<MenuModel> allListMenu = menuRepository.findAll();
        if (allListMenu.isEmpty()) {
            response.setData(null);
            response.setMessage("No Menu Found");
        }
        response.setData(allListMenu);
        response.setMessage("All Menu successfully retrieved");

        return response;
    }

    @Override
    public CommonResponse getAllMenuByKeyword(String keyword) {
        CommonResponse response = new CommonResponse();
        if (keyword == null || keyword.trim().isEmpty()) {
            response.setData(null);
            response.setMessage("Please enter the keyword");
        }else {
            List<MenuModel> allMenuByKeyword = menuRepository.getAllMenuByKeyword(keyword);
            if (allMenuByKeyword.isEmpty()) {
                response.setData(null);
                response.setMessage("Menu not found");
            } else {
                response.setData(allMenuByKeyword);
                response.setMessage("All Menu successfully retrieved for keyword: " + keyword);
            }
        }
        return response;
    }

    @Transactional
    @Override
    public CommonResponse updatedMenuById(MenuRequest request) throws BadRequestException {
        CommonResponse response = new CommonResponse();
        try {
            if (request.getMenuId() == null || request.getMenuId().isEmpty()) {
                throw new BadRequestException("Menu Code cannot be null or empty");
            }

            Optional<MenuModel> getId = menuRepository.findById(request.getMenuId());
            if (getId.isPresent()) {

                Object[] updateMenu = menuRepository.updateMenu(
                        request.getMenuId(),
                        request.getMenuName(),
                        request.getCategory(),
                        request.getPrice(),
                        request.getQuantity()
                );

                if (updateMenu != null) {
                    response.setData(updateMenu[0]);
                    response.setMessage("Menu Successfully updated");
                } else {
                    response.setData(null);
                    response.setMessage("Menu update failed");
                }
            } else {
                response.setData(null);
                response.setMessage("Menu with the given code not found");
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
    public CommonResponse deleteMenuById(String id) {
        CommonResponse response = new CommonResponse();
        Optional<MenuModel> getMenu = menuRepository.findById(id);
        if (getMenu.isPresent()) {

            menuRepository.deleteById(id);
            response.setData(getMenu);
            response.setMessage("Menu Successfully deleted");
        } else {
            response.setData(getMenu);
            response.setMessage("Menu Not Found");
        }
        return response;
    }

    private void validateMenuRequest(MenuRequest request) throws BadRequestException {
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        if (request.getMenuId() == null || request.getMenuId().isEmpty()) {
            throw new BadRequestException("Menu Id cannot be null or empty");
        }
        if (request.getMenuName() == null || request.getMenuName().isEmpty()) {
            throw new BadRequestException("Menu Name cannot be null or empty");
        }
        if (request.getPrice() == null || request.getPrice().isEmpty()) {
            throw new BadRequestException("Price cannot be null or empty");
        }
        if (request.getQuantity() == null || request.getQuantity().isEmpty()) {
            throw new BadRequestException("Quantity cannot be null or empty");
        }
    }

}
