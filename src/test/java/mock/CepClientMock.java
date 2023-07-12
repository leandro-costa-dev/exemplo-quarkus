package mock;

import com.ibm.asyncutil.iteration.AsyncIterator;
import io.quarkus.test.Mock;
import org.acme.model.rest.client.CepClient;
import org.acme.model.rest.dto.EnderecoDTO;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
@RestClient
public class CepClientMock implements CepClient {

    @Override
    public EnderecoDTO getEndereco(String cep) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep("8580000");
        return enderecoDTO;
    }
}
