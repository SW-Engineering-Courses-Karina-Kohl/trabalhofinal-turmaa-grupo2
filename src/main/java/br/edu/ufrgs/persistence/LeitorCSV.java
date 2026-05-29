package br.edu.ufrgs.persistence;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import br.edu.ufrgs.model.Venda;
import com.opencsv.bean.CsvToBeanBuilder;

public class LeitorCSV {
    public List<Venda> lerVendas(InputStream inputStream) throws Exception {
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            return new CsvToBeanBuilder<Venda>(reader)
                    .withType(Venda.class)
                    .withSeparator(',') // ou ';' dependendo do seu CSV
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        }
    }
}
