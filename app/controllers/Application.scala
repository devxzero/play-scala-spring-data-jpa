package controllers

import javax.inject.Inject
import javax.inject.Named

import models.{Person, PersonRepository}
import play.api.mvc.{Action, Controller}

/**
 * The main set of web services.
 */
@Named
class Application @Inject() (personRepository: PersonRepository) extends Controller {
  def index = Action {
    val person: Person = new Person
    person.firstname = "Bruce"
    person.surname = "Smith"
    val savedPerson: Person = personRepository.save(person)
    val retrievedPerson: Person = personRepository.findOne(savedPerson.id)
    Ok(views.html.index.render("Found id: " + retrievedPerson.id + " of person/people"))
  }
}