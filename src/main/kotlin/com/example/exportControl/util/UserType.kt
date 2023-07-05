package com.example.exportControl.util

enum class UserTypeJpn(val type: String) {
    ADMIN("管理者"), USER("一般ユーザー"),HL("HL");

}

enum class UserTypeEng(val type: String) {
    ADMIN("Admin"), USER("User"),HL("HL");

}

enum class ExportTImportJPN(val type: String) {
    Export("輸出"), Import("輸入");
}

enum class ExportTImportENG(val type: String) {
    Export("Export"), Import("Import");
}

enum class TransportModeENG(val type: String) {
    Air("Air"), Ship("Ship");
}

enum class TransportModeJPN(val type: String) {
    Air("エア"), Ship("船");
}