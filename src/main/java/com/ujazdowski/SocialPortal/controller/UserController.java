package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.exceptions.ExceptionMessage;
import com.ujazdowski.SocialPortal.exceptions.NotValidUserAuthenticationException;
import com.ujazdowski.SocialPortal.exceptions.UserExistsException;
import com.ujazdowski.SocialPortal.model.tables.Language;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.model.tables.UserRole;
import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.utils.Jwt;
import com.ujazdowski.SocialPortal.utils.Token;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Kontroler użytkowników
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private static org.apache.log4j.Logger logger = Logger.getLogger(UserController.class);
    private UsersRepository usersRepository;

    UserController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    /**
     * Pobieranie wszystkich użytkowników
     *
     * @return
     */
    @RequestMapping(value = "getUsers", method = RequestMethod.GET)
    public List<User> getUsers(){
        return usersRepository.findAll();
    }

    /**
     * Dodawanie nowego użytkownika
     *
     * @param preferredLanguage
     * @param firstName
     * @param secondName
     * @param email
     * @param password
     * @param birthday
     * @param phoneNumber
     * @param postalCode
     * @param street
     * @param city
     * @return
     */
    @RequestMapping(value = "newUser",
                    method = RequestMethod.POST,
                    params = {"preferredLanguage", "firstName", "secondName", "email", "password", "birthday", "phoneNumber", "postalCode", "street", "city"},
                    produces = { "application/json", "application/xml", "text/xml" },
                    consumes = MediaType.ALL_VALUE)
    public Boolean newUser(@RequestParam("preferredLanguage") Long preferredLanguage,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("secondName") String secondName,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("birthday") Date birthday,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("postalCode") String postalCode,
                           @RequestParam("street") String street,
                           @RequestParam("city") String city) throws UserExistsException {
        User user = this.usersRepository.findUserByEmail(email);
        if (user != null ){
            throw new UserExistsException();
        }

        Language language = new Language();
        language.setLanguageId(preferredLanguage);

        UserRole role = new UserRole();
        role.setUserRoleId(new Long(1));

        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User newUser = new User();

        newUser.setPreferredLanguage(language);
        newUser.setFirstName(firstName);
        newUser.setSecondName(secondName);
        newUser.setEmail(email);
        newUser.setPassword(hashPassword);
        newUser.setBirthday(birthday);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPostalCode(postalCode);
        newUser.setStreet(street);
        newUser.setCity(city);

        newUser.setRole(role);
        newUser.setLastTimeOnline(null);

        this.usersRepository.save(newUser);

        return true;
    }

    /**
     * Logowane użytkownika
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, params = {"email", "password"},
                        produces = { "application/json", "application/xml", "text/xml" }, consumes = MediaType.ALL_VALUE)
    public Token login(@RequestParam("email") String email,
                       @RequestParam("password") String password) throws NotValidUserAuthenticationException {
        User user = this.usersRepository.findUserByEmail(email);
        Token token = null;
        if (BCrypt.checkpw(password, user.getPassword())){
            token = Jwt.generateToken(user.getUserId());
        } else {
            throw new NotValidUserAuthenticationException();
        }

        return token;
    }

    /**
     * Wywoływana w przypadku wystąpienia błędu ConstraintViolationException np.: Błędny adres email.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleConstraintViolationException(Exception ex){
        logger.error(ex.getMessage());
        return new ExceptionMessage(ex.getMessage());
    }

    /**
     * Wywoływana w przypadku wystąpienia błędu UserExistsException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(UserExistsException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleUserExistsException(Exception ex){
        logger.error(ex.getMessage());
        return new ExceptionMessage(ex.getMessage());
    }
    /**
     * Wywoływana w przypadku wystąpienia błędu NotValidUserAuthenticationException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NotValidUserAuthenticationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleNotValidUserAuthenticationException(Exception ex){
        logger.error(ex.getMessage());
        return new ExceptionMessage(ex.getMessage());
    }

}
