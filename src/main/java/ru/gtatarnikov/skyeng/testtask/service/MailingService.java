package ru.gtatarnikov.skyeng.testtask.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gtatarnikov.skyeng.testtask.exception.MailingException;
import ru.gtatarnikov.skyeng.testtask.model.dto.MailingDto;
import ru.gtatarnikov.skyeng.testtask.model.dto.MovementDto;
import ru.gtatarnikov.skyeng.testtask.model.entity.Mailing;
import ru.gtatarnikov.skyeng.testtask.model.entity.Movement;
import ru.gtatarnikov.skyeng.testtask.model.entity.PostalOffice;
import ru.gtatarnikov.skyeng.testtask.model.enumeration.Status;
import ru.gtatarnikov.skyeng.testtask.model.mapper.MailingMapper;
import ru.gtatarnikov.skyeng.testtask.model.mapper.MovementMapper;
import ru.gtatarnikov.skyeng.testtask.repository.MailingRepository;
import ru.gtatarnikov.skyeng.testtask.repository.MovementRepository;
import ru.gtatarnikov.skyeng.testtask.repository.PostalOfficeRepository;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static ru.gtatarnikov.skyeng.testtask.exception.MailingExceptionMessages.*;

@Service
public class MailingService {
    private static final String ACTION_ARRIVED = "Arrived";
    private static final String ACTION_LEFT = "Left";
    private static final String ACTION_RECEIVED = "Received";

    @Autowired
    private MailingRepository mailingRepository;

    @Autowired
    private PostalOfficeRepository postalOfficeRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private MailingMapper mailingMapper;

    @Autowired
    private MovementMapper movementMapper;

    @Transactional
    public MailingDto register(MailingDto dto) {
        if (dto.getId() != null) {
            throw new MailingException(NOT_NULL_ID);
        }

        Mailing mailingEntity = new Mailing();
        mailingEntity.setType(dto.getType());
        mailingEntity.setRecipientIndex(dto.getRecipientIndex());
        mailingEntity.setRecipientName(dto.getRecipientName());
        mailingEntity.setRecipientAddress(dto.getRecipientAddress());
        mailingEntity.setStatus(Status.REGISTERED);
        mailingEntity = mailingRepository.save(mailingEntity);
        return mailingMapper.toDto(mailingEntity);
    }

    @Transactional
    public MailingDto arriving(Long mailingId, Long postalOfficeId) {
        if (isNull(mailingId) || isNull(postalOfficeId)) {
            throw new MailingException(NOT_FOUND_ID);
        }

        Mailing mailingEntity = getMailingEntity(mailingId);

        if (!mailingEntity.getStatus().equals(Status.LEFT_WAYPOINT) &&
                !mailingEntity.getStatus().equals(Status.REGISTERED)) {
            if (mailingEntity.getStatus().equals(Status.RECEIVED)) {
                throw new MailingException(MAILING_ALREADY_RECEIVED);
            }
            if (mailingEntity.getStatus().equals(Status.ARRIVED_TO_WAYPOINT)) {
                throw new MailingException(MAILING_ALREADY_IN_WAYPOINT);
            }
            throw new MailingException(MAILING_STILL_IN_WAYPOINT);
        }

        PostalOffice postalOfficeEntity = getPostalOfficeEntity(postalOfficeId);

        postalOfficeEntity.setRecipientAddress(mailingEntity.getRecipientAddress());
        mailingEntity.setStatus(Status.ARRIVED_TO_WAYPOINT);
        mailingRepository.saveAndFlush(mailingEntity);

        saveMovement(mailingEntity, postalOfficeEntity, ACTION_ARRIVED);

        return mailingMapper.toDto(mailingEntity);
    }

