package ru.gtatarnikov.skyeng.testtask.exception;

public interface MailingExceptionMessages {
    String NOT_NULL_ID = "Создаваемая сущность не должна иметь уникальный идентификатор ";
    String NOT_FOUND_ID = "Не найден уникальной идентификатор ";
    String NOT_FOUND_MAILING = "Посылка не найдена ";
    String NOT_FOUND_POSTAL_OFFICE = "Почтовое отделение не найдено ";
    String MAILING_ALREADY_RECEIVED = "Посылка уже принята получателем ";
    String MAILING_STILL_IN_WAYPOINT = "Посылка ещё не покинула почтовое отделение ";
    String MAILING_ALREADY_LEFT_WAYPOINT = "Посылка уже покинула почтовое отделение ";
    String MAILING_NOT_IN_WAYPOINT = "Посылка ещё не поступила в почтовое отделение ";
    String MAILING_ALREADY_IN_WAYPOINT = "Посылка уже поступила в почтовое отделение ";
    String MAILING_NOT_ARRIVED_FIRST = "Посылка ещё не прибыла в изначальное почтовое отделение ";
    String MAILING_MOVEMENT_IS_EMPTY = "История перемещений посылки пуста ";
    String UNKNOWN_EXCEPTION = "Неизвестная ошибка ";
}
