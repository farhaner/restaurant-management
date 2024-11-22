package pbo.management_restourant.services;

import pbo.management_restourant.dto.request.TransactionRequest;
import pbo.management_restourant.dto.response.CommonResponse;

public interface TransactionService {

    CommonResponse addTransaction(TransactionRequest request);

    CommonResponse getAllTransaction(TransactionRequest request);

//    CommonResponse getAllTransactionByKeyword(String keyword);
//
//    CommonResponse updatedTransactionById(TransactionRequest request) throws BadRequestException;

    CommonResponse deleteTransactionById(String id);
}
