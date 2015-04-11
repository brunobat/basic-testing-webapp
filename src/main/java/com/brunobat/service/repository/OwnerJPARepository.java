package com.brunobat.service.repository;

import com.brunobat.model.Owner;
import com.brunobat.service.repository.base.JPARepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Typed;

/**
 * Created by Bruno Baptista on 09/04/15.
 */
@Stateless
@LocalBean
@Typed(OwnerJPARepository.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OwnerJPARepository extends JPARepository<Owner> {

    public OwnerJPARepository() {
        super(Owner.class);
    }


}
