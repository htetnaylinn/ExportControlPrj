package com.example.exportControl.restController


import com.example.exportControl.model.ScheduleControl
import com.example.exportControl.model.UserControl
import com.example.exportControl.repository.ScheduleControlRepository
import com.example.exportControl.repository.UserControlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal


@RestController
@CrossOrigin(origins = arrayOf("*"))
class UserMasterRestController  {

    @Autowired
    private val userControlRepo: UserControlRepository? = null

    @Autowired
    private val scheduleControlRepo: ScheduleControlRepository? = null

    @PostMapping(value = ["/getScheduleControlByUserId"])
    fun getScheduleControl(@RequestParam(value="userCode")userCode:String?,principal: Principal): ScheduleControl? {

        var scheduleControl = scheduleControlRepo?.selectAllByCompanyCode(userCode);

        return scheduleControl
    }

    @PostMapping(value = ["/getUserControlByUserId"])
    fun getUserControl(@RequestParam(value="userCode")userCode:String?,principal:Principal):UserControl?{
        var code = userCode;
        var userControl = userControlRepo?.selectAllByUserId(userCode)

        return userControl

    }
}