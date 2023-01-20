package com.solvd.bank.dao;

import com.solvd.bank.models.Credit;

import java.util.List;

public interface ICreditDAO extends IBaseDAO<Credit> {

    List<Credit> getCreditsByCardId(long id);
    List<Credit> getCreditsByClientId(long id);
}
