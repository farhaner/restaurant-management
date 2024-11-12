package pbo.management_restourant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pbo.management_restourant.models.BranchModel;

public interface BranchRepository extends JpaRepository<BranchModel, String> {
}
