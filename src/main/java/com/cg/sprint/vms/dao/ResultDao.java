package com.cg.sprint.vms.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.sprint.vms.entity.Result;

/**
 * @author sadee
 * Date-2021/02/16
 * Method- Dao Interface
 * description- extending CrudRepository to perform CRUD Operations.
 */
@Repository
public interface ResultDao extends CrudRepository<Result, Integer>
{

}
