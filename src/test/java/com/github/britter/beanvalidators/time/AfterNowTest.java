package com.github.britter.beanvalidators.time;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AfterNowTest {

    private AfterNowBean afterNowBean;
    private ValidationWrapper<AfterNowBean> validator;


    @Before
    public void setUp() {
        afterNowBean = new AfterNowBean();
        validator = new ValidationWrapper<>(afterNowBean, "must be after now");
    }

    @Test
    public void shouldValidateLocalDateAfterNow() throws Exception {
        afterNowBean.localDate = LocalDate.now().plusDays(1);

        validator.assertNoViolations("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateNow() throws Exception {
        afterNowBean.localDate = LocalDate.now();

        validator.assertViolation("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateBeforeNow() throws Exception {
        afterNowBean.localDate = LocalDate.now().minusDays(1);

        validator.assertViolation("localDate");
    }

    private static class AfterNowBean {
        @AfterNow
        private LocalDate localDate;
    }
}
