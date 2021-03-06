/*
 * Class: CompanyRepository
 *
 * Created on Feb 26, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.repository;

import org.springframework.data.repository.CrudRepository;

import com.sps.vn.entities.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>{

    
    
}
