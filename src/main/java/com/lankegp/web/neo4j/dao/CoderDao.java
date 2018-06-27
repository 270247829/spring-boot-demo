package com.lankegp.web.neo4j.dao;

import com.lankegp.web.neo4j.model.Coder;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by liugongrui on 2018/4/16.
 */
@Repository
public interface CoderDao extends GraphRepository<Coder> {

    Coder findByName(@Param("name") String name);

}
