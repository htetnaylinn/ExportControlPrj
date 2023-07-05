package com.example.exportControl.utils

import org.apache.commons.logging.LogFactory
import org.springframework.security.crypto.password.PasswordEncoder
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

object AESUtils:PasswordEncoder{

//    var str:String? = null
//
//    var newStr:String = ""

    private val logger = LogFactory.getLog(javaClass)

    fun encrypt(strToEncrypt: String): String? {
        var newStr:String = ""

        val array = Array(strToEncrypt.length) {strToEncrypt[it].toString()}
        array.forEach { println(it) }
        for (e in array) {
            var value = e


            when (value) {
                "a" -> {
                    newStr += "{"
                }

                "b" -> {
                    newStr += "}"
                }

                "c" -> {
                    newStr += "#"
                }

                "d" -> {
                    newStr += "~"
                }

                "e" -> {
                    newStr += "+"
                }

                "f" -> {
                    newStr += "-"
                }

                "g" -> {
                    newStr += "@"
                }

                "h" -> {
                    newStr += "*"
                }

                "i" -> {
                    newStr += "/"

                }

                "j" -> {
                    newStr += "\\"
                }

                "k" -> {
                    newStr += "?"
                }

                "l" -> {
                    newStr += "$"
                }

                "m" -> {
                    newStr += "!"
                }

                "n" -> {
                    newStr += "^"
                }

                "o" -> {
                    newStr += "("

                }

                "p" -> {
                    newStr += ")"

                }

                "q" -> {
                    newStr += "<"

                }

                "r" -> {
                    newStr += ">"

                }

                "s" -> {
                    newStr += "="

                }

                "t" -> {
                    newStr += ";"

                }

                "u" -> {
                    newStr += ","

                }

                "v" -> {
                    newStr += "_"

                }

                "w" -> {
                    newStr += "["

                }

                "x" -> {
                    newStr += "]"

                }

                "y" -> {
                    newStr += ":"

                }

                "z" -> {
                    newStr += "\""

                }

                " " -> {
                    newStr += " "

                }

                "." -> {
                    newStr += "ﻂ"

                }

                "," -> {
                    newStr += "ﮏ"

                }

                "(" -> {
                    newStr += "﴾"

                }

                "\"" -> {
                    newStr += "יּ"

                }

                ")" -> {
                    newStr += "﴿"

                }

                "?" -> {
                    newStr += "2"

                }

                "!" -> {
                    newStr += "ךּ"

                }

                "-" -> {
                    newStr += "ﻕ"

                }

                "%" -> {
                    newStr += "ﻁ"

                }


                "1" -> {
                    newStr += "►"

                }

                "2" -> {
                    newStr += "▼"

                }

                "3" -> {
                    newStr += "◄"

                }

                "4" -> {
                    newStr += "◊"

                }

                "5" -> {
                    newStr += "○"

                }

                "6" -> {
                    newStr += "◌"

                }

                "7" -> {
                    newStr += "●"

                }

                "8" -> {
                    newStr += "◘"

                }

                "9" -> {
                    newStr += "◦"

                }

                "0" -> {
                    newStr += "⸗"

                }

                "A" -> {
                    newStr += "Ś"

                }

                "B" -> {
                    newStr += "Ů"

                }

                "C" -> {
                    newStr += "ƃ"

                }

                "D" -> {
                    newStr += "ƪ"

                }

                "E" -> {
                    newStr += "ƾ"

                }

                "F" -> {
                    newStr += "Ɋ"

                }

                "G" -> {
                    newStr += "ɞ"

                }

                "H" -> {
                    newStr += "ɲ"

                }

                "I" -> {
                    newStr += "ʆ"

                }

                "J" -> {
                    newStr += "ʚ"

                }

                "K" -> {
                    newStr += "Ͷ"

                }

                "L" -> {
                    newStr += "λ"

                }

                "M" -> {
                    newStr += "Ϗ"

                }

                "N" -> {
                    newStr += "Ϸ"

                }

                "O" -> {
                    newStr += "Ӈ"

                }

                "P" -> {
                    newStr += "Ф"

                }

                "Q" -> {
                    newStr += "П"

                }

                "R" -> {
                    newStr += "г"

                }

                "S" -> {
                    newStr += "ч"

                }

                "T" -> {
                    newStr += "ћ"

                }

                "U" -> {
                    newStr += "ќ"

                }

                "V" -> {
                    newStr += "ѯ"

                }

                "W" -> {
                    newStr += "җ"

                }

                "X" -> {
                    newStr += "ҫ"

                }

                "Y" -> {
                    newStr += "Ҭ"

                }

                "Z" -> {
                    newStr += "Ҿ"

                }


//                else-> {
//                    newStr += "0"
//
//                }


            }
        }

        return newStr
    }

