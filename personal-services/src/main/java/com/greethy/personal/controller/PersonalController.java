package com.greethy.personal.controller;

import com.greethy.personal.dto.ProfileRequest;
import com.greethy.personal.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/personal")
public class PersonalController {

    private final PersonalService personalService;

    @PostMapping("/profile/{id}")
    public ResponseEntity<?> createProfile(@PathVariable String id,
                                           @RequestBody ProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personalService.createNewProfile(id, request));
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> readProfile(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(personalService.getUserProfile(id));
    }

}
