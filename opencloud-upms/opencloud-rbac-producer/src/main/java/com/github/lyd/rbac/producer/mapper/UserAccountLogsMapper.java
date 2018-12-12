package com.github.lyd.rbac.producer.mapper;


import com.github.lyd.common.mapper.CrudMapper;
import com.github.lyd.rbac.client.entity.UserAccountLogs;
import org.springframework.stereotype.Repository;

/**
 * @author liuyadu
 */
@Repository
public interface UserAccountLogsMapper extends CrudMapper<UserAccountLogs> {
}