    fun decrypt(strToDecrypt: String?): String? {

        var newStr:String = ""
        if (strToDecrypt != null) {
            val array = Array(strToDecrypt.length) {strToDecrypt[it].toString()}
            array.forEach { println(it) }
            for (e in array) {
                var value = e
                print("$e ")
                when (e) {
                    "{" -> {
                        newStr += "a"

                    }

                    "}" -> {
                        newStr += "b"

                    }

                    "#" -> {
                        newStr += "c"

                    }

                    "~" -> {
                        newStr += "d"

                    }

                    "+" -> {
                        newStr += "e"

                    }

                    "-" -> {
                        newStr += "f"

                    }

                    "@" -> {
                        newStr += "g"

                    }

                    "*" -> {
                        newStr += "h"

                    }

                    "/" -> {
                        newStr += "i"

                    }

                    "\\" -> {
                        newStr += "j"

                    }

                    "?" -> {
                        newStr += "k"

                    }

                    "$" -> {
                        newStr += "l"

                    }

                    "!" -> {
                        newStr += "m"

                    }

                    "^" -> {
                        newStr += "n"

                    }

                    "(" -> {
                        newStr += "o"

                    }

                    ")" -> {
                        newStr += "p"

                    }

                    "<" -> {
                        newStr += "q"

                    }

                    ">" -> {
                        newStr += "r"

                    }

                    "=" -> {
                        newStr += "s"

                    }

                    ";" -> {
                        newStr += "t"

                    }

                    "," -> {
                        newStr += "u"

                    }

                    "_" -> {
                        newStr += "v"

                    }

                    "[" -> {
                        newStr += "w"

                    }

                    "]" -> {
                        newStr += "x"

                    }

                    ":" -> {
                        newStr += "y"

                    }

                    "\"" -> {
                        newStr += "z"

                    }

                    " " -> {
                        newStr += " "

                    }

                    "ﻂ" -> {
                        newStr += "."

                    }

                    "ﮏ" -> {
                        newStr += ","

                    }

                    "﴾" -> {
                        newStr += "("

                    }

                    "יּ" -> {
                        newStr += "\""

                    }

                    "﴿" -> {
                        newStr += ")"

                    }

                    "?" -> {
                        newStr += "2"

                    }

                    "ךּ" -> {
                        newStr += "!"

                    }

                    "ﻕ" -> {
                        newStr += "-"

                    }

                    "ﻁ" -> {
                        newStr += "%"

                    }

                    "►" -> {
                        newStr += "1"

                    }

                    "▼" -> {
                        newStr += "2"

                    }

                    "◄" -> {
                        newStr += "3"

                    }

                    "◊" -> {
                        newStr += "4"

                    }

                    "○" -> {
                        newStr += "5"

                    }

                    "◌" -> {
                        newStr += "6"

                    }

                    "●" -> {
                        newStr += "7"

                    }

                    "◘" -> {
                        newStr += "8"

                    }

                    "◦" -> {
                        newStr += "9"

                    }

                    "⸗" -> {
                        newStr += "0"

                    }

                    "Ś" -> {
                        newStr += "A"

                    }

                    "Ů" -> {
                        newStr += "B"

                    }

                    "ƃ" -> {
                        newStr += "C"

                    }

                    "ƪ" -> {
                        newStr += "D"

                    }

                    "ƾ" -> {
                        newStr += "E"

                    }

                    "Ɋ" -> {
                        newStr += "F"

                    }

                    "ɞ" -> {
                        newStr += "G"

                    }

                    "ɲ" -> {
                        newStr += "H"

                    }

                    "ʆ" -> {
                        newStr += "I"

                    }

                    "ʚ" -> {
                        newStr += "J"

                    }

                    "Ͷ" -> {
                        newStr += "K"

                    }

                    "λ" -> {
                        newStr += "L"

                    }

                    "Ϗ" -> {
                        newStr += "M"

                    }

                    "Ϸ" -> {
                        newStr += "N"

                    }

                    "Ӈ" -> {
                        newStr += "O"

                    }

                    "Ф" -> {
                        newStr += "P"

                    }

                    "П" -> {
                        newStr += "Q"

                    }

                    "г" -> {
                        newStr += "R"

                    }

                    "ч" -> {
                        newStr += "S"

                    }

                    "ћ" -> {
                        newStr += "T"

                    }

                    "ќ" -> {
                        newStr += "U"

                    }

                    "ѯ" -> {
                        newStr += "V"

                    }

                    "җ" -> {
                        newStr += "W"

                    }

                    "ҫ" -> {
                        newStr += "X"

                    }

                    "Ҭ" -> {
                        newStr += "Y"

                    }

                    "Ҿ" -> {
                        newStr += "Z"

                    }

//                    else-> {
//                        newStr += "0"
//
//                    }


                }
            }
        }

        return newStr
    }

