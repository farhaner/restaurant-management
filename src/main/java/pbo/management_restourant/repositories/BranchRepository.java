package pbo.management_restourant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pbo.management_restourant.models.BranchModel;

import java.util.List;

public interface BranchRepository extends JpaRepository<BranchModel, String> {

//    List<BranchModel> findByKeyword(String keyword);
}
