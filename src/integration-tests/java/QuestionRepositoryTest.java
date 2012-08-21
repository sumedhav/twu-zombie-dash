import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/test-application-context.xml")
public class QuestionRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private void setUpQuestions(){
        givenAQuestionWith(1, "Where is Red Fort");
        givenAnOptionFor(1, 1, "Delhi", true);
        givenAnOptionFor(1, 2, "Paris", false);
        givenAnOptionFor(1, 3, "New York", false);
        givenAQuestionWith(2, "Is it lunch time?");
        givenAnOptionFor(2, 4, "I bet it is", true);
        givenAnOptionFor(2, 5, "No thanks, fasting at the moment", false);

    }
    @Test
    public void shouldRetrieveAllQuestions() {
        setUpQuestions();
        QuestionRepository questionRepository = new QuestionRepository(jdbcTemplate);

        List<Question> questions = questionRepository.listAllQuestions();

        assertThat(questions.size(),is(2));
        Matcher<Iterable<Question>> matchTheInsertedQuestions = hasItems(
                aQuestionWith("Where is Red Fort",
                        new HashMap<String, Boolean>(){{
                            put("Delhi", true);
                            put("Paris", false);
                            put("Delhi", false);
                        }}
                ),
                aQuestionWith("Is it lunch time?", new HashMap<String, Boolean>() {{
                    put("I bet it is", true);
                    put("No thanks, fasting at the moment", false);
                }})
        );
        assertThat(questions, matchTheInsertedQuestions);
    }

    private Matcher<Question> aQuestionWith(String text, HashMap<String, Boolean> options) {
        return new QuestionMatcher(text, options);
    }

    private void givenAQuestionWith(int id, String text) {
        jdbcTemplate.execute(String.format(
                "insert into Question (ID,Text) values(%d, '%s')", id, text));
    }

    private void givenAnOptionFor(int questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.execute(String.format("insert into Option (id,question_id,text,correct) " +
                "values (%d, %d, '%s', %b)",
                optionId, questionId, text, correct));
    }

    private class QuestionMatcher extends BaseMatcher<Question> {
        private final String text;

        public QuestionMatcher(String text, Map<String, Boolean> options) {
            this.text = text;
        }

        @Override
        public boolean matches(Object item) {
            Question question = (Question) item;
            return question.getText().equals(text);
        }

        @Override
        public void describeTo(Description description) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
