package com.qwict.studyplanetandroid.domain.validator

class Validators {
    val emailValidator = ValidateEmail()
    val passwordValidator = ValidatePassword()
    val usernameValidator = ValidateUsername()
    val notEmptyTextValidator = ValidateNotEmptyText()
}
