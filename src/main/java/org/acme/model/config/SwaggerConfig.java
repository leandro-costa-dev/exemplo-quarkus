package org.acme.model.config;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "API-Usuarios",
                version = "1.0.0",
                contact = @Contact(
                        name = "Exemplo nome",
                        url = "www.url.com.br",
                        email = "email.com.br"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)

public class SwaggerConfig {

}