    override fun encode(rawPassword: CharSequence?): String {
        var newStr:String = ""

        val array = Array(rawPassword.toString().length) {rawPassword.toString()[it].toString()}
        array.forEach { println(it) }
        for (e in array) {
            var value = e.toLowerCase()

            print("$e ")
            when (value) {
                "a" -> {
                    newStr += "{"
                }

                "b" -> {
                    newStr += "}"
                }

                "c" -> {
                    newStr += "#"
                }

                "d" -> {
                    newStr += "~"
                }

                "e" -> {
                    newStr += "+"
                }

                "f" -> {
                    newStr += "-"
                }

                "g" -> {
                    newStr += "@"
                }

                "h" -> {
                    newStr += "*"
                }

                "i" -> {
                    newStr += "/"

                }

                "j" -> {
                    newStr += "\\"
                }

                "k" -> {
                    newStr += "?"
                }

                "l" -> {
                    newStr += "$"
                }

                "m" -> {
                    newStr += "!"
                }

                "n" -> {
                    newStr += "^"
                }

                "o" -> {
                    newStr += "("

                }

                "p" -> {
                    newStr += ")"

                }

                "q" -> {
                    newStr += "<"

                }

                "r" -> {
                    newStr += ">"

                }

                "s" -> {
                    newStr += "="

                }

                "t" -> {
                    newStr += ";"

                }

                "u" -> {
                    newStr += ","

                }

                "v" -> {
                    newStr += "_"

                }

                "w" -> {
                    newStr += "["

                }

                "x" -> {
                    newStr += "]"

                }

                "y" -> {
                    newStr += ":"

                }

                "z" -> {
                    newStr += "\""

                }

                " " -> {
                    newStr += " "

                }

                "." -> {
                    newStr += "3"

                }

                "," -> {
                    newStr += "1"

                }

                "(" -> {
                    newStr += "4"

                }

                "\"" -> {
                    newStr += "5"

                }

                ")" -> {
                    newStr += "7"

                }

                "?" -> {
                    newStr += "2"

                }

                "!" -> {
                    newStr += "8"

                }

                "-" -> {
                    newStr += "6"

                }

                "%" -> {
                    newStr += "9"

                }

                "1" -> {
                    newStr += "r"

                }

                "2" -> {
                    newStr += "k"

                }

                "3" -> {
                    newStr += "b"

                }

                "4" -> {
                    newStr += "e"

                }

                "5" -> {
                    newStr += "q"

                }

                "6" -> {
                    newStr += "h"

                }

                "7" -> {
                    newStr += "u"

                }

                "8" -> {
                    newStr += "y"

                }

                "9" -> {
                    newStr += "w"

                }

                "0" -> {
                    newStr += "z"

                }

//                else-> {
//                    newStr += "0"
//
//                }
            }
        }
        return newStr
    }

    override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
        requireNotNull(rawPassword) { "rawPassword cannot be null" }
        if (encodedPassword == null || encodedPassword.length == 0) {
            this.logger.warn("Empty encoded password")
            return false
        }
       /* if (!this.BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            this.logger.warn("Encoded password does not look like BCrypt")
            return false
        }*/

        return checkpw(rawPassword.toString(), encodedPassword)

    }

    fun checkpw(plaintext: String?, hashed: String?): Boolean {
        return equalsNoEarlyReturn(hashed!!, hashpw(plaintext!!, hashed)as String)
    }

    fun equalsNoEarlyReturn(a: String, b: String): Boolean {
        return MessageDigest.isEqual(a.toByteArray(StandardCharsets.UTF_8), b.toByteArray(StandardCharsets.UTF_8))
    }

    fun hashpw(password: String, salt: String?): String? {
        val passwordb: ByteArray
        passwordb = password.toByteArray(StandardCharsets.UTF_8)
        return encrypt(password)
    }
}