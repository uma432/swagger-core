package io.swagger.v3.jaxrs2.annotations.responses;

import io.swagger.v3.jaxrs2.annotations.AbstractAnnotationTest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.callbacks.Callback;
import io.swagger.v3.oas.annotations.callbacks.Callbacks;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.testng.annotations.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class ApiResponseTypeSchemaTicket3169Test extends AbstractAnnotationTest {

    @Test
    public void testApiResponseInOperationSchemaTicket3169Test() throws Exception {
        String expectedYaml = "openapi: 3.0.1\n" +
                "paths:\n" +
                "  /path:\n" +
                "    get:\n" +
                "      summary: Op\n" +
                "      description: Response return a MyModel Type\n" +
                "      operationId: simpleGET\n" +
                "      requestBody:\n" +
                "        content:\n" +
                "          '*/*':\n" +
                "            schema:\n" +
                "              type: string\n" +
                "      responses:\n" +
                "        200:\n" +
                "          description: voila!\n" +
                "          content:\n" +
                "            '*/*':\n" +
                "              schema:\n" +
                "                $ref: '#/components/schemas/MyModel'\n" +
                "components:\n" +
                "  schemas:\n" +
                "    MyModel:\n" +
                "      type: object\n";
        compareAsYaml(ResponseWithType.class, expectedYaml);
        compareAsYaml(ResponseWithTypeImpl.class, expectedYaml);
    }

    static class ResponseWithType {
        @Operation(
                summary = "Op",
                description = "Response return a MyModel Type",
                operationId = "simpleGET",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "voila!",
                                type = MyModel.class)
                })
        @GET
        @Path("/path")
        public void simpleGet(String data) {
        }
    }

    class MyModel {

    }

    class AnotherModel {

    }

    static class ResponseWithTypeImpl {
        @Operation(
                summary = "Op",
                description = "Response return a MyModel Type",
                operationId = "simpleGET",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "voila!",
                                content = @Content(schema = @Schema(implementation = MyModel.class)))
                })
        @GET
        @Path("/path")
        public void simpleGet(String data) {
        }
    }

    @Test
    public void testApiResponseTypeVoidSchemaTicket3169Test() throws Exception {
        String expectedYaml = "openapi: 3.0.1\n" +
                "paths:\n" +
                "  /path:\n" +
                "    get:\n" +
                "      summary: Op\n" +
                "      description: 'Response return a Void Type '\n" +
                "      operationId: getWithNoParameters\n" +
                "      requestBody:\n" +
                "        content:\n" +
                "          '*/*':\n" +
                "            schema:\n" +
                "              type: string\n" +
                "      responses:\n" +
                "        200:\n" +
                "          description: voila!\n";
        compareAsYaml(ResponseWithTypeVoid.class, expectedYaml);
        compareAsYaml(ResponseWithTypeVoidImpl.class, expectedYaml);
    }

    static class ResponseWithTypeVoid {
        @Operation(
                summary = "Op",
                description = "Response return a Void Type ",
                operationId = "getWithNoParameters",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "voila!",
                                type = Void.class)
                })
        @GET
        @Path("/path")
        public void simpleGet(String data) {
        }
    }

    static class ResponseWithTypeVoidImpl {
        @Operation(
                summary = "Op",
                description = "Response return a Void Type ",
                operationId = "getWithNoParameters",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "voila!",
                                content = @Content(schema = @Schema(implementation = Void.class)))
                })
        @GET
        @Path("/path")
        public void simpleGet(String data) {
        }
    }

    @Test
    public void testApiResponseTypeInApiResponsesSchemaTicket3169Test() throws Exception {
        String expectedYaml = "openapi: 3.0.1\n" +
                "paths:\n" +
                "  /path:\n" +
                "    get:\n" +
                "      summary: Op\n" +
                "      description: 'Response return a Void Type '\n" +
                "      operationId: getWithNoParameters\n" +
                "      requestBody:\n" +
                "        content:\n" +
                "          '*/*':\n" +
                "            schema:\n" +
                "              type: string\n" +
                "      responses:\n" +
                "        200:\n" +
                "          description: voila!\n" +
                "          content:\n" +
                "            '*/*':\n" +
                "              schema:\n" +
                "                $ref: '#/components/schemas/MyModel'\n" +
                "        400:\n" +
                "          description: voila!\n" +
                "          content:\n" +
                "            '*/*':\n" +
                "              schema:\n" +
                "                $ref: '#/components/schemas/AnotherModel'\n" +
                "components:\n" +
                "  schemas:\n" +
                "    MyModel:\n" +
                "      type: object\n" +
                "    AnotherModel:\n" +
                "      type: object\n";
        compareAsYaml(ResponseWithTypeInApiResponses.class, expectedYaml);
        compareAsYaml(ResponseWithTypeInApiResponsesImpl.class, expectedYaml);
    }

    static class ResponseWithTypeInApiResponses {
        @Operation(
                summary = "Op",
                description = "Response return a Void Type ",
                operationId = "getWithNoParameters")
        @GET
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "voila!",
                        type = MyModel.class),
                @ApiResponse(
                        responseCode = "400",
                        description = "voila!",
                        type = AnotherModel.class),
        })
        @Path("/path")
        public void simpleGet(String data) {
        }
    }

    static class ResponseWithTypeInApiResponsesImpl {
        @Operation(
                summary = "Op",
                description = "Response return a Void Type ",
                operationId = "getWithNoParameters")
        @GET
        @ApiResponses(value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "voila!",
                        content = @Content(schema = @Schema(implementation = MyModel.class))),
                @ApiResponse(
                        responseCode = "400",
                        description = "voila!",
                        content = @Content(schema = @Schema(implementation = AnotherModel.class))),
        })
        @Path("/path")
        public void simpleGet(String data) {
        }
    }

    @Test
    public void simpleCallbacksWithTypeInApiResponse() throws  Exception{
        String expectedYAML = "openapi: 3.0.1\n" +
                "paths:\n" +
                "  /path:\n" +
                "    get:\n" +
                "      summary: Simple get operation\n" +
                "      operationId: getWithNoParameters\n" +
                "      responses:\n" +
                "        200:\n" +
                "          description: voila!\n" +
                "          content:\n" +
                "            '*/*':\n" +
                "              schema:\n" +
                "                $ref: '#/components/schemas/MyModel'\n" +
                "      callbacks:\n" +
                "        testCallback1:\n" +
                "          localhost:9080/airlines/reviews/{id}/1: {}\n" +
                "components:\n" +
                "  schemas:\n" +
                "    MyModel:\n" +
                "      type: object\n";
        compareAsYaml(SimpleCallbacksTestWithApiReponseType.class, expectedYAML);
        compareAsYaml(SimpleCallbacksTestWithApiReponseTypeImpl.class, expectedYAML);
    }

    static class SimpleCallbacksTestWithApiReponseType {
        @Callbacks({
                @Callback(name = "testCallback1", operation = @Operation(), callbackUrlExpression = "localhost:9080/airlines/reviews/{id}/1")
        })
        @Operation(
                summary = "Simple get operation",
                operationId = "getWithNoParameters",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "voila!",
                                type = MyModel.class)
                })
        @GET
        @Path("/path")
        public void simpleGet() {
        }
    }

    static class SimpleCallbacksTestWithApiReponseTypeImpl {
        @Callbacks({
                @Callback(name = "testCallback1", operation = @Operation(), callbackUrlExpression = "localhost:9080/airlines/reviews/{id}/1")
        })
        @Operation(
                summary = "Simple get operation",
                operationId = "getWithNoParameters",
                responses = {
                        @ApiResponse(
                                responseCode = "200",
                                description = "voila!",
                                content = @Content(schema = @Schema(implementation = MyModel.class)))
                })
        @GET
        @Path("/path")
        public void simpleGet() {
        }
    }
}
