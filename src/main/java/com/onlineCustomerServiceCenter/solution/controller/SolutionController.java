package com.onlineCustomerServiceCenter.solution.controller;

import com.onlineCustomerServiceCenter.solution.dto.SolutionIdDto;
import com.onlineCustomerServiceCenter.solution.exceptions.SolutionException;
import com.onlineCustomerServiceCenter.solution.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @PostMapping("/solution/accept/{issueId}")
    public ResponseEntity<String> acceptSolution(@PathVariable Integer issueId, @RequestBody SolutionIdDto solutionId) {
        try {
            String result = solutionService.acceptSolution(issueId, solutionId.getSolutionId());
            return ResponseEntity.ok(result);
        } catch (SolutionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/solution/reject/{issueId}")
    public ResponseEntity<String> rejectSolution(@PathVariable Integer issueId, @RequestBody SolutionIdDto solutionId) {
        try {
            String result = solutionService.rejectSolution(issueId, solutionId.getSolutionId());
            return ResponseEntity.ok(result);
        } catch (SolutionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

