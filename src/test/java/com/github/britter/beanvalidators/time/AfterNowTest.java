package com.github.britter.beanvalidators.time;

import com.github.britter.beanvalidators.ValidationWrapper;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static com.google.common.collect.Iterables.getLast;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AfterNowTest {

    private AfterNowBean afterNowBean;
    private ValidationWrapper<AfterNowBean> validator;


    @Before
    public void setUp() {
        afterNowBean = new AfterNowBean();
        validator = new ValidationWrapper<>(afterNowBean, null);
    }

    @Test
    public void shouldValidateLocalDateAfterNow() throws Exception {
        afterNowBean.localDate = LocalDate.now().plusDays(1);

        validator.assertNoViolations("localDate");
    }

    @Test
    public void shouldNotValidateLocalDateNow() throws Exception {
        afterNowBean.localDate = LocalDate.now();

        Set<ConstraintViolation<AfterNowBean>> violations = validator.validate("localDate");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AfterNowBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be after now")));
    }

    @Test
    public void shouldNotValidateLocalDateBeforeNow() throws Exception {
        afterNowBean.localDate = LocalDate.now().minusDays(1);

        Set<ConstraintViolation<AfterNowBean>> violations = validator.validate("localDate");

        assertThat(violations, hasSize(1));
        ConstraintViolation<AfterNowBean> violation = getLast(violations);
        assertThat(violation.getMessage(), is(equalTo("must be after now")));
    }

    private static class AfterNowBean {
        @AfterNow
        private LocalDate localDate;
    }
}
