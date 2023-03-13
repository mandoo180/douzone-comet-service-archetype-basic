package com.douzone.comet.service.hr.pamaah.x20173;

import com.douzone.comet.components.DzCometService;
import com.douzone.gpd.components.exception.DzApplicationRuntimeException;
import com.douzone.gpd.restful.annotation.DzApi;
import com.douzone.gpd.restful.annotation.DzApiService;
import com.douzone.gpd.restful.annotation.DzParam;
import com.douzone.gpd.restful.enums.CometModule;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.douzone.gpd.restful.enums.DzParamType.Body;
import static com.douzone.gpd.restful.enums.DzParamType.QueryString;
import static com.douzone.gpd.restful.enums.DzRequestMethod.GET;
import static com.douzone.gpd.restful.enums.DzRequestMethod.POST;


@DzApiService(value = "${servicename}", module = CometModule.${module}, desc = "${servicename}", version = "${version}")
public class ${servicename} extends DzCometService {

    private final ${daoname} repo;
    private final CompanyMasterDataProvider cmdp;

    public ${servicename}(${daoname} repo, CompanyMasterDataProvider cmdp) {
        this.repo = repo;
        this.cmdp = cmdp;
    }

    @DzApi(url = "/list", desc = "list", httpMethod = GET)
    public List<Map<String, Object>> list(
            @DzParam(key = "searchType", desc = "searchType", paramType = QueryString) String searchType
    ) throws Exception {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("searchType", searchType);
            return repo.list(params);
        } catch (Exception e) {
            throw new DzApplicationRuntimeException(e);
        }
    }

    @Transactional
    @DzApi(url = "/save", desc = "save", httpMethod = POST)
    public String save(
            @DzParam(key = "item", desc = "item", paramType = Body) Map<String, Object> item
    ) {
        try {
            Map<String, Object> params = new HashMap<>(item);
            String sqNo = cmdp.getSequenceNo(this.getCompanyCode(), "HR", "16", (String) item.get("REQ_DT"));
            params.put("SQ_NO", sqNo);
            repo.insert("insert_offapply", params);
            return sqNo;
        } catch (Exception e) {
            throw new DzApplicationRuntimeException(e);
        }
    }

}