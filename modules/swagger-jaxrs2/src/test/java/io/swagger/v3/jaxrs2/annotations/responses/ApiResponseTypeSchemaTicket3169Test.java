package io.swagger.v3.jaxrs2.annotations.responses;

import io.swagger.v3.jaxrs2.annotations.AbstractAnnotationTest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
}
