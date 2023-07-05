package com.example.exportControl.messagingstompwebsocket

import com.example.exportControl.config.WebConfiguration
import com.example.exportControl.model.ExportChatDailySave
import com.example.exportControl.model.ExportChatDailySaveOrg
import com.example.exportControl.model.ReactUser
import com.example.exportControl.repository.DailyChatSaveRepository
import com.example.exportControl.repository.DailyChatSaveRepositoryOrg
import com.example.exportControl.repository.ReactUserRepository
import com.example.exportControl.repository.UserMasterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils
import java.io.ByteArrayInputStream
import java.security.Principal
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Controller
class GreetingController {

    @Autowired
    private val usermasterRepo: UserMasterRepository? = null

    @Autowired
    private val dailyChatSaveRepo: DailyChatSaveRepository? = null

    @Autowired
    private val dailyChatSaveRepoOrg: DailyChatSaveRepositoryOrg? = null

    @Autowired
    private  val reactUserRepo: ReactUserRepository? = null

    @Autowired
    private val webConfig: WebConfiguration? = null

    @MessageMapping("/{cname}/{invoiceCode}")
    @SendTo("/topic/{cname}/{invoiceCode}")
    @Throws(Exception::class)
    fun greeting(message: HelloMessage, @DestinationVariable cname:String?,@DestinationVariable invoiceCode:String?,principal: Principal): Greeting {
        val userCode = principal.name
        val user = usermasterRepo?.selectUserByUserCode(userCode)
        val timeNow = LocalDateTime.now()
        val upTimeStamp: Timestamp = Timestamp.valueOf(timeNow)
        var messageId:Int = 0 ;
        var reactUserId: Int = 0;
        var latestId = dailyChatSaveRepo?.selectLastMessageResourceId();
        var reactlastId = reactUserRepo?.selectLastReactId();
        messageId = if(latestId == null){1}else {latestId+1}
        reactUserId = if(reactlastId == null){1}else {reactlastId+1}

        val date = Date(upTimeStamp.getTime())
        val pattern = "MM/dd HH:mm"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val current_date = simpleDateFormat.format(upTimeStamp)
        var messageContent:String? = ""
        var reactCount:Int? = -1;
        var sendImg = false
        var sendText = false

        if(message.image == null){
            message.image = ""
        }else{
            sendImg = true;
        }

        if(message.reactMessageId == null){
            message.reactMessageId = ""
        }

        if(message.userCode == null){
            message.userCode = ""
        }

        if(message.reactMessageId !="" && message.userCode != ""){
            var messageId = message.reactMessageId!!.filter { it.isDigit() }.toInt()

            when(message.reactionStatus){
                "LIKE" -> {
                    val newReactPerson = ReactUser(reactUserId,message.reactMessageId,message.userCode,message.userCode,upTimeStamp)
                    reactUserRepo?.save(newReactPerson)
                    dailyChatSaveRepoOrg?.plusReactionCount(messageId)
                }

                "DISLIKE" -> {
                    reactUserRepo?.removeReaction(message.reactMessageId,message.userCode)
                    dailyChatSaveRepoOrg?.minusReactionCount(messageId)
                }
            }
            reactCount = dailyChatSaveRepoOrg?.selectAllReactCountById(messageId)
        }
        if(message.name == null){
            message.name = ""
        }else{
            sendText = true
//            messageContent = current_date.toString() +"&emsp;"+user?.userName + "&nbsp;:&nbsp;"+ HtmlUtils.htmlEscape(message.name!!)
        }

        if(message.reactionStatus === null){
            message.reactMessageId = "id"+messageId
            val newMessage =  ExportChatDailySaveOrg(messageId,invoiceCode,user?.companyCode,"000000", user?.userId, message.name,message.image!!.toByteArray(),0,upTimeStamp,user?.userId,upTimeStamp,user?.userId,upTimeStamp)
            dailyChatSaveRepoOrg?.save(newMessage)
        }


        messageContent = current_date.toString() +"&emsp;"+user?.userName + "&nbsp;:&nbsp;"+ HtmlUtils.htmlEscape(message.name!!)

        return Greeting(messageContent,HtmlUtils.htmlEscape(message.image!!),message.reactMessageId,message.userCode,reactCount,sendImg,sendText,principal.name,invoiceCode)
    }


//    @MessageMapping("/{cname}/{invoiceCode}")
//    @SendTo("/topic/{cname}")
//    @Throws(Exception::class)
//    fun sendNotification(message: HelloMessage, @DestinationVariable cname:String?,@DestinationVariable invoiceCode:String?,principal: Principal): Notification {
//    return Notification(invoiceCode)
//    }
}