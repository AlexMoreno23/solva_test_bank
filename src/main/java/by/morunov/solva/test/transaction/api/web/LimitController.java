package by.morunov.solva.test.transaction.api.web;

import by.morunov.solva.test.transaction.api.model.entity.UsdLimit;
import by.morunov.solva.test.transaction.api.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alex Morunov
 */
@RestController
@RequestMapping("limit")
public class LimitController {

    @Autowired
    TransactionService transactionService;

    @ApiOperation(value = "Get all limits by account ID", notes = "Return limits as per account ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - Limits was not found")
    })
    @GetMapping("/all/{accountId}")
    public ResponseEntity<List<UsdLimit>> getAllLimits(@PathVariable @ApiParam(name = "accountID"
    , value = "account id", example = "0000000001", required = true) String accountId){
        List<UsdLimit> limits = transactionService.getAllLimits(accountId);
        return new ResponseEntity<>(limits, HttpStatus.OK);
    }
}
