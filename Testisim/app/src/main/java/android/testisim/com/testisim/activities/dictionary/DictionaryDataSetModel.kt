package android.testisim.com.testisim.activities.dictionary

import com.google.gson.annotations.SerializedName

data class DictionaryDataSetModel(val list: List<DictionaryEntity>)

data class DictionaryEntity(@SerializedName("Ack") val ack: String, @SerializedName("Kelime") val word: String)