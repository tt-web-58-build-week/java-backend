package com.ttweb55.recipeapp.services;

import com.ttweb55.recipeapp.RecipeApplicationTesting;
import com.ttweb55.recipeapp.models.*;
import com.ttweb55.recipeapp.repository.CategoryRepository;
import com.ttweb55.recipeapp.repository.RecipeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecipeApplicationTesting.class,
        properties =
                {
                        "command.line.runner.enabled=false"
                })
public class RecipeServiceImplUnitTestNoDB
{
    @Autowired
    private RecipeService recipeService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private InstructionsService instructionsService;

    List<User> users = new ArrayList<>();

    List<Recipe> recipes = new ArrayList<>();

    private List<Instructions> generateInstructions(String... details) {
        return Arrays.stream(details).map(d -> {
            Instructions instructions = new Instructions();
            instructions.setInstructionDetails(d);
            return instructions;
        })
                .collect(Collectors.toList());
    }

    List<Instructions> instructionsList = new ArrayList<>();

    private List<Ingredient> generateIngredients(String... names) {
        return  Arrays.stream(names).map(name -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientname(name);
            return ingredient;
        })
                .collect(Collectors.toList());
    }

    List<Ingredient> ingredientList = new ArrayList<>();
    @Before
    public void setUp() throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1.setRoleid(1);
        r2.setRoleid(2);
        r3.setRoleid(3);

        Category c1 = new Category("Italian");
        Category c2 = new Category("Pizza");
        Category c3 = new Category("French");

        c1.setCategoryid(1);
        c2.setCategoryid(2);
        c3.setCategoryid(3);

        Recipe rec1 = new Recipe();
        rec1.setTitle("Recipe 1");
        rec1.setSource("Grandma");

        Recipe rec2 = new Recipe();
        rec2.setTitle("Recipe 2");
        rec2.setSource("Gordon Ramsey");

        Recipe rec3 = new Recipe();
        rec3.setTitle("Recipe 3");
        rec3.setSource("Anthony Bourdain");

        Recipe rec4 = new Recipe();
        rec4.setTitle("Recipe 4");
        rec4.setSource("Guy Fieri");

        Recipe rec5 = new Recipe();
        rec5.setTitle("Recipe 5");
        rec5.setSource("Paula Deen");

        // admin, data, user
        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.getRoles()
                .add(new UserRoles(u1,
                        r1));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r2));
        u1.getRoles()
                .add(new UserRoles(u1,
                        r3));

        u1.setUserid(1);

        users.add(u1);

        //recipes

        //recipe 1
        rec1.setUser(u1);
        rec1.getCategories()
                .add(new RecipeCategory(rec1, c1));
        rec1.getCategories()
                .add(new RecipeCategory(rec1, c2));
        rec1.setRecipeid(1337);

        Ingredient ing1 = new Ingredient();
        ing1.setIngredientid(123);
        ing1.setIngredientname("tomato");
        ing1.setRecipe(rec1);
        rec1.getIngredients().add(ing1);

        Instructions inst1 = new Instructions();
        inst1.setInstructionsid(123);
        inst1.setInstructionDetails("Cut tomato in half");
        inst1.setRecipe(rec1);
        rec1.getInstructions().add(inst1);
        recipes.add(rec1);

        //recipe 2
        rec2.setUser(u1);
        rec2.getCategories()
                .add(new RecipeCategory(rec2, c1));
        rec2.getCategories()
                .add(new RecipeCategory(rec2, c2));

        Ingredient ing2 = new Ingredient();
        ing2.setIngredientid(321);
        ing2.setIngredientname("beef");
        ing2.setRecipe(rec2);
        rec2.getIngredients().add(ing2);

        Instructions inst2 = new Instructions();
        inst2.setInstructionsid(321);
        inst2.setInstructionDetails("thaw beef");
        inst2.setRecipe(rec2);
        rec2.getInstructions().add(inst2);

        recipes.add(rec2);

        //recipe 3
        rec3.setUser(u1);
        rec3.getCategories()
                .add(new RecipeCategory(rec3, c1));
        rec3.getCategories()
                .add(new RecipeCategory(rec3, c2));

        Ingredient ing3 = new Ingredient();
        ing3.setIngredientid(231);
        ing3.setIngredientname("rice");
        ing3.setRecipe(rec3);
        rec3.getIngredients().add(ing3);

        Instructions inst3 = new Instructions();
        inst3.setInstructionsid(231);
        inst3.setInstructionDetails("boil rice");
        inst3.setRecipe(rec3);
        rec3.getInstructions().add(inst3);
        recipes.add(rec3);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void createNewRecipe()
    {
        Category c1 = new Category("Italian");
        c1.setCategoryid(1);
        RecipeMinimum rec4 = new RecipeMinimum();
        rec4.setTitle(recipes.get(0).getTitle());
        rec4.setSource(recipes.get(0).getSource());

        List<Long> myList = new ArrayList<>();
        rec4.setCategories(myList);
        rec4.getCategories().add(1L);

        List<String> thisList = new ArrayList<>();
        rec4.setInstructions(thisList);
        rec4.getInstructions().add("Instruction 1");
        rec4.getInstructions().add("Instruction 2");

        List<String> thatList = new ArrayList<>();
        rec4.setIngredients(thatList);
        rec4.getIngredients().add("Ingredient 1");
        rec4.getIngredients().add("Ingredient 2");
        rec4.getIngredients().add("Ingredient 3");

        Mockito.when(categoryService.findByCategoryId(1L))
                .thenReturn(c1);
        Mockito.when(recipeRepository.save(any(Recipe.class)))
                .thenReturn(recipes.get(0));
        Recipe addRecipe = recipeService.createNewRecipe(rec4, users.get(0));
        assertNotNull(addRecipe);
        assertEquals(rec4.getTitle(), addRecipe.getTitle());
    }

//    @Test
//    public void updateRecipe()
//    {
//    }

//    @Test
//    public void deleteRecipe()
//    {
//        Mockito.when(recipeService.findRecipeById(1L))
//                .thenReturn(Optional.of(recipeList.get(0)));
//
//        Mockito.doNothing()
//                .when(recipeService)
//                .deleteRecipe(1L);
//
//        recipeService.deleteRecipe(1L);
//        assertEquals(2, recipeList.size());
//    }
    
//    @Test
//    public void findAllRecipesForCurrentUser()
//    {
//
//    }

//    @Test
//    public void findRecipeById()
//    {
//        Mockito.when(recipeService.findRecipeById(1L))
//                .thenReturn(Optional.of(recipes.get(0)));
//        assertEquals("Recipe 1", recipeService.findRecipeById(1L).getTitle());
//    }
}