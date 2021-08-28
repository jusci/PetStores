//1 - Pacote

package petstores;

// 2 - Bibliotecas

// 3- Classe

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withNoArgs;
import static org.hamcrest.CoreMatchers.is;

public class Pet {

    //3.1 - Atributos

    String uri="https://petstore.swagger.io/v2/pet";//endereço da entidade Pet


    //3.2 - metodos e funçoes


    public String lerJson (String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Incluir- Creat - Post
    @Test // identifica o metodo ou funcao como um teste para o TestNG ( mesma função que o Junit)
    public void incluirPet() throws IOException {

        String jsonBody = lerJson("db/pet1.json");

        //Sintaxe Gherkin
        //Dado - Quando - Então
        //Give - When - Then

        given()
                .contentType("application/json") //comum em API REST - antigas era "Text/XML"
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)

        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Jimmy"))
                .body("status",is("available"))
        ;


    }

}
