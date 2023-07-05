package com.example.exportControl.mapper

import com.example.exportControl.model.Muser
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.sql.SQLException

class AppUserMapper : RowMapper<Muser> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Muser {
        val userCode = rs.getString("user_code")
        val userName = rs.getString("user_name")
        val encrytedPassword = rs.getString("password")
        return Muser(userCode, userName, encrytedPassword)
    }

    @Throws(SQLException::class)
    fun mapRowID(rs: ResultSet, rowNum: Int): Muser {
        val userId = rs.getString("user_code")
        return Muser(userId)
    }

    companion object {
        const val BASE_SQL //
                = "Select u.user_code,u.user_name, u.password From m_user u "
    }
}