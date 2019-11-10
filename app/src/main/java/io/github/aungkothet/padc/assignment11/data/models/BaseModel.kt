package io.github.aungkothet.padc.assignment11.data.models

import io.github.aungkothet.padc.assignment11.utils.DataBaseHelper

abstract class BaseModel {

    val dataBase = DataBaseHelper.photoDatabase
}