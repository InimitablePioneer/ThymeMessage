package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    public void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
        System.out.println(result);
    }
    @Test
    public void notFoundMessageCode() {
        ms.getMessage("no-code", null, null);
        assertThatThrownBy(() -> ms.getMessage("no-code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    @Test
    public void notFoundMessafeCodeDefaultMessage() {
        String result = ms.getMessage("no-code", null, "기본 메세지", null);
        System.out.println(result);
        assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    public void argumentMessage() {
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
        System.out.println(result);
    }


}
