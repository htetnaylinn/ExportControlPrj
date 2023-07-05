package com.example.exportControl.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_react_user")
class ReactUser {
    @Id
    @Column(name = "id")
    var id : Int? = null

    @Column(name = "message_id")
    var messageId : String? = null

    @Column(name = "user_code")
    var userCode : String? = null

    @Column(name = "updated_by")
    var updatedBy : String? = null

    @Column(name = "updated_dt")
    var updatedDt : Timestamp? = null




    constructor()
    constructor(id: Int?, messageId: String?, userCode: String?, updatedBy: String?, updatedDt: Timestamp?) {
        this.id = id
        this.messageId = messageId
        this.userCode = userCode
        this.updatedBy = updatedBy
        this.updatedDt = updatedDt
    }


}