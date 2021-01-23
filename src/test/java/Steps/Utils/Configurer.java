package Steps.Utils;

import Utils.AdvancedString;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableCellTransformer;

import java.util.Locale;

public class Configurer implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry registry) {
        registry.defineDataTableType(new DataTableType(AdvancedString.class,
                new TableCellTransformer<AdvancedString>() {
                    @Override
                    public AdvancedString transform(String cell) throws Throwable {
                        return new AdvancedString(cell);
                    }
                }));
    }
}
