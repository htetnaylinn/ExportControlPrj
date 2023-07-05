package com.example.exportControl.repository

import com.example.exportControl.model.ExportChatDailySave
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface DailyChatSaveRepository : CrudRepository<ExportChatDailySave,String>{

    @Query(
        value = "select t.*,(select 1 from t_export_chat_daily_save as temp join t_react_user as react on CONCAT('id',cast(temp.id as varchar(25))) = react.message_id where temp.id = t.id and react.user_code = :userCode)as react_flag,\n" +
                "(select m.user_name from t_export_chat_daily_save as temp2 join m_user as m on temp2.user_code = m.user_code  where temp2.id = t.id) as user_name \n" +
                "from t_export_chat_daily_save as t  where t.invoice_code = :invoiceCode and t.send_company_code = :companyCode order by t.id ",
        nativeQuery = true
    )
    fun selectAllMessage(@Param("userCode")userCode:String?,@Param("invoiceCode")invoiceCode:String?,@Param("companyCode")companyCode:String?): List<ExportChatDailySave>?

    @Query(
        value = "select t.*,(select 1 from t_export_chat_daily_save as temp join t_react_user as react on CONCAT('id',cast(temp.id as varchar(25))) = react.message_id where temp.id = t.id and react.user_code = :userCode)as react_flag,\n" +
                "(select m.user_name from t_export_chat_daily_save as temp2 join m_user as m on temp2.user_code = m.user_code  where temp2.id = t.id) as user_name \n" +
                "from t_export_chat_daily_save as t  where t.chat_message like %:searchMsg% and t.invoice_code = :invoiceCode and t.send_company_code = :companyCode order by t.id",
        nativeQuery = true
    )
    fun searchMessages(@Param("searchMsg")searchMsg:String?,@Param("userCode")userCode:String?,@Param("invoiceCode")invoiceCode:String?,@Param("companyCode")companyCode:String?): List<ExportChatDailySave>?

    @Query(
        value = "select id from t_export_chat_daily_save order by id desc limit 1",
        nativeQuery = true
    )
    fun selectLastMessageResourceId(): Int?

    @Query(
        value = "select\n" +
                "    t.id \n" +
                "from\n" +
                "    t_export_chat_daily_save t  \n" +

                "where\n" +
                "    t.invoice_code = :invoiceCode and t.send_company_code = :companyCode \n" +
                "order by\n" +
                "    t.id desc \n" +
                "limit\n" +
                "    1\n",
        nativeQuery = true
    )
fun selectLatestMassageByUserCode(@Param("invoiceCode")invoiceCode:String?,@Param("companyCode")companyCode:String?): Int?

}