package pbo.management_restourant.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pbo.management_restourant.dto.request.TransactionRequest;
import pbo.management_restourant.dto.response.CommonResponse;
import pbo.management_restourant.services.TransactionService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(value = "/create")
    public CommonResponse creatTransaction(@RequestBody TransactionRequest request) {
        return transactionService.addTransaction(request);
    }
//
//    @GetMapping(value = "/{keyword}")
//    public CommonResponse getTransactionByKeywords(@PathVariable String keyword) {
//        return transactionService.getAllTransactionByKeyword(keyword);
//    }
//
//    @PostMapping(value = "/get")
//    public CommonResponse getTransactionByKeyword(@RequestBody TransactionRequest request) {
//        return transactionService.getAllTransactionByKeyword(request.getKeyword());
//    }

    @GetMapping
    public CommonResponse getAllTransaction(@RequestBody TransactionRequest id) {
        return transactionService.getAllTransaction(id);
    }
//
//    @PutMapping(value = "/update")
//    public CommonResponse updateTransaction(@RequestBody TransactionRequest request) throws BadRequestException {
//        return transactionService.updatedTransactionById(request);
//    }

    @DeleteMapping(value = "/{id}")
    public CommonResponse deleteTransaction(@PathVariable String id) {
        return transactionService.deleteTransactionById(id);
    }
}
