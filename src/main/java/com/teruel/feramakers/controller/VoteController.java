package com.teruel.feramakers.controller;

import com.teruel.feramakers.dto.VoteDTO;
import com.teruel.feramakers.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote (@RequestBody VoteDTO voteDTO){
        System.out.println("dentro de vote controller");

        voteService.vote(voteDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
