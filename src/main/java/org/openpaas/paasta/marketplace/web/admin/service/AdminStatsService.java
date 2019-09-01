package org.openpaas.paasta.marketplace.web.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openpaas.paasta.marketplace.api.domain.CustomPage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author hrjin
 * @version 1.0
 * @since 2019-08-30
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AdminStatsService {

    private final RestTemplate paasApiRest;

    /**
     * 상품별 사용자 수 목록 조회
     *
     * @param idIn
     * @return
     */
    public Map<Long, Long> getCountsOfInsts(List<Long> idIn) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().path("/admin/stats/instances/counts/ids");
        for (Long id : idIn) {
            builder.queryParam("idIn", id);
        }
        String url = builder.buildAndExpand().toUriString();

        return paasApiRest.getForObject(url, Map.class);
    }

    /**
     * 판매된 상품 총 개수 조회
     *
     * @return
     */
    public long getCountOfInstsUsing() {
        return paasApiRest.getForObject("/admin/stats/instances/counts/sum", long.class);
    }


    /**
     * 총 사용자 수 조회
     *
     * @return
     */
    public long getCountOfUsersUsing() {
        return paasApiRest.getForObject("/admin/stats/users/counts/sum", long.class);
    }

    public Map getUserStatList(List<String> idIn) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().path("/admin/stats/users");
        for (String id : idIn) {
            builder.queryParam("idIn", id);
        }
        String url = builder.buildAndExpand().toUriString();
        return paasApiRest.getForObject(url, Map.class);
    }

    /**
     * 마켓플레이스 전체 사용자 목록 조회
     *
     * @param queryParamString
     * @return
     */
    public CustomPage<Object> getUserList(String queryParamString) {
        ResponseEntity<CustomPage<Object>> responseEntity = paasApiRest.exchange("/admin/users/page" + queryParamString, HttpMethod.GET, null, new ParameterizedTypeReference<CustomPage<Object>>() {});
        CustomPage<Object> customPage = responseEntity.getBody();
        return customPage;
    }


    /**
     * 사용자 상세 조회
     *
     * @param id
     * @return
     */
    public Object getUser(String id) {
        String url = UriComponentsBuilder.newInstance().path("/admin/users/{id}")
                .build()
                .expand(id)
                .toString();
        return paasApiRest.getForObject(url, Object.class);
    }
}
