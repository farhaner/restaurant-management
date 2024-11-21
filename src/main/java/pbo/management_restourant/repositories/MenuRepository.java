package pbo.management_restourant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pbo.management_restourant.models.MenuModel;

public interface MenuRepository extends JpaRepository<MenuModel, String> {

//    List<MenuModel> findByKeyword(String keyword);
}
