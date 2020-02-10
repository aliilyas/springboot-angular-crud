import {Component, OnInit} from '@angular/core';
import {PersonService} from "../service/person-service.service";
import {Person} from "../model/person";
import {MessageService} from "../service/message-service.service";
import {Observable, Subscriber} from "rxjs";

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent implements OnInit {

  person: Person;
  editMode: boolean = false;

  constructor(private personService: PersonService, private messageService: MessageService) {
    this.person = new Person();
  }

  onSubmit() {
    this.person.hobby = this.person.hobby?.map(item => item?.value); // convert from tag object to simple string

    let updateTable = new Subscriber<Person>(result => {
      this.messageService.createPerson.emit(result);
    })

    if (!this.editMode) {
      this.personService.save(this.person).subscribe(updateTable);
    } else {
      this.personService.update(this.person).subscribe(updateTable);
      this.editMode = false;
      this.person = new Person();
    }
  }

  ngOnInit(): void {
    this.messageService.editPerson.subscribe(personToEdit => {
      this.person = personToEdit;
      this.person.hobby = this.person.hobby?.map(item => ({display: item, value: item}));
      this.editMode = true;
    })
  }
}
