package kiryasay.spring.dao;

import kiryasay.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Tom", 15, "tom7409@gmai.com"));
        people.add(new Person(++PEOPLE_COUNT,"Jon", 100, "jon7409@gmai.com"));
        people.add(new Person(++PEOPLE_COUNT,"Kirill",19, "kiry7409@gmai.com"));
        people.add(new Person(++PEOPLE_COUNT,"Bob", 23, "bob7409@gmai.com"));
        people.add(new Person(++PEOPLE_COUNT,"Mike", 14, "mike7409@gmai.com"));
    }
    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        return people.stream().filter(person->person.getId() == id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void update(int id, Person updatePerson)
    {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());

    }
    public void delete(int id)
    {
        people.removeIf(p->p.getId() == id);
    }


}
