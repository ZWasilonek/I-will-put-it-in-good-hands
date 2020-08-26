package pl.coderslab.charity.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private Category category;
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void getInstance() {
        category = new Category();
        category.setId(1L);
        category.setName("Zabawki");
    }

    @Test
    final void testCreateCategory() {
        //when
        when(categoryRepository.save(category)).thenReturn(category);
        Category savedCategory = categoryService.create(this.category);
        //then
        verify(categoryRepository).save(this.category);
        assertThat(savedCategory).isNotNull();
    }

    @Test
    final void testFindByIdCategory() {
        //when
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        Category foundedCategory = categoryService.findById(category.getId());
        //then
        verify(categoryRepository).findById(anyLong());
        assertThat(foundedCategory).isNotNull();
    }

    @Test
    final void testUpdateCategory() {
        //given
        given(categoryRepository.findById(anyLong())).willReturn(Optional.ofNullable(category));
        String categoryName = "Książki";
        category.setName(categoryName);
        //when
        categoryService.update(category);
        //then
        verify(categoryRepository).save(any(Category.class));
        assertEquals(categoryName, category.getName());
    }

    @Test
    final void testFindAllCategories() {
        //given
        List<Category> categories = List.of(category);
        when(categoryRepository.findAll()).thenReturn(categories);
        //when
        Set<Category> foundedCategories = categoryService.findAll();
        //then
        verify(categoryRepository).findAll();
        assertThat(foundedCategories).hasSize(1);
    }

    @Test
    void remove() {
        //given
        given(categoryRepository.findById(anyLong())).willReturn(Optional.ofNullable(category));
        //when | then
        assertDoesNotThrow(() -> {
            categoryService.remove(category);
            verify(categoryRepository, times(1)).delete(category);
        });
    }

    @Test
    final void testRemoveByIdCategory() {
        //given
        given(categoryRepository.findById(anyLong())).willReturn(Optional.ofNullable(category));
        //when
        categoryService.removeById(category.getId());
        //then
        verify(categoryRepository).deleteById(anyLong());
    }

    @Test
    final void testFindByNameCategory() {
        //given
        when(categoryRepository.findByName(category.getName())).thenReturn(category);
        //when
        Category foundedCategory = categoryService.findByName(category.getName());
        //then
        assertThat(foundedCategory).isNotNull();
        verify(categoryRepository).findByName(category.getName());
    }

}