package com.seredasv.coloringbook.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ImageModel(@PrimaryKey open var id: Int = 0, open var name: String? = null,
                      open var status: String = ImageColoringStatus.NOT_COLORED.toString(),
                      open var coordinates: RealmList<ImageCoordinateModel> = RealmList()) : RealmObject() {
}