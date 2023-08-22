package ru.gtatarnikov.skyeng.testtask.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gtatarnikov.skyeng.testtask.model.dto.MailingDto;
import ru.gtatarnikov.skyeng.testtask.rest.api.MailingController;
import ru.gtatarnikov.skyeng.testtask.service.MailingService;

@RestController
@RequestMapping("/mailing")
public class MailingControllerImpl implements MailingController {
    @Autowired
    private MailingService mailing;

    @Override
    public ResponseEntity<?> registerMailing(MailingDto dto) {
        return ResponseEntity.ok(mailing.register(dto));
    }

    @Override
    public ResponseEntity<?> arrivedToWaypoint(Long mailingId, Long postalOfficeId) {
        return ResponseEntity.ok(mailing.arriving(mailingId, postalOfficeId));
    }

    @Override
    public ResponseEntity<?> leftWaypoint(Long id) {
        return ResponseEntity.ok(mailing.left(id));
    }

    @Override
    public ResponseEntity<?> receiving(Long id) {
        return ResponseEntity.ok(mailing.receiving(id));
    }

    @Override
    public ResponseEntity<?> getHistory(Long id) {
        return ResponseEntity.ok(mailing.getHistory(id));
    }
}
