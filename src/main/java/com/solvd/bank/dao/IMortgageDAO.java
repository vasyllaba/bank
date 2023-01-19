package com.solvd.bank.dao;

import com.solvd.bank.models.Mortgage;

import java.util.List;

public interface IMortgageDAO extends IBaseDAO<Mortgage>{

    List<Mortgage> getByCardId(long id);
}
