
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CarRentalApplication extends Application<CarRentalConfiguration> {
    public static void main(String[] args) throws Exception {
        new CarRentalApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CarRentalConfiguration> bootstrap) {
        // Initialization logic if needed
    }

    @Override
    public void run(CarRentalConfiguration configuration, Environment environment) {
        SearchService searchService = new SearchService();
        SearchResource searchResource = new SearchResource(searchService);

        // Register the REST API resource
        environment.jersey().register(searchResource);
    }
}
