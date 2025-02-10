package com.myprojects.LMS.Controllers;

import com.myprojects.LMS.DTOs.RequestBooksDTO;
import com.myprojects.LMS.Services.RequestBooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestBooksController {
    @Autowired
    private RequestBooksServices requestBooksServices;
    // do a request
    // view all requests
    @GetMapping("/getRequests")
    public ResponseEntity<List<RequestBooksDTO>> retrieveAllRequestBooks(){
        return requestBooksServices.retrieveAllRequestBooks();
    }
    @PostMapping("/request/user/{uid}/book/{bid}")
    public ResponseEntity<String> requestBook(@PathVariable int uid,@PathVariable int bid){
        return requestBooksServices.createRequest(uid, bid);
    }
    @DeleteMapping("/request/{rid}")
    public ResponseEntity<String> deleteRequestBook(@PathVariable int rid){
        return requestBooksServices.deleteRequests(rid);
    }
}
