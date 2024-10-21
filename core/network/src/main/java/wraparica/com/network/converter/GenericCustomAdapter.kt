package wraparica.com.network.converter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import wraparica.com.network.util.ErrorInfo
import wraparica.com.network.util.ErrorTokenResponse

class GenericCustomAdapter constructor(
    moshi: Moshi
) : JsonAdapter<ErrorTokenResponse>() {

    private val options: JsonReader.Options = JsonReader.Options.of("error", "moreInfo", "message")

    private val nullableErrorInfoAdapter: JsonAdapter<ErrorInfo?> =
        moshi.adapter(ErrorInfo::class.java, emptySet(), "error")

    private val nullableStringAdapter: JsonAdapter<String?> = moshi.adapter(
        String::class.java,
        emptySet(), "moreInfo"
    )

    override fun fromJson(reader: JsonReader): ErrorTokenResponse {
        var error: ErrorInfo? = null
        var moreInfo: String? = null
        var message: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> error = nullableErrorInfoAdapter.fromJson(reader)
                1 -> moreInfo = nullableStringAdapter.fromJson(reader)
                2 -> message = nullableStringAdapter.fromJson(reader)
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        return ErrorTokenResponse(
            error = error,
            moreInfo = moreInfo,
            message = message
        )
    }

    override fun toJson(writer: JsonWriter, value: ErrorTokenResponse?) {
        if (value == null) {
            throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("error")
        nullableErrorInfoAdapter.toJson(writer, value.error)
        writer.name("moreInfo")
        nullableStringAdapter.toJson(writer, value.moreInfo)
        writer.name("message")
        nullableStringAdapter.toJson(writer, value.message)
        writer.endObject()
    }
}