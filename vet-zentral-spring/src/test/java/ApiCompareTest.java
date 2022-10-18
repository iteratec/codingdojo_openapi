import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.OpenApiCompare;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class ApiCompareTest {

    private static final String ApiV1 = "";
    private static final String ApiV2 = "";

    @Test
    void testApiCompatibility() {
        ChangedOpenApi diff = OpenApiCompare.fromLocations(ApiV1, ApiV2);
    }

    @Test
    void testApiChanges() {
        ChangedOpenApi diff = OpenApiCompare.fromLocations(ApiV1, ApiV2);
    }
}
