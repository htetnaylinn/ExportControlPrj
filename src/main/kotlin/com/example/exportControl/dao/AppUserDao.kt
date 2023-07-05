package com.example.exportControl.dao

import com.example.exportControl.mapper.AppUserMapper
import com.example.exportControl.model.Muser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.support.JdbcDaoSupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource


@Repository
@Transactional
class AppUserDao : JdbcDaoSupport() {

    @Autowired
    fun AppUserDao(dataSource: DataSource?) {
        setDataSource(dataSource!!)
    }

    fun findUserAccount(userCode: String): Muser? {
        println(userCode)
        val sql = AppUserMapper.BASE_SQL + " where u.user_code = ? "

        val params = arrayOf<Any>(userCode)
        //println(params[0])
        val mapper = AppUserMapper()
        return try {
            jdbcTemplate!!.queryForObject(sql, params, mapper)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}