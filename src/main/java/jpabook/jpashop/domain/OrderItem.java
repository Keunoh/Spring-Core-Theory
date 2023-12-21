package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    //3
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //주문 상품

    //2
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //주문

    private int orderPrice; //주문 가격
    private int count;  //주문 수량

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        //이 아이템이 해당 OrderItem 과 관계가 있다는 것을 어떻게 증명할 것인가?
        //Order 가 먼저 생성되고 OrderItem 이 생성된다.
        //이 메서드는 어느 시점에 호출되어야 하는 것인가?
        return orderItem;
    }

    //==비즈니스 로직==//
    //주문 취소
    public void cancel() {
        getItem().addStock(count);
        //단순히 stock 만 감소 시켜도 되는 것인가?
        //데이터베이스에 만약 데이터가 들어간 상태라면
        //기존 데이터는 지우지 않고 다른 처리를 해주어야 하는 것 아닌가?
        //예를 들면 취소라고 따로 상태값을 준다던가
    }

    //==조회 로직==//
    //주문상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}

