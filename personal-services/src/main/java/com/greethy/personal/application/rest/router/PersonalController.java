package com.greethy.personal.application.rest.router;

import com.greethy.annotation.hexagonal.ApplicationAdapter;
import com.greethy.personal.application.rest.request.ProfileRequest;
import com.greethy.personal.domain.port.inbound.CreateUserProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ApplicationAdapter
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/personal")
public class PersonalController {

    private final CreateUserProfileUseCase getUserProfileUseCase;

    @PostMapping("/profile/{id}")
    public ResponseEntity<?> createProfile(@PathVariable String id,
                                           @RequestBody ProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getUserProfileUseCase.createNewProfile(id, request));
    }

//    @GetMapping("/profile/{id}")
//    public ResponseEntity<?> readProfile(@PathVariable String id) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(personalService.getUserProfile(id));
//    }

}