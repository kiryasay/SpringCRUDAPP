package kiryasay.spring.dao;

import kiryasay.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index(){
        /* JDBC API
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM PERSON";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){//пока есть строки
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }
    public Person show(int id){
        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;

         */
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        //если поля класса совпадают с названиеями столбцов бд можно испоьзовать готовый RowMapper
    }
    public Person show(int id){

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
        //если есть хотя бы один объект если его нет то вернеться null
    }
    public void save(Person person){
        /*
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON VALUES (1, ?, ?, ?)");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

         */
        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?,?)", person.getName(), person.getAge(), person.getEmail());
    }
    public void update(int id, Person updatePerson)
    {
        /*
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name = ?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void delete(int id)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id = ?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //people.removeIf(p->p.getId() == id);

         */
        jdbcTemplate.update("UPDATE Person SET name = ?, age = ?, email=? WHERE id=?",
                updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id =?", id);
    }


}
