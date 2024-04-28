package ru.kaplaan.consumer.web.dto.payment

import jakarta.validation.constraints.*
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class PaymentInfoDto(
    @field:Pattern(regexp = "^\\d{10}\$", message = "ИНН должен иметь 10 цифр", groups = [OnCreate::class, OnUpdate::class])
    val inn: String,

    @field:Pattern(regexp = "^\\d{9}\$", message = "КПП должен иметь 9 цифр", groups = [OnCreate::class, OnUpdate::class])
    val kpp: String,

    @field:NotBlank(message = "Название компании не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val companyName: String,

    @field:Pattern(regexp = "^\\d{20}\$", message = "Номер счета плательщика должен иметь 20 цифр", groups = [OnCreate::class, OnUpdate::class])
    val companyAccountNumber: String,

    @field:Pattern(regexp = "^\\d{9}\$", message = "БИК банка должен иметь 20 цифр", groups = [OnCreate::class, OnUpdate::class])
    val bankBik: String,

    @field:Pattern(regexp = "^\\d{20}\$", message = "Номер счета банка должен иметь 20 цифр", groups = [OnCreate::class, OnUpdate::class])
    val bankAccountNumber: String,

    @field:NotBlank(message = "Название банка не должно быть пустым", groups = [OnCreate::class, OnUpdate::class])
    val bankName: String,

    @field:Min(0, message = "Минимальное Id компании - 0!", groups = [OnCreate::class, OnUpdate::class])
    val companyId: Long
){
    @field:Null(message = "Id информации о плательщике не должна быть заполнена!", groups = [OnCreate::class])
    @field:NotNull(message = "Id информации о плательщике должна быть заполнена!", groups = [OnUpdate::class])
    var id: Long? = null
}