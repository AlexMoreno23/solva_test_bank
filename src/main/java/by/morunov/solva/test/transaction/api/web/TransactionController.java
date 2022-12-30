package by.morunov.solva.test.transaction.api.web;

import by.morunov.solva.test.transaction.api.model.entity.Transaction;
import by.morunov.solva.test.transaction.api.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Alex Morunov
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @ApiOperation(value = "Receive and save transaction", notes = "Receive and save transaction if currency short name is" +
            "KZT. Also You can add limit for you spend at USD in different categories. There are two categories of spending: PRODUCT and " +
            "SERVICE")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 404, message = "Not saved - Check your input data")
    })
    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@Valid @RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }


}
