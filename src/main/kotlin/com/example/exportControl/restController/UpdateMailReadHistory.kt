package com.example.exportControl.restController

import com.example.exportControl.model.MailHistory
import com.example.exportControl.repository.MailReadHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@CrossOrigin(origins = arrayOf("*"))
class UpdateMailReadHistory {
    @Autowired
    private val MailReadHistoryRepo: MailReadHistoryRepository? = null

    @GetMapping(value = ["/updateMailReadHistory"])
    fun getMyAjaxMessage(@RequestParam(value = "historymailId") historymailId: Int?, principal: Principal): String {
        var userCode = principal.name
        var historyId = MailReadHistoryRepo?.selectLastHistoryId()
        var mailIdList = MailReadHistoryRepo?.selectMailIdByUserCode(userCode)!!
        historyId = if (historyId == null) {
            1
        } else {
            historyId + 1
        }

        if (!mailIdList.contains(historymailId)) {
            var newMailReadHistory = MailHistory(historyId, principal.name, historymailId)
            MailReadHistoryRepo.save(newMailReadHistory)
        }

        return "updated"
    }
}