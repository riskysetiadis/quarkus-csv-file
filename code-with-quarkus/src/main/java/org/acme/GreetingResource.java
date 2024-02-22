package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

// @Path("/hello")
// public class GreetingResource {

//     @GET
//     @Produces(MediaType.TEXT_PLAIN)
//     public String hello() {
//         return "Hello from RESTEasy Reactive";
//     }
// }

@Path("/csv")
public class CsvResource {

    @ConfigProperty(name = "upload.folder") // Add this if you want to configure the upload folder
    String uploadFolder;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadCsvFile(@MultipartForm FormData formData) {
        try {
            InputStream fileInputStream = formData.getFile();
            // Process the CSV file - you can use libraries like OpenCSV for parsing
            // For demonstration, let's just print the file name
            System.out.println("Uploaded file name: " + formData.getFileName());
            return Response.ok("File uploaded successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error uploading the file: " + e.getMessage())
                    .build();
        }
    }
}