    @Transactional
    public MailingDto left(Long mailingId) {
        if (isNull(mailingId))
            throw new MailingException(NOT_FOUND_ID);

        Mailing mailingEntity = getMailingEntity(mailingId);

        if (!mailingEntity.getStatus().equals(Status.ARRIVED_TO_WAYPOINT) &&
                !mailingEntity.getStatus().equals(Status.REGISTERED)) {
            if (mailingEntity.getStatus().equals(Status.RECEIVED)) {
                throw new MailingException(MAILING_ALREADY_RECEIVED);
            }
            if (mailingEntity.getStatus().equals(Status.LEFT_WAYPOINT)) {
                throw new MailingException(MAILING_ALREADY_LEFT_WAYPOINT);
            }
            throw new MailingException(MAILING_NOT_IN_WAYPOINT);
        }

        mailingEntity.setStatus(Status.LEFT_WAYPOINT);
        mailingEntity.setRecipientAddress(null);

        saveMovement(mailingEntity, ACTION_ARRIVED, ACTION_LEFT);

        mailingEntity = mailingRepository.save(mailingEntity);

        return mailingMapper.toDto(mailingEntity);
    }

    @Transactional
    public MailingDto receiving(Long mailingId) {
        if (isNull(mailingId))
            throw new MailingException(NOT_FOUND_ID);

        Mailing mailingEntity = getMailingEntity(mailingId);

        if (mailingEntity.getStatus().equals(Status.RECEIVED))
            throw new MailingException(MAILING_ALREADY_RECEIVED);
        if (!mailingEntity.getStatus().equals(Status.ARRIVED_TO_WAYPOINT))
            throw new MailingException(MAILING_NOT_IN_WAYPOINT);

        mailingEntity.setStatus(Status.RECEIVED);

        mailingEntity = mailingRepository.saveAndFlush(mailingEntity);

        saveMovement(mailingEntity, ACTION_ARRIVED, ACTION_RECEIVED);

        return mailingMapper.toDto(mailingEntity);
    }

    public List<MovementDto> getHistory(Long mailingId) {
        if (isNull(mailingId))
            throw new MailingException(NOT_FOUND_ID);
        if (!mailingRepository.existsById(mailingId))
            throw new MailingException(NOT_FOUND_MAILING);

        return movementMapper.toDtoList(getMovements(mailingId));
    }

    private Mailing getMailingEntity(Long mailingId) {
        return mailingRepository.findById(mailingId)
                .orElseThrow(() -> new MailingException(NOT_FOUND_MAILING));
    }

    private PostalOffice getPostalOfficeEntity(Long postalOfficeId) {
        return postalOfficeRepository.findById(postalOfficeId)
                .orElseThrow(() -> new MailingException(NOT_FOUND_POSTAL_OFFICE));
    }

    private List<Movement> getMovements(Long mailingId) {
        return movementRepository.findAllByMailingId(mailingId)
                .orElseThrow(() -> new MailingException(MAILING_MOVEMENT_IS_EMPTY));
    }

    private PostalOffice getLastActionPostalOffice(Long mailingId, String action) {
        return postalOfficeRepository.findById(movementRepository.getLatestPostalOfficeIdByMailingIdAndAction(mailingId, action))
                .orElseThrow(() -> new MailingException(NOT_FOUND_POSTAL_OFFICE));
    }

    private void saveMovement(Mailing mailingEntity, PostalOffice postalOfficeEntity, String currentAction) {
        Movement movementEntity = new Movement();
        movementEntity.setMailing(mailingEntity);
        movementEntity.setPostalOffice(postalOfficeEntity);
        movementEntity.setMovementDateTime(LocalDateTime.now());
        movementEntity.setAction(currentAction);
        movementRepository.save(movementEntity);
    }

    private void saveMovement(Mailing mailingEntity, String actionBefore, String currentAction) {
        Movement movement = new Movement();
        movement.setMailing(mailingEntity);
        movement.setPostalOffice(getLastActionPostalOffice(mailingEntity.getId(), actionBefore));
        movement.setMovementDateTime(LocalDateTime.now());
        movement.setAction(currentAction);
        movementRepository.save(movement);
    }
}
