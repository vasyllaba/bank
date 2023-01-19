package com.solvd.bank.dao;

import com.solvd.bank.models.Deposit;

import java.util.List;

public interface IDepositDAO extends IBaseDAO<Deposit>{

    List<Deposit> getByCardId(long id);
}
