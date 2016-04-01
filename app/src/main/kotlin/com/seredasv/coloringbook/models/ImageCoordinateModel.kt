package com.seredasv.coloringbook.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ImageCoordinateModel(@PrimaryKey open var id: Int = 0,
                                open var longitude: Int = 0,
                                open var latitude: Int = 0,
                                open var color: Int = 0) : RealmObject() {
}