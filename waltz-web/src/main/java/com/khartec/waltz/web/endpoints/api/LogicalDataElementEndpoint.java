package com.khartec.waltz.web.endpoints.api;

import com.khartec.waltz.model.logical_data_element.LogicalDataElement;
import com.khartec.waltz.service.logical_data_element.LogicalDataElementService;
import com.khartec.waltz.web.WebUtilities;
import com.khartec.waltz.web.endpoints.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spark.Request;
import spark.Response;

import static com.khartec.waltz.common.Checks.checkNotNull;
import static com.khartec.waltz.web.WebUtilities.getId;
import static com.khartec.waltz.web.WebUtilities.mkPath;
import static com.khartec.waltz.web.endpoints.EndpointUtilities.getForDatum;
import static com.khartec.waltz.web.endpoints.EndpointUtilities.getForList;


@Service
public class LogicalDataElementEndpoint implements Endpoint {

    private static final Logger LOG = LoggerFactory.getLogger(LogicalDataElementEndpoint.class);
    private static final String BASE_URL = WebUtilities.mkPath("api", "logical-data-element");

    private final LogicalDataElementService service;


    public LogicalDataElementEndpoint(LogicalDataElementService service) {
        checkNotNull(service, "service cannot be null");

        this.service = service;
    }


    @Override
    public void register() {
        // read
        getForDatum(mkPath(BASE_URL, "id", ":id"), this::getByIdRoute);
        getForList(BASE_URL, (request, response) -> service.findAll());
    }


    private LogicalDataElement getByIdRoute(Request request, Response response) {
        long id = getId(request);
        return service.getById(id);
    }

}