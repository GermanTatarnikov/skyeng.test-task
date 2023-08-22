package ru.gtatarnikov.skyeng.testtask.rest.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gtatarnikov.skyeng.testtask.model.dto.MailingDto;

public interface MailingController {
    @PostMapping("/register")
    ResponseEntity<?> registerMailing(@RequestBody MailingDto mailing);

    @PutMapping("/{mailingId}/arriving_to/{postalOfficeId}")
    ResponseEntity<?> arrivedToWaypoint(@PathVariable Long mailingId, @PathVariable Long postalOfficeId);

    @PutMapping("/{id}/left_waypoint")
    ResponseEntity<?> leftWaypoint(@PathVariable Long id);

    @PutMapping("/{id}/receiving")
    ResponseEntity<?> receiving(@PathVariable Long id);

    @GetMapping("/{id}/history")
    ResponseEntity<?> getHistory(@PathVariable Long id);
}
