package com.example.exportControl.restController


import com.example.exportControl.model.LatestChatRead
import com.example.exportControl.repository.DailyChatSaveRepository
import com.example.exportControl.repository.LatestChatReadRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.sql.Timestamp
import java.time.LocalDateTime


@RestController
@CrossOrigin(origins = arrayOf("*"))
class UpdateChatLatestRead  {
    @Autowired
    private val DailyChatSaveRepo: DailyChatSaveRepository? = null
    @Autowired
    private val LatestChatReadRepo: LatestChatReadRepository? = null

    @Autowired
    private val userMasterRepo: UserMasterRepository? = null

    @GetMapping(value = ["/updateLatestReadMessage"])
    fun updateChatLatestRead(@RequestParam(value="invoiceCode")invoiceCode:String?,principal: Principal):String {
        var loginUserCode = principal.name
        val user = userMasterRepo?.selectUserByUserCode(loginUserCode)
        var latestMessageId = DailyChatSaveRepo?.selectLatestMassageByUserCode(invoiceCode,user?.companyCode)
        latestMessageId = if(latestMessageId == null){0}else {latestMessageId}

        var latestId = LatestChatReadRepo?.selectLatestId()
        latestId = if(latestId == null){1}else {latestId+1}

        var saveNew : Boolean =if(LatestChatReadRepo?.getAllRecordByUserCodeAndInvoice(loginUserCode,invoiceCode,user?.companyCode)!!.isEmpty()){true} else {false}

        var currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: Timestamp = Timestamp.valueOf(currentDateTime)
        if(saveNew){
            var latestChatRead = LatestChatRead(latestId,loginUserCode,invoiceCode,user?.companyCode,latestMessageId,currentTime);
            LatestChatReadRepo?.save(latestChatRead)
        }else{
            LatestChatReadRepo?.updateMessageId(latestMessageId,loginUserCode,invoiceCode,user?.companyCode)
        }

        return "updated"
    }
}