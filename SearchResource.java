 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {
    private final SearchService searchService;

    public SearchResource(SearchService searchService) {
        this.searchService = searchService;
    }

    @POST
    public Response search(Search searchRequest) {
        List<SearchResult> results = searchService.searchByCity(searchRequest.getCity());

        if (results.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No results found for city: " + searchRequest.getCity())
                    .build();
        }

        return Response.ok(results).build();
    }
}
