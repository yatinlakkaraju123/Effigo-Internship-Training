package com.myprojects.LMS.Controllers;

import com.myprojects.LMS.DTOs.BorrowDTO;
import com.myprojects.LMS.Services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @GetMapping("/getBorrows")
    public ResponseEntity<List<BorrowDTO>> retrieveAllBorrows(){
        return borrowService.retrieveAllBorrows();
    }

    @PostMapping("borrow/users/{uid}/books/{bid}")
    public ResponseEntity<String> borrow(@PathVariable int uid, @PathVariable int bid
            , @RequestBody BorrowDTO borrowDTO){
        return borrowService.borrow(uid,bid,borrowDTO);
    }
    @PostMapping("return/borrow/{bid}")
    public ResponseEntity<String> returned(@PathVariable int bid
            , @RequestBody BorrowDTO borrowDTO){
        return borrowService.returned(bid,borrowDTO);
    }
}
