package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemUpdateDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JdbcTemplateItemRepositoryV1Test {

    @Autowired
    private ItemRepository repository;

    @Autowired
    PlatformTransactionManager transactionManager;

    TransactionStatus status;

    @BeforeEach
    void beforeEach() {
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @AfterEach
    void afterEach() {
        transactionManager.rollback(status);
    }

    @Test
    void save() {
        //given
        Item item = new Item();
        item.setItemName("keunoh");
        item.setPrice(5000);
        item.setQuantity(5);

        //when
        Item savedItem = repository.save(item);

        //then
        assertThat(savedItem).isEqualTo(item);
    }

    @Test
    void update() {
        //given
        Item item = new Item();
        item.setItemName("keunoh");
        item.setPrice(5000);
        item.setQuantity(5);

        //when
        Item savedItem = repository.save(item);

        Long itemId = savedItem.getId();
        ItemUpdateDto itemUpdateDto = new ItemUpdateDto("keunoh2", 7000, 10);

        repository.update(itemId, itemUpdateDto);

        //then
        Item updatedItem = repository.findById(itemId).get();

        assertThat(savedItem.getId()).isEqualTo(updatedItem.getId());
        assertThat(updatedItem.getItemName()).isEqualTo("keunoh2");
        assertThat(updatedItem.getPrice()).isEqualTo(7000);
        assertThat(updatedItem.getQuantity()).isEqualTo(10);
    }

}