package com.prometheus.project.app.service.enumeration

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import java.io.IOException

enum class RoleType(
        val resource: String
) {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    READ("READ"),
    LIST("LIST"),
    DELETE("DELETE"),
    DOWNLOAD("DOWNLOAD"),
    FULL_ACCESS_APP("FULL_ACCESS_APP");

    internal class RoleDeserializer : JsonDeserializer<RoleType>() {
        @Throws(IOException::class, JsonProcessingException::class)
        override fun deserialize(jsonParser: JsonParser, ctx: DeserializationContext?): RoleType {
            val oc: ObjectCodec = jsonParser.codec
            val node: JsonNode = oc.readTree(jsonParser)
            return valueOf(node["text"].asText())
        }
    }
}
