package solvd.laba.ermakovich.hf.service;

import solvd.laba.ermakovich.hf.domain.Account;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountService {

    Account getByExternalId(UUID employeeId);

    Account create(UUID employeeUuid);

}
