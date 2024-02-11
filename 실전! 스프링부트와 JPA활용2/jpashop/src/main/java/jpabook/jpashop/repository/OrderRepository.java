package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.order.simplequery.SimpleOrderQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    /**
     * <쿼리 방식 선택 권장 순서>
     * 1. 우선 엔티티를 DTO로 변환하는 방법을 선택.
     * 2. 필요하면 페치 조인으로 성능 최적화 -> 대부분 성능 이슈 해결
     * 3. 그래도 안되면 DTO로 직접 조회
     * 4. 최후의 방법은 JPA가 제공하는 네이티브SQL이나 스프링 JDBC Template을 사용해 SQL 직접 사용.
     * **/

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        //language=JPAQL
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000); //최대 1000건
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }
        return query.getResultList();
    }

    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();
    }

    public List<SimpleOrderQueryDto> findOrderDtos() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.SimpleOrderQueryDto(o.id,m.name,o.orderDate,o.status,d.address) "+
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", SimpleOrderQueryDto.class
        ).getResultList();
    }

    public List<Order> findAllWithItem() {
        //distinct로 fetch Join시 발생하는 데이터 뻥튀기 해결 => 일단 디비에서 데이터를 가져온 후 Order 고유값이 같다면 중복제거
        return em.createQuery(
                "select distinct o from Order o" +
                " join fetch o.member m"+
                " join fetch o.delivery d" +
                " join fetch o.orderItems oi" +
                " join fetch oi.item",Order.class).getResultList();
    }

    public List<Order> findAllWithWithMemberDelivery() {
        return em.createQuery(
                "select distinct o from Order o" +
                        " join fetch o.member m"+
                        " join fetch o.delivery d",Order.class).getResultList();
    }

    public List<Order> findAllWithWithMemberDelivery(int offset, int limit) {
        return em.createQuery(
                "select distinct o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    //JPA Criteria로 동적쿼리 해결
//    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
//
//    }
}
