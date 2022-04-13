package com.carvalho.pi.helpers

import android.text.Editable

class Validator {

    fun validationName(name: String,valueEditable: Editable): String {

        if (valueEditable.isBlank()) {
            return "Campo está vazio"
        } else if (name.length < 3) {
            return "Campo tem menos de 3 letras"
        } else {
            return ""
        }
    }

    fun validationQuantity(quant: String, valueEditable: Editable):String{

        if (valueEditable.isBlank()) {
            return "Uma quantidade deve ser selecionada"
        } else if (quant.equals("Quantidade" , true)) {
            return "Uma quantidade deve ser selecionada"
        } else {
            return ""
        }
    }

    fun validationDescription(descrip: String, valueEditable: Editable):String{

        if (valueEditable.isBlank()) {
            return "Campo está vazio"
        } else if (descrip.length < 5) {
            return "Campo tem menos de 5 letras"
        } else {
            return ""
        }
    }

    fun validationAddress(address: String, valueEditable: Editable):String{

        if (valueEditable.isBlank()) {
            return "Campo está vazio"
        } else if (address.length < 5) {
            return "Campo tem menos de 5 letras"
        } else {
            return ""
        }
    }

    fun validationPay(pay: String, valueEditable: Editable):String{
        if (valueEditable.isBlank()) {
            return "Forma de pagamento precisa ser selecionada"
        } else if (pay.equals("Forma de pagamento", true)) {
            return "Forma de pagamento precisa ser selecionada"
        } else {
            return ""
        }
    }

    fun validationValue(value: String, valueEditable: Editable):String{
        if (valueEditable.isBlank()) {
            return "Campo está vazio"
        } else if (value.length < 2) {
            return "Campo tem menos de 2 numeros"
        } else {
            return ""
        }

    }

}