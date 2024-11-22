package pbo.management_restourant.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pbo.management_restourant.dto.request.TransactionRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.dto.response.TransactionResponse;
import pbo.management_restourant.models.BranchModel;
import pbo.management_restourant.models.EmployeeModel;
import pbo.management_restourant.models.MenuModel;
import pbo.management_restourant.models.TransactionModel;
import pbo.management_restourant.repositories.BranchRepository;
import pbo.management_restourant.repositories.EmployeeRepository;
import pbo.management_restourant.repositories.MenuRepository;
import pbo.management_restourant.repositories.TransactionRepository;
import pbo.management_restourant.services.TransactionService;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BranchRepository branchRepository;
    private final MenuRepository menuRepository;
    private final EmployeeRepository employeeRepository;
    private static LocalDate LOCALDATE = LocalDate.now();

    @Override
    @Transactional
    public CommonResponse addTransaction(TransactionRequest request) {
        CommonResponse response = new CommonResponse();
        try {
            if (!isValidTransaction(request)) {
                throw new IllegalArgumentException("Transaction properties cannot be null or empty");
            }
            Optional<TransactionModel> transactionId = transactionRepository.findById(request.getTransactionId());
            if (transactionId.isPresent()) {
                response.setMessage("Transaction already exists");
                response.setData(null);
            } else {

                BranchModel branchId = branchRepository.findById(request.getBranchId()).orElseThrow(() -> new IllegalArgumentException("Branch not found"));
                EmployeeModel employeeId = employeeRepository.findById(request.getEmployeeId()).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
                MenuModel menuId = menuRepository.findById(request.getMenuId()).orElseThrow(() -> new IllegalArgumentException("Menu not found"));

                TransactionModel transactionModel = new TransactionModel();
                transactionModel.setTransactionId(request.getTransactionId());
                transactionModel.setBranch(branchId);
                transactionModel.setEmployee(employeeId);
                transactionModel.setMenu(menuId);
                transactionModel.setTransactionCreatedAt(LOCALDATE);
                transactionModel.setTransactionUpdatedAt(LOCALDATE);
                transactionRepository.save(transactionModel);

                TransactionResponse transactionResponse = new TransactionResponse();
                transactionResponse.setTransactionId(request.getTransactionId());
                transactionResponse.setEmployeeName(employeeId.getName());
                transactionResponse.setBranchName(branchId.getBranchName());
                transactionResponse.setMenu(menuId.getMenuName());
                transactionResponse.setTransactionCreatedAt(LOCALDATE);

                response.setData(transactionResponse);
                response.setMessage("Transaction added successfully");
            }
        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public CommonResponse getAllTransaction(TransactionRequest request) {
        CommonResponse response = new CommonResponse();

        Object[] allTransaction = transactionRepository.getAll(request.getTransactionId());
        if (allTransaction == null) {
            response.setData(null);
            response.setMessage("No transactions found");
        }
        response.setData(allTransaction);
        response.setMessage("All transactions found");
        return response;
    }

    @Override
    public CommonResponse deleteTransactionById(String id) {
        CommonResponse response = new CommonResponse();
        transactionRepository.deleteById(id);
        Optional<TransactionModel> findById = transactionRepository.findById(id);
        if (findById.isPresent()) {
            response.setData(findById.get());
            response.setMessage("Transaction deleted failed");
        } else {
            response.setData(null);
            response.setMessage("Transaction deleted successfully");
        }
        return response;
    }

    public boolean isValidTransaction(TransactionRequest transaction) {
        return transaction.getTransactionId() != null && !transaction.getTransactionId().isEmpty() &&
                transaction.getBranchId() != null && !transaction.getBranchId().isEmpty() &&
                transaction.getEmployeeId() != null && !transaction.getEmployeeId().isEmpty() &&
                transaction.getMenuId() != null && !transaction.getMenuId().isEmpty();
    }

}