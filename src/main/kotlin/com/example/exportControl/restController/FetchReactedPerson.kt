package com.example.exportControl.restController

import com.example.exportControl.message.ReactedPerson
import com.example.exportControl.repository.ReactUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

@RestController
class FetchReactedPerson {

    @Autowired
    private val reactuserRepo: ReactUserRepository? = null

    @GetMapping(value = ["fetchReactedPerson/messageId"])
    fun getMyAjaxMessage(@RequestParam(value = "messageId") messageId: String?): ReactedPerson {
        var reactedPerson: List<String>? = null

        reactedPerson = reactuserRepo?.fetchReactedPerson(messageId)
        if(reactedPerson?.size == 0){
            reactedPerson = null
        }
        return ReactedPerson(reactedPerson)
    }

    @GetMapping(value = ["/check-session"])
    fun checkSession(request: HttpServletRequest): Array<Cookie> {
        var h = HttpStatus.OK
        val cookies: Array<Cookie> = request.cookies
        return cookies
    }
}