package br.edu.ifpr.foz.ifprstore.repositories;

import br.edu.ifpr.foz.ifprstore.models.Seller;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SellerRepositoryTest {

    @Test
    public void deveExibirUmaListaDeSellers(){

        SellerRepository repository = new SellerRepository();

        List<Seller> sellers = repository.getSellers();

        for (Seller s: sellers) {
            System.out.println(s);
        }

    }

}
