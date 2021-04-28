package com.ttweb55.recipeapp;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.ttweb55.recipeapp.models.*;
import com.ttweb55.recipeapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    /**
     * Connects the Recipe Service to this process
     */
    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientsService ingredientsService;

    @Autowired
    InstructionsService instructionsService;

    @Autowired
    CategoryService categoryService;


    private List<Instructions> generateInstructions(String... details) {
        return Arrays.stream(details).map(d -> {
                    Instructions instructions = new Instructions();
                    instructions.setInstructionDetails(d);
                    return instructions;
                })
                .collect(Collectors.toList());
    }

    private List<Ingredient> generateIngredients(String... names) {
        return  Arrays.stream(names).map(name -> {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setIngredientname(name);
                    return ingredient;
                })
                .collect(Collectors.toList());
    }

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        userService.deleteAll();
        roleService.deleteAll();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        Category c1 = new Category("Italian");
        Category c2 = new Category("Pizza");
        Category c3 = new Category("French");
        Category c4 = new Category("Mexican");
        Category c5 = new Category("Dessert");
        Category c6 = new Category("BBQ");
        Category c7 = new Category("Thai");
        Category c8 = new Category("Chinese");
        Category c9 = new Category("Egyptian");
        Category c10 = new Category("Hawaiian");
        Category c11 = new Category("Chuck Norris Fuel");
        Category c12 = new Category("Breakfast");
        Category c13 = new Category("Lunch");
        Category c14 = new Category("Dinner");

        c1 = categoryService.save(c1);
        c2 = categoryService.save(c2);
        c3 = categoryService.save(c3);
        c4 = categoryService.save(c4);
        c5 = categoryService.save(c5);
        c6 = categoryService.save(c6);
        c7 = categoryService.save(c7);
        c8 = categoryService.save(c8);
        c9 = categoryService.save(c9);
        c10 = categoryService.save(c10);
        c11 = categoryService.save(c11);
        c12 = categoryService.save(c12);
        c13 = categoryService.save(c13);
        c14 = categoryService.save(c14);

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

        u1 = userService.save(u1);

        //recipes

        //recipe 1
        rec1.setUser(u1);
        rec1.getCategories()
                .add(new RecipeCategory(rec1, c1));
        rec1.getCategories()
                .add(new RecipeCategory(rec1, c2));
        rec1 = recipeService.save(rec1);

        Recipe finalRec1 = rec1;
        generateIngredients("Ingredient 1", "Ingredient 2", "Ingredient3").forEach(i -> {
            i.setRecipe(finalRec1);
            ingredientsService.save(i);
        });

        generateInstructions("Do this", "Do that", "Do something else").forEach(instructions -> {
            instructions.setRecipe(finalRec1);
            instructionsService.save(instructions);
        });

        //recipe 2
        rec2.setUser(u1);
        rec2.getCategories()
                .add(new RecipeCategory(rec2, c1));
        rec2.getCategories()
                .add(new RecipeCategory(rec2, c2));
        rec2 = recipeService.save(rec2);

        Recipe finalRec2 = rec2;
        generateIngredients("Ingredient 1", "Ingredient 2", "Ingredient3").forEach(i -> {
            i.setRecipe(finalRec2);
            ingredientsService.save(i);
        });

        generateInstructions("Do this", "Do that", "Do something else").forEach(instructions -> {
            instructions.setRecipe(finalRec2);
            instructionsService.save(instructions);
        });

        //recipe 3
        rec3.setUser(u1);
        rec3.getCategories()
                .add(new RecipeCategory(rec3, c1));
        rec3.getCategories()
                .add(new RecipeCategory(rec3, c2));
        rec3 = recipeService.save(rec3);

        Recipe finalRec3 = rec3;
        generateIngredients("Ingredient 1", "Ingredient 2", "Ingredient3").forEach(i -> {
            i.setRecipe(finalRec3);
            ingredientsService.save(i);
        });

        generateInstructions("Do this", "Do that", "Do something else").forEach(instructions -> {
            instructions.setRecipe(finalRec3);
            instructionsService.save(instructions);
        });

        //recipe 4
        rec4.setUser(u1);
        rec4.getCategories()
                .add(new RecipeCategory(rec4, c1));
        rec4.getCategories()
                .add(new RecipeCategory(rec4, c2));
        rec4 = recipeService.save(rec4);

        Recipe finalRec4 = rec4;
        generateIngredients("Ingredient 1", "Ingredient 2", "Ingredient3").forEach(i -> {
            i.setRecipe(finalRec4);
            ingredientsService.save(i);
        });

        generateInstructions("Do this", "Do that", "Do something else").forEach(instructions -> {
            instructions.setRecipe(finalRec4);
            instructionsService.save(instructions);
        });

        //recipe 5
        rec5.setUser(u1);
        rec5.getCategories()
                .add(new RecipeCategory(rec5, c1));
        rec5.getCategories()
                .add(new RecipeCategory(rec5, c2));
        rec5 = recipeService.save(rec5);

        Recipe finalRec5 = rec5;
        generateIngredients("Ingredient 1", "Ingredient 2", "Ingredient3").forEach(i -> {
            i.setRecipe(finalRec5);
            ingredientsService.save(i);
        });

        generateInstructions("Do this", "Do that", "Do something else").forEach(instructions -> {
            instructions.setRecipe(finalRec5);
            instructionsService.save(instructions);
        });

        // data, user
        User u2 = new User("cinnamon",
            "1234567",
            "cinnamon@lambdaschool.local");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getRoles()
            .add(new UserRoles(u2,
                r3));
        userService.save(u2);

        // user
        User u3 = new User("barnbarn",
            "ILuvM4th!",
            "barnbarn@lambdaschool.local");
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));

        userService.save(u3);

        User u4 = new User("puttat",
            "password",
            "puttat@school.lambda");
        u4.getRoles()
            .add(new UserRoles(u4,
                r2));
        userService.save(u4);

        User u5 = new User("misskitty",
            "password",
            "misskitty@school.lambda");
        u5.getRoles()
            .add(new UserRoles(u5,
                r2));
        userService.save(u5);


        if (false)
        {
            // using JavaFaker create a bunch of regular users
            // https://www.baeldung.com/java-faker
            // https://www.baeldung.com/regular-expressions-java

            FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                new RandomService());
            Faker nameFaker = new Faker(new Locale("en-US"));

            for (int i = 0; i < 25; i++)
            {
                new User();
                User fakeUser;

                fakeUser = new User(nameFaker.name()
                    .username(),
                    "password",
                    nameFaker.internet()
                        .emailAddress());
                fakeUser.getRoles()
                    .add(new UserRoles(fakeUser,
                        r2));
//                fakeUser.getUseremails()
//                    .add(new Useremail(fakeUser,
//                        fakeValuesService.bothify("????##@gmail.com")));
                userService.save(fakeUser);
            }
        }
    }
}