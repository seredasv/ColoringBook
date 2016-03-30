package com.seredasv.coloringbook.models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "image")
class ImageModel {
    @DatabaseField(id = true, generatedId = true)
    private var id: Int = 0
    @DatabaseField
    private var name: String? = null
    @DatabaseField
    private var status: ImageColoringStatus = ImageColoringStatus.NOT_COLORED
    @DatabaseField
    private var coordinates: List<ImageCoordinateModel>? = null
}