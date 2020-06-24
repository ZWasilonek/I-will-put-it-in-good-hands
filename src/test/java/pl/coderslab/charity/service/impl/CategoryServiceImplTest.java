package pl.coderslab.charity.service.impl;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    @InjectMocks
    private CategoryServiceImpl systemUnderTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testFindByName() {
//        String name = "Foundation \"without a home\"";
//        Category result = categoryRepository.findByName(name);
//        assertThat(mockCategory(), is(result));
//    }

    private Category mockCategory() {
        Category category = new Category();
        category.setName("Foundation \"without a home\"");
        category.setId(1L);
        return category;
    }
}