package io.swagger.v3.jaxrs2;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.core.util.AnnotationsUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.links.Link;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Map;
import java.util.Optional;

public class OperationParser {

    public static final String COMPONENTS_REF = "#/components/schemas/";

    public static Optional<RequestBody> getRequestBody(io.swagger.v3.oas.annotations.parameters.RequestBody requestBody, Consumes classConsumes, Consumes methodConsumes, Components components, JsonView jsonViewAnnotation) {
        if (requestBody == null) {
            return Optional.empty();
        }
        RequestBody requestBodyObject = new RequestBody();
        boolean isEmpty = true;

        if (StringUtils.isNotBlank(requestBody.ref())) {
            requestBodyObject.set$ref(requestBody.ref());
            return Optional.of(requestBodyObject);
        }

        if (StringUtils.isNotBlank(requestBody.description())) {
            requestBodyObject.setDescription(requestBody.description());
            isEmpty = false;
        }
        if (requestBody.required()) {
            requestBodyObject.setRequired(requestBody.required());
            isEmpty = false;
        }
        if (requestBody.extensions().length > 0) {
            Map<String, Object> extensions = AnnotationsUtils.getExtensions(requestBody.extensions());
            if (extensions != null) {
                for (String ext : extensions.keySet()) {
                    requestBodyObject.addExtension(ext, extensions.get(ext));
                }
            }
            isEmpty = false;
        }

        if (requestBody.content().length > 0) {
            isEmpty = false;
        }

        if (isEmpty) {
            return Optional.empty();
        }
        AnnotationsUtils.getContent(requestBody.content(), classConsumes == null ? new String[0] : classConsumes.value(),
                methodConsumes == null ? new String[0] : methodConsumes.value(), null, components, jsonViewAnnotation).ifPresent(requestBodyObject::setContent);
        return Optional.of(requestBodyObject);
    }

    public static Optional<ApiResponses> getApiResponses(final io.swagger.v3.oas.annotations.responses.ApiResponse[] responses, Produces classProduces, Produces methodProduces, Components components, JsonView jsonViewAnnotation) {
        if (responses == null) {
            return Optional.empty();
        }
        ApiResponses apiResponsesObject = new ApiResponses();
        for (io.swagger.v3.oas.annotations.responses.ApiResponse response : responses) {
            ApiResponse apiResponseObject = new ApiResponse();
            if (StringUtils.isNotBlank(response.ref())) {
                apiResponseObject.set$ref(response.ref());
                if (StringUtils.isNotBlank(response.responseCode())) {
                    apiResponsesObject.addApiResponse(response.responseCode(), apiResponseObject);
                } else {
                    apiResponsesObject._default(apiResponseObject);
                }
                continue;
            }
            if (StringUtils.isNotBlank(response.description())) {
                apiResponseObject.setDescription(response.description());
            }
            if (response.extensions().length > 0) {
                Map<String, Object> extensions = AnnotationsUtils.getExtensions(response.extensions());
                if (extensions != null) {
                    for (String ext : extensions.keySet()) {
                        apiResponseObject.addExtension(ext, extensions.get(ext));
                    }
                }
            }

            if (!Void.class.equals(response.type())) {
                Content content = new Content();
                MediaType mediaType = new MediaType();
                AnnotationsUtils.getSchemaFromType(response.type(), components, jsonViewAnnotation).ifPresent(mediaType::setSchema);
                AnnotationsUtils.applyTypes(classProduces == null ? new String[0] : classProduces.value(),
                        methodProduces == null ? new String[0] : methodProduces.value(), content, mediaType);
                apiResponseObject.content(content);
            } else {
                io.swagger.v3.oas.annotations.media.Content[] content = response.content();
                if (content.length > 0 && content[0] != null && isContentNull(content[0])) {
                    content = null;
                }
                AnnotationsUtils.getContent(content, classProduces == null ? new String[0] : classProduces.value(),
                        methodProduces == null ? new String[0] : methodProduces.value(), null, components, jsonViewAnnotation).ifPresent(apiResponseObject::content);
            }
            AnnotationsUtils.getHeaders(response.headers(), jsonViewAnnotation).ifPresent(apiResponseObject::headers);
            if (StringUtils.isNotBlank(apiResponseObject.getDescription()) || apiResponseObject.getContent() != null || apiResponseObject.getHeaders() != null) {

                Map<String, Link> links = AnnotationsUtils.getLinks(response.links());
                if (links.size() > 0) {
                    apiResponseObject.setLinks(links);
                }
                if (StringUtils.isNotBlank(response.responseCode())) {
                    apiResponsesObject.addApiResponse(response.responseCode(), apiResponseObject);
                } else {
                    apiResponsesObject._default(apiResponseObject);
                }
            }
        }

        if (apiResponsesObject.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(apiResponsesObject);
    }

    private static boolean isContentNull(io.swagger.v3.oas.annotations.media.Content content) {
        if (content.examples().length > 0 || !content.mediaType().isEmpty() || !isSchemaNull(content.array().schema()) ||
                content.encoding().length > 0 || content.extensions().length > 0) {
            return false;
        }

        final Schema schema = content.schema();
        if (!isSchemaNull(schema)) {
            return false;
        }

        return true;
    }

    private static boolean isSchemaNull(final Schema schema) {
        if (StringUtils.isNotEmpty(schema.name()) || StringUtils.isNotEmpty(schema.description()) ||
                StringUtils.isNotEmpty(schema.type()) || StringUtils.isNotEmpty(schema.format()) ||
                StringUtils.isNotEmpty(schema.example()) || StringUtils.isNotEmpty(schema.ref()) ||
                StringUtils.isNotEmpty(schema.minimum()) || StringUtils.isNotEmpty(schema.maximum()) ||
                StringUtils.isNotEmpty(schema.title()) || StringUtils.isNotEmpty(schema.defaultValue()) ||
                StringUtils.isNotEmpty(schema.pattern()) || StringUtils.isNotEmpty(schema.discriminatorProperty())) {
            return false;
        }

        Class<?> implementation = schema.implementation();
        if (!"java.lang.Void".equals(implementation.getName())) {
            return false;
        }
        return true;
    }

}
